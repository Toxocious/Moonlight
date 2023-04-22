# Scarecrow Summoning Sack (2434325) | Used to spawn a Dojo Dummy on Client's position
from net.swordie.ms.constants import GameConstants

STRAW_DUMMY_ID = 9305650
DURATION = GameConstants.DOJO_DUMMY_DURATION * 60 * 1000

if not sm.getChr().getField().isTown():
    sm.chat("You can only spawn a dummy in a Town Map")

elif sm.hasMobsInField():
    sm.chat("You cannot spawn a dummy whilst there are other monsters or dummies in the map")

else:
    sm.spawnMob(STRAW_DUMMY_ID, chr.getPosition().getX(), chr.getPosition().getY(), False, 999999999999999999)
    sm.invokeAfterDelay(DURATION, "removeMobByTemplateId", STRAW_DUMMY_ID)
    sm.chatBlue("The Training Dummy will be removed after "+ str(GameConstants.DOJO_DUMMY_DURATION) +" minutes.")
    sm.consumeItem()
sm.dispose()
