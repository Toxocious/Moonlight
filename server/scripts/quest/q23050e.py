# 23049 - WH 4th job advancement

BELLE = 2159111
GELIMERS_KEY_CARD = 4032743
SECRET_PLAZA = 310010000

sm.setSpeakerID(BELLE)
sm.sendNext("Did you destroy the Black Wings' new weapon? Nice work. I'm proud to have you in the Resistance.")
if sm.sendAskYesNo("But it's too early to celebrate. #p2154009# will show up with his goons when he hears the news. Let's get out of here via the Underground Base Hideout Return Scroll. On my count. One... two... three!"):
    sm.completeQuest(parentID)
    sm.consumeItem(GELIMERS_KEY_CARD)
    sm.warpInstanceOut(SECRET_PLAZA, 0)
