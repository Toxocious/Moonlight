# TODO: {serverName} Custom Beginnings - Cygnus

speaker = 2007 # maple administrator

# JobOptions: { string jobName, string jobDesc, int jobId }
options = [
    ["Dawn Warrior", "one with the spirit of light", 1100],
    ["Wind Archer", "one with the spirit of wind", 1300],
    ["Blaze Wizard", "one with the spirit of fire", 1200],
    ["Night Walker", "one with the spirit of darkness", 1400],
    ["Thunder Breaker", "one with the spirit of lightning", 1500],
]

optionText = "It's time for you to pick a job.\r\nAs a #bCygnus Knight#k, you have the option of the " + str(len(options)) + " following options:"

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
            sm.startScript("job_cygnus", "npc")
