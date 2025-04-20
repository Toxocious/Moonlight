SKIPPER = 1096000

sm.setSpeakerID(SKIPPER)
sm.sendNext("Greetings! Isn't this just the perfect weather for a journey? I'm Skipper, the captain of this fine ship. You must be a new Explorer, eh? Nice to meet you.")

if sm.sendAskAccept("We're not quite ready to leave, so feel free to look around the ship while we're waiting."):
    sm.sendNext("Looks like we're all set! I think this is going to be a great voyage. Let's get underway.")
    sm.startQuest(parentID)
    sm.warp(3000000, 0)
else:
    sm.sendNext("Hey, take it easy! Sometimes you just gotta wait.")
    sm.dispose()