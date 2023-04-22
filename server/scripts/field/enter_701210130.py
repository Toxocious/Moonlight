NINE_TAILED_FOX = 9310579

if sm.hasQuest(62015):
    sm.chatScript("..Maybe there's a hidden entrance somewhere")
    sm.chatScript("You can't find the Woodcutter anywhere..")
elif "1" in sm.getQRValue(62015):
    sm.setBoxChat()
    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.setQRValue(62015, "2")
    sm.sendNext("Alright.. Time to ask #p"+ str(NINE_TAILED_FOX) +"# about this..")