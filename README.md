<div align="center">
  <h1 align="center">Moonlight</h1>

  **Moonlight** is the source-code of a Maplestory GMS v214 private server, including an authentication hook and launcher.

  This is, of course, based upon Swordie's v214 source, so credits go to their phenomenal development team.

  <img src="https://img.shields.io/github/issues/Toxocious/Moonlight?style=for-the-badge&logo=appveyor" />
  <img src="https://img.shields.io/github/forks/Toxocious/Moonlight?style=for-the-badge&logo=appveyor" />
  <img src="https://img.shields.io/github/stars/Toxocious/Moonlight?style=for-the-badge&logo=appveyor" />
  <img src="https://img.shields.io/github/license/Toxocious/Moonlight?style=for-the-badge&logo=appveyor" />
  <a href="https://visitorbadge.io/status?path=https%3A%2F%2Fgithub.com%2FToxocious%Moonlight">
    <img src="https://api.visitorbadge.io/api/visitors?path=https%3A%2F%2Fgithub.com%2FToxocious%Moonlight&countColor=%2337d67a" />
  </a>
  <br /><br />

  Check us out on Discord and consider starring the repository if you liked it!

  <a href="https://discord.gg/NRZ2zWfpwK" target="_blank">
    <img src="https://discord.com/api/guilds/1002005327555862620/widget.png?style=banner2" alt="Discord Banner" />
  </a>
</div>
<br />



## Disclaimer
I do not guarantee that this will make any substantial progress. Use at your own peril.



## Table of Contents
- [Environment Variables](#environment-variables)
- [Source Code Rundown](#source-code-rundown)
  - [Server Source Code](#server-source-code)
  - [Auth Hook Source Code](#auth-hook-source-code)
  - [Launcher Source Code](#launcher-source-code)
- [Installation](#installation)
  - [Requirements](#requirements)
  - [v214 Client Files](#v214-client-files)
  - [Server WZ Files](#server-wz-files)
  - [Start The Server](#start-the-server)
- [Contributing](#contributing)
- [Privacy Policy](#privacy-policy)
- [License](#license)



# Environment Variables
Some aspects of the code-base relies on a filled out `.env` file.
Be sure to look at the `.env.local` file for what a valid example looks like.


# Source Code Rundown
## Server Source Code
You can find the source code for the server in the [server](./server) directory.
> The applicable `dat` and `wz` files are not included.

## Auth Hook Source Code
You can find the source code for the server auth hook in the [auth_hook](./auth_hook) directory.

## Launcher Source Code
You can find the source code and applicable README for the custom launcher in the [launcher](./launcher) directory.

# Installation
## Requirements
The server and its database are hosted in Docker containers, so you will need [Docker](https://www.docker.com/) installed on your machine.
> For Windows users, do be sure to pick up Docker Desktop and not just the Docker daemon.

## v214 Client Files
We highly recommend getting all of GMS v214 through Steam via the use of Depot Downloader.

1. Open the [Steam console](steam://nav/console)
2. Run `download_depot 216150 216151 976750626611673486`
3. Wait until the download has finished

> If this method does not work for you due to being outside of North America, there should be publicly available Mega.nz download links out there.

## Server WZ Files
Due to the immense file size of dumped WZ files, we do not include them in this repository.

These files are required though, and you should dump them yourself.

1. Download the latest release of [HaRepacker Resurrected](https://github.com/lastbattle/Harepacker-resurrected/releases/)
2. Using the v214 client files you dumped earlier, open HaRepacker and load all of the client's WZ files
3. Using HaRepacker, dump all of the WZ files by clicking on Tools -> Export File(s) To -> Private Server XML
4. Wait until the dump has finished and move the dumped WZ files to Server/wz

## Start The Server
Once all previous steps have been completed, you may run the server by typing `./start.sh` in your terminal while in this directory.

The first time you start the server, you may have to wait for several minutes as the Docker images and volumes are set-up, as well as for when the server generates all necessary DAT file data.



# Contributing
See our [Contributing](.github/contributing.md) standards for information on contributing to development.



# Privacy Policy
In Moonlight, information is kept between you and our server and all sensitive information is encrypted.

For more information, see our [Privacy Policy](./github/PRIVACY.md).



# License
This project is licensed under GNU GPL 3.

For more information about the license, check out the [LICENSE](LICENSE).
