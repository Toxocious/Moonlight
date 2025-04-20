# 140090000
LILIN = 1202000

if not "helper=clear" in sm.getQRValue(21019):
    sm.setSpeakerID(LILIN)
    sm.flipSpeaker()
    sm.sendNext("You've finally awoken...!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("And you are...?")

    sm.setSpeakerID(LILIN)
    sm.flipSpeaker()
    sm.sendSay("The hero who fought against the Black Mage... I've been waiting for you to wake up!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("Who... Who are you? And what are you talking about?")
    sm.sendSay("And who am I...? I can't remember anything... Ouch, my head hurts!")

    sm.reservedEffect("Effect/Direction1.img/aranTutorial/face")
    sm.addQRValue(21019, "helper=clear")
else:
    sm.setSpeakerID(LILIN)
    sm.flipSpeaker()
    sm.sendNext("Are you alright? ")

    sm.setPlayerAsSpeaker()
    sm.sendSay("I can't remember anything. Where am I? And who are you...?")

    sm.setSpeakerID(LILIN)
    sm.flipSpeaker()
    sm.sendSay(
        "Stay calm. There is no need to panic. You can't remember anything because the curse of the Black Mage erased "
        "your memory. I'll tell you everything you need to know...step by step.")
    sm.sendSay(
        "You're a hero who fought the Black Mage and saved Maple World hundreds of years ago. But at the very last "
        "moment, the curse of the Black Mage put you to sleep for a long, long time. That's when you lost all of your "
        "memories.")
    sm.sendSay(
        "This island is called Rien, and it's where the Black Mage trapped you. Despite its name, this island is "
        "always covered in ice and snow because of the Black Mage's curse. You were found deep inside the Ice Cave.")
    sm.sendSay(
        "My name is Lilin and I belong to the clan of Rien. The Rien Clan has been waiting for a hero to return for a "
        "long time now, and we finally found you. You've finally returned!")
    sm.sendSay(
        "I've said too much. It's okay if you don't really understand everything I just told you. You'll get it "
        "eventually. For now, #byou should head to town#k. I'll stay by your side and help you until you get there.")
    sm.hireTutor(True)
    sm.warp(140090100, 1)
