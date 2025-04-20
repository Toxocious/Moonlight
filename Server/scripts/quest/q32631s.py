# [Grand Athenaeum] Ariant : Near the Castle
sm.removeEscapeButton()
sm.setSpeakerID(2510001)

sm.sendNext("Hatsar is struggling with the monsters that have been hounding his trade route.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("(The monsters were definitely peculiar... Felt like something ghoulish.)")

sm.setSpeakerID(2510001)
sm.sendSay("Only one person in the world can solve this problem. But they've gone missing. That's why Hatsar wants to find them.")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Who is that?")

sm.setSpeakerID(2510001)
answer = sm.sendSay("I don't know, ask him for the details! Hatsar will shower you in riches if you do what he wants. I'm sure this is a pretty big deal for a mercenary like you!\r\n\r\n#b#L0#Accept the request. #l\r\n#L1#You're not feeling it.")
if answer == 0:
    sm.sendNext("Then go see Hatsar right away.\r\n\n\n(Talk to Hatsar.) ")
    sm.startQuest(parentID)
    sm.warp(302090110, 0)
elif answer == 1:
    sm.sendNext("Really? You can get your hands on some pretty big money...")
