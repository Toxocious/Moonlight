# https://strategywiki.org/wiki/MapleStory/Professions
# this was the first big script i did so forgive the mess lol

import random

# unsure if all of these are handled here but they are all grouped together and look the same so.. idk

PRIMAL_ESSENCE = 4021022 # Occasionally found from Heartstones, Gold Flowers, Mysterious Ore Veins, Mysterious Legendary Ore Veins, Mysterious Herbs, and Mysterious Legendary Herbs
DUSK_ESSENCE = 4023023 # occasionally found from Heartstones, Gold Flowers, Mysterious Ore Veins, and Mysterious Herbs
BRILLIANT_DUSK_ESSENCE = 4023024 # occasionally found from Mysterious Legendary Ore Veins and Mysterious Herbs
PIECE_OF_TIME = 4020009

UNRELENTING_FLAME = 4023025 # occasionally found in Mysterious Herbs
FOREVER_UNRELENTING_FLAME = 4023026 # occasionally found in Mysterious Legendary Herbs

CUBIC_BLADE = 4021041 # Occasionally found in Mysterious Ore Veins
CUBIC_CHAOS_BLADE = 4021042 # Occasionally found in Mysterious Legendary Ore Veins

# legend: [REACTOR_ID], [DROP_ID], [DROP_ID]

RANDOM_POWDER = [4007001, 4007002, 4007003, 4007004, 4007005, 4007006, 4007007]
RANDOM_ADVANCED = [PRIMAL_ESSENCE, DUSK_ESSENCE, PIECE_OF_TIME]

# basic
HERBS = [
[100000, 4022000, 4022001], 			# marjorama seed & flower 
[100010, 4022000, 4022001], 			# marjorama seed & flower 
[100001, 4022002, 4022003], 			# lavender seed & flower 
[100002, 4022004, 4022005], 			# rosemary seed & flower 
[100003, 4022006, 4022007], 			# mandarin seed & flower 
[100004, 4022008, 4022009, 4022010],	# lemon balm seed & flower, and peppermint flower
[100005, 4022011, 4022012], 			# jasmine seed & flower 
[100006, 4022013, 4022014], 				# tea tree seed & flower 
[100007, 4022015, 4022016], 			# chamomille seed & flower 
[100008, 4022017, 4022018], 			# patchouli seed & flower 
[100009, 4022019, 4022020, 4022021]] 	# juniper berry seed & flower, and hyssop flower

# advanced
GOLD_FLOWER = 100011
ADVANCED_HERBS = [
[100012, UNRELENTING_FLAME, BRILLIANT_DUSK_ESSENCE], 								# mysterious herb
[100013, UNRELENTING_FLAME, FOREVER_UNRELENTING_FLAME, BRILLIANT_DUSK_ESSENCE]] 	# mysterious legendary herb

# basic 
VEINS = [
[200000, 4010004, 4020004],				# silver and opal ore
[200010, 4010004, 4020004],				# silver and opal ore
[200001, 4010005, 4020001],				# orihalcon and amethyst ore
[200002, 4010001, 4020005],				# steel and sapphire ore
[200003, 4010003, 4010000],				# adamantium and bronze ore
[200004, 4010002, 4020003, 4004002],	# mithril and emerald ore, and dex crystal ore
[200005, 4010006, 4020006],				# gold and topaz ore
[200006, 4020002, 4020007],				# aquamarine and diamond ore
[200007, 4020000, 4004000],				# garnet and power crystal ore
[200008, 4020008, 4004004],				# black crystal and dark crystal ore
[200009, 4004001, 4004003, 4010007]]	# luk crystal and wisdom crystal ore, and lidium ore


# advanced
HEARTSTONE = 200011
ADVANCED_VEINS = [
[200012, CUBIC_BLADE, BRILLIANT_DUSK_ESSENCE], 						# MYSTERIOUS_VEIN
[200013, BRILLIANT_DUSK_ESSENCE, CUBIC_BLADE, CUBIC_CHAOS_BLADE]] 	# MYSTERIOUS_LEGENDARY_VEIN

OTHER = [
[1209002]] # nautilus quest herb

RANDOM_FLOWER = [4034886, 4034887, 4034888, 4034889, 4034890, 4034891, 4034892] # drops when reactor isn't coded

reward = 0

# iterate herbs
for i in HERBS:
	if parentID in i:
		i.remove(parentID)
		reward = random.choice(i)
		break

# iterate advanced herbs
if reward == 0:
	for i in ADVANCED_HERBS:
		if parentID in i:
			i.remove(parentID)
			reward = random.choice(i + RANDOM_ADVANCED)
			break

# iterate veins
if reward == 0:
	for i in VEINS:
		if parentID in i:
			i.remove(parentID)
			reward = random.choice(i)
			break

# iterate advanced veins
if reward == 0:
	for i in ADVANCED_VEINS:
		if parentID in i:
			i.remove(parentID)
			reward = random.choice(i + RANDOM_ADVANCED)
			break

if reward == 0:
	if parentID == HEARTSTONE or parentID == GOLD_FLOWER:
		reward = random.choice(RANDOM_POWDER + RANDOM_ADVANCED)

#if reward > 0:
#	for i in OTHER:
#		if parentID in i:
#			i.remove(parentID)
#			reward = random.choice(i)

pos = sm.getPosition(objectID)
sm.removeReactor()

if reward <= 0:
	reward = random.choice(RANDOM_FLOWER)
	sm.systemMessage("Oopsie, it seems like [" + str(parentID) + "] this cute little guy doesn't know what to do! Alert staff so they can help him find his purpose. :)")

sm.dropItem(reward, pos.getX(), pos.getY())