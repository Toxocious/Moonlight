# Mysterious Statue, Sleepywood (105000000)
response = sm.sendAskYesNo("Once I lay my hand on the statue, a strange light covers me and it feels like I'm being sucked "
                           + "into somewhere else. Is it okay to be moved to somewhere else randomly just like that?")

if response:
    sm.warp(910530000)
