sm.lockInGameUI(True)
sm.curNodeEventEnd(True)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()

sm.sendNext("Hmm...")
sm.sendSay("(Where am I? I don't recognize this place... It's not the cave I was in before... Ugh, everything hurts.)")

sm.forcedInput(1)
sm.sendDelay(600)

sm.forcedInput(0)
sm.sendNext("(This looks like a Treatment Room... Where am I? What happened to me?)")
sm.sendSay("(I must remember what happened...)")
sm.sendSay("(The Black Mage broke his promise and destroyed the southern part of Ossyria, where my mother and Damien were. He destroyed my home...)")
sm.sendSay("(My locket! Where is my locket?)")
sm.sendSay("(Did I lose it during the fight? That was all I have left from my family... No...)")

sm.forcedInput(2)
sm.sendDelay(600)

sm.forcedInput(0)
sm.sendNext("(I went to the Temple of Time to take revenge on the Black Mage... On the way, I let #p2151009# go, to get away from the Commanders. #p2159309# tried to stop me, but I was determined... Say, I wonder how the Heroes did?)")
sm.sendSay("(The Black Mage was too powerful for me. I knew he would be, but I thought I could do at least a little damage. All I managed was breaking his barrier and tearing his robe... How pathetic.)")
sm.sendSay("(But... How did I survive? The Black Mage would never have spared me. Did someone else get involved? The Heroes...?)")
sm.sendSay("(Ugh, now I have a headache. I don't even know where I am now. This place is so strange. And yet...does this mean Maple World wasn't destroyed?)")

sm.forcedInput(1)
sm.sendDelay(600)

sm.forcedInput(0)
sm.sendNext("(I should check on myself. I'm going to need my Demon Fury no matter what... But how much is left?)")

sm.showBalloonMsg("Effect/Direction6.img/effect/tuto/balloonMsg0/13", 2000)
sm.sendDelay(1500)

sm.sendNext("(No! My Demon Aegis is so weak...I've never seen it this bad. Almost all of my power and abilities are gone. How could this happen?)")
sm.sendSay("(I can't hardly fight like this. That man with the hat... He doesn't look dangerous, but I can't trust anyone.)")
sm.sendSay("(It's going to take time to build my power back up, and sitting here won't accomplish anything. I need to go.)")

sm.forcedInput(1)