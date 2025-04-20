drop table if exists equip_drops;

create table equip_drops (
	id int,
    job varchar(255),
    level int,
	primary key (id, job)
);

insert into equip_drops (id, job, level) values 

# Bowman
# Bow
(1452002, "BOWMAN", 10), # War Bow - Lv 10
(1452003, "BOWMAN", 10), # Composite Bow - Lv 15
(1452001, "BOWMAN", 20), # Hunter's Bow - Lv 20
(1452005, "BOWMAN", 30), # Ryden - Lv 30
(1452007, "BOWMAN", 40), # Vaulter 2000 - Lv 40
(1452008, "BOWMAN", 50), # Olympus - Lv 50
(1452004, "BOWMAN", 60), # Asianic Bow - Lv 60
(1452009, "BOWMAN", 70), # Red Hinkel - Lv 70
(1452010, "BOWMAN", 70), # Blue Hinkel - Lv 70
(1452011, "BOWMAN", 70), # Golden Hinkel - Lv 70
(1452012, "BOWMAN", 80), # Marine Arund - Lv 80
(1452013, "BOWMAN", 80), # Fire Arund - Lv 80
(1452014, "BOWMAN", 80), # Golden Arund - Lv 80
(1452017, "BOWMAN", 90), # Metus - Lv 90
(1452025, "BOWMAN", 90), # Blue Metus - Lv 90
(1452026, "BOWMAN", 90), # Black Metus - Lv 90
(1452019, "BOWMAN", 100), # White Nisrock - Lv 100
(1452020, "BOWMAN", 100), # Golden Nisrock - Lv 100
(1452021, "BOWMAN", 100), # Dark Nisrock - Lv 100
(1452044, "BOWMAN", 110), # Dragon Shiner Bow - Lv 110
(1452236, "BOWMAN", 120), # Briser Longbow - Lv 120
(1452237, "BOWMAN", 130), # Jaihin Bow - Lv 130
(1452238, "BOWMAN", 140), # Utgard Bow - Lv 140

# Crossbow
(1462001, "BOWMAN", 10), # Crossbow - Lv 12
(1462002, "BOWMAN", 10), # Battle Crossbow - Lv 18
(1462003, "BOWMAN", 20), # Balanche - Lv 22
(1462000, "BOWMAN", 20), # Mountain Crossbow - Lv 28
(1462004, "BOWMAN", 30), # Eagle Crow - Lv 32
(1462005, "BOWMAN", 30), # Heckler - Lv 38
(1462006, "BOWMAN", 40), # Silver Crow - Lv 42
(1462007, "BOWMAN", 50), # Rower - Lv 50
(1462008, "BOWMAN", 60), # Golden Crow - Lv 60
(1462009, "BOWMAN", 70), # Gross Jaeger - Lv 70
(1462013, "BOWMAN", 80), # Dark Raven - Lv 80
(1462011, "BOWMAN", 80), # Fire Raven - Lv 80
(1462012, "BOWMAN", 80), # Golden Raven - Lv 80
(1462010, "BOWMAN", 80), # Marine Raven - Lv 80
(1462018, "BOWMAN", 90), # Casa Crow - Lv 90
(1462021, "BOWMAN", 90), # Dark Crow - Lv 90
(1462022, "BOWMAN", 90), # Yellow Crow - Lv 90
(1462017, "BOWMAN", 100), # Dark Neschere - Lv 100
(1462016, "BOWMAN", 100), # Golden Neschere - Lv 100
(1462039, "BOWMAN", 110), # Dragon Shiner Cross - Lv 110
(1462223, "BOWMAN", 120), # Briser Crossbow - Lv 120
(1462224, "BOWMAN", 130), # Jaihin Crossbow - Lv 130
(1462225, "BOWMAN", 140), # Utgard Crossbow - Lv 140

# Ancient Bow
(1592000, "BOWMAN", 20), # Marad Ancient Bow - Lv 10
(1592001, "BOWMAN", 30), # Assur Ancient Bow - Lv 10
(1592002, "BOWMAN", 50), # Uruk Ancient Bow - Lv 10
(1592003, "BOWMAN", 60), # Avaris Ancient Bow - Lv 10
(1592004, "BOWMAN", 70), # Magna Ancient Bow - Lv 10
(1592005, "BOWMAN", 80), # Tanis Ancient Bow - Lv 10
(1592006, "BOWMAN", 90), # Philos Ancient Bow - Lv 10
(1592007, "BOWMAN", 100), # Ephesus Ancient Bow - Lv 10
(1592009, "BOWMAN", 110), # Dragon Ancient Bow - Lv 10
(1592012, "BOWMAN", 120), # Briser Ancient Bow - Lv 10
(1592014, "BOWMAN", 130), # Jaihin Ancient Bow - Lv 10
(1592016, "BOWMAN", 140), # Utgard Ancient Bow - Lv 10

# Dual Bowgun
(1522000, "BOWMAN", 10), # Trusty & Faithful - Lv 10
(1522002, "BOWMAN", 20), # Elven Diplomacy - Lv 20
(1522003, "BOWMAN", 20), # Songs of Nature - Lv 25
(1522004, "BOWMAN", 30), # Twin Angels - Lv 30
(1522005, "BOWMAN", 30), # Proud Blossoms - Lv 35
(1522006, "BOWMAN", 40), # The Argents - Lv 40
(1522007, "BOWMAN", 50), # Judge & Jury - Lv 50
(1522008, "BOWMAN", 60), # Lunar Glory - Lv 60
(1522009, "BOWMAN", 70), # Twin Zephyrs - Lv 70
(1522086, "BOWMAN", 70), # Stellar Argents - Lv 75
(1522010, "BOWMAN", 80), # Sylvan Hunters - Lv 80
(1522011, "BOWMAN", 90), # Royal Decrees - Lv 90
(1522012, "BOWMAN", 100), # Final Retorts - Lv 100
(1522014, "BOWMAN", 110), # Dragon Majesty - Lv 110
(1522122, "BOWMAN", 120), # Briser Dual Bowguns - Lv 120
(1522123, "BOWMAN", 130), # Jaihin Dual Bowguns - Lv 130
(1522124, "BOWMAN", 140), # Utgard Dual Bowguns - Lv 140

# Warrior
# Katana
(1542000, "WARRIOR", 10), # Simple Iron Sword - Lv 10
(1542001, "WARRIOR", 20), # Tempered Steel Sword - Lv 20
(1542002, "WARRIOR", 30), # Iron Guardian - Lv 30
(1542003, "WARRIOR", 40), # Bare Blade - Lv 40
(1542004, "WARRIOR", 50), # Iron Savior - Lv 50
(1542006, "WARRIOR", 60), # Helix - Lv 60
(1542007, "WARRIOR", 80), # Heat Wave - Lv 80
(1542008, "WARRIOR", 90), # Battle Cry - Lv 90
(1542009, "WARRIOR", 100), # Gilded Sword - Lv 100
(1542011, "WARRIOR", 100), # Mist Cutter - Lv 100
(1542010, "WARRIOR", 110), # Phoenix Rising - Lv 110
(1542099, "WARRIOR", 120), # Briser Katana - Lv 120
(1542100, "WARRIOR", 130), # Jaihin Katana - Lv 130
(1542101, "WARRIOR", 140), # Utgard Katana - Lv 140

# Desperado
(1232000, "WARRIOR", 10), # Blue Ravager - Lv 10
(1232002, "WARRIOR", 30), # Dim Memory - Lv 30
(1232003, "WARRIOR", 50), # Eternal Wanderer - Lv 50
(1232004, "WARRIOR", 60), # Plum Sadness - Lv 60
(1232005, "WARRIOR", 70), # Cardinal Omen - Lv 70
(1232006, "WARRIOR", 80), # Brand Edge - Lv 80
(1232007, "WARRIOR", 90), # Nightmare - Lv 90
(1232008, "WARRIOR", 100), # Cruel Vengeance - Lv 100
(1232010, "WARRIOR", 110), # Dragon Rage - Lv 110
(1232093, "WARRIOR", 120), # Briser Desperado - Lv 120
(1232094, "WARRIOR", 130), # Jaihin Desperado - Lv 130
(1232095, "WARRIOR", 140), # Utgard Desperado - Lv 140

# Polearm
(1442000, "WARRIOR", 10), # PoleArm - Lv 10
(1442006, "WARRIOR", 10), # Iron Ball - Lv 15
(1442007, "WARRIOR", 20), # Studded Polearm - Lv 20
(1442001, "WARRIOR", 30), # Mithril Pole Arm - Lv 30
(1442009, "WARRIOR", 40), # Crescent Polearm - Lv 40
(1442005, "WARRIOR", 50), # The Nine Dragons - Lv 50
(1442010, "WARRIOR", 60), # Skylar - Lv 60
(1442008, "WARRIOR", 70), # The Gold Dragon - Lv 70
(1442019, "WARRIOR", 80), # Eclipse - Lv 80
(1442020, "WARRIOR", 90), # Hellslayer - Lv 90
(1442044, "WARRIOR", 100), # Zedbug - Lv 100
(1442045, "WARRIOR", 110), # Dragon Hellslayer - Lv 110
(1442169, "WARRIOR", 120), # Eviscerator - Lv 120
(1442252, "WARRIOR", 120), # Briser Hellslayer - Lv 120
(1442253, "WARRIOR", 130), # Jaihin Hellslayer - Lv 130
(1442254, "WARRIOR", 140), # Utgard Hellslayer - Lv 140

