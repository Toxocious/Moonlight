# Created by MechAvive
# Quest ID :: 25568
# Moth-Eaten Scroll

sm.setSpeakerID(0)
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("The Aurora emblem is dimmer than I remember, but this is it. Is it the scroll's age, or the darkness within me?")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Little matter. Time to see what secrets this scroll holds.\r\n#b<Double-click on the #r#t2430872##b in your Use tab.>#k")

sm.startQuest(25568)
