add_executable(zlib_test zlib.cpp)
target_include_directories(zlib_test PRIVATE ${NOTE_LIB_PATH}/zlib-1.2.11)
target_link_libraries(zlib_test PRIVATE c_poet_core zlib)
add_test(NAME zlib_test COMMAND zlib_test)

add_executable(zlib_out_test zlib_out.cpp)
target_include_directories(zlib_out_test PRIVATE ${NOTE_LIB_PATH}/zlib-1.2.11)
target_link_libraries(zlib_out_test PRIVATE c_poet_core zlib)
add_test(NAME zlib_out_test COMMAND zlib_out_test)
