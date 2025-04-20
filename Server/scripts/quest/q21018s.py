# 140000000
LILIN = 1201000

sm.setSpeakerID(LILIN)
sm.sendNext("Now, you will undergo a test that will determine whether you're fit or not. All you have to do is take on the most powerful monster on this island, #o0100134#s. About #r50#k of them would suffice, but...")

if sm.sendAskAccept("We can't have you wipe out the entire population of #o0100134#s, since they aren't many of them out there. How about 5 of them? You're here to train, not to destroy the ecosystem."):
    sm.startQuest(parentID)
    sm.sendNext("#o0100134#s can be found in deeper parts of the island. Continue going left until you reach #b#m140010200##k, and defeat #r5 #o0100134#s#k.")
else:
    sm.sendNext("Oh, is 5 not enough? If you feel the need to train further, please feel free to slay more than that. If you slay all of them, I'll just have to look the other way even if it breaks my heart, since they will have been sacrificed for a good cause...")
    sm.dispose()