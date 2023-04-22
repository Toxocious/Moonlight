# id 34308 ([Lachelein] Finding the Awakened Ones (2)), field 450003000
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(3003201) # Protective Mask
sm.sendNext("Did you identify the Awakened One? Who was it?")
sm.setSpeakerType(4)
sm.setSpeakerID(3003202) # Protective Mask
res = sm.sendNext("What should I say?\r\n#b\r\n#L0# Beauty Mask#l\r\n#L1# Classy Cat Mask#l\r\n#L2# Shrimp Mask#l")
sm.setSpeakerType(3)
if res == 0:
    sm.sendSayOkay("That doesn't sound right... Maybe you weren't looking carefully enough.")
elif res == 1:
    sm.sendSayOkay("That doesn't sound right... Maybe you weren't looking carefully enough.")
elif res == 2:
    sm.sendNext("I see. Speak with Shrimp Mask. Convince him to aid our cause.")
    sm.startQuest(parentID)

