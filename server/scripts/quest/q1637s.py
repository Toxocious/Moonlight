# [Silent Cruade] Progress Report

STARLING = 9120221

sm.setSpeakerID(STARLING)
sm.sendNext("The Mystic Gates are connected to the Black Mage somehow. I'm sure of it! "
            "The gates seem to control nearby monsters and drain the life right out of anyone who gets near them. "
            "But why? At any rate, I need to tell Bastille about all this. Actually, why don't you tell him for me? "
            "I'll follow you when I'm done here.")

response = sm.sendAskYesNo("All right! I'll send you straight to Edelstein!")

if response:
    sm.warp(310000000)
    sm.startQuest(parentID)

else:
    sm.sendSayOkay("Let me know when you're ready to be warped.")