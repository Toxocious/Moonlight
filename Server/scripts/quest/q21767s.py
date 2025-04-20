# Created by MechAviv
# Quest ID :: 21767
# Secrets of the Wooden Box

sm.setSpeakerID(1204033)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.sendNext(" (Inside the box was an unidentifiable treatment of some sort.)")


sm.setSpeakerID(1204033)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendSay("#bHm, there's a medicinal substance in the box. What could this be? You better take this to John and ask him what it is.#k")


sm.giveItem(4032423)
sm.startQuest(21767)