# [Commerci Republic] Neinheart's Request

sm.setSpeakerID(1064026) # Neinheart
sm.sendNext("According to intelligence reports, the people of Commerci are fiercely independent. "
            "The Empress means well, but in their eyes, any outreach might be thought an attempt to draw them under our influence. "
            "This would destroy any possibility of a relationship with Commerci, and I can't allow that.")


sm.sendNext("To ensure there are no misunderstandings, we must approach this matter with the utmost care. "
            "We should gain their trust before we relay the Empress' proposal. "
            "This is part of why the Empress has chosen you rather than a royal messenger. "
            "Please do not fail the Empress.")

response = sm.sendAskYesNo("I've arranged your passage to Commerci. Seek out an explorer named #b'Parbell'#k in Lith Harbor. "
                "Let me know when you are ready to depart for Lith Harbor. "
                "\r\n#b(You will be moved to Lith Harbor if you accept.)#k")

if response:
    sm.warp(104000000, 0)
    sm.startQuest(parentID)
else:
    sm.sendSayOkay("Let me know once you are ready to depart.")
sm.dispose()
