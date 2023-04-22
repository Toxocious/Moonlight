# \\ Princess No \\ Next Map \\ 2 \\ 3 \\ 3 \\ 4 \\

if sm.getFieldID() == 811000200:
    if sm.hasMobsInField():
        sm.chat("You must kill all monsters before proceeding.")
    else:
        sm.warp(811000300)
elif sm.getFieldID() == 811000300:
    if sm.hasMobsInField():
        sm.chat("You must kill all monsters before proceeding.")
    else:
        sm.warp(811000400)
elif sm.getFieldID() == 811000400:
    if sm.hasMobsInField():
        sm.chat("You must kill all monsters before proceeding.")
    else:
        sm.warp(811000500)
elif sm.getFieldID() == 811000500:

    sm.sendAskYesNo

    dialog = str()

    if sm.hasMobsInField():
        dialog = "Are you sure you want to leave the battlefield?"
    if not sm.hasMobsInField():
        dialog = "Are you sure you want to leave the battlefield?"
    if sm.sendAskYesNo(dialog):
        sm.warpInstanceOutParty(811000008)

