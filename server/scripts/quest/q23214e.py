# 23214 | Sparring with Mastema

isDS = chr.getJob() == 3110

sm.setSpeakerID(2450017)

if not sm.canHold(1142343):
    sm.sendSayOkay("Please make space in your equip inventory.")
    sm.dispose()

sm.sendNext("How did it go? Did my copy give you any trouble? Hey... it didn't... say anything weird, did it? Well anyway, let me record your status in my little book here...")
sm.jobAdvance(chr.getJob()+1)
sm.giveItem(isDS and 1142343 or 1142555)
sm.giveAndEquip(isDS and 1099003 or 1099008)  # todo: upgrade instead of replace secondary? (potentials)
sm.completeQuest(parentID)
sm.sendNext("I'm glad I could help you. Now, if you'll excuse me, I used up too much of my power, so I'm going to pass out...")
sm.sendSayOkay("I used too much power, that's why I turned red!")
