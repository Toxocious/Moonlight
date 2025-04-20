# [Stone Colusses] It Ain't Natural

CHIEF_TATOMO = 2081000
GUWARU = 2210011

sm.setSpeakerID(CHIEF_TATOMO)
sm.sendNext("Minar Forest has always been known as a place of strange occurrences, but this one takes the proverbial peach pie. "
            "A mountain that moves... amazing.")

sm.sendSay("I don't mind telling you, I was as shocked as you are. "
            "My bread was practically standing on end. "
            "But it's the truth, and that's a fact.")

sm.sendSay("When times like these come around, I pray to the great spirit Guwaru for guidance. "
           "Sure, sure maybe the Black Mage had control over him for a while, but he's rock-solid fella now, hehe")

response = sm.sendAskYesNo("We halflingers are kin to the sky, wind and forest. We know a few tricks to contact the great spirits. if you'd like, I could arrange a meeting right now.")

if response:
    sm.sendNext("All right then. Clear your noodle, focus on my topknot, and let's contact a spirit! Listen carefully...")

    sm.setSpeakerID(GUWARU)
    sm.flipDialogue()
    sm.sendSay("This is my fault. I could never have known a being like this would awaken...\r\n"
               "#b(You hear a strange and rumbling voice.)")

    sm.flipDialogue()
    sm.sendSay("I should have seen it coming. Long ago, when the Black Mage completed his betrayal, my powers were stolen from me. "
               "Everything began then. I had to recover from the blow that I had been dealt, and I neglected my duties as guardian of the forest.")

    sm.flipDialogue()
    sm.sendSay("This bizarre scenario is my sole responsibility, but I am no more use than an errant fly with my current powers. "
               "Perhaps a mortal touch would be more appropriate. Please meet this colossus in my place.")

    sm.flipDialogue()
    sm.sendSay("You will need some of my power to understand the great beast... "
               "The transfer should not hurt, so long as it doesn't make you grow roots.\r\n"
               "#b(Guwaru's light washes over you. you feel different, more... earthy.)")

    sm.flipDialogue()
    sm.sendSay("I will contact you again when I need you. Until then...")
    sm.startQuest(parentID)
else:
    sm.sendSayOkay("Wot? You don't want to contact a higher being?")