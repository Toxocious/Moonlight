# id 34330 ([Lachelein] Nightmare Clocktower 4F), field 450003530
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(3003209) # Gray Mask
sm.sendNext("Hm... The dream is growing weaker.")
sm.sendSay("Hold on to this for me, won't you? Who knows whether I'll transform into a Dreamkeeper again.")
sm.sendSay("I discovered this while I was a Dreamkeeper. I guess I was still awake, in a way. Perhaps it will be of use to you.")
sm.sendSay("I see Protective Mask couldn't make it. The shock of his ordeal must have been great. ")
sm.sendSay("But don't worry. I'm sure he will recover. ")
sm.sendSay("#h0#...")
sm.setSpeakerType(4)
sm.setSpeakerID(3003210) # Gray Mask
sm.setParam(2)
sm.sendSay("I'm going to stop Lucid.")
sm.setSpeakerType(3)
sm.setParam(4)
sm.sendSay("I won't stand in your way then. Win, #h0#. Both for yourself, and for us.")
if sm.canHold(1712003):
    sm.giveItem(1712003)
    sm.completeQuest(34330)
    sm.startQuest(34343)
else:
    sm.sendNext("Please make room in your EQUIP inventory. I have something very important to give you.")
