# Henesys START
itemsHene = [
[1452002, 1452003, 1452001, 1452000, 1452005, 1452006, 1452007], # Bows
[1462001, 1462002, 1462003, 1462000, 1462004, 1462005, 1462006, 1462007], # Xbows
[1082012, 1082013, 1082016, 1082048, 1082068, 1082071, 1082084, 1082089], # Gloves
[1082015,1082014,1082017,1082018,1082049,1082050,1082069,1082070,1082072,1082073,1082085,1082083,1082090,1082091], # Gloves Upgrades
[4003001,4003001,4003000], # Materials
[2060000,2061000,2060001,2061001,2060002,2061002] # Arrows
]

nonEquipSuffixHene = ["with Tree Branches", "with Firewood", "(packs of 15)"]

costHene = [
[   # Bow Materials
    [4003001,4000000],
    [4011001,4003000],
    [4003001,4000016],
    [4011001,4021006,4003000],
    [4011001,4011006,4021003,4021006,4003000],
    [4011004,4021000,4021004,4003000],
    [4021008,4011001,4011006,4003000,4000014]
],

[   # Xbow Materials
    [4003001,4003000],
    [4011001,4003001,4003000],
    [4011001,4003001,4003000],
    [4011001,4021006,4021002,4003000],
    [4011001,4011005,4021006,4003001,4003000],
    [4021008,4011001,4011006,4021006,4003000],
    [4021008,4011004,4003001,4003000],
    [4021008,4011006,4021006,4003001,4003000]
],

[   # Gloves Materials
    [4000021,4000009],
    [4000021,4000009,4011001],
    [4000021,4000009,4011006],
    [4000021,4011006,4021001],
    [4011000,4011001,4000021,4003000],
    [4011001,4021000,4021002,4000021,4003000],
    [4011004,4011006,4021002,4000030,4003000],
    [4011006,4011007,4021006,4000030,4003000]
],

[   # Gloves Upgrade Materials
    [1082013,4021003],
    [1082013,4021000],
    [1082016,4021000],
    [1082016,4021008],
    [1082048,4021003],
    [1082048,4021008],
    [1082068,4011002],
    [1082068,4011006],
    [1082071,4011006],
    [1082071,4021008],
    [1082084,4011000,4021000],
    [1082084,4011006,4021008],
    [1082089,4021000,4021007],
    [1082089,4021007,4021008]
],

[   # Create Material
    [4000003],
    [4000018],
    [4011000,4011001]
],

[   # Create Arrows
    [4003001,4003004],
    [4003001,4003004],
    [4011000,4003001,4003004],
    [4011000,4003001,4003004],
    [4011001,4003001,4003005],
    [4011001,4003001,4003005]
]
]

costQHene = [
[   # Bow Material Quantity
    [5,30],
    [1,3],
    [30,50],
    [2,2,8],
    [5,5,3,3,30],
    [7,6,3,35],
    [1,10,3,40,50]
],

[   # Xbow Material Quantity
    [7,2],
    [1,20,5],
    [1,50,8],
    [2,1,1,10],
    [5,5,3,50,15],
    [1,8,4,2,30],
    [2,6,30,30],
    [2,5,3,40,40]
],

[   # Gloves Material Quantity
    [15,20],
    [20,20,2],
    [40,50,2],
    [50,2,1],
    [1,3,60,15],
    [3,1,3,80,25],
    [3,1,2,40,35],
    [2,1,8,50,50]
],

[   # Glove Upgrade Materials Quantity
    [1,2],
    [1,1],
    [1,3],
    [1,1],
    [1,3],
    [1,1],
    [1,4],
    [1,2],
    [1,4],
    [1,2],
    [1,1,5],
    [1,2,2],
    [1,5,1],
    [1,2,2]
],

[   # Create Materials Quantity
    [10],
    [5],
    [1, 1]
],

[   # Create Arrows Quantity
    [1,1],
    [1,1],
    [1,3,10],
    [1,3,10],
    [1,5,15],
    [1,5,15]
]
]

costmesoHene = [
[800,2000,3000,5000,30000,40000,80000], # Meso Cost Bow
[1000,2000,3000,10000,30000,50000,80000,200000], # Meso Cost Xbow
[5000,10000,15000,20000,30000,40000,50000,70000], # Meso Cost Glove
[7000,7000,10000,12000,15000,20000,22000,25000,30000,40000,55000,60000,70000,80000], # Meso Cost Upgrade Glove
[0,0,0], # Meso Cost Materials
[0,0,0,0,0,0] # Meso Cost Arrows
]
# Henesys END



# Perion START
itemsPerion = [
[4011000,4011001,4011002,4011003,4011004,4011005,4011006],  # Mineral Refine
[4021000,4021001,4021002,4021003,4021004,4021005,4021006,4021007,4021008],  # Jewel Refine
[1002042,1002041,1002002,1002044,1002003,1002040,1002007,1002052,1002011,1002058,1002009,1002056,1002087,1002088,1002049,1002050,1002047,1002048,1002099,1002098,1002085,1002028,1002022,1002101],  # Helmet Refine
[1092014,1092013,1092010,1092011]   # Shield Refine
]

costPerion = [
[   # Mineral Refine Materials
    [4010000],
    [4010001],
    [4010002],
    [4010003],
    [4010004],
    [4010005],
    [4010006]
],

[   # Jewel Refine Materials
    [4020000],
    [4020001],
    [4020002],
    [4020003],
    [4020004],
    [4020005],
    [4020006],
    [4020007],
    [4020008]
],

[   # Refine Helmet Materials
    [1002001,4011002],
    [1002001,4021006],
    [1002043,4011001],
    [1002043,4011002],
    [1002039,4011001],
    [1002039,4011002],
    [1002051,4011001],
    [1002051,4011002],
    [1002059,4011001],
    [1002059,4011002],
    [1002055,4011001],
    [1002055,4011002],
    [1002027,4011002],
    [1002027,4011006],
    [1002005,4011006],
    [1002005,4011005],
    [1002004,4021000],
    [1002004,4021005],
    [1002021,4011002],
    [1002021,4011006],
    [1002086,4011002],
    [1002086,4011004],
    [1002100,4011007,4011001],
    [1002100,4011007,4011002]
],

[   # Refine Shield Materials
    [1092012,4011003],
    [1092012,4011002],
    [1092009,4011007,4011004],
    [1092009,4011007,4011003]
]
]

costQPerion = [
[   # Refine Mineral Materials Quantity
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10]
],

[   # Refine Jewel Materials Quantity
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10]
],

[   # Refine Helmet Materials Quantity
    [1,1],
    [1,1],
    [1,1],
    [1,1],
    [1,1],
    [1,1],
    [1,2],
    [1,2],
    [1,3],
    [1,3],
    [1,3],
    [1,3],
    [1,4],
    [1,4],
    [1,5],
    [1,5],
    [1,3],
    [1,3],
    [1,5],
    [1,6],
    [1,5],
    [1,4],
    [1,1,7],
    [1,1,7]
],

[   # Refine Shield Materials Quantity
    [1,10],
    [1,10],
    [1,1,15],
    [1,1,15]
]
]

costmesoPerion = [
[300,300,300,500,500,500,800],  # Meso Cost Mineral
[500,500,500,500,500,500,500,1000,3000],# Meso Cost Jewel
[500,300,500,800,500,800,1000,1500,1500,2000,1500,2000,2000,4000,4000,5000,8000,10000,12000,15000,20000,25000,30000,30000], # Meso Cost Helmet
[100000,100000,120000,120000]   # Meso Cost Shield
]
# Perion END



# Kerning Item START
itemsKerningItem = [
[1082002,1082029,1082030,1082031,1082032,1082037,1082042,1082046,1082075,1082065,1082092],   # Create Gloves
[1082033,1082034,1082038,1082039,1082043,1082044,1082047,1082045,1082076,1082074,1082067,1082066,1082093,1082094],  # Upgrade Gloves
[1472001,1472004,1472007,1472008,1472011,1472014,1472018],  # Create Claws
[1472002,1472003,1472005,1472006,1472009,1472010,1472012,1472013,1472015,1472016,1472017,1472019,1472020],  # Upgrade Claws
[4003001,4003001,4003000]   # Create Materials
]

