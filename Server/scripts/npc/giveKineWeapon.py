# Created by MechAviv
# Kinesis Introduction
# Map ID :: 331001000
# Hideout  :: HQ
JAY = 1531001

sm.setNpcOverrideBoxChat(JAY)
if sm.sendAskYesNo("You lost your gear? Ugh, dude! Don't trash my stuff! It takes time to hack those things together. Here, I have backups of your primary and secondary, but only the basic models. TRY to respect these, hmm?"):
    sm.giveItem(1353200)
    sm.giveItem(1262000)