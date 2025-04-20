# 21301 - [Job Adv] (Lv.60)   Aran
sm.setSpeakerID(1201002)
if sm.sendAskYesNo("Did you slay the #o2600111#? Yippy! You're my master, indeed! Now, give me the Red Jade you fuond! I'll reattach it and... Wait, why aren't you saying anything? Don't tell me you didn't bring it back..."):
    sm.sendNext("What? You didn't bring the Red Jade?! Why not?! Did you forget?! Yikes, I never thought the Black Mage's curse would turn you into a dummy...")
    sm.sendNext("No, I can't let this drive me to despair. Now more than ever, I must stay optimistic and alert. Argh...")
    sm.sendNext("You can go back if you want, but I'm sure the thief has already fled the scene. You'll just have to make a new Red Jade. You've made one before, so you remember the required materials, don't you? So hurry it up.")
    sm.completeQuest(parentID)
    sm.dispose()
else:
    sm.dispose()