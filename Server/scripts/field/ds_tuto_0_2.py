# Dialog Constants
MASTEMA = 2159307
sm.removeEscapeButton()
sm.setSpeakerID(MASTEMA)

sm.sendNext("...Are you okay? You seem different... Yeah, you are. You used to scold me when I asked you such things, but now... Hey, you don't look so good. Did something happen? Are you hurt?")

sm.setPlayerAsSpeaker()
sm.sendSay("...Tell me, #p2151009#. Who do you serve? Is it me, or the Black Mage?")

sm.setSpeakerID(MASTEMA)
sm.sendSay("W-what? Why would you ask me such a-")

sm.setPlayerAsSpeaker()
sm.sendSay("Answer me!")

sm.setSpeakerID(MASTEMA)
sm.sendSay("Well... Um... I am loyal to the Black Mage, of course... But the day you saved my life, I pledged myself to you. I go where you go. Does, um, does that answer your question?")

sm.setPlayerAsSpeaker()
sm.sendSay("Yes. I have a favor to ask of you, then.")
sm.sendSay("Give this letter to the #rHeroes#k.")

sm.setSpeakerID(MASTEMA)
sm.sendSay("Do what?! Why... What are you thinking? After everything that's happened, you want to make things worse? If anyone finds out you're trying to contact the Heroes, you're finished as a Commander!")

sm.setPlayerAsSpeaker()
sm.sendSay("I am already finished as a Commander.")

sm.setSpeakerID(MASTEMA)
sm.sendSay("Wait... Are you betraying the Black Mage? But, you're the most loyal of his Commanders! You practically GAVE him the Temple of Time! We have everything... Why are you doing this?")

sm.setPlayerAsSpeaker()
sm.sendSay("There's no time to explain! If you are truly loyal to me, you will do as I ask. If not...")

sm.setSpeakerID(MASTEMA)
sm.sendSay("No! No... I'll do it. I'm just...worried about you.")

sm.setPlayerAsSpeaker()
sm.sendSay("......")

sm.setSpeakerID(MASTEMA)
sm.sendSay("I mean, what about your family? Won't they be in dan-")

sm.setPlayerAsSpeaker()
sm.sendSay("SILENCE! Do not say another word about my family...")

sm.setSpeakerID(MASTEMA)
sm.sendSay("...What? Did... Did something happen to them already...?")

sm.setPlayerAsSpeaker()
sm.sendSay("......")

sm.setSpeakerID(MASTEMA)
sm.sendSay("I see... You've always been the quiet type, but silence sometimes speaks for itself.")
sm.sendSay("Very well. I swear to you, on my very life, that I will deliver this letter to the Heroes.")

sm.setPlayerAsSpeaker()
sm.sendSay("Thank you. I am sorry to ask such a thing of you, #p2151009#...")

sm.setSpeakerID(MASTEMA)
sm.sendSay("Don't be sorry. I owe you my life, after all. In fact, I...I really appreciate your trust.")
sm.sendSay("Okay...I'm going. Whatever it is you're doing...good luck.")

sm.showNpcSpecialActionByTemplateId(MASTEMA, "teleportation", 0)
sm.sendDelay(720)

sm.removeNpc(MASTEMA)
sm.setPlayerAsSpeaker()
sm.sendNext("(Your loyalty means so much to me, #p2151009#. Thank you.)")

sm.forcedInput(2)
sm.curNodeEventEnd(True)