# Spear
(1432000, "WARRIOR", 10), # Spear - Lv 10
(1432001, "WARRIOR", 10), # Fork on a Stick - Lv 15
(1432002, "WARRIOR", 30), # Forked Spear - Lv 30
(1432005, "WARRIOR", 40), # Zeco - Lv 40
(1432004, "WARRIOR", 50), # Serpent's Tongue - Lv 50
(1432006, "WARRIOR", 60), # Holy Spear - Lv 60
(1432007, "WARRIOR", 70), # Redemption - Lv 70
(1432010, "WARRIOR", 80), # Omega Spear - Lv 80
(1432011, "WARRIOR", 90), # Fairfrozen - Lv 90
(1432030, "WARRIOR", 100), # Pinaka - Lv 100
(1432038, "WARRIOR", 110), # Dragon Faltizan - Lv 110
(1432198, "WARRIOR", 120), # Briser Spear - Lv 120
(1432199, "WARRIOR", 130), # Jaihin Spear - Lv 130
(1432200, "WARRIOR", 140), # Utgard Spear - Lv 140

# 1h sword
(1302005, "WARRIOR", 10), # Sabre - Lv 10
(1302006, "WARRIOR", 20), # Machete - Lv 20
(1302002, "WARRIOR", 20), # Viking Sword - Lv 20
(1302008, "WARRIOR", 30), # Gladius - Lv 30
(1302004, "WARRIOR", 30), # Cutlass - Lv 35
(1302009, "WARRIOR", 40), # Traus - Lv 40
(1302010, "WARRIOR", 50), # Jeweled Katar - Lv 50
(1302011, "WARRIOR", 60), # Neocora - Lv 60
(1302012, "WARRIOR", 70), # Red Katana - Lv 70
(1302018, "WARRIOR", 80), # Khan - Lv 80
(1302023, "WARRIOR", 90), # Fraute - Lv 90
(1302056, "WARRIOR", 100), # Sparta - Lv 100
(1302059, "WARRIOR", 110), # Dragon Carabella - Lv 110
(1302313, "WARRIOR", 120), # Briser Saber - Lv 120
(1302314, "WARRIOR", 130), # Jaihin Saber - Lv 130
(1302315, "WARRIOR", 140), # Utgard Saber - Lv 140

# 2h sword
(1402001, "WARRIOR", 10), # Wooden Sword - Lv 10
(1402000, "WARRIOR", 20), # Two-Handed Sword - Lv 20
(1402008, "WARRIOR", 20), # Broadsword - Lv 25
(1402002, "WARRIOR", 30), # Scimitar - Lv 30
(1402006, "WARRIOR", 30), # Lionheart - Lv 35
(1402007, "WARRIOR", 40), # Zard - Lv 40
(1402003, "WARRIOR", 50), # Lion's Fang - Lv 50
(1402013, "WARRIOR", 50), # Japanese Map - Lv 50
(1402011, "WARRIOR", 60), # Sparta - Lv 60
(1402012, "WARRIOR", 70), # Doombringer - Lv 70
(1402015, "WARRIOR", 80), # Heaven's Gate - Lv 80
(1402004, "WARRIOR", 80), # Blue Screamer - Lv 80
(1402005, "WARRIOR", 90), # Berzerker - Lv 90
(1402016, "WARRIOR", 90), # Devil's Sunrise - Lv 90
(1402035, "WARRIOR", 100), # The Beheader - Lv 100
(1402037, "WARRIOR", 100), # Stonetooth Sword - Lv 100
(1402036, "WARRIOR", 110), # Dragon Claymore - Lv 110
(1402234, "WARRIOR", 120), # Briser Two-handed Sword - Lv 120
(1402235, "WARRIOR", 130), # Jaihin Two-handed Sword - Lv 130
(1402236, "WARRIOR", 140), # Utgard Two-handed Sword - Lv 140

# 1h mace
(1322000, "WARRIOR", 10), # Mace - Lv 10
(1322002, "WARRIOR", 20), # Iron Mace - Lv 20
(1322004, "WARRIOR", 20), # Fusion Mace - Lv 25
(1322014, "WARRIOR", 30), # War Hammer - Lv 30
(1322016, "WARRIOR", 40), # Jacker - Lv 40
(1322017, "WARRIOR", 50), # Knuckle Mace - Lv 50
(1322018, "WARRIOR", 60), # Tamus - Lv 60
(1322019, "WARRIOR", 70), # The Judgement - Lv 70
(1322020, "WARRIOR", 70), # Bent Judgement - Lv 75
(1322028, "WARRIOR", 80), # Heaven's Justice - Lv 80
(1322029, "WARRIOR", 90), # Ruin Hammer - Lv 90
(1322045, "WARRIOR", 100), # Battle Hammer - Lv 100
(1322052, "WARRIOR", 110), # Dragon Mace - Lv 110
(1322234, "WARRIOR", 120), # Briser Hammer - Lv 120
(1322235, "WARRIOR", 130), # Jaihin Hair - Lv 130
(1322236, "WARRIOR", 140), # Utgard Hair - Lv 140

# 2h mace
(1422000, "WARRIOR", 10), # Wooden Mallet - Lv 10
(1422003, "WARRIOR", 20), # Square Hammer - Lv 20
(1422001, "WARRIOR", 30), # Mithril Maul - Lv 30
(1422008, "WARRIOR", 30), # Sledgehammer - Lv 35
(1422007, "WARRIOR", 40), # Titan - Lv 40
(1422005, "WARRIOR", 50), # Golden Mole - Lv 50
(1422009, "WARRIOR", 60), # The Blessing - Lv 60
(1422010, "WARRIOR", 70), # Gigantic Sledge - Lv 70
(1422018, "WARRIOR", 80), # The Morningstar - Lv 80
(1422013, "WARRIOR", 90), # Leomite - Lv 90
(1422027, "WARRIOR", 100), # Golden Smith Hammer - Lv 100
(1422028, "WARRIOR", 110), # Dragon Flame - Lv 110
(1422169, "WARRIOR", 120), # Briser Two-handed Hammer - Lv 120
(1422170, "WARRIOR", 130), # Jaihin Two-handed Hammer - Lv 130
(1422171, "WARRIOR", 140), # Utgard Two-handed Hammer - Lv 140

# 1h axe
(1312000, "WARRIOR", 10), # Double Axe - Lv 10
(1312001, "WARRIOR", 10), # Battle Axe - Lv 15
(1312003, "WARRIOR", 20), # Mithril Axe - Lv 25
(1312005, "WARRIOR", 30), # Fireman's Axe - Lv 30
(1312006, "WARRIOR", 30), # Dankke - Lv 35
(1312007, "WARRIOR", 40), # Blue Counter - Lv 40
(1312008, "WARRIOR", 50), # Buck - Lv 50
(1312009, "WARRIOR", 60), # Hawkhead - Lv 60
(1312010, "WARRIOR", 70), # Mikhail - Lv 70
(1312011, "WARRIOR", 80), # Gaea - Lv 80
(1312015, "WARRIOR", 90), # Vifennis - Lv 90
(1312030, "WARRIOR", 100), # Tomahawk - Lv 100
(1312031, "WARRIOR", 110), # Dragon Axe - Lv 110
(1312183, "WARRIOR", 120), # Jaihin Axe - Lv 120
(1312184, "WARRIOR", 130), # Jaihin Axe - Lv 130
(1312185, "WARRIOR", 140), # Utgard Axe - Lv 140

# 2h axe
(1412001, "WARRIOR", 10), # Metal Axe - Lv 10
(1412012, "WARRIOR", 10), # Iron Axe - Lv 15
(1412000, "WARRIOR", 20), # Two-Handed Axe - Lv 25
(1412006, "WARRIOR", 30), # Blue Axe - Lv 30
(1412004, "WARRIOR", 30), # Niam - Lv 35
(1412005, "WARRIOR", 40), # Sabretooth - Lv 40
(1412003, "WARRIOR", 50), # The Rising - Lv 50
(1412007, "WARRIOR", 60), # The Shining - Lv 60
(1412008, "WARRIOR", 70), # Chrono - Lv 70
(1412009, "WARRIOR", 80), # Helios - Lv 80
(1412010, "WARRIOR", 90), # Colonian Axe - Lv 90
(1412021, "WARRIOR", 100), # Tavar - Lv 100
(1412026, "WARRIOR", 110), # Dragon Battle Axe - Lv 110
(1412162, "WARRIOR", 120), # Briser Two-handed Axe - Lv 120
(1412163, "WARRIOR", 130), # Jaihin Two-handed Axe - Lv 130
(1412164, "WARRIOR", 140), # Utgard Two-handed Axe - Lv 140

