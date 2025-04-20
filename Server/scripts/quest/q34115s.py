# id 34115 ([Vanishing Journey] Lying in Repose), field 450001200
sm.setSpeakerID(3003127) # Rino
sm.sendNext("#h0#, you're awake! It's a good thing we landed on this soft sand...")
sm.sendSay("Ugh, my ankle... I-it's nothing. Don't worry about me. Just worry about escaping this cave.")
sm.setParam(2)
sm.sendSay("#b(Kao's actions weigh on your mind, but there are more important matters at hand.)#k")
sm.setParam(0)
sm.sendSay("This place is the last stop along the Vanishing Journey... The Cave of Repose. Traveling through this cave will lead you to what lies beyond. We're almost there.")
if sm.sendAskAccept("It's practically a maze in here but... I know a shortcut to the cave's exit. Now, follow me."):
    sm.completeQuestNoCheck(parentID)
