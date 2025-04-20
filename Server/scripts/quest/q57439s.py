
# Quest: Internal Affairs  (57439)
# Author: Tiger

PRINCESS_SAKUNO = 9130021
TSUCHIMIKADO_HARUAKI = 9130010
TAKEDA = 9130000
MOURI = 9130006
UESUGI_KENSHIN = 9130009

if sm.hasQuestCompleted(57437) and chr.getLevel() >= 30: #  Honnou-ji Infiltration 2
    sm.removeEscapeButton()

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendNext("Ah, hello there, little princess. I see you've escaped the big, bad wolf.")

    sm.setSpeakerID(PRINCESS_SAKUNO)
    sm.setBoxChat()
    sm.sendSay("Ah Haruaki. You are your usual jovial self. Enjoying all this turmoil?")

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendSay("I was only a fool the last time we met, dearest princess. I am honored that you still remember me.")

    sm.setSpeakerID(PRINCESS_SAKUNO)
    sm.setBoxChat()
    sm.sendSay("Judging by the lion's mane and foul odor, I will assume you are the Tiger, Takeda Shingen. Meaning this stunning beauty will be the Dragon of Echigo, Uesugi Kenshin. That leaves the mastermind, Mouri Motonari. Your presence is as commanding as I had heard. ")
    sm.sendSay("I am Matsuyama Sakuno, eldest daughter of Matsuyama Nobukane, and acting head of the clans. ")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("Your old man would be proud to see his little girl take to power so quickly.")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("It is a pleasure to meet you, your highness. The Uesugi clan is at your disposal.")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("I apologize for the humble surroundings, your highness. Please make yourself at home.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("(Her eyes are like fire for a girl her age. She truly is the daughter of the Shogun...)")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("(She commands such power.)")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("(Perhaps she has the capacity to lead this group.)")

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendSay("Your highness, did you happen to learn anything about Nobunaga while you were in his custody? Our information is severely lacking.")

    sm.setSpeakerID(PRINCESS_SAKUNO)
    sm.setBoxChat()
    sm.sendSay("He has been surrounded by spirit walkers and conjurers of all sorts, but I have seen nothing out of the ordinary. The only person I spoke to was the guardsman who delivered my meals.")

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    if sm.sendAskAccept("He's surrounded himself with power. Impenetrable. Kanna, perhaps you have more questions for the princess?"):
        sm.startQuest(57439)  #  Internal Affairs
