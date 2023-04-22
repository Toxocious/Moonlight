# [Shaolin Temple] Demon-iority Complex

GOBLIN_DEMON = 9310578
DABAO = 9310034
MAHAVIRA_HALL = 701220000

sm.removeEscapeButton()
sm.setBoxChat()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Hey, #b#p"+ str(GOBLIN_DEMON) +"##k. Will it gross you out too much if I talk to you?")

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("Yes. But what choice do I have? You've already helped the others...")

sm.sendNext("The world is changing, you know? Demons and humans, working together.")

sm.sendNext("My parents would die if they knew. But I have to adapt, if I want to survive. \r\n"
            "It's just... I find humans so utterly disgusting.")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("I have an Idea.")

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
sm.sendNext("I'm almost afraid to ask...")

sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("It's about acclimation. You have to immerse yourself. Hey, I think I'm gonna get you the mask of a human face.")

sm.setSpeakerID(GOBLIN_DEMON)
sm.setBoxChat()
response = sm.sendAskYesNo("How is that a good idea? Haven't you heard a word I've been saying?")

if response:
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("You've got to live, breathe, and think human. Then you'll understand us. Trust me. I'm going to go to ask #p"+ str(DABAO) +"# at #m"+ str(MAHAVIRA_HALL) +"# to make you that mask.")

    sm.startQuest(parentID)