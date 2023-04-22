# Regards, Takeda Shingen (Sengoku Era) Questline | Near Momiji Hills 1 (811000001)
# Author: Tiger

TAKEDA = 9000427

if "1" in sm.getQRValue(58901): # Regards, Takeda Shingen
    sm.setSpeakerID(TAKEDA)

    sm.flipDialogue()
    sm.sendNext("Good, you're here! I was about to pick another fight")

    sm.flipDialogue()
    sm.sendSay("We have a problem, and it's not a lack of conditioner. I'll tell ya that!")

    sm.flipDialogue()
    sm.sendSay("That warrior you found is in a coma. Lost their fight with consciousness. I guess. I had a letter somewhere here from Momijigaoka (He smashes boxes and chairs looking for the letter )")

    sm.setQRValue(58901, "2") # Regards, Takeda Shingen

elif "2" in sm.getQRValue(58901): # Regards, Takeda Shingen
    sm.setSpeakerID(TAKEDA)

    sm.flipDialogue()
    sm.sendNext("Hm... I don't remember where I left it. It had the instructions on how to lift the spell.. Well, it wasn't that important anyway")

    sm.flipDialogue()
    sm.sendSay("Ha ha ha, a real man never sweats over losing such unimportant things!")

    sm.flipDialogue()
    sm.sendSay("As I recall, the Oda army is teaching wicked spells to it's soliders. Maybe one of them knocked our new friend out of commission.")

    response = sm.sendAskYesNo("There are a couple things that need to get done to lift the spell.\r\nYou can help, right?")

    if response:
        sm.flipDialogue()
        sm.sendNext("Ha, I knew you would do it.")

        sm.flipDialogue()
        sm.sendSay("First we need to know more about the spells. Eliminate some Oda Warrior Trainee monsters to find clues.")

        sm.flipDialogue()
        sm.sendSay("I don't need that many just 30 of them. That should be enough to mash into reason. Now, get to it!")

        sm.setQRValue(58901, "3") # Regards, Takeda Shingen
        sm.startQuest(parentID)
