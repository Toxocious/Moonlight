# (701210161)  |  Path of Stone 1  Hidden Map for Script in Shaolin Temple Questline

coordinates = [
    [207, 240],
    [-177, 240],
    [-281, -60],
    [159, -120],
    [310, 240]
]

DEMON_B_GONE = 2434568
ZHEUNG_GUAN = 9310046
HAUNTED_STONE = 9310588 # Npc

sm.lockInGameUI(True, False) #Lock, but don't show black frames
sm.chatScript("Test the Demon B Gone.")
sm.sendDelay(1000)

sm.forcedMove(False, 860)
sm.sendDelay(4000)

sm.removeEscapeButton()
sm.setBoxChat()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Geez. This place is crawling with demons...")
sm.sendNext("Well, time to test this out. If I sprinkle it on the demons, they should disappear. I hope #p"+ str(ZHEUNG_GUAN) +"# knows what he's talking about.")
if sm.hasItem(DEMON_B_GONE):
    sm.consumeItem(DEMON_B_GONE)

sm.sendDelay(2000)

sm.sendNext("Hey, looks like it's working!")

sm.sendDelay(3000)

for coordinate in coordinates:
    sm.spawnNpc(HAUNTED_STONE, coordinate[0], coordinate[1])

sm.sendNext("#fs 22# Whoa, whoa, whoa! The demons aren't disappearing... they're multiplying!")

sm.sendNext("Well, THAT didn't work. I'd better let #p"+ str(ZHEUNG_GUAN) +"# know.")

sm.forcedMove(True, 850)
sm.sendDelay(5000)

sm.warpInstanceOut(701210120, 2)
sm.completeQuest(62005)
sm.giveExp(1515339)

sm.lockInGameUI(False)
