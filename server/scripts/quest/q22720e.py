# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331002000
# School for the Gifted  :: First Floor Corridor
KINESIS = 1531000
YUNA = 1531002

sm.lockForIntro()
sm.setIntroBoxChat(YUNA)
sm.sendNext("#b#h0##k! You jerk, I've been waiting TWO DAYS for you to get back to me! Have you been fooling with Jay again?")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("He forgot to tell me until just now, and I came as soon as I could. You know I hate to keep you waiting.")

sm.setIntroBoxChat(YUNA)
sm.sendSay("#face1#...Like I haven't heard THAT before.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face2#(Her expression softens. Nice save.)#k")

sm.setIntroBoxChat(YUNA)
sm.sendSay("Well... You know, this wouldn't happen if you'd just show up for Student Council Meetings. I mean, you ARE the president! Give me the Meeting Minutes.")

sm.playSound("Sound/SoundEff.img/PvP/80001740_Use")
sm.OnOffLayer_On(500, "0", 800, 0, 12, "Map/Effect2.img/kinesis/chaYuna", 5, 1, -1, 0)
sm.OnOffLayer_Move(300, "0", -800, 0)
sm.sendDelay(5000)

sm.playSound("Sound/SoundEff.img/12thMS/jump_m")
sm.OnOffLayer_Off(500, "0", 0)
sm.OnOffLayer_Move(300, "0", 800, 0)
sm.sendDelay(1000)

sm.consumeItem(4034601)
sm.completeQuest(parentID)
sm.giveExp(800)
sm.unlockForIntro()




