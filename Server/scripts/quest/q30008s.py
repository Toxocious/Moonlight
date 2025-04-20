# [Root Abyss] World Tree Guardian

NEINHEART = 1404009
sm.setSpeakerID(NEINHEART)
sm.sendNext("The Maple Alliance will focus all resources towards rescuing the World Tree.")

sm.setPlayerAsSpeaker()
sm.sendNext("I knew you guys would help out.")

sm.setSpeakerID(NEINHEART)
sm.sendNext("The World Tree's control over the powers of life is important, to say the least. "
            "It was instrumental in sealing away the Black Mage. Unfortunately, the battle took its toll. "
            "Fortunately, Athena Pierce was able to contain its #blife essence#k and bring it here before the tree withered away entirely.")

sm.sendNext("But one day, it was gone. I believed it was stolen by the Black Mage, but to think that it was healing itself... It's incredible.")

sm.sendNext("I don't know who would seek to harm the World Tree, but I know that we must stop it. It must be protected at all costs.")

sm.setPlayerAsSpeaker()
sm.sendNext("The people that have the World Tree aren't just going to take this lying down.")

sm.setSpeakerID(NEINHEART)
response = sm.sendAskYesNo("We will handle them, as we have handled their kind in the past. "
            "I believe you have it in you to take the lead in battle. "
            "You will have our full support.")

if response:
    sm.sendNext("Good! I'm always glad to see a solder that isn't afraid of resigning to the history books. "
                "Return to the World Tree and do what you must to bring it back here.")
else:
    sm.dispose()

sm.sendNext("News of the World Tree's plight will have reached many by now. I will return you to Root Abyss to lead the charge.")
sm.startQuest(parentID)
sm.warp(105200000, 0) # Root Abyss  (Actual Map)
