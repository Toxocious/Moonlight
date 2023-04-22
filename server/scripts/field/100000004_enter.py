# Created by MechAviv
# Map ID :: 100000004
# Henesys : Closet House

if sm.hasQuest(32707):
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.setIntroBoxChat(1530020)
    sm.setSpeakerType(3)
    sm.sendNext("Hey, you should head back now. How long are you gonna mooch off of us?")


    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendSay("Oh em gee... You were the one that summoned me in the first place! Don't tell me what to do.")


    sm.setIntroBoxChat(1530020)
    sm.setSpeakerType(3)
    sm.sendSay("How many times do I have to tell you, it was an accident! And can you PLEASE talk like a normal person, not this oh em...whatever stuff?")


    sm.setIntroBoxChat(1530000)
    sm.setSpeakerType(3)
    sm.sendSay("Practically everyone talks like that back where I'm from.")


    sm.setIntroBoxChat(1530020)
    sm.setSpeakerType(3)
    sm.sendSay("Great, I opened a portal to a world full of mutants.")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.setSpeakerType(3)
    sm.sendSay("Hey guys. I'm here now, so, um, care to fill me in?")


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)