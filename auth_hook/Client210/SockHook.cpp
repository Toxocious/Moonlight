#include <WinSock2.h>
#include "Global.h"
#include <WS2spi.h>

WSPPROC_TABLE g_ProcTable;
DWORD nexonServer;

int WINAPI WSPGetPeerName_Hook(SOCKET s, struct sockaddr* name, LPINT namelen, LPINT lpErrno)
{
	int nRet = g_ProcTable.lpWSPGetPeerName(s, name, namelen, lpErrno);

	if (nRet == SOCKET_ERROR)
	{
		Log("[WSPGetPeerName] ErrorCode: %d", *lpErrno);
	}
	else
	{
		char szAddr[50];
		DWORD dwLen = 50;
		WSAAddressToStringA((sockaddr*)name, *namelen, NULL, szAddr, &dwLen);

		sockaddr_in* service = (sockaddr_in*)name;

		auto nPort = ntohs(service->sin_port);

		if (nPort >= OPT_PORT_LOW && nPort <= OPT_PORT_HIGH)
		{
			memcpy(&service->sin_addr, &nexonServer, sizeof(DWORD));
			struct in_addr addr;
			addr.s_addr = (long)nexonServer;
			Log("[WSPGetPeerName] Replaced: %s", inet_ntoa(addr));
		}
		else
		{
			Log("[WSPGetPeerName] Original: %s", szAddr);
		}
	}

	return  nRet;
}

int WINAPI WSPConnect_Hook(SOCKET s, const struct sockaddr* name, int namelen, LPWSABUF lpCallerData, LPWSABUF lpCalleeData, LPQOS lpSQOS, LPQOS lpGQOS, LPINT lpErrno)
{
	char szAddr[50];
	DWORD dwLen = 50;
	WSAAddressToStringA((sockaddr*)name, namelen, NULL, szAddr, &dwLen);

	sockaddr_in* service = (sockaddr_in*)name;

	//if (strstr(szAddr, OPT_ADDR_SEARCH))
	if (!strstr(szAddr, "443")
		&& !strstr(szAddr, "127.0.0.1")
		&& !strstr(szAddr, "localhost")
		&& !strstr(szAddr, "80"))
	{
		memcpy(&nexonServer, &service->sin_addr, sizeof(DWORD));
		service->sin_addr.S_un.S_addr = inet_addr(OPT_ADDR_HOSTNAME);
		Log("[WSPConnect] Replaced: %s", OPT_ADDR_HOSTNAME);
	}
	else
	{
		Log("[WSPConnect] Original: %s", szAddr);
	}

	return g_ProcTable.lpWSPConnect(s, name, namelen, lpCallerData, lpCalleeData, lpSQOS, lpGQOS, lpErrno);
}

bool Hook_WSPStartup(bool bEnable)
{
	static auto _WSPStartup = decltype(&WSPStartup)(GetFuncAddress("MSWSOCK", "WSPStartup"));

	decltype(&WSPStartup) Hook = [](WORD wVersionRequested, LPWSPDATA lpWSPData, LPWSAPROTOCOL_INFOW lpProtocolInfo, WSPUPCALLTABLE UpcallTable, LPWSPPROC_TABLE lpProcTable) -> int
	{
		Log("[WSPStartup] Hijacked ProcTable");

		int ret = _WSPStartup(wVersionRequested, lpWSPData, lpProtocolInfo, UpcallTable, lpProcTable);
		g_ProcTable = *lpProcTable;

		lpProcTable->lpWSPConnect = WSPConnect_Hook;
		lpProcTable->lpWSPGetPeerName = WSPGetPeerName_Hook;

		return ret;
	};

	return SetHook(bEnable, reinterpret_cast<void**>(&_WSPStartup), Hook);
}

bool HookSockApi()
{
	return  Hook_WSPStartup(true);
}