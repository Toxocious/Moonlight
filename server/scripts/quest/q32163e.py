# [Riena Strait] Maybe I Shouldn't have gotten a boat
from net.swordie.ms.constants import WzConstants

lumber = 4030022
exp = 17739

sm.setSpeakerID(1510005) # Daichi

sm.sendNext("Have you collected all the lumber?\r\n\r\n"
            "#b#v"+ str(lumber) +"##t"+ str(lumber) +"##k\r\n\r\n"
            ""+ WzConstants.ICON_EXP +" "+ str(exp) +" exp")
sm.completeQuestNoRewards(32164) # [Riena Strait] Wood That's Good 1
sm.completeQuestNoRewards(32165) # [Riena Strait] Wood That's Good 2
sm.completeQuestNoRewards(32166) # [Riena Strait] Wood That's Good 3
sm.completeQuest(parentID)
sm.consumeItem(lumber, 3)
sm.giveExp(exp)

sm.sendSayOkay("Thank you, Brave Warrior. I can see you are pretty talented in piloting a ship.\r\n\r\n#b"
               "(Talk to Putan to begin the mission.)")
sm.dispose()
