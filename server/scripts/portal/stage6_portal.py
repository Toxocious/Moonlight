# Hidden street | Abandoned Tower <Stage 3>

STAGE_3_COMPLETE = "Stage3Complete"

if field.hasProperty(STAGE_3_COMPLETE):
    sm.invokeForParty("warp", 922010700)
else:
    sm.sendChat("Something went wrong, please contact an admin.")
