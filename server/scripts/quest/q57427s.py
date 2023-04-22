
# Quest: Audience with the Empress (57427)
# Author: Tiger

NEINHEART = 1101002

if sm.hasQuestCompleted(57421) and chr.getLevel() >= 23: # To Ereve
    sm.removeEscapeButton()

    sm.setSpeakerID(NEINHEART)
    sm.setBoxChat()
    sm.sendNext("I received Hersha's report. You helped out a great deal in Ellinia. For that, I thank you.")
    if sm.sendAskAccept("Hersha's report was a bit light on details, so I was hoping you could fill in the blanks."):
        sm.startQuest(57427)  #  Audience with the Empress

        sm.flipSpeaker()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setBoxChat()
        sm.sendNext("(You explained your monster fighting rampage in Ellinia, and that the Spirit Walkers of Oda's Army were involved in the incident.)")

        sm.setSpeakerID(NEINHEART)
        sm.setBoxChat()
        sm.sendSay("The plot thickens. One would expect the Black Wings to be behind something like this, but if what you say is true, we've got a new clan to worry about. Oda's Army, you say?")
        sm.sendSay("The Empress must hear about this. I'm sorry I didn't grant you an immediate audience. But think about it this way: you had time to gather evidence and create a compelling case.")
        sm.sendSay("The Empress is with Shinsoo to the right. Mind your manners.")
