#include "discord_rpc.h"
#include "discord_register.h"
#include <Windows.h>
#include <stdint.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "Discord.h"

#define DBGPRINTA(fmt, ...)
// one sec, I'll transfer the project to your
// never mind, gotta send you the lib, source is complicated

static DiscordRichPresence discordPresence;

static void handleDiscordReady(const DiscordUser* connectedUser)
{
	if (discordPresence.state != NULL) Discord_UpdatePresence(&discordPresence);
	DBGPRINTA("\nDiscord: connected to user %s#%s - %s\n",
		connectedUser->username,
		connectedUser->discriminator,
		connectedUser->userId);
}

static void handleDiscordDisconnected(int errcode, const char* message)
{
	DBGPRINTA("\nDiscord: disconnected (%d: %s)\n", errcode, message);
}

static void handleDiscordError(int errcode, const char* message)
{
	DBGPRINTA("\nDiscord: error (%d: %s)\n", errcode, message);
}

static void handleDiscordJoin(const char* secret)
{
	DBGPRINTA("\nDiscord: join (%s)\n", secret);
}

static void handleDiscordSpectate(const char* secret)
{
	DBGPRINTA("\nDiscord: spectate (%s)\n", secret);
}

static void handleDiscordJoinRequest(const DiscordUser* request)
{
	int response = -1;
	DBGPRINTA("\nDiscord: join request from %s#%s - %s\n",
		request->username,
		request->discriminator,
		request->userId);
	// TODO
	if (response != -1) {
		Discord_Respond(request->userId, response);
	}
}

unsigned DiscordStart(void* param)
{
	memset(&discordPresence, 0, sizeof(discordPresence));

	DiscordEventHandlers handlers;
	memset(&handlers, 0, sizeof(handlers));
	handlers.ready = handleDiscordReady;
	handlers.disconnected = handleDiscordDisconnected;
	handlers.errored = handleDiscordError;
	handlers.joinGame = handleDiscordJoin;
	handlers.spectateGame = handleDiscordSpectate;
	handlers.joinRequest = handleDiscordJoinRequest;
	char discordClientId[32] = "938393506811088956";
	GetEnvironmentVariableA("DISCORD_CLIENTID", discordClientId, 32);
	Discord_Initialize(discordClientId, &handlers, 0, NULL);

	char gameLauncherPath[1024] = { 0 };
	gameLauncherPath[1] = NULL;
	GetEnvironmentVariableA("MU2_LAUCHER_PATH", gameLauncherPath + 1, 1023);
	if (gameLauncherPath[1]) {
		gameLauncherPath[0] = '"';
		strcat(gameLauncherPath, "\" \"%1\"");
		DBGPRINTA("Registering launcher path (%s) to discord protocol", gameLauncherPath);
	}
	Discord_Register(discordClientId, gameLauncherPath);

	discordPresence.state = malloc(256);
	discordPresence.details = malloc(512);
	discordPresence.startTimestamp = time(0);
	// discordPresence.endTimestamp = time(0) + 5 * 60;
	discordPresence.largeImageKey = malloc(64);
	discordPresence.smallImageKey = malloc(64);
	strcpy(discordPresence.state, "Mapling");
	strcpy(discordPresence.details, "Playing v214");
	strcpy(discordPresence.largeImageKey, "mapleimage");
    //strcpy(discordPresence.smallImageKey, "image2");

	GetEnvironmentVariableA("DISCORD_STATE", discordPresence.state, 256);
	//GetEnvironmentVariableA("DISCORD_DETAILS", discordPresence.details, 512);
	GetEnvironmentVariableA("DISCORD_LIMAGEKEY", discordPresence.largeImageKey, 64);
	GetEnvironmentVariableA("DISCORD_SIMAGEKEY", discordPresence.smallImageKey, 64);
	//discordPresence.partyId = "party1234";
	//discordPresence.partySize = 1;
	//discordPresence.partyMax = 6;
	//discordPresence.partyPrivacy = DISCORD_PARTY_PUBLIC;
	//discordPresence.matchSecret = "xyzzy";
	//discordPresence.joinSecret = "join";
	//discordPresence.spectateSecret = "look";
	//discordPresence.instance = 0;
	Discord_UpdatePresence(&discordPresence);

#ifdef DISCORD_DISABLE_IO_THREAD
	Discord_UpdateConnection();
#endif
	int* signal = (int*)param;
	do {
		Discord_RunCallbacks();
		Sleep(3000);
	} while (*signal == 0);

	free(discordPresence.state);
	free(discordPresence.details);
	free(discordPresence.largeImageKey);
	free(discordPresence.smallImageKey);
	Discord_Shutdown();
	return 0;
}