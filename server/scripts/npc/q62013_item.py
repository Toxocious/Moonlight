BLUE_DEMON = 9310577
KID_SHOES = 4034657
MOUNT_SONG_HAMLET = 701210000

sm.removeEscapeButton()
sm.setBoxChat()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.sendNext("Those are the shoes that the #p"+ str(BLUE_DEMON) +"# lost. Return them to their owner in #m"+ str(MOUNT_SONG_HAMLET) +".")

if not sm.canHold(KID_SHOES):
    sm.sendNext("I should make some room in my inventory before grabbing the shoes.")
    sm.dispose()

sm.chatScript("Go to [Mount Song Hamlet] and find the owner of the shoes.")
sm.giveItem(KID_SHOES)
