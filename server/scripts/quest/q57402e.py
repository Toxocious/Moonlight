# Quest: Fresh Start (57402)
# Author: Tiger

KANETSUGU = 9130022

if sm.hasQuestCompleted(57401) and chr.getLevel() >= 11: # Quest: Zipangu's World
    sm.removeEscapeButton()

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    sm.sendNext("This is the new base, Momijigaoka. Mouri, a prominent member of the alliance, gathered the first to arrive in this new world and established a group.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Momijigaoka... Autumn's blood-red leaves. A fitting name.")

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    sm.sendSay("Mouri wouldn't have it any other way. Go see him inside the stronghold. He has plans for you.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("What about you?")

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    sm.sendSay("I must continue searching for Kenshin-Daimyo. I can't shake the feeling that she's lost somewhere in this place, surrounded by danger, calling out for me. I can know no peace nor rest until she is found.")

    sm.completeQuest(57402)  #  Fresh Start
    sm.giveItem(2000000, 100) # Red Potions x100
    sm.giveItem(2000003, 100) # Blue Potions x100
    sm.giveItem(1004550) # Spirit Walker Hat
    sm.giveItem(1052479) # Lunar Short Onmyouji Robes
    sm.giveItem(1082450) # Lunar Bracelet
    sm.giveItem(1072684) # Lunar Shoes
    sm.giveItem(1552000) # Iron Fan
    sm.giveItem(1142506) # Yin and Yang
