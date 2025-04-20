# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001130
# Hideout  :: HQ
KINESIS = 1531000
JAY = 1531001

sm.lockForIntro()

sm.setIntroBoxChat(JAY)
sm.sendNext("The system is running smoothly, and all the errors are cleared. I'll compile the data and then we can decide our next move.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("You sure you don't need to start upgrading again? I'm going to outpace your system before you know it.")

sm.setIntroBoxChat(JAY)
sm.sendSay("You keep saying that like you know anything about my rig. Nobody runs a better system than me. You stick to mind crushing and being pretty.")
sm.sendSay("...Which reminds me, are you EVER going to start covering your face when you do the superhero thing? It's the digital age, dude. Even my blender has a 15 megapixel camera.")

selection = sm.sendNext("Seriously, are you going to cover your face?\r\n#b\r\n#L1#Eh. Too much trouble.#l\r\n#L2#I don't have time to suit up in an emergency.#l\r\n#L3#I'm too handsome, I can't deny the world this face.#l")
# TODO: find script all operations
if selection == 1 or selection == 2 or selection == 3:
    sm.sendNext("...I figured you'd say that. You really don't take this serious enough.")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("Fine. Give me your kitty mask. I was thinking about changing my name to Captain Fuzzums anyway.")

    sm.setIntroBoxChat(JAY)
    sm.sendSay("How do you know about Captain... Dude, stop reading my fanfic! In fact, just stay away from all my stuff. No stuff for you!")

    sm.playSound("Sound/SoundEff.img/PvP/80001740_Use")
    sm.OnOffLayer_On(500, "0", 800, 0, 12, "Map/Effect2.img/kinesis/chaJay", 5, 1, -1, 0)
    sm.OnOffLayer_Move(300, "0", -800, 0)
    sm.sendDelay(5000)

    sm.playSound("Sound/SoundEff.img/12thMS/jump_m")
    sm.OnOffLayer_Off(500, "0", 0)
    sm.OnOffLayer_Move(300, "0", 800, 0)
    sm.sendDelay(1000)

    sm.startQuest(parentID)
    sm.giveItem(4034601)
    sm.sendNext("You're already in trouble with #bYuna#k, you know. She stopped by while you were gone and told me to give you this.")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("Great, Student Council minutes. The highlight of my day.")

    sm.setIntroBoxChat(JAY)
    sm.sendSay("That's what you get for being president, hotshot. Yuna's surely waiting for you at school. I can track her Rage Gauge as you make her wait, if you want.")
    sm.sendSay("School is right there, dude. Go out and hang a right. Don't make her any angrier than she already is. It's not cute.")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("#b(Go outside and find Yuna.)#k")
    sm.unlockForIntro()


