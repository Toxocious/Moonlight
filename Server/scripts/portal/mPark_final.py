from net.swordie.ms.client.character.quest import Quest
from net.swordie.ms.constants import GameConstants
from net.swordie.ms.enums import QuestStatus

qm = sm.getChr().getQuestManager()
quest = qm.getQuests().get(GameConstants.MONSTER_PARK_EXP_QUEST)
if quest is None:
    quest = Quest(GameConstants.MONSTER_PARK_EXP_QUEST, QuestStatus.STARTED)
    quest.setQrValue("0")
    qm.addQuest(quest)
quest.setQrValue(str(int(quest.getQRValue())))


fieldID = sm.getFieldID()

if fieldID / 1000000 == 952 or fieldID / 1000000 == 953 or fieldID / 1000000 == 954:
    sm.setSpeakerID(9071005)
    sm.sendNext("Did you have a blast in Monster Park? Here's your reward\r\n"
                "\r\n"
                "#e#bDay-of-the-week reward: #v"+ str(sm.getMPReward()) +"##t"+ str(sm.getMPReward()) +"#\r\n"
                "EXP reward: "+ sm.formatNumber(quest.getQRValue()))
    if sm.canHold(sm.getMPReward()):
        #Warp out instance | Monster Park
        sm.warpInstanceOut(951000000) #Monster Park
        sm.giveExp(long(quest.getQRValue()))
        sm.giveItem(sm.getMPReward())
        sm.stopEvents()
    else:
        sm.sendSayOkay("Please make some space before leaving.")
else:
    sm.chat("This is uncoded for the current map (" + fieldID + ")")

sm.dispose()
