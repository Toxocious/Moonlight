sm.setSpeakerID(1102102)
if sm.sendAskYesNo("Chin up! No slouching! I'm going to whip you into shape!"):
    sm.removeEscapeButton()
    sm.sendNext("Do you even know how to hold a sword?! Press the #rCtrl key#k to perform a basic attack! Do it now! In the Drill Hall! Defeat #b3 #o9300730##k monsters and show me I'm not wasting my time!")
    sm.startQuest(parentID)
    for i in range(3):
        sm.spawnMob(9300730, -364, -7, False)
    sm.playSound("Aran/balloon", 100)
    sm.avatarOriented("UI/tutorial.img/4")
else:
    sm.sendNext("What, you too good to fight?!")