# [Silent Crusade] Stranger at the Gate

STARLING = 9120221

sm.setSpeakerID(STARLING)
sm.sendNext("What are the odds... I didn't expect to see you again.")

sm.setPlayerAsSpeaker()
sm.sendSay("(Isn't that the woman I rescued in the Kerning City Subway?) Hey, long time no see!")

sm.setSpeakerID(STARLING)
sm.sendSay("I came here to help out the Excavation Site, but it looks like I'm too late. Say, did you see a strange gate around here by any chance?")

sm.setPlayerAsSpeaker()
sm.sendSay("Yeah, I saw it when I was fighting the Commander Skeleton in the Prohibited Area. It gave me the creeps.")

sm.setSpeakerID(STARLING)
response = sm.sendAskYesNo("Really? I'd love to see it! Mind showing me where it is?")

if response:
    sm.sendNext("All right! To the Prohibited Area!")

    sm.sendNext("Let's move")

    sm.warp(102040600, 1)
    sm.startQuest(parentID)