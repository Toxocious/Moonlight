# Created by MechAviv
# ID :: [865010200]
# Commerci Republic : Berry
sm.setSpeakerID(9010000)
sm.flipDialogue()
sm.setSpeakerType(8)
if sm.sendAskYesNo("Would you like to leave the Commerci Republic?"):
    sm.dispose()