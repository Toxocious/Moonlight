
# Quest: Blind and Battered 2 (57409)
# Author: Tiger

KANETSUGU = 9130022
KANETSUGU_2 = 9130084
HAKU = 9130081

if sm.hasQuestCompleted(57408) and chr.getLevel() >= 17: # Blind and Battered 1
    sm.removeEscapeButton()

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    sm.sendNext("Oh, hey Kanna. You were still out there fighting? Trying to break my record? I think Motonari wants you to return.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("I'm just doing a thorough job, Kanetsugu. Not trying to compete. For the record, how many more scouts did you take care of?")

    sm.setSpeakerID(KANETSUGU)
    sm.setBoxChat()
    sm.sendSay("I'm making pretty slow progress. I think I got...I don't know...72 more? I wasn't really counting. Probably missed 10 or 20.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("Errr.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("What's with that vein in your forehead?")

    sm.setSpeakerID(KANETSUGU_2)
    sm.setBoxChat()
    if sm.sendAskYesNo("Listen, I'm going to stay behind and search for Kenshin. She doesn't have a speck of fat on her, so she's probably starving to death. As her loyal servant, I may have to allow her to eat me. You should head back to camp."):
        sm.completeQuest(57409)  #  Blind and Battered 2