nonEquipSuffixKerningItem = ["with Tree Branches", "with Firewood", "(packs of 15)"]

costKerningItem = [
[   # Create Gloves Materials
    [4000021],
    [4000021,4000018],
    [4000021,4000015],
    [4000021,4000020],
    [4011000,4000021],
    [4011000,4011001,4000021],
    [4011001,4000021,4003000],
    [4011001,4011000,4000021,4003000],
    [4021000,4000014,4000021,4003000],
    [4021005,4021008,4000030,4003000],
    [4011007,4011000,4021007,4000030,4003000]
],

[   # Upgrade Gloves Materials
    [1082032,4011002],
    [1082032,4021004],
    [1082037,4011002],
    [1082037,4021004],
    [1082042,4011004],
    [1082042,4011006],
    [1082046,4011005],
    [1082046,4011006],
    [1082075,4011006],
    [1082075,4021008],
    [1082065,4021000],
    [1082065,4011006,4021008],
    [1082092,4011001,4000014],
    [1082092,4011006,4000027]
],

[   # Create Claw Materials
    [4011001,4000021,4003000],
    [4011000,4011001,4000021,4003000],
    [1472000,4011001,4000021,4003001],
    [4011000,4011001,4000021,4003000],
    [4011000,4011001,4000021,4003000],
    [4011000,4011001,4000021,4003000],
    [4011000,4011001,4000030,4003000]
],

[   # Upgrade Claw Materials
    [1472001,4011002],
    [1472001,4011006],
    [1472004,4011001],
    [1472004,4011003],
    [1472008,4011002],
    [1472008,4011003],
    [1472011,4011004],
    [1472011,4021008],
    [1472014,4021000],
    [1472014,4011003],
    [1472014,4021008],
    [1472018,4021000],
    [1472018,4021005]
],

[   # Create Materials Materials
    [4000003],
    [4000018],
    [4011000,4011001]
]
]

costQKerningItem = [
[   # Create Gloves Materials Quantity
    [15],
    [30,20],
    [30,20],
    [30,20],
    [2,40],
    [2,1,10],
    [2,50,10],
    [3,1,60,15],
    [3,200,80,30],
    [3,1,40,30],
    [1,8,1,50,50]
],

[   # Upgrade Gloves Materials Quantity
    [1,1],
    [1,1],
    [1,2],
    [1,2],
    [1,2],
    [1,1],
    [1,3],
    [1,2],
    [1,4],
    [1,2],
    [1,5],
    [1,2,1],
    [1,7,200],
    [1,7,150]
],

[   # Create Claw Materials Quantity
    [1,20,5],
    [2,1,30,10],
    [1,3,20,30],
    [3,2,50,20],
    [4,2,80,25],
    [3,2,100,30],
    [4,2,40,35]
],

[   # Upgrade Claw Materials Quantity
    [1,1],
    [1,1],
    [1,2],
    [1,2],
    [1,3],
    [1,3],
    [1,4],
    [1,1],
    [1,5],
    [1,5],
    [1,2],
    [1,6],
    [1,6]
],

[   # Create Materials Materials Quantity
    [10],
    [5],
    [1,1]
]
]

costmesoKerningItem = [
[1000,7000,7000,7000,10000,15000,25000,30000,40000,50000,70000],# Create Gloves meso
[5000,7000,10000,12000,15000,20000,22000,25000,40000,50000,55000,60000,70000,80000],# Upgrade Gloves meso
[2000,3000,5000,15000,30000,40000,50000],   # Create Claw meso
[1000,2000,3000,5000,10000,15000,20000,25000,30000,30000,35000,40000,40000],# Upgrade Claw meso
[0,0,0] # Materials meso
]
# Kerning Item END



# Kerning Ore START
itemsKerningOre = [
[4011000,4011001,4011002,4011003,4011004,4011005,4011006],  # Mineral Refine
[4021000,4021001,4021002,4021003,4021004,4021005,4021006,4021007,4021008],  # Jewel Refine
[1472023,1472024,1472025],  # Claw Refine
]

costKerningOre = [
[   # Mineral Refine Material
    [4010000],
    [4010001],
    [4010002],
    [4010003],
    [4010004],
    [4010005],
    [4010006]
],

[   # Jewel Refine Material
    [4020000],
    [4020001],
    [4020002],
    [4020003],
    [4020004],
    [4020005],
    [4020006],
    [4020007],
    [4020008]
],

[   # Claw Upgrade Material
    [1472022,4011007,4021000,2012000],
    [1472022,4011007,4021005,2012002],
    [1472022,4011007,4021008,4000046]
]
]

costQKerningOre = [
[   # Mineral Refine Material Quantity
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10]
],

[   # Jewel Refine Material Quantity
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10]
],

[   # Claw Upgrade Material Quantity
    [1,1,8,10],
    [1,1,8,10],
    [1,1,3,5]
]
]

costmesoKerningOre = [
[300,300,300,500,500,500,800],  # Mineral Meso Cost
[500,500,500,500,500,500,500,1000,3000],# Jewel Meso Cost
[80000,80000,100000]# Claw Upgrade Meso Cost
]
# Kerning Ore END



# ElNath Item START
itemsElNathItem = [
[1072147,1072148,1072149,1072154,1072155,1072156,1072210,1072211,1072212],  # Warrior Shoes
[1072136,1072137,1072138,1072139,1072157,1072158,1072159,1072160,1072177,1072178,1072179],  # Magician Shoes
[1072144,1072145,1072146,1072164,1072165,1072166,1072167,1072182,1072183,1072184,1072185],  # Archer Shoes
[1072150,1072151,1072152,1072161,1072162,1072163,1072172,1072173,1072174],  # Thief Shoes
[1072306,1072309,1072312] # Pirate Shoes
]

costElNathItem = [
[   # Warrior Shoes Materials
    [4021008,4011007,4021005,4000030,4003000],  # x
    [4021008,4011007,4011005,4000030,4003000],
    [4021008,4011007,4021000,4000030,4003000],
    [4005000,4005002,4011002,4000048,4003000],
    [4005000,4005002,4011005,4000048,4003000],  # x
    [4005000,4005002,4021008,4000048,4003000],
    [4005000,4005002,4021000,4000030,4003000],
    [4005000,4005002,4021002,4000030,4003000],
    [4005000,4005002,4021008,4000030,4003000]   # x
],

[   # Magician Shoes Materials
    [4021009,4011006,4011005,4000030,4003000],
    [4021009,4011006,4021003,4000030,4003000],
    [4021009,4011006,4011003,4000030,4003000],
    [4021009,4011006,4021002,4000030,4003000],
    [4005001,4005003,4021002,4000051,4003000],
    [4005001,4005003,4021000,4000051,4003000],
    [4005001,4005003,4011003,4000051,4003000],
    [4005001,4005003,4011006,4000051,4003000],
    [4005001,4005003,4021003,4000030,4003000],
    [4005001,4005003,4021001,4000030,4003000],
    [4005001,4005003,4021008,4000030,4003000]
],

[   # Archer Shoes Materials
    [4011006,4021000,4021007,4000030,4003000],
    [4011006,4021005,4021007,4000030,4003000],
    [4011006,4021003,4021007,4000030,4003000],
    [4005002,4005000,4021005,4000055,4003000],
    [4005002,4005000,4021004,4000055,4003000],
    [4005002,4005000,4021003,4000055,4003000],
    [4005002,4005000,4021008,4000055,4003000],
    [4005002,4005000,4021002,4000030,4003000],
    [4005002,4005000,4021000,4000030,4003000],
    [4005002,4005000,4021003,4000030,4003000],
    [4005002,4021008,4000030,4003000]
],

[   # Thief Shoes Materials
    [4021007,4011007,4021000,4000030,4003000],
    [4021007,4011007,4011006,4000030,4003000],
    [4021007,4011007,4021008,4000030,4003000],
    [4005003,4005000,4021001,4000051,4003000],
    [4005003,4005002,4021005,4000051,4003000],
    [4005002,4005003,4021000,4000051,4003000],
    [4005000,4005003,4021003,4000030,4003000],
    [4005002,4005003,4021000,4000030,4003000],
    [4005003,4005002,4021008,4000030,4003000]
],

[   # Pirate Shoes Materials
    [4021008,4011007,4021005,4000030,4003000],
    [4005000,4005002,4011005,4000048,4003000],
    [4005000,4005002,4021008,4000030,4003000]
]
]

