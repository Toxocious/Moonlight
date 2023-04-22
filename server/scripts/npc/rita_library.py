# Grand Athenaeum - Dimension Library
from net.swordie.ms.enums import UIType

if sm.hasQuestCompleted(32662):
    sm.openUI(UIType.UI_DIMENSION_LIBRARY)
else:
    sm.setSpeakerID(2500001)
    sm.sendSayOkay("Chirp chirp! Talk to Thales the Librarian first.")