# Hand cannon
(1582000, "WARRIOR", 10), # Hand Buster - Lv 10
(1582001, "WARRIOR", 30), # Hand Crusher - Lv 30
(1582002, "WARRIOR", 50), # Blow Bomb - Lv 50
(1582003, "WARRIOR", 60), # Giant Hand - Lv 60
(1582004, "WARRIOR", 70), # Taurus - Lv 70
(1582005, "WARRIOR", 80), # Ogre Grip - Lv 80
(1582006, "WARRIOR", 90), # Gigantic Arm Cannon - Lv 90
(1582007, "WARRIOR", 100), # Titan Arms - Lv 100
(1582008, "WARRIOR", 110), # Valore Punch - Lv 110
(1582009, "WARRIOR", 140), # Briser Surtr - Lv 140
(1582010, "WARRIOR", 140), # Jaihin Surtr - Lv 140
(1582011, "WARRIOR", 140), # Utgard Surtr - Lv 140

# Magician
# Lucent Gauntlet
(1282000, "MAGICIAN", 10), # Luna Lucent Gauntlet - Lv 10
(1282001, "MAGICIAN", 30), # Matis Lucent Gauntlet - Lv 30
(1282002, "MAGICIAN", 50), # Mercury Lucent Gauntlet - Lv 50
(1282003, "MAGICIAN", 60), # Robis Lucent Gauntlet - Lv 60
(1282004, "MAGICIAN", 70), # Veneris Lucent Gauntlet - Lv 70
(1282005, "MAGICIAN", 80), # Saturni Lucent Gauntlet - Lv 80
(1282006, "MAGICIAN", 90), # Solis Lucent Gauntlet - Lv 90
(1282007, "MAGICIAN", 100), # Perias Lucent Gauntlet - Lv 100
(1282008, "MAGICIAN", 110), # Dragon Lucent Gauntlet - Lv 110
(1282010, "MAGICIAN", 120), # Briser Lucent Gauntlet - Lv 120
(1282011, "MAGICIAN", 130), # Jaihin Lucent Gauntlet - Lv 130
(1282013, "MAGICIAN", 140), # Utgard Lucent Gauntlet - Lv 140

# Fan
(1552000, "MAGICIAN", 10), # Iron Fan - Lv 10
(1552001, "MAGICIAN", 20), # Metallic Fan - Lv 20
(1552002, "MAGICIAN", 30), # Triple Fan - Lv 30
(1552003, "MAGICIAN", 40), # Crane Fan - Lv 40
(1552004, "MAGICIAN", 50), # Fan of Enlightenment - Lv 50
(1552005, "MAGICIAN", 60), # Melodic Fan - Lv 60
(1552006, "MAGICIAN", 70), # Unique Fan - Lv 70
(1552007, "MAGICIAN", 80), # Leaf-Patterned Fan - Lv 80
(1552008, "MAGICIAN", 90), # Erupting Fan - Lv 90
(1552009, "MAGICIAN", 100), # Clear Skies - Lv 100
(1552011, "MAGICIAN", 100), # Florist's Fan - Lv 100
(1552010, "MAGICIAN", 110), # Crossed Fan - Lv 110
(1552100, "MAGICIAN", 120), # Briser Fan - Lv 120
(1552101, "MAGICIAN", 130), # Jaihin Fan - Lv 130
(1552102, "MAGICIAN", 140), # Utgard Fan - Lv 140

# Staff
(1382000, "MAGICIAN", 10), # Wooden Staff - Lv 10
(1382003, "MAGICIAN", 10), # Sapphire Staff - Lv 15
(1382005, "MAGICIAN", 10), # Emerald Staff - Lv 15
(1382004, "MAGICIAN", 20), # Old Wooden Staff - Lv 20
(1382002, "MAGICIAN", 20), # Wizard Staff - Lv 25
(1382017, "MAGICIAN", 30), # Circle-Winded Staff - Lv 30
(1382009, "MAGICIAN", 30), # Maple Staff - Lv 35
(1382018, "MAGICIAN", 30), # Petal Staff - Lv 35
(1382019, "MAGICIAN", 40), # Hall Staff - Lv 40
(1382020, "MAGICIAN", 40), # Arc Staff - Lv 45
(1382011, "MAGICIAN", 50), # Mystic Cane - Lv 51
(1382026, "MAGICIAN", 50), # Thorns - Lv 55
(1382027, "MAGICIAN", 60), # Evil Wings - Lv 65
(1382023, "MAGICIAN", 70), # Dark Ritual - Lv 75
(1382024, "MAGICIAN", 80), # Kage - Lv 85
(1382035, "MAGICIAN", 90), # Blue Marine - Lv 95
(1382037, "MAGICIAN", 100), # Doomsday Staff - Lv 102
(1382036, "MAGICIAN", 110), # Dragon Staff - Lv 110
(1382243, "MAGICIAN", 120), # Briser Staff - Lv 120
(1382244, "MAGICIAN", 130), # Jaihin Staff - Lv 130
(1382245, "MAGICIAN", 140), # Utgard Staff - Lv 140

# Wand
(1372005, "MAGICIAN", 0), # Wooden Wand - Lv 8
(1372002, "MAGICIAN", 10), # Metal Wand - Lv 18
(1372004, "MAGICIAN", 20), # Ice Wand - Lv 23
(1372003, "MAGICIAN", 20), # Mithril Wand - Lv 28
(1372001, "MAGICIAN", 30), # Wizard Wand - Lv 33
(1372012, "MAGICIAN", 30), # Crystal Wand - Lv 38
(1372000, "MAGICIAN", 30), # Fairy Wand - Lv 38
(1372007, "MAGICIAN", 40), # Cromi - Lv 48
(1372014, "MAGICIAN", 50), # Evil Tale - Lv 58
(1372008, "MAGICIAN", 60), # Hinomaru Fan - Lv 60
(1372015, "MAGICIAN", 60), # Angel Wings - Lv 68
(1372016, "MAGICIAN", 70), # Phoenix Wand - Lv 78
(1372009, "MAGICIAN", 80), # Magicodar - Lv 88
(1372010, "MAGICIAN", 90), # Dimon Wand - Lv 98
(1372032, "MAGICIAN", 100), # Dragon Wand - Lv 108
(1372205, "MAGICIAN", 120), # Briser Wand - Lv 130
(1372206, "MAGICIAN", 130), # Jaihin Wand - Lv 130
(1372207, "MAGICIAN", 140), # Utgard Wand - Lv 140

# Shining Rod
(1212001, "MAGICIAN", 10), # Plain - Lv 10
(1212002, "MAGICIAN", 30), # Golden Shine - Lv 30
(1212003, "MAGICIAN", 50), # Decor - Lv 50
(1212004, "MAGICIAN", 60), # Vallentier - Lv 60
(1212005, "MAGICIAN", 70), # Sharp Slaver - Lv 70
(1212006, "MAGICIAN", 80), # Light Sage Wing - Lv 80
(1212007, "MAGICIAN", 90), # Shining Twin Star - Lv 90
(1212010, "MAGICIAN", 110), # Shining Dragon Rod - Lv 110
(1212099, "MAGICIAN", 120), # Briser Shining Rod - Lv 120
(1212100, "MAGICIAN", 130), # Jaihin Shining Rod - Lv 130
(1212101, "MAGICIAN", 140), # Utgard Shining Rod - Lv 140

# Psy-limiter
(1262000, "MAGICIAN", 10), # Prototype Psy-limiter - Lv 10
(1262001, "MAGICIAN", 30), # Trial Psy-limiter - Lv 30
(1262002, "MAGICIAN", 50), # First Psy-limiter - Lv 50
(1262003, "MAGICIAN", 60), # Strategic Psy-limiter - Lv 60
(1262004, "MAGICIAN", 70), # Engaging Psy-limiter - Lv 70
(1262005, "MAGICIAN", 80), # Technical Psy-limiter - Lv 80
(1262006, "MAGICIAN", 90), # Mad Psy-limiter - Lv 90
(1262007, "MAGICIAN", 100), # Daemon Psy-limiter - Lv 100
(1262008, "MAGICIAN", 110), # Dragon Psy-limiter - Lv 110
(1262009, "MAGICIAN", 120), # Briser Psy-limiter - Lv 120
(1262010, "MAGICIAN", 130), # Jaihin Psy-limiter - Lv 130
(1262011, "MAGICIAN", 140), # Utgard Psy-limiter - Lv 140

