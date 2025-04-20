sm.setSpeakerID(1013000)
sm.sendNext("Yo, master. Now that I've shown you what I can do, it's your turn. Prove to me...that you can find food! I'm starving. You can use my power now, so you have to take care of me.")

sm.setPlayerAsSpeaker()
sm.sendSay("Eh, I still don't get what's going on, but I can't let a poor little critter like you starve, right? Food, you say? What do you want to eat?")

sm.setSpeakerID(1013000)
#sm.spawnMob(1210100, -407, 35, False)
#sm.spawnMob(1210100, 28, 35, False)
#sm.spawnMob(1210100, 62, -265, False)
#sm.spawnMob(1210100, -548, -265, False)
#sm.spawnMob(1210100, -676, 35, False)
#sm.spawnMob(1210100, 432, 35, False)
#sm.spawnMob(1210100, -883, 35, False)
#sm.spawnMob(1210100, -770, -265, False)
sm.sendNext("Hi, I was just born a few minutes ago. How would I know what I eat? All I know is that I'm a Dragon... I'm YOUR Dragon. And you're my master. You have to treat me well!")

if sm.sendAskAccept("I guess we're supposed to learn together. But I'm hungry. Master, I want food. Remember, I'm a baby! I'll start crying soon!"):
    if not sm.hasQuest(parentID):
        sm.startQuest(parentID)
    sm.sendSayOkay("#b#b(#p1013000# the baby Dragon appears to be extremely hungry. You must feed him. Maybe your Dad can give you advice on what dragons eat.)")
else:
    sm.sendNext("*gasp* How can you refuse to feed your Dragon? This is child abuse! ")
    sm.dispose()