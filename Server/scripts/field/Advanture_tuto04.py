# Created by MechAviv
# ID :: [4000004]
# Hidden Street : Explorer Video

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.playURLVideoByScript("http://nxcache.nexon.net/maplestory/video/yt/adventurer.html")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.warp(4000005, 0)
