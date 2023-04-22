# Neru (2040033) | Ludibrium Pet Walkway
if sm.hasItem(4031128):
    sm.sendNext("Eh, that's my brother's letter! "
                "Probably scolding me for thinking I'm not working and stuff...Eh? "
                "Ahhh...you followed my brother's advice and trained your pet and got up here, huh? "
                "Nice!! Since you worked hard to get here, I'll boost your intimacy level with your pet.")
    sm.consumeItem(4031128)
    sm.sendSayOkay("What do you think? Don't you think you have gotten much closer with your pet? "
                   "If you have time, train your pet again on this obstacle course...of course, with my brother's permission.")
    # Pet Closeness Gained
else:
    sm.sendSayOkay("My brother told me to take care of the pet obstacle course, "
                   "but ... since I'm so far away from him, I can't help but wanting to goof around ...hehe, "
                   "since I don't see him in sight, might as well just chill for a few minutes.")

