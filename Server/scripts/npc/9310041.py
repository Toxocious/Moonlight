# Zhikeseng (9310041)  |  Mount Song-Shaolin Area : Temple Plaza

INTERCEPTED_MESSAGE = 4034635
ELDER_JUNG = 9310049
WISE_CHIEF_PRIEST = 9310053
NOT_A_DEMON = 62002

sm.removeEscapeButton()
sm.setSpeakerID(parentID)
sm.setBoxChat()
if sm.hasQuest(62001):
    if sm.hasQuest(NOT_A_DEMON):
        sm.sendSayOkay("I'm waiting...")

    elif sm.hasQuestCompleted(NOT_A_DEMON):
        sm.sendSayOkay("Turns out you're a human after all...")

    else:
        sm.sendNext("Hello! Goodbye! Shaolin Temple is closed! Come back and visit us again real soon!")

        sm.flipBoxChat()
        sm.flipBoxChatPlayerNoEscape()
        if not sm.hasItem(INTERCEPTED_MESSAGE):
            sm.sendNext("#b(I don't think I can enter just by word from elder Jung, I should find that message before heading here again.")
            sm.diposse()
        sm.sendNext("Wait, I need to deliver this message to the #b#p"+ str(WISE_CHIEF_PRIEST) +"##k! #b#p"+ str(ELDER_JUNG) +"##k from Mount Song Hamlet sent me!")

        sm.setSpeakerID(parentID)
        sm.setBoxChat()
        sm.sendNext("The #b#p"+ str(WISE_CHIEF_PRIEST) +"##k? The #b#p"+ str(WISE_CHIEF_PRIEST) +"##k, you say? "
                    "Why didn't you say so! The #b#p"+ str(WISE_CHIEF_PRIEST) +"##k is exactly the person who gave me orders not to let anyone in!")

        sm.sendNext("Just look around. Even the censers are possessed by evil spirits, and you look even scarier than they do.")

        sm.sendNext("You probably have a legion of demons tap-dancing inside you, so I can't let you in. Bye-bye now!")

        sm.flipBoxChat()
        sm.flipBoxChatPlayerNoEscape()
        sm.sendNext("Alright.. Listen up motherfucker.\r\n"
                    "My look is 'singular' not 'scary' and I'm NOT possessed.")

        sm.setSpeakerID(parentID)
        sm.setBoxChat()
        response = sm.sendAskYesNo("That's what the last guy said, before he burst into flames and tried to eat my robe. "
                    "If you REALLY want to enter the temple to talk to The #b#p"+ str(WISE_CHIEF_PRIEST) +"##k, you're going to have to prove you're human.")

        if response:
            sm.sendNext("Then defeat 100 Censers and bring me the Prayer Beads they stole. "
                        "I'll be able to tell if you're human because they glow when humans hold them.")
            sm.startQuestNoCheck(NOT_A_DEMON) # [Shaolin Temple] Not a demon

else:
    sm.sendSayOkay("Hello! Goodbye! Shaolin Temple is closed! Come back and visit us again real soon!")