
# Quest: An Eye on Honnou-ji (57435)
# Author: Tiger

TSUCHIMIKADO_HARUAKI = 9130010
TAKEDA = 9130000
MOURI = 9130006
UESUGI_KENSHIN = 9130009

if sm.hasQuestCompleted(57474) and chr.getLevel() >= 27: # Family Seal
    sm.removeEscapeButton()

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendNext("Nothing major. I just think we've got some pretty elite warriors here. Why not send in a small group to infiltrate?")

    sm.setSpeakerID(MOURI)
    sm.setBoxChat()
    sm.sendSay("It would be prudent to execute a precision strike. If we send three people, we only lose three people.")

    sm.setSpeakerID(UESUGI_KENSHIN)
    sm.setBoxChat()
    sm.sendSay("This plan illustrates a nuanced understanding of strategy. Surely Shingen didn't come up with this by himself.")

    sm.setSpeakerID(TAKEDA)
    sm.setBoxChat()
    sm.sendSay("Whoever thought of it, we can all agree it's the only option. Let us prepare for the Honnou-ji-infiltration-rescue-Princess Sakuno mission!")

    sm.completeQuest(57435)  #  An Eye on Honnou-ji
