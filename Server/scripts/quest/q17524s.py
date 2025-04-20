# id 17524 ([Gollux] The Cleansing), field 863010000
sm.setSpeakerID(9390120) # Heart Tree Guardian
sm.sendNext("This is the Heart Tree once stood, before it was Gollux.")
sm.sendSay("I created this place in the hopes that it would return to its former self one day. But the corruption has spread so far... I'm not sure it can ever truly return.")
sm.sendSay("Gollux is a colossal being, but it is much like us. If its head falls, the rest of it will follow. It will not be easy though.")
sm.sendSay("You could go directly for its head if you wanted, but it would not be advisable. If one were to attack its arms and legs first, an assault on his head might be less dangerous. ")
res = sm.sendAskYesNo("Will you help me?")
sm.startQuest(parentID)
sm.sendNext("Thank you.")
