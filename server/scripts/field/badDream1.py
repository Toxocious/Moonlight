# id 913051000 (Ereve : Conference Room of the Alliance), field 913051000
sm.lockInGameUI(True, True)
sm.sendDelay(2000)
sm.setSpeakerType(3)
sm.setSpeakerID(1105001) # Athena Pierce
sm.setParam(1)
sm.sendNext("Let's get started right away.")
sm.setSpeakerID(1105002) # Claudine
sm.sendSay("This is taking forever. And so many did not attend. Why do you enjoy being alone?")
sm.setSpeakerID(1105003) # Neinheart
sm.sendSay("Everyone has their orders. Claudine, it will not be easy to get all of Maple World's warriors in one place.")
sm.setSpeakerID(1105002) # Claudine
sm.sendSay("Sir Neinheart, it is our duty to carry out the impossible. Why would the Alliance exist otherwise?")
sm.setSpeakerID(1105003) # Neinheart
sm.sendSay("You're talking without thinking. We've worked hard for this alliance. Didn't everyone swear to join forces to defeat the Black Mage?")
sm.setSpeakerID(1105002) # Claudine
sm.sendSay("Wait... Am I hearing a lecture on promises from the Cygnus Knights?")
sm.setSpeakerID(1105001) # Athena Pierce
sm.sendSay("Stop with the wasteful debate.")
sm.setSpeakerID(1105000) # Cygnus
sm.sendSay("This threatens the very future of the Alliance... We can't afford for the public to lose confidence")
sm.sendSay("We have gathered today to discuss the grave events occurring in Maple World.")
sm.lockInGameUI(False, True)
sm.warp(913051001)
