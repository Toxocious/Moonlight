sm.setSpeakerID(9201324)
if sm.getChr().getLevel() < 160:
    sm.sendNext("Please return to me when you're a bit stronger..")
else:
    if sm.hasItem(4161080):
        sm.sendNext("I heard from an adventurer they might have seen her deep under the waves in a cave.")
    else:
        sm.sendNext("I seem to have lost my pet, could you help me find her? I'll need to know you're trustworthy first!.")
        if sm.sendAskYesNo("Are you ready for your question?"):
            question = sm.sendAskText("What is the colour of Athena Pierce's bow?", "", 1, 20)
            if question == "Blue":
                sm.sendNext("I'm so glad I can trust you! Hurry now, I really miss her.")
                sm.sendNext("Take this item with you so she knows that you can be trusted.")
                sm.sendNext("I heard from an adventurer they might have seen her deep under the waves in a cave.")
                sm.sendNext("Please return her safely to me.")
                if sm.canHold(4161080):
                    sm.giveItem(4161080)
                else:
                    sm.sendNext("You don't have pocket space for my item!")

        else:
            sm.sendNext("Please return why you can answer my question!")