# Created by MechAviv
# Quest ID :: 21766
# Suspicious John

sm.setSpeakerID(1002001)
if sm.sendAskAccept("Did you find out what's in the Wooden Box? Ooh, tell me, tell me!\r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 1200 exp"):
    sm.setSpeakerID(1002001)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("#bThere really wasn't anything inside. All it had were some snail shells and ingredients to treat arthritis.#k")


    sm.setSpeakerID(1002001)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.sendSay("Oh? Then why has #p20000# started acting so strange? I'm so curious, I can't stand it! Anyway, thanks for your help.")


    sm.setSpeakerID(1002001)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendSay("#b(You feel bad for #p1002001#, but sometimes, ignorance is bliss.)#k")

    sm.startQuest(21766)
    sm.completeQuest(21766)
    sm.giveExp(1200)
else:
    sm.setSpeakerID(1002001)
    sm.sendNext("I guess you don't know yet... That Wooden Box is... strange.")