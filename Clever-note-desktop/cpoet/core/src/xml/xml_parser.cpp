// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include <QFile>
#include "inc/core.h"
#include "inc/xml/xml_parser.h"
#include "inc/xml/xml_handle.h"

XmlMapper *XmlParser::parseFile(const QString &file) {
    auto *f = new QFile(file);
    XmlMapper *xmlMapper = nullptr;
    if (f->open(QIODevice::ReadOnly)) {
        auto stream = new QTextStream(f);
        QString content = stream->readAll();
        xmlMapper = parse(content);
        delete stream;
        f->close();
    } else {
        qDebug() << "Xml解析，读取[" << file << "]文件失败";
    }
    delete f;
    return xmlMapper;
}

XmlMapper *XmlParser::parse(const QString &content) {
    return parse(content, XmlParseConfig());
}

XmlMapper *XmlParser::parse(const QString &content, const XmlParseConfig &config) {
    auto mapper = handleXmlRead(content, config);
    return mapper == nullptr ? nullptr : new XmlMapper(mapper);
}
