# Created by MechAviv
# Map ID :: 940001160
# Unknown : Unknown

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.hideUser(True)
OBJECT_1 = sm.sendNpcController(3000106, 250, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(3000107, -800, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(3000108, -600, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Did something happen to Kaiser? I've got a bad feeling...")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("High Priest!")


sm.moveCamera(False, 300, -400, 27)


sm.sendDelay(1334)


sm.moveNpcByObjectId(OBJECT_2, False, 450, 100)
sm.moveNpcByObjectId(OBJECT_3, False, 450, 100)
sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(1000)


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("What's going on? Beldar...why are you here...?")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Heliseum has been captured. I escaped along with a few other survivors.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Heliseum is... But, Kaiser left for Heliseum a while ago...")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I have not seen him. As we were escaping, though... We saw a huge explosion. It might have been...")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Fenelle! I will lead our forces into Heliseum and re-take the city! Kaiser might need-")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Don't be a fool! Heliseum has already fallen. We must hold the line here, and protect the shield. If Nova is to survive this catastrophe, we must act carefully.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Then...you're just going to let Kaiser...?")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("There were thousands of Specters in Heliseum. If Kaiser has not escaped by now...he won't be escaping at all. Not even he can survive such odds.")


sm.showEffect("Effect/Direction9.img/effect/tuto/BalloonMsg0/2", 0, 0, -100, 0, OBJECT_1, False, 0)
sm.showEffect("Effect/Direction9.img/effect/tuto/BalloonMsg0/2", 0, 0, -120, 0, OBJECT_2, False, 0)
sm.showEffect("Effect/Direction9.img/effect/tuto/BalloonMsg0/2", 0, 0, -120, 0, OBJECT_3, False, 0)
sm.sendDelay(2000)


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("In truth, we wouldn't have enough power to form the shield over Pantheon without the Relics at Heliseum.")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("So, I ordered priests to bring the Relics with them when we escaped. With the Relics safe, we can raise a shield strong enough to protect us from Darmoor.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I see. You only care about saving yourself.")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Say whatever you want, but I did what I had to for the good of Nova. It would have been easy to stay and fall in battle. It is harder to have to live with the shame of our loss.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("But...how did Heliseum fall in the first place? They had the shield up, and still they were captured.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Come to think of it, how DID Heliseum fall that easily with the shield intact?")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Do you remember Magnus, the disgraced knight exiled by the Council?")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Yes. He was said to be a strong as Kaiser, but used his power for personal gain. A wholly despicable fellow.")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It was him. He disabled the shield and let Darmoor's forces in. Magnus appeared in the city not long before the invasion, and we thought perhaps he had turned over a new leaf. Instead...")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It was mistake to think that it would take Darmoor too long to invade. He found a way to seize both Aboris and Heliseum at once, leaving us completely off-guard.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...By the way, where is the King and the royal families?")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I don't know. We've lost track of so many people in all the chaos. If they escaped, they will find their way here. Our priority now is to get the shield up.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("This time, let's spread the Relics out instead of keeping them in a single place. We don't want same thing to happen here.")


sm.setSpeakerID(3000107)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("That will make them harder to defend, though.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Well, I will cast a protective spell on the Relics that only allows elite priests to handle them. It is not a perfect solution, but it is something.")


sm.setSpeakerID(3000108)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I will leave the shield to you. As for Kaiser, if he has fallen, when will he reincarnate?")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("His spirit will select a child born after he passes on. All we can do it wait.")


sm.setSpeakerID(3000106)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("For now, we should keep Kaiser's reincarnation a secret. The last thing we need is our enemy targeting our children.")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
# [FORCED_STAT_RESET] []
sm.warp(940002040, 0)
