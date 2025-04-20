# id 17654 ([Commerci Republic] Screaming in the Night), field 865000002
sm.setSpeakerType(3)
sm.setParam(16)
res = sm.sendAskYesNo("I guess this would be a bad time to bring up the peace treaty from the Empress. Maybe a walk around San Commerci would clear my head...")
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9390207) # Zion
sm.sendNext("Arrrrgggghhhh!!")
sm.setParam(35)
sm.sendSay("Huh? What was that?! It sounded like a person screaming! Maybe east?")
sm.sendSay("That scream came from the east, near #b#m865020000##k.")
sm.startQuest(parentID)
sm.warp(865020071)
