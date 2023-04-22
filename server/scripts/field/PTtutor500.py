CYGNUS = 1402100
NEINHEART = 1402101
MIHILE = 1402102
OZ = 1402103
IRENA = 1402104
ECKHART = 1402105
HAWKEYE = 1402106
KIRIGA = 1402200
KIDIS = 1402201
KIMAN = 1402202
KIBELU = 1402203
HILLA = 1402400
HILLA_GUARD = 1402401

sm.showFieldEffect("phantom/mapname3", 0)
sm.lockInGameUI(True)
sm.forcedInput(0)
sm.sendDelay(2000)

sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("(Looks like I'm not too late. Everyone's here, but nothing has started yet. I'll just grab one of these empty seats...)")

sm.forcedInput(2)
sm.sendDelay(1500)

sm.forcedInput(0)
sm.sendDelay(500)

sm.sendNext("(Cygnus and her knights look very serious. Can't say that I blame them.)")
sm.sendSay("(The senators don't seem so thrilled either. I wonder what they're thinking? Maybe I can get closer...)")

sm.sendDelay(2000)

sm.forcedInput(0)

sm.setSpeakerID(KIRIGA)
sm.sendNext("Could it be true? Could Cygnus have been a fake this whole time?")

sm.setSpeakerID(KIDIS)
sm.sendSay("Are you listening to yourself? How could that even be possible? Cygnus is the empress now and she'll be the empress tomorrow!")

sm.setSpeakerID(KIBELU)
sm.sendSay("I'm not arguing that she's a total impostor, but if there really is someone with the treasure of Ereve...")

sm.setSpeakerID(KIMAN)
sm.sendSay("Aria was supposed to have valued that gem above all other things. She was going to pass it on to the rightful heir.")

sm.setSpeakerID(KIRIGA)
sm.sendSay("If that treasure proves she is the real empress, I mean if someone other than Cygnus even belongs to Aria's bloodline... everything we've worked for could be in jeopardy.")

sm.setSpeakerID(KIBELU)
sm.sendSay("I won't betray Cygnus after all she's done for Ereve, but I can't ignore the legitimacy of this woman's claims either! ")

sm.setSpeakerID(KIMAN)
sm.sendSay("The Maple World alliance was just about to form a unified front. The only reason most of these people are here is because they trusted Cygnus. The alliance may fall to pieces if someone else steps in.")

sm.setSpeakerID(KIDIS)
sm.sendSay("We could stand here and speculate all day. I think it is time we let this accuser speak for herself.")
sm.sendSay("Shhh... She's here.")

sm.forcedInput(0)
sm.sendDelay(500)

sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("(The director of this convoluted play finally arrives.)")

sm.showFieldEffect("phantom/hillah", 0)
sm.playSound("Magatia/alceCircle", 100)
sm.sendDelay(1200)

sm.spawnNpc(HILLA, -131, -7)
sm.showNpcSpecialActionByTemplateId(HILLA, "summon", 0)

sm.spawnNpc(HILLA_GUARD, -209, -7)
sm.showNpcSpecialActionByTemplateId(HILLA_GUARD, "summon", 0)

sm.spawnNpc(HILLA_GUARD, -282, -7)
sm.showNpcSpecialActionByTemplateId(HILLA_GUARD, "summon", 0)

sm.spawnNpc(HILLA_GUARD, -59, -7)
sm.showNpcSpecialActionByTemplateId(HILLA_GUARD, "summon", 0)

sm.forcedInput(0)
sm.sendDelay(4500)

sm.setSpeakerID(HILLA)
sm.sendNext("Thank you all for coming! I am Hilla, and I am here to tell you that I am the true heir to the Empress's throne. ")

sm.setSpeakerID(CYGNUS)
sm.sendSay("...")

sm.setSpeakerID(NEINHEART)
sm.sendSay("We're here to prove that you're lying.")

sm.setSpeakerID(HILLA)
sm.sendSay("So you would like to think, but I'd like to talk about an old tale that the people of Ereve seem to have conveniently forgotten. A tale of Aria, the empress during the time of the Black Mage's reign...")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("(Aria...)")

sm.setSpeakerID(HILLA)
sm.sendSay("As you all must know, there was no historical record left over after the Black Mage rose to power, but there WAS a persistent story of a gem that Aria clung to. The treasure called Skaia.")
sm.sendSay("Skaia, Aria's lost treasure. It's said to have been passed down from empress to empress to protect them! It holds many wondrous powers!")

sm.setSpeakerID(IRENA)
sm.sendSay("The stories about Aria's possession of Skaia are not in question, but there are no records of what powers the jewel actually possessed...")

sm.setSpeakerID(HILLA)
sm.sendSay("I stand before you with proof of my lineage and you argue semantics? I have the jewel, not Cygnus!")
sm.sendSay("When the Black Mage's army decimated Ereve, the Skaia was thought lost. I'm sure that's the old story you've all heard. But do you all really think such an important treasure would be written off as a loss? Do you think our forefathers would have let it linger in some tomb?")
sm.sendSay("That is lunacy! The Skaia was protected from the Black Mage, quietly passed down for hundreds of years until I could reveal my birthright!")

sm.setSpeakerID(ECKHART)
sm.sendSay("So that's your argument?")

sm.setSpeakerID(HILLA)
sm.sendSay("That is the truth.")

