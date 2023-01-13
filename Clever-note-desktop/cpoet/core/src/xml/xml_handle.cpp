// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "inc/core.h"
#include "inc/xml/xml_handle.h"

// 读取处理函数声明
typedef void (*XmlReadHandle)(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry);

QString XmlHandleContext::joinPrefix(const QString &suffix) const {
    return this->prefix.isEmpty() ? suffix : this->prefix + this->config.separator + suffix;
}

XmlHandleContext *createXmlHandleContext(const XmlParseConfig &config) {
    auto *context = new XmlHandleContext();
    context->config = config;
    context->duplicateKeyCount = new QMap<QStringRef, int>;
    context->handles = new QMap<QXmlStreamReader::TokenType, void *>();
    (*context->handles)[QXmlStreamReader::StartDocument] = (void *) handleDocument;
    (*context->handles)[QXmlStreamReader::StartElement] = (void *) handleElement;
    (*context->handles)[QXmlStreamReader::Characters] = (void *) handleCharacters;
    return context;
}

void freeXmlHandleContext(XmlHandleContext *context) {
    delete context->duplicateKeyCount;
    delete context->handles;
    delete context;
}

void callXmlHandle(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry) {
    auto xmlReadHandle = (XmlReadHandle) (*context->handles)[reader->tokenType()];
    if (xmlReadHandle != nullptr) {
        xmlReadHandle(reader, context, carry);
    }
}

QMap<QString, QString> *handleXmlRead(const QString &content, const XmlParseConfig &config) {
    auto *context = createXmlHandleContext(config);
    auto *carry = new QMap<QString, QString>();
    auto *reader = new QXmlStreamReader(content);
    startHandleRead(reader, context, carry);
    delete reader;
    freeXmlHandleContext(context);
    return carry;
}

void startHandleRead(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry) {
    while (!reader->atEnd()) {
        callXmlHandle(reader, context, carry);
        reader->readNext();
    }
}

void handleDocument(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry) {
    while (reader->readNext() != QXmlStreamReader::EndDocument) {
        callXmlHandle(reader, context, carry);
    }
}

void handleElement(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry) {
    QString prefix = context->prefix;
    QString nextKey = context->joinPrefix(reader->name().toString());
    context->prefix = nextKey;
    // 判断是否有重复的key
    QStringRef keyRef = QStringRef(&context->prefix);
    int keyCount = (*context->duplicateKeyCount)[keyRef];
    if (keyCount != 0) {
        nextKey = context->joinPrefix(QString::number(keyCount));
    }
    (*context->duplicateKeyCount)[keyRef] = ++keyCount;
    context->prefix = nextKey;
    // 处理标签上的属性值
    auto attrs = reader->attributes();
    if (!attrs.isEmpty()) {
        for (auto &attr: attrs) {
            qDebug() << "attr " << attr.name() << ":" << attr.value();
            (*carry)[context->joinPrefix(attr.name().toString())] = attr.value().toString();
        }
    }
    while (reader->readNext() != QXmlStreamReader::EndElement) {
        callXmlHandle(reader, context, carry);
    }
    context->prefix = prefix;
}

void handleCharacters(QXmlStreamReader *reader, XmlHandleContext *context, QMap<QString, QString> *carry) {
    auto text = reader->text().trimmed();
    if (!text.isEmpty()) {
        QString textKey = context->joinPrefix(context->config.textKey);
        qDebug() << "Characters " << textKey << ":" << text;
        (*carry)[textKey] = text.toString();
    }
}
