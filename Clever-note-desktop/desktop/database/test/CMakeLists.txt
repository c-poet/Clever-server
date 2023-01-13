add_executable(notebook_test notebook.cpp)
target_link_libraries(notebook_test PRIVATE database)
add_test(NAME notebook_test COMMAND notebook_test)
