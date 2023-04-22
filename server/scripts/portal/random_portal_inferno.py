POLLO = 9001059
INFERNO_CHANNEL = 2
INFERNO_FIELD_ID = 993000500
field = chr.getField()
sm.setSpeakerID(POLLO)
answer = sm.sendNext("My brother and I have been tracking down the legendary #rInferno Wolf#k, and we finally found its "
                "hideout! It's a really vicious monster that ruthlessly attacks Maple World's travelers... So, will"
                "you join us in bringing that beast down?\r\n#L0#Let's do it!#l\r\n#L1#Nah, I'm good.#l")
if answer == 0:
    chr.setPreviousFieldID(chr.getFieldID())
    sm.changeChannelAndWarp(INFERNO_CHANNEL, INFERNO_FIELD_ID)
    field.removeLife(objectID, False)