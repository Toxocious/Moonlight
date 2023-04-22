# Cut-sence for Sengoku Era Questline when first meeting Ayame | Master Room (811000007)
# Author: Tiger

AYAME = 9130106

sm.spawnNpc(AYAME, 620, 187)
sm.hideNpcByTemplateId(AYAME, True, True) # removes Ayame temporarily, this is needed because
# for some reason when the NPC first spawns, it glides to different position than originally spawned.
sm.lockInGameUI(True)
sm.removeEscapeButton()

sm.sendDelay(1100) # Send a short delay
sm.hideNpcByTemplateId(AYAME, False, False) # After delay, you can see the spawned NPC standing still

sm.sendDelay(1000)
sm.moveNpcByTemplateId(AYAME, True, 50, 50)

sm.sendDelay(200)
sm.moveNpcByTemplateId(AYAME, True, 20, 50)

sm.sendDelay(200)
sm.moveNpcByTemplateId(AYAME, True, 20, 50)

sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("(The new warrior is.. a girl?)")

sm.moveNpcByTemplateId(AYAME, True, 100, 50)

sm.sendDelay(200)
sm.setSpeakerID(AYAME)

sm.moveNpcByTemplateId(AYAME, True, 100, 50)
sm.flipDialogue()
sm.sendSay("You brought me this medicine?")

sm.moveNpcByTemplateId(AYAME, True, 100, 50)

sm.flipDialogue()
sm.sendSay("Well, thanks! You saved my life!")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("...This is my first time feeling irritated from a thank-you.")

sm.lockInGameUI(False)
sm.removeNpc(AYAME)
sm.warpInstanceOut(811000008, 0) # Momiji Hills : Master Room