# Scepter
(1252001, "MAGICIAN", 10), # Beast Tamer Scepter - Lv 10
(1252048, "MAGICIAN", 10), # Hardwood Beast Tamer Scepter - Lv 15
(1252049, "MAGICIAN", 20), # Metal Beast Tamer Scepter - Lv 20
(1252050, "MAGICIAN", 30), # Assign Scepter - Lv 30
(1252051, "MAGICIAN", 40), # High Assign Scepter - Lv 40
(1252052, "MAGICIAN", 50), # Talon Scepter - Lv 50
(1252053, "MAGICIAN", 60), # Panther Scepter - Lv 60
(1252054, "MAGICIAN", 70), # Grizzly Scepter - Lv 70
(1252006, "MAGICIAN", 80), # Tigress Scepter - Lv 80
(1252007, "MAGICIAN", 90), # Eagle Scepter - Lv 90
(1252008, "MAGICIAN", 100), # Falcon Scepter - Lv 100
(1252009, "MAGICIAN", 100), # Lion Glass Scepter - Lv 105
(1252010, "MAGICIAN", 110), # Dragon Kitty Soul Scepter - Lv 110
(1252084, "MAGICIAN", 120), # Briser Shining Stick - Lv 120
(1252085, "MAGICIAN", 130), # Jaihin Shining Stick - Lv 130
(1252086, "MAGICIAN", 140), # Utgard Shining Stick - Lv 140

# Thief
# Chain
(1272000, "THIEF", 10), # De Mercurio - Lv 10
(1272001, "THIEF", 30), # De Venus - Lv 30
(1272002, "THIEF", 50), # De Tierra - Lv 50
(1272003, "THIEF", 60), # De Marte - Lv 60
(1272004, "THIEF", 70), # De Jupiter - Lv 70
(1272005, "THIEF", 80), # De Saturno - Lv 80
(1272006, "THIEF", 90), # De Urano - Lv 90
(1272007, "THIEF", 100), # De Neptuno - Lv 100
(1272008, "THIEF", 110), # Dragon Chain - Lv 110
(1272009, "THIEF", 120), # Briser Chain - Lv 120
(1272011, "THIEF", 130), # Jaihin Chain - Lv 130
(1272013, "THIEF", 140), # Utgard Chain - Lv 140

# Cane
(1362002, "THIEF", 10), # Iron Cane - Lv 15
(1362003, "THIEF", 20), # Noble Stick - Lv 20
(1362004, "THIEF", 20), # Croix Canne - Lv 25
(1362005, "THIEF", 30), # Rouge Way - Lv 30
(1362006, "THIEF", 30), # Arc-en-ciel - Lv 35
(1362007, "THIEF", 40), # Twisted - Lv 40
(1362008, "THIEF", 50), # Oriental Royal Cane - Lv 50
(1362009, "THIEF", 60), # Bijou Canne - Lv 60
(1362010, "THIEF", 70), # Regne - Lv 70
(1362011, "THIEF", 70), # Majesty Cane - Lv 70
(1362012, "THIEF", 90), # Phantom Originality - Lv 90
(1362013, "THIEF", 100), # Celestial Cane - Lv 100
(1362015, "THIEF", 110), # Dragon Permanche - Lv 110
(1362119, "THIEF", 120), # Briser Cane - Lv 120
(1362120, "THIEF", 130), # Jaihin Cane - Lv 130
(1362121, "THIEF", 140), # Utgard Cane - Lv 140

# Dagger
(1332007, "THIEF", 0), # Fruit Knife - Lv 8
(1332000, "THIEF", 10), # Triangular Zamadar - Lv 12
(1332006, "THIEF", 10), # Field Dagger - Lv 15
(1332002, "THIEF", 10), # Triple-Tipped Zamadar - Lv 17
(1332008, "THIEF", 20), # Coconut Knife - Lv 20
(1332013, "THIEF", 20), # Stinger - Lv 22
(1332010, "THIEF", 20), # Iron Dagger - Lv 25
(1332004, "THIEF", 20), # Forked Dagger - Lv 27
(1332009, "THIEF", 30), # Cass - Lv 30
(1332012, "THIEF", 30), # Reef Claw - Lv 30
(1332001, "THIEF", 30), # Halfmoon Zamadar - Lv 32
(1332014, "THIEF", 30), # Gephart - Lv 35
(1332011, "THIEF", 40), # Bazlud - Lv 40
(1332031, "THIEF", 40), # Dragon Toenail - Lv 40
(1332003, "THIEF", 50), # Shinkita - Lv 50
(1332016, "THIEF", 50), # Sai - Lv 50
(1332024, "THIEF", 50), # Bushido - Lv 55
(1332015, "THIEF", 60), # Deadly Fin - Lv 60
(1332017, "THIEF", 60), # Serpent's Coil - Lv 60
(1332030, "THIEF", 60), # Fan - Lv 65
(1332019, "THIEF", 70), # Golden River - Lv 70
(1332018, "THIEF", 70), # Kandine - Lv 70
(1332022, "THIEF", 80), # Angelic Betrayal - Lv 80
(1332023, "THIEF", 80), # Dragon's Tail - Lv 80
(1332026, "THIEF", 90), # Cursayer - Lv 90
(1332027, "THIEF", 90), # Varkit - Lv 90
(1332052, "THIEF", 100), # Blood Dagger - Lv 100
(1332049, "THIEF", 110), # Dragon Kanzir - Lv 110
(1332050, "THIEF", 110), # Dragon Kreda - Lv 110
(1332258, "THIEF", 120), # Briser Dagger - Lv 120
(1332259, "THIEF", 130), # Jaihin Dagger - Lv 130
(1332260, "THIEF", 140), # Utgard Dagger - Lv 140

# Claw
(1472000, "THIEF", 10), # Garnier - Lv 10
(1472001, "THIEF", 10), # Steel Titans - Lv 15
(1472002, "THIEF", 10), # Mithril Titans - Lv 15
(1472003, "THIEF", 10), # Gold Titans - Lv 15
(1472004, "THIEF", 20), # Bronze Igor - Lv 20
(1472005, "THIEF", 20), # Steel Igor - Lv 20
(1472006, "THIEF", 20), # Adamantium Igor - Lv 20
(1472007, "THIEF", 30), # Meba - Lv 35
(1472008, "THIEF", 30), # Steel Guards - Lv 30
(1472009, "THIEF", 30), # Mithril Guards - Lv 30
(1472010, "THIEF", 30), # Adamantium Guards - Lv 30
(1472011, "THIEF", 30), # Bronze Guardian - Lv 35
(1472012, "THIEF", 30), # Silver Guardian - Lv 35
(1472013, "THIEF", 30), # Dark Guardian - Lv 35
(1472014, "THIEF", 40), # Steel Avarice - Lv 40
(1472015, "THIEF", 40), # Blood Avarice - Lv 40
(1472016, "THIEF", 40), # Adamantium Avarice - Lv 40
(1472017, "THIEF", 40), # Dark Avarice - Lv 40
(1472018, "THIEF", 50), # Steel Slain - Lv 50
(1472019, "THIEF", 50), # Blood Slain - Lv 50
(1472020, "THIEF", 50), # Sapphire Slain - Lv 50
(1472021, "THIEF", 50), # Dark Slain - Lv 50
(1472022, "THIEF", 60), # Bronze Gigantic - Lv 60
(1472023, "THIEF", 60), # Blood Gigantic - Lv 60
(1472024, "THIEF", 60), # Sapphire Gigantic - Lv 60
(1472025, "THIEF", 60), # Dark Gigantic - Lv 60
(1472055, "THIEF", 60), # Maple Skanda - Lv 64
(1472026, "THIEF", 70), # Brown Scarab - Lv 70
(1472027, "THIEF", 70), # Green Scarab - Lv 70
(1472028, "THIEF", 70), # Blue Scarab - Lv 70
(1472029, "THIEF", 70), # Black Scarab - Lv 70
(1472031, "THIEF", 80), # Black Mamba - Lv 80
(1472033, "THIEF", 90), # Casters - Lv 90
(1472053, "THIEF", 100), # Red Craven - Lv 100
(1472051, "THIEF", 110), # Dragon Green Sleeve - Lv 110
(1472052, "THIEF", 110), # Dragon Purple Sleeve - Lv 110
(1472245, "THIEF", 120), # Briser Guards - Lv 120
(1472246, "THIEF", 130), # Jaihin Guards - Lv 130
(1472247, "THIEF", 140), # Utgard Guards - Lv 140

# Katara
(1342000, "THIEF", 20), # Champion Katara - Lv 20
(1342001, "THIEF", 30), # Guardian Katara - Lv 30
(1342002, "THIEF", 40), # Justice Katara - Lv 40
(1342003, "THIEF", 50), # Majestic Katara - Lv 50
(1342004, "THIEF", 60), # Ascalon Katara - Lv 60
(1342005, "THIEF", 70), # Heavenly Katara - Lv 70
(1342006, "THIEF", 80), # Blazing Dragon Katara - Lv 80
(1342007, "THIEF", 90), # Bloodsoaked Katara - Lv 90
(1342008, "THIEF", 100), # Meteor Katara - Lv 100
(1342010, "THIEF", 110), # Moonshadow Katara - Lv 110
(1342098, "THIEF", 120), # Briser Katara - Lv 120
(1342099, "THIEF", 130), # Jaihin Katara - Lv 130
(1342100, "THIEF", 140), # Utgard Katara - Lv 140

