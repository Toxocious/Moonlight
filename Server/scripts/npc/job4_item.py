selection = sm.sendNext("...Can I help you?"
            "\r\n\r\n#b"
            "#L0#Find the ingredients for the Flying Potion (Dragon moss Extract)#l")

if selection == 0:
    if sm.hasQuest(3759) and not sm.hasItem(4032531):
        sm.sendNext("So, have you progressed in the investigation of the Dragon Rider?")
        sm.sendSay("What? You met#b Matada#k? Well, well, well. I suppose he's finally returned, after having traveled the world. Not that he's ever done anything for this town, but perhaps he missed his home?")
        sm.setPlayerAsSpeaker()
        sm.sendSay("To pursue the Dragon Rider, you'll have to be able to fly... To do so, you'll need the#b Dragon Moss Extract")
        sm.setSpeakerID(parentID)
        sm.sendSay("The Dragon Moss Extract? That's an ingredient used in specialty medicine from the Halfingerers. Sure, I'll give you some if you need it, but are you sure it'll let you fly?")
        sm.setPlayerAsSpeaker()
        sm.sendSay("Yes. Matada said it's a key ingredient to make a potion for flying.")
        sm.setSpeakerID(parentID)
        sm.sendSay("Oh, I see. Alright. I'll give you some, I hope you knock some sense into that Dragon Rider!")
        sm.sendSay("Please keep looking into it, will you?")
        if sm.canHold(4032531):
            sm.giveItem(4032531)
        else:
            sm.sendSayOkay("Please make some room in your ETC inventory.")
    else:
        sm.sendSayOkay("I'm sorry, I can't give you Dragon Moss Extract at the moment.")