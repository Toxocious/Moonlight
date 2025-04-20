# 914000100
HELENA = 1209000

if not "1" == sm.getQRValue(21002):
    sm.setSpeakerID(HELENA)
    sm.sendNext("Aran, you're awake! How are you feeling? Hm? You want to know what's been going on?")
    sm.sendSay("We're almost done preparing for the escape. You don't have to worry. Everyone I could possibly find has boarded the ark, and Shinsoo has agreed to guide the way. We'll head to Victoria Island as soon as we finish the remaining preparations.")
    sm.sendSay("The other heroes? They've left to fight the Black Mage. They're buying us time to escape. What? You want to fight with them? No! You can't! You're hurt. You must leave with us!")
    sm.reservedEffect("Effect/Direction1.img/aranTutorial/Trio")
    sm.setQRValue(21002, "1", False)