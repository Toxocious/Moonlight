// excuse me for ugly code. this will work tho

#include <ws2spi.h>
#include <mswsock.h>
#include <Windows.h>

#include <fstream>
#include <sstream>
#include <format>
#include <mutex>

#include "MinHook.h"

#define HOOK(a, b) hook(reinterpret_cast<void**>(&a), b)

std::string g_sIPAddress;

__declspec(dllexport) void windia_me() { }

template<typename T>
void write_to_mem(const uintptr_t address, T value)
{
    unsigned long oldprot;
    VirtualProtect(reinterpret_cast<void*>(address), sizeof(value), PAGE_EXECUTE_READWRITE, &oldprot);
    *reinterpret_cast<T*>(address) = value;
    VirtualProtect(reinterpret_cast<void*>(address), sizeof(value), oldprot, nullptr);
}

inline void write_jmp(const uintptr_t from, const uintptr_t to)
{
	if(const size_t delta = to - from; delta + 2 <= 127)
	{
		write_to_mem<uint8_t>(from, 0xEB);
		write_to_mem<uint8_t>(from + 1, static_cast<uint8_t>(delta - 2));
	}

	else
	{
		write_to_mem<uint8_t>(from, 0xE9);
		write_to_mem<size_t>(from + 1, delta - 5);
	}
}

inline void write_ret(const uint32_t address)
{
	constexpr uint8_t ret[] =
	{
		0x33, 0xC0,	// xor eax, eax
		0xC3		// ret
	};

	unsigned long oldprot;
	VirtualProtect(reinterpret_cast<void*>(address), sizeof(ret), PAGE_EXECUTE_READWRITE, &oldprot);

	for(int i = 0; i < sizeof(ret); i++)
	{
		*reinterpret_cast<uint8_t*>(address + i) = ret[i];
	}

	VirtualProtect(reinterpret_cast<void*>(address), sizeof(ret), oldprot, nullptr);
}

template <typename T, typename Y>
void hook(T** pTrampoline, Y pDetour)
{
	if(auto pTarget = *pTrampoline; MH_CreateHook(pTarget, pDetour, pTrampoline) == MH_OK)
	{
		MH_EnableHook(pTarget);
	}
}

uintptr_t get_fn(const std::string_view mod, const std::string_view fn)
{
	auto module = GetModuleHandleA(mod.data());

	if(module == nullptr)
	{
		module = LoadLibraryA(mod.data());
	}

	return reinterpret_cast<uintptr_t>(GetProcAddress(module, fn.data()));
}

namespace hooks
{
	WSPPROC_TABLE _WSPTable{};
	uint32_t ulServerAddress{};
	uintptr_t _CreateMutexA;
	uintptr_t _CreateWindowExA;
	uintptr_t _WSPStartup;

	HANDLE __cdecl InitSafeDll()
	{
		return LoadLibraryA("ws2_32.dll");
	}

	FARPROC __cdecl MyGetProcAddress(const HINSTANCE hModule, const char* lpProcName)
	{
		return GetProcAddress(hModule, lpProcName);
	}

	int __stdcall connect(const SOCKET s, const sockaddr* name, const int namelen, const LPWSABUF lpCallerData, const LPWSABUF lpCalleeData, const LPQOS lpSQOS, const LPQOS lpGQOS, const LPINT lpErrno)
	{
		// to avoid static linking
		const auto _ntohs = reinterpret_cast<decltype(&ntohs)>(get_fn("ws2_32.dll", "ntohs"));
		const auto _inet_addr = reinterpret_cast<decltype(&inet_addr)>(get_fn("ws2_32.dll", "inet_addr"));

		const auto pName = reinterpret_cast<sockaddr_in*>(const_cast<sockaddr*>(name));
		const auto port = pName->sin_port;

		if(const uint16_t usPort = _ntohs(port); usPort >= 7000 && usPort <= 10000)
		{
			std::memcpy(&ulServerAddress, &pName->sin_addr, sizeof(pName->sin_addr));
			pName->sin_addr.S_un.S_addr = _inet_addr(g_sIPAddress.data());
		}
		
		return _WSPTable.lpWSPConnect(s, name, namelen, lpCallerData, lpCalleeData, lpSQOS, lpGQOS, lpErrno);
	}

