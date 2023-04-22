# [Stone Colusses] It Ain't Natural

CHIEF_TATOMO = 2081000

sm.setSpeakerID(CHIEF_TATOMO)
sm.sendNext("Well, you don't look like you just spoke to an ancient nature spirit, but I suppose we'll know soon enough. "
            "Are you ready for a little adventure?\r\n\r\n"
            "#bYou know it! How do I get to the Stone Colossus?")

sm.sendSay("Ah, humans. No patience, and not enough hair. "
           "I would advise you to seek out the Halflinger expedition that has already traveled to the area. "
           "They could help you.\r\n\r\n"
           "#bThese are Halflinger explorers?")

response = sm.sendAskYesNo("Don't act so surprised! "
                "Our people are peaceful home-bodies for the most part, but the blood of the explorer can show up where you least expect it. "
                "What kind of chief would I be if I held them back?\r\n"
                "If you'd like, I can send you to their camp right away.")

if response:
    sm.sendNext("That's the spirit. Do an old-timer a favour and check on my villagers for me.")
    sm.completeQuest(parentID)
    sm.warp(240090000) # Stone Colossus Exploration Site

else:
    sm.sendNext("Oh.. Okay.. I mean.. I thought you were all about adventures.. I guess I was wrong..\r\n\r\n"
                "This is so sad.\r\n"
                "Alexa, play Despacito 5!")