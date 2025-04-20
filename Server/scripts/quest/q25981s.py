# id 25981 (Do you know about Maple Guide?), field 610050000
sm.setSpeakerID(9010000) # Maple Administrator
response = sm.sendAskYesNo("Hello, #h0#! I'd like to give you a brief explanation of the #rMaple Guide#k.")
if response:
    sm.startQuestNoCheck(parentID)
else:
    sm.sendSayOkay("Maple guide is to teleport somewhere uwu")
sm.dispose()