# id 913051002 (Hidden Street : Present Henesys), field 913051002
sm.lockInGameUI(True, True)
sm.hideUser(True)
sm.showFieldEffect("twilightPerion/text1", 0)
sm.sendDelay(2500)
sm.setSpeakerType(3)
sm.setSpeakerID(2142900) # Chief Stan
sm.setParam(1)
sm.sendNext("That's strange. All of the people in one town having the same dream?")
sm.setSpeakerID(2142901) # Mrs. Ming Ming
sm.sendSay("It was a horrible dream. The very thought of it sends shivers down my spine.")
sm.setSpeakerID(2142904) # Pia
sm.sendSay("I never imagined that the Cygnus Knights would side with the powers of the dark...")
sm.setSpeakerID(2142902) # Bruce
sm.sendSay("We can't be sure that's what happened!")
sm.setSpeakerID(2142903) # Camila
sm.sendSay("But it was too vivid to be called a dream. It was like watching the world through someone else's eyes.")
sm.hideUser(False)
sm.lockInGameUI(False, True)
sm.warp(913051003)
