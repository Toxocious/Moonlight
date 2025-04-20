# Created by MechAviv
# Kinesis Introduction
from net.swordie.ms.enums import TextEffectType

KINESIS = 1531000
JAY = 1531001
NERO = 1531003

sm.setIntroBoxChat(JAY)
sm.sendNext("Welcome baWHAT IS THAT.")

sm.setIntroBoxChat(NERO)
sm.sendSay("#face4#Meow!")

sm.lockForIntro()
sm.changeBGM("Bgm38.img/LifeIsComedy", 0, 0)
sm.sendDelay(1500)

sm.setIntroBoxChat(JAY)
sm.sendNext("Y-you brought a CAT in here?! A filthy, disgusting alley cat? It's going to get hair in my cooling fans! Or chew my cables! Or run off with my collector's edition models! Get it OUT of here!")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("Aww, have a heart, Jay. Look at these little tiny paws. Nero here would be done for on the street. You want that on your conscience?")


sm.setIntroBoxChat(NERO)
sm.sendSay("#face4#Meow! ")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face2#Here, touch the paws. Touch them.")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face5#Dude, get the heck away from me! Ugh... FINE. Do what you want. But if it pees on my servers, it's going through a window.")
sm.sendSay("#face5#Did you at least bring back something on the monsters? You know, the thing I ASKED FOR.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face2#Yeah, chill. It's right here.")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face6#...Covered in dust and grime, of course. Thanks for wiping it off before bringing it in here.")
sm.sendSay("#face8#You both just... stand over there. Away from me. Good. Let me work.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face2#Here Nero, come closer and look.")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face7##fs30#KINESIS!")

sm.blind(1, 255, 0, 500)
sm.sendDelay(500)

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.createFieldTextEffect("#fn������� ExtraBold##fs18#The next day, at the hideout", 100, 2500, 4, 0, 0, 1, 4, TextEffectType.KinesisIntro, 0, 0)
sm.sendDelay(3000)

# take 10 Twisted Dust Ball
sm.completeQuest(parentID)
sm.giveExp(750)
sm.unlockForIntro()
sm.setIntroBoxChat(NERO)
sm.sendSay("")


sm.setIntroBoxChat(NERO)
sm.sendSay("")


sm.setIntroBoxChat(NERO)
sm.sendSay("")


sm.setIntroBoxChat(NERO)
sm.sendSay("")


sm.setIntroBoxChat(NERO)
sm.sendSay("")