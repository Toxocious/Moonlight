# Created by MechAviv
# [Mayor Berry]  |  [9390201]
# Commerci Republic : Berry

sm.setSpeakerID(9390201)
selection = sm.sendNext("What's goin' on over there?\r\n#b\r\n#L0# How's the fishing going?#l\r\n#L1# Do you know anything about the Commerci Republic?#l")
if selection == 0:
    sm.dispose()
elif selection == 1:
    sm.setSpeakerID(9390201)
    sm.sendNext("You're in the Commerci Republic right now! Follow that there coastal road southeast to get to #e#bSan Commerci#k#n. That's the capital.")


    sm.setSpeakerID(9390201)
    sm.sendPrev("San Commerci is a hub for traders all around the Republic. That Gilberto Daniella grew it up from a tiny fishing village into a boomin' jewel of the sea. It's 'cause of him my fish go for such good prices.")
