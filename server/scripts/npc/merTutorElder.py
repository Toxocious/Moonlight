# Philius NPC (1033202) | Used for Mercedes storyline

if sm.getQRValue(24007) != "1":
    sm.setPlayerAsSpeaker()
    sm.sendNext("Elders! You're okay! But...but the village...!")

    sm.setSpeakerID(1033202)
    sm.sendSay("A fierce, frozen curse has fallen upon the town. Your Highness, I see it has fallen upon you as well.")

    sm.setSpeakerID(1033203)
    sm.sendSay("I sense it from you most of all! Is this the power of the Black Mage?!")

    sm.setSpeakerID(1033204)
    sm.sendSay("The children are already trapped in ice, and soon, the adults will follow them. It takes longer to freeze the stronger Elves, which is why we are still all right, but our time is limited...")

    sm.setPlayerAsSpeaker()
    sm.sendSay("This is my fault. We sealed the Black Mage, but he managed to #rcurse#k us anyway...")

    sm.setSpeakerID(1033203)
    sm.sendSay("So it is his doing?!")

    sm.setSpeakerID(1033204)
    sm.sendSay("I knew this was his doing...")

    sm.setSpeakerID(1033202)
    sm.sendSay("The Black Mage has cursed our sovereign, and the curse has spread to all Elves...")

    sm.setPlayerAsSpeaker()
    sm.sendSay("I should have been more careful. Please, I didn't mean for this to happen...")

    sm.setSpeakerID(1033202)
    sm.sendSay("What a fearful being, this Black Mage. Even form beyond the seal, he wields such power... It is a miracle we were able to seal him at all")

    sm.setSpeakerID(1033204)
    sm.sendSay("There was no way you could stop this, Your Majesty. Nobody could have.")

    sm.setSpeakerID(1033203)
    sm.sendSay("That's right! It's not your fault, My Liege! You sealed him! YOU'RE the hero!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("I shouldn't have fought the Black Mage in the first place! If I'd let him be, this wouldn't have happened to the Elves. I've failed my people!")

    sm.setSpeakerID(1033204)
    sm.sendSay("Don't say such things, Your Highness! Even if you'd let him be, the Black Mage would have come for us sooner or later.")

    sm.setSpeakerID(1033202)
    sm.sendSay("It's our fault. We are your council. We should have better prepared you to face the Black Mage.")

    sm.setSpeakerID(1033203)
    sm.sendSay("I'm supposed to be the Elder of War, but even I was too weak to join the fight. I'm the one who failed you, Your Highness...")

    sm.setPlayerAsSpeaker()
    sm.sendSay("No, this isn't your fault! I'm the one who decided to face the Black Mage. I don't regret fighting...I regret failling to protect my people.")

    sm.setSpeakerID(1033202)
    sm.sendSay("In that case, we all regret failing to do this, Your Majesty.")

    sm.setSpeakerID(1033204)
    sm.sendSay("This is not your burden alone. The decision to fight the Black Mage was the decision of the Elves, and so we will all share in the results, whatever they may be.")

    sm.setSpeakerID(1033203)
    sm.sendSay("No one blames you, Your Highness!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Everyone...")

    sm.setSpeakerID(1033202)
    sm.sendSay("Regardless of this wicked curse, we will survive. We will overcome this together.")

    sm.setSpeakerID(1033202)
    sm.sendSay("Long as Your Highness is safe, the hope for the Elves lives on.")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Is there a way?")

    sm.setSpeakerID(1033202)
    sm.sendSay("We can't stop the curse now. But we are the Elves. We may outlive it.")

    sm.setSpeakerID(1033202)
    sm.sendSay("Your Highness, we should seal Elluel before the curse can spread beyond the village. We cannot avoid it, but we can keep it from spreading beyone the Elves. #bWe Elves will all slumber here, undisturbed by the outside world#k.")

    sm.setSpeakerID(1033204)
    sm.sendSay("We don't know how long the curse will last, but time is on our side. Your Highness, we've nothing to worry about.")

    sm.setSpeakerID(1033203)
    sm.sendSay("Eventually we will awaken together, and the Black Mage will be a distant memory!")

    sm.setSpeakerID(1033202)
    sm.sendSay("Not even the curse of the Black Mage can last forever. In the end, we will be the victors.")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Yes! We will win!")

    sm.setSpeakerID(1033202)
    sm.sendSay("Of course we will. Ah... I'm growing weak. Your Highness, it is time to seal the village. It is the only way we can rest in peace.")

    sm.setSpeakerID(1033202)
    sm.sendSay("There are some things we should take care of first. I believe #p1033204# wants to speak with you.")
    sm.createQuestWithQRValue(24007, "1")
    # sm.startQuestNoCheck(24000)
