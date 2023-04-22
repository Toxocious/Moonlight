# 20893 - [Job Adv] (Lv.100)   The Empress' Chief Knight
sm.setSpeakerID(1101000)
sm.sendNext("#h #...  what is this?")
sm.setPlayerAsSpeaker()
sm.sendNext("This, milady, is the report from Neinheart about the activities of the Cygnus Knights.")
sm.setSpeakerID(1101000)
sm.sendNext("Haha, is that wat Neinheart said? It is a recommendation about you. It's all about the process of you getting stronger and the activities done by you...")
sm.setPlayerAsSpeaker()
sm.sendNext("What did Neinheart write about me?")
sm.setSpeakerID(1101000)
if sm.sendAskYesNo("I would like to appoint a title to you for your activities and effort. will you accept this?"):
    sm.sendSay("#h #, with your braveness and courage, from now on you are a new captain of the knights. Please use your power to protect the Maple World.")
    if sm.canHold(1142069):
        chrJobID = sm.getChr().getJob()
        sm.jobAdvance(chrJobID+1)
        sm.giveItem(1142069)
        sm.completeQuest(parentID)
    else:
        sm.sendSay("Please make space in your Equip inventory.")
        sm.dispose()
else:
    sm.sendSay("Please speak to me again when you change your mind.")
    sm.dispose()