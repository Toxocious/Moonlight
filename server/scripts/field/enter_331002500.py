# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002500
# School for the Gifted  :: 2-1 Classroom
KINESIS = 1531000

sm.setSpineObjectEffectAlpha(False, "class_flyObj", 0, 0)
if "1" in sm.getQuestEx(22700, "coNight"):
    sm.setSpineObjectEffectPlay(False, "classroom", "night", True, False)
    sm.setSpineObjectEffectPlay(False, "class_dayObj", "night", True, False)
if sm.hasQuest(22728):
    sm.blind(1, 255, 0, 0)
    sm.sendDelay(1200)

    sm.blind(0, 0, 0, 1000)
    sm.sendDelay(1400)

    sm.sendDelay(1500)

    sm.setIntroBoxChat(KINESIS)
    sm.sendNext("Is someone there?")

    sm.sendDelay(1500)

    sm.sendSay("Must be imagining...")
    sm.sendSay("#face0#Ugh... My head...")

    sm.forcedInput(4)
    sm.localEmotion(7, 9000, False)
    sm.setFieldFloating(331002500, 20, 20, 100)
    sm.showFadeTransition(0, 1000, 3000)
    sm.sendDelay(500)

    sm.removeOverlapScreen(1000)
    sm.sendDelay(500)

    sm.showFadeTransition(0, 1000, 3000)
    sm.sendDelay(500)

    sm.removeOverlapScreen(1000)
    sm.sendDelay(500)

    sm.setSpineObjectEffectAlpha(False, "class_flyObj", 255, 1000)
    sm.setSpineObjectEffectAlpha(False, "class_dayObj", 0, 1000)
    sm.showFadeTransition(0, 1000, 3000)
    sm.sendDelay(500)

    sm.removeOverlapScreen(1000)
    sm.sendDelay(500)

    sm.showFadeTransition(0, 1000, 3000)
    sm.sendDelay(500)

    sm.removeOverlapScreen(1000)
    sm.sendDelay(500)

    sm.setFieldFloating(331002500, 0, 0, 0)
    sm.sendDelay(1000)

    sm.blind(1, 255, 0, 500)
    sm.sendDelay(500)

    sm.sendDelay(1000)

    sm.setSpineObjectEffectAlpha(False, "class_flyObj", 0, 0)
    sm.setSpineObjectEffectAlpha(False, "class_dayObj", 255, 0)
    sm.blind(1, 255, 0, 0)
    sm.sendDelay(1200)

    sm.blind(0, 0, 0, 1000)
    sm.sendDelay(1400)

    sm.sendDelay(1000)

    sm.setIntroBoxChat(KINESIS)
    sm.sendNext("#face0#Ahhh... It hurts.")
    sm.sendSay("What was that? I think I blacked out for a second there. And just for a moment, I felt like I lost control of my power.")
    sm.sendSay("Ah... It's probably nothing. I must have just been tired. Let's go.")

    sm.sendDelay(1000)

    sm.setQuestEx(22700, "blackCat", "1")
    sm.completeQuest(22728)
    sm.giveExp(1200)