# Gate to the present
if sm.hasQuestCompleted(1478): #V-matrix quest
    sm.setSpeakerID(1520021)
    selection = sm.sendNext("At the center of the Temple of Time stands an enormous door, the Gate of the Present.\r\n #b#L0#Step through into the Arcane River.#l \r\n #L1#Pass through the Gate of the Present. #l")
    if selection == 0:
        sm.warp(450001003)