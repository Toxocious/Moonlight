# 140090200
PUIR  = 1202003

sm.setSpeakerID(PUIR)
sm.sendNext("Wait, are you... No way.... Are you the hero that #p1201000# has been talking about all this time?! #p1201000#! Don't just nod... Tell me! Is this the hero you've been waiting for?! ")
sm.sendSay("   #i4001171#")
sm.sendSay("I'm sorry. I'm just so overcome with emotions... *Sniff sniff* My goodness, I'm starting to tear up. You must be so happy, #p1201000#.")
sm.sendSay("Wait a minute... You're not carrying any weapons. From what I've heard, each of the heroes had a special weapon. Oh, you must have lost it during the battle against the Black Mage.")

if sm.sendAskYesNo("This isn't good enough to replace your weapon, but #bcarry this sword with you for now#k. It's my gift to you. A hero can't be walking around empty-handed.\r\n#fUI/UIWindow2.img/QuestIcon/4/0# \r\n#i1302000# 1 #t1302000# \r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 35 exp"):
    sm.giveItem(1302000)
    sm.giveExp(35)
    sm.completeQuest(parentID)

    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(Your skills are nowhere close to being hero-like... But a sword? Have you ever even held a sword in your lifetime? You can't remember... How do you even equip it?)#k")
    sm.tutorAutomatedMsg(16)
else:
    sm.sendNext("*sniff sniff* Isn't this sword good enough for you, just for now? I'd be so honored...")