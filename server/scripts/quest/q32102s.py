# [Ellinel Fairy Academy] You Can Do It

FANZY = 1500010 # NPC ID
YOU_CAN_DO_IT = 32102 # QUEST ID

sm.setSpeakerID(FANZY)
sm.sendNext("Are you asking where we are? Did you follow me without knowing where I was going? This is the forest path to the #b Ellinel Fairy Academy#k.")

sm.setPlayerAsSpeaker()
sm.sendSay("Ellinel Fairy Academy?")

sm.setSpeakerID(FANZY)
sm.sendSay("Yes. #b Ellinel#k is an academy where fairy children learn magic.")

sm.setPlayerAsSpeaker()
sm.sendSay("But why is it hidden so deep within the forest?")

sm.setSpeakerID(FANZY)
sm.sendSay("Did you know that #bEllinia#k used to be a fairy town? Several hundred years ago, after a war with the Black Mage, humans came in and reclaimed the town and it became the #bEllinia#k we know now.")

sm.setPlayerAsSpeaker()
sm.sendSay("Then that must mean that fairies live outside of Ellinia, too.")

sm.setSpeakerID(FANZY)
sm.sendSay("Some fairies are okay with humans, but others very much are not. It's the same in the #bEllinel Fairy Academy#k. They don't want to mix with humans, and so they disappeared into the forest. That's why the school is far across the lake.")

sm.setPlayerAsSpeaker()
sm.sendSay("You think Cootie got captured by human-hating fairies?")

sm.setSpeakerID(FANZY)
response = sm.sendAskAccept("Most likely. I know I thought about using him as a scratching post a few times. Master #bGrendel#k and I tried to befriend the fairies, but they just weren't listening. I think we should use more... forceful methods.\r\n#b #h ##k, let me ask... are you a good swimmer?")

if response:
    sm.sendNext("Why don't you go for a swim! Show us how brave you are, meow...\r\n#b (Cross the lake to the right.)#k'")
    sm.startQuestNoCheck(YOU_CAN_DO_IT)
sm.dispose()