sm.setSpeakerID(OZ)
sm.sendSay("How can you prove that the Skaia you have is real? It could be a fake.")

sm.setSpeakerID(HILLA)
sm.sendSay("A valid question, but ultimately foolish. The name Skaia is well known, but very few have actually seen it. In fact, the only people in Maple World that would have even seen its picture are here today. That means that all of YOU are the proof that my Skaia is real!")
sm.sendSay("Do you not recognize the Skaia in my hand? Is it not the jewel you have all seen before?")

sm.setSpeakerID(HAWKEYE)
sm.sendSay("Listen to what you're saying! Jewels can be forged and reproduced. There is no way we can be certain that the one you have is the real thing.")

sm.setSpeakerID(HILLA)
sm.sendSay("I'm sorry, Sir Hawkeye, but were you alive hundreds of years ago? No, Your opinion on the validity of this gem is of no importance.")
sm.sendSay("Besides, we have not yet gotten to the real argument. I ask you all, why is the Lady Cygnus so frail? If she is indeed the true heir, she would not be overpowered by Shinsoo's strength. Lady Cygnus, you yourself must know that you were not meant to wield the power you've stolen...")

sm.setSpeakerID(MIHILE)
sm.sendSay("Such insolence!")

sm.setSpeakerID(HILLA)
sm.sendSay("Oh... was I being insolent? Is speaking the truth now a sign of betrayal?")
sm.sendSay("Think on my words and decide for yourselves! Isn't that your role, Cygnus? To act on what is best for the people?")

sm.setSpeakerID(CYGNUS)
sm.sendSay("She is right. I am no one special and I am unable to fully absorb Shinsoo's power. I don't know why, but I was born like this. ")
sm.sendSay("If this woman questions my validity as your empress, we must at least allow her to discuss it, or we will be no better than the Black Mage...")

sm.setSpeakerID(NEINHEART)
sm.sendSay("Lady Cygnus!")

sm.setSpeakerID(CYGNUS)
sm.sendSay("I have to do what is right! I've asked the world to fight for me while I sit here under the protection of more people I've asked to fight for me. If I've done that without any real authority or qualification...")
sm.sendSay("Then I am no better than a tyrant.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("(Her voice is shaking like a leaf, but her eyes are firm. She really is Aria's niece... and she seems to be quite popular.)")

sm.setSpeakerID(HILLA)
sm.sendSay("I believe we've dragged this on long enough! Let's see who truly carries the bloodline of the empress. It is said that Skaia will shine in the hands of an Empress. Do you care to test your mettle, little Cygnus?")
sm.sendSay("It shines in my hands. Will it shine in yours?")

sm.showFieldEffect("phantom/skaia", 0)
sm.playSound("phantom/skaia", 100)
sm.forcedInput(0)
sm.sendDelay(7500)

sm.setSpeakerID(CYGNUS)
sm.sendNext("Ah...")

sm.setSpeakerID(HILLA)
sm.sendSay("Not even a flicker. What more do you need?")

sm.setSpeakerID(CYGNUS)
sm.sendSay("...")

sm.setSpeakerID(MIHILE)
sm.sendSay("It's too early for conclusions, Empress.")

sm.setSpeakerID(HAWKEYE)
sm.sendSay("That's right. We still don't even know if that stone is real.")

sm.setSpeakerID(OZ)
sm.sendSay("R-right! Even I can make light with magic!")

sm.setSpeakerID(IRENA)
sm.sendSay("When Shinsoo returns, she'll reveal the truth. You absolutely cannot believe that woman's words.")

sm.setSpeakerID(ECKHART)
sm.sendSay("If you falter, the Cygnus Knights falter as well. Stay strong.")

sm.setSpeakerID(NEINHEART)
sm.sendSay("Your alliance is the foundation for a new era in Maple World. This could all be a scheme to place doubt on your position. We cannot listen to her until she presents solid proof.")

sm.setSpeakerID(CYGNUS)
sm.sendSay("Everyone...")

sm.setSpeakerID(HILLA)
sm.sendSay("Your hounds are trying desperately to ignore the truth.")

sm.setSpeakerID(CYGNUS)
sm.sendSay("I won't deny your hard work, Cygnus. You have been surprisingly wise for a young girl, but I urge you to make the right decision.")
sm.sendSay("Acknowledge me as the real empress and step away from this charade before it's too late.")

sm.setSpeakerID(HILLA)
sm.sendSay("Tell the alliance that they will follow me now.")
sm.sendSay("Of course, I'm not without sympathy to your situation. I'll give you some time to take all of this in. Do whatever you need to do to reconcile yourself with the truth.")

sm.setSpeakerID(CYGNUS)
sm.sendSay("When you are finished, you will find that the true Empress of Maple World is not Cygnus, but Hilla.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("(Gaston should be ready about now. Time to take the plunge!)")

sm.showFieldEffect("phantom/phantom", 0)
sm.forcedInput(0)
sm.sendDelay(1500)

sm.sendNext("Jumping ahead of ourselves, aren't we?")

sm.sendDelay(500)

sm.startQuestNoCheck(25001)
sm.lockInGameUI(False)
sm.removeNpc(HILLA)
sm.removeNpc(HILLA_GUARD)
sm.removeNpc(HILLA_GUARD)
sm.removeNpc(HILLA_GUARD)
sm.warpInstanceOut(150000000, 2)
sm.dispose()