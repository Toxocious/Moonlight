# id 17525 ([Gollux] The Right Shoulder), field 863010000
sm.setSpeakerID(9390120) # Heart Tree Guardian
sm.sendNext("You can greatly reduce Gollux's strength if you neutralize its head last.")
sm.sendSay("Dodging its hands on the way up to its head won't be easy.")
sm.sendSay("You can weaken the beast by damaging its right shoulder. Its defensive aura and abilities should fall, and it will not be able to use its right arm when you attack its head.")
res = sm.sendAskYesNo("What do you think? Do you want to give it a try?")
sm.startQuest(parentID)
sm.sendNext("Neutralize its right shoulder, and you will be one step closer to success.")
