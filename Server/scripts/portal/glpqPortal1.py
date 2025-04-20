if sm.getFieldID() == 610030200:
    if sm.fieldHasProperty("Stage1Cleared"):
        sm.warpInstanceIn(610030200)
    else:
        sm.chatRed("Some of the Sigils have not been activated yet")