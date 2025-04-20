sm.setSpeakerID(1402000)
sm.sendNext("All preparations complete. The #m150000000# is anchored directly above the Knight's Chamber and it does not appear that we have been spotted from below. ")
sm.sendSay("You will need to remain wary. The security in #m130000000# is currently on high alert. I suppose having this many of Maple World's leaders in one place warrants the rather excessive defense strategy they've adopted. ")
sm.sendSay("Still, they are only guards. It should not prove too difficult for a seasoned thief like yourself to slip past them unnoticed. Keep a close watch on their eyelines and you will be fine.")
sm.sendSay("The Lumiere will remain here until you return. Do not concern yourself with the pursuit. We'll keep the engine running.")

response = sm.sendAskAccept("It is time for you to make your decision. Are you ready to infiltrate #m130000000#?")
if response:
    sm.startQuestNoCheck(25000)
    sm.sendSayOkay("I wish you luck.")
else:
    sm.sendNext("The window of opportunity will not remain open forever.")
sm.dispose()