# 25825 - [Job Advancement] Agent of Justige (AB 2nd job adv)

ESKALADE_NPC_ID = 3000018

if chr.getLevel() >= 30 and chr.getJob() == 6500:
    sm.setSpeakerID(ESKALADE_NPC_ID)
    sm.sendNext("Looking pretty tough there, #h #.")
else:
    sm.dispose()

sm.setSpeakerID(ESKALADE_NPC_ID)
sm.setPlayerAsSpeaker()
sm.sendNext("Yeah? I'm totally rocking this Heroine of Justice thing.")
sm.sendNext("Have you noticed feeling a lot closer to me lately? Our pact has been getting stronger and stronger...")
sm.setPlayerAsSpeaker()
sm.sendNext("Uh, is that good?.")
sm.sendNext("What could be bad about getting closer to your dragon-lord? We need to move and act as one.")
sm.setPlayerAsSpeaker()
sm.sendNext("That sounds complicated... and gross. What are you getting at?")
sm.sendNext("I'm trying to let you use my muscle more! You never trust me...")
sm.setPlayerAsSpeaker()
sm.sendNext("I could use some more strength!")
response = sm.sendAskYesNo("I knew you'd be swayed by a little extra power. Let's strengthen our bond.")
if response:
    sm.jobAdvance(6510)
    sm.dispose()
    sm.completeQuest(25825)
    sm.giveAndEquip(1352602) # secondary
    chr.setSpToCurrentJob(5)
    sm.sendNext("Now focus!")
else:
    sm.sendSayOkay("What happened to wanting more power?")
sm.dispose()