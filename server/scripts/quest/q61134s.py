# id 61134 (Final Defensive Line), field 610050000
sm.setSpeakerID(9201428) # Angel
sm.sendNext("#i3800858#\r\nHere's your first mission, recruit. It's a search and rescue of a scout we sent to eastern Blackgate.")
sm.setParam(2)
sm.sendSay("You sure you want to send a recruit to save a recruit?")
sm.setParam(0)
sm.sendSay("#i3800859#\r\nDon't question your orders! We don't have the time. Our best officers need to hold the main defense lines.")
res = sm.sendAskYesNo("But I'm sure you can slip into the central area and find our man!")
sm.setParam(2)
sm.sendNext("Okay, I get it.\r\nMaybe through the outskirts...")
sm.startQuest(parentID)
