# Created by MechAviv
# Quest ID :: 32157
# [Theme Dungeon] Ellinel Fairy Academy

sm.setSpeakerID(1040002)
sm.sendNext("You're here to help me with this ruckus, are you? About time you got here! Mrow!")


sm.setSpeakerID(1040002)
sm.setPlayerAsSpeaker()
sm.sendSay("Indeed. My business here is my own, but I would offer my aid should you need it.")


sm.setSpeakerID(1040002)
sm.removeEscapeButton()
sm.sendSay("Was that a yes or what? Fine, I'll put you to work!")


sm.completeQuest(32157)
sm.giveExp(3877)