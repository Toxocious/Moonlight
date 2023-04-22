sm.setSpeakerType(2)
sm.setParam(546)
sm.setColor(1)
sm.setSpeakerID(9000139) # Kitty
sm.setInnerOverrideSpeakerTemplateID(9000139) # Kitty
if sm.hasQuestCompleted(14256):
    if sm.sendAskYesNo("Would you like to go to #bThe Celestial Halloween Party#k?"):
        sm.warp(993034000)
else:
    sm.sendNext("HEY! Hands off my cat she's very scared right now..")
    sm.setParam(547)
    sm.sendNext("what the heckie")
    sm.setSpeakerType(2)
    sm.setParam(546)
    sm.setSpeakerID(9000139)
    sm.sendNext("we're trapped here.. something dark is keeping the main doors locked and we don't know what to do..")
    sm.setParam(547)
    sm.sendNext("well maybe I could be of some help? will you let me pet that damn cat then?")
    sm.setParam(546)
    sm.setSpeakerID(9000139)
    if sm.sendAskYesNo("I'll think about it.\r\nBut will you help us get out of this nightmare?"):
        sm.sendNext("Thank u uwu")
    else:
        sm.sendNext("You don't have to be mean about it wtf? fine no kitty pets for you.")

