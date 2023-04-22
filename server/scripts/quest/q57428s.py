
# Quest: Scaling the El Nath Mountains (57428)
# Author: Tiger

NEINHEART = 1101002

if sm.hasQuestCompleted(57427) and chr.getLevel() >= 24: # Audience with the Empress
    sm.removeEscapeButton()

    sm.setSpeakerID(NEINHEART)
    sm.setBoxChat()
    sm.sendNext("It's been a while, Kanna. I'm sorry to burden you like this, but there is an urgent matter we must discuss.")
    sm.sendSay("You've no doubt heard of the continent of Orbis. If not, just look at a map. Traces of Oda's Army have been popping up near a town called El Nath. The enemies in question all match the descriptions you provided, so I'm sure they belong to Oda's Army.")
    sm.sendSay("It seems your mysterious foe's schemes are beginning to spread across Maple World.")
    if sm.sendAskAccept("Please hurry to El Nath.  In my experience, it's important to quell these problems as early as possible. Alcaster in El Nath will provide further details."):
        sm.startQuest(57428)  #  Scaling the El Nath Mountains
        # TODO: jav to -> Empress' Road : Sky Ferry  (130000210)
