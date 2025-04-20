sm.setSpeakerID(1013000)
sm.sendNext("I knew it! I knew we were connected, master! When you get stronger, I get stronger, too. And when I get stronger, you can use my strength! That's our pact. I knew I picked a good master!")

sm.setPlayerAsSpeaker()
sm.sendSay("#bI see. How did we end up in this pact anyway?")

sm.setSpeakerID(1013000)
sm.sendSay("I don't know. I was just an egg. I can't really remember...though I faintly recall you, master, walking toward me in a foggy forest. I remember your surprise upon seeing me. And I was calling out to you in return.")

sm.setPlayerAsSpeaker()
sm.sendSay("#b#b(Wait! That sounds just like that one dream you had... Did the two of you meet in a dream? Is it possible that the giant Dragon you saw in that dream was...#p1013000#?)")

sm.setSpeakerID(1013000)
sm.sendSay("Master, you and I are one in spirit. I knew it the moment I saw you. That's why I wanted to make the pact with you. No one else. You had to pay the price I set, of course.")

sm.setPlayerAsSpeaker()
sm.sendSay("#bI paid a price?")

sm.setSpeakerID(1013000)
sm.sendSay("Don't you remember? When you recognized me and touched me? That was the one condition I set. The moment you touched my egg, you and I became one in spirit.")

sm.setPlayerAsSpeaker()
sm.sendSay("#bOne in...spirit?")

sm.setSpeakerID(1013000)
sm.sendSay("Yes! The Spirit Pact! You and I have seperate bodies, but we share one spirit. That's why you get stronger when I get stronger, and vice versa! Awesome, right? At least, I think so.")

sm.setPlayerAsSpeaker()
sm.sendSay("#bI have no idea what you're talking about, but it sounds like a pretty big deal.")

sm.setSpeakerID(1013000)
sm.sendSay("Of course it's a big deal, silly master! You never have to worry about monsters again. You have me to protect you now! Go ahead and test me. In fact, let's go right now!")

sm.setPlayerAsSpeaker()
sm.sendSay("#bBut it's peaceful here. There are no dangerous monsters around.")

sm.setSpeakerID(1013000)
sm.sendSay("WHAT?! That's no fun! Don't you like adventuring, master? Fighting monsters on behalf of your people, defeating evil, rescuing the innocent, and all that? You're not into that kind of thing?")

sm.setPlayerAsSpeaker()
sm.sendSay("#bIt's not part of my five year plan. I'm just kidding, but seriously, I'm a farmer's kid...")

sm.setSpeakerID(1013000)
if sm.sendAskAccept("Bah, well let me tell you this. It's impossible for a Dragon Master to live a peaceful life. I'll have plenty of chances to prove my skills. Trust me, our life will be one big adventure. Promise me that you'll stick with me, okay?\r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 810 exp"):
    if not sm.hasQuestCompleted(parentID):
        sm.giveExp(810)
        sm.startQuest(parentID)
        sm.completeQuest(parentID)
    sm.removeEscapeButton()
    sm.sendNext("Hehehe, alrighty then, master. Let's get to it!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("#b(You're a bit confused, but you are now traveling with Mir the Dragon. Perhaps you'll go on an adventure together, like he said.)")
    sm.sendSay("#b#b(You still have an errand to run. Your dad needs to talk to you, so go and see him now.)")
    sm.dispose()
else:
    sm.sendNext("Uh, you're kidding me, right? Tell me your finger slipped! Go ahead and accept the quest.")
    sm.dispose()