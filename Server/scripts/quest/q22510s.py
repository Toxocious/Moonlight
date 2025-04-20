sm.setSpeakerID(1013103)
selection = sm.sendSay("Hm? What is it, Evan? Are you here to help your old dad? Huh? What do you mean, you defeated the #o1210111#s?! Geez, are you hurt?!\r\n#b#L0#I'm fine, Dad! It was easy.#l")
if (selection == 0):
    if sm.sendAskAccept("What a relief. You need to be careful, though. It could've been dangerous... By the way, I've got something for you to do. Can you run an errand for me?"):
        if not sm.hasQuest(parentID):
            sm.giveItem(4032455)
            sm.startQuest(parentID)
        sm.removeEscapeButton()
        sm.sendNext("Could you tell #b#p1012003##k in #b#m100000000# that I'm not going to be able to deliver the #t4032453# on time? The #o1210111#s have caused so many problems.")
        sm.sendSay("I've written everything down in this letter, so all you have to do is take this to him. I'd go myself, but I have to deal with problems here.")
        sm.sendSayImage("UI/tutorial/evan/13/0")
    else:
        sm.sendNext("I guess all that action with the #o1210111#s has worn you out. Why don't you come back and see me after you get some rest.")
        sm.dispose()
else:
    sm.dispose()