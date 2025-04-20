# Created by MechAviv
# ID :: [865090003]
# Unknown : Unknown

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.setStandAloneMode(True)
sm.hideUser(True)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs26#That Evening", 100, 2500, 4, 0, 0, 1, 4, 0, 0, 0)
sm.sendDelay(3000)

sm.OnOffLayer_On(3000, "0", 0, 0, 0, "Map/EffectBT.img/dawnveil1/storm", 4, 1, -1, 0)
sm.sendDelay(1000)


sm.playSound("Sound/SoundEff.img/thunder3", 100)
sm.sendDelay(1000)


sm.playSound("Sound/SoundEff.img/thunder2", 100)
sm.sendDelay(2000)


sm.playSound("Sound/SoundEff.img/thunder", 100)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.setSpeakerType(3)
sm.sendNext("Whoa, whoa, whoa! Mr. Great Explorer, sir! What's going on!?")


sm.setIntroBoxChat(9390200)
sm.setSpeakerType(3)
sm.sendSay("Well yuh sure weren't burdened with an over-abundance o' brains, now were yuhs? This right here is what we call a storm. Hold on to something so yuhs don't get gobbled up by the waves!")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.setSpeakerType(3)
sm.sendSay("(A 'safe and cozy' trip, my foot! I'll get you for this, Neinheart!)")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.setSpeakerType(3)
sm.sendSay("Aaaaaaaaaah! Mr. Parbell! #eThe ship's going to be torn apart!#n")


sm.setIntroBoxChat(9390200)
sm.setSpeakerType(3)
sm.sendSay("Dagnabbit! The mast looks like it's 'bout to give way! But we's so close to Commerci... Hey, greenhorn! What're yuh doin' layin' about at a time like this'un!")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.setSpeakerType(3)
sm.sendSay("#b(...You passed out, just like that.)#k")


sm.OnOffLayer_Off(3000, "0", 0)
sm.hideUser(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.setStandAloneMode(False)
sm.warp(865010200, 0)
