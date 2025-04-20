KINESIS = 1531000
JAY = 1531001
YUNA = 1531002
NERO = 1531003

sm.setIntroBoxChat(JAY)
sm.sendNext("Okay, now that you're all settled, take a look at this.")
sm.sendSay("This is the #bsinkhole#k that appeared on the day you disappeared. It hasn't really grown much since that first day.")
sm.sendSay("But the hole itself is not the problem. The problem is what's inside. ")
sm.sendSay("Things are occurring that #bcannot be explained with the current laws of physics#k. And it's impossible to get any closer to investigate. It's almost like it's a black hole. ")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("What about all the people? They couldn't just vanish... right?")

sm.setIntroBoxChat(JAY)
sm.sendSay("Thousands of people went missing when the sinkhole appeared. But since we can't get any closer, we have no idea what's happened to them. We have them currently listed as #bmissing#k. ")
sm.sendSay("Considering what we know now, though, it's entirely possible those people are fine, #bjust transported to the other world. ")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face4#Not bad, Jay. I guess you weren't sitting around and netsurfing while I was away.")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face2#To be fair, I have a LOT of time in my day.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("So, the missing people should be alive somewhere in Maple World? ")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face2#It's a reasonable theory, yes.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face4#Then I have to go save them. Simple as that.")

sm.setIntroBoxChat(YUNA)
sm.sendSay("#face2#W-what? You just returned, and you want to go back?")

sm.setIntroBoxChat(NERO)
if sm.sendAskYesNo("#face0#Ooh, I bet Three Moon can help you! Let's go see Three Moon."):
    sm.sendNext("#face0#Take the door on the floor below.")
    sm.startQuest(parentID)
else:
    sm.sendSayOkay("#face0#What, you got something more urgent?")