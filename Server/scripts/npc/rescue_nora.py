# Nora the Explorer (1514002) | Nora's Cove

if not sm.hasMobsInField():
    sm.sendNext("You have to be calm... very calm... Mother and Father, please give me strength...")

    sm.sendSay("Huh? The monsters are gone! Where have they gone to?")

    sm.sendSay("Brave warrior! You saved me! I don't know how to thank you but... Thank you so much.")

    sm.sendSay("My name is #bNora#k, an Archaeologist.\r\n\r\n"
                "I came to Rien because my interest is in the Riena Straits"
                "I was doing research on the mysterious fossil found here, but it started to move and attack me."
                "I was to scared back then.")

    sm.sendSay("But why were you there, brave warrior?")

    sm.setPlayerAsSpeaker()
    sm.sendSay("#b(I told Nora that I was in search of a witch to help the residents here.)#k")

    sm.setSpeakerID(parentID)
    sm.sendSay("A witch? Are you saying the appearance of the monsters and the rising of the sea levels is all done by the witch?")

    sm.sendSay("No way. "
                "These fossils fell off as the eternal frost started to melt due to the rising temperature and as the foundation rock plate was influenced by the ores with its magical power."
                "Which means, these monsters appeared naturally. They have not been summoned by anyone.")

    sm.sendSay("As for the rising of the sea levels.. Would that be really the work of the witch? Wouldn't there be other reasons behind it?")

    sm.sendSay("I have a favor to ask. Can I come along as you investigate the area? Please take me as your colleague!")

    sm.setPlayerAsSpeaker()
    sm.sendSay("#b(The knowledge of this young scholar would be a good help when solving problems.)")

    sm.setSpeakerID(parentID)
    sm.sendSay("Thank you! You will not regret it.\r\n"
                "(You will be transported to the 1st Observatory.)")

    #sm.completeQuest(32170) # [Riena Strait] Nerd Rescue
    #sm.startQuestNoCheck(32194) # Seems to be a quest needed  'in progress'  to make nora appear in other maps
    sm.createQuestWithQRValue(32194, "1")

    sm.warpInstanceOut(141010000,0) # Ice Station 1