# Pirate
# Pistol
(1492000, "PIRATE", 10), # Pistol - Lv 10
(1492001, "PIRATE", 10), # Dellinger Special - Lv 15
(1492002, "PIRATE", 20), # The Negotiator - Lv 20
(1492003, "PIRATE", 20), # Golden Hook - Lv 25
(1492004, "PIRATE", 30), # Cold Mind - Lv 30
(1492005, "PIRATE", 30), # Shooting Star - Lv 35
(1492006, "PIRATE", 40), # Lunar Shooter - Lv 40
(1492007, "PIRATE", 50), # Mr. Rasfelt - Lv 50
(1492008, "PIRATE", 60), # Burning Hell - Lv 60
(1492009, "PIRATE", 70), # Abyss Shooter - Lv 70
(1492010, "PIRATE", 80), # Infinity's Wrath - Lv 80
(1492011, "PIRATE", 90), # The Peacemaker - Lv 90
(1492012, "PIRATE", 100), # Concerto - Lv 100
(1492013, "PIRATE", 110), # Dragon Revolver - Lv 110
(1492210, "PIRATE", 120), # Briser Pistol - Lv 120
(1492211, "PIRATE", 130), # Jaihin Pistol - Lv 130
(1492212, "PIRATE", 140), # Utgard Pistol - Lv 140

# Knuckle
(1482000, "PIRATE", 10), # Steel Knuckler - Lv 10
(1482001, "PIRATE", 10), # Leather Arms - Lv 15
(1482002, "PIRATE", 20), # Double Tail Knuckler - Lv 20
(1482003, "PIRATE", 20), # Norman Grip - Lv 25
(1482004, "PIRATE", 30), # Prime Hands - Lv 30
(1482005, "PIRATE", 30), # Silver Maiden - Lv 35
(1482020, "PIRATE", 30), # Maple Knuckle - Lv 35
(1482006, "PIRATE", 40), # Neozard - Lv 40
(1482007, "PIRATE", 50), # Fury Claw - Lv 50
(1482008, "PIRATE", 60), # Seraphims - Lv 60
(1482022, "PIRATE", 60), # Maple Golden Claw - Lv 64
(1482009, "PIRATE", 70), # Beia Crash - Lv 70
(1482010, "PIRATE", 80), # Steel Renault - Lv 80
(1482011, "PIRATE", 90), # White Fangz - Lv 90
(1482012, "PIRATE", 100), # King Cent - Lv 100
(1482013, "PIRATE", 110), # Dragon Slash Claw - Lv 110
(1482200, "PIRATE", 120), # Briser Claw - Lv 120
(1482201, "PIRATE", 130), # Jaihin Claw - Lv 130
(1482202, "PIRATE", 140), # Utgard Claw - Lv 140

# Soul Shooter
(1222001, "PIRATE", 10), # Purple Haze - Lv 10
(1222002, "PIRATE", 30), # Pink Haze - Lv 30
(1222003, "PIRATE", 50), # Purple Hatchling - Lv 50
(1222004, "PIRATE", 60), # Jade Hatchling - Lv 60
(1222005, "PIRATE", 70), # Pink Wormhead - Lv 70
(1222006, "PIRATE", 80), # Red Wormhead - Lv 80
(1222007, "PIRATE", 90), # Jade Wormhead - Lv 90
(1222008, "PIRATE", 100), # Green Dragon Soul - Lv 100
(1222009, "PIRATE", 100), # Jade Worm - Lv 105
(1222010, "PIRATE", 110), # Iron Dragon - Lv 110
(1222093, "PIRATE", 120), # Briser Dragon Soul - Lv 120
(1222094, "PIRATE", 130), # Jaihin Dragon Soul - Lv 130
(1222095, "PIRATE", 140), # Utgard Dragon Soul - Lv 140

# Cannon
(1532001, "PIRATE", 10), # Solid Cannon - Lv 15
(1532002, "PIRATE", 20), # Ironside - Lv 20
(1532003, "PIRATE", 20), # Steel Beast - Lv 25
(1532004, "PIRATE", 30), # Heavy Artillery - Lv 30
(1532005, "PIRATE", 30), # Titan Cannon - Lv 35
(1532006, "PIRATE", 40), # Grand Cannon - Lv 40
(1532007, "PIRATE", 50), # Dreadnought - Lv 50
(1532008, "PIRATE", 60), # The Bigshot - Lv 60
(1532009, "PIRATE", 70), # Infernalizer - Lv 70
(1532010, "PIRATE", 80), # Deck Sweeper - Lv 80
(1532011, "PIRATE", 90), # Bilge Breaker - Lv 90
(1532012, "PIRATE", 100), # Gilded Giant - Lv 100
(1532014, "PIRATE", 110), # Dragon Breaker - Lv 110
(1532128, "PIRATE", 120), # Briser Hand Cannon - Lv 120
(1532129, "PIRATE", 130), # Jaihin Siege Gun - Lv 130
(1532130, "PIRATE", 140), # Utgard Siege Gun - Lv 140

# Whip Blade
(1242001, "PIRATE", 10), # Horned Blade - Lv 10
(1242002, "PIRATE", 30), # Ridge Cutter - Lv 30
(1242003, "PIRATE", 50), # Rough Violet - Lv 50
(1242004, "PIRATE", 60), # Macro Knife - Lv 60
(1242005, "PIRATE", 70), # Scorpio Tail - Lv 70
(1242006, "PIRATE", 80), # Diode - Lv 80
(1242007, "PIRATE", 90), # Green Fairy - Lv 90
(1242008, "PIRATE", 100), # Crimson Skull - Lv 100
(1242009, "PIRATE", 100), # Fish Fin - Lv 105
(1242010, "PIRATE", 110), # Dragon Energy Skull - Lv 110
(1242100, "PIRATE", 120), # Briser Heavy Head - Lv 120
(1242101, "PIRATE", 130), # Jaihin Heavy Head - Lv 130
(1242102, "PIRATE", 140), # Utgard Heavy Head - Lv 140

# Armor -------------------------------------------------------

# Warrior 
# Hat
(1002043, "WARRIOR", 10), # Bronze Coif - Lv 10
(1002059, "WARRIOR", 20), # Bronze Viking Helmet - Lv 20
(1002023, "WARRIOR", 30), # Jousting Helmet - Lv 30
(1002025, "WARRIOR", 40), # Red Duke - Lv 40
(1002028, "WARRIOR", 50), # Silver Crusader Helm - Lv 50
(1002029, "WARRIOR", 60), # Red Oriental Helmet - Lv 60
(1002030, "WARRIOR", 70), # Silver Planet - Lv 70
(1002340, "WARRIOR", 80), # Dark Dragon Barbute - Lv 80
(1002532, "WARRIOR", 90), # Dark Grace Helmet - Lv 90
(1002379, "WARRIOR", 100), # Dark Valhalla Helmet - Lv 100
(1002551, "WARRIOR", 110), # Blue Dragon Helmet - Lv 110
(1004219, "WARRIOR", 120), # Eclectic Fennel - Lv 120
(1004224, "WARRIOR", 130), # Muspell Warrior Hat - Lv 130
(1004229, "WARRIOR", 140), # Pensalir Battle Helm - Lv 140

# Gloves
(1082004, "WARRIOR", 20), # Venon - Lv 20
(1082025, "WARRIOR", 40), # Dark Knuckle - Lv 40
(1082059, "WARRIOR", 60), # Bronze Clench - Lv 60
(1082116, "WARRIOR", 80), # Blood Emperor - Lv 80
(1082141, "WARRIOR", 100), # Dark Korben - Lv 100
(1082168, "WARRIOR", 110), # Blue Dragon Gauntlet - Lv 110
(1082598, "WARRIOR", 120), # Eclectic Bergamot - Lv 120
(1082603, "WARRIOR", 130), # Muspell Warrior Gloves - Lv 130
(1082608, "WARRIOR", 140), # Pensalir Battle Gloves - Lv 140

# Shoes
(1072007, "WARRIOR", 20), # Brown High Boots - Lv 20
(1072000, "WARRIOR", 40), # Brown Jangoon Shoes - Lv 40
(1072149, "WARRIOR", 60), # Blood Camel Boots - Lv 60
(1072210, "WARRIOR", 80), # Red Rivers Boots - Lv 80
(1072222, "WARRIOR", 100), # Dark Crescent Boots - Lv 100
(1072273, "WARRIOR", 110), # Blue Dragon Boots - Lv 110
(1072957, "WARRIOR", 120), # Eclectic Grabbe - Lv 120
(1072962, "WARRIOR", 130), # Muspell Warrior Shoes - Lv 130
(1072967, "WARRIOR", 140), # Pensalir Battle Boots - Lv 140

# Cape
(1102003, "WARRIOR", 50), # White Adventurer Cape - Lv 50
(1102004, "WARRIOR", 50), # Black Adventurer Cape - Lv 50
(1102017, "WARRIOR", 60), # White Magic Cape - Lv 60
(1102018, "WARRIOR", 60), # Black Magic Cape - Lv 60
(1102029, "WARRIOR", 70), # White Seraph Cape - Lv 75
(1102030, "WARRIOR", 70), # Black Seraph Cape - Lv 75
(1102033, "WARRIOR", 80), # Red Giles Cape - Lv 85
(1102034, "WARRIOR", 80), # Blue Giles Cape - Lv 85
(1102718, "WARRIOR", 140), # Pensalir Battle Cape - Lv 140

