# id 50722 (Fly in the Sky!), field 240080000
sm.setSpeakerID(2085000) # Matada 
res = sm.sendAskYesNo("Do you have the Ancient Dragon Wing Scales?")
sm.createQuestWithQRValue(18418, "B=34973")
sm.sendNext("Good, now close your eyes and imagine soaring through the sky on your mount, using the Up Arrow and Jump keys to soar ever higher.")
sm.sendSay("Not many mounts know the Soaring skill. Pegasus and Dragon are two creatures that do.")
sm.sendSay("Do not forget, you can only stay aloft for a certain amount of time. Press the Jump key often to stay airborne!")
