# 21202 - [Job Adv] (Lv.30)   Aran
sm.setSpeakerID(1203000)
sm.sendNext("Oh, have you brought me the Sign of Acceptances? You're stronger than I thought! But more importantly, I am impressed with the amount of courage you displayed when you agreed to take this dangerous weapon without any hesitation. You deserve it. The #p1201001# is yours.")
sm.sendNext("(After a long time passed, #p1203000# handed you the #p1201001# which was carefully wrapped in cloth.)")
if sm.sendAskYesNo("Here, this is #p1201002#, the Polearm you've asked for. Please take good care of it."):
    # todo: show effect
    sm.warpInstanceOut(140030000)
    sm.completeQuest(parentID)
    sm.consumeItem(4032311, 30)
