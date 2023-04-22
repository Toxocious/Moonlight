# Cutsence for Hayato Tutorial
# Author: Tiger

from net.swordie.ms.world.field.fieldeffect import GreyFieldType

# npc
TAKEDA = 9131007
AKECHI = 9131000
PRINCESS_NO = 9131005

# mob
ODA_SOLDIER = 9421505
AKECHI_SOLDIER = 9421507

if sm.getFieldID() == 807100000: # Honnou-ji Eastern Wall
    
    if sm.sendAskAccept("Would you like to skip the introduction?"):
        sm.warp(807040000)
        sm.dispose()

    sm.lockInGameUI(True, False)
    #sm.sendDelay(1200)
    sm.setFieldColour(GreyFieldType.Field, 0, 0, 0, 0)
    sm.hideNpcByTemplateId(TAKEDA, True, True)
    sm.hideUser(True)
    sm.sendDelay(1200)
    sm.showFieldEffect("Map/Effect.img/JPKenji/text0")
    sm.sendDelay(8000)
    sm.setFieldColour(GreyFieldType.Field, 255, 255, 255, 0)
    sm.sendDelay(200)
    sm.hideNpcByTemplateId(TAKEDA, False)
    sm.hideUser(False)
    sm.forcedInput(0)

    sm.forcedFlip(True)
    sm.removeEscapeButton()
    sm.forcedInput(1)
    sm.sendDelay(3000)

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.forcedInput(0)
    sm.sendDelay(100)
    sm.sendNext("Finally, the has come! Today, we will put an end to the so-called Demon King. Today we wipe Oda Nobunaga from history!")

    sm.setBoxChat()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("My hands shake with anticipation. The disgrace of the Matsuyama clan will haunt me no more.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendNext("Clear your head, falcon. You get all emotional on me, and I'll shave your head bald.")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("The Mist Cutter yearns for vengeance. That is the only emotion I feel")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendNext("Hahahaha! You're just as serious as I'd heard you were. I like that. How about you start the attack on Honnou-ji?")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendNext("The Eastern Door?")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendNext("Get over the wall and open up the eastern gate. My cavalry will be waiting for you on the other side, ready to trample the enemy into submission.")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("Alright but I hope your horsemen will forgive me when there are no enemies left to be trampled.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendNext("Haha, I wish I could adopt you! Good luck out there, soldier. Try not to beat them all!")

    sm.lockInGameUI(False, False)

elif sm.getFieldID() == 807100011:
    sm.lockInGameUI(True)
    sm.hideUser(True)
    sm.sendDelay(1200)
    sm.showFieldEffect("Map/Effect.img/JPKenji/text1")
    sm.sendDelay(6000)
    sm.lockInGameUI(False)
    sm.warpInstanceIn(807100001)

elif sm.getFieldID() == 807100001: # Honnou-ji Eastern Grounds
    sm.spawnMob(ODA_SOLDIER, 543, 32, False)
    sm.spawnMob(AKECHI_SOLDIER, 543, 32, False)

    sm.spawnMob(ODA_SOLDIER, 210, 32, False)
    sm.spawnMob(AKECHI_SOLDIER, 210, 32, False)

    sm.spawnMob(ODA_SOLDIER, 107, 32, False)
    sm.spawnMob(AKECHI_SOLDIER, 107, 32, False)

    sm.spawnMob(ODA_SOLDIER, 495, 32, False)
    sm.spawnMob(AKECHI_SOLDIER, 495, 32, False)

    sm.lockInGameUI(True, False)
    sm.removeEscapeButton()

    sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/0", 2000)
    sm.sendDelay(2000)
    sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/1", 2300)
    sm.sendDelay(2300)
    sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/2", 2300)
    sm.sendDelay(2300)

    sm.setBoxChat()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()

    sm.sendNext("Take down all the enemies and open the east gate!")

    sm.lockInGameUI(False, False)
    sm.showFieldEffect("Map/Effect.img/aran/tutorialGuide2")

elif sm.getFieldID() == 807100003: # Honnou-ji : Honnou-ji Courtyard
    sm.lockInGameUI(True)
    sm.removeEscapeButton()
    sm.setFieldColour(GreyFieldType.Field, 0, 0, 0, 0)
    sm.hideNpcByTemplateId(AKECHI, True)
    sm.hideUser(True)
    sm.sendDelay(1200)
    sm.showFieldEffect("Map/Effect.img/JPKenji/text2")
    sm.sendDelay(8000)
    sm.setFieldColour(GreyFieldType.Field, 255, 255, 255, 0)
    sm.hideUser(False)
    sm.hideNpcByTemplateId(AKECHI, False)
    sm.sendDelay(200)

    sm.forcedInput(1)
    sm.sendDelay(1700)

    sm.forcedInput(0)
    sm.sendDelay(1000)

    sm.setSpeakerID(AKECHI)
    sm.flipDialogue()
    sm.sendNext("A little deer comes to meet the tiger? You cannot possibly belong to the Oda clan. You are far too unkempt. What brings you to Honnou-ji?")

    sm.setPlayerAsSpeaker()
    sm.sendSay("(This woman appears delicate, but her voice is deep and rough with callous intent. Could she be one of Oda's?)")

    sm.setPlayerAsSpeaker()
    sm.sendSay("My name is Anegasaki Kenji, eldest son of Anegasaki Tomonobu, retainer to the Matsuyama clan. I have come to avenge my family and rescue the princess. Who are you, fair maiden?")

    sm.setSpeakerID(AKECHI)
    sm.flipDialogue()
    sm.sendSay("Hehe, am I truly so fair that you would mistake me for a maiden? I vaguely recall an unimportant family from the southwest named Matsuyama, though I thought I had put it from my memory long ago.")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Only Nobunaga's followers would be so sharp of tongue and empty of mind. I do not relish harming one so beautiful, but my katana is less discerning.")

    sm.setPlayerAsSpeaker()
    sm.sendSay("I will give you one last chance to tell me your name. Bear in mind, it will be the last thing you say on this plane of existence.")

    sm.setSpeakerID(TAKEDA)
    sm.flipDialogue()
    sm.sendSay("No need to get caught up dealing with this louse, Hayato.")

    sm.spawnNpc(TAKEDA, 92, 32)
    sm.sendDelay(100)

    sm.setPlayerAsSpeaker()
    sm.sendSay("Master Shingen!")

    sm.setSpeakerID(TAKEDA)
    sm.flipDialogue()
    sm.sendSay("Honnou-ji has fallen on worse times than I'd hoped. Though I can't say I'm suprised, now that I see one of Oda's Four Heavenly Kinds sthanding before me. Wouldn't you agree, Akechi Mitsuhide?!")

    sm.sendDelay(500)
    sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/0", 2000)
    sm.sendDelay(2000)
    sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/3", 2300)
    sm.sendDelay(2300)
    sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/4", 2000)
    sm.sendDelay(2000)

    sm.setSpeakerID(AKECHI)
    sm.flipDialogue()
    sm.sendNext("You are slightly less idiotic than your portrait made you out to be, Takeda Shingen. Yet you figured out what is going on here, and you figured out that I was the one who started this rebellion. Bravo, you goonish oaf. Bravo!")

    sm.setSpeakerID(TAKEDA)
    sm.flipDialogue()
    sm.sendSay("I've been told you're the kind of guy that'd stab his mother in the back for the right price. How about you and I team up, turn the tables on the Demon King?")

    sm.setPlayerAsSpeaker()
    sm.sendSay("You would count yourself in league with this trickster?! This scoundrel who destroyed my family?! I will not let that happen! Prepared yourself, Akechi Mitsuhide!")

    sm.sendDelay(500)
    sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/5", 2000)

    sm.sendDelay(2000)
    sm.showBalloonMsgOnNpc("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/6", 2300, TAKEDA)
    sm.sendDelay(2300)

    sm.showBalloonMsgOnNpc("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/7", 2000, TAKEDA)
    sm.sendDelay(2000)

    sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/8", 2000)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/9", 2300, TAKEDA)
    sm.sendDelay(2300)

    sm.showBalloonMsg("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/10", 2000)
    sm.sendDelay(2000)

    sm.forcedInput(1)
    sm.sendDelay(800)
    sm.forcedInput(0)
    sm.hideUser(True)

    sm.sendDelay(300)
    sm.showBalloonMsgOnNpc("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/11", 2000, AKECHI)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/12", 2000, AKECHI)
    sm.sendDelay(2000)

    sm.showBalloonMsgOnNpc("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/13", 2000, TAKEDA)
    sm.sendDelay(2000)

    sm.sendDelay(100)
    sm.moveNpcByTemplateId(TAKEDA, True, 500, 0)

    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/shingenAttack/0")

    # TODO: show Takeda move and hit AKECHI

    sm.sendDelay(100)
    sm.removeNpc(TAKEDA)
    sm.lockInGameUI(False)
    sm.warp(807100004)