costQElNathItem = [
[   # Warrior Shoes Materials Quantity
    [1,1,8,80,55],  # x
    [1,1,8,80,55],
    [1,1,8,80,55],
    [1,3,5,100,55],
    [2,2,5,100,55], # x
    [3,1,1,100,55],
    [2,3,7,90,65],
    [3,2,7,90,65],
    [4,1,2,90,65]   # x
],

[   # Archer Shoes Materials Quantity
    [1,1,8,80,55],
    [1,1,8,80,55],
    [1,1,8,80,55],
    [1,3,5,100,55],
    [2,2,5,100,55],
    [3,1,1,100,55],
    [2,3,7,90,65],
    [3,2,7,90,65],
    [4,1,2,90,65]
],

[   # Magician Shoes Materials Quantity
    [5,8,1,75,50],
    [5,8,1,75,50],
    [5,8,1,75,50],
    [1,3,5,100,55],
    [2,2,5,100,55],
    [2,2,5,100,55],
    [3,1,1,100,55],
    [2,3,7,90,60],
    [3,2,7,90,60],
    [4,1,7,90,60],
    [5,2,90,60]
],

[   # Thief Shoes Materials Quantity
    [1,1,8,75,50],
    [1,1,5,75,50],
    [1,1,1,75,50],
    [1,3,5,100,55],
    [1,3,5,100,55],
    [1,3,5,100,55],
    [3,2,7,90,60],
    [3,2,7,90,60],
    [3,2,7,90,60]
],

[   # Pirate Shoes Materials Quantity
    [1,1,8,80,55],
    [2,2,5,100,55],
    [4,1,2,90,65]
]
]

costmesoElNathItem = [
[60000,60000,60000,70000,70000,70000,80000,80000,80000],
[60000,60000,60000,70000,70000,70000,70000,80000,80000,80000,80000],
[60000,60000,60000,60000,70000,70000,70000,70000,80000,80000,80000],
[60000,60000,60000,70000,70000,70000,80000,80000,80000],
[60000,70000,80000]
]
# ElNath Item END



# ElNath Refine START
itemsElNathRefine = [
[4011000,4011001,4011002,4011003,4011004,4011005,4011006],  # Mineral Refine
[4021000,4021001,4021002,4021003,4021004,4021005,4021006,4021007,4021008],  # Jewel Refine
[4011007,4021009],  # Rock Refine
[4005000,4005001,4005002,4005003,4005004],  # Crystal Refine
[4003001,4003001,4003000],   # Material Refine
[2060000,2061000,2060001,2061001,2060002,2061002]   # Create Arrows
]

nonEquipSuffixElNathRefine = ["with Tree Branches", "with Firewood", "(packs of 15)"]

costElNathRefine = [
[   # Mineral Refine Materials
    [4010000],
    [4010001],
    [4010002],
    [4010003],
    [4010004],
    [4010005],
    [4010006]
],

[   # Jewel Refine Materials
    [4020000],
    [4020001],
    [4020002],
    [4020003],
    [4020004],
    [4020005],
    [4020006],
    [4020007],
    [4020008]
],

[   # Rock Refine Materials
    [4011000,4011001,4011002,4011003,4011004,4011005,4011006],
    [4021000,4021001,4021002,4021003,4021004,4021005,4021006,4021007,4021008]
],

[   # Crystal Refine Materials
    [4004000],
    [4004001],
    [4004002],
    [4004003],
    [4004004]
],

[   # Material Refine Materials
    [4000003],
    [4000018],
    [4011000,4011001]
],

[   # Create Arrows Materials
    [4003001,4003004],
    [4003001,4003004],
    [4011000,4003001,4003004],
    [4011000,4003001,4003004],
    [4011001,4003001,4003005],
    [4011001,4003001,4003005]
]
]

costQElNathRefine = [
[   # Mineral Refine Material Quantity
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10]
],

[   # Jewel Refine Material Quantity
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10],
    [10]
],

[   # Rock Refine Material Quantity
    [1,1,1,1,1,1,1],
    [1,1,1,1,1,1,1,1,1]
],

[   # Crystal Refine Material Quantity
    [10],
    [10],
    [10],
    [10],
    [10]
],

[   # Refine Material Material Quantity
    [10],
    [5],
    [1,1]
],

[   # Create Arrow Material Quantity
    [1,1],
    [1,1],
    [1,3,10],
    [1,3,10],
    [1,5,15],
    [1,5,15]
]
]

costmesoElNathRefine = [
[300,300,300,500,500,500,800],  # Mineral Refine Meso Cost
[500,500,500,500,500,500,500,1000,3000], # Jewel Refine Meso Cost
[10000,15000],  # Rock Refine Meso Cost
[5000,5000,5000,5000,1000000],  # Crystal Refine Meso Cost
[0,0,0],# Material Refine Meso Cost
[0,0,0,0,0,0],  # Create Arrows Meso Cost
]
# ElNath Refine END



# Ludibrium Shoes START
itemsLudiShoes = [
[1072003,1072039,1072040,1072041,1072002,1072112,1072113,1072000,1072126,1072127,1072132,1072133,1072134,1072135],  # Warrior Shoes
[1072075,1072076,1072077,1072078,1072089,1072090,1072091,1072114,1072115,1072116,1072117,1072140,1072141,1072142,1072143,1072136,1072137,1072138,1072139],  # Magician Shoes
[1072079,1072080,1072081,1072082,1072083,1072101,1072102,1072103,1072118,1072119,1072120,1072121,1072122,1072123,1072124,1072125],  # Archer Shoes
[1072032,1072033,1072035,1072036,1072104,1072105,1072106,1072107,1072108,1072109,1072110,1072128,1072130,1072129,1072131],  # Thief Shoes
[1072294,1072297,1072300,1072303]   # Pirate Shoes
]

