sm.setSpeakerID(9010000)
sm.sendNext("Hello, #b#e#h0##n#k!\r\nWelcome to #e#bMaple World#k#n! I'd like to give you a little help!")
sm.sendSay("Do you know what a #i2030059# #e#bReturn Scroll - Nearest Town#k#n is?")
sm.sendSay("#e#bReturn Scroll - Nearest Town#k#n is an item that sends you to the#r#e nearest town#k#n from your current location. Use it when you're out hunting and ready to return to town. Then you can save those footsies for fighting! Pretty useful, huh?")
sm.sendSay("But don't forget#e#r one#k#n Return Scroll - Nearest Town will be consumed#e#r immediately upon use#k#n.\r\nAlso, you can't use it in#e#r places where Teleport is not allowed#k#n.")
sm.sendSay("Last, you can buy#b Return Scrolls - Nearest Town#k#n from a#b#e Merchant#n#k in the nearest town.")

if sm.sendAskYesNo("Now I'm ready to hand it over.\r\nDo you want #i2030059##e#b Return Scroll - Nearest Town x10#k#n now?"):
    sm.giveItem(2030059, 10)
    sm.startQuest(parentID)
    sm.completeQuestNoRewards(parentID)
    sm.sendSayOkay("I just gave you #i2030059##e#b Return Scroll - Nearest Town x10#k#n.\r\nHappy travels, #e#b#h0##k#n!")
else:
    sm.sendSayOkay("If you want to accept the #i2030059##e#b Return Scroll - Nearest Town#k#n, please click the light bulb overhead.")