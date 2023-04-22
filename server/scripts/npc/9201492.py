import random

items = [1050004

]


if sm.sendAskYesNo("#eHey, i'm a retarded bitch but i can give you any #e#bOverall#n #ein the game!"):
    question = sm.sendAskYesNo("#eWould you like to spend #r2k NX#n #efor a random #bOverall?")
    if question:
        sm.giveItem(random.choice(items))
        sm.deductNX(-2000)
    else:
        sm.sendNext("no")