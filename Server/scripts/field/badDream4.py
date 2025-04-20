# id 913051003 (Ereve : Conference Room of the Alliance), field 913051003
sm.lockInGameUI(True, True)
sm.showFieldEffect("twilightPerion/text2", 0)
sm.sendDelay(2500)
sm.setSpeakerType(3)
sm.setSpeakerID(1105005) # Lady Syl
sm.setParam(1)
sm.sendNext("Ereve destroyed and Cygnus turned to evil? Didn't we prove this was only a false future?")
sm.setSpeakerID(1105003) # Neinheart
sm.sendSay("That is not our future. It is nothing but a fabrication.")
sm.setSpeakerID(1105002) # Claudine
sm.sendSay("It's not completely impossible...")
sm.setSpeakerID(1105014) # Mihile
sm.sendSay("How dare you insinuate we would be involved?!")
sm.setSpeakerID(1105006) # Belle
sm.sendSay("What are you going to do about it, blondie?")
sm.setSpeakerID(1105001) # Athena Pierce
sm.sendSay("No fights!")
sm.setSpeakerID(1105002) # Claudine
sm.sendSay("Everyone stay calm. We need to look at this situation from all sides if we're going to solve it.")
sm.setParam(17)
sm.sendSay("#b(It's going to take some time before the Alliance members can agree with one another.)#k")
sm.setSpeakerID(1105001) # Athena Pierce
sm.setParam(1)
sm.sendSay("This is serious. That fake future is haunting peoples' dreams.")
sm.setSpeakerID(1105004) # Grendel the Really Old
sm.sendSay("Is something like that even possible? This kind of dream control would be next to impossible for even the most highly-trained magician.")
sm.setSpeakerID(1105000) # Cygnus
sm.sendSay("It's not just Henesys. This nightmare is spreading like the plague.")
sm.lockInGameUI(False, True)
sm.warp(913051004)
