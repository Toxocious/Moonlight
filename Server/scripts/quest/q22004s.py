# 22004 |   Fixing the Fence (Evan intro)
sm.setSpeakerID(1013103)
sm.sendNext("The pigs at the farm have been acting strange these past couple days. They've been angry and irritable for no reason. I was worried so I came out to the farm early this morning and sure enough, it seems like a few of these Pigs got past the fence.")
if sm.sendAskYesNo("Before I go and find the Pigs, I should mend the broken fence. Luckily, it wasn't damaged too badly. I just need a few Thick Branches to fix it right up. Will you bring me #b3 Thick Branches#k, Evan?"):
    sm.sendNext("Oh, that's very nice of you. You'll be able to find the #bThick Branches#k from the nearby #rStumps#k. They're not too strong, but use your skills and items when you find yourself in danger.")
    sm.startQuest(parentID)
else:
    sm.dispose()
