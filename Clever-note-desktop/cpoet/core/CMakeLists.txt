# 替换模板文件 
configure_file(${CMAKE_CURRENT_SOURCE_DIR}/inc/cpoet.h.in ${CMAKE_CURRENT_SOURCE_DIR}/inc/cpoet.h)

list_include_file(INC_FILE_LIST "${CMAKE_CURRENT_SOURCE_DIR}/inc")
list_source_file(SRC_FILE_LIST "${CMAKE_CURRENT_SOURCE_DIR}/src")
add_library(c_poet_core SHARED ${INC_FILE_LIST} ${SRC_FILE_LIST})
target_link_libraries(c_poet_core PUBLIC Qt${QT_VERSION_MAJOR}::Widgets)
add_subdirectory(test)
