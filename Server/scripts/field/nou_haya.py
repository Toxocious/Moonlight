# Cutsence for Sengoku Era Questline | Honnou-ji : Precinct of Honnou-ji (811000012)
# Author: Tiger

PRINCESS_NO = 9000435
AYAME = 9130100

sm.lockInGameUI(True)
sm.hideUser(True)
sm.removeEscapeButton()

sm.blind(1, 255, 0, 0)
sm.sendDelay(1200)

sm.setSpeakerID(AYAME)

sm.flipDialogue()
sm.sendNext("Where could she be? Ugh, did I really get messed up that bad?")

sm.blind(0, 0, 0, 1000)
sm.sendDelay(1400)
sm.lockInGameUI(False)
