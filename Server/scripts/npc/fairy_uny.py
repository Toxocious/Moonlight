# id 1500016 (Woonie the Fairy), field 101073300
sm.setSpeakerType(3)

if not sm.hasMobsInField():
    sm.setParam(5)
    sm.setInnerOverrideSpeakerTemplateID(1500016) # Woonie the Fairy
    sm.sendNext("You saved our lives... And our dignity. Thank you so much.")
    sm.setInnerOverrideSpeakerTemplateID(1500018) # Tracy the Fairy
    sm.sendSay("I shall never forget your kindness!")
    sm.warp(101073200)
    sm.completeQuest(32128)
else:
    sm.setInnerOverrideSpeakerTemplateID(1500016) # Woonie the Fairy
    sm.sendNext("Please defeat the mole king!")
