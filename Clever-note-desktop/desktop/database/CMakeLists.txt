list_include_file(INC_FILE_LIST "${CMAKE_CURRENT_SOURCE_DIR}/inc")
list_source_file(SRC_FILE_LIST "${CMAKE_CURRENT_SOURCE_DIR}/src")
add_library(database SHARED ${INC_FILE_LIST} ${SRC_FILE_LIST})
target_link_libraries(database PRIVATE c_poet_core c_poet_database)

add_subdirectory(test)
