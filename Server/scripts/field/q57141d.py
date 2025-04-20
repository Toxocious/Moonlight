# Field: Honnou-ji : Honnou-ji Eastern Wall Exterior (807050201)
# Quest: Honnou-ji Infiltration 2 (57437)
# Author: Tiger

if sm.hasQuest(57437): # Honnou-ji Infiltration 2
    if sm.getFieldID() == 807050201: # Honnou-ji Eastern Wall Exterior
        sm.lockInGameUI(True, True)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/21", 0, 0, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/22", 0, 0, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.lockInGameUI(False, False)

    elif sm.getFieldID() == 807050202: # Honnou-ji : Honnou-ji Temple
        sm.lockInGameUI(True, True)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/23", 0, 0, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/24", 0, 450, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/25", 0, 0, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/26", 0, 450, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/27", 0, 0, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/28", 0, 450, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/29", 0, 450, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.lockInGameUI(False, False)
        sm.warpInstanceIn(807050203, 0) # Honnou-ji : Honnou-ji Eastern Grounds

    elif sm.getFieldID() == 807050203: # Honnou-ji : Honnou-ji Eastern Grounds
        sm.lockInGameUI(True, True)

        ODA_GUARDSMAN = 9130070 # Npc
        MOB_ODA_GUARDSMAN = 9421567 # Mob

        sm.spawnNpc(ODA_GUARDSMAN, 40, 24)
        sm.showNpcSpecialActionByObjectId(ODA_GUARDSMAN, "summon", 0)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/30", 0, 220, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/31", 0, 0, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.showEffect("Effect/DirectionJP3.img/effect/kannaTuto/balloonMsg/32", 0, 220, -120, -2, -2, False, 0)
        sm.sendDelay(2000)

        sm.removeNpc(ODA_GUARDSMAN)
        sm.spawnMob(MOB_ODA_GUARDSMAN, 40, 24, False)
        sm.lockInGameUI(False, False)

        sm.waitForMobDeath(MOB_ODA_GUARDSMAN)
        sm.warpInstanceOut(807050204, 0) # Honnou-ji : Honnou-ji Eastern Wall

    elif sm.getFieldID() == 807050204: # Honnou-ji : Honnou-ji Eastern Wall
        # TODO: wtf after resucing princess sakuno,
        # and created a quest value of "1" for quest (57437)
        # Noae, should start the end script, but instead his status / quest
        # Still thinks that we haven't rescued princess sakuno??
        sm.createQuestWithQRValue(57437, "1") #  Honnou-ji Infiltration 2
