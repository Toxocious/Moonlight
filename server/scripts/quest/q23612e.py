# Only the Brave ; Xenon 3rd Job

PROMATHUS = 2300002

sm.setSpeakerID(PROMATHUS)
sm.sendNext("That took longer than I expected. Is everything all right?")
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("(You explain that you were almost discovered by the Black Wings.)")
sm.setSpeakerID(PROMATHUS)
sm.sendSay("Haha! Gelimer would be furious if he knew you'd gotten by right under his nose. Quite a daring move, yes, yes.")
if sm.sendAskYesNo("Well done, child., well done. Your courage is evident. Will you take what we have readied for you?"):
    if not sm.canHold(1142577):
        sm.sendSayOkay("Please make some space in your equipment inventory.")
        sm.dispose()
    sm.completeQuest(parentID)
    sm.jobAdvance(3611)
    sm.giveAndEquip(1353003)
    sm.giveItem(1142577)
    sm.sendSayOkay("I've uploaded every bit of information on the Black Wings and their agents to your memory banks. Your neural interface should create a sort of camouflage effect, should any Black Wings cross your path. To them, you will appear as someone they do not know. It should even work on Gelimer.")
