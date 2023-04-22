# Manual Labor

# Constants
CYGNUS = 1106001
NEINHEART = 1106003

sm.setSpeakerID(CYGNUS)
sm.sendNext("Do you see, Neinheart? This child must be the descendant of the Knight of Light.")
    
sm.setSpeakerID(NEINHEART)
sm.sendSay("I suppose the Empress is correct. You will have to learn the ways of a true knight, shopkeep. Your skills with a broom won't get you far on the battlefield.")

sm.setPlayerAsSpeaker()
sm.sendSay("My father was the Knight of Light? What does that even mean? I'm just an ordinary kid...")

sm.setSpeakerID(CYGNUS)
if sm.sendAskAccept("The choice is yours. Listen to your heart. The voice of destiny will guide you down the correct path. For the good of your own soul, and the good of the world...\r\nWill you come with me?"):
    sm.sendNext("There has been an incident at the #b Ellinel Fairy Academy#k. A human magician has trespassed in the sacred halls of the fairy school.")
    sm.giveItem(1142399)# Newborn Light (Medal)
    sm.giveItem(1052444)# Apprentice Knight of Light Robe
    sm.giveItem(1302077)# Beginner Warrior's Sword
    sm.giveAndEquip(1098000)# Soul Shield of Protection
    sm.removeEscapeButton()
    sm.sendNext("You need a name. How about... #b#e'Mihile'#k#n? It means 'Born of Light'? I think it will suit you well, in the end. Let us go to Ereve. A brilliant new life awaits you.")
    sm.addLevel(2)
    sm.jobAdvance(5100)
    sm.addAP(40)
    sm.startQuestNoCheck(29976)
    sm.completeQuestNoRewards(29976)
    sm.completeQuestNoRewards(20036)
    sm.warpInstanceOut(913070071, 0)
else:
    sm.setSpeakerID(CYGNUS)
    sm.sendNext("Why are you refusing destiny?")
sm.dispose()