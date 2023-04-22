# Created by MechAviv
# [Lilin]  |  [1201000]
# Snow Island : Rien

sm.setSpeakerID(1201000)
selection = sm.sendNext("What is it?\r\n\r\n#b#L0# I want to talk to you.#l")
if selection == 0:
    sm.setSpeakerID(1201000)
    selection2 = sm.sendNext("What do you want to talk about?\r\n\r\n#b#L0# How's life been treating you lately?#l")
    if selection2 == 0:
        sm.setSpeakerID(1201000)
        sm.sendNext("Aran, you seem to have a lot of free time lately, what with all your slacking off. Can you do me a favor? I want the flower from the top of Zakum's head. If you get me that, then we can have a nice cup of tea and chit chat.")
