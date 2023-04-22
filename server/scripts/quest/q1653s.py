# [Silent Crusade] Special Delivery

STARLING = 9120221
HIDEOUT = 931050500

sm.setPlayerAsSpeaker()
sm.sendNext("(You come across a letter, but you're not sure where it came from.)\r\n"
            "Hm, this symbol... This letter must be from the Silent Crusade. Might as well open it.")

sm.setSpeakerID(STARLING)
sm.sendSay("If you're reading this. I need you to return to our hideout as soon as you can. I'll fill you in when you get here.\r\n\r\n"
           "-Starling")

sm.sendSay("P.S. - I've attached transportation to the hideout. Don't lose it, or you'll have to return the long way.")

sm.startQuest(parentID)
sm.showFieldEffect("Map/Effect.img/crossHunter/chapter/start3")
if not sm.getFieldID() == HIDEOUT:
    sm.warp(HIDEOUT)