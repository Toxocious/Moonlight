# Created by MechAviv
# Kinesis Introduction

KINESIS = 1531000
JAY = 1531001
NERO = 1531003

sm.setIntroBoxChat(JAY)
sm.sendNext("I analyzed those things you fought yesterday, but I can't figure them out. I want to say they're ghosts, but I mean, that's ridiculous. ")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Whatever they are, I can take them. Don't worry about me. ")

sm.setIntroBoxChat(JAY)
sm.sendNext("That confidence of yours will be your downfall. Also, get that cat away from my gear. It's been eyeing my mouse for the last hour, the mangy thing.")
sm.sendSay("Still, this might be a good time for confidence. I got another one of those codes. ")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Good. Point me at them and let me loose.")


sm.setIntroBoxChat(JAY)
sm.sendSay("K, hold it. I want to ask you something first.")
sm.sendSay("#face0#I know you're hiding something. I lost the uplink with you for just a moment, but your Psy-Limiter showed a spike.")

selection = sm.sendNext("#face0#What happened at the school?\r\n#b#L1#(Tell the truth about losing consciousness.)#l\r\n#L2#(Lie.)#l")

if selection == 2 or selection == 1:# TODO: sniff selection 1
    sm.setIntroBoxChat(KINESIS)
    sm.sendNext("No! ...It's nothing. Don't be so worried.")

    sm.setIntroBoxChat(JAY)
    sm.sendSay("#face0#Really? You wouldn't keep secrets from your partner, would you? Partner?")


sm.lockForIntro()
sm.changeBGM("Bgm00.img/Silence", 0, 0)
sm.playSound("Sound/SoundEff.img/radionoise")
sm.playSound("Sound/Voice3.img/Kinesis/news_01")
sm.progressMessageFont(3, 20, 20, 0, "We have breaking reports of a situation at Seoul's Hannam subway station.")
sm.sendDelay(3500)

sm.progressMessageFont(3, 20, 20, 0, "An unknown assailant has been attacking people at random in the area. ")
sm.sendDelay(3500)

sm.progressMessageFont(3, 20, 20, 0, "Citizens are advised to avoid the station.")
sm.sendDelay(2000)

sm.setIntroBoxChat(KINESIS)
sm.sendNext("#face0##fs30#!!")

sm.changeBGM("Bgm40.img/SecretMission", 0, 0)
sm.playSound("Sound/Voice3.img/Kinesis/news_02")
sm.progressMessageFont(3, 20, 20, 0, "Again, the authorities are requesting that all citizens stay away")
sm.sendDelay(1500)

sm.progressMessageFont(3, 20, 20, 0, "from the Hannam station until this matter is resolved. ")
sm.sendDelay(1500)

sm.playSound("Sound/SoundEff.img/radionoise")
sm.progressMessageFont(3, 20, 20, 0, "Do not leave your homes.")
sm.sendDelay(3000)

sm.unlockForIntro()

sm.setIntroBoxChat(JAY)
selection = sm.sendNext("#face1#...Kinesis, this is serious. I don't want you out there.\r\n#b#L1#I have to save those people!#l\r\n#L2#I want to know more about my abilities.#l\r\n#L3#I just want to enjoy this power while I have it.#l")

if selection == 1 or selection == 2 or selection == 3:# sniff selection 2 and 3
    # increase ambition by 100
    sm.setIntroBoxChat(KINESIS)
    sm.sendNext("Didn't you hear? The monsters are attacking innocents now. I have to do something.")

    sm.setIntroBoxChat(JAY)
    sm.sendSay("#face0#...Why do I even bother? Get going. I'll be with you on comms.")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("#b(Hurry to the subway.)#k")

    sm.setQuestEx(22700, "blackCat", "0")
    sm.setQuestEx(22700, "coNight", "0")
    sm.startQuest(parentID)