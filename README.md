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
I'm extremely interested in learning about the development of private servers, including client edits, wz editing, and server-sided security to deter hackers and client/wz editors.

I do not guarantee that this will go anywhere, but I hope that it does!

> 'Moonlight' is a temporary working name for this project, which is due to change later on down the line.



## Table of Contents
- [Environment Variables](#environment-variables)
- [Source Code Rundown](#source-code-rundown)
  - [Server Source Code](#server-source-code)
  - [Auth Hook Source Code](#auth-hook-source-code)
  - [Launcher Source Code](#launcher-source-code)
- [Installation](#installation)
  - [v214 Client Files](#v214-client-files)
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
## v214 Client Files
We highly recommend getting all of GMS v214 through Steam via the use of Depot Downloader.

1. Open the [Steam console](steam://nav/console)
2. Run `download_depot 216150 216151 976750626611673486`
3. Wait until the download has finished

> If this method does not work for you due to being outside of North America, there should be publicly available Mega.nz download links out there.



# Contributing
See our [Contributing](.github/contributing.md) standards for information on contributing to development.



# Privacy Policy
In Moonlight, information is kept between you and our server and all sensitive information is encrypted.

For more information, see our [Privacy Policy](./github/PRIVACY.md).



# License
This project is licensed under GNU GPL 3.

For more information about the license, check out the [LICENSE](LICENSE).
