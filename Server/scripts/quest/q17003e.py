# Created by MechAviv
# Quest ID :: 17003
# [Commerci] Get Rich Quick

sm.setSpeakerID(9390220)
sm.sendNext("Welcome to Commerci, the place where pockets are sewn with gold thread and buttons are made of gold nuggets. In other words, we're rich.")


sm.setSpeakerID(9390220)
sm.sendSay("To start trading, just sign your life away on this contract. Then you'll be one of us. Whoo.")


sm.setSpeakerID(9390220)
if sm.sendAskYesNo("You want to be rich, right?"):
    sm.setSpeakerID(9390220)
    sm.removeEscapeButton()
    sm.sendNext("Ha! Great choice, #h0#! You won't regret this. Wait... Where'd the contract go?")


    # Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
    sm.completeQuest(17003)
    # [GUILD_RESULT] [74 F9 DA 01 00 8E 02 00 00 03 00 00 00 BA 00 00 00 ]
    # [GUILD_RESULT] [6D F9 DA 01 00 1E 4F 85 00 8E 02 00 00 19 00 00 00 B2 01 00 00 70 49 CC 55 38 ED D4 01 ]
    # Update Quest Record EX | Quest ID: [18418] | Data: B=1347
else:
    sm.setSpeakerID(9390220)
    sm.sendNext("Well, the offer stands. Come back whenever you like.")


    sm.startQuest(17003)