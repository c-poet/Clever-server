#ifndef PLUGIN_GITHUB_CORE_H
#define PLUGIN_GITHUB_CORE_H

// GNU
#ifdef __GNUC__
#if __GNUC__ >= 4
// 动态库外部可见性
#define PUBLIC_API __attribute__ ((visibility ("default")))
// 动态库隐藏可见性
#define PRIVATE_API __attribute__ ((visibility ("hide")))
#else
#define PUBLIC_API
#define PRIVATE_API
#endif
#endif

// MSVC
#ifdef _MSC_VER
#define PUBLIC_API __declspec(dllexport)
#define PRIVATE_API
#endif

#endif