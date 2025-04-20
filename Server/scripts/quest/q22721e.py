# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002000
# School for the Gifted  :: First Floor Corridor
KINESIS = 1531000
JAY = 1531001
YUNA = 1531002

sm.lockForIntro()
sm.setIntroBoxChat(YUNA)
sm.sendNext("Did you get all of the signatures? Nice.")
sm.sendSay("#face3#Hey, #b#h0##k... Don't you think maybe you've done enough of the hero thing?")
sm.sendSay("#face3#I know helping people is a noble cause, but your duty is to be a student. And on top of that, you're the Student Council President. How do you think we all feel when you ditch us to go levitate trucks and tear up pavement?")

selection = sm.sendNext("#face3#Well, #b#h0##k?\r\n#b#L1#Yuna, you worry too much. I can help you relax...#l\r\n#L2#Maybe you can tell me more about that last council meeting...#l")

if selection == 2 or selection == 1:# TODO: sniff selection 1
    sm.setIntroBoxChat(KINESIS)
    sm.sendNext("#face2#Is that a new ribbon? It's quite nice. You always look so good, but today you look-")

    sm.setIntroBoxChat(YUNA)
    sm.sendSay("#face1#Are you ALWAYS going to change the topic when this comes up?")
    sm.sendSay("#face1#Maybe you should save those lines for your little groupies! Especially if you're not going to take this seriously!")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("Hey. If I wasn't serious, I wouldn't be here. The only girl I'm worried about is the one right in front of me.")

    sm.setIntroBoxChat(YUNA)
    sm.sendSay("#face1#Then enough with the lines and answer my question.")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("#face2#Sure, sure... What was it, again?")

    sm.setIntroBoxChat(YUNA)
    sm.sendSay("#face1#YOU. ARE. INFURIATING.")
    sm.sendSay("#face2# Look... All I'm saying is, don't go getting yourself killed playing hero. I know you think you've got everything on lockdown, but you can't be prepared for everything...")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("#face2#Are you worried that I'm going to get hurt? Or are you worried that I'm going to get more fans?")

    sm.setIntroBoxChat(YUNA)
    sm.sendSay("#face1#See? SEE? THIS is what I'm talking about! You don't LISTEN!")

    sm.setIntroBoxChat(JAY)
    sm.sendSay("#face10##fs12#...You're so bad at this, yet she doesn't dump you. It's incredible.")

    sm.setIntroBoxChat(YUNA)
    sm.sendSay("#face1#RRRRGH... Take this and GO. Just GO. I'm the Vice President, I don't deserve this kind of abuse.")
    sm.sendSay("#face2##b...I keep feeling so anxious.#k")

    sm.setQuestEx(22700, "q21end", "1")
    sm.completeQuest(parentID)
    sm.giveExp(1200)
    sm.sendDelay(1000)

    sm.setIntroBoxChat(JAY)
    sm.sendNext("#face9#K, you're going to regret teasing Yuna so much.")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("#face2#I can't help it. It's too much fun.")
    sm.sendSay("Hey, why aren't you here? You keep talking about wanting some field testing.")

    sm.setIntroBoxChat(JAY)
    sm.sendSay("#face9#I'm not a fan of the sun, buddy. Plus, I like giving orders from on high.")

    sm.blind(1, 200, 0, 1300)
    sm.sendDelay(1600)

    sm.sayMonologue("#fs20#\r\n\r\n#fEffect/BasicEff.img/QuestAlert/Default/0#\r\nClick the lightbulb on the left side of your screen to check the quests that have arrived remotely.", True)

    sm.blind(0, 0, 0, 1300)
    sm.sendDelay(1600)

    sm.unlockForIntro()