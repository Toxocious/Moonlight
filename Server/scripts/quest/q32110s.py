# id 32110 ([Ellinel Fairy Academy] Combing the Academy 1), field 101072200
sm.setSpeakerID(1500011) # Cootie the Really Small
sm.sendNext("Isn't this place amazing, #h0#. Let's have a look around.")
sm.setParam(2)
sm.sendSay("What should we do first?")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1500011) # Cootie the Really Small
sm.sendSay("You know what kids love the most? Secrets! I remember trading potion recipes with my friends behind the teacher's backs, hiding away my alchemy research in the nooks around school...")
sm.setParam(0)
res = sm.sendNext("I bet these kids have hidden notes all around the school. But how would we find them?#b\r\n#L0#Find the children and ask them where they hid things.#l\r\n#L1#They must be nearby, we should look around.#l#l\r\n#L2#I have no idea. This is hard...#l")
if res == 0:
    sm.sendNext("What a human thing to say, #h0#. We have to find their secrets FIRST! Then we find them... geez.")
elif res == 1:
    sm.sendNext("Everyone already searched, We should try something else.")
elif res == 2:
    sm.sendNext("Maybe there are clues written in these books.")
    sm.startQuest(parentID)
    sm.addQRValue(32133, "1")
