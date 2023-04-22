VON_LEON = 2159310
ARKARIUM = 2159308
ORCHID = 2159339

sm.lockInGameUI(True)
sm.forcedInput(1)
sm.sendDelay(30)

sm.forcedInput(0)
sm.removeEscapeButton()
sm.setSpeakerID(VON_LEON)
sm.sendNext("Are all the Commanders here, aside from those away on missions? Good. Let's begin the meeting.")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Until the mighty Black Mage finishes his plan, we must not relax for even a moment! We are still vulnerable to attack, and must remain on guard while the Black Mage is distracted. Now, #h0#, I heard you uncovered some interesting information.")

sm.setPlayerAsSpeaker()
sm.sendSay("Yes...I have discovered a resistance group has formed in secret, and is building a force to move against us.")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Resistance? Ha! There's no one left in this world that can resist us. I've even heard some of the rabble calling them #rHeroes#k. Isn't that precious?")

sm.setSpeakerID(ORCHID)
sm.sendSay("Of course, I'm a little excited to see them scramble around in their last-ditch panic. Might make for some entertaining fights. They certainly didn't put up much resistance when we took Ereve. Or when I eliminated the Castellan.")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("The battle at Ereve was easy because of the Black Mage's involvement, not because of your power, #p2159339#. Watch your tongue.")

sm.setSpeakerID(ORCHID)
sm.sendSay("Well... Since the Black Mage took care of everything, I didn't have to use my full power. So, there.")

sm.setPlayerAsSpeaker()
sm.sendSay("Lotus seems very busy... What are you doing here, Orchid? Are you not working with Lotus?")

sm.setSpeakerID(ORCHID)
sm.sendSay("Lotus is the one who's too busy for me! I was GOING to go back my twin up...you don't have to bug me about it. You guys are too uptight, anyway.")

sm.setSpeakerID(VON_LEON)
sm.sendSay("...This meeting is not going anywhere...")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Have you ever noticed that when #p2159339# opens her mouth, our meetings grind to a halt? Funny, that. As for the Heroes, I'm sure #h0# has a plan to deal with them.")
sm.sendSay("Since you brought down the Goddess of Time, I'm sure these pathetic 'Heroes' will be no match for you.")

sm.setPlayerAsSpeaker()
sm.sendSay("...They will not be so easy to eliminate. Unlike most foes, the Heroes fight for others, not themselves. They are special, because they have chosen to protect the world, rather than struggle desperately. That makes them dangerous. And I'll remind you, I only stunned the Goddess. The Black Mage was the one to defeat her, of course.")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Goodness, how modest of you! That must be why you're the Black Mage's favorite. You really just put the rest of us to shame, don't you? My, my, my...")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg0/10", 2000)
sm.sendDelay(1500)

sm.setSpeakerID(VON_LEON)
sm.sendNext("#p2159309#, I've heard enough from you. #h0# stunned the Goddess of Time, allowing for our victory. THAT was the turning point in the battle. Accept it.")

sm.sendSay("Besides, you were credited for blinding the Goddess. What more do you want, #p2159309#?")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Oh, I don't want anything. I'm just...making observations. Shouldn't we move on with the meeting? The Heroes will be taken care of, but what of the remaining resistance group?")

sm.setSpeakerID(VON_LEON)
sm.sendSay("As commanded, they have been completely eliminated.")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Oh, I see!")

sm.setSpeakerID(ORCHID)
sm.sendSay("I have a question, actually. Why has the Black Mage changed our orders? I mean, if we destroy everything, who will be left to rule over...?")

sm.setPlayerAsSpeaker()
sm.sendSay("Destroy everything? Did the Black Mage order such a thing? I received no such order.")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Oh, yes! I forgot. You seemed so tired from your epic battle against the Goddess, I didn't even mention the new orders to you.")

sm.sendSay("You see, our great leader, the Black Mage, ordered all of us, except you, to eliminate everything. And I mean, EVERYTHING!")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg1/18", 2000)
sm.sendDelay(1500)

sm.setSpeakerID(VON_LEON)
sm.sendNext("Indeed. For example, I saw Leafre burned to cinders. Nothing remained...")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("The Black Mage told us to show the world the price of resistance, so we began eliminating areas suspected of treachery. I think we did rather well.")

sm.setSpeakerID(VON_LEON)
sm.sendSay("Yes... There are only a few remaining dragon servants left.")

sm.setPlayerAsSpeaker()
sm.sendSay("Wait, wait. Did the Black Mage not promise that he would not attack the Southern Region? Which parts were destroyed?")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Which parts? Ha! All of them, of course! We took our orders quite seriously. Why...is something bothering you?")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg0/11", 2000)
sm.sendDelay(1500)

sm.setPlayerAsSpeaker()
sm.sendNext("...Please excuse me. There is a matter I must attend to.")

sm.setSpeakerID(ARKARIUM)
sm.sendSay("Hold it right there! No matter how favored you are by the Black Mage, you follow orders. No one has dismissed you yet. Sit down. THAT is an order.")

sm.forcedInput(2)