# [Theme Dungeon] Ellinel Fairy Academy

FANZY = 1040002 # NPC ID
FAIRYNAPPERS = 32101 # QUEST ID

sm.setSpeakerID(FANZY)
sm.sendNext("Are you the one I invited to help out with the ruckus at the Ellinel Fairy Academy?")

sm.setPlayerAsSpeaker()
sm.sendSay("Um, of course?")

sm.setSpeakerID(FANZY)
sm.sendNext("You don't look as strong as I'd hoped. But, you're famous, so I'll leave it to you.")

sm.startQuestNoCheck(FAIRYNAPPERS)
sm.completeQuest(parentID)
sm.dispose()