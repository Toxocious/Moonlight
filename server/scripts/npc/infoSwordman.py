# Created by MechAviv
# [Dances with Balrog]  |  [10202]
# Maple Road : Split Road of Destiny

sm.setSpeakerID(10202)
sm.sendNext("Warriors possess an enormous power with stamina to back it up, and they shine the brightest in melee combat situation. Regular attacks are powerful to begin with, and armed with complex skills, the job is perfect for explosive attacks.")


sm.setSpeakerID(10202)
if sm.sendAskYesNo("Would you like to experience what it's like to be a Warrior?"):
    sm.setTemporarySkillSet(0)
    sm.createQuestWithQRValue(32219, "4000026")
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(1020100, 0)
else:
    sm.setSpeakerID(10202)
    sm.sendNext("If you wish to experience what it's like to be a Warrior, come see me again.")