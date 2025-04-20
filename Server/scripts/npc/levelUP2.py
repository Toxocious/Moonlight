# Haiku - Custom Beginnings

from net.swordie.ms.constants.JobConstants import JobEnum, isBeginnerJob, isAdventurerBeginner, isResistance, isCygnusKnight
from net.swordie.ms.scripts import ScriptType

speaker = 2007 # maple administrator

# JobOptions: { string jobName, string jobDesc, int jobId }
options_explorer = [
    ["Warrior", "powerful and defensive", JobEnum.WARRIOR.getJobId()],
    ["Bowman", "long-ranged and controlled", JobEnum.BOWMAN.getJobId()],
    ["Magician", "intelligent and magical", JobEnum.MAGICIAN.getJobId()],
    ["Thief", "speedy and sneaky", JobEnum.THIEF.getJobId()],
    ["Pirate", "fancy and unique", JobEnum.PIRATE.getJobId()],
    ["Beginner", "useless and useless", JobEnum.BEGINNER.getJobId()]
]

options_cygnus = [
    ["Dawn Warrior", "one with the spirit of light", JobEnum.DAWN_WARRIOR_1.getJobId()],
    ["Wind Archer", "one with the spirit of wind", JobEnum.WIND_ARCHER_1.getJobId()],
    ["Blaze Wizard", "one with the spirit of fire", JobEnum.BLAZE_WIZARD_1.getJobId()],
    ["Night Walker", "one with the spirit of darkness", JobEnum.NIGHT_WALKER_1.getJobId()],
    ["Thunder Breaker", "one with the spirit of lightning", JobEnum.THUNDER_BREAKER_1.getJobId()],
    ["Noblesse", "one that's useless, but has magic", JobEnum.NOBLESSE.getJobId()]
]

options_resistance = [
    ["Battle Mage", "consecutive attacks with magical blows", JobEnum.BATTLE_MAGE_1.getJobId()],
    ["Wild Hunter", "fight with a Jaguar by your side", JobEnum.WILD_HUNTER_1.getJobId()],
    ["Mechanic", "pistol wielding, humanoid robot rider", JobEnum.MECHANIC_1.getJobId()],
    ["Blaster", "martial arts skills with technical innovation", JobEnum.BLASTER_1.getJobId()],
    ["Citizen", "wannabe role player", JobEnum.CITIZEN.getJobId()],
]

options_warrior = [
    ["Spearman", "specialised in spears and polearms", JobEnum.SPEARMAN.getJobId()],
    ["Fighter", "deals powerful blows with swords and axes", JobEnum.FIGHTER.getJobId()],
    ["Page", "wields a shield for powerful defences", JobEnum.PAGE.getJobId()]
]

options_bowman = [
    ["Hunter", "specialised in bows", JobEnum.HUNTER.getJobId()],
    ["Crossbowman", "specialised in crossbows", JobEnum.CROSSBOWMAN.getJobId()]
]

options_magician = [
    ["Wizard (Fire, Poison)", "utilizes fire and poison magic", JobEnum.FP_WIZARD.getJobId()],
    ["Wizard (Ice, Lightning)", "utilizes ice and lightning magic", JobEnum.IL_WIZARD.getJobId()],
    ["Cleric", "supports others with holy magic", JobEnum.CLERIC.getJobId()]
]

options_thief = [
    ["Assassin", "uses throwing stars", JobEnum.ASSASSIN.getJobId()],
    ["Bandit", "quick attacks with daggers", JobEnum.BANDIT.getJobId()]
]

options_pirate = [
    ["Brawler", "short range attacks with knucklers", JobEnum.BRAWLER.getJobId()],
    ["Gunslinger", "long range attacks with guns", JobEnum.GUNSLINGER.getJobId()]
]

options = {
    JobEnum.BEGINNER.getJobId(): options_explorer,
    JobEnum.NOBLESSE.getJobId(): options_cygnus,
    JobEnum.CITIZEN.getJobId(): options_resistance,
    JobEnum.WARRIOR.getJobId(): options_warrior,
    JobEnum.BOWMAN.getJobId(): options_bowman,
    JobEnum.MAGICIAN.getJobId(): options_magician,
    JobEnum.THIEF.getJobId(): options_thief,
    JobEnum.PIRATE.getJobId(): options_pirate,
}

jobId = chr.getJob()
sm.removeEscapeButton()

# Multi Path Job
if (isBeginnerJob(jobId) and chr.getLevel() >= 10 and chr.getSubJob() == 0) or (isAdventurerBeginner(jobId) and chr.getLevel() >= 30):
    if chr.getJob() in options:
        current_options = options[jobId]

        optionText = "It's time for you to pick a job!\r\nYou have the following options:"

        for idx, job in enumerate(current_options):
            optionText += "\r\n#b#L" + str(job[2]) + "#" + job[0] + "#k, " + job[1] + "#l"
        choice = sm.sendNext(optionText)

        for job in current_options:
            if (choice == job[2]):
                response = sm.sendAskYesNo("So, you wish to become a(n) #b" + job[0] + "#k?")
                if response:
                    sm.jobAdvance(job[2])
                    if (isAdventurerBeginner(job[2]) or isResistance(job[2]) or isCygnusKnight(job[2])):
                        chr.getJobHandler().handleJobEnd()
                    sm.sendSayOkay("You are now a(n) #b" + job[0] + "#k!")
                else:
                    # executes the current script again
                    sm.dispose()
                    sm.startScript(0, "job_advance", ScriptType.Npc)
else:
     sm.sendSayOkay("You're not an #bExplorer#k or you're not supposed to job advance.")