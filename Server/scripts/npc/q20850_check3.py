# Created by MechAviv
# [Kimu]  |  [1102204]
# Hidden Street : Tiru Forest
OBJECT_1 = sm.getIntroNpcObjectID(1102204)
sm.setSpeakerID(1102204)
sm.setPlayerAsSpeaker()
sm.sendNext("Kimu! You haven't seen the Master of Disguise, have you?")


sm.setSpeakerID(1102204)
sm.sendSay("Nope! Nothing to report! Search somewhere else!")


sm.setSpeakerID(1102204)
sm.setPlayerAsSpeaker()
sm.sendSay("Yeah, okay. Oh, by the way, thanks for the cape earlier. It's really helped me out.")


sm.setSpeakerID(1102204)
sm.sendSay("You betcha! Anytime, pal!")


sm.setSpeakerID(1102204)
sm.setPlayerAsSpeaker()
sm.sendSay("... You never gave me a cape.")


sm.setSpeakerID(1102204)
sm.sendSay("Argh! But my disguise was perfect! Very well, I guess we have no choice but to fight. Behold the power of the #rBlack Wings#k!")


sm.sendNpcController(OBJECT_1, False)
sm.spawnMob(9300743, -1934, 88, False)