from net.swordie.ms.client.character.union import Union

union = chr.getUnion()
if union.getPresets() < Union.MAX_PRESETS:
    # TODO: set proper quest value
    sm.consumeItem(parentID)
    union.addPreset()
else:
    sm.sendNext("You're already on the maximum amount of presets!")