# Created by MechAviv
# Map ID :: 931050960
# Classified Lab : Prison

sm.sendDelay(1500)


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Excuse me... I have something to ask you.")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/2", 900, 700, 50, 0, -2, True, 0)
sm.sendDelay(810)


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("I'm not talking, you Black Wings scum!")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(This sensation is familiar, yet distant. I am sure I have met this woman before.)")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(And this dagger... Have I held it before?)")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Umm, here.")


sm.showFieldEffect("xenon/knife", 0)
sm.sendDelay(4200)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 900, 700, 50, 0, -2, True, 0)
sm.sendDelay(810)


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("My dagger!")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Tell me about this weapon. Does it hold special powers? Where did you get it?")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Why do you want to know, Black Wing?")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("This item has caused a strange fluctuation in my memory circuits. Roo-D tells me it may have something to do with my past.")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("What? I don't understand...")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 1200, 700, 50, 0, -2, True, 0)
sm.sendDelay(1200)


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Wait, you said you recognize this dagger? Could you be...")

OBJECT_1 = sm.sendNpcController(2159381, 1200, 180)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.moveNpcByObjectId(OBJECT_1, True, 350, 100)
sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#h0#, #h0#!")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Gelimer is coming back! Did she tell you anything?")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("No, nothing. And the... glitch earlier will not return to my mind.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Too bad... I was hoping that would trigger your memory.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("My memory? What are you talking about Roo-D?")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("No time to explain now, #h0#. This might be your last chance. You need to join this lady and get out of here! Otherwise, Gelimer's going to erase what's left of your memory.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("He would erase my memory?")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Look, I know you're confused, but I made a promise to keep you safe!")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Promise? With who?")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("With you...")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 1200, 700, 50, 0, -2, True, 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 32 00 00 00 FF 00 00 00 00
sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Look, you came to me, before Gelimer took your memories. You wanted to leave! I know you don't remember that now, but you have to trust me.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I've acted like Gelimer's loyal underling for a long time now, but today is our chance.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm sure this lady is from your past. I could see it in your eyes.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You have to get out of here, before you lose what little memory you have left.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("That flash before... was that a memory of my past?")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I don't know, but you don't have time to think about it now. Gelimer is coming back here now, and when he gets here, he'll wipe your memory and hurt that woman. You need to think about what you want...")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I want to find my memories again.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I-I don't even know why, but I know I want to protect her.")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg0/1", 1200, 700, 50, 0, -2, True, 0)
sm.sendDelay(900)


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("That's a good enough reason. Get out of here. I'll clean things up.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Roo-D, you should come with me. If Gelimer finds out you helped...")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm not built for combat. I'd only slow you down.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("That's exactly why I can't leave you here by yourself. You're coming.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...Fine, but we have to leave now!")


sm.curNodeEventEnd(True)
sm.warp(931050970, 0)
