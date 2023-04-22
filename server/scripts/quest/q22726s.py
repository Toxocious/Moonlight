# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001000
# Hideout  :: HQ
KINESIS = 1531000
JAY = 1531001

sm.setIntroBoxChat(JAY)
sm.sendNext("#face1#My money is on this being a trap. I'd advise against you going.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("If I don't go, how am I supposed to stop the big bad dropping monsters on everyone?")

sm.setIntroBoxChat(JAY)
sm.sendSay("You could always come up with a plan instead of winging it and hoping for the best. What's it going to be?")

selection = sm.sendNext("All signs point to the school, and soon. Are you going?\r\n#b#L1#Wing it and hope for the best. Time to go back to school.#l\r\n#b#L2#Tell me more of these 'plans'.#l")

if (selection == 1):
    if sm.sendAskYesNo("I don't even know why I ask. You've still got time to get over there and prepare. I'll back you up from here, of course."):
        sm.sendNext("I've got the location pinpointed. Looks like a classroom... and it's still occupied.")

        sm.setIntroBoxChat(KINESIS)
        sm.sendSay("#b(Go to the school and look for anything weirder than usual.)#k")
        sm.setQuestEx(22700, "coNight", "1")
        sm.startQuest(parentID)
    else:
        sm.sendSayOkay("R-really? Oh. Umm... okay. Didn't see that coming. Lemme get my graph paper...")
        sm.dispose()