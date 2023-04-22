# Created by MechAviv
# Quest ID :: 34922
# Not coded yet

sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face0#This should be plenty to do the repairs we need. You've been working pretty hard today. Why don't you take a break?")


sm.setSpeakerID(3001510)
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
if sm.sendAskAccept("#face0#Oh, but before you do, could you put the scrap material into storage? We can't afford to waste anything."):
    sm.setSpeakerID(3001510)
    sm.setSpeakerType(3)
    sm.flipDialogue()
    sm.setBoxChat()
    sm.boxChatPlayerAsSpeaker()
    sm.setBoxOverrideSpeaker()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setColor(1)
    sm.sendNext("#face0#Storage is down to your right there. If you've got time, come back, and I'll teach you how to relax caravan style.")


    sm.startQuest(34922)
    # [START_NAVIGATION] [D8 0A F6 17 01 00 00 00 07 00 33 30 30 31 34 30 33 ]