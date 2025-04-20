ATHENA = 1012100

sm.setSpeakerID(ATHENA)
sm.sendNext("A young elf named Athena Pierce... I believe I'm the only elf with that name. And I did come from Ossyria to Victoria Island to get away from the Black Mage...")

sm.setPlayerAsSpeaker()
sm.sendSay("But she was so young. I'm sorry, but you couldn't...")

sm.setSpeakerID(ATHENA)
sm.sendSay("Are you talking about the curse on the elves? Ah, I was lucky enough to avoid it. #rHundreds of years#k is enough time for an elf to mature, you know.")

sm.setPlayerAsSpeaker()
sm.sendSay("A curse? Hundreds... of years? This makes no sense. The day Mercedes and the rest of us faced the Black Mage was the same day that little Athena Pierce got on the ship to Victoria Island.")

sm.setSpeakerID(ATHENA)
sm.sendSay("Then were you... Were you also under the Curse of the Black Mage? Listen to me carefully... It's been centuries since the day you were sealed. Do you understand? #r#eCenturies#k#n.")

sm.startQuest(parentID)
