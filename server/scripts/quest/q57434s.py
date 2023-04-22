
# Quest: Deepening Suspicion (57434)
# Author: Tiger

HAKU = 9130081

if sm.hasQuestCompleted(57433) and chr.getLevel() >= 26: # Fact or Fiction
    sm.removeEscapeButton()

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendNext("So Empress Cygnus was right. Nobunaga is finding friends in this world, just like us.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("This is bigger than we thought.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("Nobunaga's all about the spectacle. If he's going to enact an evil scheme, he wants the world to know it. Evil auras, menacing monsters, fireworks: the whole shebang.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("He's never been known to ally with others though. There must be some reason he needs all that power.")
    sm.sendSay("He's even incorporated the monsters into his plans.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("Maybe to understand the bad guy... we have to become the bad guy...")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("That's a horrible idea. Mori Ranmaru manages the Oda Army's Spirit Walker. He must play a large part in all of this as well.")

    sm.setSpeakerID(HAKU)
    sm.setBoxChat()
    sm.sendSay("Well if you don't like my ideas, you don't have to be so mean about it. Let's just go back to Momijigaoka and ask the others. They'll like my idea.")

    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.sendSay("That's better. First, let's go to Orbis to try and intercept Kanetsugu.")

    sm.startQuest(57434)  #  Deepening Suspicion
    sm.warp(200000000, 19) # Orbis : Orbis