costLudiShoes = [
[   # Warrior Shoes
    [4021003,4011001,4000021,4003000],  # x
    [4011002,4011001,4000021,4003000],
    [4011004,4011001,4000021,4003000],
    [4021000,4011001,4000021,4003000],
    [4011001,4021004,4000021,4000030,4003000],  # x
    [4011002,4021004,4000021,4000030,4003000],
    [4021008,4021004,4000021,4000030,4003000],
    [4011003,4000021,4000030,4003000,4000103],
    [4011005,4021007,4000030,4003000,4000104],
    [4011002,4021007,4000030,4003000,4000105],  # x
    [4021008,4011001,4021003,4000030,4003000],
    [4021008,4011001,4011002,4000030,4003000],
    [4021008,4011001,4011005,4000030,4003000],
    [4021008,4011001,4011006,4000030,4003000]   # x
],

[   # Magician Shoes
    [4021000,4000021,4003000],
    [4021002,4000021,4003000],
    [4011004,4000021,4003000],
    [4021008,4000021,4003000],
    [4021001,4021006,4000021,4000030,4003000],
    [4021000,4021006,4000021,4000030,4003000],
    [4021008,4021006,4000021,4000030,4003000],
    [4021000,4000030,4000110,4003000],
    [4021005,4000030,4000111,4003000],
    [4011006,4021007,4000030,4000100,4003000],
    [4021008,4021007,4000030,4000112,4003000],
    [4021009,4011006,4021000,4000030,4003000],
    [4021009,4011006,4021005,4000030,4003000],
    [4021009,4011006,4021001,4000030,4003000],
    [4021009,4011006,4021003,4000030,4003000]
],

[   # Archer Shoes
    [4000021,4021000,4003000],
    [4000021,4021005,4003000],
    [4000021,4021003,4003000],
    [4000021,4021004,4003000],
    [4000021,4021006,4003000],
    [4021002,4021006,4000030,4000021,4003000],
    [4021003,4021006,4000030,4000021,4003000],
    [4021000,4021006,4000030,4000021,4003000],
    [4021000,4003000,4000030,4000106],
    [4021006,4003000,4000030,4000107],
    [4011003,4003000,4000030,4000108],
    [4021002,4003000,4000030,4000099],
    [4011001,4021006,4021008,4000030,4003000,4000033],
    [4011001,4021006,4021008,4000030,4003000,4000032],
    [4011001,4021006,4021008,4000030,4003000,4000041],
    [4011001,4021006,4021008,4000030,4003000,4000042]
],

[   # Thief Shoes
    [4011000,4000021,4003000],
    [4011001,4000021,4003000],
    [4011004,4000021,4003000],
    [4011006,4000021,4003000],
    [4021000,4021004,4000021,4000030,4003000],
    [4021003,4021004,4000021,4000030,4003000],
    [4021002,4021004,4000021,4000030,4003000],
    [4021000,4000030,4000113,4003000],
    [4021003,4000030,4000095,4003000],
    [4021006,4000030,4000096,4003000],
    [4021005,4000030,4000097,4003000],
    [4011007,4021005,4000030,4000114,4003000],
    [4011007,4021000,4000030,4000115,4003000],
    [4011007,4021003,4000030,4000109,4003000],
    [4011007,4021001,4000030,4000036,4003000]
],

[   # Pirate Shoes
    [4021003,4011001,4000021,4003000],
    [4011001,4021004,4000021,4000030,4003000],
    [4011002,4021007,4000030,4003000,4000105],
    [4021008,4011001,4011006,4000030,4003000]
]
]

costQLudiShoes = [
[   # Warrior Shoes
    [4,2,45,15],# x
    [4,2,45,15],
    [4,2,45,15],
    [4,2,45,15],
    [3,1,30,20,25], # x
    [3,1,30,20,25],
    [2,1,30,20,25],
    [4,100,40,30,100],
    [4,1,40,30,100],
    [4,1,40,30,100],# x
    [1,3,6,65,45],
    [1,3,6,65,45],
    [1,3,6,65,45],
    [1,3,6,65,45]   # x
],

[   # Magician Shoes
    [2,50,15],
    [2,50,15],
    [2,50,15],
    [1,50,15],
    [3,1,30,15,20],
    [3,1,30,15,20],
    [2,1,40,25,20],
    [4,40,100,25],
    [4,40,100,25],
    [2,1,40,100,25],
    [2,1,40,100,30],
    [1,3,3,60,40],
    [1,3,3,60,40],
    [1,3,3,60,40],
    [1,3,3,60,40]
],

[   # Archer Shoes
    [50,2,15],
    [50,2,15],
    [50,2,15],
    [50,2,15],
    [50,2,15],
    [3,1,15,30,20],
    [3,1,15,30,20],
    [3,1,15,30,20],
    [4,30,45,100],
    [4,30,45,100],
    [5,30,45,100],
    [5,30,45,100],
    [3,3,1,60,35,80],
    [3,3,1,60,35,150],
    [3,3,1,60,35,100],
    [3,3,1,60,35,250]
],

[   # Thief Shoes
    [3,50,15],
    [3,50,15],
    [2,50,15],
    [2,50,15],
    [3,1,30,15,20],
    [3,1,30,15,20],
    [3,1,30,15,20],
    [5,45,100,30],
    [4,45,100,30],
    [4,45,100,30],
    [4,45,100,30],
    [2,3,50,100,35],
    [2,3,50,100,35],
    [2,3,50,100,35],
    [2,3,50,80,35]
],

[   # Pirate Shoes
    [4,2,45,15],
    [3,1,30,20,25],
    [4,1,40,30,100],
    [1,3,6,65,45]
]
]

costmesoLudiShoes = [
[20000,20000,20000,20000,22000,22000,25000,38000,38000,38000,50000,50000,50000,50000],  # Warrior Shoes
[19000,19000,19000,19000,19000,19000,20000,20000,20000,32000,32000,40000,40000,50000,50000,50000,50000],# Magician Shoes
[18000,18000,18000,18000,20000,20000,22000,30000,30000,35000,40000,50000,50000,50000,50000],# Archer Shoes
[19000,19000,19000,21000,20000,20000,20000,40000,32000,35000,35000,50000,50000,50000,50000],# Thief Shoes
[20000,22000,38000,50000]   # Pirate Shoes
]
# Ludibrium Shoes END



# Ludirbium Gloves START
itemsLudiGloves = [
[1082007,1082008,1082023,1082009],  # Warrior
[1082051,1082054,1082062,1082081],  # Magician
[1082048,1082068,1082071,1082084],  # Archer
[1082042,1082046,1082075,1082065],  # Thief
[1082189,1082192,1082195,1082198]   # Pirate
]

costLudiGloves = [
[   # Warrior
    [4011000,4011001,4003000],
    [4000021,4011001,4003000],
    [4000021,4011001,4003000],
    [4011001,4021007,4000030,4003000]
],

[   # Magician
    [4000021,4021006,4021000],
    [4000021,4011006,4011001,4021000],
    [4000021,4021000,4021006,4003000],
    [4021000,4011006,4000030,4003000]
],

[   # Archer
    [4000021,4011006,4021001],
    [4011000,4011001,4000021,4003000],
    [4011001,4021000,4021002,4000021,4003000],
    [4011004,4011006,4021002,4000030,4003000]
],

[   # Thief
    [4011001,4000021,4003000],
    [4011001,4011000,4000021,4003000],
    [4021000,4000101,4000021,4003000],
    [4021005,4021008,4000030,4003000]
],

[   # Pirate
    [4011000,4011001,4003000],
    [4000021,4011001,4003000],
    [4000021,4011001,4003000],
    [4011001,4021007,4000030,4003000]
]
]

costQLudiGloves = [
[   # Warrior
    [3,2,15],
    [30,4,15],
    [50,5,40],
    [3,2,30,45]
],

[   # Magician
    [60,1,2],
    [70,1,3,2],
    [80,3,3,30],
    [3,2,35,40]
],

[   # Archer
    [50,2,1],
    [1,3,60,15],
    [3,1,3,80,25],
    [3,1,2,40,35]
],

[   # Thief
    [2,50,10],
    [3,1,60,15],
    [3,100,80,30],
    [3,1,40,30]
],

[   # Pirate
    [3,2,15],
    [30,4,15],
    [50,5,40],
    [3,2,30,45]
]
]

costmesoLudiGloves = [
[18000,27000,36000,45000],  # Warrior
[22500,27000,36000,45000],  # Magician
[18000,27000,36000,45000],  # Archer
[22500,27000,36000,45000],  # Thief
[18000,27000,36000,45000],  # Pirate
]
# Ludibrium Gloves END



# Ellinia Item START
itemsElliniaItem = [
[1082019,1082020,1082026,1082051,1082054,1082062,1082081,1082086], # Create Gloves
[1082021,1082022,1082027,1082028,1082052,1082053,1082055,1082056,1082063,1082064,1082082,1082080,1082087,1082088],  # Upgrade Gloves
[1002065,1002013],  # Upgrade Hat
[1372005,1372006,1372002,1372004,1372003,1372001,1372000,1372007],  # Create Wand
[1382000,1382003,1382005,1382004,1382002,1382001]   # Create Staff
]

