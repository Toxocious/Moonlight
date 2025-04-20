# Limbert's General Store (913070000) - continue script

# Constants
NEINHEART = 1106003
CYGNUS = 1106001
JAAH = 1106005
MOB = 9001050

# Script answer init
sm.removeEscapeButton()
sm.setSpeakerID(JAAH)
sm.setPlayerAsSpeaker()

# Actions
sm.forcedInput(0)
sm.sendNext("Who are you? Are you lost? ")

sm.setSpeakerID(CYGNUS)
sm.sendSay("I have sought after you for some time, and now you stand before me. The warrior with the destiny of light.")

sm.setPlayerAsSpeaker()
sm.sendSay("What are you talking about?")

sm.setSpeakerID(NEINHEART)
sm.sendSay("Mind your manners, peon! This is the Empress!")

sm.setPlayerAsSpeaker()
sm.sendSay("You're the guy from before! What's going on here? That guy you talked about, Chromile... I found a letter from him in the attic. Is that Limbert's real name or something?")

sm.setSpeakerID(CYGNUS)
sm.sendSay("Chromile and Mr. Limbert have no connection, save you. Chromile... is your father.")

sm.setPlayerAsSpeaker()
sm.sendSay("My father left me here when I was little. He abandoned me to this old chicken-keeper.")

sm.setSpeakerID(CYGNUS)
sm.sendSay("He did not abandon you. Your father left you here after your mother passed away to save your life. His path was not one you could follow...")

sm.setPlayerAsSpeaker()
sm.sendSay("Save me? He didn't save me. He left me to be a slave in this shack. He didn't even give me a name! And now I find out I've been here waiting for a father that'll never return...")

sm.setSpeakerID(CYGNUS)
sm.sendSay("Only the darkest night can produce a brilliant sunrise. Put aside your anger and come with me. You will find the light you seek.")

sm.setSpeakerID(NEINHEART)
sm.sendSay("Empress, I do not have faith in this boy. We know nothing about him. I don't think he is fit to be the knight of light.")

sm.setSpeakerID(CYGNUS)
sm.sendSay("Dear Neinheart, I should have known better than to assume you would trust in faith. Go ahead and test him, but be gentle.")

sm.setPlayerAsSpeaker()
sm.sendSay("Wait, what?")

sm.sendDelay(1000)

for i in range(10):
    sm.spawnMobWithAppearType(MOB, 200, 50, 0, 0)
sm.lockInGameUI(False)
sm.completeQuestNoRewards(20034) # neinheart npc to disappear after completing the quest
sm.startQuestNoCheck(20035)
sm.dispose()