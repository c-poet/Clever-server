list_include_file(INC_FILE_LIST "${CMAKE_CURRENT_SOURCE_DIR}/inc")
list_source_file(SRC_FILE_LIST "${CMAKE_CURRENT_SOURCE_DIR}/src")
add_library(c_poet_theme SHARED ${INC_FILE_LIST} ${SRC_FILE_LIST})
target_link_libraries(c_poet_theme PRIVATE c_poet_core)
