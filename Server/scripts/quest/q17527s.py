# id 17527 ([Gollux] The Abdomen), field 863010000
sm.setSpeakerID(9390120) # Heart Tree Guardian
sm.sendNext("You can greatly reduce Gollux's strength if you neutralize its head last.")
sm.sendSay("Dodging its hands on the way up to its head won't be easy.")
sm.sendSay("You can greatly weaken Gollux by neutralizing its abdomen. The aura that protects it will weaken when its shoulders are broken, and it the core in its abdomen will not be able to inflict you with status ailments when you assault its head.")
res = sm.sendAskYesNo("What do you think? Do you want to give it a try?")
sm.startQuest(parentID)
sm.sendNext("Damaging Gollux's abdomen has put you one step closer to victory.")
