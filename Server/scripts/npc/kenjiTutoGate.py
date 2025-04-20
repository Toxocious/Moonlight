# Honnou-ji Eastern Gate | Honnou-ji Eastern grounds
# Author: Tiger

if not sm.hasMobsInField():
    sm.warpInstanceOut(807100002) # Honnou-ji Eastern Grounds
else:
    sm.chat("You must clear the gate!") # TODO: gms-like convo (Don't even think there is a msg here...)
