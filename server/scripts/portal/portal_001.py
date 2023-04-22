# Limbert's General Store (913070000) - continue script

# Constants
NEINHEART = 1106000
LIMBERT = 1106002

# Script answer init
sm.removeEscapeButton()
sm.setSpeakerID(NEINHEART)
sm.setPlayerAsSpeaker()

# Actions
sm.forcedInput(0)
sm.sendNext("Do you have something to say to me?")


sm.setSpeakerID(NEINHEART)
sm.sendSay("What is your name?")

sm.setPlayerAsSpeaker()
sm.sendSay("I don't have one. Just call me #bKiddo#k. That's what the old man calls me.")

sm.setSpeakerID(NEINHEART)
sm.sendSay("Is he your grandpa? Where are your parents?")

sm.setPlayerAsSpeaker()
sm.sendSay("I don't have any family. I just work here.#b\r\n(What's with all the questions?)#k\r\nLook, I have to get back to work before the old man comes back...")

sm.setSpeakerID(NEINHEART)
sm.sendSay("Do you know the name Chromile? The Knight of Light?")

sm.setPlayerAsSpeaker()
sm.sendSay("Nope, never heard of the guy...#b\r\n(Why does that name sound familiar?)")

sm.setSpeakerID(LIMBERT)
sm.sendSay("#eYou little brat!\r\nI told you to move boxes, not chat up my customers!")

sm.setPlayerAsSpeaker()
sm.sendSay("I was just about to clean it up...\r\nSorry, I gotta do what he says...")

sm.completeQuestNoRewards(20030) # neinheart npc to disappear after completing the quest
sm.addLevel(1)
sm.showBalloonMsg("Effect/Direction7.img/effect/tuto/step0/4", 2000)
sm.localEmotion(6, 2000, False)
sm.sendDelay(2000)

sm.setPlayerAsSpeaker()
sm.sendNext("H-hey! Where did he go?!\r\n Ugh, who cares?! I gotta get that stuff out of here before Limbert starts raising a ruckus again...")

sm.forcedInput(2)
sm.sendDelay(800)

sm.lockInGameUI(False)
sm.warpInstanceIn(913070001, 0)
sm.dispose()