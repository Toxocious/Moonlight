cmake_minimum_required (VERSION 3.8)

project ("mushroom_redirector")

option(BUILD_SHARED_LIBS "Build shared libraries (.dll/.so) instead of static ones (.lib/.a)" ON)
add_library(mushroom_redirector "mushroom_redirector.cpp" "buffer.c" "buffer.h" "hde32.c" "hde32.h" "hook.c" "MinHook.h" "pstdint.h" "table32.h" "trampoline.c" "trampoline.h")
target_link_options(mushroom_redirector PUBLIC /SUBSYSTEM:WINDOWS /NODEFAULTLIB:MSVCRT)

if (CMAKE_VERSION VERSION_GREATER 3.12)
	set_property(TARGET mushroom_redirector PROPERTY CXX_STANDARD 20)
endif()

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_CONFIGURATION_TYPES "Release" CACHE STRING "" FORCE)
set(CMAKE_CXX_FLAGS_RELEASE "/MT /O1")
set(CMAKE_CXX_FLAGS_DEBUG "/MTd")
