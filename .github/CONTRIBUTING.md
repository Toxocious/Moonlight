First, thank you for contributing to Moonlight!
We're a community-driven project and we appreciate improvements, both large and small.

Here are some tips to make sure your Pull Request (PR) can be merged smoothly:

1. If you want to add a feature or make some change to Moonlight, consider [filing an issue](https://github.com/toxocious/moonlight/issues/new) describing your idea first. This will give the Moonlight community a chance to discuss the idea, offer suggestions and pointers, and make sure what you're thinking of fits with the style and direction of where we're going. If you want a more free-form chat, [join our Discord](https://discord.gg/NRZ2zWfpwK).
2. Resist the temptation to change more than one thing in your PR. Keeping PRs focused on a single change makes them much easier to review and accept. If you want to change multiple things, or clean up/refactor the code, make a new branch and submit those changes as a separate PR.
3. Don't forget to add a description of your change to [docs/CHANGELOG.md](CHANGELOG.md) so it'll be included in the release notes!



## Developer Quick Start
- [Developer Quick Start](#developer-quick-start)
- [Prerequisites](#prerequisites)
  - [Getting Maplestory GMS v214](#getting-maplestory-gms-v214)
- [Clone The Repository](#clone-the-repository)
- [Server Development](#server-development)
- [Authentication Hook Development](#authentication-hook-development)
- [Moonlight Launcher Development](#moonlight-launcher-development)
- [Contributing Your Code](#contributing-your-code)



## Prerequisites
We currently recommend all of the following tools in order to begin development on Moonlight:

* Install [Git](https://git-scm.com/downloads)
* Install [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)
* Install [MySQL 8.x+](https://dev.mysql.com/downloads/mysql/)
* Install [MySQL 8.x+ Workbench](https://dev.mysql.com/downloads/workbench/)
* Install [Java Dev Kit 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* Install [WzIDs](https://mega.nz/#!FPhyzDqb!Qbjn4QHD1lF8A473tGagrUNKIFIOXDcQbvYbxHoWQ1Y)
* Install [WzDumper](https://mega.nz/#!dSInlaBR!EdlI9i5bKB66TfAgFOdnLFeU4CdgFWhj-YgMoJC_5Ek)

> We don't currently support spinning up the server via Docker, but intend on supporting this in the future.

### Getting Maplestory GMS v214
We highly recommend getting all of GMS v214 through Steam via the use of Depot Downloader.

1. Open the [Steam console](steam://nav/console)
2. Run `download_depot 216150 216151 976750626611673486`
3. Wait until it downloads all 16 GB

> If this method does not work for you due to being outside of North America, there should be publicly available Mega.nz download links out there.



## Clone The Repository
We'll cover local development here.
> Note that setting this up for a remote server is nearly the exact same, but requires changing a few values in a few source-code files.

Clone the repository through your terminal:
```sh
git clone --recursive https://github.com/toxocious/moonlight
```

Afterwards, you may begin editing the server source-code that resides in `Moonlight/server`.



## Server Development
Contribution documentation is a work in progress.


## Authentication Hook Development
Contribution documentation is a work in progress.


## Moonlight Launcher Development
Contribution documentation is a work in progress.



## Contributing Your Code
To contribute changes to the project, you'll want to:

1. Fork Moonlight to make your own copy of the repository
2. Clone the forked repository to your local machine
3. Edit the local files
4. Commit and push your code changes to your fork
5. Create a Pull Request from your forked repository to the original upstream repository, allowing Moonlight maintainers to accept and merge your changes

More detailed information on these steps is [here](https://docs.github.com/en/get-started/quickstart/contributing-to-projects).
