# a pile of flowers (1043000) | Forest of Endurance : Stage 2 (910130001)
# Forest of Endurance (Quest 2050)
# Author: Tiger

import random

rewards = [
 4020005, # Sapphire Ore
 4020006, # Topaz Ore
 4020004, # Opal ore
 4020001, # Amethyst Ore
 4020003, # Emerald Ore
 4020000, # Garnet Ore
 4020002  # AquaMarine Ore
 ]

if sm.hasQuest(2050): # Forest of Endurance - The Pink Anthurium Quest
    response = sm.sendAskYesNo("Are you sure you want to take #bPink Anthurium#k with you?")

    if response:
         sm.giveItem(4031020, 1) # Pink Anthurium
         sm.warp(101000000) # Ellinia
else:
    sm.giveItem(random.choice(rewards), 2)
    sm.warp(101000000) # Ellinia
