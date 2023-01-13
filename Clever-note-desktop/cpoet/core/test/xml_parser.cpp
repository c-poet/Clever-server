// Copyright (c) 2022 Wanggf(CPoet)
// Project: Clever-note-desktop
// Author: CPoet
// Date: 2022/10/26

#include "cpoet/core/inc/xml/xml_parser.h"
#include "cpoet/core/inc/core.h"

void testXmlParser() {
#ifdef Q_OS_WINDOWS
    auto result = XmlParser::parseFile("E:/OpenSource/Clever-note-desktop/theme/default/default.xml");
#else
    auto result = XmlParser::parseFile("/home/cpoet/OpenSource/Clever-note-desktop/theme/default/default.xml");
#endif
    if (result != nullptr && !result->isEmpty()) {
        qDebug() << "打印结果: ";
        qDebug() << (*result->getAllMapper());

        auto *map = result->getAsMap("theme.files");
        qInfo() << *map;
        delete map;
    }
    delete result;
}

int main(int argc, char *argv[]) {
    testXmlParser();
    return 0;
}
