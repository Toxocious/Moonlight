# id 17528 ([Gollux] The Head), field 863010000
sm.setSpeakerID(9390120) # Heart Tree Guardian
sm.sendNext("As I told you before, Gollux's head must be neutralized to quell the beast.")
sm.sendSay("Dodging its hands on the way up to its head won't be easy.")
sm.sendSay("You can greatly weaken Gollux by neutralizing its abdomen. The aura that protects it will weaken when its shoulders are broken, and it the core in its abdomen will not be able to inflict you with status ailments when you assault its head.")
res = sm.sendAskYesNo("What do you think? Do you want to give it a try?")
sm.startQuest(parentID)
sm.sendNext("If you stun the head, we might be able to revert Gollux back to its original form.")
