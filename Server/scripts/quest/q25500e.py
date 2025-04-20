# Created by MechAviv
# Quest ID :: 25500
# Eclipse and Sunfire

sm.setSpeakerID(1032209)
LIGHT = sm.getQuestEx(25502, "light")
DARK = sm.getQuestEx(25502, "dark")
sm.sendSayOkay("I guess you're not quite ready to fully control Light and Darkness. Why don't you practice a little more? You reached Sunfire #b" + str(LIGHT) + " times#k and Eclipse #b" + str(DARK) + " times#k.")