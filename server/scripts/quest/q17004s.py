# Created by MechAviv
# Quest ID :: 17004
# [Commerci] Making a Buck

sm.setSpeakerID(9390220)
sm.sendNext("That's the very last contract we had. Aren't you lucky? Now, I'd better explain a few things.")


sm.setSpeakerID(9390220)
sm.sendSay("You have no money, right? Of course, you don't. You wouldn't be here if you were rich. Here's a welcome gift for joining the trading elite, #h0#. It contains a Cargo Skiff and some funds to get you started.")


# Inventory Operation with 1 operations.
# Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
sm.startQuest(17004)
sm.completeQuest(17004)
sm.setSpeakerID(9390220)
sm.removeEscapeButton()
sm.sendNext("You need gold to trade, so don't spend it all. Unless you want to quit trading for good.")


sm.setSpeakerID(9390220)
sm.removeEscapeButton()
sm.sendSay("Learn more about your ship by clicking the icon on the lower left-hand corner on your character window. Before you know it, you'll be able to afford a bigger boat. ")


sm.setSpeakerID(9390220)
sm.removeEscapeButton()
sm.sendPrev("Talk to me again when you're ready to start your very first voyage.")
