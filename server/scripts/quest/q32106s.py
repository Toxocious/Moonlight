# id 32106 ([Ellinel Fairy Academy] Ivana's Misunderstanding), field 101072000
sm.setSpeakerID(1500001) # Headmistress Ivana
sm.setParam(4)
sm.setSpeakerID(1500001) # Headmistress Ivana
sm.sendNext("You're still here. Is there more to discuss?")
sm.setInnerOverrideSpeakerTemplateID(1500002) # Faculty Head Kalayan
sm.sendSay("You cannot trust this outsider, Headmistress! The human will only feed us lies and lame excuses. ")
sm.setPlayerAsSpeaker()
sm.sendSay("#bI thought you were a wise and rational people. We should analyze the facts before we come to any kind of judgment. #k")
sm.setInnerOverrideSpeakerTemplateID(1500002) # Faculty Head Kalayan
sm.sendNext("Five children vanished into thin air at once! What other facts do you need? This one kidnapped them, end of story!")
sm.setPlayerAsSpeaker()
sm.sendNext("#b So, you have proof that Cootie was the culprit?")
sm.setInnerOverrideSpeakerTemplateID(1500002) # Faculty Head Kalayan
sm.sendNext("The on you call Cootie has been chased off of these grounds a number of times, but he continues to return and defy our wishes. He has been conducting secret experiments in our forest!")
sm.sendNext("He's been planning this! It's the perfect crime. He comes to scout the area for weeks before he finally steals the children from underneath our very noses! He knew we had a number of staffers going out on vacation, and I caught him loitering around the scene of the crime afterward. He MUST be guilty!")
sm.setPlayerAsSpeaker()
sm.sendNext("#b(Could Cootie really have planned the kidnapping of five children? He's so small!)")
sm.setInnerOverrideSpeakerTemplateID(1500001) # Headmistress Ivana
sm.sendNext("Your desire is to find the most rational explanation. I present to you that our primes suspect IS the omst rational explanation. We must interrogate him.")
sm.setPlayerAsSpeaker()
sm.sendNext("#b(They're way too upset to see anybody except Cootie as a suspect. Better talk to him...)")
sm.startQuest(parentID)