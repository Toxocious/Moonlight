# [Silent Crusade] A Veteran Hunter

BASTILLE = 9073003
KRAG = 9073005

sm.setSpeakerID(BASTILLE)
sm.sendNext("#p"+ str(KRAG) +"# probably mentioned something about a reward? Well, this is for you, #h0#, for investigating the Mystic Gates so effectively.")

sm.sendSay("You already received your Rank A hunter title from the UI, right? "
           "If not, open up the Silent Crusade UI and grab it. "
           "I've included a little something extra for you too...")

sm.sendSay("See you at your next mission. Take care.")

sm.completeQuest(parentID)
sm.showFieldEffect("Map/Effect.img/crossHunter/chapter/end3")