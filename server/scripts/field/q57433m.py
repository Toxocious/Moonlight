# Field: Hidden Street : Secret Place (807051100)
# Used for Kannas quest Fact of Fiction (57433)
# Author: Tiger

# TODO: Find out what happens if player crashes.
# Do we respawn every mob? Or do we check the quest progress
# To see how many mobs they've already killed and then spawn the remaining.
# Also, should we be able to leave the portal if mobs are present in the field?

# Npcs
ODA_SPIRIT_WALKER = 9130068
BLACK_WINGS_NINJA = 9130069

# Mobs
MOB_BLACK_WINGS_NINJA = 9421566
MOB_ODA_ONMYOUJI = 9421565

if sm.hasQuest(57433) and not sm.hasQuestCompleted(57433): # Fact of Fiction
    sm.lockInGameUI(True, True)

    sm.spawnNpc(ODA_SPIRIT_WALKER, -80, 77)
    sm.showNpcSpecialActionByObjectId(ODA_SPIRIT_WALKER, "summon", 0)

    sm.spawnNpc(ODA_SPIRIT_WALKER, 10, 77)
    sm.showNpcSpecialActionByObjectId(ODA_SPIRIT_WALKER, "summon", 0)

    sm.spawnNpc(ODA_SPIRIT_WALKER, 100, 77)
    sm.showNpcSpecialActionByObjectId(ODA_SPIRIT_WALKER, "summon", 0)

    sm.spawnNpc(ODA_SPIRIT_WALKER, 190, 77)
    sm.showNpcSpecialActionByObjectId(ODA_SPIRIT_WALKER, "summon", 0)

    sm.spawnNpc(ODA_SPIRIT_WALKER, 280, 77)
    sm.showNpcSpecialActionByObjectId(ODA_SPIRIT_WALKER, "summon", 0)

    sm.spawnNpc(BLACK_WINGS_NINJA, 370, 77)
    sm.showNpcSpecialActionByObjectId(BLACK_WINGS_NINJA, "summon", 0)


    sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/13", 0, 300, -120, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/14", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/15", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/16", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/17", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/18", 0, 550, -120, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/19", 0, 300, -120, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/20", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)

    # Spawn Mobs First
    sm.spawnMob(MOB_ODA_ONMYOUJI, -80, 77, False)
    sm.spawnMob(MOB_ODA_ONMYOUJI, 10, 77, False)
    sm.spawnMob(MOB_ODA_ONMYOUJI, 100, 77, False)
    sm.spawnMob(MOB_ODA_ONMYOUJI, 190, 77, False)
    sm.spawnMob(MOB_ODA_ONMYOUJI, 280, 77, False)
    sm.spawnMob(MOB_BLACK_WINGS_NINJA, 370, 77, False)

    # Then we remove their Npcs
    sm.removeNpc(ODA_SPIRIT_WALKER)
    sm.removeNpc(ODA_SPIRIT_WALKER)
    sm.removeNpc(ODA_SPIRIT_WALKER)
    sm.removeNpc(ODA_SPIRIT_WALKER)
    sm.removeNpc(ODA_SPIRIT_WALKER)
    sm.removeNpc(BLACK_WINGS_NINJA)

    sm.lockInGameUI(False, False)