# Top
(1040015, "WARRIOR", 10), # Brown Lolico Armor - Lv 10
(1041014, "WARRIOR", 10), # Orange Lolica Armor - Lv 10
(1040012, "WARRIOR", 20), # Blue Sergeant - Lv 20
(1041021, "WARRIOR", 20), # Brown Lamelle - Lv 20
(1040021, "WARRIOR", 30), # Red Hwarang Shirt - Lv 30
(1040085, "WARRIOR", 40), # Maroon Jangoon Armor - Lv 40
(1041085, "WARRIOR", 40), # Brown Jangoon Armor - Lv 40
(1040087, "WARRIOR", 50), # Blue Shouldermail - Lv 50
(1040092, "WARRIOR", 60), # Blue Orientican - Lv 60
(1041092, "WARRIOR", 60), # Red Ice Queen - Lv 60
(1040103, "WARRIOR", 70), # Mithril Platine - Lv 70
(1041097, "WARRIOR", 70), # Aqua Platina - Lv 70 
(1041120, "WARRIOR", 90), # Purple Valkyrie - Lv 90 
(1040112, "WARRIOR", 90), # Blue Commodore - Lv 90 
(1040121, "WARRIOR", 100), # Blue Neos - Lv 100 
(1041123, "WARRIOR", 100), # Purple Lucida - Lv 100 

# Bottom
(1060008, "WARRIOR", 10), # Brown Lolico Pants - Lv 10 
(1060028, "WARRIOR", 10), # Blue Lolico Pants - Lv 10 
(1060155, "WARRIOR", 20), # Blue Sergeant Kilt - Lv 20 
(1060029, "WARRIOR", 20), # Red Sergeant Kilt - Lv 20 
(1060018, "WARRIOR", 30), # Red Martial Arts Pants - Lv 30 
(1060075, "WARRIOR", 40), # Brown Jangoon Pants - Lv 40 
(1060076, "WARRIOR", 50), # Blue Shouldermail Pants - Lv 50 
(1060080, "WARRIOR", 60), # Red Orientican Pants - Lv 60 
(1061091, "WARRIOR", 60), # Red Ice Queen Skirt - Lv 60 
(1060091, "WARRIOR", 70), # Mithril Platine Pants - Lv 70 
(1060101, "WARRIOR", 90), # Blue Commodore Pants - Lv 90 
(1061118, "WARRIOR", 90), # Green Valkyrie Skirt - Lv 90 
(1060111, "WARRIOR", 100), # Black Neos Pants - Lv 100 
(1061123, "WARRIOR", 100), # Dark Lucida Skirt - Lv 100 

# Overall
(1050081, "WARRIOR", 80), # Red Battle Lord - Lv 80 
(1051078, "WARRIOR", 80), # Red Battle Empress - Lv 80 
(1052075, "WARRIOR", 110), # Blue Dragon Armor - Lv 110 
(1052789, "WARRIOR", 120), # Eclectic Taragon - Lv 120 
(1052794, "WARRIOR", 130), # Muspell Warrior Suit - Lv 130 
(1052799, "WARRIOR", 140), # Pensalir Battle Mail - Lv 140 


# Magician
# Hat
(1002017, "MAGICIAN", 10), # Brown Apprentice Hat - Lv 10
(1002073, "MAGICIAN", 20), # Red Wizardry Hat - Lv 20
(1002036, "MAGICIAN", 30), # Green Jester - Lv 30
(1002155, "MAGICIAN", 40), # White Guiltian - Lv 40
(1002217, "MAGICIAN", 50), # Orange Golden Circlet - Lv 50
(1002246, "MAGICIAN", 60), # Dark Seraphis - Lv 60
(1002254, "MAGICIAN", 70), # Dark Infinium Circlet - Lv 70
(1002274, "MAGICIAN", 80), # Dark Galaxy - Lv 80
(1002366, "MAGICIAN", 90), # Black Oriental Fury Hat - Lv 90
(1002401, "MAGICIAN", 100), # Dark Varr Hat - Lv 100
(1002773, "MAGICIAN", 110), # Gold Dragon Crown - Lv 110
(1004220, "MAGICIAN", 120), # Eclectic Coral - Lv 120
(1004225, "MAGICIAN", 130), # Muspell Magician Hat - Lv 130
(1004230, "MAGICIAN", 140), # Pensalir Mage Sallet - Lv 140

# Gloves
(1082064, "WARRIOR", 40), # Dark Arten - Lv 40 
(1082086, "WARRIOR", 60), # Steel Manute - Lv 60 
(1082122, "WARRIOR", 80), # Blue Clarity - Lv 80 
(1082152, "WARRIOR", 100), # Blue Ciara - Lv 100 
(1082164, "WARRIOR", 110), # Blue Elemental Gloves - Lv 110 
(1082599, "WARRIOR", 120), # Eclectic Hermosa - Lv 120 
(1082604, "WARRIOR", 130), # Muspell Magician Gloves - Lv 130 
(1082609, "WARRIOR", 140), # Pensalir Mage Gloves - Lv 140 

# Boots
(1072019, "MAGICIAN", 20), # Blue Jewelry Boots - Lv 20 
(1072115, "MAGICIAN", 40), # Blue Moon Shoes - Lv 40 
(1072139, "MAGICIAN", 60), # Blue Goldrunners - Lv 60 
(1072178, "MAGICIAN", 80), # Purple Enigma Shoes - Lv 80 
(1072224, "MAGICIAN", 100), # Blue Varr Shoes - Lv 100 
(1072268, "MAGICIAN", 110), # Blue Elemental Shoes - Lv 110 
(1072958, "MAGICIAN", 120), # Eclectic Cabatina - Lv 120 
(1072963, "MAGICIAN", 130), # Muspell Magician Shoes - Lv 130 
(1072968, "MAGICIAN", 140), # Pensalir Mage Boots - Lv 140 

# Capes
(1102003, "MAGICIAN", 50), # White Adventurer Cape - Lv 50
(1102004, "MAGICIAN", 50), # Black Adventurer Cape - Lv 50
(1102017, "MAGICIAN", 60), # White Magic Cape - Lv 60
(1102018, "MAGICIAN", 60), # Black Magic Cape - Lv 60
(1102029, "MAGICIAN", 70), # White Seraph Cape - Lv 75
(1102030, "MAGICIAN", 70), # Black Seraph Cape - Lv 75
(1102033, "MAGICIAN", 80), # Red Giles Cape - Lv 85
(1102034, "MAGICIAN", 80), # Blue Giles Cape - Lv 85
(1102719, "MAGICIAN", 140), # Pensalir Mage Cape - Lv 140 

# Top
(1040160, "MAGICIAN", 0), # Grey Training Shirt - Lv 8 
(1040020, "MAGICIAN", 10), # Blue Split Piece - Lv 18 
(1041015, "MAGICIAN", 0), # Black Armine - Lv 8 
(1041041, "MAGICIAN", 20), # Purple Fairy Top - Lv 28 
(1041052, "MAGICIAN", 30), # Blue Amoria Top - Lv 33 

# Bottom
(1061010, "MAGICIAN", 0), # Black Armine Skirt - Lv 8 

# Overall
(1050008, "MAGICIAN", 10), # Beige Plain Robe - Lv 13 
(1050194, "MAGICIAN", 10), # Beige Plain Robe - Lv 13 
(1050027, "MAGICIAN", 20), # Black Mage Robe - Lv 28 
(1050197, "MAGICIAN", 20), # Black Mage Robe - Lv 28 
(1050039, "MAGICIAN", 30), # Dark Starlight - Lv 38 
(1050200, "MAGICIAN", 30), # Dark Starlight - Lv 38 
(1051034, "MAGICIAN", 40), # Orange Calaf - Lv 48 
(1050053, "MAGICIAN", 50), # Blue Anakamoon - Lv 58 
(1051044, "MAGICIAN", 50), # Blue Anakarune - Lv 58 
(1050067, "MAGICIAN", 60), # Blue Requiem - Lv 68 
(1051052, "MAGICIAN", 60), # Blue Requierre - Lv 68 
(1050073, "MAGICIAN", 70), # Blue Enigmatic - Lv 78 
(1051057, "MAGICIAN", 70), # Purple Enigma - Lv 78 
(1050092, "MAGICIAN", 80), # Green Oriental Fury Coat - Lv 88 
(1051094, "MAGICIAN", 80), # Green Oriental Fury Coat - Lv 88 
(1050103, "MAGICIAN", 90), # Blue Varuna - Lv 98 
(1051102, "MAGICIAN", 90), # Blue Bazura - Lv 98 
(1050102, "MAGICIAN", 90), # Green Varuna - Lv 98 
(1051103, "MAGICIAN", 90), # Red Bazura - Lv 98 
(1052076, "MAGICIAN", 100), # Blue Czar - Lv 108 
(1052790, "MAGICIAN", 120), # Eclectic Myst Blue - Lv 120 
(1052795, "MAGICIAN", 130), # Muspell Magician Suit - Lv 130 
(1052800, "MAGICIAN", 140), # Pensalir Mage Robe - Lv 140 

