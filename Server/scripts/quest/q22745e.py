# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
KINESIS = 1531000
THREE_MOON = 1531004

sm.setIntroBoxChat(THREE_MOON)
sm.sendNext("I can sense new powers blossoming within you. The first step of the journey is taken.")
sm.sendSay("Let me ask you this, #h0#, now that you have grown.")

selection = sm.sendNext("What is it that makes one more powerful?\r\n" +
                        "#b\r\n" +
                        "#L1#Natural Ability#l\r\n" +
                        "#L2#Careful Planning#l\r\n" +
                        "#L3#Hard Work#l")
if selection == 1:#TODO: SNIFF the others
    sm.setIntroBoxChat(KINESIS)
    sm.sendNext("It has to be natural ability, right? I would never have made it this far without my psychokinesis.")

    sm.setIntroBoxChat(THREE_MOON)
    sm.sendSay("Is that truly your belief?")
    sm.sendSay("No correct answer exists for this question. What you feel is the correct answer would be your own correct answer.")
    sm.sendSay("Your next step is to decide on the path of training, for there are many. We shall meet again when you grow stronger still.")

    sm.setIntroBoxChat(KINESIS)
    sm.sendSay("Thanks, wolf dude.")
    sm.sendSay("#face2#And sorry I called you a dog when I first met you!")

    sm.setIntroBoxChat(THREE_MOON)
    sm.sendSay("......")
    sm.completeQuest(parentID)
    sm.giveExp(12500)