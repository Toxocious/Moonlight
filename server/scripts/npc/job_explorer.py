# TODO: {serverName} Custom Beginnings - Explorer

speaker = 2007 # maple administrator

# JobOptions: { string jobName, string jobDesc, int jobId }
options = [
    ["Warrior", "powerful and defensive", 100],
    ["Bowman", "long-ranged and controlled", 300],
    ["Magician", "intelligent and magical", 200],
    ["Thief", "speedy and sneaky", 400],
    ["Pirate", "fancy and unique", 500],
    ["Jett", "Not like the other heroes", 508],
]

optionText = "It's time for you to pick a job!\r\nAs an #bExplorer#k, you have the option of the " + str(len(options)) + " following options:"

for idx, job in enumerate(options):
    optionText += "\r\n#b#L" + str(job[2]) + "#" + job[0] + "#k, " + job[1] + "#l"
choice = sm.sendNext(optionText)


for job in options:
    if (choice == job[2]):
        response = sm.sendAskYesNo("So, you wish to become a #b" + job[0] + "#k?")
        if response:
            sm.jobAdvance(job[2])
            sm.doJobEnd()
            sm.sendSayOkay("You are now a #b" + job[0] + "#k!")
        else:
            # executes the current script again
            sm.dispose()
            sm.startScript("job_explorer", "npc")