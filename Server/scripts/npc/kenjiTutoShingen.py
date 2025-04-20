# Takeda Shingen | Honnou-ji Eastern Wall
# Author: Tiger

if sm.getFieldID() == 807100002: # Honnou-ji Eastern Grounds
    sm.setBoxChat()
    sm.removeEscapeButton()
    sm.sendNext("You did all right, samurai. I'll let you join my side for now.")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("The battles goes well. I fear the Uesugi troops barged in too early. They may require assistance.")

    sm.setSpeakerID(parentID)

    sm.setBoxChat()
    sm.sendNext("Wouldn't suprise me. Kenshin couldn't keep her men under control if she had a stack of gold for each of them. It's not like her to be early though...")

    sm.setBoxChat()
    sm.sendNext("It's not important. We're doing well so far and I hate to beak good momentum. Men, prepare for the final charge! TO THE TEMPLE!")

    sm.flipBoxChat()
    sm.flipBoxChatPlayerAsSpeaker()
    sm.sendNext("We shall meet again, Tiger of Kai!")

else:
    sm.sendNext("Get to the Honnou-ji Outer wall and open the Eastern Door.")
