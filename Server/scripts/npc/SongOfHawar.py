# id 9400593 (Hawalu), field 867201100
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400593) # Hawalu
sm.sendNext("Let's softly, gently head to the forest")
sm.sendSay("Ever so quietly so we don't wake")
sm.sendSay("Do you need me to perk you up? What should I sing?")
res = sm.sendNext("Do you want me to sing you a song?#b\r\n#L0# Who Lives in Windsleep Forest#l\r\n#L1# Yummy Meat#l\r\n#L2# Abrup, Land of Snow#l\r\n#L3# The Wind Blows#l\r\n#L10# Did an Eyeful steal your necklace?#l\r\n#L11# What do you mean by ancestors living in Windsleep Forest?#l\r\n#L20# I'm okay right now. Maybe later.#l")
sm.createQuestWithQRValue(64063, "chk1=1;chk2=1;chk3=1")
sm.createQuestWithQRValue(64063, "chk1=2;chk2=1;chk3=1")
sm.createQuestWithQRValue(64272, "0=2")
