# [Silent Crusade] A Superior Hunter

BASTILLE = 9073003
CRUSADER_COIN = 4310029

sm.setSpeakerID(BASTILLE)
sm.sendNext("I've been waiting! Did you meet the so-called 'legend'? Tell me about him.")

sm.setPlayerAsSpeaker()
sm.sendSay("There's not much to say. He keeps to himself.")

sm.setSpeakerID(BASTILLE)
sm.sendSay("Too bad. I know next to nothing about the guy. Anyway, nice work, #h0#. You're now a #bRank S Hunter#k.")

sm.sendSay("I only know of three people who have that title. "
           "Be proud of yourself. Oh, and here's a little something from me.")

if not sm.canHold(CRUSADER_COIN):
    sm.sendSayOkay("Please make some space in your inventory.")
    sm.dispose()

sm.completeQuest(parentID)
sm.giveItem(CRUSADER_COIN, 100)
sm.showFieldEffect("Map/Effect.img/crossHunter/chapter/end4")
sm.sendNext("I'll see you the next time something pops up. Until then, take care.")
