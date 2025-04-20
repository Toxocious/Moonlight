# id 1500019 (Ephony the Fairy), field 101073110
sm.setSpeakerType(3)

if not sm.hasMobsInField():
    sm.setParam(5)
    sm.setInnerOverrideSpeakerTemplateID(1500019) # Ephony the Fairy
    sm.sendNext("Whew! You saved me! I thought those monsters were gonna eat me up.")
    sm.setInnerOverrideSpeakerTemplateID(1500020) # Phiny the Fairy
    sm.sendSay("A-are you a h-hero?")
    sm.setParam(17)
    sm.sendSay("#b(There were five missing in total... Where are the other kids?)#k")
    sm.setParam(5)
    sm.sendSay("Ya gotta get Woonie and Tracy! I saw a shadow monster that was gonna eat them!")
    sm.setParam(17)
    sm.sendSay("#bA shadow monster?#k")
    sm.warp(101073201)
    sm.completeQuest(32126)
else:
    sm.sendNext("Please kill all the mobs, we can't get out like this!")
