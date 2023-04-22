# Created by MechAviv
# ID :: [865010200]
# Commerci Republic : Berry

if not sm.hasQuest(17608) and not sm.hasQuestCompleted(17608):
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.forcedInput(4)
    sm.localEmotion(7, 7000, False)
    sm.sendDelay(3000)


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("Ugh... Where... am I...? ")


    sm.forcedInput(2)
    sm.sendDelay(1000)


    sm.forcedInput(0)
    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("Oof my head. Hey, where's the ship...? What about Parbell...? How did I end up here? I... can't remember anything.")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("It looks like I'm not too badly hurt. This place looks like a fishing village. I should talk to someone and figure out where I am.")


    sm.startQuest(17608)
    sm.sendDelay(1000)


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.setStandAloneMode(False)
    sm.showFieldEffect("Effect/EffectBT.img/dawnveil1/temaD", 0)