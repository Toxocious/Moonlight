# 57103 - Picking up the Pieces

sm.setSpeakerID(9130024)
if sm.sendAskYesNo("I am Yamanaka Yukimori, a retainer to the Amako clan. I was at Honnou-ji with you. Before we continue, may i ask your name?"):
    sm.setPlayerAsSpeaker()
    sm.startQuest(parentID)
    sm.sendNext("I Am Anegasaki Kenji, heir to the Matsuyama clan, and the descendant of Anegasaki Tomonobu")
    sm.setSpeakerID(9130024)
    sm.sendNext("Anegasaki Kenji... I've heard that name before! It's my honor to meet the master of Battoujutsu.")
    sm.setPlayerAsSpeaker()
    sm.sendNext("Please do not fawn over me, noble warrior. If i had known you were at Honnou-Ji, I would have greeted you as a brother.")
    sm.setSpeakerID(9130024)
    sm.sendNext("Much has happened since the raid on Honnou-Ji. I will fill you in when you have gathered yourself.")
    sm.dispose()
else:
    sm.dispose()