# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002000
# School for the Gifted  :: First Floor Corridor
KINESIS = 1531000
JAY = 1531001
YUNA = 1531002

sm.setIntroBoxChat(YUNA)
sm.sendNext("#face3##b#h0##k... You know your picture is all over the internet, right? You keep letting yourself get photographed!")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Why are you whispering? Speak up.")

sm.setIntroBoxChat(YUNA)
sm.sendSay("#face4#What if someone hears us? Ooh, we're trying so hard to keep this secret, and you're blowing it! What is your DEAL?")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face9##fs12#Yeah, she's right! What IS your deal? Heh heh.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("Relax. Only the three of us know the truth.")

sm.setIntroBoxChat(YUNA)
sm.sendSay("For now! But... Ugh, whatever. I need you to do something. I need 100 student signatures for the gymnasium renovation.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face2#I'm sure our lovely Vice President has already taken care of it?")

sm.setIntroBoxChat(YUNA)
selection = sm.sendNext("I got 97 signatures. I need YOU to get the remaining 3, #b#h0##k.\r\n#b#L1#I guess I can do that.#l\r\n#L2#Ehh, sounds like too much work.#l")
if selection == 1:
    sm.sendNext("Huh. Are you turning over a new leaf? I didn't think you'd agree so easily.")
elif selection == 2:
    sm.sendNext("#face3#Sounds like someone wants their position vacated, then.")

if sm.sendAskYesNo("Get three student signatures. That's it! You can do that, right?"):
    sm.sendNext("I'll be waiting here.")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("#b(You should go find 3 student signatures somewhere in the school.)#k")

    sm.startQuest(parentID)
else:
    sm.sendSayOkay("#b#h0##k, I'm staying right here until you get those signatures.")