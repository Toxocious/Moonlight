# 32216 Victoria Island or Bust!, sugar : Maple Road | the Sangri-La
sm.setSpeakerID(10306)
sm.sendNext("You d-defeated the monsters, and you really helped me out, too, #h #. You seem r-ready to pick a Job. Did you decide which one you want?")
sm.setPlayerAsSpeaker()
sm.sendNext("#b Huh? Job?")
sm.setSpeakerID(10306)
sm.sendNext("There are five different Explorer Jobs. You can advance to them on Victoria Island. Hm I think they were... Warrior, Magician, Bowman, Thief, and Pirate.")
sm.setPlayerAsSpeaker()
sm.sendNext("#b What are they like?")
sm.setSpeakerID(10306)
sm.sendNext("Let's see. Warriors have great strength and defense, so they excel at close-range combat. Magicians use magic, so they f-favor intelligence over power, and they're good at long-range combat against multiple enemies.")
sm.sendNext("B-bowman are also good at long-range combat. They shoot arrows from afar and can keep enemies at a distance. And, let's see... Thieves are close-range, like warriors, but they focus on speed instead of strength.")
sm.sendNext("Finally, Pirates... are Pirates. Some use their fists in close-range combat, others shoot guns or cannons from afar. Their attacks are pretty fancy, either way.")

selection = sm.sendNext("If you pick your Job right now, the captain offered to contract your new job instructor as soon as we arrive. \r\n\r\n#L0##b Warrior, powerful and defensive#l \r\n #L1#Magician, intelligent and magical#l \r\n #L2#Bowman, long-ranged and controlled#l \r\n #L3#Thief, speedy and sneaky#l \r\n #L4#Pirate, fancy and unique #l")
if selection == 0:
    sm.sendNext("Oh, t-totally! #h #, you'll make a great Warrior!")
elif selection == 1:
    sm.sendNext("Oh, t-totally! #h #, you'll make a great Magician!")
elif selection == 2:
    sm.sendNext("Oh, t-totally! #h #, you'll make a great Bowman!")
elif selection == 3:
    sm.sendNext("Oh, t-totally! #h #, you'll make a great Thief!")
elif selection == 4:
    sm.sendNext("Oh, t-totally! #h #, you'll make a great Pirate!")

sm.warp(104000000, 0)
sm.sendNext("The ship is ready to set sail!")
sm.startQuest(parentID)
sm.completeQuest(parentID)
sm.createQuestWithQRValue(1406, str(selection+1))
if sm.getChr().getLevel() < 10:
    sm.addLevel(10 - sm.getChr().getLevel())
