# Created by MechAviv
# Map ID :: 100000000
# NPC ID :: 9110000
# Perry
maps = [["Showa Town", 100000000], ["Ninja Castle", 100000000], ["Six Path Crossway", 100000000]]# TODO
sm.setSpeakerID(9110000)
selection = sm.sendNext("Welcome! Where to?\r\n#L0# To Showa Town#l\r\n#L1# To Ninja Castle#l\r\n#L2# To Six Path Crossway#l")


sm.setSpeakerID(9110000)
if sm.sendAskYesNo(maps[selection][0] + "? Drive safely!"):
    sm.warp(maps[selection][1])
else:
    sm.setSpeakerID(9110000)
    sm.sendNext("I hope the ride wasn't too uncomfortable. I can't upgrade the seating without charging fares.")


