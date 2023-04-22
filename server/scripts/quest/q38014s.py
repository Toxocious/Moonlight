COMPASS = 3002006

# In GMS, the correct answer and any after it seems to be in blue while any before it are grey, changed to just the correct answer being blue because why not


sm.setSpeakerID(COMPASS)

if not sm.sendAskAccept("I see, you are taking the test of the foxes. I'm very proud of you. You will be talked about by the younger generation for overcoming your lack in many ways. Now here is the test. Pass this multiple-choice quiz and I shall give you my paw stamp. Do you want to begin now?"):
    sm.sendSayOkay("It's nothing urgent, so take your time. Talk to me when you are ready.")
    sm.dispose()

answer = sm.sendSay("The first question. What's the favorite meal of Pointy-Ear Foxes?\r\n#L0# Dried Caterpillar#l\r\n#L1# Spotted Wings#l\r\n#L2# Sparrow Eggs#l\r\n#L3# Mouse Tail#l\r\n#L4# #bRaw Liver#l")

if not answer == 4:
    sm.sendNext("Wrong! You should study more!")
    sm.dispose()

answer = sm.sendSay("That's right! We love raw liver. Second question, who do we serve as our god?\r\n#L0# Tiger God#l\r\n#L1# #bFox God#k#l\r\n#L2# Wolf God#l\r\n#L3# Mountain God#l\r\n#L4# Nongod#l")
if not answer == 1:
    sm.sendNext("Wrong! You should study more!")
    sm.dispose()

answer = sm.sendSay("That's right, the Pointy-Ear Foxes believe in the fox god. Now last question, what kind of power do we have?\r\n#L0# #bPower to handle spirits#k#l\r\n#L1# Power to control time#l\r\n#L2# Power to control animals#l\r\n#L3# Power to ignore other people#l\r\n#L4# Power to be super cute#l")
if not answer == 0:
    sm.sendNext("Wrong! You should study more!")
    sm.dispose()

sm.sendNext("That's right, we have the power to handle spirits. I heard you got one too, from the fox god. Use it wisely, or the fox god will not be pleased.")
sm.sendSay("This is it. My test is done. Here is my paw stamp, so now go talk to #r#p3002009##k. She will give you the next test.\r\n                                                                               #fUI/DialogImage.img/quest/0")
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.giveExp(2800)
