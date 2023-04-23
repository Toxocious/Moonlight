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
- [Disclaimer](#disclaimer)
- [Table of Contents](#table-of-contents)
- [Environment Variables](#environment-variables)
- [Server Source Code](#server-source-code)
- [Auth Hook Source Code](#auth-hook-source-code)
- [Launcher Source Code](#launcher-source-code)
- [Contributing](#contributing)
  - [General Stance](#general-stance)
- [License](#license)



## Environment Variables
Some aspects of the code-base relies on a filled out `.env` file.
Be sure to look at the `.env.local` file for what a valid example looks like.


## Server Source Code
You can find the source code for the server in the [server](./server) directory.
> The applicable `dat` and `wz` files are not included.

## Auth Hook Source Code
You can find the source code for the server auth hook in the [auth_hook](./auth_hook) directory.

## Launcher Source Code
You can find the source code and applicable README for the custom launcher in the [launcher](./launcher) directory.



## Contributing
### General Stance
In general, we welcome pull requests that fix bugs or builds upon an existing feature.

1. Fork the Project
2. Create your Feature Branch (``git checkout -b feature/feature-name``)
3. Commit your Changes (``git commit -m 'Add some feature-name'``)
4. Push to the Branch (``git push origin feature/feature-name``)
5. Open a Pull Request



## License
This project is licensed under GNU GPL 3.

For more information about the license, check out the [LICENSE](LICENSE).
