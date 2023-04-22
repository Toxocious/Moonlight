# [Silent Crusade] Dark Energy Rising

STARLING = 9120221
CRANKY_FOREST = 240010200

sm.setSpeakerID(STARLING)
sm.sendNext("Long time no see. I heard about your work in Magatia. Crow was really impressed, and that impressed me. "
            "He's a hard one to please, but I'm sure I don't have to tell you that.")

response = sm.sendAskYesNo("Just like in Magatia, Mystic Gates have been popping up in Leafre as well. To make matters worse, dark energy is seeping out of them. "
                           "We need to handle this fast, and you're just the person to lead the charge. "
                           "Ready to begin?")

if response:
    sm.sendNext("The Mystic Gate near the Herb area is really making a mess of things. Head over to the #r#m"+ str(CRANKY_FOREST) +"##k and do something about it.")
    sm.showFieldEffect("Map/Effect.img/crossHunter/chapter/start2")
    sm.startQuest(parentID)

else:
    sm.sendSayOkay("Let me know when you are ready")