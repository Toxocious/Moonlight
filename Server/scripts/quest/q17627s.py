# [Commerci Republic] Delfino Deleter 2

sm.setSpeakerID(9390256) # Leon Daniella
sm.sendNext("Is it just me, or am I totally awesome at fish-slaying?")

response = sm.sendAskYesNo("We successfully routed the #o9390808# group, but I doubt that was the end. "
                "It seems like there are other groups around. "
                "You're going to help again, right?")

if response:
    sm.sendNext("#b#o9390811##k group should be in #m865020100#. Let's see who can rout out #b#e30#k#n of them faster again! I'll be waiting in #m865020100#!")
    sm.startQuest(parentID)
else:
    sm.sendSayOkay("Oh, I suppose we can rest for a little bit.")
sm.dispose()
