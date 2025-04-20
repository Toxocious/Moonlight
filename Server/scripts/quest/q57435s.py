
# Quest: An Eye on Honnou-ji (57435)
# Author: Tiger

TSUCHIMIKADO_HARUAKI = 9130010
TAKEDA = 9130000

if sm.hasQuestCompleted(57474) and chr.getLevel() >= 27: # Family Seal
    sm.removeEscapeButton()

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendNext("Our scouts sighted Princess Sakuno imprisoned deep within the eastern wing of Honnou-ji. Unfortunately, she's under tight watch. For what it's worth, the scouts said she looks healthy.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("We must break her free of Nobunaga's shackles immediately.")

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendSay("No, we don't have the strength yet. Both you and our military need more time to recover. We've made allies in this world, but we can't draw them in just yet or we risk fighting Nobunaga with exhausted forces.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("But, Haruaki!")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("I'm just gonna butt in here for a sec. Haruaki knows his stuff. We can't risk our forces for one person, even if it is Princes Sakuno. But there may still be a way.")

    sm.setSpeakerID(TSUCHIMIKADO_HARUAKI)
    sm.setBoxChat()
    sm.sendSay("You have a plan, Shingen?")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("You bet your bangs I do.")

    sm.startQuest(57435)  #  An Eye on Honnou-ji