# Bowman

(1002010, "BOWMAN", 10), # Brown Winter Hat - Lv 10
(1002119, "BOWMAN", 20), # Green Robin Hat - Lv 20
(1002163, "BOWMAN", 30), # Green Hawkeye - Lv 30
(1002168, "BOWMAN", 40), # Green Distinction - Lv 40
(1002214, "BOWMAN", 50), # Black Maro - Lv 50
(1002270, "BOWMAN", 60), # Black Polyfeather Hat - Lv 60
(1002289, "BOWMAN", 70), # Dark Patriot - Lv 70
(1002278, "BOWMAN", 80), # Dark Falcon - Lv 80
(1002405, "BOWMAN", 90), # Dark Arlic Helmet - Lv 90
(1002407, "BOWMAN", 100), # Blue Arnah Cap - Lv 100
(1002547, "BOWMAN", 110), # Red Hunter - Lv 110
(1004221, "BOWMAN", 120), # Eclectic Rapid - Lv 120
(1004226, "BOWMAN", 130), # Muspell Bowman Hat - Lv 130
(1004231, "BOWMAN", 140), # Pensalir Sentinel Cap - Lv 140

# Gloves
(1082090, "BOWMAN", 60), # Sephia Garner - Lv 60 
(1082111, "BOWMAN", 80), # Green Cordon - Lv 80 
(1004383, "BOWMAN", 70), # Dark Focus Hat\r\n - Lv 75 
(1082127, "BOWMAN", 90), # Dark Focus - Lv 90 
(1082160, "BOWMAN", 100), # Green Arcina - Lv 100 
(1082163, "BOWMAN", 110), # Red Hunter Gloves - Lv 110 
(1082600, "BOWMAN", 120), # Eclectic Presto - Lv 120 
(1082605, "BOWMAN", 130), # Muspell Bowman Gloves - Lv 130 
(1082610, "BOWMAN", 140), # Pensalir Sentinel Gloves - Lv 140 

# Shoes
(1072026, "BOWMAN", 20), # Rabbit Huntertop - Lv 20 
(1072120, "BOWMAN", 40), # Brown Pierre Shoes - Lv 40 
(1072144, "BOWMAN", 60), # Red Gore Boots - Lv 60 
(1072184, "BOWMAN", 80), # Green Wing Boots - Lv 80 
(1072204, "BOWMAN", 90), # Green Ades Shoes - Lv 90 
(1072228, "BOWMAN", 100), # Blue Arnah Shoes - Lv 100 
(1072269, "BOWMAN", 110), # Red Hunter Shoes - Lv 110 
(1072959, "BOWMAN", 120), # Eclectic Rontano - Lv 120 
(1072964, "BOWMAN", 130), # Muspell Bowman Shoes - Lv 130 
(1072969, "BOWMAN", 140), # Pensalir Sentinel Boots - Lv 140 

# Capes
(1102003, "BOWMAN", 50), # White Adventurer Cape - Lv 50
(1102004, "BOWMAN", 50), # Black Adventurer Cape - Lv 50
(1102017, "BOWMAN", 60), # White Magic Cape - Lv 60
(1102018, "BOWMAN", 60), # Black Magic Cape - Lv 60
(1102029, "BOWMAN", 70), # White Seraph Cape - Lv 75
(1102030, "BOWMAN", 70), # Black Seraph Cape - Lv 75
(1102033, "BOWMAN", 80), # Red Giles Cape - Lv 85
(1102034, "BOWMAN", 80), # Blue Giles Cape - Lv 85
(1102720, "BOWMAN", 140), # Pensalir Sentinel Cape - Lv 140 

# Top
(1040008, "BOWMAN", 10), # Brown Archer Top - Lv 10 
(1040003, "BOWMAN", 20), # Brown Hard Leather Top - Lv 20 
(1040024, "BOWMAN", 20), # Blue Bennis Chainmail - Lv 25 
(1041034, "BOWMAN", 20), # Blue Bennis Chainmail - Lv 25 
(1040067, "BOWMAN", 30), # Green Hunter's Armor - Lv 30 
(1040075, "BOWMAN", 30), # Dark Legolier - Lv 35 
(1040146, "BOWMAN", 30), # King Pepe Dark Legolier - Lv 35 
(1040079, "BOWMAN", 40), # Brown Piette - Lv 40 
(1040178, "BOWMAN", 40), # Brown Piette - Lv 40 
(1041008, "BOWMAN", 10), # Green Able Armor - Lv 15 
(1041027, "BOWMAN", 20), # Red Shivermail - Lv 20 
(1041035, "BOWMAN", 20), # Yellow Bennis Chainmail - Lv 25 
(1041054, "BOWMAN", 30), # Green Huntress Armor - Lv 30 
(1041176, "BOWMAN", 30), # Green Huntress Armor - Lv 30 
(1041067, "BOWMAN", 30), # Green Legolia - Lv 35 
(1041081, "BOWMAN", 40), # White Piettra - Lv 40 

# Bottom

(1061006, "BOWMAN", 10), # Green Able Armor Skirt - Lv 15 
(1061025, "BOWMAN", 20), # Red Shivermail Skirt - Lv 20 
(1061062, "BOWMAN", 30), # Green Legolia Pants - Lv 35 
(1060056, "BOWMAN", 30), # Green Hunter's Pants - Lv 30 
(1060065, "BOWMAN", 30), # Brown Legolier Pants - Lv 35 
(1060069, "BOWMAN", 40), # Brown Piette Pants - Lv 40 
(1061080, "BOWMAN", 40), # White Piettra Skirt - Lv 40
 
# Overall
(1050052, "BOWMAN", 50), # Blue-Lined Kismet - Lv 50 
(1050202, "BOWMAN", 50), # Blue-Lined Kismet - Lv 50 
(1051037, "BOWMAN", 50), # Blue Lumati - Lv 50 
(1050059, "BOWMAN", 60), # Blue Tai - Lv 60 
(1051041, "BOWMAN", 60), # Red Choro - Lv 60 
(1050061, "BOWMAN", 70), # Blue Linnex - Lv 70 
(1051062, "BOWMAN", 70), # Blue Lineros - Lv 70 
(1050075, "BOWMAN", 80), # Red Pris - Lv 80 
(1051066, "BOWMAN", 80), # Red Pria - Lv 80 
(1050088, "BOWMAN", 90), # Red Ades - Lv 90 
(1072203, "BOWMAN", 90), # Red Ades Shoes - Lv 90 
(1051082, "BOWMAN", 90), # Red Anes - Lv 90 
(1050107, "BOWMAN", 100), # Blue Arzuna - Lv 100 
(1051106, "BOWMAN", 100), # Blue Armis - Lv 100 
(1052071, "BOWMAN", 110), # Red Mantle - Lv 110 
(1052791, "BOWMAN", 120), # Eclectic Evernew - Lv 120 
(1052796, "BOWMAN", 130), # Muspell Bowman Suit - Lv 130 
(1052801, "BOWMAN", 140), # Pensalir Sentinel Suit - Lv 140 
 
# Thief

(1002125, "THIEF", 10), # Black Ghetto Beanie - Lv 10
(1002130, "THIEF", 20), # Black Loosecap - Lv 20
(1002175, "THIEF", 30), # Dark Guise - Lv 30
(1002185, "THIEF", 40), # Dark Pilfer - Lv 40
(1002207, "THIEF", 50), # Red Sonata - Lv 50
(1002249, "THIEF", 60), # Dark Identity - Lv 60
(1002284, "THIEF", 70), # White Nightfox - Lv 70
(1002330, "THIEF", 80), # Dark Pireta Hat - Lv 80
(1002326, "THIEF", 90), # Red Osfa Hat - Lv 90
(1002383, "THIEF", 100), # Dark Canal Hood - Lv 100
(1002550, "THIEF", 110), # Black Garina Hood - Lv 110
(1004222, "THIEF", 120), # Eclectic Chive - Lv 120
(1004227, "THIEF", 130), # Muspell Thief Hat - Lv 130
(1004232, "THIEF", 140), # Pensalir Chaser Hat - Lv 140

# Gloves
(1082029, "THIEF", 10), # Brown Duo - Lv 15 
(1082093, "THIEF", 60), # Steal Pow - Lv 60 
(1082120, "THIEF", 80), # Blood Larceny - Lv 80 
(1082138, "THIEF", 100), # Dark Anelin - Lv 100 
(1082167, "THIEF", 110), # Black Garina Gloves - Lv 110 
(1082601, "THIEF", 120), # Eclectic Lubav - Lv 120 
(1082606, "THIEF", 130), # Muspell Thief Gloves - Lv 130 
(1082611, "THIEF", 140), # Pensalir Chaser Gloves - Lv 140 

