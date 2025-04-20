# A New Warrior Appears (Sengoku Era) Questline | Momiji Hills: Master Room (811000008)
# Author: Tiger

AYAME = 9130100
SUKUNO = 9130124

sm.setSpeakerID(AYAME)

sm.flipDialogue()
sm.sendNext("Ooh, I had SUCH a hard time getting here! I got to a really strange place and I was all tired and then...")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("Speaking of wich, Takeda Shingen would like to know how you got here.")

sm.setSpeakerID(AYAME)

sm.flipDialogue()
sm.sendSay("Takeda Shingen? The same Takeda Shingen that I knew before?")

sm.flipDialogue()
sm.sendSay("Ugh, that old fart. Tell him that Ayame has NOTHING to say to HIM.\r\n"
    "Does he even have a SPECK of compassion? It's his fault I ended up like this!")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I get this impression you are not very fond of Takeda Shingen")

sm.setSpeakerID(AYAME)

sm.flipDialogue()
sm.sendSay("Anyway, where is Sakuno? I have so much to tell her! Sakuno\r\nWhere are you! Sa-ku-no Sakuno? Sakuno!")

sm.lockInGameUI(True)
sm.removeEscapeButton()
sm.moveCamera(200, 0, 150)

sm.spawnNpc(9130124, -603, 187)
sm.flipNpcByTemplateId(9130124, False)
sm.moveNpcByTemplateId(9130124, False, 300, 60)
sm.sendDelay(2500)

sm.setSpeakerID(SUKUNO)

sm.flipDialogue()
sm.sendNext("Ayame!")

sm.moveNpcByTemplateId(9130124, False, 100, 50)

sm.setSpeakerID(AYAME)

sm.flipDialogue()
sm.sendSay("Eee! Sakuno! hee hee, how have you been? I MISSED YOU SO MUCH!")

sm.setSpeakerID(SUKUNO)

sm.flipDialogue()
sm.sendSay("...")

sm.flipDialogue()
sm.sendSay("I heard you were back and I... I...")

sm.setSpeakerID(AYAME)

sm.flipDialogue()
sm.sendSay("Heyyy, don't start the waterworks now! I'm back!")

sm.removeNpc(9130124)
sm.startQuest(parentID)
sm.warp(811000008)
