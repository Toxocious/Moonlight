# Created by MechAviv
# Quest ID :: 20846
# The Lost Permit 1

sm.setSpeakerID(1102200)
if sm.sendAskYesNo("Hi! Have we met? I can never keep track. What? Neinheart sent you? Great! You can help me!"):
    sm.setSpeakerID(1102200)
    sm.sendNext("Okaaaaay, so... Tons of new folks have joined the knights lately, and I'm supposed to bring them all investigation permits, but I totally lost them. Neinheart's gonna be so mad.")


    sm.setSpeakerID(1102200)
    sm.sendSay("I think I dropped them somewhere back over... Um... Where? Like, I think I was distracted by a Timu, so it must've been over at #bTimu's Forest#k! You can begin your search there. I'll follow when I feel like it.")


    sm.startQuest(20846)
    # [START_NAVIGATION] [FE CB BF 07 00 00 00 00 00 00 ]
    sm.avatarOriented("UI/tutorial.img/cygnus/1")
else:
    sm.setSpeakerID(1102200)
    sm.sendNext("Hey! It's your mission to help me!")


