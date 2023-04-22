# Helmsman Tanya (1510006) | All over Riena Strait

portal = {
141000000 : 1, # Glacial Observatory
141010000 : 2, # Ice Station 1
141010400 : 7, # Nora's Cove
141020000 : 3, # Ice Station 2
141040000 : 5, # Barbara's House
141030000 : 4, # Ice Station 3
141050000 : 6, # Glacier Cutter Base
}

if sm.sendAskYesNo("Navigator, would you like to set sail?"):
    sm.warp(141060000, portal[sm.getFieldID()])
