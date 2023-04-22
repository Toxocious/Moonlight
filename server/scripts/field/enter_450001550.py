# id 450001550 (Nina's Erda Investigation : Temporary Investigation Base), field 450001550
sm.sm.getOffFieldEffectFromWz("Map/Effect.img/killing/fail")
sm.createQuestWithQRValue(34170, "count=0;date=19/06/18")
sm.setSpeakerID(3003145) # Nina
res = sm.sendNext("#b#eErda Spectrum#n#k\r\n\r\nAh, I see you made it back without a scratch. That's more than I can say. Well done!\r\n#L1# I'd like to return to Nameless Town.#l")
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(3003145) # Nina
sm.sendNext("Unfortunately, you didn't complete the investigation. Better luck next time... Goodbye!")
sm.createQuestWithQRValue(34170, "count=0;date=19/06/18;clear=0")
sm.warp(450001000)
