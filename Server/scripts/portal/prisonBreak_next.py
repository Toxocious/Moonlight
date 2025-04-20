# 921160200
# Party Quest - Escape! PQ

# Hidden Street : Aerial Prison
if sm.getFieldID() == 921160600:
    warp = sm.getReactorQuantity() > 1
    if warp: # due to invisible reactor hidden on the map
        sm.chat("Unlock all the prison doors.")
        sm.dispose()
else:
    warp = not sm.hasMobsInField() or sm.getFieldID() == 921160100
    if not warp:
        sm.chat("The portal is not opened.")
        sm.dispose()
for partyMember in sm.getParty().getPartyMembersInSameFieldList(chr):
    partyMember.warp(sm.getFieldID() + 100)
chr.warp(sm.getFieldID() + 100)