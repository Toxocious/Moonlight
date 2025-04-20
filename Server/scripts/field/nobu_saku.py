# Cut sence for Sengoku Questline (Investigating Hieizan) | Master Room (811000009)
# Author: Tiger

from net.swordie.ms.world.field.fieldeffect import GreyFieldType

SAKUNO = 9130124
NOBUKANE = 9000430
AYAME = 9130100

if sm.hasQuest(58909): # Tears of Princess Sakuno quest in-progress
    sm.spawnNpc(NOBUKANE, 143, 187)
    sm.spawnNpc(SAKUNO, 488, 187)

    sm.hideNpcByTemplateId(NOBUKANE, True, True) # hides Nobukane before showing it spawn (Prevents NPC Gliding)

    sm.lockInGameUI(True)
    sm.hideUser(True)
    sm.removeEscapeButton()

    sm.sendDelay(200)
    sm.hideNpcByTemplateId(NOBUKANE, False, False) # shows Nobukane properly

    sm.sendDelay(500)

    # TODO: You can't dim the lights and grey the screen out at the same time..
    # sm.setFieldColour(GreyFieldType.Field, 100, 100, 100, 1200)

    sm.setFieldGrey(GreyFieldType.Field, True)
    sm.sendDelay(1750)

    sm.setSpeakerID(SAKUNO)

    sm.flipDialogue()
    sm.sendNext("Father, please reconsider! Sending Ayame to spy on Princess No\r\nagain is far too dangerous.")

    sm.flipDialogue()
    sm.sendSay("I'm sure she's up to something, but I fear for Ayame's safety.")

    sm.setSpeakerID(NOBUKANE)

    sm.flipDialogue()
    sm.sendSay("Shingen and all the other heads are unanimous. Ayame must take on the role of spy for the good of the country.")

    sm.flipDialogue()
    sm.sendSay("I happen to agree with them, and I also concur that this is none of your business. Now, it's getting late, so go to bed.")

    sm.sendDelay(800)
    sm.hideNpcByTemplateId(NOBUKANE, True, True)

    sm.sendDelay(1000)
    sm.spawnNpc(AYAME, 587, 187)
    sm.setFieldGrey(GreyFieldType.NPC, True)

    sm.sendDelay(1000)

    # TODO: figure out why ayame doesn't move...
    # sm.moveNpcByTemplateId(AYAME, True, 5, 10)

    sm.setSpeakerID(AYAME)

    sm.flipDialogue()
    sm.sendNext("Is he gone?")

    sm.setSpeakerID(SAKUNO)

    sm.flipDialogue()
    sm.sendSay("...I am sorry, I have failed.")

    sm.setSpeakerID(AYAME)

    sm.flipDialogue()
    sm.sendSay("No, it's okay. I DO have the closest ties to Princess No out of anyone here. It makes sense.")

    sm.flipDialogue()
    sm.sendSay("And I know those stupid old generals are suspicious of me too,\r\nso the sooner I get away from those geezers, the better.")

    sm.flipDialogue()
    sm.sendSay("Don't worry too much, Sakuno. I'll be fine. I'm ALWAYS fine.")

    sm.completeQuest(58909) # Tears of Princess Sakuno

    sm.setFieldGrey(GreyFieldType.Field, False)
    sm.setFieldGrey(GreyFieldType.NPC, False)

    sm.sendDelay(500)
    sm.hideUser(False)

    sm.sendDelay(500)
    sm.removeNpc(NOBUKANE)
    sm.removeNpc(SAKUNO)
    sm.removeNpc(AYAME)

    sm.warp(811000008)
    sm.lockInGameUI(False)
    sm.dispose() # just in-case

elif sm.hasQuestCompleted(58909): # has completed Tears of Princess Sakuno's quest

    sm.spawnNpc(NOBUKANE, 143, 187)
    sm.spawnNpc(AYAME, 488, 187)

    sm.lockInGameUI(True)
    sm.hideUser(True)
    sm.removeEscapeButton()

    sm.sendDelay(1500)
    sm.setFieldColour(GreyFieldType.Background, 45, 45, 45, 2000) # use background for now, field doesn't dim the npc name tags for some reason

    sm.sendDelay(2000)
    sm.sayMonologue("Fine. As you probably heard, I was given the mission to go spy on Princess No.", False)
    sm.sayMonologue("Before going undercover, Nobukane gave me the family's holy artifact", True)

    sm.setFieldColour(GreyFieldType.Background, 255, 255, 255, 1500)

    sm.setSpeakerID(NOBUKANE)

    sm.sendDelay(500)
    sm.flipDialogue()
    sm.sendNext("I'm sorry...")

    sm.setSpeakerID(AYAME)

    sm.flipDialogue()
    sm.sendSay("It's all right. My life here was pretty great, but now I have a job to do. Thanks for the memories.")

    sm.setSpeakerID(NOBUKANE)

    sm.flipDialogue()
    sm.sendSay("Why do you speak as if we won't see each other again? Here, take this. It will guide you back to us. Take it.")

    sm.setSpeakerID(AYAME)

    sm.flipDialogue()
    sm.sendSay("Is this...?")

    sm.setSpeakerID(NOBUKANE)

    sm.flipDialogue()
    sm.sendSay("This mirror has been passed through generations of our family. Legends say that it will show you the way to overcome any hardship. I'm fairly certain it will help you.")

    sm.flipDialogue()
    sm.sendSay("You are family. We are family.")

    sm.flipDialogue()
    sm.sendSay("Princess No should know about this mirror as well. Tell her that you were here to steal it from us.")

    sm.setSpeakerID(AYAME)

    sm.flipDialogue()
    sm.sendSay("Thank you. Thank you so much, Mr. Nobukane!")

    sm.setSpeakerID(NOBUKANE)

    sm.flipDialogue()
    sm.sendSay("Just come back. Come back safe. We will all be waiting for you.")

    sm.setFieldColour(GreyFieldType.Background, 30, 30, 30, 2000)
    sm.sendDelay(2000)

    sm.sayMonologue("I took the mirror and went...", False)
    sm.sayMonologue("...to Princess No, my previous master.", False)
    sm.sayMonologue("As expected, she tried to get rid of me first, but changed her tune when she saw ther mirror.", False)
    sm.sayMonologue("She took me in but she still didn't fully trust me. She kept sending me further and further away on all these 'missions'.", False)
    sm.sayMonologue("And while I was gone... the Nobukane family was exterminated by Oda Nobunage, and I heard that he kidnapped her.", False)
    sm.sayMonologue("I wanted to go right away, to see for myself, but... But there was nothing I could do.", True)
    sm.sayMonologue("And then came that day...", True)

    sm.sendDelay(200)
    sm.setFieldColour(GreyFieldType.Background, 255, 255, 255, 2000)

    # TODO: most likely complete "Where did Ayame Go" quest here (58910)

    sm.removeNpc(NOBUKANE)
    sm.removeNpc(AYAME)
    sm.lockInGameUI(False)
    sm.warp(811000011) # Honnou-ji : Precinct of Honnou-ji
