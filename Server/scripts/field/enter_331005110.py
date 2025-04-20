# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331005110
# Unnamed
KINESIS = 1531000
JAY = 1531001
WHITE_MAGE = 1531005

sm.lockForIntro()
sm.changeBGM("Bgm00.img/Silence", 0, 0)
sm.blind(1, 255, 0, 0)
sm.setSpineObjectEffectAddPlay(True, "subway_bg", "outside", True)
sm.setSpineObjectEffectAddPlay(True, "subway_main", "outside", True)
sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)

sm.sendDelay(1000)

sm.setIntroBoxChat(KINESIS)
sm.sendNext("#face4#This is such a disaster. I'm sure it'll make the news tonight.")

sm.setIntroBoxChat(JAY)
sm.sendSay("#face11#K, you doing okay?")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("I'm fine. What about you?")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face9#Eh, no big. That cat you brought in is missing, though.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("Wait, what? Where did Nero go?")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face9#No idea. It left right after you did. Maybe you didn't pet it enough.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("You should have stopped it, Jay.")


sm.setIntroBoxChat(JAY)
sm.sendSay("#face9#Pfft, no thanks. I'm not a cat person. I take care of the hardware, so you can take care of any fuzzy critters you drag back here.")

sm.playSound("Sound/Voice3.img/Kinesis/subway_04")
sm.progressMessageFont(3, 20, 20, 0, "This train will stop service at the next station. ")
sm.sendDelay(4000)

sm.progressMessageFont(3, 12, 20, 0, "Please follow the crew's instructions and exit the train quickly and calmly.")
sm.sendDelay(3000)

sm.progressMessageFont(3, 20, 20, 0, "Thank you.")
sm.sendDelay(1000)

sm.sendNext("This could blow your whole secret identity thing, K. You okay with that?")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("That just means more fans, Jay. You know I don't let stuff like that hold me back. I'll return after I get off at the next station.")

sm.sendDelay(1000)

sm.setSpineObjectEffectPlay(True, "subway_bg", "insiding", False, False)
sm.setSpineObjectEffectPlay(True, "subway_main", "insiding", False, False)
sm.setSpineObjectEffectAddPlay(True, "subway_bg", "inside", True)
sm.setSpineObjectEffectAddPlay(True, "subway_main", "inside", True)
sm.sendDelay(1500)

sm.sendNext("Huh. Wonder why the train's not stopping.")

sm.sendDelay(1000)

sm.playSound("Sound/SoundEff.img/blackHeaven/explosion10")
sm.setFieldFloating(331005110, 1, 5, 20)
sm.changeBGM("Bgm41.img/BigMachine_mission", 0, 0)
sm.sendDelay(1000)

sm.playSound("Sound/Voice3.img/Kinesis/subway_05")
sm.progressMessageFont(3, 20, 20, 0, "Regular service on this train has been interrupted. ")
sm.sendDelay(4000)

sm.progressMessageFont(3, 20, 20, 0, "Please be careful. Thank you.")
sm.sendDelay(4000)

sm.setIntroBoxChat(JAY)
sm.sendNext("Hold up, K! I'm getting a bizarre energy reading up ahead. No idea what it is.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("Ahead? I don't see...")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face0#Argghhh!")

sm.setFieldFloating(331005110, 20, 20, 100)
sm.showFadeTransition(0, 1000, 3000)
sm.sendDelay(500)

sm.removeOverlapScreen(1000)
sm.sendDelay(500)

sm.showFadeTransition(0, 1000, 3000)
sm.sendDelay(500)

sm.removeOverlapScreen(1000)
sm.sendDelay(500)

sm.showFadeTransition(0, 1000, 3000)
sm.sendDelay(500)

sm.removeOverlapScreen(1000)
sm.sendDelay(500)

sm.setFieldFloating(331005110, 1, 5, 200)
sm.setIntroBoxChat(JAY)
sm.sendNext("#face1#K? Kinesis? What's going on?")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face0#Aaah... It's... My head!")


sm.setIntroBoxChat(WHITE_MAGE)
sm.sendSay("#face2#Is this the first time we've met in person? Should I say it's an honor... Kinesis?.")

sm.spawnNpc(WHITE_MAGE, 627, 48)
sm.showNpcSpecialActionByTemplateId(WHITE_MAGE, "summon")
sm.sendDelay(3000)

sm.setFieldFloating(331005110, 0, 0, 0)
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 2000, 0, 530, 0)

sm.removeOverlapScreen(1000)
sm.playSound("Sound/SoundEff.img/PvP/80001740_Use")
sm.OnOffLayer_On(500, "0", 800, 0, 12, "Map/Effect2.img/kinesis/chaWhite", 5, 1, -1, 0)
sm.OnOffLayer_Move(300, "0", -800, 0)
sm.sendDelay(3000)

sm.playSound("Sound/SoundEff.img/12thMS/jump_m")
sm.OnOffLayer_Off(500,  "0", 0)
sm.OnOffLayer_Move(100, "0", 800, 0)
sm.sendDelay(1000)

sm.setIntroBoxChat(WHITE_MAGE)
sm.sendNext("You're quite special, you know. And not for the reason you think. You are the first human of this world to get abilities from the other.")
sm.sendSay("#face1#You are the catalyst that I have been searching for.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face0#Urgh... What are you talking about?")

sm.blind(1, 255, 0, 500)
sm.sendDelay(500)

sm.blind(1, 255, 0, 0)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)
sm.moveCamera(True, 0, 0, 0)
sm.sendDelay(300)

sm.playURLVideoByScript("http://nxcache.nexon.net/maplestory/video/yt/Kinesis2.html")
sm.completeQuest(22733)
sm.startQuest(22734)
sm.completeQuest(22734)
sm.giveExp(2500)

sm.warp(331005120, 0)