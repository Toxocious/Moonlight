# Resistance Training Room Entrance
ELEVATOR_CONTROL = 2151007
FERDI = 2151010
preQs = [23103, 23104, 23105, 23106, 23118]
preQsDemon = [23231, 23232, 23233, 23234]
maps = [310010100, 310010200, 310010300, 310010400, 931000400]

sm.setSpeakerID(ELEVATOR_CONTROL)
selection = sm.sendNext("An elevator that will take you to your desired training room. Choose the floor you'd like to go to.\r\n\r\n#b#L0#Underground 2nd Floor Training Room A#l\r\n#L1#Underground 3rd Floor Training Room B#l\r\n#L2#Underground 4th Floor Training Room C#l\r\n#L3#Underground 5th Floor Training Room D#l\r\n#L4#Underground 6th Floor Training Room E#l")

if selection == 4 and sm.hasQuest(preQs[selection]):
    # TODO: can you come in here anymore after turning in quest?
    sm.warpInstanceIn(maps[selection], False)
    sm.setInstanceTime(5*60)
    sm.dispose()

if (not sm.hasQuest(preQs[selection]) and not sm.hasQuestCompleted(preQs[selection]) and not sm.hasQuest(preQsDemon[selection]) and not sm.hasQuestCompleted(preQsDemon[selection])):
    sm.setSpeakerID(2151010)
    sm.sendSayOkay("You are not at the right level to go there yet.")
    sm.dispose()
sm.warp(maps[selection], 1)
