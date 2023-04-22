
# Quest: Blind and Battered 1 (57408)
# Author: Tiger

MOURI = 9130006

if sm.hasQuestCompleted(57407) and chr.getLevel() >= 17: # Dark Times
    sm.removeEscapeButton()

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendNext("Kanna, soldiers in dark armor have appeared in the area. With all the ruckus you make while training, I can't say I'm surprised.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("But you're the one who made me train in the first place!")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("An insignificant detail. It seems that Nobunaga's soldiers came to this world when we did. We weren't the only ones affected by the light. The soldiers are lightly armed and very disorganized. They're scouts, and poor ones at that.")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    if sm.sendAskAccept("They haven't found our base yet, but they draw closer with each passing day. It's only a matter of time before Momijigaoka is exposed to the enemies. I'm making it your duty to destroy them."):
        sm.startQuest(57408)  #  Blind and Battered 1
        # TODO: send dis Navigation to -> Momijigaoka : Maple Hill Road 1 (807010000)
