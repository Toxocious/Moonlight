# 21300 - [Job Adv] (Lv.60)   Aran
sm.setSpeakerID(1510009)
sm.sendNext("How is the training going? Hm, Lv. 60? You still ahve a long way to go, but it's definitely praiseworthy compared to the first time I met you. Continue to train diligently, and I'm sure you'll regain your strength soon!")
if sm.sendAskYesNo("But first, you must head to #b#m140000000##k your #b#p1201001##k is acting weird again. I think it has something to tell you. It might be able to restore your abilities, so please hurry."):
    sm.startQuest(parentID)
    sm.sendSayOkay("Anyway, I thought it was really something that a weapon had its own identity, but this weapon gets extremely annoying. It cries, saying that I'm not paying attention to its needs, and now... Oh, please keep this a secret from the Polearm. I don't think it's a good idea to upset the weapon any more than I already have.")
    sm.dispose()
else:
    sm.dispose()