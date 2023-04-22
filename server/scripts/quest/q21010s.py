# 140090100
PUKA = 1202001

sm.setSpeakerID(PUKA)
sm.sendNext("Hm, what's a human doing on this island? Wait, it's #p1201000#. What are you doing here, #p1201000#? And who's that beside you? Is it someone you know, #p1201000#? What? The hero, you say?")
sm.sendSay("     #i4001170#")
sm.sendSay("Ah, this must be the hero you and your clan have been waiting for. Am I right, #p1201000#? Ah, I knew you weren't just accompanying an average passerby...")

if sm.sendAskAccept("Oh, but it seems our hero has become very weak since the Black Mage's curse. It only makes sense, considering that the hero has been asleep for hundreds of years. #bHere, I'll give you a HP Recovery Potion.#k."):
    sm.giveItem(2000022)
    sm.startQuest(parentID)

    sm.removeEscapeButton()

    sm.flipSpeaker()
    sm.sendNext("Drink it first. Then we'll talk.")

    sm.setPlayerAsSpeaker()
    sm.sendSay("#b(How do I drink the potion? I don't remember...)#k")
    sm.tutorAutomatedMsg(14)
else:
    sm.sendNext("Oh, no need to decline my offer. It's no big deal. It's just a potion. Well, let me know if you change your mind.")
    sm.dispose()