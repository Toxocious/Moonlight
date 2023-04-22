# Stage 3 ludibrium pq boxes

#Get Order
STAGE_3_ORDER = "Stage3Order"
STAGE_3_COMPLETE = "Stage3Complete"
stage3OrderArray = field.getProperty(STAGE_3_ORDER)

#Check if at corresponding ID
if parentID == 15:
    if stage3OrderArray[0] == 3:
        sm.showObjectFieldEffect("an00")
        sm.teleportToPortal(18)
    else:
        sm.teleportToPortal(0)

elif parentID == 16:
    if stage3OrderArray[0] == 2:
        sm.showObjectFieldEffect("an01")
        sm.teleportToPortal(18)
    else:
        sm.teleportToPortal(0)

elif parentID == 17:
    if stage3OrderArray[0] == 1:
        sm.showObjectFieldEffect("an02")
        sm.teleportToPortal(18)
    else:
        sm.teleportToPortal(0)

elif parentID == 18:
    if stage3OrderArray[1] == 1:
        sm.showObjectFieldEffect("an10")
        sm.teleportToPortal(21)
    else:
        sm.teleportToPortal(0)

elif parentID == 19:
    if stage3OrderArray[1] == 2:
        sm.showObjectFieldEffect("an11")
        sm.teleportToPortal(21)
    else:
        sm.teleportToPortal(0)

elif parentID == 20:
    if stage3OrderArray[1] == 3:
        sm.showObjectFieldEffect("an12")
        sm.teleportToPortal(21)
    else:
        sm.teleportToPortal(0)

elif parentID == 21:
    if stage3OrderArray[2] == 3:
        sm.showObjectFieldEffect("an20")
        sm.teleportToPortal(24)
    else:
        sm.teleportToPortal(0)

elif parentID == 22:
    if stage3OrderArray[2] == 2:
        sm.showObjectFieldEffect("an21")
        sm.teleportToPortal(24)
    else:
        sm.teleportToPortal(0)

elif parentID == 23:
    if stage3OrderArray[2] == 1:
        sm.showObjectFieldEffect("an22")
        sm.teleportToPortal(24)
    else:
        sm.teleportToPortal(0)

elif parentID == 24:
    if stage3OrderArray[3] == 3:
        sm.showObjectFieldEffect("an30")
        sm.teleportToPortal(27)
    else:
        sm.teleportToPortal(0)

elif parentID == 25:
    if stage3OrderArray[3] == 2:
        sm.showObjectFieldEffect("an31")
        sm.teleportToPortal(27)
    else:
        sm.teleportToPortal(0)

elif parentID == 26:
    if stage3OrderArray[3] == 1:
        sm.showObjectFieldEffect("an32")
        sm.teleportToPortal(27)
    else:
        sm.teleportToPortal(0)

elif parentID == 27:
    if stage3OrderArray[4] == 1:
        sm.showObjectFieldEffect("an40")
        sm.teleportToPortal(30)
    else:
        sm.teleportToPortal(0)

elif parentID == 28:
    if stage3OrderArray[4] == 2:
        sm.showObjectFieldEffect("an41")
        sm.teleportToPortal(30)
    else:
        sm.teleportToPortal(0)

elif parentID == 29:
    if stage3OrderArray[4] == 3:
        sm.showObjectFieldEffect("an42")
        sm.teleportToPortal(30)
    else:
        sm.teleportToPortal(0)

elif parentID == 30:
    if stage3OrderArray[5] == 1:
        sm.showObjectFieldEffect("an50")
        sm.teleportToPortal(33)
    else:
        sm.teleportToPortal(0)

elif parentID == 31:
    if stage3OrderArray[5] == 2:
        sm.showObjectFieldEffect("an51")
        sm.teleportToPortal(33)
    else:
        sm.teleportToPortal(0)

elif parentID == 32:
    if stage3OrderArray[5] == 3:
        sm.showObjectFieldEffect("an52")
        sm.teleportToPortal(33)
    else:
        sm.teleportToPortal(0)

elif parentID == 33:
    if stage3OrderArray[6] == 3:
        sm.showObjectFieldEffect("an60")
        sm.teleportToPortal(36)
    else:
        sm.teleportToPortal(0)

elif parentID == 34:
    if stage3OrderArray[6] == 1:
        sm.showObjectFieldEffect("an61")
        sm.teleportToPortal(36)
    else:
        sm.teleportToPortal(0)

elif parentID == 35:
    if stage3OrderArray[6] == 2:
        sm.showObjectFieldEffect("an62")
        sm.teleportToPortal(36)
    else:
        sm.teleportToPortal(0)

elif parentID == 36:
    if stage3OrderArray[7] == 3:
        sm.showObjectFieldEffect("an70")
        sm.teleportToPortal(39)
    else:
        sm.teleportToPortal(0)

elif parentID == 37:
    if stage3OrderArray[7] == 2:
        sm.showObjectFieldEffect("an71")
        sm.teleportToPortal(39)
    else:
        sm.teleportToPortal(0)

elif parentID == 38:
    if stage3OrderArray[7] == 1:
        sm.showObjectFieldEffect("an72")
        sm.teleportToPortal(39)
    else:
        sm.teleportToPortal(0)

elif parentID == 39:
    if stage3OrderArray[8] == 1:
        sm.showObjectFieldEffect("an80")
        sm.teleportToPortal(42)
    else:
        sm.teleportToPortal(0)

elif parentID == 40:
    if stage3OrderArray[8] == 3:
        sm.showObjectFieldEffect("an81")
        sm.teleportToPortal(42)
    else:
        sm.teleportToPortal(0)

elif parentID == 41:
    if stage3OrderArray[8] == 2:
        sm.showObjectFieldEffect("an82")
        sm.teleportToPortal(42)
    else:
        sm.teleportToPortal(0)

elif parentID == 42:
    if stage3OrderArray[9] == 1:
        sm.invokeForParty("teleportToPortal", 2)
        sm.invokeForParty("showFieldEffect", "quest/party/clear")
        field.setProperty(STAGE_3_COMPLETE, True)
    else:
        sm.teleportToPortal(0)

elif parentID == 43:
    if stage3OrderArray[9] == 2:
        sm.invokeForParty("teleportToPortal", 2)
        sm.invokeForParty("showFieldEffect", "quest/party/clear")
        field.setProperty(STAGE_3_COMPLETE, True)
    else:
        sm.teleportToPortal(0)

elif parentID == 44:
    if stage3OrderArray[9] == 3:
        sm.invokeForParty("teleportToPortal", 2)
        sm.invokeForParty("showFieldEffect", "quest/party/clear")
        field.setProperty(STAGE_3_COMPLETE, True)
    else:
        sm.teleportToPortal(0)