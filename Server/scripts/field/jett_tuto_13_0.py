# Created by MechAviv
# Map ID :: 620100044
# Nautilus : Nautilus Bunkroom

sm.startQuest(5674)
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.setStandAloneMode(True)
sm.forcedInput(0)
sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("You're up. How ya feeling? Any concussions? Fuzzy vision? Head injury rage?")


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("What's going on...? Are you the... royal guards? I didn't do anything!")


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Yep, definitely a concussion... Relax, cowpoke! You're safe here with us scallywags.")


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Calm yourself down before I shackle you to the bed. You're on board the Nautilus and I'm her captain, Kyrin. ")


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Bark tells me you're from... space? Why did you come to Maple World?")


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("#b(Maple... World? I've never even heard of that! This place must be a real dump to get left off the galactic charts.)#k")


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("#b(I'd better keep low for now... let these outlaws think I'm not a threat. They saved my backside, but a bounty as big as mine has a funny way of changin' people's minds.)#k")


sm.sendDelay(500)


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("I was on a space-vacation when my ship's converter fan pooped out. Lucky for me your, planet was close and soft enough to touch down on. ")


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Y-You're sayin' you came from OUTER SPACE?! Hahahahaha! Captain, you don't believe this nonsense, do you?")


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#b(I knew that ship wasn't regular old Maple tech. I wonder how much I could make off of a real live alien...)#k")


sm.sendDelay(500)


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Put your tongue back in your mouth, Bark. Tell me about your ship, alien. It was awfully small to be pumping out that kind of power. You sure you're not just trying to cover up some secret government experiment?")


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Captain... You gotta be kidding...")


sm.sendDelay(500)


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("My kin figured out focus-beam propulsion a hundred years ago. You can believe whatever you want, but it works.")


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#b(With that kind of power, the Nautilus would be unstoppable! I am gonna be SO RICH.)#k")


sm.sendDelay(500)


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("We'll help you fix up your craft. In the meantime, you need to stay here and rest. I'll make sure the crew gives you a wide berth.")


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("If the Captain likes you, so do I. That's why I'm gonna give you back all the stuff I stole from you when you crashed.")


sm.sendDelay(500)


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Good show of hospitality there, Bark. That's a fancy looking gun you've got, alien, but what's with the busted up chunk of rock?")


sm.sendDelay(1000)


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("#b(That light... It has to be the core! B-but, it's only a fragment...)#k")


sm.sendDelay(500)


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("I found this floating around in the bits of your ship.")


sm.sendDelay(500)


sm.setSpeakerID(9270088)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("#b(It's a very small fragment of the Core...I must do what I can with this piece...and find a way to get my powers back!)#k")


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Ha! I'd be careful about what you say around old Black Bark. He never forgets a debt!")


sm.sendDelay(500)


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("#b(These outlaws should be the perfect bunch to keep me hidden for now. All I've gotta do is stick with them and lay low.)#k")


sm.setSpeakerID(9270089)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Why don't you get out and stretch your legs. If you're gonna stay a while, you should start getting in good with the crew right away. They're a lively bunch at the best of times. Come by the #m120000101# when you're done.")


sm.chatScript("Follow the arrow and move to the portal.")
sm.sendDelay(500)


sm.setStandAloneMode(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(Even this tiny fragment of the Core is giving off a lot of power. I may make it yet.)")

sm.levelUntil(10)
sm.giveAndEquip(1003530)
sm.giveItem(1492139)
sm.giveAndEquip(1352820)
# [INVENTORY_GROW] [01 1C ]
# [INVENTORY_GROW] [04 1C ]
sm.resetStats()
# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
sm.addSP(5, True)
# Inventory Operation with 1 operations.
# Inventory Operation with 1 operations.
sm.createQuestWithQRValue(5675, "1")

sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(If I'm gonna surround myself with outlaws, I need my gun at my side.\r\nJust need to open my inventory first. I wonder if the #bI#k key still works.)")

sm.createQuestWithQRValue(5674, "1")
sm.startQuest(53256)# not sure about that
sm.avatarOriented("UI/tutorial.img/29")
