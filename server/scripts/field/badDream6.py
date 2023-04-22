# id 913051005 (Hidden Street : Recent Elluel), field 913051005
sm.lockInGameUI(True, True)
sm.hideUser(True)
sm.showFieldEffect("twilightPerion/text7", 0)
sm.sendDelay(2500)
sm.setSpeakerType(3)
sm.setSpeakerID(2142934) # Deet
sm.setParam(1)
sm.sendNext("It's always the same for me... Is it for you too?")
sm.setSpeakerID(2142935) # Roa
sm.sendSay("It's so scary to go to bed at night.")
sm.setSpeakerID(2142936) # Moonie
sm.sendSay("Don't cry. It's only a dream.")
sm.setSpeakerID(2142933) # Klas
sm.sendSay("There's no need to fear. It was just a dream.")
sm.moveCamera(False, 150, -110, -210)
sm.sendDelay(6000)
sm.setSpeakerID(2142932) # Astilda
sm.sendNext("I've been alive for hundreds of years, and I've never seen anything like this.")
sm.setSpeakerID(2142931) # Danika
sm.sendSay("We've never heard of this sort of thing either... Could it be one of the Black Mage's commanders?")
sm.setSpeakerID(2142930) # Philius
sm.sendSay("We don't know the enemy's plans. They could just be trying to scare us, or it could just be distracting us from something bigger...")
sm.hideUser(False)
sm.lockInGameUI(False, True)
sm.warp(913051006)
