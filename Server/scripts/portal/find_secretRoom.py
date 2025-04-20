from net.swordie.ms.client.character.skills.temp import CharacterTemporaryStat

random = sm.getRandomIntBelow(5)

if random == 0:
    sm.warpInstanceOut(701220300) # Sutra 5-6
    sm.removeCTS(CharacterTemporaryStat.Morph)
    if sm.hasQuest(62011):
        sm.completeQuest(62011)
        sm.giveExp(2020453)
    sm.createQuestWithQRValue(62011, "0")
else:
    sm.setPlayerAsSpeaker()
    sm.sendSayOkay("This doesn't seem to be it..")