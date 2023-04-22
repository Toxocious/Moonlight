# [Commerci Republic] A Chat with Gilberto

COMMERCI_BOOTS = 1072874

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Whao, my dad has, like, so much faith in me. It's incredible, right?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Yeah, just try to tone it down in battle, okay? Don't rush into situations that are obviously traps.")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Why not?")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Your dad would've been upset to learn that some strange girl helped us...")

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Her cloak is so mysterious, don't you think? it just oozes, like, mystery.")

if sm.canHold(COMMERCI_BOOTS):
    sm.sendNext("Oh, and you can have this. I don't want it.")
    sm.giveItem(COMMERCI_BOOTS)
    sm.completeQuest(parentID)
else:
    sm.sendNext("I was going to give you some fancy boots, but I see you're carrying to much..\r\n"
                "Why don't you drop some of your garbage?")
sm.dispose()