# Shoes
(1072065, "THIEF", 20), # Red Enamel Boots - Lv 20 
(1072107, "THIEF", 40), # Black Red-Lined Shoes - Lv 40 
(1072592, "THIEF", 40), # Black Red-Lined Shoes - Lv 40 
(1072151, "THIEF", 60), # Gold Moss Boots - Lv 60 
(1072174, "THIEF", 80), # Dark Pirate Boots - Lv 80 
(1072216, "THIEF", 100), # Dark Katina Boots - Lv 100 
(1072272, "THIEF", 110), # Black Garina Shoes - Lv 110 
(1072960, "THIEF", 120), # Eclectic Moonsteed - Lv 120 
(1072965, "THIEF", 130), # Muspell Thief Shoes - Lv 130 
(1072970, "THIEF", 140), # Pensalir Chaser Boots - Lv 140 

# Capes
(1102003, "THIEF", 50), # White Adventurer Cape - Lv 50
(1102004, "THIEF", 50), # Black Adventurer Cape - Lv 50
(1102017, "THIEF", 60), # White Magic Cape - Lv 60
(1102018, "THIEF", 60), # Black Magic Cape - Lv 60
(1102029, "THIEF", 70), # White Seraph Cape - Lv 75
(1102030, "THIEF", 70), # Black Seraph Cape - Lv 75
(1102033, "THIEF", 80), # Red Giles Cape - Lv 85
(1102034, "THIEF", 80), # Blue Giles Cape - Lv 85
(1102721, "THIEF", 140), # Pensalir Chaser Cape - Lv 140 

# Top/Bottom
(1040033, "THIEF", 10), # Black Cloth Vest - Lv 10 
(1040034, "THIEF", 10), # Dark Nightshift - Lv 15 
(1060024, "THIEF", 10), # Dark Nightshift Pants - Lv 15 
(1040044, "THIEF", 20), # Black Pao - Lv 20 
(1060033, "THIEF", 20), # Black Pao Bottom - Lv 20 
(1040048, "THIEF", 20), # Brown Sneak - Lv 25 
(1060037, "THIEF", 20), # Dark Brown Sneak Pants - Lv 25 
(1060038, "THIEF", 20), # Brown Sneak Pants - Lv 25 
(1060160, "THIEF", 20), # Dark Brown Sneak Pants - Lv 25 
(1040057, "THIEF", 30), # Dark Brown Stealer - Lv 30 
(1060043, "THIEF", 30), # Dark Brown Stealer Pants - Lv 30 
(1040061, "THIEF", 30), # Green Knucklevest - Lv 35 
(1040083, "THIEF", 40), # Marine Shadow - Lv 40 
(1040094, "THIEF", 50), # Red China - Lv 50 
(1060083, "THIEF", 50), # Red China Pants - Lv 50 
(1040099, "THIEF", 60), # Ocher Scorpio - Lv 60 
(1060088, "THIEF", 60), # Ocher Scorpio Pants - Lv 60 
(1040107, "THIEF", 70), # Dark Studded Top - Lv 70 
(1040109, "THIEF", 80), # Red Pirate Top - Lv 80 
(1040116, "THIEF", 90), # Brown Osfa Suit - Lv 90 
(1041116, "THIEF", 90), # Brown Osfa Suit - Lv 90 
(1041003, "THIEF", 20), # Red Qi Pao - Lv 20 
(1061003, "THIEF", 20), # Red Qi Pao Pants - Lv 20 
(1061040, "THIEF", 20), # Red Qi Pao Skirt - Lv 22 
(1041095, "THIEF", 60), # Bloody Mantis - Lv 60 
(1061094, "THIEF", 60), # Bloody Mantis Pants - Lv 60 
(1041101, "THIEF", 70), # Blue Mystique - Lv 70 
(1061100, "THIEF", 70), # Blue Mystique Pants - Lv 70 
(1072162, "THIEF", 70), # Blue Mystique Shoes - Lv 70 
(1041106, "THIEF", 80), # Red Pirate Blouse - Lv 80 

# Overall
(1050097, "THIEF", 100), # Blue Katinas - Lv 100 
(1051091, "THIEF", 100), # Blue Katte - Lv 100 
(1052072, "THIEF", 110), # Black Garina - Lv 110 
(1052792, "THIEF", 120), # Eclectic Prinsid - Lv 120 
(1052797, "THIEF", 130), # Muspell Thief Suit - Lv 130 
(1052802, "THIEF", 140), # Pensalir Chaser Armor - Lv 140 

# Pirate

(1002610, "PIRATE", 10), # Brown Rocky Bandana - Lv 10
(1002616, "PIRATE", 20), # Brown Double Marine - Lv 20
(1002622, "PIRATE", 30), # White Oceania Cap - Lv 30
(1002628, "PIRATE", 40), # Red Misty - Lv 40
(1002631, "PIRATE", 50), # Brown Leather Ocean Hat - Lv 50
(1002634, "PIRATE", 60), # Purple Cast Linen - Lv 60
(1002637, "PIRATE", 70), # Black Pirate's Bandana - Lv 70
(1002640, "PIRATE", 80), # Blue Sun Boat Hat - Lv 80
(1002643, "PIRATE", 90), # Red Brave Hamal - Lv 90
(1002646, "PIRATE", 100), # Black Polax Hat - Lv 100
(1002649, "PIRATE", 110), # Canopus Hat - Lv 110
(1004223, "PIRATE", 120), # Eclectic Conrad Henkel - Lv 120
(1004228, "PIRATE", 130), # Muspell Pirate Hat - Lv 130
(1004233, "PIRATE", 140), # Pensalir Skipper Hat - Lv 140

# Gloves
(1082355, "PIRATE", 20), # Brown Leather Bracers - Lv 20 
(1082195, "PIRATE", 40), # Brown Barbee - Lv 40 
(1082201, "PIRATE", 60), # Black Schult - Lv 60 
(1082207, "PIRATE", 80), # Blue Halfgloves - Lv 80 
(1082213, "PIRATE", 100), # Black Skellduke - Lv 100 
(1082216, "PIRATE", 110), # Canopus Gloves - Lv 110 
(1082602, "PIRATE", 120), # Eclectic Charlston - Lv 120 
(1082607, "PIRATE", 130), # Muspell Pirate Gloves - Lv 130 
(1082612, "PIRATE", 140), # Pensalir Skipper Gloves - Lv 140 

# Shoes
(1072288, "PIRATE", 20), # Brown Skeedy Sandals - Lv 20 
(1072300, "PIRATE", 40), # Brown Leather Krag - Lv 40 
(1072306, "PIRATE", 60), # Black Basile Boots - Lv 60 
(1072312, "PIRATE", 80), # Blue Pageant - Lv 80 
(1072318, "PIRATE", 100), # Black Duke Barkin Shoes - Lv 100 
(1072321, "PIRATE", 110), # Canopus Boots - Lv 110 
(1072961, "PIRATE", 120), # Eclectic Faraon - Lv 120 
(1072966, "PIRATE", 130), # Muspell Pirate Shoes - Lv 130 
(1072971, "PIRATE", 140), # Pensalir Skipper Boots - Lv 140 

# Capes
(1102003, "PIRATE", 50), # White Adventurer Cape - Lv 50
(1102004, "PIRATE", 50), # Black Adventurer Cape - Lv 50
(1102017, "PIRATE", 60), # White Magic Cape - Lv 60
(1102018, "PIRATE", 60), # Black Magic Cape - Lv 60
(1102029, "PIRATE", 70), # White Seraph Cape - Lv 75
(1102030, "PIRATE", 70), # Black Seraph Cape - Lv 75
(1102033, "PIRATE", 80), # Red Giles Cape - Lv 85
(1102034, "PIRATE", 80), # Blue Giles Cape - Lv 85
(1102722, "PIRATE", 140), # Pensalir Skipper Cape - Lv 140 

# Overall

(1052095, "PIRATE", 10), # Brown Rocky Suit - Lv 10 
(1052098, "PIRATE", 10), # Brown Cotton Lagger - Lv 15 
(1052101, "PIRATE", 20), # Beige Carribean - Lv 20 
(1052107, "PIRATE", 30), # Brown Pollard - Lv 30 
(1052113, "PIRATE", 40), # Red Barbay - Lv 40 
(1052116, "PIRATE", 50), # Green Plasteer - Lv 50 
(1050313, "PIRATE", 60), # Black Royal Barone - Lv 60 
(1052122, "PIRATE", 70), # Red Viska - Lv 70 
(1052125, "PIRATE", 80), # White Pioneer - Lv 80 
(1052128, "PIRATE", 90), # White Marquini - Lv 90 
(1052131, "PIRATE", 100), # Red Belly Duke - Lv 100 
(1052134, "PIRATE", 110), # Canopus Suit - Lv 110 
(1052793, "PIRATE", 120), # Eclectic Burgunt - Lv 120 
(1052798, "PIRATE", 130), # Muspell Pirate Suit - Lv 130 
(1052803, "PIRATE", 140); # Pensalir Skipper Coat - Lv 140 
