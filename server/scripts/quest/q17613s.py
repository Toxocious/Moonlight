# [Commerci Republic] The Minister's Son

if sm.getFieldID() == 865010200:
    sm.setSpeakerID(9390201) # Mayor Berry
    sm.sendSayOkay("Find #e#bLeon Daniella#k#n in the guest house on the east end of this village.")
    sm.dispose()
else:
    sm.setSpeakerID(9390202) # Leon Daniella
    sm.sendSayOkay("Oi")
    sm.dispose()
