# Trainer's Command
sm.sendNext("Do you want to become an elite pet trainer like me? Once you reach my level, it's easy to handle multiple pets at once. What do you think? Are you interested in walking around with multiple pets at once?")

if sm.sendAskYesNo("If you want to learn how to control a group of pets, then get me some Pet Snacks. I have my own secret method of handling them, and it is foolproof. Are you interested?"):
    sm.sendNext("You won't find #bPet Snacks#k at a regular store... but you can find it at a big store, where you can buy a huge variety of items. Find it and return to me!")
    sm.startQuest(5747)
    sm.dispose()
else:
    sm.sendSayOkay("There's no such thing as a free lesson here. I'll give you some time to think over.")
    sm.dispose()