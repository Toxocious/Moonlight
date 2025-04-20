# id 101073201 (Mandraky Field : Kidnapping Site), field 101073201
sm.lockInGameUI(True, True)
sm.hideUser(True)
sm.spawnNpc(1500026, -322, 228)
sm.showNpcSpecialActionByTemplateId(1500026, "summon", 0)
sm.spawnNpc(1500031, 40, 228)
sm.showNpcSpecialActionByTemplateId(1500031, "summon", 0)
sm.spawnNpc(1500032, 180, 228)
sm.showNpcSpecialActionByTemplateId(1500032, "summon", 0)
sm.setSpeakerType(3)
sm.setSpeakerID(1500016) # Woonie the Fairy
sm.setParam(1)
sm.sendNext("I'm scared... We were only rehearsing our play...")
sm.setSpeakerID(1500018) # Tracy the Fairy
sm.sendSay("Don't worry, Woonie. Everything will be fine! Someone will come and save us... I think...")
sm.setSpeakerID(1500026) # ???
sm.sendSay("What's this? Little lady fairies in the land of the Mole King?! What brave little morsels you must be!")
sm.setSpeakerID(1500018) # Tracy the Fairy
sm.sendSay("Please let us go. I don't want to be mole chow!")
sm.setSpeakerID(1500026) # ???
sm.sendSay("Oh, I won't eat you! I'll keep you around to be my brides! When you're old enough, obviously, we moles have a very strong sense of chivalry.")
sm.setSpeakerID(1500016) # Woonie the Fairy
sm.sendSay("What?! GROSS!")
sm.setSpeakerID(1500026) # ???
sm.sendSay("I am sorry if I offended you, m'lady, but I'm not spending my days under the dank dark earth! Once I've liberated all these Mandrakies from your oppressive fairy regime, I'll be the ruler up here, and you will come to love me... as long as that's okay with you.")
sm.setSpeakerID(1500018) # Tracy the Fairy
sm.sendSay("Okay, somebody HAS to save us. ")
sm.hideUser(False)
sm.lockInGameUI(False, True)
sm.warp(101073100)
