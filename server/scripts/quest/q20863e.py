# Path of a Wind Archer - Completion
sm.setSpeakerID(1101005)  # Irena

sm.jobAdvance(1300)  # Wind Archer 1st Job
sm.resetAP(False, 1300)
sm.giveItem(1452002)  # War Bow
sm.giveItem(2060000, 1000)  # Bow Arrow
sm.giveItem(1142066)

sm.completeQuest(parentID)
sm.sendSayOkay("Congratulations, you are now a #bWind Archer#k! I have added 5 AP and 5 SP, enjoy your journey!")
