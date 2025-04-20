FREUD = 2159357

sm.forcedInput(0)

sm.removeEscapeButton()
sm.setSpeakerID(FREUD)
sm.sendNext("We did all we could, but...")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("What happened to Mercedes?")

sm.setSpeakerID(FREUD)
sm.sendSay("She'll live, but I don't know how long. The Black Mage is too strong. We've only got one option.")

sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg0/10", 0, 0, -90, 0, sm.getNpcObjectIdByTemplateId(FREUD), False, 0)
sm.sendDelay(1800)

sm.showEffect("Skill/2218.img/skill/22181003/affected", 0, 0, -90, 0, sm.getNpcObjectIdByTemplateId(FREUD), False, 0)
sm.sendDelay(1500)

sm.setSpeakerID(FREUD)
sm.sendNext("#b(Listen to me, Luminous. I'm talking to you telepathically. Do you remember the seal spell I told you about?)#k")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("#b(You intend to trap the Black Mage?)#k")

sm.setSpeakerID(FREUD)
sm.sendSay("#b(I intend to turn the power he stole from the Goddess of Time against him! But the only way to trigger the spell is to force the Black Mage to use his full power against one of us...)#k")
sm.sendSay("#b(I set the trap when I saw that we were losing. I don't think he noticed, but he won't use his full power against any of us. Not in this state...)#k")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("#b(Tell me what to do.)#k")

sm.setSpeakerID(FREUD)
sm.sendSay("#b(Activate the seals. I'll keep the Black Mage busy, but hurry! There are 5 seals you need to activate.)#k")
sm.sendSay("#b(The first seal is off to the right. It will activate when you get close to it.)#k")

sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("#b(It will be done.)#k")

sm.setSpeakerID(FREUD)
sm.sendSay("#b(Time will become unfrozen when all the seals are activated. Goddess of Time, give me your strength...)#k")

sm.lockInGameUI(False)
sm.warp(927020071, 0)