costElliniaItem = [
[   # Create Gloves
    [4000021],
    [4000021,4011001],
    [4000021,4011006],
    [4000021,4021006,4021000],
    [4000021,4011006,4011001,4021000],
    [4000021,4021000,4021006,4003000],
    [4021000,4011006,4000030,4003000],
    [4011007,4011001,4021007,4000030,4003000]
],

[   # Upgrade Gloves
    [1082020,4011001],
    [1082020,4021001],
    [1082026,4021000],
    [1082026,4021008],
    [1082051,4021005],
    [1082051,4021008],
    [1082054,4021005],
    [1082054,4021008],
    [1082062,4021002],
    [1082062,4021008],
    [1082081,4021002],
    [1082081,4021008],
    [1082086,4011004,4011006],
    [1082086,4021008,4011006]
],

[   # Hat Upgrade
    [1002064,4011001],
    [1002064,4011006]
],

[   # Create Wand
    [4003001],
    [4003001,4000001],
    [4011001,4000009,4003000],
    [4011002,4003002,4003000],
    [4011002,4021002,4003000],
    [4021006,4011002,4011001,4003000],
    [4021006,4021005,4021007,4003003,4003000],
    [4011006,4021003,4021007,4021002,4003002,4003000]
],

[   # Create Staff
    [4003001],
    [4021005,4011001,4003000],
    [4021003,4011001,4003000],
    [4003001,4011001,4003000],
    [4021006,4021001,4011001,4003000],
    [4011001,4021006,4021001,4021005,4003000,4000010,4003003]
]
]

costQElliniaItem = [
[   # Create Gloves
    [15],
    [30,1],
    [50,2],
    [60,1,2],
    [70,1,3,2],
    [80,3,3,30],
    [3,2,35,40],
    [1,8,1,50,50]
],

[   # Upgrade Gloves
    [1,1],
    [1,2],
    [1,3],
    [1,1],
    [1,3],
    [1,1],
    [1,3],
    [1,1],
    [1,4],
    [1,2],
    [1,5],
    [1,3],
    [1,3,5],
    [1,2,3]
],

[   # Upgrade Hat
    [1,3],
    [1,3]
],

[   # Create Wand
    [5],
    [10,50],
    [1,30,5],
    [2,1,10],
    [3,1,10],
    [5,3,1,15],
    [5,5,1,1,20],
    [4,3,2,1,1,30]
],

[   # Create Staff
    [5],
    [1,1,5],
    [1,1,5],
    [50,1,10],
    [2,1,1,15],
    [8,5,5,5,30,50,1]
]
]

costmesoElliniaItem = [
[7000,15000,20000,25000,30000,40000,50000,70000],   # Create Gloves
[20000,25000,30000,40000,35000,40000,40000,45000,45000,50000,55000,60000,70000,80000],  # Upgrade Gloves
[40000,50000],  # Upgrade Hat
[1000,3000,5000,12000,30000,60000,120000,200000],   # Create Wand
[2000,2000,2000,5000,12000,180000]  # Create Staff
]
# Ellinia Item END



# Ariant Refine START
itemsAriantRefine = [
[4011000,4011001,4011002,4011003,4011004,4011005,4011006,4011008],  # Mineral Refine
[4021000,4021001,4021002,4021003,4021004,4021005,4021006,4021007,4021008],  # Jewel Refine
[4005000,4005001,4005002,4005003],  # Crystal Refine
]

costAriantRefine = [
[4010000,4010001,4010002,4010003,4010004,4010005,4010006,4010007],  # Mineral Refine
[4020000,4020001,4020002,4020003,4020004,4020005,4020006,4020007,4020008],  # Jewel Refine
[4004000,4004001,4004002,4004003]   # Crystal Refine
]

costQAriantRefine = [
[10,10,10,10,10,10,10,10],  # Mineral Refine
[10,10,10,10,10,10,10,10,10],   # Jewel Refine
[10,10,10,10]   # Crystal Refine
]

costmesoAriantRefine = [
[270,270,270,450,450,450,720,270],  # Mineral Refine
[450,450,450,450,450,450,450,900,2700], # Jewel Refine
[4500,4500,4500,4500]   # Crystal Refine
]
# Ariant Refine END



