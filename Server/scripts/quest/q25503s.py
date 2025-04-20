# Created by MechAviv
# Quest ID :: 25503
# Pressure Void Appreciation

sm.setSpeakerID(1032209)
sm.sendNext("Good job on your 2nd Job Advancement, pal. Have you tried out Pressure Void yet? It's pretty hip...")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("#bI haven't tried it yet.")


sm.setSpeakerID(1032209)
sm.sendSay("Isn't that what you're supposed to DO when you get stronger? Try out all the fancy new spells? Back in my day-- actually, that story's pretty raunchy. Let me just tell you, Pressure Void is dangerous to use, but man it is something else to see...")


sm.setSpeakerID(1032209)
sm.setPlayerAsSpeaker()
sm.sendSay("#bI will devote myself to its mastery.")


sm.setSpeakerID(1032209)
if sm.sendAskAccept("Then get started right now! Use Pressure Void to crush three #b#o3501000##k monsters near Ellinel."):
    sm.startQuest(25503)
    sm.setSpeakerID(1032209)
    sm.sendNext("Remember to aim up and down while you hold down the skill!!")

    sm.showFieldEffect("lightning/skillTuto/0", 0)
else:
    sm.setSpeakerID(1032209)
    sm.sendSayOkay("You'd better get used to this one...")


