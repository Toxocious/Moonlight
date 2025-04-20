# 25825 - [Job Advancement] Agent of Justige (AB 2nd job adv)

ESKALADE_NPC_ID = 3000132

if chr.getLevel() >= 60 and chr.getJob() == 6510:
    sm.setPlayerAsSpeaker()
    sm.sendNext("Eskalade, how come all my skills are getting... pinker?")
else:
    sm.dispose()

sm.setSpeakerID(ESKALADE_NPC_ID)
sm.sendNext("Well, it IS my favorite color. Maybe it means you're getting better at using my power.")

sm.setPlayerAsSpeaker()
sm.sendNext("Wait, your favorite color is pink? Why do I get the only dragon who loves cutesy things?")

sm.setSpeakerID(ESKALADE_NPC_ID)
sm.sendNext("This whole thing would be a lot easier if you just gave in to my supreme will and played along.")

sm.setPlayerAsSpeaker()
sm.sendNext("You seriously can't make the pink go away?")

sm.setSpeakerID(ESKALADE_NPC_ID)
sm.sendNext("Nope! You can just deal with it. Besides, it's a good color for you. Brings out your rosy cheeks."
            "Now, do you want to synchronize souls again?")

sm.setPlayerAsSpeaker()
sm.sendNext("Yeah, I guess so... I'll get stronger, right?")

sm.setSpeakerID(ESKALADE_NPC_ID)
sm.sendNext("Absolutely! You will become my genuine pink angel.")

sm.setPlayerAsSpeaker()
sm.sendNext("I really don't know about this...")

sm.setSpeakerID(ESKALADE_NPC_ID)
response = sm.sendAskYesNo("You have to make sacrifices to be a hero! Don't you want that?")
if response:
    sm.setPlayerAsSpeaker()
    sm.jobAdvance(6511)
    sm.giveAndEquip(1352603)
    sm.sendNext("I think I just got stronger!")
sm.dispose()

