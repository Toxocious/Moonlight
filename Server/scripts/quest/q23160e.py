# Blaster 1st Job Advancement - Elex

ELEX = 2151000
BLASTER1 = 3700
HAND_BUSTER = 1582000
RUDIMENTARY_CHARGES = 1353400

sm.setSpeakerID(ELEX)
if not sm.canHold(HAND_BUSTER):
    sm.sendSayOkay("Please make room in your equip inventory.")
    sm.dispose()


if sm.sendAskYesNo("So you've finally decided to become a Blaster, eh? Well, you can still change your mind. Just stop our conversation, forfeit this quest, and talk to another class trainer. So, you sure you want to become a Blaster? I'm not interested in teaching you unless you're a hundred percent sure..."):
    sm.jobAdvance(BLASTER1)
    sm.resetAP(False, BLASTER1)
    sm.giveItem(HAND_BUSTER)
    sm.giveAndEquip(RUDIMENTARY_CHARGES)
    sm.completeQuest(parentID)
    chr.getJobHandler().reloadCylinder()

    sm.removeEscapeButton()
    sm.sendNext("Great! You're now an official member of the Resistance! Let me give you a brief explanation on Blaster skills to celebrate becoming a Blaster.")
    sm.sendSay("The two major mechanics of a Blaster are #bAmmo#k and the #bDynamo Gauge#k. In combat, you use #bAmmo#k to reload the #bDynamo Gauge#k. You can then use the powerful #eBunker Buster#n skill with the reloaded #bDynamo Gauge#k.")
    sm.sendSay("#i3801012#\r\nYou can check your #bAmmo#k and #bDynamo Gauge#k at any time through the UI, as shown above.")
    sm.sendSay("You want even more details? Teacher's pet, much?")
    sm.sendSay("#i3801012#\r\nFine, fine. #bAmmo#k can be loaded into your weapon. It's used in a skill called #eRevolving Cannon#n. #bAmmo#k reloads automatically when you're out, but you can also hold down the Revolving Cannon hotkey to reload manually.")
    sm.sendSay("#i3801012#\r\nMight be smart to reload whenever you're low on #bAmmo#k. Now, #eRevolving Cannon#n can't be used on its own. It can only be used during #bMagnum Punch#k, #bDouble Blast#k, #band Revolving Blast#k.")
    sm.sendSay("Think of #eRevolving Cannon#n as squeezing in an extra shot during Magnum Punch, Double Blast, or Revolving Blast.")
    sm.sendSay("#i3801013#\r\nNow, you're probably wondering about the #bDynamo Gauge#k. Every time you consume a suitable amount of #bAmmo#k, a bar in your #bDynamo Gauge#k will fill up.")
    sm.sendSay("#i3801014#\r\nWhen the #bDynamo Gauge#k loads up, it'll overheat, causing you to deal more damage. You can use a powerful skill called #eBunker Blaster#n when at least 3 bars in the #bDynamo Gauge#k are filled. You won't be able to use it again until you reload the #bDynamo Gauge#k or wait a bit, though.")
    sm.sendSay("You'll have to practice using up #bAmmo#k and reloading the #bDynamo Gauge#k quickly to get the hang of enabling #eBunker Blaster#n. Power takes effort, you know?")
    sm.sendSay("And... that's that. You're no longer some no-name warrior, you're a Blaster! A Blaster, blasting for freedom!")
    sm.sendSay("Watch yourself out there. If you're exposed, we're all at risk. From now on, call me 'teacher' and I'll give you special lessons here in this very room.")
else:
    sm.sendNext("Make your decision carefully.")