if parentID == 1012002:
    # Vicious | Item Maker
    selection1 = sm.sendNext("Hello. I am Vicious, retired Sniper. However, I used to be the top student of Athena Pierce. Though I no longer hunt, I can make some archer items that will be useful for you...\r\n#b"
                    "#L0#Create a bow#l \r\n"
                    "#L1#Create a crossbow#l \r\n"
                    "#L2#Create gloves#l \r\n"
                    "#L3#Upgrade gloves#l \r\n"
                    "#L4#Create materials#l \r\n"
                    "#L5#Create arrows#l \r\n"
                    )


    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsHene[selection1]):
        if selection1 == 4:
            listStr += "\r\n#L" + str(i) + "##z" + str(itemsHene[selection1][i]) + "# " + str(nonEquipSuffixHene[i])
        else:
            listStr += "\r\n#L" + str(i) + "##z" + str(itemsHene[selection1][i]) + "#"
        i += 1
    selection2 = sm.sendNext(listStr)


    if selection1 == 4 or selection1 == 5:
        materialStr = "You want #z" + str(itemsHene[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
    else:
        materialStr = "You want a #z" + str(itemsHene[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costHene[selection1][selection2]):
        materialStr += "\r\n" + str(costQHene[selection1][selection2][i]) + "x #z" + str(costHene[selection1][selection2][i]) + "#"
        i += 1
    if costmesoHene[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoHene[selection1][selection2]) + " mesos"
    if selection1 == 4 or selection1 == 5:
        materialStr += "\r\n\r\nHow many do you want me to make?"
        selection3 = sm.sendAskNumber(materialStr, 1, 1, 50)
    else:
        response = sm.sendAskYesNo(materialStr)


    if response:
        multiplier = 1
        if selection1 == 4 or selection1 == 5:
            multiplier = selection3

        if sm.getMesos() < costmesoHene[selection1][selection2]:
            sm.sendSayOkay("I'm afraid you cannot afford my services.")
            sm.dispose()

        i = 0
        while i < len(costHene[selection1][selection2]):
            complete = sm.hasItem(costHene[selection1][selection2][i], (costQHene[selection1][selection2][i] * multiplier))
            i += 1
            if complete == False:
                break

        if complete == False:
            sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
        else:
            if sm.canHold(itemsHene[selection1][selection2]) == False:
                sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                sm.dispose()
            else:
                i = 0
                while i < len(costHene[selection1][selection2]):
                    sm.consumeItem(costHene[selection1][selection2][i], (costQHene[selection1][selection2][i] * multiplier))
                    i += 1
                if costmesoHene[selection1][selection2] > 0:
                    sm.giveMesos(-(costmesoHene[selection1][selection2] * multiplier))

                if itemsHene[selection1][selection2] >= 2060000 and itemsHene[selection1][selection2] <= 2060002:
                    multiplier2 = 1000 - (itemsHene[selection1][selection2] - 2060000) * 100
                elif itemsHene[selection1][selection2] >= 2061000 and itemsHene[selection1][selection2] <= 2061002:
                    multiplier2 = 1000 - (itemsHene[selection1][selection2] - 2061000) * 100
                elif itemsHene[selection1][selection2] == 4003000:
                    multiplier2 = 15
                else:
                    multiplier2 = 1

                sm.giveItem(itemsHene[selection1][selection2], (multiplier * multiplier2))
                sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
    else:
        sm.sendSayOkay("Let me know when you are ready to create something.")
        sm.dispose()



elif parentID == 1022003:

# Mr. Thunder | Repair Durability
    selection1 = sm.sendNext("hm? Who might you be? Oh, you've heard about my forging skills? In that case, I'd be glad to process some of your ores... for a fee.#b"
                "\r\n#L0#Refine a mineral ore#l"
                "\r\n#L1#Refine a jewel ore#l"
                "\r\n#L2#Upgrade a helmet#l"
                "\r\n#L3#Upgrade a shield#l"
                )


    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsPerion[selection1]):
        listStr += "\r\n#L" + str(i) + "##z" + str(itemsPerion[selection1][i]) + "#"
        i += 1
    selection2 = sm.sendNext(listStr)

    if selection1 == 0 or selection1 == 1:
        materialStr = "You want #z" + str(itemsPerion[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
    else:
        materialStr = "You want a #z" + str(itemsPerion[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costPerion[selection1][selection2]):
        materialStr += "\r\n" + str(costQPerion[selection1][selection2][i]) + "x #z" + str(costPerion[selection1][selection2][i]) + "#"
        i += 1
    if costmesoPerion[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoPerion[selection1][selection2]) + " mesos"
    if selection1 == 0 or selection1 == 1:
        materialStr += "\r\n\r\nHow many do you want me to make?"
        selection3 = sm.sendAskNumber(materialStr, 1, 1, 50)
    else:
        response = sm.sendAskYesNo(materialStr)


    if response:
        multiplier = 1
        if selection1 == 0 or selection1 == 1:
            multiplier = selection3

        if sm.getMesos() < costmesoPerion[selection1][selection2]:
            sm.sendSayOkay("I'm afraid you cannot afford my services.")
            sm.dispose()

        i = 0
        while i < len(costPerion[selection1][selection2]):
            complete = sm.hasItem(costPerion[selection1][selection2][i], (costQPerion[selection1][selection2][i] * multiplier))
            i += 1
            if complete == False:
                break

        if complete == False:
            sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
        else:
            if sm.canHold(itemsPerion[selection1][selection2]) == False:
                sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                sm.dispose()
            else:
                i = 0
                while i < len(costPerion[selection1][selection2]):
                    sm.consumeItem(costPerion[selection1][selection2][i], (costQPerion[selection1][selection2][i] * multiplier))
                    i += 1
                if costmesoPerion[selection1][selection2] > 0:
                    sm.giveMesos(-(costmesoPerion[selection1][selection2] * multiplier))


                sm.giveItem(itemsPerion[selection1][selection2], multiplier)
                sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
    else:
        sm.sendSayOkay("Let me know when you are ready to create something.")
    sm.dispose()



elif parentID == 1052002:

# JM From Tha Streetz | Item Creator
    selection1 = sm.sendNext("Pst... If you have the right goods, I can turn it into something nice...#b"
                "\r\n#L0#Create a glove#l"
                "\r\n#L1#Upgrade a glove#l"
                "\r\n#L2#Create a claw#l"
                "\r\n#L3#Upgrade a claw#l"
                "\r\n#L4#Create materials#l"
                )


    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsKerningItem[selection1]):
        if selection1 == 4:
            listStr += "\r\n#L" + str(i) + "##z" + str(itemsKerningItem[selection1][i]) + "# " + str(nonEquipSuffixKerningItem[i])
        else:
            listStr += "\r\n#L" + str(i) + "##z" + str(itemsKerningItem[selection1][i]) + "#"
        i += 1
    selection2 = sm.sendNext(listStr)

    if selection1 == 4:
        materialStr = "You want #z" + str(itemsKerningItem[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
    else:
        materialStr = "You want a #z" + str(itemsKerningItem[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costKerningItem[selection1][selection2]):
        materialStr += "\r\n" + str(costQKerningItem[selection1][selection2][i]) + "x #z" + str(costKerningItem[selection1][selection2][i]) + "#"
        i += 1
    if costmesoKerningItem[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoKerningItem[selection1][selection2]) + " mesos"
    if selection1 == 4:
        materialStr += "\r\n\r\nHow many do you want me to make?"
        selection3 = sm.sendAskNumber(materialStr, 1, 1, 50)
    else:
        response = sm.sendAskYesNo(materialStr)


    if response:
        multiplier = 1
        if selection1 == 4:
            multiplier = selection3

        if sm.getMesos() < costmesoKerningItem[selection1][selection2]:
            sm.sendSayOkay("Where's the mesos, man?!")
            sm.dispose()

        i = 0
        while i < len(costKerningItem[selection1][selection2]):
            complete = sm.hasItem(costKerningItem[selection1][selection2][i], (costQKerningItem[selection1][selection2][i] * multiplier))
            i += 1
            if complete == False:
                break

        if complete == False:
            sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
        else:
            if sm.canHold(itemsKerningItem[selection1][selection2]) == False:
                sm.sendSayOkay("Make sure you have room in your inventory.")
                sm.dispose()
            else:
                i = 0
                while i < len(costKerningItem[selection1][selection2]):
                    sm.consumeItem(costKerningItem[selection1][selection2][i], (costQKerningItem[selection1][selection2][i] * multiplier))
                    i += 1
                if costmesoKerningItem[selection1][selection2] > 0:
                    sm.giveMesos(-(costmesoKerningItem[selection1][selection2] * multiplier))

                multiplier2 = 1
                if itemsKerningItem[selection1][selection2] == 4003000:
                    multiplier2 = 15

                sm.giveItem(itemsKerningItem[selection1][selection2], (multiplier * multiplier2))
                sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
    else:
        sm.sendSayOkay("Let me know when you are ready to create something.")
    sm.dispose()



elif parentID == 1052003:

# Chris | Ore Refiner
    selection1 = sm.sendNext("Yes, I do own this forge. If you're willing to pay, I can offer you some of my services.#b"
                "\r\n#L0#Refine a mineral ore#l"
                "\r\n#L1#Refine a jewel ore#l"
                "\r\n#L2#Upgrade a claw#l"
                )


    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsKerningOre[selection1]):
        listStr += "\r\n#L" + str(i) + "##z" + str(itemsKerningOre[selection1][i]) + "#"
        i += 1
    selection2 = sm.sendNext(listStr)

    if selection1 == 0 or selection1 == 1:
        materialStr = "You want #z" + str(itemsKerningOre[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
    else:
        materialStr = "You want a #z" + str(itemsKerningOre[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costKerningOre[selection1][selection2]):
        materialStr += "\r\n" + str(costQKerningOre[selection1][selection2][i]) + "x #z" + str(costKerningOre[selection1][selection2][i]) + "#"
        i += 1
    if costmesoKerningOre[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoKerningOre[selection1][selection2]) + " mesos"
    if selection1 == 0 or selection1 == 1:
        materialStr += "\r\n\r\nHow many do you want me to make?"
        selection3 = sm.sendAskNumber(materialStr, 1, 1, 50)
    else:
        response = sm.sendAskYesNo(materialStr)


    if response:
        multiplier = 1
        if selection1 == 0 or selection1 == 1:
            multiplier = selection3

        if sm.getMesos() < costmesoKerningOre[selection1][selection2]:
            sm.sendSayOkay("I'm afraid you cannot afford my services.")
            sm.dispose()

        i = 0
        while i < len(costKerningOre[selection1][selection2]):
            complete = sm.hasItem(costKerningOre[selection1][selection2][i], (costQKerningOre[selection1][selection2][i] * multiplier))
            i += 1
            if complete == False:
                break

        if complete == False:
            sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
        else:
            if sm.canHold(itemsKerningOre[selection1][selection2]) == False:
                sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                sm.dispose()
            else:
                i = 0
                while i < len(costKerningOre[selection1][selection2]):
                    sm.consumeItem(costKerningOre[selection1][selection2][i], (costQKerningOre[selection1][selection2][i] * multiplier))
                    i += 1
                if costmesoKerningOre[selection1][selection2] > 0:
                    sm.giveMesos(-(costmesoKerningOre[selection1][selection2] * multiplier))


                sm.giveItem(itemsKerningOre[selection1][selection2], multiplier)
                sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
    else:
        sm.sendSayOkay("Let me know when you are ready to create something.")
    sm.dispose()



elif parentID == 2020002:

# Gordon | Shoemaker
    selection1 = sm.sendNext("Hello there. El Nath winters are incredibly cold, you're going to need a warm pair of shoes to survive.#b"
                "\r\n#L0#Create warrior shoes#l"
                "\r\n#L1#Create magician shoes#l"
                "\r\n#L2#Create archer shoes#l"
                "\r\n#L3#Create thief shoes#l"
                "\r\n#L4#Create pirate shoes#l"
                )


    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsElNathItem[selection1]):
        listStr += "\r\n#L" + str(i) + "##z" + str(itemsElNathItem[selection1][i]) + "#"
        i += 1
    selection2 = sm.sendNext(listStr)

    materialStr = "You want a #z" + str(itemsElNathItem[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costElNathItem[selection1][selection2]):
        materialStr += "\r\n" + str(costQElNathItem[selection1][selection2][i]) + "x #z" + str(costElNathItem[selection1][selection2][i]) + "#"
        i += 1
    if costmesoElNathItem[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoElNathItem[selection1][selection2]) + " mesos"
    response = sm.sendAskYesNo(materialStr)


    if response:
        multiplier = 1

        if sm.getMesos() < costmesoElNathItem[selection1][selection2]:
            sm.sendSayOkay("I'm afraid you cannot afford my services.")
            sm.dispose()

        i = 0
        while i < len(costElNathItem[selection1][selection2]):
            complete = sm.hasItem(costElNathItem[selection1][selection2][i], (costQElNathItem[selection1][selection2][i] * multiplier))
            i += 1
            if complete == False:
                break

        if complete == False:
            sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
        else:
            if sm.canHold(itemsElNathItem[selection1][selection2]) == False:
                sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                sm.dispose()
            else:
                i = 0
                while i < len(costElNathItem[selection1][selection2]):
                    sm.consumeItem(costElNathItem[selection1][selection2][i], (costQElNathItem[selection1][selection2][i] * multiplier))
                    i += 1
                if costmesoElNathItem[selection1][selection2] > 0:
                    sm.giveMesos(-(costmesoElNathItem[selection1][selection2] * multiplier))


                sm.giveItem(itemsElNathItem[selection1][selection2], multiplier)
                sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
    else:
        sm.sendSayOkay("Let me know when you are ready to create something.")
        sm.dispose()



elif parentID == 2020000 or parentID == 2040016:

# Vogen | Refining Expert  &  Pi | Refining Expert
    selection1 = sm.sendNext("Hm? Who might you be? Oh, you've heard about my forging skills? In that case, I'd be glad to process some of your ores... for a fee.#b\r\n#b"
                "#L0#Refine a mineral ore#l \r\n"
                "#L1#Refine a jewel ore#l \r\n"
                "#L2#Refine a rare jewel ore#l \r\n"
                "#L3#Refine a crystal ore#l \r\n"
                "#L4#Create materials#l \r\n"
                "#L5#Create arrows#l \r\n"
                )


    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsElNathRefine[selection1]):
        if selection1 == 4:
            listStr += "\r\n#L" + str(i) + "##z" + str(itemsElNathRefine[selection1][i]) + "# " + str(nonEquipSuffixElNathRefine[i])
        else:
            listStr += "\r\n#L" + str(i) + "##z" + str(itemsElNathRefine[selection1][i]) + "#"
        i += 1
    selection2 = sm.sendNext(listStr)

    if selection1 == 4 or selection1 == 5:
        materialStr = "You want #z" + str(itemsElNathRefine[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
    else:
        materialStr = "You want a #z" + str(itemsElNathRefine[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costElNathRefine[selection1][selection2]):
        materialStr += "\r\n" + str(costQElNathRefine[selection1][selection2][i]) + "x #z" + str(costElNathRefine[selection1][selection2][i]) + "#"
        i += 1
    if costmesoElNathRefine[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoElNathRefine[selection1][selection2]) + " mesos"
    materialStr += "\r\n\r\nHow many do you want me to make?"
    selection3 = sm.sendAskNumber(materialStr, 1, 1, 50)


    multiplier = selection3

    if sm.getMesos() < costmesoElNathRefine[selection1][selection2]:
        sm.sendSayOkay("I'm afraid you cannot afford my services.")
        sm.dispose()

    i = 0
    while i < len(costElNathRefine[selection1][selection2]):
        complete = sm.hasItem(costElNathRefine[selection1][selection2][i], (costQElNathRefine[selection1][selection2][i] * multiplier))
        i += 1
        if complete == False:
            break

    if complete == False:
        sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
    else:
        if sm.canHold(itemsElNathRefine[selection1][selection2]) == False:
            sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
            sm.dispose()
        else:
            i = 0
            while i < len(costElNathRefine[selection1][selection2]):
                sm.consumeItem(costElNathRefine[selection1][selection2][i], (costQElNathRefine[selection1][selection2][i] * multiplier))
                i += 1
            if costmesoElNathRefine[selection1][selection2] > 0:
                sm.giveMesos(-(costmesoElNathRefine[selection1][selection2] * multiplier))

            if itemsElNathRefine[selection1][selection2] >= 2060000 and itemsElNathRefine[selection1][selection2] <= 2060002:
                multiplier2 = 1000 - (itemsElNathRefine[selection1][selection2] - 2060000) * 100
            elif itemsElNathRefine[selection1][selection2] >= 2061000 and itemsElNathRefine[selection1][selection2] <= 2061002:
                multiplier2 = 1000 - (itemsElNathRefine[selection1][selection2] - 2061000) * 100
            elif itemsElNathRefine[selection1][selection2] == 4003000:
                multiplier2 = 15
            else:
                multiplier2 = 1

            sm.giveItem(itemsElNathRefine[selection1][selection2], (multiplier * multiplier2))
            sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")



elif parentID == 2040021:

# Tara | Shoe maker
    selection1 = sm.sendNext("Hello, and welcome to the Ludibrium Shoe Store. How can I help you today? #b"
                "\r\n#L0#Create warrior shoes#l"
                "\r\n#L1#Create magician shoes#l"
                "\r\n#L2#Create archer shoes#l"
                "\r\n#L3#Create thief shoes#l"
                "\r\n#L4#Create pirate shoes#l"
                )


    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsLudiShoes[selection1]):
        listStr += "\r\n#L" + str(i) + "##z" + str(itemsLudiShoes[selection1][i]) + "#"
        i += 1
    selection2 = sm.sendNext(listStr)

    materialStr = "You want a #z" + str(itemsLudiShoes[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costLudiShoes[selection1][selection2]):
        materialStr += "\r\n" + str(costQLudiShoes[selection1][selection2][i]) + "x #z" + str(costLudiShoes[selection1][selection2][i]) + "#"
        i += 1
    if costmesoLudiShoes[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoLudiShoes[selection1][selection2]) + " mesos"
    response = sm.sendAskYesNo(materialStr)


    if response:
        multiplier = 1

        if sm.getMesos() < costmesoLudiShoes[selection1][selection2]:
            sm.sendSayOkay("I'm afraid you cannot afford my services.")
            sm.dispose()

        i = 0
        while i < len(costLudiShoes[selection1][selection2]):
            complete = sm.hasItem(costLudiShoes[selection1][selection2][i], (costQLudiShoes[selection1][selection2][i] * multiplier))
            i += 1
            if complete == False:
                break

        if complete == False:
            sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
        else:
            if sm.canHold(itemsLudiShoes[selection1][selection2]) == False:
                sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                sm.dispose()
            else:
                i = 0
                while i < len(costLudiShoes[selection1][selection2]):
                    sm.consumeItem(costLudiShoes[selection1][selection2][i], (costQLudiShoes[selection1][selection2][i] * multiplier))
                    i += 1
                if costmesoLudiShoes[selection1][selection2] > 0:
                    sm.giveMesos(-(costmesoLudiShoes[selection1][selection2] * multiplier))


                sm.giveItem(itemsLudiShoes[selection1][selection2], multiplier)
                sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
    else:
        sm.sendSayOkay("Let me know when you are ready to create something.")
    sm.dispose()



elif parentID == 2040020:

# Sarah | Glove maker
    selection1 = sm.sendNext("Hello, and welcome to the Ludibrium Glove Store. How can I help you today?#b"
                "\r\n#L0#Create warrior gloves#l"
                "\r\n#L1#Create magician gloves#l"
                "\r\n#L2#Create archer gloves#l"
                "\r\n#L3#Create thief gloves#l"
                "\r\n#L4#Create pirate gloves#l"
                )


    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsLudiGloves[selection1]):
        listStr += "\r\n#L" + str(i) + "##z" + str(itemsLudiGloves[selection1][i]) + "#"
        i += 1
    selection2 = sm.sendNext(listStr)

    materialStr = "You want a #z" + str(itemsLudiGloves[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costLudiGloves[selection1][selection2]):
        materialStr += "\r\n" + str(costQLudiGloves[selection1][selection2][i]) + "x #z" + str(costLudiGloves[selection1][selection2][i]) + "#"
        i += 1
    if costmesoLudiGloves[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoLudiGloves[selection1][selection2]) + " mesos"
    response = sm.sendAskYesNo(materialStr)


    if response:
        multiplier = 1

        if sm.getMesos() < costmesoLudiGloves[selection1][selection2]:
            sm.sendSayOkay("I'm afraid you cannot afford my services.")
            sm.dispose()

        i = 0
        while i < len(costLudiGloves[selection1][selection2]):
            complete = sm.hasItem(costLudiGloves[selection1][selection2][i], (costQLudiGloves[selection1][selection2][i] * multiplier))
            i += 1
            if complete == False:
                break

        if complete == False:
            sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
        else:
            if sm.canHold(itemsLudiGloves[selection1][selection2]) == False:
                sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                sm.dispose()
            else:
                i = 0
                while i < len(costLudiGloves[selection1][selection2]):
                    sm.consumeItem(costLudiGloves[selection1][selection2][i], (costQLudiGloves[selection1][selection2][i] * multiplier))
                    i += 1
                if costmesoLudiGloves[selection1][selection2] > 0:
                    sm.giveMesos(-(costmesoLudiGloves[selection1][selection2] * multiplier))


                sm.giveItem(itemsLudiGloves[selection1][selection2], multiplier)
                sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
    else:
        sm.sendSayOkay("Let me know when you are ready to create something.")
    sm.dispose()



elif parentID == 1032002:

# Francois | Item Creator
    selection1 = sm.sendNext("Welcome to my eco-safe refining operation! What would you like today?#b"
                "\r\n#L0#Create gloves#l"
                "\r\n#L1#Upgrade gloves#l"
                "\r\n#L2#Upgrade a hat#l"
                "\r\n#L3#Create a wand#l"
                "\r\n#L4#Create a staff#l"
                )

    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsElliniaItem[selection1]):
        listStr += "\r\n#L" + str(i) + "##z" + str(itemsElliniaItem[selection1][i]) + "#"
        i += 1
    sm.sendNext(listStr)

    selection2 = answer
    materialStr = "You want a #z" + str(itemsElliniaItem[selection1][selection2]) + "#? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costElliniaItem[selection1][selection2]):
        materialStr += "\r\n" + str(costQElliniaItem[selection1][selection2][i]) + "x #z" + str(costElliniaItem[selection1][selection2][i]) + "#"
        i += 1
    if costmesoElliniaItem[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoElliniaItem[selection1][selection2]) + " mesos"
    response = sm.sendAskYesNo(materialStr)

    if response:
        multiplier = 1

        if sm.getMesos() < costmesoElliniaItem[selection1][selection2]:
            sm.sendSayOkay("I'm afraid you cannot afford my services.")
            sm.dispose()

        i = 0
        while i < len(costElliniaItem[selection1][selection2]):
            complete = sm.hasItem(costElliniaItem[selection1][selection2][i], (costQElliniaItem[selection1][selection2][i] * multiplier))
            i += 1
            if complete == False:
                break

        if complete == False:
            sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
        else:
            if sm.canHold(itemsElliniaItem[selection1][selection2]) == False:
                sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
                sm.dispose()
            else:
                i = 0
                while i < len(costElliniaItem[selection1][selection2]):
                    sm.consumeItem(costElliniaItem[selection1][selection2][i], (costQElliniaItem[selection1][selection2][i] * multiplier))
                    i += 1
                if costmesoElliniaItem[selection1][selection2] > 0:
                    sm.giveMesos(-(costmesoElliniaItem[selection1][selection2] * multiplier))


                sm.giveItem(itemsElliniaItem[selection1][selection2], multiplier)
                sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")
    else:
        sm.sendSayOkay("Let me know when you are ready to create something.")
    sm.dispose()



elif parentID == 2100001:

# Muhammad | Jewel Refiner
    selection1 = sm.sendNext("Are you here to refine the ores of a mineral or a jewel? It doesn't matter how many ores you have, if you don't have them refined by a master like me, then they won't see the light of day. What do you think, do you want to refine them right now?\r\n#b"
                "#L0#Refine a mineral ore#l \r\n"
                "#L1#Refine a jewel ore#l \r\n"
                "#L2#Refine a crystal ore#l \r\n"
                )

    listStr = "What item would you like to make? #b"
    i = 0
    while i < len(itemsElNathRefine[selection1]):
        if selection1 == 4:
            listStr += "\r\n#L" + str(i) + "##z" + str(itemsElNathRefine[selection1][i]) + "# " + str(nonEquipSuffixElNathRefine[i])
        else:
            listStr += "\r\n#L" + str(i) + "##z" + str(itemsElNathRefine[selection1][i]) + "#"
        i += 1
    selection2 = sm.sendNext(listStr)

    materialStr = "You want #z" + str(itemsElNathRefine[selection1][selection2]) + "#s? \r\nIn that case, I'm going to need specific items from you in order to make it."
    i = 0
    while i < len(costElNathRefine[selection1][selection2]):
        materialStr += "\r\n" + str(costQElNathRefine[selection1][selection2][i]) + "x #z" + str(costElNathRefine[selection1][selection2][i]) + "#"
        i += 1
    if costmesoElNathRefine[selection1][selection2] > 0:
        materialStr += "\r\n#i4031138#" + str(costmesoElNathRefine[selection1][selection2]) + " mesos"
    materialStr += "\r\n\r\nHow many do you want me to make?"
    selection3 = sm.sendAskNumber(materialStr, 1, 1, 50)


    multiplier = selection3
    if sm.getMesos() < costmesoElNathRefine[selection1][selection2]:
        sm.sendSayOkay("I'm afraid you cannot afford my services.")
        sm.dispose()

    i = 0
    while i < len(costElNathRefine[selection1][selection2]):
        complete = sm.hasItem(costElNathRefine[selection1][selection2][i], (costQElNathRefine[selection1][selection2][i] * multiplier))
        i += 1
        if complete == False:
            break

    if complete == False:
        sm.sendSayOkay("Surely you, of all people, would understand the value of having quality items? I can't do that without the items I require.")
    else:
        if sm.canHold(itemsElNathRefine[selection1][selection2]) == False:
            sm.sendSayOkay("Please make sure you have room in your inventory, and talk to me again.")
            sm.dispose()
        else:
            i = 0
            while i < len(costElNathRefine[selection1][selection2]):
                sm.consumeItem(costElNathRefine[selection1][selection2][i], (costQElNathRefine[selection1][selection2][i] * multiplier))
                i += 1
            if costmesoElNathRefine[selection1][selection2] > 0:
                sm.giveMesos(-(costmesoElNathRefine[selection1][selection2] * multiplier))

            sm.giveItem(itemsElNathRefine[selection1][selection2], multiplier)
            sm.sendSayOkay("A perfect item, as usual. Come and see me if you need anything else.")


else:
    sm.sendSayOkay("I'm an uncoded NPC, I'm lost. \r\nWhere am I?\r\nI believe my ID is "+str(parentID))
