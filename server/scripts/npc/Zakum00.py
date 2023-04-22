# Adobis - Door to Zakum field

sm.setSpeakerType(4)
sm.setSpeakerID(2030008)
res = sm.sendNext("Well... Okay. It seems you fulfill the requirements. What would you like to do?\r\n#b#L2# Receive an offering for Zakum.#l\r\n#b#L3# Go to El Nath.#l")
#b#L0# Investigate dead mine caves. #l
#b#L1# Explore Zakum Dungeon.#l

if res == 2:
    mode = sm.sendNext("Which Zakum are you making an offering to ?\r\n#b#L0# Easy Zakum#l\r\n#L1# Normal/Chaos Zakum#l")
    mode_name = "Easy " if mode == 0 else ""
    item = 4001796 if mode == 0 else 4001017
    sm.sendNext("You need an offering for "+mode_name+"Zakum..")
    sm.sendSay("Since I have lots of #b#t{0}##k{1}".format(str(item),
               (" that is needed for Zakum's offering, I will just give it for free."
                if mode == 0 else ",, I'll just give you some. Not good for anything besides offerings anyway.")))
    # if sm.hasItem(item):
    #    sm.sendSayOkay("You've already had it.")
    # el
    if sm.canHold(item):
        sm.giveItem(item, 5)
        sm.sendSay("Just drop this on "+mode_name+"Zakum's Altar.")
    else:
        sm.sendSayOkay("Please make more space in your ETC inventory.")
elif res == 3:
    sm.warp(211000000)
