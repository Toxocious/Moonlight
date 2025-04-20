# [Silent Crusade] Arkarium, the Guardian of Time

CROW = 9073002
BASTILLE = 9073003

sm.setSpeakerID(CROW)
sm.sendNext("I think Arkarium's back! Someone saw him at the Temple of Time, though just for a moment...")

sm.setPlayerAsSpeaker()
sm.sendSay("Who's Arkarium?")

sm.setSpeakerID(BASTILLE)
sm.sendSay("You don't know THE Arkarium? He was one of the Black Mage's Commanders. After the Black Mage was sealed, he disappeared.")

sm.setPlayerAsSpeaker()
sm.sendSay("If one of the Black Mage's right-hand guys is back, does that mean the Black Mage is back, too?")

sm.setSpeakerID(BASTILLE)
sm.sendSay("I'm not sure. But something is amiss in Maple World, that much is certain. "
           "The return of Arkarium, the appearance of the Mystic Gates... Evil forces are in motion.")

sm.completeQuest(parentID)
sm.showFieldEffect("Map/Effect.img/crossHunter/chapter/end2")
