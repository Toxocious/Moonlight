# 2nd job advancement blaster

ELEX = 2151000
STANDARD_CHARGES = 1353401

sm.setSpeakerID(ELEX)
sm.sendNext("You've brougth the Black Wings Report. Not too shabby.")
sm.sendSay("I gave this mission to YOU for a reason. Those Black Wings you took out... They're the same ones who used to bully you. You make that connection? Must feel good to complete your mission AND get some sweet revenge.")
sm.sendSay("I've been watching you. Not everyone can adjust to a weapon like yours, with the mix of melee combat thrown in. Seriously, kid, not too shabby.")

if sm.sendAskYesNo("Okay, I think you're ready for the next stage, a stage in which you'll be transformed into an unimaginably strong Blaster..."):
    if not sm.canHold(STANDARD_CHARGES):
        sm.sendSayOkay("Please make space in your equipment inventory.")
        sm.dispose()
    sm.completeQuest(parentID)
    sm.consumeItem(4034787)
    sm.jobAdvance(3710)
    sm.giveItem(STANDARD_CHARGES)
    sm.sendNext("I've advanced you. I also passed along the knowledge of some amazing skills. Try practicing with them and eventually you'll get the hang of things.")
    sm.sendPrev("I'll see you at the next lesson. Until then, continue your good fight.")
else:
    sm.sendNext("I suppose you should take a moment to prepare yourself.")
