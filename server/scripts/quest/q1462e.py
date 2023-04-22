#   [Job Adv] (5th job)   5th Job : Arcane stone of maple world

sm.setSpeakerID(1540942)
sm.sendNext("What is it that you want to protect the most in this world? \r\n\r\n #L0#Friends whom I went on adventures with. #l \r\n #L1#The people of Maple World #l")
sm.sendNext("I see. People have many things that are precious to them. There is no right answer... I simply wanted to know where your priorities lie. \r\n\r\n #v2435734# #bArcane Stone of Maple World x1")
if sm.canHold(2435734):
    sm.giveItem(2435734)
    sm.completeQuest(parentID)
    sm.dispose()
else:
    sm.sendSayOkay("I have something to give you, but you're carrying too many items. Please empty 1 Use slot, and then talk to me again.")
    sm.dispose()