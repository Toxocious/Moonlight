# 34203 (Master Lyck's Special Dish)
sm.setSpeakerID(3003152)
sm.sendNext("#h #! I need 20 #i2435856# #bSweet Hoof items to make a #rsignature dish #kthat will satisfy Muto! Slurp!")
if sm.sendAskYesNo("Can you be back with my ingredients in a jiffy?!"):
    sm.startQuest(parentID)
    sm.sendNext("You can get #i2435856# #bSweet Hoof #kby hunting the #bPinedeer #kthat live in #bFive-Color Hill #kwhich is to the right of the village!")
    sm.sendSayOkay("Hurry back, we have a culinary masterpiece to perfect, and very little time! Slurp!")
