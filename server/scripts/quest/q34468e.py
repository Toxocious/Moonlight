# id 34468 ([Arcana] Reviving the Bramble Harp 2), field 940200216
sm.setSpeakerType(3)
sm.setParam(37)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(3003301) # Small Spirit
sm.sendNext("#face1#You brought the Volatile Shrieks! Quickly, place them on this side of the tree...")
sm.completeQuestNoCheck(parentID)
sm.lockInGameUI(True, False)
sm.removeAdditionalEffect()
sm.sendDelay(600)
sm.playExclSoundWithDownBGM("Mob.img/8644006/Die", 100)
sm.sendDelay(1500)
sm.sendNext("#face2#Yes, yes, it worked! Now that the tree is out of the way, when the stars begin to shine--")
sm.sendDelay(1000)
sm.setInnerOverrideSpeakerTemplateID(3003309) # Tree Spirits
sm.sendNext("#face0#What are you up to now?!")
sm.sendSay("#face0#Whatever you're doing, stop it right now!")
sm.setInnerOverrideSpeakerTemplateID(3003301) # Small Spirit
sm.sendSay("#face1#Uh oh... those shrieks got the attention of the Tree Spirits...")
sm.lockInGameUI(False, True)
