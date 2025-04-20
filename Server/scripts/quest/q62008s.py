# [Shaolin Temple] Funny Face

JANITOR_MONK = 9310048
WISE_CHIEF_PRIEST = 9310053
ANTIDOTE = 2050000

sm.removeEscapeButton()

sm.setSpeakerID(JANITOR_MONK)
sm.setBoxChat()
sm.sendNext("Shoo, shoo! You don't belong here! I'm trying to clean!")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("(Huh? Is that demon wearing monk robes?)")

sm.setSpeakerID(JANITOR_MONK)
sm.setBoxChat()
sm.sendNext("Oh, wipe that dopey expression off your face. The demons played a trick on me, okay? "
            "I'm the temple janitor, a HUMAN, and they thought it'd be soooo funny to give me a demon face. "
            "Now shoo, I have floors to sweep.")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("The #b#p"+ str(WISE_CHIEF_PRIEST) +"##k sent me. I'm here to find a #bbook#k. But... your poor face. What can I do to help?")

sm.setSpeakerID(JANITOR_MONK)
sm.setBoxChat()
response = sm.sendAskYesNo("You can move and let me sweep! Oh, fine. "
            "Why don't you get me some #v"+ str(ANTIDOTE) +"# #b#t"+ str(ANTIDOTE) +"##k from the General Store? Take your time, "
            "and make sure you don't track mud on your way back!")

if response:
    sm.sendNext("Why are you still here? I can't sweep with you here! Go get me 1 #v"+ str(ANTIDOTE) +"# #b#t"+ str(ANTIDOTE) +"##k")
    sm.startQuestNoCheck(parentID)
else:
    sm.sendSayOkay("You just offered to help me, but when I ask for a #v"+ str(ANTIDOTE) +"# #b#t"+ str(ANTIDOTE) +"##k, it's too much work?")