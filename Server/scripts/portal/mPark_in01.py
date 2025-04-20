# Monster Park

from net.swordie.ms.constants import GameConstants

minLv = 115
maxLv = 160

maps = [
["Mossy Tree Forest (Lv.115-124", 953030000],
["Sky Forest Training Center (Lv.125-129)", 953040000],
["Secret Pirate Hideout (Lv.125-134)", 953060000],
["Otherworld Battlefield (Lv.135-144)", 953070000],
["Dangerously Isolated Forest (Lv.140-149)", 953080000],
["Forbidden Time (Lv.145-154)", 953050000],
["Clandestine Ruins (Lv.150-159)", 953090000],
]

sm.setSpeakerID(9071004)
if not sm.getParty() is None:
    sm.sendSayOkay("Please leave your party to enter Monster Park.")
    sm.dispose()

elif sm.getChr().getLevel() < minLv or sm.getChr().getLevel() > maxLv:
    sm.sendSayOkay("You need to be between Level "+ str(minLv) +" and "+ str(maxLv) +" to enter.")
    sm.dispose()

else:
    if sm.getMonsterParkCount() >= GameConstants.MAX_MONSTER_PARK_RUNS:
        colour = "#r"
    else:
        colour = "#b"
    string = "#eToday is #b"+ sm.getDay() +"#k.\r\nToday's Clear Count "+ colour +""+ str(sm.getMonsterParkCount()) +"/"+ str(GameConstants.MAX_MONSTER_PARK_RUNS) +"#k (per Maple Character)\r\n\r\nYou have #b"+ str(2) +"#k free clears left for today.\r\n\r\n#n#b"
    i = 0
    while i < len(maps):
        string += "#L"+ str(i) +"#"+ maps[i][0] +"#l\r\n"
        i += 1
    selection = sm.sendNext(string)

    if sm.getMonsterParkCount() >= GameConstants.MAX_MONSTER_PARK_RUNS:
        sm.sendSayOkay("I'm sorry, but you've used up all your clears for today.")
        sm.dispose()
    else:
        if sm.sendAskYesNo("#eToday is #b"+ sm.getDay() +"#k.\r\n\r\n" +
                                   "Selected Dungeon: #b"+ maps[selection][0] +"#k\r\n" +
                                   "Clearing the dungeon will use up #bone of your free clears#k \r\nfor today.\r\n\r\n" +
                                   "Would you like to enter the dungeon?"):
            sm.warpInstanceIn(maps[selection][1])
            sm.incrementMonsterParkCount()
            sm.setInstanceTime(GameConstants.MONSTER_PARK_TIME)
            sm.createQuestWithQRValue(GameConstants.MONSTER_PARK_EXP_QUEST, "0")