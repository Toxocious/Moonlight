# Midsummer Night's Forest: Ellinel Lake Shore's Water Portal Script

YOU_CAN_DO_IT = 32102 # QUEST ID
MIDSUMMER_NIGHTS_FOREST_ELLINEL_LAKE_SHORE = 101070000 # MAP ID
MIDSUMMER_NIGHTS_FOREST_ELLINEL_LAKE_SHORE_VER2 = 101070001 # MAP ID

if sm.hasQuest(YOU_CAN_DO_IT):
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("Ugh! Why do I feel so... heavy?! Each... step... getting... tougher!\r\n\r\nARGH!")
else:
    sm.warp(MIDSUMMER_NIGHTS_FOREST_ELLINEL_LAKE_SHORE)
    sm.dispose()

sm.warp(MIDSUMMER_NIGHTS_FOREST_ELLINEL_LAKE_SHORE_VER2)
