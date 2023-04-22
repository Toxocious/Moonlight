# Barriers of Elluel

sm.setSpeakerID(1033205) # Entrance to Elluel NPC
response = sm.sendAskYesNo("#b(You can see the wards to create the seal around Elluel. \r\n"
                "Speaking the magic word will finisht eh spell, cutting the village off from the outside world for at least 100 years. \r\n"
                "Activate the seal?)#k")

if response:
    sm.startQuestNoCheck(parentID)
    sm.completeQuest(parentID)
    sm.sendSayOkay("#b(The seal is complete, and the town is safe.)")
