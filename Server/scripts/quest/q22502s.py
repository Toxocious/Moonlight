sm.setSpeakerID(1013103)
if sm.sendAskAccept("Wouldn't a lizard enjoy a #b#t4032452##k, like a cow? There are a lot of #bHaystacks#k nearby, so try feeding it that."):
    if not sm.hasQuest(parentID):
        sm.startQuest(parentID)
    sm.sendSayImage("UI/tutorial/evan/12/0")
    sm.dispose()
else:
    sm.sendNext("Hm, you never know unless you try. That lizard is big enough to be on Maple's Believe It Or Not. It might eat hay.")
    sm.dispose()