# id 1466 (A Greater Power), field 270000000
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(2140001) # Memory Keeper
sm.sendNext("Wait. I have something to tell you before you head for Arcane River.")
res = sm.sendNext("Do you remember one of the temple keepers here named #bKao#k?\r\n\r\n#b#L0# I remember.#l")
sm.sendNext("#fNpc/3003131.img/stand/0#\r\nThat poor child could not discover their identity in the end. I imagine one would do anything to know. Anything.")
res = sm.sendNext("When the temple keepers went through the Gate of the Present to investigate the abnormal flow of Erda, that child disappeared with them.\r\nI wanted to dissuade them, but I was too late.\r\n\r\n#b#L0# I will go through the Gate of the Present and find them.#l")
res = sm.sendNext("Wait. The monsters in Arcane River are born from a river that flows with the highest concentration of Erda we have ever seen...\r\n\r\nYou must possess #eArcane Power#n, or all your strength will come to nothing.\r\n\r\n#b#L0# Arcane Power?#l")
sm.sendSayOkay("Seeing is believing. Go and hunt some of the monsters there. Return when you've had enough.\r\n\r\n#b(Hunt some of the monsters you first encounter at Arcane River, beyond the Gate of the Present, and then go back to the Memory Keeper.)#k")
sm.startQuest(parentID)
sm.completeQuest(parentID)