	int __stdcall getpeername(const SOCKET s, sockaddr* name, const LPINT namelen, const LPINT lpErrno)
	{
		const auto nRet = _WSPTable.lpWSPGetPeerName(s, name, namelen, lpErrno);
		const auto pService = reinterpret_cast<sockaddr_in*>(name);
		std::memcpy(&pService->sin_addr, &ulServerAddress, sizeof(ulServerAddress));

		return nRet;
	}

	int __stdcall WSPStartup(const WORD wVersionRequested, const LPWSPDATA lpWSPData, const LPWSAPROTOCOL_INFOW lpProtocolInfo, const WSPUPCALLTABLE UpcallTable, const LPWSPPROC_TABLE lpProcTable)
	{
		const auto nRet = reinterpret_cast<decltype(&::WSPStartup)>(_WSPStartup)(wVersionRequested, lpWSPData, lpProtocolInfo, UpcallTable, lpProcTable);
		_WSPTable = *lpProcTable;
		lpProcTable->lpWSPConnect = connect;
		lpProcTable->lpWSPGetPeerName = getpeername;

		return nRet;
	}

	HANDLE __stdcall CreateMutexA(const LPSECURITY_ATTRIBUTES lpMutexAttributes, const BOOL bInitialOwner, const LPCSTR lpName)
	{
		if(lpName != nullptr && std::strcmp(lpName, "WvsClientMtx") == 0)
		{
			static std::once_flag flag;

			std::call_once(flag, []
			{
				// bypass themida code integrity check
				write_jmp(0x019DD7AD, 0x019DD844);

				// bypass ngs
				write_ret(0x01960B00);

				// ws2 bypass
				write_jmp(0x00D293B0, reinterpret_cast<uintptr_t>(&InitSafeDll));
				write_jmp(0x00D292A0, reinterpret_cast<uintptr_t>(&MyGetProcAddress));

				// logo skipper
				write_jmp(0x00B31CD0, 0x00B2F740);
			});

			return reinterpret_cast<decltype(&::CreateMutexA)>(_CreateMutexA)(lpMutexAttributes, bInitialOwner, std::format("WvsClientMtx-{}", GetCurrentProcessId()).data());
		}					   

		return reinterpret_cast<decltype(&::CreateMutexA)>(_CreateMutexA)(lpMutexAttributes, bInitialOwner, lpName);
	}

	HWND __stdcall CreateWindowExA(const DWORD dwExStyle, const LPCSTR lpClassName, const LPCSTR lpWindowName, const DWORD dwStyle, const int X, const int Y, const int nWidth, const int nHeight, const HWND hWndParent, const HMENU hMenu, const HINSTANCE hInstance, const LPVOID lpParam)
	{
		if(lpClassName != nullptr && (std::strcmp(lpClassName, "StartUpDlgClass") == 0 || std::strcmp(lpClassName, "NexonADBrowser") == 0))
		{
			return nullptr;
		}

		return reinterpret_cast<decltype(&::CreateWindowExA)>(_CreateWindowExA)(dwExStyle, lpClassName, lpWindowName, dwStyle, X, Y, nWidth, nHeight, hWndParent, hMenu, hInstance, lpParam);
	}
}

extern int __stdcall DllMain(void* hModule, const unsigned int ul_reason_for_call, void* lpReserved)
{
    if(ul_reason_for_call == 1)
    {
        std::getline(std::ifstream("server_ip.txt"), g_sIPAddress);

		MH_Initialize();
		hooks::_CreateMutexA = get_fn("kernel32.dll", "CreateMutexA");
		HOOK(hooks::_CreateMutexA, hooks::CreateMutexA);

		hooks::_CreateWindowExA = get_fn("user32.dll", "CreateWindowExA");
		HOOK(hooks::_CreateWindowExA, hooks::CreateWindowExA);

		hooks::_WSPStartup = get_fn("mswsock.dll", "WSPStartup");
		HOOK(hooks::_WSPStartup, hooks::WSPStartup);
    }

    return 1;
}
