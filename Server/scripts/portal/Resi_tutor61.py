sm.setSpeakerID(2159008)
sm.sendNext("Little rats. I say, how DARE you try to escape this place?")

sm.setPlayerAsSpeaker()
sm.sendSay("Shoot, we were spotted!")

sm.setSpeakerID(2159008)
sm.sendSay("Now, now, children. Don't make this harder than it needs to be. Just walk towards me, nice and easy... Wait, you're not one of the test subjects. You're one of the townspeople, aren't you?")

sm.setPlayerAsSpeaker()
sm.sendSay("That's right. I'm a resident of Edelstein, not a test subject. You can't boss ME around.")

sm.setSpeakerID(2159008)
sm.sendSay("Oh my, oh my. I told them to make sure the townspeople kept their kids away from the mines... Alas, it's too late now. I can't allow you to tell anyone about this laboratory, so I guess you'll just have to stay here and...help with the experiments. *snicker*")

sm.setPlayerAsSpeaker()
sm.sendSay("Hmph. Big words, but let's see if you can catch me first.")

sm.setSpeakerID(2159008)
sm.sendSay("Why, you insolent, little-- Ahem, ahem, ahem. Your words don't matter. Time for me to pull out the big guns. I do hope you're ready. If not, you will suffer.")

sm.getChr().heal(-sm.getChr().getHP()/2)
sm.setPlayerAsSpeaker()
sm.sendSay("#b(Oh no! Schiller's attack HALVED your HP! He's tougher than you anticipated.)#k")

sm.setSpeakerID(2159008)
sm.sendSay("I say, got any more big words, kiddo? I'll make sure Gelimer performs some especially atrocious experiments on you. But I'll be nice if you come with me quiet-like.")

sm.setSpeakerID(2159010)
sm.sendSay("Hold it right there!")

sm.warp(931000021, 1)