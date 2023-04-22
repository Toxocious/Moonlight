# Black Jack - Resistance Headquarters : Secret Plaza

from net.swordie.ms.constants import JobConstants

BLACK_JACK = 2151008

sm.setSpeakerID(BLACK_JACK)
if not JobConstants.isWildHunter(sm.getChr().getJob()):
    sm.sendSayOkay("Grrrr....(You can't enter. Only Wild Hunters may enter.)")
    sm.dispose()

response = sm.sendAskYesNo("Do you want to enter the jaguar habitat?")
if response:
    sm.warp(931000500, 0)