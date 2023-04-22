# [Vanishing Journey] Amnesiac Temple Keeper Kao
from net.swordie.ms.world.field.fieldeffect import GreyFieldType
KAO = 3003131
KAO_ILLUSTRATION = 3003113


if not sm.hasQuestCompleted(1466):
    sm.setSpeakerID(KAO)
    sm.sendNext("..What should we do to assuage their anger?\r\n"
                "#b(Complete the A Greater Power quest and obtain an Arcane Symbol.)")
else:
    sm.removeEscapeButton()
    sm.lockInGameUI(True, False)
    sm.setFieldColour(GreyFieldType.Field, 100, 100, 100, 2000)
    sm.setSpeakerID(KAO_ILLUSTRATION)
    sm.setBoxChat()
    sm.sendNext("I'll have to tell you this first. Have you seen the big lake on the edge of this village? "
                "The villagers call it Oblivion Lake because its water causes loss of memory.")

    sm.sendNext("And... Perhaps because of it, those who live near the lake lose their memories little by little, every day.")

    sm.showOffFieldEffect("Map/Effect2.img/ArcaneRiver1/tree1")
    sm.sendNext("So they made this Tree of Memories. On which they hang their memories and look at them every day, until they are weathered beyond recognition.")

    sm.sendNext("When I first heard about the tree, I got so excited that my heart raced. "
                "I thought it could have something to do with my lost memories, and I couldn't wait to investigate it. "
                "But")

    sm.showOffFieldEffect("Map/Effect2.img/ArcaneRiver1/tree2")
    sm.sendNext("I touched the tree, and...")

    sm.showOffFieldEffect("Map/Effect2.img/ArcaneRiver1/tree3")
    sm.sendNext("The memories... The precious memories of the villagers' scattered.")

    sm.sendNext("The villagers were so devastated that they stopped doing things that they did every day. "
                "They stopped farming and bossing, they even stopped operating the boat that traveled across Oblivion Lake.")

    sm.setFieldColour(GreyFieldType.Field, 255, 255, 255, 1000)
    sm.lockInGameUI(False)
    sm.completeQuest(parentID)