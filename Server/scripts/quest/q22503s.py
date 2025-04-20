sm.setSpeakerID(1013000)
sm.sendNext("No, no, no. This isn't what I need. I need something more nutritious, master!")

sm.setPlayerAsSpeaker()
sm.sendSay("#bHm... So you're not a herbivore. You might be a carnivore. You're a Dragon, after all. How does some #t4032453# sound?")

sm.setSpeakerID(1013000)
if sm.sendAskAccept("What's a...#t4032453#? Never heard of it, but if it's yummy, I accept! Just feed me something tasty. Anything but plants!"):
    if not sm.hasQuest(parentID):
        sm.startQuest(parentID)
    sm.setPlayerAsSpeaker()
    sm.sendSayOkay("#b#bOkay then, let's give #t4032453# to #p1013000#. Get them from a few #o1210100# critters on the farm. I think 3 will do it.")
    sm.dispose()
else:
    sm.sendNext("How can you starve me like this. I'm just a baby. This is wrong!")
    sm.dispose()