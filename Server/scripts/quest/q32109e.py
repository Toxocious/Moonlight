# id 32109 ([Ellinel Fairy Academy] Cootie's Suggestion), field 101072000
sm.setSpeakerID(1500001) # Headmistress Ivana
res = sm.sendNext("You think YOU can find the missing children? How do you propose to do that?#b\r\n#L0#Let's look around the lake. #l\r\n#L1#Why don't we use magic?#l#l\r\n#L2#I'd like to look through the childrens' rooms.#l")
if res == 0:
    sm.sendNext("The lake has been searched ten times over at this point. There's nothing left there to find.  ")
elif res == 1:
    sm.sendNext("Magic doesn't work like that.")
elif res == 2:
    sm.sendNext("I don't really like the idea of you snooping around, but you can go ahead.")
    sm.completeQuest(parentID)
