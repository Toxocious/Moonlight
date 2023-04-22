from net.swordie.ms.enums import UIType

ATHENA = 1012100

sm.setSpeakerID(ATHENA)
sm.sendNext("Would you mind helping us out while you train? It will help you adjust to this new Maple World.")
sm.sendSay("Didn't you hear of the troubles at the #rEllinel Fairy Academy#k? You should check it out.")
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(5000)

sm.openUI(UIType.UI_GROWTH_HELPER)
sm.sendSayOkay("Use the Maple Guide function to get to #rEllinel Fairy Academy#k easily. I'll contact you again if something else happens.")