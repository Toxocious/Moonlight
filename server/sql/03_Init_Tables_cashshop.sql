drop table if exists cs_categories;
drop table if exists cs_items;

create table cs_categories (
	id int not null auto_increment,
    idx int,
    name varchar(255),
    flag int,
    parentIdx int,
    stock int,
    primary key (id)
);

create table cs_items (
	id int not null auto_increment,
    itemID int not null,
    stock int default 1,
    shopItemFlag int default 0,
    idk1 int default 0,
    idk2 int default 0,
    oldPrice int default 0,
    newPrice int default 0,
    idkTime1 datetime,
    idkTime2 datetime,
    idkTime3 datetime,
    idkTime4 datetime,
    idk3 int default 0,
    bundleQuantity int default 0,
    availableDays int default 0,
    buyableWithMaplePoints smallint default 1,
    buyableWithCredit smallint default 1,
    buyableWithPrepaid smallint default 1,
    likable smallint default 1,
    meso smallint default 0,
    favoritable smallint default 1,
    gender int default 2,
    likes int default 0,
    requiredLevel int default 0,
    idk10 varchar(255),
    idk11 int default 0,
    idk13 int default 0,
    idk14 int default 0,
    category varchar(255),
    primary key (id)
);

insert into `cs_categories` (`idx`, `name`, `parentIdx`, `stock`) values ('2000000', 'Favourite', '0', '100'),


('1010000', 'Special Promotions', '0', '100'),
('1010100', 'New Arrivals', '0', '100'),
('1010200', 'Discounted', '0', '100'),
('1010300', 'Limited Time', '0', '100'),
('1010400', 'Limited Quantity', '0', '100'),
('1010500', 'Daily Deals', '0', '100'),
('1010600', 'Maple Rewards Shop', '0', '100'),


('1020000', 'Time Savers', '0', '100'),
('1020100', 'Teleport Rocks', '0', '100'),
('1020200', 'Item Stores', '0', '100'),
('1020300', 'Quest Helpers', '0', '100'),
('1020400', 'Dungeon Passes', '0', '100'),


('1030000', 'Random Rewards', '0', '100'),
('1030100', 'Gachapon Tickets', '0', '100'),
('1030200', 'Surprise Boxes', '0', '100'),
('1030300', 'Special Items', '0', '100'),
('1030400', 'Meso Sacks', '0', '100'),


('1040000', 'Equipment Modifications', '0', '100'),
('1040100', 'Miracle Cubes', '0', '100'),
('1040200', 'Scrolls', '0', '100'),
('1040300', 'Upgrade Slots', '0', '100'),
('1040400', 'Trade', '0', '100'),
('1040500', 'Other', '0', '100'),
('1040501', 'Item Tag', '0', '100'),
('1040502', 'Item Guards', '0', '100'),
('1040600', 'Duration', '0', '100'),
('1040700', 'Bypass Keys', '0', '100'),
('1040800', 'Fusion Anvils', '0', '100'),


('1050000', 'Character Modifications', '0', '100'),
('1050100', 'SP/AP modifications', '0', '100'),
('1050200', 'EXP Coupons', '0', '100'),
('1050300', 'Drop Coupons', '0', '100'),
('1050400', 'Inventory slots', '0', '100'),
('1050500', 'Skill Modifications', '0', '100'),
('1050600', 'Protection', '0', '100'),
('1050700', 'Wedding', '0', '100'),
('1050800', 'Other', '0', '100'),
('1050900', 'Mount', '0', '100'),


('1060000', 'Equipment', '0', '100'),
('1060100', 'Weapon', '0', '100'),
('1060200', 'Weapon 2', '0', '100'),
('1060300', 'Weapon 3', '0', '100'),
('1060400', 'Weapon 4', '0', '100'),
('1060500', 'Hat', '0', '100'),
('1060600', 'Hat 2', '0', '100'),
('1060700', 'Hat 3', '0', '100'),
('1060800', 'Hat 4', '0', '100'),
('1060900', 'Hat 5', '0', '100'),
('1061000', 'Hat 6', '0', '100'),
('1061100', 'Hat 7', '0', '100'),
('1061200', 'Hat 8', '0', '100'),
('1061300', 'Face', '0', '100'),
('1061400', 'Eye', '0', '100'),
('1061500', 'Accessory', '0', '100'),
('1061600', 'Earrings', '0', '100'),
('1061700', 'Overall', '0', '100'),
('1061800', 'Overall 2', '0', '100'),
('1061900', 'Overall 3', '0', '100'),
('1062000', 'Overall 4', '0', '100'),
('1062100', 'Overall 5', '0', '100'),
('1062200', 'Overall 6', '0', '100'),
('1062300', 'Top', '0', '100'),
('1062400', 'Top 2', '0', '100'),
('1062500', 'Bottom', '0', '100'),
('1062600', 'Bottom 2', '0', '100'),
('1062700', 'Shoes', '0', '100'),
('1062800', 'Shoes 2', '0', '100'),
('1062900', 'Shoes 3', '0', '100'),
('1063000', 'Glove', '0', '100'),
('1063100', 'Ring', '0', '100'),
('1063200', 'Ring 2', '0', '100'),
('1063300', 'Cape', '0', '100'),
('1063400', 'Cape 2', '0', '100'),
('1063500', 'Cape 3', '0', '100'),


('1064000', 'Transparent', '0', '100'),


('1070000', 'Appearance', '0', '100'),
('1070100', 'Beauty Parlor', '0', '100'),
('1070101', 'Hair', '0', '100'),
('1070102', 'Face', '0', '100'),
('1070103', 'Skin', '0', '100'),
('1070200', 'Facial Expressions', '0', '100'),
('1070300', 'Effect', '0', '100'),
('1070400', 'Transformations', '0', '100'),
('1070500', 'Special', '0', '100'),


('1080000', 'Pet', '0', '100'),
('1080100', 'Pets', '0', '100'),
('1080200', 'Pets 2', '0', '100'),
('1080300', 'Pet Appearance', '0', '100'),
('1080400', 'Pet Appearance 2', '0', '100'),
('1080500', 'Pet Use', '0', '100'),
('1080600', 'Pet Food', '0', '100'),
('1080700', 'Pet Skills', '0', '100'),


('1090000', 'Messenger and Social', '0', '100'),
('1090100', 'Megaphones', '0', '100'),
('1090200', 'Messengers', '0', '100'),
('1090300', 'Weather Effects', '0', '100'),
('1090301', 'Stats', '0', '100'),
('1090302', 'Non-Stat', '0', '100'),
('1090400', 'Other', '0', '100');





/*		TIME SAVERS		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5040004', '9900', 'Teleport Rocks'),      /*Hyper Teleport Rock*/



/*Page 1*/
('5450004', '1000', 'Item Stores'),      /*Traveling Merchant (30-day)*/
('5450005', '1000', 'Item Stores'),      /*Portable Storage (30-day)*/
('5450006', '400', 'Item Stores'),      /*Traveling Merchant (1-day)*/
('5450008', '400', 'Item Stores'),      /*Portable Storage (1-day)*/



/*Page 1*/
('5252030', '300', 'Dungeon Passes');      /*Monster Park Additional Entry Ticket*/





/*		RANDOM REWARDS		*/

/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5680021', '2500', 'Gachapon Tickets');      /*Chair Gachapon Ticket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5680021', '25000', 'Gachapon Tickets', '11');      /*Chair Gachapon Ticket (11)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5451000', '1050', 'Gachapon Tickets');      /*Remote Gachapon Ticket*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5451000', '10500', 'Gachapon Tickets', '11');      /*Remote Gachapon Ticket (11)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5220000', '10000', 'Gachapon Tickets', '11');      /*Gachapon Ticket (11)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5220000', '1000', 'Gachapon Tickets'),      /*Gachapon Ticket*/



/*Page 1*/
('5222060', '3400', 'Surprise Boxes');      /*Premium Surprise Style Box*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5222060', '34000', 'Surprise Boxes', '11');      /*Premium Surprise Style Box (11)*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5222006', '2100', 'Surprise Boxes');      /*Surprise Style Box*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5222006', '21000', 'Surprise Boxes', '11');      /*Surprise Style Box (11)*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5202000', '1000', 'Meso Sacks'),      /*Rare Meso Sack*/
('5202001', '3000', 'Meso Sacks'),      /*Unique Meso Sack*/
('5202002', '2000', 'Meso Sacks'),      /*Epic Meso Sack*/





/*		EQUIPMENT MODIFICATIONS		*/


/* CUBES */

/*Page 1*/
('5062009', '1000', 'Miracle Cubes'), 	# Red Cube
('5062010', '3000', 'Miracle Cubes'), 	# Black Cube
('5062024', '10000', 'Miracle Cubes'), 	# Violet Cube
('5062500', '5000', 'Miracle Cubes'), 	# Bonus Potential Cube

# Scrolls

# Upgrade Slots

# Trade

# Other

/*Page 1*/
('5700000', '1200', 'Other'),      /*Android Naming Coupon*/
('5060000', '1800', 'Other'),      /*Item Tag*/

# Other -> Item Tag

/*Page 1*/
('5700000', '1200', 'Item Tag'),      /*Android Naming Coupon*/
('5060000', '1800', 'Item Tag'),      /*Item Tag*/

# Other -> Item Guards

# Duration

# Bypass Keys

# Fusion Anvils


/*		CHARACTER MODIFICATIONS		*/

/*Page 1*/
('5430000', '6900', 'Inventory slots'),      /*Extra Character Slot Coupon*/
('9110000', '5000', 'Inventory slots'),      /*Add Storage Slots*/
('9111000', '5000', 'Inventory slots'),      /*Add Equip Slots*/
('9112000', '5000', 'Inventory slots'),      /*Add Use Slots*/
('9114000', '5000', 'Inventory slots'),      /*Add ETC Slots*/
('9113000', '5000', 'Inventory slots'),      /*Add Set-up Slots*/





/*		EQUIPMENT		*/

/*  Weapon  */
/*Page 1*/
('1701000', '7600', 'Weapon'),	/*Elizabeth Fan*/
('1702009', '5600', 'Weapon'),	/*Tiger Paw*/
('1702008', '6400', 'Weapon'),	/*Santa Sack*/
('1702011', '5200', 'Weapon'),	/*Pink Toy Hammer*/
('1702010', '3600', 'Weapon'),	/*Orange Toy Hammer*/
('1702013', '5200', 'Weapon'),	/*Teddy Bear*/
('1702012', '4700', 'Weapon'),	/*Yellow Spatula*/
('1702015', '5000', 'Weapon'),	/*Bug Net*/
('1702014', '6400', 'Weapon'),	/*Toy RIfle*/
/*Page 2*/
('1702001', '7100', 'Weapon'),	/*Bouquet*/
('1702000', '3400', 'Weapon'),	/*Dual Plasma Blade*/
('1702003', '4000', 'Weapon'),	/*Plastic Slingshot*/
('1702002', '8800', 'Weapon'),	/*Wooden Slingshot*/
('1702005', '6300', 'Weapon'),	/*Yellow Candy Cane*/
('1702004', '6400', 'Weapon'),	/*Angel Wand*/
('1702007', '6300', 'Weapon'),	/*Green Candy Cane*/
('1702006', '4900', 'Weapon'),	/*Red Candy Cane*/
('1702041', '6400', 'Weapon'),	/*Horoscope Sword*/
/*Page 3*/
('1702040', '5600', 'Weapon'),	/*Horoscope Bow*/
('1702043', '6000', 'Weapon'),	/*Poo Stick*/
('1702042', '4900', 'Weapon'),	/*Microphone*/
('1702045', '6300', 'Weapon'),	/*Sunflower Stalk*/
('1702044', '3200', 'Weapon'),	/*Toy Machine Gun*/
('1702047', '3600', 'Weapon'),	/*Snowflake Staff*/
('1702046', '3800', 'Weapon'),	/*Horoscope Crossbow*/
('1702033', '4700', 'Weapon'),	/*Sun Quan Staff*/
('1702032', '7400', 'Weapon'),	/*Zhu-Ge-Liang Wand*/
/*Page 4*/
('1702035', '5600', 'Weapon'),	/*Cao Cao Bow*/
('1702034', '3200', 'Weapon'),	/*Guan Yu Spear*/
('1702037', '7100', 'Weapon'),	/*Coffee Pot*/
('1702036', '3200', 'Weapon'),	/*Witch's Broomstick*/
('1702039', '7100', 'Weapon'),	/*Horoscope Net*/
('1702038', '8800', 'Weapon'),	/*Horoscope Claw*/
('1702025', '7600', 'Weapon'),	/*Cherub's Bow*/
('1702024', '3400', 'Weapon'),	/*Cupid's Bow*/
('1702027', '4900', 'Weapon'),	/*Blazing Sword*/
/*Page 5*/
('1702026', '5200', 'Weapon'),	/*Cupid's Crossbow*/
('1702029', '4900', 'Weapon'),	/*White Rabbit's Foot*/
('1702028', '3200', 'Weapon'),	/*Donut*/
('1702031', '3200', 'Weapon'),	/*Liu Bei Sword*/
('1702030', '6300', 'Weapon'),	/*Diao Chan Sword*/
('1702017', '4700', 'Weapon'),	/*Pink Rabbit Puppet*/
('1702016', '3600', 'Weapon'),	/*Picnic Basket*/
('1702019', '7600', 'Weapon'),	/*Pillow*/
('1702018', '4700', 'Weapon'),	/*Vanilla Ice Cream*/
/*Page 6*/
('1702021', '7400', 'Weapon'),	/*Black Electric Guitar*/
('1702020', '3200', 'Weapon'),	/*Lollipop*/
('1702023', '7600', 'Weapon'),	/*Green Electric Guitar*/
('1702022', '5600', 'Weapon'),	/*Brown Electric Guitar*/
('1702073', '4900', 'Weapon'),	/*Blue Shiner Crossbow*/
('1702072', '5600', 'Weapon'),	/*Laser Sword*/
('1702075', '7100', 'Weapon'),	/*USA Cheer Towel*/
('1702074', '3200', 'Weapon'),	/*Pink Shiner Crossbow*/
('1702077', '3200', 'Weapon'),	/*Australia Cheer Towel*/
/*Page 7*/
('1702076', '5200', 'Weapon'),	/*Mexico Cheer Towel*/
('1702079', '6400', 'Weapon'),	/*Blue Blazing Sword*/
('1702078', '3400', 'Weapon'),	/*Fairy Fan*/
('1702065', '4700', 'Weapon'),	/*Paper Stick*/
('1702064', '4900', 'Weapon'),	/*Rock Stick*/
('1702067', '6000', 'Weapon'),	/*England Cheer Towel*/
('1702066', '4900', 'Weapon'),	/*Canvas Tote Bag*/
('1702069', '3200', 'Weapon'),	/*Brazil Cheer Towel*/
('1702068', '3400', 'Weapon'),	/*France Cheer Towel*/
/*Page 8*/
('1702071', '3600', 'Weapon'),	/*Japan Cheer Towel*/
('1702070', '3200', 'Weapon'),	/*Sporty Band*/
('1702057', '8800', 'Weapon'),	/*Blue Guitar*/
('1702056', '4900', 'Weapon'),	/*Guitar*/
('1702059', '8800', 'Weapon'),	/*Cactus*/
('1702058', '4900', 'Weapon'),	/*Big Hand*/
('1702061', '4900', 'Weapon'),	/*Red Fist of Fury*/
('1702060', '5000', 'Weapon'),	/*Shiner*/
('1702063', '7400', 'Weapon'),	/*Scissor Stick*/
/*Page 9*/
('1702062', '8800', 'Weapon'),	/*Blue Fist of Fury*/
('1702049', '4900', 'Weapon'),	/*Snowman Claw*/
('1702048', '5600', 'Weapon'),	/*Green Wash Cloth*/
('1702051', '8800', 'Weapon'),	/*Hong Bao*/
('1702050', '8800', 'Weapon'),	/*Cellphone*/
('1702053', '4000', 'Weapon'),	/*In-Hand FB Helmet(Away)*/
('1702052', '3600', 'Weapon'),	/*In-Hand FB Helmet(Home)*/
('1702055', '4700', 'Weapon'),	/*Ancient Korean Bow*/
('1702054', '3400', 'Weapon'),	/*Football Claw*/
/*Page 10*/
('1702105', '6000', 'Weapon'),	/*Heart Key*/
('1702104', '3200', 'Weapon'),	/*Deluxe Cone*/
('1702107', '4000', 'Weapon'),	/*Chocolate*/
('1702106', '5200', 'Weapon'),	/*Melting Chocolate*/
('1702108', '4000', 'Weapon'),	/*Giant Lollipop*/
('1702097', '4900', 'Weapon'),	/*Fire Katana*/
('1702096', '8800', 'Weapon'),	/*Pizza Pan*/
('1702099', '7400', 'Weapon'),	/*Transparent Claw*/
('1702098', '7100', 'Weapon'),	/*Violin*/
/*Page 11*/
('1702101', '4700', 'Weapon'),	/*Meso Gunner*/
('1702100', '4900', 'Weapon'),	/*Christmas Bell*/
('1702103', '3200', 'Weapon'),	/*Pink Ribbon Umbrella*/
('1702102', '7400', 'Weapon'),	/*Stellar Staff*/
('1702089', '5000', 'Weapon'),	/*Candy Hammer*/
('1702088', '4000', 'Weapon'),	/*Super Scrubber*/
('1702091', '3600', 'Weapon'),	/*Tennis Racquet*/
('1702090', '3200', 'Weapon'),	/*Feather Scimitar*/
('1702093', '6400', 'Weapon'),	/*Okie Donkie*/
/*Page 12*/
('1702092', '4000', 'Weapon'),	/*Glowing Pumpkin Basket*/
('1702095', '3800', 'Weapon'),	/*Frog Glove*/
('1702094', '6300', 'Weapon'),	/*Mad Cow*/
('1702081', '7600', 'Weapon'),	/*Purple Blazing Sword*/
('1702080', '4900', 'Weapon'),	/*Green Blazing Sword*/
('1702083', '6000', 'Weapon'),	/*Foam Hand*/
('1702082', '5200', 'Weapon'),	/*Harp*/
('1702085', '3200', 'Weapon'),	/*Frog Claw*/
('1702084', '3600', 'Weapon'),	/*Toy Pinwheel*/
/*Page 13*/
('1702087', '6000', 'Weapon'),	/*Red Pencil*/
('1702086', '4700', 'Weapon'),	/*Chicken Smackaroo*/
('1702136', '7100', 'Weapon'),	/*Ice Flower*/
('1702139', '4900', 'Weapon'),	/*Hook Hand*/
('1702138', '8800', 'Weapon'),	/*Spanish Ham*/
('1702141', '3800', 'Weapon'),	/*My Buddy Max*/
('1702140', '5200', 'Weapon'),	/*Giant Orchid*/
('1702143', '4900', 'Weapon'),	/*Combat Syringe*/
('1702142', '5200', 'Weapon'),	/*Pink Angel Stick*/
/*Page 14*/
('1702129', '4900', 'Weapon'),	/*Purple Shiner*/
('1702128', '3200', 'Weapon'),	/*Green Shiner*/
('1702131', '4700', 'Weapon'),	/*Pepe Beak*/
('1702130', '5000', 'Weapon'),	/*Red Shiner*/
('1702133', '7600', 'Weapon'),	/*Smackdown Fist */
('1702132', '3400', 'Weapon'),	/*Slime Stick*/
('1702135', '6300', 'Weapon'),	/*Vengence Claw*/
('1702134', '5000', 'Weapon'),	/*Serpent Staff */
('1702121', '6000', 'Weapon'),	/*Seal Pillow*/
/*Page 15*/
('1702120', '7400', 'Weapon'),	/*Veamoth Sword*/
('1702123', '6000', 'Weapon'),	/*Forked Pork*/
('1702122', '7600', 'Weapon'),	/*Dragon's Fury*/
('1702125', '3400', 'Weapon'),	/*Heart Cane*/
('1702124', '4000', 'Weapon'),	/*Kitty*/
('1702127', '5600', 'Weapon'),	/*Water Gun*/
('1702126', '6300', 'Weapon'),	/*Blue Shiner */
('1702113', '3200', 'Weapon'),	/*Maoster Pole Arm*/
('1702112', '3200', 'Weapon'),	/*Celestial Wand*/
/*Page 16*/
('1702115', '5600', 'Weapon'),	/*Red Rose*/
('1702114', '8800', 'Weapon'),	/*Wonky's Leaf*/
('1702117', '5000', 'Weapon'),	/*Jie 2*/
('1702116', '5200', 'Weapon'),	/*Jie 1*/
('1702119', '7600', 'Weapon'),	/*Sachiel Sword*/
('1702118', '6300', 'Weapon'),	/*Janus Sword*/
('1702169', '6000', 'Weapon'),	/*My Buddy Tina*/
('1702168', '7600', 'Weapon'),	/*Holiday Tree Ring*/
('1702171', '4700', 'Weapon'),	/*Party Popper*/
/*Page 17*/
('1702170', '3600', 'Weapon'),	/*Electric Knuckle*/
('1702173', '5000', 'Weapon'),	/*Hessonite Saber*/
('1702172', '5600', 'Weapon'),	/*Bluebird*/
('1702175', '6300', 'Weapon'),	/*Hot Dog Link*/
('1702174', '6400', 'Weapon'),	/*Butterfly Staff*/
('1702161', '7600', 'Weapon'),	/*Dogged Out*/
('1702160', '4700', 'Weapon'),	/*Tiger Paw Knuckle*/
('1702163', '4000', 'Weapon'),	/*Hot Dog Fork*/
('1702162', '4900', 'Weapon'),	/*Koala Doll*/
/*Page 18*/
('1702165', '4000', 'Weapon'),	/*My Buddy DJ*/
('1702164', '3600', 'Weapon'),	/*Bunny Nunchucks*/
('1702167', '6000', 'Weapon'),	/*Glow Fingers*/
('1702166', '7400', 'Weapon'),	/*Holiday Candy Cane*/
('1702153', '3600', 'Weapon'),	/*Crissagrim Blade*/
('1702152', '8800', 'Weapon'),	/*Flame Tongue*/
('1702155', '3800', 'Weapon'),	/*Shooting Star*/
('1702154', '5600', 'Weapon'),	/*Plasma Saber*/
('1702157', '6300', 'Weapon'),	/*Burning Marshmellow*/
/*Page 19*/
('1702156', '6000', 'Weapon'),	/*Forked Turkey*/
('1702159', '3400', 'Weapon'),	/*Blackbeard's Knuckle*/
('1702158', '4000', 'Weapon'),	/*The Jackal*/
('1702145', '3800', 'Weapon'),	/*Bionic Claw*/
('1702144', '5600', 'Weapon'),	/*Broken Sword*/
('1702147', '5600', 'Weapon'),	/*Skull Axe*/
('1702146', '3800', 'Weapon'),	/*Skull Staff*/
('1702149', '7400', 'Weapon'),	/*Tania Sword*/
('1702148', '8800', 'Weapon'),	/*Moon Baton*/
/*Page 20*/
('1702151', '7100', 'Weapon'),	/*Royal Oaken Staff*/
('1702150', '5200', 'Weapon'),	/*Mercury Sword*/
('1702201', '5200', 'Weapon'),	/*Bone Weapon*/
('1702200', '6400', 'Weapon'),	/*Plastic umbrella*/
('1702203', '5000', 'Weapon'),	/*Halloween Teddy*/
('1702202', '3800', 'Weapon'),	/*Baby Ellie*/
('1702204', '8800', 'Weapon'),	/*Japanese War Fan*/
('1702207', '6300', 'Weapon'),	/*Musical Violin*/
('1702193', '7600', 'Weapon'),	/*Towel Whip*/
/*Page 21*/
('1702195', '7100', 'Weapon'),	/*MapleGirl Wand*/
('1702197', '3600', 'Weapon'),	/*Tsunami Wave*/
('1702196', '4000', 'Weapon'),	/*Fly Blue Bird*/
('1702198', '3800', 'Weapon'),	/*Bullseye Board*/
('1702185', '8800', 'Weapon'),	/*White & Yellow Seraphim*/
('1702184', '3400', 'Weapon'),	/*Aqua Seraphim*/
('1702187', '6300', 'Weapon'),	/*Patriot Seraphim*/
('1702186', '4700', 'Weapon'),	/*3rd Anniversary Weapon*/
('1702189', '7400', 'Weapon'),	/*Crabby*/
/*Page 22*/
('1702188', '6000', 'Weapon'),	/*Pink Seraphim*/
('1702191', '7600', 'Weapon'),	/*Rainbow Sabre*/
('1702190', '7100', 'Weapon'),	/*Transparent Knuckle*/
('1702177', '4700', 'Weapon'),	/*Power Pesticide*/
('1702179', '3400', 'Weapon'),	/*Cloud 9 Pillow*/
('1702178', '6300', 'Weapon'),	/*MDAS Weapon*/
('1702181', '6300', 'Weapon'),	/*Picky Ducky*/
('1702180', '7600', 'Weapon'),	/*Dark Seraphim*/
('1702183', '4900', 'Weapon'),	/*Sunset Seraphim*/
/*Page 23*/
('1702182', '3600', 'Weapon'),	/*Giant Pop with a Swirl*/
('1702233', '4000', 'Weapon'),	/*Rainbow Brush*/
('1702232', '5200', 'Weapon'),	/*My friend Gold Bulldog*/
('1702235', '7100', 'Weapon'),	/*Metallic Arm*/
('1702234', '3400', 'Weapon'),	/*Pluto Legend Hall*/
('1702237', '4900', 'Weapon'),	/*Inari the White Fox*/
('1702236', '7600', 'Weapon'),	/*Death Note*/
('1702239', '8800', 'Weapon'),	/*Holy Mystics*/
('1702238', '3600', 'Weapon'),	/*Soft Plush Dolphin*/
/*Page 24*/
('1702224', '3600', 'Weapon'),	/*Transparent Weapon*/
('1702226', '3800', 'Weapon'),	/*My Buddy Whale*/
('1702229', '7400', 'Weapon'),	/*Demon Sickle*/
('1702228', '8800', 'Weapon'),	/*Choco Banana*/
('1702231', '8800', 'Weapon'),	/*We Care! Weapon*/
('1702230', '6000', 'Weapon'),	/*Popsicle Sword*/
('1702217', '3800', 'Weapon'),	/*Ducky Tube*/
('1702216', '5200', 'Weapon'),	/*Magic Heart Stick*/
('1702219', '7100', 'Weapon'),	/*Knockout Boxing Gloves*/
/*Page 25*/
('1702218', '6300', 'Weapon'),	/*Dumbell Weapon*/
('1702221', '3400', 'Weapon'),	/*Mini Bean Propeller*/
('1702220', '4700', 'Weapon'),	/*Transparent Wand*/
('1702223', '7600', 'Weapon'),	/*Sparkler*/
('1702222', '5200', 'Weapon'),	/*Fanfare Firecracker*/
('1702209', '3600', 'Weapon'),	/*Rudolph Stick*/
('1702208', '4000', 'Weapon'),	/*Alligator Tube*/
('1702211', '3600', 'Weapon'),	/*Blizzard Stick*/
('1702210', '3200', 'Weapon'),	/*Santa Buddy*/
/*Page 26*/
('1702213', '6300', 'Weapon'),	/*Heartbreak Sword*/
('1702212', '6400', 'Weapon'),	/*Galactic Legend*/
('1702215', '5200', 'Weapon'),	/*Boleadoras*/
('1702214', '7600', 'Weapon'),	/*Whip*/
('1702264', '6300', 'Weapon'),	/*Strawberry Basket*/
('1702266', '5600', 'Weapon'),	/*Sunshine Pan*/
('1702269', '8800', 'Weapon'),	/*Mini Dawn Warrior*/
('1702268', '5600', 'Weapon'),	/*Evan Wand*/
('1702271', '7100', 'Weapon'),	/*Mini Wind Archer*/
/*Page 27*/
('1702270', '6400', 'Weapon'),	/*Mini Blaze Wizard*/
('1702257', '4900', 'Weapon'),	/*Mini Dawn Warrior*/
('1702256', '6000', 'Weapon'),	/*Mini Wind Archer*/
('1702259', '3400', 'Weapon'),	/*Mini Thunder Breaker*/
('1702258', '7100', 'Weapon'),	/*Mini Blaze Wizard*/
('1702261', '3600', 'Weapon'),	/*Cherry Blossom Weapon*/
('1702260', '7100', 'Weapon'),	/*Mini Night Walker*/
('1702263', '3400', 'Weapon'),	/*Kitty Spirit Weapon*/
('1702262', '6300', 'Weapon'),	/*Green Leaf Guards*/
/*Page 28*/
('1702249', '3400', 'Weapon'),	/*Gosling Cushion*/
('1702248', '5000', 'Weapon'),	/*Rudolph*/
('1702251', '8800', 'Weapon'),	/*Saw Machine Gun*/
('1702250', '3400', 'Weapon'),	/*Steel Briefcase*/
('1702253', '5000', 'Weapon'),	/*Bunny Umbrella*/
('1702252', '3400', 'Weapon'),	/*Hunting Hawk*/
('1702254', '3400', 'Weapon'),	/*Rudolph*/
('1702240', '5000', 'Weapon'),	/*Holy Mystics*/
('1702246', '3800', 'Weapon'),	/*Ghost Weapon*/
/*Page 29*/
('1702296', '5600', 'Weapon'),	/*Yo Yo*/
('1702299', '4700', 'Weapon'),	/*Chocolate Dipped Stick*/
('1702301', '5000', 'Weapon'),	/*Rabbit Weapon*/



/*Page 1*/
('1702303', '3400', 'Weapon 2'),	/*Baby Bottle*/
('1702302', '5600', 'Weapon 2'),	/*Alien Mug*/
('1702289', '5000', 'Weapon 2'),	/*Royal Marine Flag*/
('1702288', '7600', 'Weapon 2'),	/*Wild Hunter Crossbow*/
('1702291', '7400', 'Weapon 2'),	/*Elizabeth Fan*/
('1702293', '4900', 'Weapon 2'),	/*Suitcase*/
('1702295', '6300', 'Weapon 2'),	/*Playing Cards*/
('1702281', '4000', 'Weapon 2'),	/*Shining Feather Lord*/
('1702280', '6400', 'Weapon 2'),	/*Shining Feather Slayer*/
/*Page 2*/
('1702283', '4700', 'Weapon 2'),	/*Shining Feather Knuckle*/
('1702282', '6000', 'Weapon 2'),	/*Shining Feather Bow*/
('1702285', '4900', 'Weapon 2'),	/*Handbag (Blue)*/
('1702284', '4000', 'Weapon 2'),	/*Handbag (Pink)*/
('1702287', '7600', 'Weapon 2'),	/*Battle Mage Staff*/
('1702286', '6400', 'Weapon 2'),	/*Marchosias*/
('1702273', '5600', 'Weapon 2'),	/*Mini Thunder Breaker*/
('1702272', '4700', 'Weapon 2'),	/*Mini Night Walker*/
('1702275', '3200', 'Weapon 2'),	/*Rainbow Umbrella*/
/*Page 3*/
('1702274', '8800', 'Weapon 2'),	/*Dragon Lord Gloves*/
('1702277', '3600', 'Weapon 2'),	/*Test Pen*/
('1702276', '5600', 'Weapon 2'),	/*Rainbow Bow*/
('1702279', '6000', 'Weapon 2'),	/*Shining Feather Sword*/
('1702278', '7400', 'Weapon 2'),	/*King Crow Fan*/
('1702329', '7400', 'Weapon 2'),	/*Strawberry Delight*/
('1702328', '5200', 'Weapon 2'),	/*Pink Angel Syringe*/
('1702330', '6300', 'Weapon 2'),	/*Milky Way*/
('1702333', '5000', 'Weapon 2'),	/*Strawberry Sword*/
/*Page 4*/
('1702335', '4000', 'Weapon 2'),	/*Alchemist Potion Weapon*/
('1702334', '7100', 'Weapon 2'),	/*Crystal Fantasia Wand*/
('1702321', '5600', 'Weapon 2'),	/*Dark Magenta Sabre*/
('1702320', '4900', 'Weapon 2'),	/*Slate Thunder Sabre*/
('1702323', '5000', 'Weapon 2'),	/*Ombra & Luce Sabre*/
('1702322', '3600', 'Weapon 2'),	/*Soild Black Sabre*/
('1702324', '3200', 'Weapon 2'),	/*Shock Wave*/
('1702313', '3600', 'Weapon 2'),	/*Orange Seraphim*/
('1702315', '6400', 'Weapon 2'),	/*Stellar Seraphim*/
/*Page 5*/
('1702314', '6400', 'Weapon 2'),	/*Heaven's Seraphim*/
('1702317', '7100', 'Weapon 2'),	/*Bloody Ruby Sabre*/
('1702316', '3800', 'Weapon 2'),	/*Dynamic Seraphim*/
('1702319', '3800', 'Weapon 2'),	/*Evergreen Sabre*/
('1702318', '4000', 'Weapon 2'),	/*Twilight Sabre*/
('1702305', '3200', 'Weapon 2'),	/*Carrot*/
('1702304', '7600', 'Weapon 2'),	/*Funny Punch Yo-yo*/
('1702306', '7100', 'Weapon 2'),	/*Burning Breeze Fan*/
('1702309', '3400', 'Weapon 2'),	/*Rainbow Sparkle*/
/*Page 6*/
('1702308', '3400', 'Weapon 2'),	/*Spring Blossoms*/
('1702311', '3200', 'Weapon 2'),	/*MSE 4 Years & Unstoppable Star*/
('1702310', '3200', 'Weapon 2'),	/*6th Anniversary Party Wand*/
('1702361', '6400', 'Weapon 2'),	/*Hunter Hawk*/
('1702360', '3600', 'Weapon 2'),	/*Coin Sword*/
('1702363', '7600', 'Weapon 2'),	/*Crystalline Sheen*/
('1702362', '7400', 'Weapon 2'),	/*Pink Bean Buddy*/
('1702365', '3800', 'Weapon 2'),	/*Tedimus Beartaculous*/
('1702364', '7400', 'Weapon 2'),	/*Dragon Familiar*/
/*Page 7*/
('1702367', '5200', 'Weapon 2'),	/*Rose Butterwand*/
('1702366', '5600', 'Weapon 2'),	/*Shark-sicle*/
('1702352', '4700', 'Weapon 2'),	/*Magic Herb Teaspoon*/
('1702355', '4700', 'Weapon 2'),	/*Lucky Weapon*/
('1702357', '6300', 'Weapon 2'),	/*Starfall Magic Square*/
('1702356', '3800', 'Weapon 2'),	/*Legendary Weapon*/
('1702359', '7400', 'Weapon 2'),	/*Blue Angel Syringe*/
('1702358', '4700', 'Weapon 2'),	/*Pink Bean Buddy*/
('1702345', '6400', 'Weapon 2'),	/*Fierce Cat*/
/*Page 8*/
('1702344', '5200', 'Weapon 2'),	/*Boom Box*/
('1702347', '4700', 'Weapon 2'),	/*Fortune Flash*/
('1702346', '4700', 'Weapon 2'),	/*Lucky Pouch Weapon*/
('1702348', '4700', 'Weapon 2'),	/*Snowflake Rod*/
('1702351', '7400', 'Weapon 2'),	/*Tedtacular Bearingtons*/
('1702350', '7100', 'Weapon 2'),	/*Chocolatier Stick*/
('1702337', '4900', 'Weapon 2'),	/*Lightning Soul*/
('1702336', '4900', 'Weapon 2'),	/*Lord Tempest*/
('1702341', '4000', 'Weapon 2'),	/*Sweet Lollipop*/
/*Page 9*/
('1702340', '4900', 'Weapon 2'),	/*Rabbit in a Hat*/
('1702342', '8800', 'Weapon 2'),	/*Orchid's Bunny Doll*/
('1702393', '4700', 'Weapon 2'),	/*Slither Style Snake Sword*/
('1702392', '7100', 'Weapon 2'),	/*Dark Devil Weapon*/
('1702395', '5200', 'Weapon 2'),	/*Baller Cane*/
('1702394', '5200', 'Weapon 2'),	/*Golden Holy Cup*/
('1702397', '5200', 'Weapon 2'),	/*Twinkle Sparkle*/
('1702399', '7600', 'Weapon 2'),	/*Neo Light Sword*/
('1702398', '3600', 'Weapon 2'),	/*Fairy Lamp*/
/*Page 10*/
('1702385', '4000', 'Weapon 2'),	/*[MS Special] Hunting Hawk*/
('1702387', '7600', 'Weapon 2'),	/*[MS Special] Dragon Familiar*/
('1702386', '5600', 'Weapon 2'),	/*[MS Special] Crystalline Sheen*/
('1702389', '4000', 'Weapon 2'),	/*[MS Special] Fly Blue Bird*/
('1702388', '7400', 'Weapon 2'),	/*[MS Special] Tedimus Beartaculous*/
('1702390', '5600', 'Weapon 2'),	/*Halloween Leopard Umbrella*/
('1702377', '5000', 'Weapon 2'),	/*Strawberry Delight*/
('1702376', '3800', 'Weapon 2'),	/*Onmyouji Fan*/
('1702379', '4000', 'Weapon 2'),	/*Arabian Magic Lamp*/
/*Page 11*/
('1702381', '7600', 'Weapon 2'),	/*Twin Crescent Blade*/
('1702380', '3400', 'Weapon 2'),	/*Azure Crystal Crusher*/
('1702382', '3200', 'Weapon 2'),	/*Persimmon Branch*/
('1702368', '7600', 'Weapon 2'),	/*Iris Butterwand*/
('1702371', '6300', 'Weapon 2'),	/*Pimp Stick*/
('1702372', '6300', 'Weapon 2'),	/*Pimp Chalice*/
('1702375', '6400', 'Weapon 2'),	/*Atlantis*/
('1702374', '3600', 'Weapon 2'),	/*Bladed Falcon's Katana*/
('1702424', '7400', 'Weapon 2'),	/*Stylish Iron*/
/*Page 12*/
('1702427', '7400', 'Weapon 2'),	/*Ombra & Luce Sabre*/
('1702426', '3800', 'Weapon 2'),	/*Lord Tempest*/
('1702429', '6000', 'Weapon 2'),	/*Heaven's Seraphim*/
('1702428', '5000', 'Weapon 2'),	/*Bloody Ruby Sabre*/
('1702431', '7100', 'Weapon 2'),	/*GM Nori's Syringe*/
('1702430', '7400', 'Weapon 2'),	/*Dynamic Seraphim*/
('1702417', '6300', 'Weapon 2'),	/*Blue Rose Parasol*/
('1702416', '7400', 'Weapon 2'),	/*Lord of the Carrots*/
('1702419', '3800', 'Weapon 2'),	/*Pink Bean Buddy*/
/*Page 13*/
('1702418', '6300', 'Weapon 2'),	/*Hunter Hawk*/
('1702421', '5000', 'Weapon 2'),	/*Dragon Familiar*/
('1702420', '6300', 'Weapon 2'),	/*Crystalline Sheen*/
('1702423', '5000', 'Weapon 2'),	/*Goblin Fire*/

('1702409', '4700', 'Weapon 2'),	/*Hilla Weapon*/
('1702408', '8800', 'Weapon 2'),	/*Francis's Puppet*/
('1702411', '3800', 'Weapon 2'),	/*Mini Blaze Wizard*/
('1702410', '4700', 'Weapon 2'),	/*Mini Dawn Warrior*/
/*Page 14*/
('1702413', '3200', 'Weapon 2'),	/*Mini Night Walker*/
('1702412', '7600', 'Weapon 2'),	/*Mini Wind Archer*/
('1702415', '8800', 'Weapon 2'),	/*Dreamy Candy Pillow*/
('1702414', '6400', 'Weapon 2'),	/*Mini Thunder Breaker*/
('1702401', '3800', 'Weapon 2'),	/*Rabbit with Carrot*/
('1702400', '6000', 'Weapon 2'),	/*Lotus's Bunny Doll*/
('1702403', '4000', 'Weapon 2'),	/*Sherlock's Magnifier*/
('1702402', '3800', 'Weapon 2'),	/*Stylish Iron*/
('1702405', '4000', 'Weapon 2'),	/*Starlight Heart Scepter*/
/*Page 15*/
('1702404', '5600', 'Weapon 2'),	/*Muffin*/
('1702407', '8800', 'Weapon 2'),	/*Dandelion Seed Weapon*/
('1702406', '7400', 'Weapon 2'),	/*Starfall Magic Square*/
('1702457', '4900', 'Weapon 2'),	/*Fantastic Ice Pop*/
('1702456', '3600', 'Weapon 2'),	/*Fairy Pico*/
('1702459', '3200', 'Weapon 2'),	/*Cotton Candy*/
('1702458', '3400', 'Weapon 2'),	/*Fireworks Fan*/
('1702461', '3800', 'Weapon 2'),	/*Chicky-Chicky Boom*/
('1702460', '3200', 'Weapon 2'),	/*Star Weapon*/
/*Page 16*/
('1702462', '3800', 'Weapon 2'),	/*Fantasy Butterfly Flower*/
('1702449', '8800', 'Weapon 2'),	/*Strawberry Delight*/
('1702448', '5600', 'Weapon 2'),	/*Funny Punch Yo-yo*/
('1702451', '6400', 'Weapon 2'),	/*Superstar Microphone*/
('1702450', '4000', 'Weapon 2'),	/*Tedimus Beartaculous*/
('1702453', '6400', 'Weapon 2'),	/*Astral Bolt*/
('1702455', '7100', 'Weapon 2'),	/*RED Paint Bucket*/
('1702454', '5200', 'Weapon 2'),	/*Seal Wave Snuggler*/
('1702443', '4000', 'Weapon 2'),	/*Puppeteer's Promise*/
/*Page 17*/
('1702442', '7100', 'Weapon 2'),	/*Baseball Bat*/
('1702445', '4700', 'Weapon 2'),	/*Detective Glass*/
('1702444', '3400', 'Weapon 2'),	/*Fermata*/
('1702446', '7100', 'Weapon 2'),	/*Sea Otter Slammer*/
('1702433', '8800', 'Weapon 2'),	/*Salamander*/
('1702437', '4900', 'Weapon 2'),	/*Spring Blossoms*/
('1702436', '3600', 'Weapon 2'),	/*Galactic Legend*/
('1702439', '6400', 'Weapon 2'),	/*Evergreen Sabre*/
('1702438', '6400', 'Weapon 2'),	/*Stellar Seraphim*/
/*Page 18*/
('1702489', '5000', 'Weapon 2'),	/*Sweet Chocolate Fondue Stick*/
('1702488', '6400', 'Weapon 2'),	/*Pony's Carrot*/
('1702491', '3600', 'Weapon 2'),	/*Bubble Cleaner*/
('1702492', '3400', 'Weapon 2'),	/*Red Lantern*/
('1702480', '5200', 'Weapon 2'),	/*Celena*/
('1702485', '4900', 'Weapon 2'),	/*Goodie Bundle*/
('1702487', '4900', 'Weapon 2'),	/*Red Flower*/
('1702486', '3200', 'Weapon 2'),	/*Fluttering Camellia Flower*/
('1702473', '7600', 'Weapon 2'),	/*Shadow Executor*/
/*Page 19*/
('1702472', '7400', 'Weapon 2'),	/*Vampire Phantom's Fate*/
('1702475', '3400', 'Weapon 2'),	/*Maha*/
('1702474', '5200', 'Weapon 2'),	/*Evan Wand*/
('1702477', '3200', 'Weapon 2'),	/*Evil Skull*/
('1702476', '4700', 'Weapon 2'),	/*Sweet Snake*/
('1702479', '3600', 'Weapon 2'),	/*バットソード*/
('1702478', '8800', 'Weapon 2'),	/*Cat Soul*/
('1702464', '3200', 'Weapon 2'),	/*Sparkling Buddy*/
('1702467', '4900', 'Weapon 2'),	/*Cotton Candy Cloud*/
/*Page 20*/
('1702466', '7100', 'Weapon 2'),	/*Mint Chocolatier Stick*/
('1702469', '5000', 'Weapon 2'),	/*Arachne*/
('1702468', '6300', 'Weapon 2'),	/*Soft Chocolate Fondue Scepter*/
('1702471', '6300', 'Weapon 2'),	/*Dark Devil Weapon*/
('1702470', '6400', 'Weapon 2'),	/*Free Spirit*/
('1702521', '3200', 'Weapon 2'),	/*Blue Swallow*/
('1702523', '4700', 'Weapon 2'),	/*Sunny Day Rainbow*/
('1702522', '5200', 'Weapon 2'),	/*Viking Sword for Transformation*/
('1702525', '4700', 'Weapon 2'),	/*Final Ingredient*/
/*Page 21*/
('1702524', '3400', 'Weapon 2'),	/*Plump Tomato*/
('1702526', '3200', 'Weapon 2'),	/*Rifle Blade*/
('1702512', '4000', 'Weapon 2'),	/*Crown Rod*/
('1702519', '8800', 'Weapon 2'),	/*Pink Antique Parasol*/
('1702505', '5600', 'Weapon 2'),	/*Breezy Bamboo*/
('1702504', '5600', 'Weapon 2'),	/*Frozen Heart*/
('1702507', '3200', 'Weapon 2'),	/*Contemporary Chic Fan*/
('1702506', '6000', 'Weapon 2'),	/*Perfect Cooking */
('1702509', '8800', 'Weapon 2'),	/*Sunny Rainbow */
/*Page 22*/
('1702510', '5200', 'Weapon 2'),	/*Rabbit and Bear Flashlight*/
('1702497', '6400', 'Weapon 2'),	/*Sparkling Luck Sack*/
('1702499', '4700', 'Weapon 2'),	/*Guardian Scepter*/
('1702501', '7400', 'Weapon 2'),	/*Flower Dance*/
('1702503', '6400', 'Weapon 2'),	/*Bubbling Shot*/
('1702502', '6400', 'Weapon 2'),	/*Cane From the Stars*/
('1702553', '3200', 'Weapon 2'),	/*Dangerous Medicine Bottle*/
('1702555', '3400', 'Weapon 2'),	/*Noble Lady's Black Fan*/
('1702554', '3600', 'Weapon 2'),	/*Scary Huge Hand*/
/*Page 23*/
('1702557', '6400', 'Weapon 2'),	/*Duster*/
('1702556', '3800', 'Weapon 2'),	/*Blade*/
('1702559', '3600', 'Weapon 2'),	/*Puppy Pal Weapon (White)*/
('1702547', '3800', 'Weapon 2'),	/*Sweet Persimmon*/
('1702549', '7400', 'Weapon 2'),	/*Pom-pom Power*/
('1702551', '7400', 'Weapon 2'),	/*Korean Thanksgiving Persimmon Branch*/
('1702550', '7600', 'Weapon 2'),	/*Peach Trio*/
('1702538', '7400', 'Weapon 2'),	/*Dewdrop Lantern*/
('1702541', '6000', 'Weapon 2'),	/*Perfect Baby*/
/*Page 24*/
('1702540', '6000', 'Weapon 2'),	/*Here's the Flashlight!*/
('1702529', '5000', 'Weapon 2'),	/*Shadow Lamp*/
('1702528', '3400', 'Weapon 2'),	/*Xylophone Melody*/
('1702530', '6400', 'Weapon 2'),	/*Sweet Summer Hammer*/
('1702533', '7600', 'Weapon 2'),	/*Photo-op*/
('1702535', '3600', 'Weapon 2'),	/*Hula Hula Penglyn*/
('1702534', '4000', 'Weapon 2'),	/*Baby Paci*/
('1702585', '5200', 'Weapon 2'),	/*Universal Transparent Weapon*/
('1702584', '4700', 'Weapon 2'),	/*Cutie Puppy*/
/*Page 25*/
('1702587', '6300', 'Weapon 2'),	/*Rockin' Guitar*/
('1702586', '4900', 'Weapon 2'),	/*Dreaming Dandelion*/
('1702589', '6000', 'Weapon 2'),	/*Fairy Blossom*/
('1702588', '8800', 'Weapon 2'),	/*Black Cat Plush*/
('1702591', '4900', 'Weapon 2'),	/*Grand Romance*/
('1702590', '3800', 'Weapon 2'),	/*자이언트 돼지바*/
('1702577', '7100', 'Weapon 2'),	/*Lalala Goldfish Fishing Net*/
('1702576', '8800', 'Weapon 2'),	/*Ground Pounder*/
('1702579', '3600', 'Weapon 2'),	/*Crystal Cat Weapon*/
/*Page 26*/
('1702581', '4000', 'Weapon 2'),	/*Sweetie Bros*/
('1702583', '7400', 'Weapon 2'),	/*Kitty Pringles*/
('1702571', '3600', 'Weapon 2'),	/*Top Snow Shovel*/
('1702570', '7100', 'Weapon 2'),	/*Fluffy Snow Bunny*/
('1702572', '3200', 'Weapon 2'),	/*Red Rose Umbrella*/
('1702575', '6400', 'Weapon 2'),	/*Lovely Chocolate Basket*/
('1702574', '8800', 'Weapon 2'),	/*Beast Trainer*/
('1702561', '6000', 'Weapon 2'),	/*Sweet Fork Cake*/
('1702560', '3400', 'Weapon 2'),	/*Puppy Pal Weapon (Brown)*/
/*Page 27*/
('1702562', '4000', 'Weapon 2'),	/*Winter Snowman*/
('1702565', '4700', 'Weapon 2'),	/*Death's Scythe*/
('1702564', '3800', 'Weapon 2'),	/*Funny Punch Yo-yo*/
('1702567', '4700', 'Weapon 2'),	/*Rawrin' Tiger Weapon*/
('1702566', '5200', 'Weapon 2'),	/*Rammy Scepter*/
('1702617', '5000', 'Weapon 2'),	/*Lotus Fantasy*/
('1702616', '5000', 'Weapon 2'),	/*Ducky Candy Bar*/
('1702619', '6000', 'Weapon 2'),	/*Musical Green Onion*/
('1702621', '8800', 'Weapon 2'),	/*Mystery Dice*/
/*Page 28*/
('1702620', '3800', 'Weapon 2'),	/*Mystery Dice*/
('1702623', '6000', 'Weapon 2'),	/*Today Jay*/
('1702608', '5200', 'Weapon 2'),	/*Marine Stripe Umbrella*/
('1702611', '3600', 'Weapon 2'),	/*Duckling Cross Bag Weapon*/
('1702613', '6300', 'Weapon 2'),	/*Crown Rod*/
('1702612', '7400', 'Weapon 2'),	/*Fairy Pico*/
('1702614', '5000', 'Weapon 2'),	/*Baseball Bat*/
('1702601', '5200', 'Weapon 2'),	/*Bacon*/
('1702600', '3200', 'Weapon 2'),	/*Pasta*/
/*Page 29*/
('1702603', '3400', 'Weapon 2'),	/*Rib Steak*/
('1702602', '7100', 'Weapon 2'),	/*Hamburger*/
('1702605', '8800', 'Weapon 2'),	/*Donut*/



/*Page 1*/
('1702604', '6400', 'Weapon 3'),	/*Parfait*/
('1702607', '8800', 'Weapon 3'),	/*Cheese 'n' Carrots Stick*/
('1702606', '4900', 'Weapon 3'),	/*Squid*/
('1702593', '4000', 'Weapon 3'),	/*Winding Sky Bamboo*/
('1702595', '6400', 'Weapon 3'),	/*Mint Kitty Tea Time*/
('1702594', '5000', 'Weapon 3'),	/*Sweepy Orchid*/
('1702597', '7100', 'Weapon 3'),	/*Rainbow Seashell*/
('1702599', '3600', 'Weapon 3'),	/*Hoya Roar*/
('1702649', '4900', 'Weapon 3'),	/*Shining Rod of Equilibrium*/
/*Page 2*/
('1702648', '6300', 'Weapon 3'),	/*Maha the Polearm*/
('1702651', '3400', 'Weapon 3'),	/*Forgotten Hero's Knuckle*/
('1702650', '7600', 'Weapon 3'),	/*Shining Rod of Equilibrium*/
('1702653', '8800', 'Weapon 3'),	/*Transparent Arm Cannon*/
('1702652', '6000', 'Weapon 3'),	/*Forgotten Hero's Knuckle*/
('1702655', '5200', 'Weapon 3'),	/*Lil Mercedes*/
('1702654', '6300', 'Weapon 3'),	/*Mr. Hot Spring Kitty*/
('1702641', '5200', 'Weapon 3'),	/*Dragon Master's Wand*/
('1702640', '4700', 'Weapon 3'),	/*Bunny Snowman Attacker*/
/*Page 3*/
('1702643', '5000', 'Weapon 3'),	/*Elven Monarch's Dual Bowguns*/
('1702642', '6300', 'Weapon 3'),	/*Dragon Master's Wand*/
('1702645', '3800', 'Weapon 3'),	/*Phantom's Cane*/
('1702644', '5600', 'Weapon 3'),	/*Elven Monarch's Dual Bowguns*/
('1702647', '4700', 'Weapon 3'),	/*Maha the Polearm*/
('1702646', '8800', 'Weapon 3'),	/*Phantom's Cane*/
('1702633', '4900', 'Weapon 3'),	/*Banana Monkey Attacker*/
('1702632', '4000', 'Weapon 3'),	/*Zakum Arms*/
('1702635', '7600', 'Weapon 3'),	/*Mr. Orlov Coin Sword*/
/*Page 4*/
('1702634', '6400', 'Weapon 3'),	/*Maple Zombies*/
('1702637', '7100', 'Weapon 3'),	/*Hard Carrier Suitcase*/
('1702636', '7400', 'Weapon 3'),	/*Arctic Narwhal Pillow*/
('1702639', '6400', 'Weapon 3'),	/*Kitty Bangle*/
('1702638', '3800', 'Weapon 3'),	/*Blue Marine Thirst For Knowledge*/
('1702625', '6300', 'Weapon 3'),	/*Sparking Bluebird*/
('1702624', '6000', 'Weapon 3'),	/*Master Time*/
('1702627', '5000', 'Weapon 3'),	/*Sakura Sword*/
('1702626', '5200', 'Weapon 3'),	/*British Handbag Weapon*/
/*Page 5*/
('1702629', '5200', 'Weapon 3'),	/*Vintage Cellphone*/
('1702628', '7100', 'Weapon 3'),	/*Farmer's Glorious Egg Stick*/
('1702631', '3200', 'Weapon 3'),	/*Bloody Fairytale*/
('1702630', '5200', 'Weapon 3'),	/*Striking Lantern*/
('1702681', '3800', 'Weapon 3'),	/*Flask of Life*/
('1702680', '3200', 'Weapon 3'),	/*Camellia's Sword*/
('1702682', '3800', 'Weapon 3'),	/*Triple Fish Skewer*/
('1702685', '7600', 'Weapon 3'),	/*Red Phoenix Weapon*/
('1702684', '4900', 'Weapon 3'),	/*Blue Phoenix Weapon*/
/*Page 6*/
('1702687', '7400', 'Weapon 3'),	/*Strawberry Bon Bon*/
('1702686', '8800', 'Weapon 3'),	/*Sweet Pig Weapon*/
('1702673', '3400', 'Weapon 3'),	/*Monkey Banana*/
('1702672', '7400', 'Weapon 3'),	/*Duckling Cross Bag*/
('1702675', '5600', 'Weapon 3'),	/*Smile Seed Weapon*/
('1702677', '3200', 'Weapon 3'),	/*Lil Damien*/
('1702676', '4700', 'Weapon 3'),	/*Muse Crystal*/
('1702679', '6400', 'Weapon 3'),	/*Playful Black Nyanya*/
('1702678', '7600', 'Weapon 3'),	/*Lil Alicia*/
/*Page 7*/
('1702665', '3400', 'Weapon 3'),	/*Lil Evan*/
('1702667', '6000', 'Weapon 3'),	/*Lil Phantom*/
('1702666', '3600', 'Weapon 3'),	/*Lil Aran*/
('1702668', '3400', 'Weapon 3'),	/*Winter Deer Tambourine*/
('1702671', '3400', 'Weapon 3'),	/*Magic Tome Weapon*/
('1702657', '3600', 'Weapon 3'),	/*Lil Shade*/
('1702656', '7400', 'Weapon 3'),	/*Lil Luminous*/
('1702659', '3400', 'Weapon 3'),	/*Timemaster*/
('1702658', '4700', 'Weapon 3'),	/*Holiday Tree Ring*/
/*Page 8*/
('1702660', '7600', 'Weapon 3'),	/*Snowman Weapon*/
('1702713', '4000', 'Weapon 3'),	/*Bichon Paw Weapon*/
('1702712', '4000', 'Weapon 3'),	/*Moon Bunny Bell Weapon*/
('1702715', '6000', 'Weapon 3'),	/*Lachelein Fantasia*/
('1702714', '3200', 'Weapon 3'),	/*Witch's Staff*/
('1702717', '7400', 'Weapon 3'),	/*Glow Stick of Love*/
('1702716', '8800', 'Weapon 3'),	/*Dew Parasol*/
('1702719', '4900', 'Weapon 3'),	/*Flutter Flower Doll Weapon*/
('1702718', '3800', 'Weapon 3'),	/*Shadow Warrior's Sword*/
/*Page 9*/
('1702705', '3400', 'Weapon 3'),	/*Teddy Tube Wave*/
('1702704', '7400', 'Weapon 3'),	/*Blue Marine Knowledge*/
('1702707', '7400', 'Weapon 3'),	/*Pony's Carrot*/
('1702706', '4900', 'Weapon 3'),	/*Ice Cream Scream*/
('1702709', '3800', 'Weapon 3'),	/*High-five Neon V*/
('1702708', '3600', 'Weapon 3'),	/*Heaven's Seraphim*/
('1702711', '4000', 'Weapon 3'),	/*Owl Spellbook*/
('1702710', '3200', 'Weapon 3'),	/*Kamaitachi's Sickle*/
('1702697', '7100', 'Weapon 3'),	/*Cup Cat Weapon*/
/*Page 10*/
('1702696', '8800', 'Weapon 3'),	/*Silver Wolf*/
('1702699', '6000', 'Weapon 3'),	/*Colorful Beach Ball*/
('1702698', '6000', 'Weapon 3'),	/*Blaster Weapon*/
('1702701', '3200', 'Weapon 3'),	/*Dragonmare Ninth Sword*/

('1702703', '3200', 'Weapon 3'),	/*Natural Ink Painting*/
('1702702', '6300', 'Weapon 3'),	/*Porong Fan*/
('1702689', '6300', 'Weapon 3'),	/*Fairy Flora*/
('1702688', '7400', 'Weapon 3'),	/*Superstar M*/
/*Page 11*/
('1702691', '4900', 'Weapon 3'),	/*Fairy Flora*/
('1702690', '5600', 'Weapon 3'),	/*Noble Maple Rod*/
('1702693', '7600', 'Weapon 3'),	/*Bubble Leaf Weapon*/
('1702692', '6300', 'Weapon 3'),	/*Chicken Cutie Weapon*/
('1702695', '5000', 'Weapon 3'),	/*Overly Cute Puppy*/
('1702694', '5000', 'Weapon 3'),	/*Pastel Rose*/
('1702745', '7100', 'Weapon 3'),	/*Spring Fairy Flower*/
('1702744', '3800', 'Weapon 3'),	/*Starlight Lantern*/
('1702747', '5000', 'Weapon 3'),	/*Baby Magpie Buddy*/
/*Page 12*/
('1702746', '3600', 'Weapon 3'),	/*Dinofrog*/
('1702749', '3800', 'Weapon 3'),	/*Love Letter Book Bag*/
('1702748', '4900', 'Weapon 3'),	/*Guinea Pig Weapon*/
('1702750', '5600', 'Weapon 3'),	/*Strawberry Fitness Jump Rope*/
('1702737', '4700', 'Weapon 3'),	/*Frost Staff*/
('1702736', '3400', 'Weapon 3'),	/*Frost Staff*/
('1702740', '6400', 'Weapon 3'),	/*Go Yellow Chicks!*/
('1702742', '7600', 'Weapon 3'),	/*Nova Enchanter Staff*/
('1702729', '5600', 'Weapon 3'),	/*Deep-fried Drumstick*/
/*Page 13*/
('1702728', '6000', 'Weapon 3'),	/*Sweet Jelly Paw*/
('1702731', '3400', 'Weapon 3'),	/*Snowrabbit*/
('1702733', '4000', 'Weapon 3'),	/*Monk Drum*/
('1702732', '3800', 'Weapon 3'),	/*Rabbit in a Hat*/
('1702735', '3600', 'Weapon 3'),	/*Rose Constellation*/
('1702734', '6000', 'Weapon 3'),	/*Maple 5000-Day Flag*/
('1702721', '7100', 'Weapon 3'),	/*죽음의 키읔*/
('1702720', '7600', 'Weapon 3'),	/*Maple M Playphone*/
('1702723', '4700', 'Weapon 3'),	/*Power Porker Trio*/
/*Page 14*/
('1702722', '3400', 'Weapon 3'),	/*Winter Bunny*/
('1702725', '8800', 'Weapon 3'),	/*Necromancer*/
('1702724', '3400', 'Weapon 3'),	/*Undead Teddy*/
('1702727', '3600', 'Weapon 3'),	/*Rabbit Soap Shooter*/
('1702726', '3600', 'Weapon 3'),	/*Pumpkin Star*/
('1702777', '5000', 'Weapon 3'),	/*Refreshing Lemon Weapon*/
('1702776', '3800', 'Weapon 3'),	/*Water Granos and Weapon*/
('1702779', '8800', 'Weapon 3'),	/*Fluffy Teddy Candy*/
('1702778', '5000', 'Weapon 3'),	/*Summer Flower Fairy Weapon*/
/*Page 15*/
('1702781', '4000', 'Weapon 3'),	/*Fish on a Stick*/
('1702780', '8800', 'Weapon 3'),	/*Bitten Donut Ring*/
('1702783', '4900', 'Weapon 3'),	/*Rabble Rouser Weapon*/
('1702782', '5000', 'Weapon 3'),	/*Surf's Up*/
('1702769', '5200', 'Weapon 3'),	/*Maple Galaxy Laser Gun*/
('1702768', '8800', 'Weapon 3'),	/*Mallow Fluff on a Stick*/
('1702771', '4900', 'Weapon 3'),	/*Banana Shake*/
('1702770', '8800', 'Weapon 3'),	/*Fancy Feather Quill*/
('1702773', '8800', 'Weapon 3'),	/*Eagle Weapon*/
/*Page 16*/
('1702772', '3400', 'Weapon 3'),	/*Carrot Cake Shake*/
('1702775', '3400', 'Weapon 3'),	/*Watermelon Slice*/
('1702774', '4900', 'Weapon 3'),	/*Ocean Hydrangea*/
('1702761', '4000', 'Weapon 3'),	/*Sproutbrella*/
('1702760', '6300', 'Weapon 3'),	/*Hamster Devotion!*/
('1702765', '3800', 'Weapon 3'),	/*Soda Pop Weapon*/
('1702764', '3400', 'Weapon 3'),	/*Iron Mace Uniform Weapon*/
('1702767', '7600', 'Weapon 3'),	/*Mustachio on a Stick*/
('1702766', '7600', 'Weapon 3'),	/*Diamond Brilliance*/
/*Page 17*/
('1702753', '3800', 'Weapon 3'),	/*Pandora Weapon*/
('1702752', '3600', 'Weapon 3'),	/*Silver Flower Child Weapon*/
('1702755', '5600', 'Weapon 3'),	/*Sweet Baguette*/
('1702757', '5200', 'Weapon 3'),	/*Cygnus's Guard*/
('1702756', '3400', 'Weapon 3'),	/*Starlit Dreamweaver*/
('1702759', '4700', 'Weapon 3'),	/*Charming Cherry Pop*/
('1702758', '5200', 'Weapon 3'),	/*Pop Star Mic Skin*/
('1702809', '3200', 'Weapon 3'),	/*Bunny Marker*/
('1702808', '7600', 'Weapon 3'),	/*Iron Mace*/
/*Page 18*/
('1702811', '7600', 'Weapon 3'),	/*Luminous Sea*/
('1702810', '7600', 'Weapon 3'),	/*Soft Snow*/
('1702813', '4000', 'Weapon 3'),	/*Royal Guard Weapon*/
('1702812', '7100', 'Weapon 3'),	/*Lunar New Year VIP Weapon*/
('1702815', '4700', 'Weapon 3'),	/*Plum Blossom*/
('1702814', '6300', 'Weapon 3'),	/*Rainbow Dreamcloud Weapon*/
('1702801', '3600', 'Weapon 3'),	/*Busy Penguin Bubble*/
('1702800', '5600', 'Weapon 3'),	/*Strawberry Fitness Jump Rope*/
('1702803', '3400', 'Weapon 3'),	/*Fish Bubbles Weapon*/
/*Page 19*/
('1702802', '4900', 'Weapon 3'),	/*Cadena Vendetta Chain*/
('1702805', '8800', 'Weapon 3'),	/*Party Scepter*/
('1702804', '3800', 'Weapon 3'),	/*Take Me Too!*/
('1702807', '6300', 'Weapon 3'),	/*Steamed Sweet*/
('1702806', '8800', 'Weapon 3'),	/*Glowing Pumpkin Basket*/
('1702793', '7400', 'Weapon 3'),	/*Eggplant of Doom*/
('1702792', '6300', 'Weapon 3'),	/*Blood Oath Weapon*/
('1702795', '5000', 'Weapon 3'),	/*Enchanting Flute*/
('1702794', '4900', 'Weapon 3'),	/*Cattail Cutlass*/
/*Page 20*/
('1702797', '4000', 'Weapon 3'),	/*Meow Weapon*/
('1702796', '6300', 'Weapon 3'),	/*Organic Rice*/
('1702799', '4700', 'Weapon 3'),	/*Small Formosan Deer Weapon*/
('1702798', '6000', 'Weapon 3'),	/*Christmas Bunny Weapon*/
('1702785', '3200', 'Weapon 3'),	/*Cursed Bat Weapon*/
('1702784', '6400', 'Weapon 3'),	/*Feather Messenger Weapon*/
('1702787', '4700', 'Weapon 3'),	/*Shadow Tactician Weapon*/
('1702786', '3200', 'Weapon 3'),	/*Blood Oath Weapon*/
('1702789', '7600', 'Weapon 3'),	/*Pupmallow Pop Weapon*/
/*Page 21*/
('1702788', '3800', 'Weapon 3'),	/*Cat Cafe Weapon*/
('1702791', '5200', 'Weapon 3'),	/*Master of Hearts*/
('1702790', '7100', 'Weapon 3'),	/*Golden Eventides*/
('1702841', '6000', 'Weapon 3'),	/*Erda Weapon*/
('1702840', '4000', 'Weapon 3'),	/*Happy Ghost Weapon*/
('1702842', '3600', 'Weapon 3'),	/*Navy Telescope Weapon*/
('1702845', '6300', 'Weapon 3'),	/*Your Good Side*/
('1702844', '6000', 'Weapon 3'),	/*Antoine Pocket Watch*/
('1702847', '3400', 'Weapon 3'),	/*Cloudy Paper Plane Weapon*/
/*Page 22*/
('1702846', '6300', 'Weapon 3'),	/*Summer Story Weapon*/
('1702833', '5000', 'Weapon 3'),	/*Oceanic Requiem Weapon*/
('1702832', '4900', 'Weapon 3'),	/*Spring Flower Deer*/
('1702835', '4900', 'Weapon 3'),	/*Uglee*/
('1702834', '4700', 'Weapon 3'),	/*Electric Weapon*/
('1702837', '3800', 'Weapon 3'),	/*Candy Angel*/
('1702836', '8800', 'Weapon 3'),	/*Maple Gumshoe's Tablet*/
('1702839', '5000', 'Weapon 3'),	/*Bichon Bam*/
('1702838', '3200', 'Weapon 3'),	/*Jailbreak Spoon*/
/*Page 23*/
('1702825', '7600', 'Weapon 3'),	/*Homeless Cat Weapon*/
('1702824', '3400', 'Weapon 3'),	/*Golden Mochi*/
('1702827', '3200', 'Weapon 3'),	/*Rainbow Bubbles*/
('1702826', '6400', 'Weapon 3'),	/*Kiddy Crayon*/
('1702829', '4000', 'Weapon 3'),	/*Little Darling*/
('1702828', '5600', 'Weapon 3'),	/*Carbon Wings*/
('1702831', '3400', 'Weapon 3'),	/*Tennis Racket*/
('1702830', '7100', 'Weapon 3'),	/*Spring Rain Parasol*/
('1702817', '3600', 'Weapon 3'),	/*Invincible Blade*/
/*Page 24*/
('1702816', '5600', 'Weapon 3'),	/*Flurry Bear*/
('1702819', '5000', 'Weapon 3'),	/*Tennis Weapon*/
('1702818', '3800', 'Weapon 3'),	/*World of Pink Weapon*/
('1702821', '4000', 'Weapon 3'),	/*Picnic Basket Weapon*/
('1702820', '4000', 'Weapon 3'),	/*Butterfly Weapon*/
('1702823', '3200', 'Weapon 3'),	/*Maple Blitzer Strategist Deck*/
('1702822', '6300', 'Weapon 3'),	/*Pink Puzzle Weapon*/
('1702873', '7100', 'Weapon 3'),	/*Tri-color Bag Weapon*/
('1702872', '6400', 'Weapon 3'),	/*Cluck, Cluck, Bean*/
/*Page 25*/
('1702875', '7600', 'Weapon 3'),	/*Frostblade Weapon*/
('1702874', '7100', 'Weapon 3'),	/*Little Star Cocoon Weapon*/
('1702877', '7600', 'Weapon 3'),	/*Mu Young's Sword*/
('1702876', '5000', 'Weapon 3'),	/*Necrotic Whip*/
('1702879', '5600', 'Weapon 3'),	/*Dreamland Unicorn*/
('1702878', '4700', 'Weapon 3'),	/*Regal Romance Parasol*/
('1702865', '3800', 'Weapon 3'),	/*Reflection of Truth*/
('1702867', '6300', 'Weapon 3'),	/*Fairy in a Gilded Cage*/
('1702866', '5000', 'Weapon 3'),	/*Firefly Firelight Lantern*/
/*Page 26*/
('1702869', '7100', 'Weapon 3'),	/*Autumn Lantern Weapon*/
('1702868', '5000', 'Weapon 3'),	/*Heavenly Prayer Weapon*/
('1702871', '8800', 'Weapon 3'),	/*Heart Diary*/
('1702870', '5200', 'Weapon 3'),	/*Imperial Honor*/
('1702857', '8800', 'Weapon 3'),	/*Infinite Star Cluster*/
('1702856', '3400', 'Weapon 3'),	/*Infinite Star Cluster*/
('1702859', '6000', 'Weapon 3'),	/*Lotus's Lovey Doll*/
('1702858', '3800', 'Weapon 3'),	/*Seafoam Coral Blade*/
('1702861', '4900', 'Weapon 3'),	/*One-Eyed Grim Reaper Weapon*/
/*Page 27*/
('1702860', '3600', 'Weapon 3'),	/*Starry Summer Night Weapon*/
('1702863', '4900', 'Weapon 3'),	/*Alliance Commander Wing Sword*/
('1702862', '3600', 'Weapon 3'),	/*Night Procession Spirit Lamp Weapon*/
('1702849', '7400', 'Weapon 3'),	/*Crispy Carrot Skateboard*/
('1702848', '5000', 'Weapon 3'),	/*Retro Scalene Rapier*/
('1702851', '4700', 'Weapon 3'),	/*Catkerchief Packed Lunch*/
('1702850', '4000', 'Weapon 3'),	/*Spring Green Foxtail*/
('1702853', '5200', 'Weapon 3'),	/*Traditional Thai Attire Weapon*/
('1702855', '3600', 'Weapon 3'),	/*White Ducky Inner Tube*/
/*Page 28*/
('1702854', '4900', 'Weapon 3'),	/*Maple Alliance Flag*/
('1702905', '6000', 'Weapon 3'),	/*Wish Fulfiller Crossbody Tote*/
('1702904', '3200', 'Weapon 3'),	/*Midnight Magician Weapon*/
('1702906', '4900', 'Weapon 3'),	/*Cursed Bow*/
('1702909', '6000', 'Weapon 3'),	/*Gorgon Baston*/
('1702911', '4900', 'Weapon 3'),	/*Drowsy Rabbit*/
('1702910', '4700', 'Weapon 3'),	/*Bushmaster Sword*/
('1702897', '3800', 'Weapon 3'),	/*SALLY's Skateboard*/
('1702896', '7100', 'Weapon 3'),	/*LEONARD's Leafy Greens*/
/*Page 29*/
('1702899', '4000', 'Weapon 3'),	/*Valiant EDWARD*/
('1702898', '5600', 'Weapon 3'),	/*Gamboling CONY*/
('1702901', '4000', 'Weapon 3'),	/*Crystalline Wand*/



/*Page 1*/
('1702900', '4900', 'Weapon 4'),	/*Azure Sunset Sword*/
('1702903', '6000', 'Weapon 4'),	/*Sunny Songbird Weapon*/
('1702902', '5000', 'Weapon 4'),	/*Celestial Staff*/
('1702889', '5600', 'Weapon 4'),	/*Hunny Bun Bear Bell*/
('1702888', '5600', 'Weapon 4'),	/*Drowsy Bunny*/
('1702891', '8800', 'Weapon 4'),	/*Scribble Bloom*/
('1702890', '5600', 'Weapon 4'),	/*Rainbow Duster*/
('1702893', '5600', 'Weapon 4'),	/*Green Leaf Goobie*/
('1702892', '3800', 'Weapon 4'),	/*Sugarsweet Candy Spear*/
/*Page 2*/
('1702895', '6000', 'Weapon 4'),	/*Golden Attack Pig*/
('1702894', '5200', 'Weapon 4'),	/*Ice Cream CONY*/
('1702881', '4700', 'Weapon 4'),	/*Chrome Dumbbell*/
('1702880', '8800', 'Weapon 4'),	/*Reaper's Wing*/
('1702883', '5600', 'Weapon 4'),	/*Pajama Party*/
('1702882', '4700', 'Weapon 4'),	/*Snowflake Sugarpop*/
('1702885', '3800', 'Weapon 4'),	/*Lunar New Year Pudgy Piggy*/
('1702884', '6000', 'Weapon 4'),	/*Cobalt Filigree*/
('1702887', '5200', 'Weapon 4'),	/*Camelia Tea Time*/
/*Page 3*/
('1702886', '6400', 'Weapon 4'),	/*Sweet Beary Chocolate*/
('1702927', '6400', 'Weapon 4'),	/*Red Lotus Spirit Walker's Fan*/
('1702913', '8800', 'Weapon 4'),	/*Starry Light Weapon*/
('1702912', '8800', 'Weapon 4'),	/*Demonic Sword*/
('1702915', '6000', 'Weapon 4'),	/*Fox Fire Familiar*/
('1702917', '4700', 'Weapon 4'),	/*Blue Flame Whip*/
('1702916', '6300', 'Weapon 4'),	/*Springtime Sprout Greenery*/





/*  Hat  */
/*Page 1*/
('1000015', '7100', 'Hat'),	/*Blue Crown*/
('1000014', '7400', 'Hat'),	/*Green Crown*/
('1000013', '7100', 'Hat'),	/*Yellow Crown*/
('1000012', '6000', 'Hat'),	/*Black M-Forcer Helmet*/
('1000011', '5000', 'Hat'),	/*Green M-Forcer Helmet*/
('1000010', '6300', 'Hat'),	/*Blue M-Forcer Helmet*/
('1000009', '7100', 'Hat'),	/*Red M-Forcer Helmet*/
('1000008', '3800', 'Hat'),	/*Detective Hat*/
('1000007', '5200', 'Hat'),	/*Hat of Death*/
/*Page 2*/
('1000006', '4000', 'Hat'),	/*Samurai Hair-do*/
('1000005', '3800', 'Hat'),	/*Men's Ninja Hat*/
('1000004', '8800', 'Hat'),	/*Old School Uniform Hat*/
('1000003', '7600', 'Hat'),	/*Ghost Mask*/
('1000002', '7400', 'Hat'),	/*Fine Blue Hanbok Hat*/
('1000001', '5600', 'Hat'),	/*Fine Black Hanbok Hat*/
('1000000', '3400', 'Hat'),	/*Blue Beanie*/
('1000031', '5600', 'Hat'),	/*Veamoth Wig (M)*/
('1000030', '4700', 'Hat'),	/*Sachiel Wig (M)*/
/*Page 3*/
('1000029', '3800', 'Hat'),	/*Wedding veil*/
('1000028', '4700', 'Hat'),	/*Korean Official Hat*/
('1000027', '3400', 'Hat'),	/*Lunar Festivities Cap*/
('1000026', '5600', 'Hat'),	/*Santa Boy Hat*/
('1000024', '3600', 'Hat'),	/*Oriental Bridegroom Hat*/
('1000023', '5200', 'Hat'),	/*Race Ace Cap*/
('1000022', '7400', 'Hat'),	/*General's Wig*/
('1000021', '3800', 'Hat'),	/*General's Wig*/
('1000020', '3200', 'Hat'),	/*Chief Hat*/
/*Page 4*/
('1000019', '3600', 'Hat'),	/*Green Goya Hat*/
('1000018', '8800', 'Hat'),	/*Kuniragi Hat*/
('1000017', '7600', 'Hat'),	/*Van Hat*/
('1000016', '5600', 'Hat'),	/*Red Crown*/
('1000046', '3400', 'Hat'),	/*Elven Spirit Band (M)*/
('1000045', '3800', 'Hat'),	/*Dark Force Horns (M) */
('1000044', '5000', 'Hat'),	/*Twinkling Boy Hat*/
('1000043', '8800', 'Hat'),	/*Santa Hat*/
('1000042', '7400', 'Hat'),	/*Napoleon Hat*/
/*Page 5*/
('1000041', '5200', 'Hat'),	/*Napoleon Hat*/
('1000035', '8800', 'Hat'),	/*White Floral Hat*/
('1000032', '7400', 'Hat'),	/*Janus Wig (M)*/
('1000062', '7400', 'Hat'),	/*Cool Carrot Hat*/
('1000061', '6400', 'Hat'),	/*Alps Boy Hat*/
('1000060', '3600', 'Hat'),	/*Dark Force Horns*/
('1000059', '7100', 'Hat'),	/*[MS Custom] Black M-Forcer Helmet*/
('1000058', '6400', 'Hat'),	/*Evergreen Magistrate Hat*/
('1000051', '7100', 'Hat'),	/*Aerial Elven Spirit Band*/
/*Page 6*/
('1000050', '5200', 'Hat'),	/*Mint Snow Cap*/
('1000079', '4900', 'Hat'),	/*Mad Doctor Bolt*/
('1000077', '6300', 'Hat'),	/*Dylan's Silk Hat*/
('1000076', '5600', 'Hat'),	/*Red Dusk*/
('1000074', '7100', 'Hat'),	/*Yellow Picnic Beret*/
('1000072', '6000', 'Hat'),	/*Jumpy Blue*/
('1000071', '5200', 'Hat'),	/*Blue Pedora*/
('1000070', '3200', 'Hat'),	/*Bon-Bon Pony Hat*/
('1000069', '6300', 'Hat'),	/*Moonlight Floral Hat*/
/*Page 7*/
('1000095', '3800', 'Hat'),	/*Floral Bandana*/
('1000094', '3600', 'Hat'),	/*Bon-Bon Pony Hair*/
('1000092', '4000', 'Hat'),	/*Nutcracker Hat*/
('1000091', '7100', 'Hat'),	/*Bloody Guardian Hood*/
('1000090', '7400', 'Hat'),	/*Penguin Hood*/
('1000089', '7600', 'Hat'),	/*Kinesis Wig*/
('1000088', '4900', 'Hat'),	/*Kinesis Wig*/
('1000087', '7400', 'Hat'),	/*Ribbon Headband*/
('1000086', '7100', 'Hat'),	/*Team Wig*/
/*Page 8*/
('1000085', '6300', 'Hat'),	/*Aquamarine Gem*/
('1000084', '7100', 'Hat'),	/*Little Wing Cap*/
('1000083', '5600', 'Hat'),	/*Maple Festival Wig*/
('1000082', '4700', 'Hat'),	/*Fashionista Wig (M)*/

('1000080', '6400', 'Hat'),	/*Santa Boy Hat*/
('1000101', '5600', 'Hat'),	/*Santa Boy Hat*/
('1000100', '6000', 'Hat'),	/*Movie Protagonist Wig*/
('1000099', '5600', 'Hat'),	/*New Year Photo Protagonist Wig*/
/*Page 9*/
('1000097', '4900', 'Hat'),	/*Night Ciel*/
('1000096', '5000', 'Hat'),	/*Moonlit Night Hair*/
('1001007', '3600', 'Hat'),	/*Miko Wig*/
('1001006', '5200', 'Hat'),	/*SF Ninja Hat*/
('1001005', '4700', 'Hat'),	/*Women's Ninja Hat*/
('1001004', '3800', 'Hat'),	/*White Nurse Hat*/
('1001003', '6400', 'Hat'),	/*Pink Nurse Hat*/
('1001002', '5600', 'Hat'),	/*Witch Hat*/
('1001001', '7600', 'Hat'),	/*Hanbok Jobawi*/
/*Page 10*/
('1001000', '4000', 'Hat'),	/*Orange Beanie*/
('1001023', '5200', 'Hat'),	/*Picnic Hat*/
('1001022', '6000', 'Hat'),	/*Van Hat with Heart*/
('1001021', '3400', 'Hat'),	/*The Gabera Hat*/
('1001020', '5600', 'Hat'),	/*Lady Yellow*/
('1001019', '3200', 'Hat'),	/*Lady Pink*/
('1001018', '6000', 'Hat'),	/*Lady Blue*/
('1001017', '8800', 'Hat'),	/*Princess Tiara*/
('1001016', '4900', 'Hat'),	/*Black M-Forcer Helmet*/
/*Page 11*/
('1001015', '4900', 'Hat'),	/*Yellow M-Forcer Helmet*/
('1001014', '5000', 'Hat'),	/*Pink M-Forcer Helmet*/
('1001013', '3800', 'Hat'),	/*Beret*/
('1001012', '4900', 'Hat'),	/*Tiara*/
('1001011', '7100', 'Hat'),	/*Strawberry Headgear*/
('1001010', '3400', 'Hat'),	/*Teddy Bear Hat*/
('1001009', '6300', 'Hat'),	/*Ribbon*/
('1001008', '3800', 'Hat'),	/*A Ladylike Hat*/
('1001039', '3200', 'Hat'),	/*Lunar Festivities Ornament*/
/*Page 12*/
('1001038', '4900', 'Hat'),	/*Korean Dress Wig*/
('1001037', '3800', 'Hat'),	/*Leopard Print Hat*/
('1001036', '4700', 'Hat'),	/*Santa Girl Hat*/
('1001034', '5200', 'Hat'),	/*Oriental Princess Hat*/
('1001033', '3800', 'Hat'),	/*Maid Hat*/
('1001032', '7400', 'Hat'),	/*Black Cat Ears*/
('1001031', '3800', 'Hat'),	/*White Cat Ears*/
('1001030', '6000', 'Hat'),	/*Diao Chan Headpiece*/
('1001029', '3600', 'Hat'),	/*Yellow Bride's Veil*/
/*Page 13*/
('1001028', '7400', 'Hat'),	/*Jami Wig*/
('1001027', '7400', 'Hat'),	/*Blue-Feathered Bandana*/
('1001026', '7100', 'Hat'),	/*Red-Feathered Bandana*/
('1001025', '4000', 'Hat'),	/*Ruby Tiara*/
('1001024', '5000', 'Hat'),	/*Diamond Tiara*/
('1001055', '5000', 'Hat'),	/*Strawberry Milk Frill Bonnet*/
('1001049', '5200', 'Hat'),	/*Gothic Headband*/
('1001048', '4900', 'Hat'),	/*Gothic Mini Hat*/
('1001047', '4900', 'Hat'),	/*Janus Wig (F)*/
/*Page 14*/
('1001046', '3200', 'Hat'),	/*Veamoth Wig (F)*/
('1001045', '8800', 'Hat'),	/*Sachiel Wig (F)*/
('1001044', '3400', 'Hat'),	/*Green Bride's Veil*/
('1001043', '6000', 'Hat'),	/*Royal Tiara*/
('1001042', '6000', 'Hat'),	/*Purple Bride's Veil*/
('1001041', '6300', 'Hat'),	/*Royal Nurse Hat*/
('1001040', '7100', 'Hat'),	/*Royal Maid Hat*/
('1001071', '3800', 'Hat'),	/*Silver Angora Gatsby*/
('1001070', '4000', 'Hat'),	/*Gold Angora Gatsby*/
/*Page 15*/
('1001069', '3400', 'Hat'),	/*Elven Spirit Band (F) */
('1001068', '7400', 'Hat'),	/*Dark Force Horns (F) */
('1001066', '5000', 'Hat'),	/*Red Hood Bandana*/
('1001065', '3200', 'Hat'),	/*Pink Angel Wing Cap*/
('1001064', '4900', 'Hat'),	/*Twinkling Girl Hat*/
('1001063', '6400', 'Hat'),	/*Dear Christmas*/
('1001062', '5200', 'Hat'),	/*Elizabeth Hat*/
('1001061', '5000', 'Hat'),	/*Elizabeth Hat*/
('1001058', '7100', 'Hat'),	/*Native American Chief Hat*/
/*Page 16*/
('1001087', '7600', 'Hat'),	/*Dark Force Horns*/
('1001085', '4000', 'Hat'),	/*Pinky Butterfly Hair Pin*/
('1001084', '5000', 'Hat'),	/*Angelic Navy Cap*/
('1001083', '3200', 'Hat'),	/*Angelic Ribbon*/
('1001082', '4700', 'Hat'),	/*Red Hood Bandana*/
('1001077', '4900', 'Hat'),	/*Aerial Elven Spirit Band*/
('1001076', '5600', 'Hat'),	/*Cherry Snow Cap*/
('1001075', '3200', 'Hat'),	/*Star of Ereve*/
('1001103', '3600', 'Hat'),	/*Fashionista Wig (F)*/
/*Page 17*/
('1001101', '7400', 'Hat'),	/*Santa Girl Hat*/
('1001100', '4900', 'Hat'),	/*Ribbon Angel Cap*/
('1001099', '6000', 'Hat'),	/*Rosalia's Rose*/
('1001098', '7400', 'Hat'),	/*Blue Twilight*/
('1001097', '5000', 'Hat'),	/*White Picnic Beret*/
('1001095', '5600', 'Hat'),	/*Jumpy Pink*/
('1001094', '4000', 'Hat'),	/*Lace Cap*/
('1001093', '5600', 'Hat'),	/*Bon-Bon Pony Cap*/
('1001092', '4900', 'Hat'),	/*Moonlight Floral Hairpin*/
/*Page 18*/
('1001091', '3600', 'Hat'),	/*Dumpling Head Wig*/
('1001090', '4900', 'Hat'),	/*Fluffy Cat Hood*/
('1001089', '3400', 'Hat'),	/*Warm Carrot Hat*/
('1001088', '6300', 'Hat'),	/*Alps Girl Hat*/
('1001119', '7600', 'Hat'),	/*Snowy Night Hair*/
('1001118', '3400', 'Hat'),	/*Floral Ayam*/
('1001117', '5200', 'Hat'),	/*Lucid's Hat*/
('1001116', '7600', 'Hat'),	/*Pon-Pon Pony Hat*/
('1001113', '7600', 'Hat'),	/*Bloody Veil*/
/*Page 19*/
('1001112', '4900', 'Hat'),	/*Penguin Hood*/
('1001111', '4000', 'Hat'),	/*Kinesis Wig*/
('1001110', '6300', 'Hat'),	/*Kinesis Wig*/
('1001109', '6400', 'Hat'),	/*Odette Tiara*/
('1001108', '5000', 'Hat'),	/*Ribbon Headband*/
('1001107', '4700', 'Hat'),	/*Momo Wig*/
('1001106', '5600', 'Hat'),	/*Pink Diamond Gem*/
('1001105', '4700', 'Hat'),	/*Little Wing Fedora*/
('1001104', '5000', 'Hat'),	/*Maple Festival Wig*/
/*Page 20*/
('1001124', '5200', 'Hat'),	/*Santa Girl Hat*/
('1001123', '6300', 'Hat'),	/*Movie Protagonist Wig*/
('1001122', '5200', 'Hat'),	/*New Year Photo Protagonist Wig*/
('1001120', '4900', 'Hat'),	/*Night Elodie*/
('1002015', '7600', 'Hat'),	/*Red Swimming Goggle*/
('1002000', '5200', 'Hat'),	/*Brown Flight Headgear*/
('1002031', '6000', 'Hat'),	/*Cat Hat*/
('1002018', '4000', 'Hat'),	/*Green Camping Hat*/
('1002032', '4900', 'Hat'),	/*Puffy Brown Hat*/
/*Page 21*/
('1002079', '5000', 'Hat'),	/*Pink Camping Hat*/
('1002078', '3200', 'Hat'),	/*Sky Blue Camping Hat*/
('1002077', '3400', 'Hat'),	/*Blue Flight Headgear*/
('1002076', '3600', 'Hat'),	/*Red Flight Headgear*/
('1002071', '7400', 'Hat'),	/*Blue Swimming Goggle*/
('1002070', '7600', 'Hat'),	/*Green Swimming Goggle*/
('1002191', '5600', 'Hat'),	/*Red Pre-School Hat*/
('1002190', '6300', 'Hat'),	/*Blue Pre-School Hat*/
('1002189', '5200', 'Hat'),	/*Dark Cowboy Hat*/
/*Page 22*/
('1002188', '7100', 'Hat'),	/*Red Cowboy Hat*/
('1002187', '5600', 'Hat'),	/*Blue Cowboy Hat*/
('1002186', '7100', 'Hat'),	/*Transparent Hat*/
('1002206', '7400', 'Hat'),	/*Green Rain Cap*/
('1002205', '3200', 'Hat'),	/*Sky Blue Rain Cap*/
('1002204', '7400', 'Hat'),	/*Red Rain Cap*/
('1002203', '6300', 'Hat'),	/*Yellow Rain Cap*/
('1002202', '7100', 'Hat'),	/*Orange Visor*/
('1002201', '5600', 'Hat'),	/*Sky Blue Visor*/
/*Page 23*/
('1002200', '5600', 'Hat'),	/*Green Visor*/
('1002199', '4900', 'Hat'),	/*Black Baseball Helmet*/
('1002198', '4700', 'Hat'),	/*Indigo Baseball Helmet*/
('1002197', '4000', 'Hat'),	/*Red Baseball Helmet*/
('1002196', '8800', 'Hat'),	/*Blue Baseball Helmet*/
('1002195', '7600', 'Hat'),	/*Flowery Swimming Cap*/
('1002194', '7100', 'Hat'),	/*Rosy Swimming Cap*/
('1002193', '3200', 'Hat'),	/*Maroon Chinese Undead Hat*/
('1002192', '3200', 'Hat'),	/*Blue Chinese Undead Hat*/
/*Page 24*/
('1002223', '5600', 'Hat'),	/*Blue Upside-Down Visor*/
('1002222', '5200', 'Hat'),	/*Red Upside-Down Visor*/
('1002221', '3800', 'Hat'),	/*Purple Slanted Visor*/
('1002220', '7400', 'Hat'),	/*Black Slanted Visor*/
('1002219', '4900', 'Hat'),	/*Destreza Hat*/
('1002239', '3400', 'Hat'),	/*The Legendary Gold Poop Hat*/
('1002238', '6400', 'Hat'),	/*Construction Hardhat*/
('1002237', '3400', 'Hat'),	/*Blue Cap*/
('1002236', '7100', 'Hat'),	/*Khaki Goggled Beanie*/
/*Page 25*/
('1002235', '5200', 'Hat'),	/*Sky Blue Goggled Beanie*/
('1002234', '7400', 'Hat'),	/*Starry Sky Blue Beanie*/
('1002233', '5600', 'Hat'),	/*Starry Pink Beanie*/
('1002232', '4700', 'Hat'),	/*Starry Red Beanie*/
('1002231', '4700', 'Hat'),	/*Goggled Blue Cap*/
('1002230', '7400', 'Hat'),	/*Goggled Black Cap*/
('1002229', '3400', 'Hat'),	/*Goggled Red Cap*/
('1002228', '7600', 'Hat'),	/*Cabbie*/
('1002227', '6400', 'Hat'),	/*Blue Fisherman Hat*/
/*Page 26*/
('1002226', '3200', 'Hat'),	/*Fashionable Hat*/
('1002225', '8800', 'Hat'),	/*Santa Hat*/
('1002224', '5000', 'Hat'),	/*Tiger Mask*/
('1002255', '5000', 'Hat'),	/*Circus Cowboy Hat*/
('1002251', '8800', 'Hat'),	/*The Graduation Hat*/
('1002250', '4000', 'Hat'),	/*Headphone Bandana*/
('1002241', '3800', 'Hat'),	/*Techwin Wig*/
('1002240', '4700', 'Hat'),	/*Hajimaki*/
('1002266', '7400', 'Hat'),	/*Basic Earmuff*/
/*Page 27*/
('1002265', '5600', 'Hat'),	/*Elf's Ear*/
('1002264', '5200', 'Hat'),	/*Hardhat*/
('1002263', '5600', 'Hat'),	/*Green Trucker Hat*/
('1002262', '7400', 'Hat'),	/*Red Trucker Hat*/
('1002261', '6300', 'Hat'),	/*Blue Trucker Hat*/
('1002260', '4700', 'Hat'),	/*Yellow Trucker Hat*/
('1002259', '3400', 'Hat'),	/*Black Top Hat*/
('1002258', '7600', 'Hat'),	/*Blue Diamondy Bandana*/
('1002257', '5600', 'Hat'),	/*Blue Mushroom Hat*/
/*Page 28*/
('1002256', '5600', 'Hat'),	/*Orange Mushroom Hat*/
('1002280', '6300', 'Hat'),	/*Ducky Hat*/
('1002279', '6400', 'Hat'),	/*Bunny Hat*/
('1002303', '4000', 'Hat'),	/*Blue Picnic Hat*/
('1002302', '3200', 'Hat'),	/*Pink Picnic Hat*/
('1002301', '6300', 'Hat'),	/*Yellow Picnic Hat*/
('1002300', '4000', 'Hat'),	/*Green Picnic Hat*/
('1002299', '7100', 'Hat'),	/*Cubic Newsie Hat*/
('1002298', '3800', 'Hat'),	/*Blue Bucket Hat*/
/*Page 29*/
('1002297', '6400', 'Hat'),	/*Brown Bucket Hat*/
('1002296', '7600', 'Hat'),	/*Slime Hat*/
('1002295', '7100', 'Hat'),	/*Chef's Hat*/



/*Page 1*/
('1002294', '8800', 'Hat 2'),	/*Red Frill Pajama Hat*/
('1002293', '4700', 'Hat 2'),	/*Blue Pajama Hat*/
('1002292', '6300', 'Hat 2'),	/*Pink Frill Pajama Hat*/
('1002291', '3600', 'Hat 2'),	/*Starred Hunting Hat*/
('1002290', '6400', 'Hat 2'),	/*Camouflaged Helmet*/
('1002319', '7600', 'Hat 2'),	/*Whale Hat*/
('1002318', '4000', 'Hat 2'),	/*Red Headband*/
('1002317', '3800', 'Hat 2'),	/*Grey Headband*/
('1002316', '7400', 'Hat 2'),	/*Blue Straw Hat*/
/*Page 2*/
('1002315', '5600', 'Hat 2'),	/*Red Straw Hat*/
('1002314', '3400', 'Hat 2'),	/*Zombie Mushroom Hat*/
('1002313', '3400', 'Hat 2'),	/*Palm Tree Hat*/
('1002312', '3600', 'Hat 2'),	/*Evil Watermelon Hat*/
('1002311', '7400', 'Hat 2'),	/*Traveler's Hat*/
('1002310', '8800', 'Hat 2'),	/*Flower Crown*/
('1002309', '6400', 'Hat 2'),	/*Watermelon Hat*/
('1002308', '7100', 'Hat 2'),	/*Orange B-Ball Headband*/
('1002307', '3200', 'Hat 2'),	/*Blue B-Ball Headband*/
/*Page 3*/
('1002306', '7600', 'Hat 2'),	/*Brown Headband*/
('1002305', '5600', 'Hat 2'),	/*Blue Headband*/
('1002304', '8800', 'Hat 2'),	/*Silver Chain Hat*/
('1002335', '5600', 'Hat 2'),	/*Triangular Hat*/
('1002334', '7400', 'Hat 2'),	/*Raccoon Hat*/
('1002333', '5600', 'Hat 2'),	/*Big Halo*/
('1002332', '7400', 'Hat 2'),	/*Cloud Goblin*/
('1002331', '7100', 'Hat 2'),	/*Wind Goblin*/
('1002322', '4700', 'Hat 2'),	/*Lobster Hat*/
/*Page 4*/
('1002321', '6000', 'Hat 2'),	/*Crow Hat*/
('1002320', '7100', 'Hat 2'),	/*Fuji Hat*/
('1002351', '7100', 'Hat 2'),	/*Yellow Cowboy Hat*/
('1002350', '7100', 'Hat 2'),	/*Red Cowboy Hat*/
('1002349', '8800', 'Hat 2'),	/*Black Cowboy Hat*/
('1002348', '5600', 'Hat 2'),	/*Bamboo Hat*/
('1002347', '4000', 'Hat 2'),	/*Brown Corporal Hat*/
('1002346', '7600', 'Hat 2'),	/*Blue Corporal Hat*/
('1002345', '7400', 'Hat 2'),	/*Party Hat*/
/*Page 5*/
('1002344', '6000', 'Hat 2'),	/*Woodsman Hat*/
('1002343', '3800', 'Hat 2'),	/*White Beanie*/
('1002342', '3400', 'Hat 2'),	/*Olive Beanie*/
('1002341', '5200', 'Hat 2'),	/*Starry Olive Beanie*/
('1002337', '7600', 'Hat 2'),	/*Laurel Crown*/
('1002336', '6300', 'Hat 2'),	/*Noble Moca*/
('1002367', '8800', 'Hat 2'),	/*Angel Halo*/
('1002362', '6400', 'Hat 2'),	/*White Festive Gumball*/
('1002361', '7600', 'Hat 2'),	/*Red Festive Gumball*/
/*Page 6*/
('1002360', '7100', 'Hat 2'),	/*Pink Knitted Gumball*/
('1002359', '6400', 'Hat 2'),	/*Blue Knitted Gumball*/
('1002358', '6300', 'Hat 2'),	/*Green Knitted Gumball*/
('1002356', '5200', 'Hat 2'),	/*Yellow Kitty Beanie*/
('1002355', '7100', 'Hat 2'),	/*Blue Kitty Beanie*/
('1002354', '3200', 'Hat 2'),	/*Yellow Knitted Hat*/
('1002353', '5000', 'Hat 2'),	/*Purple Knitted Hat*/
('1002352', '4900', 'Hat 2'),	/*Red Knitted Hat*/
('1002376', '7400', 'Hat 2'),	/*Pink Beret*/
/*Page 7*/
('1002375', '7600', 'Hat 2'),	/*Yellow Beret*/
('1002374', '8800', 'Hat 2'),	/*Red Beret*/
('1002373', '5600', 'Hat 2'),	/*Cloth Wrapper*/
('1002372', '3400', 'Hat 2'),	/*Feathered Bandana with Hearts*/
('1002371', '7100', 'Hat 2'),	/*Red-Dotted Feathered Bandana*/
('1002370', '3200', 'Hat 2'),	/*Black-Striped Feathered Bandana*/
('1002369', '8800', 'Hat 2'),	/*Antenna Hairband*/
('1002368', '6000', 'Hat 2'),	/*Reindeer Hat*/
('1002397', '7400', 'Hat 2'),	/*Sunflower Petal*/
/*Page 8*/
('1002396', '7100', 'Hat 2'),	/*Hawaiian Flower*/
('1002389', '6300', 'Hat 2'),	/*Devilish Horns*/
('1002388', '5000', 'Hat 2'),	/*Peter Pan Hat*/
('1002387', '3600', 'Hat 2'),	/*Green Eskimo Hat*/
('1002386', '3400', 'Hat 2'),	/*Brown Eskimo Hat*/
('1002385', '3800', 'Hat 2'),	/*Red Eskimo Hat*/
('1002384', '7600', 'Hat 2'),	/*Casual Cowboy Hat*/
('1002415', '4700', 'Hat 2'),	/*Zombie Mushroom Hat*/
('1002414', '8800', 'Hat 2'),	/*Orange Mushroom Hat*/
/*Page 9*/
('1002413', '7600', 'Hat 2'),	/*Octopus Hat*/
('1002412', '5200', 'Hat 2'),	/*Skyblue Turban*/
('1002411', '3200', 'Hat 2'),	/*Yellow Turban*/
('1002410', '3600', 'Hat 2'),	/*Pink Turban*/
('1002409', '6400', 'Hat 2'),	/*Tin Bucket*/
('1002431', '3400', 'Hat 2'),	/*Bull's Horn*/
('1002429', '3600', 'Hat 2'),	/*Meshcap*/
('1002428', '6300', 'Hat 2'),	/*Beige Checkered Hat*/
('1002427', '7400', 'Hat 2'),	/*Green Goya Beret*/
/*Page 10*/
('1002426', '7100', 'Hat 2'),	/*Beige Goya Beret*/
('1002423', '7400', 'Hat 2'),	/*Yellow Knitted Beanie*/
('1002422', '7600', 'Hat 2'),	/*Blue Knitted Beanie*/
('1002421', '5000', 'Hat 2'),	/*Pink Knitted Beanie*/
('1002420', '7100', 'Hat 2'),	/*Biker Bandana*/
('1002417', '3400', 'Hat 2'),	/*Drake Hat*/
('1002416', '3600', 'Hat 2'),	/*Slime Hat*/
('1002447', '3800', 'Hat 2'),	/*Rolled Towel*/
('1002446', '3400', 'Hat 2'),	/*Sun Quan Headpiece*/
/*Page 11*/
('1002445', '4900', 'Hat 2'),	/*Cao Cao Headpiece*/
('1002444', '4000', 'Hat 2'),	/*Liu Bei Headpiece*/
('1002443', '3400', 'Hat 2'),	/*Patissier Hat*/
('1002442', '3400', 'Hat 2'),	/*Rainbow Afro Wig*/
('1002440', '7400', 'Hat 2'),	/*Pink Jelly Cap*/
('1002439', '3400', 'Hat 2'),	/*Blue Jelly Cap*/
('1002438', '3600', 'Hat 2'),	/*Zhu-Ge-Liang Hat*/
('1002437', '6400', 'Hat 2'),	/*Guan Yu Headpiece*/
('1002435', '7600', 'Hat 2'),	/*Korean Flower Petal*/
/*Page 12*/
('1002434', '4900', 'Hat 2'),	/*Autumn Hat*/
('1002433', '3600', 'Hat 2'),	/*Summer Hat*/
('1002432', '6000', 'Hat 2'),	/*Spring Hat*/
('1002463', '8800', 'Hat 2'),	/*Horoscope Hat (Virgo)*/
('1002462', '4900', 'Hat 2'),	/*Horoscope Hat (Leo)*/
('1002461', '8800', 'Hat 2'),	/*Horoscope Hat (Cancer)*/
('1002460', '5200', 'Hat 2'),	/*Horoscope Hat (Gemini)*/
('1002459', '3600', 'Hat 2'),	/*Horoscope Hat (Taurus)*/
('1002458', '3200', 'Hat 2'),	/*Horoscope Hat (Aries)*/
/*Page 13*/
('1002457', '7400', 'Hat 2'),	/*Horoscope Hat (Pisces)*/
('1002456', '3600', 'Hat 2'),	/*Horoscope Hat (Aquarius)*/
('1002451', '4700', 'Hat 2'),	/*Starfish*/
('1002450', '7400', 'Hat 2'),	/*Conch Cap*/
('1002449', '3200', 'Hat 2'),	/*Winged Cap*/
('1002479', '5600', 'Hat 2'),	/*Snowman Mask*/
('1002478', '3800', 'Hat 2'),	/*Mushroom Hair Pin*/
('1002477', '5600', 'Hat 2'),	/*Slime Hair Pin*/
('1002476', '7400', 'Hat 2'),	/*Rough Hat*/
/*Page 14*/
('1002472', '6400', 'Hat 2'),	/*Cabbage Patch Hat*/
('1002470', '4900', 'Hat 2'),	/*Welding Mask*/
('1002469', '3600', 'Hat 2'),	/*Jester Hat*/
('1002468', '3200', 'Hat 2'),	/*Golden Bulldog Hat*/
('1002467', '8800', 'Hat 2'),	/*Horoscope Hat (Capricorn)*/
('1002466', '8800', 'Hat 2'),	/*Horoscope Hat (Sagittarius)*/
('1002465', '6400', 'Hat 2'),	/*Horoscope Hat (Scorpius)*/
('1002464', '4700', 'Hat 2'),	/*Horoscope Hat (Libra)*/
('1002495', '6300', 'Hat 2'),	/*Angora Hat*/
/*Page 15*/
('1002493', '7100', 'Hat 2'),	/*Teddy Bear Headgear*/
('1002491', '3200', 'Hat 2'),	/*Musashi Hat*/
('1002490', '3800', 'Hat 2'),	/*Football Helmet (Away)*/
('1002489', '3800', 'Hat 2'),	/*Football Helmet (Home)*/
('1002488', '3800', 'Hat 2'),	/*Military Fur Hat*/
('1002487', '6300', 'Hat 2'),	/*Rainbow Visor Beanie*/
('1002486', '7100', 'Hat 2'),	/*Green Visor Beanie*/
('1002485', '3800', 'Hat 2'),	/*Grey Visor Beanie*/
('1002484', '5200', 'Hat 2'),	/*Polar Bear Hat*/
/*Page 16*/
('1002482', '3800', 'Hat 2'),	/*Red Snowboard Helmet*/
('1002481', '7600', 'Hat 2'),	/*Black Snowboard Helmet*/
('1002480', '6400', 'Hat 2'),	/*White Wig Hat*/
('1002507', '7400', 'Hat 2'),	/*Soccer Ball Hat*/
('1002506', '3200', 'Hat 2'),	/*Flower Crown*/
('1002505', '4700', 'Hat 2'),	/*Sergeant Hat*/
('1002504', '4000', 'Hat 2'),	/*Old Fisherman Hat*/
('1002503', '6300', 'Hat 2'),	/*Vintage Pink Hat*/
('1002502', '3400', 'Hat 2'),	/*Vintage Denim Hat*/
/*Page 17*/
('1002501', '5600', 'Hat 2'),	/*Reggae Hat*/
('1002500', '7100', 'Hat 2'),	/*Korean Flag Bandana*/
('1002499', '4900', 'Hat 2'),	/*White Tiger Hat*/
('1002498', '4700', 'Hat 2'),	/*Bald Wig*/
('1002497', '6400', 'Hat 2'),	/*Hunting Cap*/
('1002496', '3600', 'Hat 2'),	/*Black Skull Bandana*/
('1002526', '6000', 'Hat 2'),	/*Skull Hat*/
('1002525', '5200', 'Hat 2'),	/*Mummy Hat*/
('1002524', '3600', 'Hat 2'),	/*Coke Hat*/
/*Page 18*/
('1002523', '3600', 'Hat 2'),	/*Paper Boat Hat*/
('1002522', '4900', 'Hat 2'),	/*Pink-Dotted Hairband*/
('1002521', '3400', 'Hat 2'),	/*White Hairband*/
('1002520', '7400', 'Hat 2'),	/*Red Rose*/
('1002519', '3600', 'Hat 2'),	/*White Felt Hat*/
('1002513', '5600', 'Hat 2'),	/*Maple Party Hat*/
('1002512', '3600', 'Hat 2'),	/*Red Spirit Bandana*/
('1002543', '8800', 'Hat 2'),	/*Acorn Helmet*/
('1002542', '6300', 'Hat 2'),	/*Acorn Headgear*/
/*Page 19*/
('1002536', '6400', 'Hat 2'),	/*Paper Bag*/
('1002534', '6400', 'Hat 2'),	/*White Puppy Hat*/
('1002559', '3800', 'Hat 2'),	/*Nordic Knitted Beanie*/
('1002558', '4900', 'Hat 2'),	/*Werebeast*/
('1002557', '6300', 'Hat 2'),	/*Jr. Lioner Hat*/
('1002556', '6000', 'Hat 2'),	/*Maple-Stein Head*/
('1002555', '3200', 'Hat 2'),	/*Demon Goblin*/
('1002552', '3200', 'Hat 2'),	/*Moon Bunny Headgear*/
('1002549', '3400', 'Hat 2'),	/*Black Cat Hat*/
/*Page 20*/
('1002548', '5600', 'Hat 2'),	/*White Rabbit Hat*/
('1002545', '5600', 'Hat 2'),	/*Yellow Slime Hat*/
('1002544', '6300', 'Hat 2'),	/*Pumpkin Headgear*/
('1002575', '4900', 'Hat 2'),	/*Angel Headband*/
('1002570', '7100', 'Hat 2'),	/*Pastel Cap*/
('1002569', '3200', 'Hat 2'),	/*Candlelight hat*/
('1002568', '3200', 'Hat 2'),	/*Tweed Headband*/
('1002567', '7100', 'Hat 2'),	/*Elf Hat*/
('1002566', '4900', 'Hat 2'),	/*Skull Beanie*/
/*Page 21*/
('1002565', '6300', 'Hat 2'),	/*Fur Hat*/
('1002560', '7100', 'Hat 2'),	/*Striped Knitted Beanie*/
('1002591', '4900', 'Hat 2'),	/*Leatty Hat*/
('1002590', '8800', 'Hat 2'),	/*Star Baseball Cap*/
('1002583', '6300', 'Hat 2'),	/*Wrestling Mask*/
('1002582', '4900', 'Hat 2'),	/*Maximus Galea*/
('1002576', '5000', 'Hat 2'),	/*Fallen Angel Headband*/
('1002607', '5200', 'Hat 2'),	/*Zhu Ba Jie Hat*/
('1002605', '3600', 'Hat 2'),	/*Jet Black Head Scarf*/
/*Page 22*/
('1002599', '3200', 'Hat 2'),	/*Golden Trench Helmet*/
('1002598', '7100', 'Hat 2'),	/*Rabbit Ear*/
('1002597', '4700', 'Hat 2'),	/*Husky Hat*/
('1002596', '7100', 'Hat 2'),	/*Bulldog Cap*/
('1002594', '6400', 'Hat 2'),	/*Goggled Smiley Headgear*/
('1002593', '6300', 'Hat 2'),	/*Smiley Headgear*/
('1002592', '6400', 'Hat 2'),	/*Sun Wu Kong Hat*/
('1002609', '8800', 'Hat 2'),	/*Crazy Bunny Hat*/
('1002608', '3200', 'Hat 2'),	/*Superstar Cap*/
/*Page 23*/
('1002654', '3400', 'Hat 2'),	/*Orange Mushroom Hat*/
('1002653', '4000', 'Hat 2'),	/*Stack of Books*/
('1002650', '7400', 'Hat 2'),	/*Vintage Grey Cap*/
('1002667', '3800', 'Hat 2'),	/*Star Hair Pin*/
('1002666', '7600', 'Hat 2'),	/*White Basic Cap*/
('1002665', '5200', 'Hat 2'),	/*Tomato Hat*/
('1002661', '4900', 'Hat 2'),	/*Bird Nest*/
('1002660', '4900', 'Hat 2'),	/*Orange Cap with Shades*/
('1002679', '4000', 'Hat 2'),	/*Eye Poppers*/
/*Page 24*/
('1002678', '6000', 'Hat 2'),	/*Old Hockey Mask*/
('1002674', '5000', 'Hat 2'),	/*Helm of the Bronze Monk*/
('1002673', '3200', 'Hat 2'),	/*Helm of the Silver Monk*/
('1002672', '7100', 'Hat 2'),	/*Helm of the Golden Monk*/
('1002703', '3400', 'Hat 2'),	/*Big Blue Eye - Bluesy*/
('1002701', '7600', 'Hat 2'),	/*Huge Green Lips*/
('1002700', '8800', 'Hat 2'),	/*Big Green Eye*/
('1002698', '8800', 'Hat 2'),	/*Vintage Khaki Cap*/
('1002697', '7600', 'Hat 2'),	/*Devilfish Headgear*/
/*Page 25*/
('1002696', '4000', 'Hat 2'),	/*Stoplight Hat*/
('1002695', '3200', 'Hat 2'),	/*Soul Teddy Hat*/
('1002694', '5000', 'Hat 2'),	/*Centaurus Horns (Light)*/
('1002693', '3800', 'Hat 2'),	/*Centaurus Horns (Green)*/
('1002692', '8800', 'Hat 2'),	/*Centaurus Horns (Ghost)*/
('1002691', '5600', 'Hat 2'),	/*Centaurus Horns*/
('1002715', '5600', 'Hat 2'),	/*Military Beanie*/
('1002714', '3400', 'Hat 2'),	/*Christmas Tree Hat*/
('1002713', '7600', 'Hat 2'),	/*Black Bubble Beanie*/
/*Page 26*/
('1002712', '7100', 'Hat 2'),	/*Black Kitty Ears*/
('1002711', '4000', 'Hat 2'),	/*White Kitty Ears*/
('1002710', '3400', 'Hat 2'),	/*Pink Kitty Hat*/
('1002709', '7100', 'Hat 2'),	/*Snowy Knitted Hat*/
('1002708', '3800', 'Hat 2'),	/*Red Vintage Bandana*/
('1002706', '4000', 'Hat 2'),	/*Huge Red Lips*/
('1002705', '3200', 'Hat 2'),	/*Huge Blue Lips*/
('1002704', '6300', 'Hat 2'),	/*Big Blue Eye - Bright*/
('1002735', '3600', 'Hat 2'),	/*Glowy Smile Cap*/
/*Page 27*/
('1002734', '6000', 'Hat 2'),	/*Chinese Lion Headgear*/
('1002727', '7100', 'Hat 2'),	/*Huge Pink Ribbon*/
('1002726', '5200', 'Hat 2'),	/*Umbrella Hat*/
('1002725', '5000', 'Hat 2'),	/*Pierced Apple*/
('1002724', '7100', 'Hat 2'),	/*Cat Hat*/
('1002722', '5200', 'Hat 2'),	/*Teddy Earmuffs*/
('1002721', '8800', 'Hat 2'),	/*Raccoon Earmuffs*/
('1002720', '4000', 'Hat 2'),	/*Lovely Christmas*/
('1002748', '6300', 'Hat 2'),	/*Apple-Green Hood*/
/*Page 28*/
('1002747', '6000', 'Hat 2'),	/*Superstar Headphones*/
('1002746', '3600', 'Hat 2'),	/*Sleepy Turkey*/
('1002745', '8800', 'Hat 2'),	/*Baby Gold Dragon*/
('1002742', '7600', 'Hat 2'),	/*Baby Turkey Hat*/
('1002741', '7100', 'Hat 2'),	/*Yellow Baby Dragon Hat*/
('1002738', '8800', 'Hat 2'),	/*Bunny Earmuffs*/
('1002736', '6300', 'Hat 2'),	/*Glowy Patterned Cap*/
('1002761', '5200', 'Hat 2'),	/*Maple Leaf eye mask*/
('1002760', '8800', 'Hat 2'),	/*Globe Cap*/
/*Page 29*/
('1002759', '6400', 'Hat 2'),	/*Maple Hood Hat*/
('1002756', '5000', 'Hat 2'),	/*Hero's Casket*/
('1002755', '3800', 'Hat 2'),	/*Hero's Beret*/



/*Page 1*/
('1002754', '7100', 'Hat 3'),	/*Orange Mushroom Scholar*/
('1002753', '3800', 'Hat 3'),	/*Stylish Pink Cotton Cap*/
('1002752', '5600', 'Hat 3'),	/*Celestial Crown*/
('1002775', '5000', 'Hat 3'),	/*3rd Anniversary Hat*/
('1002774', '6300', 'Hat 3'),	/*Victory Hairpin*/
('1002771', '4900', 'Hat 3'),	/*Tiger Cub Hat*/
('1002770', '5000', 'Hat 3'),	/*Cone Ears*/
('1002796', '6300', 'Hat 3'),	/*Cutie Birk Hat*/
('1002785', '7400', 'Hat 3'),	/*Prismatic Sun Cap*/
/*Page 2*/
('1002784', '5000', 'Hat 3'),	/*"A" Cap*/
('1002811', '6000', 'Hat 3'),	/*Striped Bandana*/
('1002804', '3800', 'Hat 3'),	/*Brown Felt Hat*/
('1002803', '7100', 'Hat 3'),	/*Mrs. Octopus*/
('1002831', '7400', 'Hat 3'),	/*Leo Hairpin*/
('1002824', '7100', 'Hat 3'),	/*Noob Hat*/
('1002823', '3200', 'Hat 3'),	/*Scarface Mask*/
('1002822', '5200', 'Hat 3'),	/*Bird Nest*/
('1002821', '3800', 'Hat 3'),	/*Violet Heart Beanie*/
/*Page 3*/
('1002820', '4900', 'Hat 3'),	/*Inferno Horns*/
('1002847', '3200', 'Hat 3'),	/*Frog Hat*/
('1002846', '7100', 'Hat 3'),	/*Blue Bow Beret*/
('1002845', '4900', 'Hat 3'),	/*Pink Bunny Cap*/
('1002844', '7600', 'Hat 3'),	/*Chipmunk Ears*/
('1002843', '7400', 'Hat 3'),	/*Silver Fox Ears*/
('1002842', '4000', 'Hat 3'),	/*Golden Fox Ears*/
('1002840', '5200', 'Hat 3'),	/*Hatched Bird Cap*/
('1002839', '3600', 'Hat 3'),	/*Pumpkin Hat*/
/*Page 4*/
('1002837', '6000', 'Hat 3'),	/*Tengu Mask*/
('1002836', '5000', 'Hat 3'),	/*Capricorn Hair Clip*/
('1002835', '6000', 'Hat 3'),	/*Sagittarius Hair Clip*/
('1002834', '4000', 'Hat 3'),	/*Scorpius Hairpin*/
('1002863', '3600', 'Hat 3'),	/*Bear Tassel Hat*/
('1002849', '3600', 'Hat 3'),	/*Panda Hat*/
('1002878', '3600', 'Hat 3'),	/*Snow Flake Hat*/
('1002877', '5200', 'Hat 3'),	/*Cow Mask*/
('1002876', '3600', 'Hat 3'),	/*Holly Hair Clip*/
/*Page 5*/
('1002870', '4900', 'Hat 3'),	/*Moon Bunny Hat*/
('1002891', '6400', 'Hat 3'),	/*Green Ribbon Hairband*/
('1002890', '3200', 'Hat 3'),	/*Blue Ribbon Hairband*/
('1002889', '6300', 'Hat 3'),	/*Purple Ribbon Hairband*/
('1002888', '5000', 'Hat 3'),	/*Red Ribbon Hairband*/
('1002887', '6000', 'Hat 3'),	/*Pink Ribbon Hairband*/
('1002886', '3600', 'Hat 3'),	/*Strawberry Hairband*/
('1002885', '7100', 'Hat 3'),	/*Pink Bow*/
('1002884', '3200', 'Hat 3'),	/*Red Panda Cap*/
/*Page 6*/
('1002882', '6400', 'Hat 3'),	/*Owl Hat*/
('1002907', '5200', 'Hat 3'),	/*Checkered Fedora*/
('1002903', '7600', 'Hat 3'),	/*Pink Bandana*/
('1002923', '5000', 'Hat 3'),	/*Treacherous Wolf Hat*/
('1002922', '7400', 'Hat 3'),	/*Navy Hoodie Cap*/
('1002921', '3800', 'Hat 3'),	/*Blue Mini Hat*/
('1002920', '6300', 'Hat 3'),	/*Pink Mini Hat*/
('1002919', '4000', 'Hat 3'),	/*Courageous Little Lamb Hat*/
('1002913', '5600', 'Hat 3'),	/*Miranda Ribbon*/
/*Page 7*/
('1002912', '3600', 'Hat 3'),	/*Iljimae Mask*/
('1002943', '3400', 'Hat 3'),	/*Sailor Hat*/
('1002942', '4000', 'Hat 3'),	/*Green Mushroom Hat*/
('1002941', '5000', 'Hat 3'),	/*Moon Bloom Hair Pin*/
('1002937', '4900', 'Hat 3'),	/*Felt Hat*/
('1002930', '7100', 'Hat 3'),	/*6th Anniversary Cone Hat*/
('1002929', '5000', 'Hat 3'),	/*Colorful Striped Beanie*/
('1002928', '4700', 'Hat 3'),	/*Pink Star Beanie*/
('1002957', '6400', 'Hat 3'),	/*Pink Bean Hat*/
/*Page 8*/
('1002956', '5200', 'Hat 3'),	/*Blue Mushroom Hat*/
('1002955', '6000', 'Hat 3'),	/*Brave Musashi Helmet*/
('1002954', '3200', 'Hat 3'),	/*Aran Helmet*/
('1002953', '3800', 'Hat 3'),	/*Fluttering Sunhat*/
('1002952', '7400', 'Hat 3'),	/*Purple Flower Headwrap*/
('1002951', '7400', 'Hat 3'),	/*Yellow Flower Headwrap*/
('1002950', '7400', 'Hat 3'),	/*Pink Flower Headwrap*/
('1002945', '6000', 'Hat 3'),	/*Heart Hairband*/
('1002944', '5200', 'Hat 3'),	/*Honey Bee Hat*/
/*Page 9*/
('1002975', '4700', 'Hat 3'),	/*Aviator Pilot Goggles*/
('1002974', '4700', 'Hat 3'),	/*Jr. Lucida Hat*/
('1002973', '5200', 'Hat 3'),	/*Mysterious Mask*/
('1002970', '5600', 'Hat 3'),	/*Moon Bunny Hat*/
('1002969', '4900', 'Hat 3'),	/*Brown Puppy Ears*/
('1002968', '6300', 'Hat 3'),	/*Pancake Hat*/
('1002967', '6300', 'Hat 3'),	/*Teru Teru Hairband*/
('1002962', '7600', 'Hat 3'),	/*Peony Flower Accessory*/
('1002961', '5200', 'Hat 3'),	/*Gray Mask*/
/*Page 10*/
('1002960', '7400', 'Hat 3'),	/*Black Crown*/
('1002987', '3600', 'Hat 3'),	/*Cursed Golden trench helmet*/
('1002985', '7100', 'Hat 3'),	/*Pachinko Marble-box Hat*/
('1002984', '6000', 'Hat 3'),	/*Spiegelmann's Hat*/
('1002983', '4000', 'Hat 3'),	/*We Care! Hat*/
('1002979', '4000', 'Hat 3'),	/*Marbum Headgear*/
('1002978', '7600', 'Hat 3'),	/*Cute Mouse Ears*/
('1002976', '4900', 'Hat 3'),	/*Maid Headband*/
('1003006', '7400', 'Hat 3'),	/*Kitty Camping Hat*/
/*Page 11*/
('1003005', '5000', 'Hat 3'),	/*Maple Racing Helmet*/
('1003001', '7600', 'Hat 3'),	/*Chaos Metallic Helmet*/
('1003000', '3600', 'Hat 3'),	/*Cherry Blossom Hair*/
('1002999', '3200', 'Hat 3'),	/*Fire Shadow Hair*/
('1002998', '8800', 'Hat 3'),	/*Edwin Wig*/
('1002995', '5200', 'Hat 3'),	/*Royal Navy Hat*/
('1003022', '4000', 'Hat 3'),	/*Devil Horns*/
('1003015', '7400', 'Hat 3'),	/*Blue Scooter Helmet*/
('1003014', '8800', 'Hat 3'),	/*Pink Scooter Helmet*/
/*Page 12*/
('1003013', '3200', 'Hat 3'),	/*Red Loose-Fit Beanie*/
('1003010', '7100', 'Hat 3'),	/*Dancing Blue Butterfly*/
('1003009', '7400', 'Hat 3'),	/*Christmas Light Hairband*/
('1003008', '3400', 'Hat 3'),	/*Pharaoh Crown*/
('1003038', '6000', 'Hat 3'),	/*Doll Face Hat*/
('1003030', '5200', 'Hat 3'),	/*Former Hero Male Face*/
('1003029', '3800', 'Hat 3'),	/*Former Hero Female Face*/
('1003054', '4700', 'Hat 3'),	/*White Fur Hat*/
('1003053', '4000', 'Hat 3'),	/*Pink Fur Hat*/
/*Page 13*/
('1003052', '8800', 'Hat 3'),	/*Tilted Fedora*/
('1003051', '4700', 'Hat 3'),	/*Desert Fox*/
('1003050', '7600', 'Hat 3'),	/*Bunny Ears*/
('1003049', '3400', 'Hat 3'),	/*Giant Bear Cap*/
('1003048', '6400', 'Hat 3'),	/*Christmas Wreath*/
('1003047', '3600', 'Hat 3'),	/*Bear Hat*/
('1003044', '5600', 'Hat 3'),	/*Clown Hat*/
('1003043', '3200', 'Hat 3'),	/*Christmas Bell*/
('1003071', '8800', 'Hat 3'),	/*Pinky Bow*/
/*Page 14*/
('1003070', '3200', 'Hat 3'),	/*Tiger-Print Cap*/
('1003060', '5600', 'Hat 3'),	/*Silver Coronet*/
('1003059', '3400', 'Hat 3'),	/*Qi-pao Hair*/
('1003058', '5200', 'Hat 3'),	/*Christmas Hairpin*/
('1003057', '4000', 'Hat 3'),	/*Mini Crown*/
('1003084', '3600', 'Hat 3'),	/*Royal Crown*/
('1003083', '4700', 'Hat 3'),	/*Sprout Hat*/
('1003082', '7600', 'Hat 3'),	/*Wolf Hat*/
('1003080', '8800', 'Hat 3'),	/*Cat Set Hat*/
/*Page 15*/
('1003079', '8800', 'Hat 3'),	/*Green Leaf Hat*/
('1003078', '4900', 'Hat 3'),	/*Sparkling Butterfly*/
('1003077', '3600', 'Hat 3'),	/*Knitted Corsage Hat*/
('1003074', '6000', 'Hat 3'),	/*Strawberry Hat*/
('1003072', '5600', 'Hat 3'),	/*Black-Lace Ribbon*/
('1003103', '3200', 'Hat 3'),	/*6th Anniversary Top Hat*/
('1003101', '7600', 'Hat 3'),	/*Dunas Hat*/
('1003092', '3200', 'Hat 3'),	/*Hawkeye Captain Hat*/
('1003089', '4700', 'Hat 3'),	/*Evan Wing Headband*/
/*Page 16*/
('1003109', '6400', 'Hat 3'),	/*Royal Rainbow Hood*/
('1003135', '5000', 'Hat 3'),	/*Wild Hunter's Hat*/
('1003133', '6400', 'Hat 3'),	/*White Bow*/
('1003132', '3200', 'Hat 3'),	/*Red Bow*/
('1003131', '6000', 'Hat 3'),	/*Black Dressy Ribbon*/
('1003130', '3200', 'Hat 3'),	/*Shining Feather*/
('1003123', '4900', 'Hat 3'),	/*Black Petite Scarf*/
('1003122', '3200', 'Hat 3'),	/*Yellow Petite Scarf*/
('1003121', '3400', 'Hat 3'),	/*Evan Headband*/
/*Page 17*/
('1003120', '5600', 'Hat 3'),	/*Oz Magic Hat*/
('1003149', '8800', 'Hat 3'),	/*Rabbit Ear Hood*/
('1003148', '8800', 'Hat 3'),	/*Pilot Cap*/
('1003147', '5600', 'Hat 3'),	/*Maid Headband (Blue)*/
('1003146', '6000', 'Hat 3'),	/*Lace Ribbon (Pink)*/
('1003145', '5000', 'Hat 3'),	/*Dragon Hat*/
('1003144', '6000', 'Hat 3'),	/*King Crow Hat*/
('1003141', '6400', 'Hat 3'),	/*Straw Sun Hat*/
('1003136', '4700', 'Hat 3'),	/*Battle Mage Goggles*/
/*Page 18*/
('1003163', '3200', 'Hat 3'),	/*Brown Hunting Cap*/
('1003161', '5200', 'Hat 3'),	/*Sanctus Combat Veil*/
('1003182', '6400', 'Hat 3'),	/*Paypal Cap*/
('1003171', '7600', 'Hat 3'),	/*Leather Cap*/
('1003170', '8800', 'Hat 3'),	/*Star Head Wrap*/
('1003196', '5600', 'Hat 3'),	/*Rudolph Santa Hat*/
('1003194', '4700', 'Hat 3'),	/*Rookie Bobble Heart Band*/
('1003193', '5000', 'Hat 3'),	/*Red Pre-School Uniform Hat*/
('1003192', '4900', 'Hat 3'),	/*Blue Pre-School Uniform Hat*/
/*Page 19*/
('1003187', '3200', 'Hat 3'),	/*Gray Cat Hood*/
('1003186', '3400', 'Hat 3'),	/*Pink Cat Hood*/
('1003185', '7600', 'Hat 3'),	/*Rabbit hat*/
('1003215', '3200', 'Hat 3'),	/*Pink Snowdrop Cunning Hat*/
('1003214', '4700', 'Hat 3'),	/*Blue Snowdrop Cunning Hat*/
('1003211', '6300', 'Hat 3'),	/*Winter 2010 Moon Bunny Hat*/
('1003210', '3600', 'Hat 3'),	/*Earmuffs and Pom Pom Beanie*/
('1003208', '8800', 'Hat 3'),	/*Magic Hat*/
('1003207', '3800', 'Hat 3'),	/*Curly Rabbit Poof*/
/*Page 20*/
('1003204', '6000', 'Hat 3'),	/*Courageous Bunny Hat*/
('1003203', '5000', 'Hat 3'),	/*Red Riding Hood*/
('1003202', '3200', 'Hat 3'),	/*Golden Beetle*/
('1003226', '6000', 'Hat 3'),	/*Rookie Hatchling Hat*/
('1003223', '8800', 'Hat 3'),	/*Lost Baby Chick*/
('1003222', '7400', 'Hat 3'),	/*Blue Polka Dot Bow*/
('1003221', '3600', 'Hat 3'),	/*Pink Polka Dot Bow*/
('1003220', '8800', 'Hat 3'),	/*Knit Flower Hairband*/
('1003218', '4000', 'Hat 3'),	/*Flower Heiress Band*/
/*Page 21*/
('1003217', '3200', 'Hat 3'),	/*Flower Heir Cap*/
('1003216', '6300', 'Hat 3'),	/*Pirate Captain's Hat*/
('1003247', '3600', 'Hat 3'),	/*Mad Hatter's Hat*/
('1003241', '4000', 'Hat 3'),	/*6th Anniversary Party Hat*/
('1003240', '6300', 'Hat 3'),	/*Blueberry Candy Hoodie*/
('1003239', '5000', 'Hat 3'),	/*Raspberry Candy Hoodie*/
('1003238', '5200', 'Hat 3'),	/*Gray Puppy Ears*/
('1003237', '3600', 'Hat 3'),	/*Lion Head*/
('1003235', '6000', 'Hat 3'),	/*Blue Jeweled Chaplain Hat*/
/*Page 22*/
('1003234', '6000', 'Hat 3'),	/*Pink Jeweled Chaplain Hat*/
('1003233', '3600', 'Hat 3'),	/*Honey Rabbit */
('1003232', '3600', 'Hat 3'),	/*Pretty Teddy*/
('1003263', '4000', 'Hat 3'),	/*MSE 4 Years & Unstoppable Hat*/
('1003256', '5600', 'Hat 3'),	/*Emerald Musical Note*/
('1003255', '8800', 'Hat 3'),	/*Quartz Musical Note*/
('1003254', '8800', 'Hat 3'),	/*Sapphire Musical Note*/
('1003253', '3800', 'Hat 3'),	/*Amber Musical Note*/
('1003252', '7100', 'Hat 3'),	/*Amethyst Musical Note*/
/*Page 23*/
('1003251', '3600', 'Hat 3'),	/*Citrine Musical Note*/
('1003250', '7400', 'Hat 3'),	/*Ruby Musical Note*/
('1003249', '6400', 'Hat 3'),	/*Topaz Musical Note*/
('1003279', '3200', 'Hat 3'),	/*Chain Crusher Cap*/
('1003278', '3600', 'Hat 3'),	/*Mermaid Shell*/
('1003277', '6400', 'Hat 3'),	/*Grass Spirit*/
('1003276', '6300', 'Hat 3'),	/*Blue Heart Transparent Hat*/
('1003272', '4900', 'Hat 3'),	/*Bastille Hat*/
('1003271', '6000', 'Hat 3'),	/*Pink Heart Transparent Hat*/
/*Page 24*/
('1003269', '5000', 'Hat 3'),	/*Button-a-holic Toy Cap*/
('1003268', '8800', 'Hat 3'),	/*Button-a-holic Sugar Cap*/
('1003265', '5000', 'Hat 3'),	/*Marine Tinia Shades*/
('1003264', '3400', 'Hat 3'),	/*Rose Tinia Shades*/
('1003295', '8800', 'Hat 3'),	/*Lazy Chicken Headband*/
('1003358', '7100', 'Hat 3'),	/*Sweet Purple Sun Cap*/
('1003357', '7100', 'Hat 3'),	/*Night Navy Sun Cap*/
('1003356', '7100', 'Hat 3'),	/*Crystal Blue Sun Cap*/
('1003355', '6400', 'Hat 3'),	/*Lime Green Sun Cap*/
/*Page 25*/
('1003354', '6400', 'Hat 3'),	/*Fresh Lemon Sun Cap*/
('1003353', '4900', 'Hat 3'),	/*Dear Orange Sun Cap*/
('1003352', '6300', 'Hat 3'),	/*Tic Toc Red Sun Cap*/
('1003368', '8800', 'Hat 3'),	/*Western Cowboy Hat*/
('1003367', '6400', 'Hat 3'),	/*Crown of Flowers*/
('1003362', '7100', 'Hat 3'),	/*Rosy Pink Twin Ribbons*/
('1003390', '4700', 'Hat 3'),	/*Orchid's Black Wing Hat*/
('1003387', '7400', 'Hat 3'),	/*Beanie Headphone*/
('1003386', '5000', 'Hat 3'),	/*Bat Costume Hood*/
/*Page 26*/
('1003377', '6400', 'Hat 3'),	/*Alchemist Hat*/
('1003376', '3200', 'Hat 3'),	/*Memorial Angel*/
('1003404', '4000', 'Hat 3'),	/*Imp Hat*/
('1003403', '6000', 'Hat 3'),	/*Dark Cygnus's Hairband*/
('1003402', '3400', 'Hat 3'),	/*Dark Hawkeye*/
('1003401', '3800', 'Hat 3'),	/*Dark Eckhart*/
('1003400', '7600', 'Hat 3'),	/*Dark Irena*/
('1003399', '7100', 'Hat 3'),	/*Dark Oz*/
('1003398', '6400', 'Hat 3'),	/*Dark Mihile*/
/*Page 27*/
('1003393', '7400', 'Hat 3'),	/*Imperial Duke Crown*/
('1003392', '3400', 'Hat 3'),	/*Honeybee Antenna Hairband*/
('1003422', '7400', 'Hat 3'),	/*Garnet Raven Persona*/
('1003421', '3400', 'Hat 3'),	/*Noblesse Gold Hood*/
('1003417', '6300', 'Hat 3'),	/*Dino Cap*/
('1003416', '3400', 'Hat 3'),	/*Christmas Hairpin*/
('1003463', '6000', 'Hat 3'),	/*Pixiemom Hat*/
('1003462', '3800', 'Hat 3'),	/*Kitty Cap*/
('1003461', '4700', 'Hat 3'),	/*Lania's Flower Crown*/
/*Page 28*/
('1003460', '3200', 'Hat 3'),	/*Milk Chocolate Cone*/
('1003459', '5600', 'Hat 3'),	/*Lucia Hat*/
('1003487', '3200', 'Hat 3'),	/*White Zodiac Dragon Cake*/
('1003486', '5000', 'Hat 3'),	/*Yellow Zodiac Dragon Cake*/
('1003485', '6300', 'Hat 3'),	/*Green Zodiac Dragon Cake*/
('1003484', '6300', 'Hat 3'),	/*White Zodiac Dragon Hat*/
('1003483', '5600', 'Hat 3'),	/*Pink Zodiac Dragon Hat*/
('1003482', '4900', 'Hat 3'),	/*Green Zodiac Dragon Hat*/
('1003503', '5200', 'Hat 3'),	/*Alice's Teacup*/
/*Page 29*/
('1003496', '3200', 'Hat 3'),	/*Cute Shrimp Nigiri*/
('1003495', '6300', 'Hat 3'),	/*Tangy Fish Egg Nigiri*/
('1003494', '3800', 'Hat 3'),	/*Chewy Octopus Nigiri*/



/*Page 1*/
('1003493', '3600', 'Hat 4'),	/*Fresh Salmon Nigiri*/
('1003492', '6000', 'Hat 4'),	/*Crisp Egg Nigiri*/
('1003490', '6400', 'Hat 4'),	/*Maid Band*/
('1003489', '3200', 'Hat 4'),	/*Gas Mask and Helmet*/
('1003519', '4700', 'Hat 4'),	/*Sunset-colored Straw Hat*/
('1003518', '3200', 'Hat 4'),	/*Small Black Devil Hat*/
('1003517', '3800', 'Hat 4'),	/*Ebony Pimpernel Hat*/
('1003516', '7400', 'Hat 4'),	/*Honeybee Antenna Hairband*/
('1003510', '3800', 'Hat 4'),	/*Alice Rabbit Hat*/
/*Page 2*/
('1003509', '7100', 'Hat 4'),	/*Sausage Hat*/

('1003506', '4000', 'Hat 4'),	/*Intergalactic Hat*/
('1003505', '8800', 'Hat 4'),	/*Red Dragon Horn*/
('1003504', '5600', 'Hat 4'),	/*Blue Dragon Horn\r\n*/
('1003533', '3200', 'Hat 4'),	/*Legendary Hat*/
('1003532', '7100', 'Hat 4'),	/*Lucky Hat*/
('1003531', '3400', 'Hat 4'),	/*Dainty Hat*/
('1003520', '7100', 'Hat 4'),	/*Wire Headband*/
/*Page 3*/
('1003549', '6000', 'Hat 4'),	/*Aerial Mystic Black Silk Ribbon*/
('1003548', '3200', 'Hat 4'),	/*Aerial Mystic Black Silk Hat*/
('1003547', '3600', 'Hat 4'),	/*Parfait Cupcake Hairpin*/
('1003546', '4700', 'Hat 4'),	/*Chocolate Cupcake Hairpin*/
('1003545', '4700', 'Hat 4'),	/*Melon Cupcake Hairpin*/
('1003544', '5600', 'Hat 4'),	/*Strawberry Cupcake Hairpin*/
('1003543', '5600', 'Hat 4'),	/*Macaroon Hairpin*/
('1003542', '6000', 'Hat 4'),	/*Strawberry Macaroon Hairpin*/
('1003541', '6000', 'Hat 4'),	/*Country Rabbit Hat*/
/*Page 4*/
('1003539', '5600', 'Hat 4'),	/*GM Nori's Wing Cap*/
('1003538', '3600', 'Hat 4'),	/*Button-A-Holic Toy Cap*/
('1003536', '6000', 'Hat 4'),	/*Lucia Hat*/
('1003560', '7100', 'Hat 4'),	/*Yellow Cat Hood*/
('1003559', '7600', 'Hat 4'),	/*Blue Cat Hood*/
('1003597', '3200', 'Hat 4'),	/*Metal Crown Nuera*/
('1003596', '6000', 'Hat 4'),	/*Metal Pink Baseball Cap*/
('1003595', '5000', 'Hat 4'),	/*Curly Rabbit Poof*/
('1003594', '3400', 'Hat 4'),	/*Cool Summer Snorkeling*/
/*Page 5*/
('1003588', '6000', 'Hat 4'),	/*Pink Teddy Hat*/
('1003587', '4900', 'Hat 4'),	/*Pink Ribbon Marine Cap*/
('1003586', '3600', 'Hat 4'),	/*Mint Star Marine Cap*/
('1003626', '4700', 'Hat 4'),	/*Jett's Hat*/
('1003643', '6400', 'Hat 4'),	/*Yin-Yang Hairpin*/
('1003642', '5000', 'Hat 4'),	/*Cheering Gold*/
('1003641', '3800', 'Hat 4'),	/*Cheering Green*/
('1003640', '6000', 'Hat 4'),	/*Cheering Blue*/
('1003639', '3400', 'Hat 4'),	/*Cheering Pink*/
/*Page 6*/
('1003636', '4700', 'Hat 4'),	/*Aqua Shell*/
('1003658', '5000', 'Hat 4'),	/*Muneshige's Helm*/
('1003657', '3800', 'Hat 4'),	/*Shingen's Helm*/
('1003656', '7400', 'Hat 4'),	/*Hideyoshi's Helm*/
('1003655', '7100', 'Hat 4'),	/*Kanetsuku's Helm*/
('1003654', '7400', 'Hat 4'),	/*Yukimura's Helm*/
('1003673', '7400', 'Hat 4'),	/*Maple Green Beret*/
('1003672', '3200', 'Hat 4'),	/*Maple Black Beret*/
('1003671', '7100', 'Hat 4'),	/*Maple Blue Beret*/
/*Page 7*/
('1003670', '3600', 'Hat 4'),	/*Maple Red Beret*/
('1003669', '6300', 'Hat 4'),	/*Hyper Honeybee Antenna Hairband*/
('1003668', '7600', 'Hat 4'),	/*Hyper Lost Baby Chick*/
('1003667', '3600', 'Hat 4'),	/*Red Arabian Hat*/
('1003666', '3800', 'Hat 4'),	/*Blue Arabian Hat*/
('1003688', '7600', 'Hat 4'),	/*Hyper Cat Hat*/
('1003687', '6400', 'Hat 4'),	/*Hyper Teddy Earmuffs*/
('1003686', '4900', 'Hat 4'),	/*Paper Bag*/
('1003685', '4000', 'Hat 4'),	/*Angel Halo*/
/*Page 8*/
('1003684', '7100', 'Hat 4'),	/*Tiger Cub Hat*/
('1003683', '4900', 'Hat 4'),	/*Cow Mask*/
('1003682', '4000', 'Hat 4'),	/*Jiangshi Hat*/
('1003681', '6300', 'Hat 4'),	/*Ghost Mask*/
('1003711', '8800', 'Hat 4'),	/*[MS Custom] Doll Face Hat*/
('1003710', '4700', 'Hat 4'),	/*[MS Custom] Maple-Stein*/
('1003709', '6300', 'Hat 4'),	/*[MS Custom] Werebeast*/
('1003708', '7400', 'Hat 4'),	/*[MS Custom] The Chinese Undead's Hat (Maroon)*/
('1003707', '6300', 'Hat 4'),	/*[MS Custom] Black Snowboard Helmet*/
/*Page 9*/
('1003706', '5000', 'Hat 4'),	/*[MS Custom] Zombie Mushroom Hat*/
('1003705', '8800', 'Hat 4'),	/*[MS Custom] Green Picnic Hat*/
('1003704', '6300', 'Hat 4'),	/*[MS Custom] Red Red Rain Cap*/
('1003703', '8800', 'Hat 4'),	/*[MS Custom] Patissier Hat*/
('1003702', '7600', 'Hat 4'),	/*[MS Custom] Sky Blue Rain Cap*/
('1003701', '7600', 'Hat 4'),	/*[MS Custom] Yellow Rain Cap*/
('1003700', '5000', 'Hat 4'),	/*[MS Custom] Red Festive Gumball*/
('1003699', '8800', 'Hat 4'),	/*Hidden Street Red Husky Hat*/
('1003727', '4900', 'Hat 4'),	/*Red Pierre Hat*/
/*Page 10*/
('1003714', '3400', 'Hat 4'),	/*Halloween Leopard Ears*/
('1003713', '7100', 'Hat 4'),	/*Seal Hat*/
('1003712', '4700', 'Hat 4'),	/*[MS Discount] Chain Crusher Cap*/
('1003743', '5000', 'Hat 4'),	/*Slither Style Cap*/
('1003742', '4000', 'Hat 4'),	/*Dark Devil Hat*/
('1003739', '3600', 'Hat 4'),	/*Decked Out Santa Hat*/
('1003738', '3400', 'Hat 4'),	/*Santa Hat*/
('1003737', '6000', 'Hat 4'),	/*Snowman*/
('1003736', '6300', 'Hat 4'),	/*Reindeer Hat*/
/*Page 11*/
('1003735', '3200', 'Hat 4'),	/*Scarlion Boss Hat*/
('1003730', '5600', 'Hat 4'),	/*Cat Lolita Hat*/
('1003729', '3600', 'Hat 4'),	/*Hyper Bunny Earmuffs*/
('1003728', '5200', 'Hat 4'),	/*Blue Pierre Hat*/
('1003759', '4900', 'Hat 4'),	/*Blue Point Kitty Hat*/
('1003756', '3400', 'Hat 4'),	/*Polar Bear Hat*/
('1003750', '6400', 'Hat 4'),	/*Ribbon Kitty Ears*/
('1003749', '4900', 'Hat 4'),	/*Zodiac Snake Cake*/
('1003775', '6300', 'Hat 4'),	/*GM Hat*/
/*Page 12*/
('1003763', '5600', 'Hat 4'),	/*Black Wing Master's Hat*/
('1003761', '4700', 'Hat 4'),	/*Featherly Angel Hat*/
('1003760', '4900', 'Hat 4'),	/*Kitty Headphones*/
('1003790', '5000', 'Hat 4'),	/*Visor*/
('1003789', '6000', 'Hat 4'),	/*Zombie Hunter Hat*/
('1003779', '6400', 'Hat 4'),	/*White Rabbit Hat*/
('1003778', '5000', 'Hat 4'),	/*Fluffy Cat Hood*/
('1003777', '7100', 'Hat 4'),	/*Goth Cat Hood*/
('1003776', '4700', 'Hat 4'),	/*Harp Seal Mask*/
/*Page 13*/
('1003807', '3600', 'Hat 4'),	/*Heart Sunglasses*/
('1003804', '6000', 'Hat 4'),	/*Ducky Hat*/
('1003803', '4700', 'Hat 4'),	/*Purple Dinosaur Hat*/
('1003802', '5600', 'Hat 4'),	/*Green Dinosaur Hat*/
('1003792', '3800', 'Hat 4'),	/*Inkwell Hat*/
('1003820', '5000', 'Hat 4'),	/*Dark Hawkeye*/
('1003819', '4700', 'Hat 4'),	/*Dark Eckhart*/
('1003818', '6300', 'Hat 4'),	/*Dark Irena*/
('1003817', '6400', 'Hat 4'),	/*Dark Oz*/
/*Page 14*/
('1003816', '3800', 'Hat 4'),	/*Dark Mihile*/
('1003815', '3800', 'Hat 4'),	/*Hilla's Hairpin*/
('1003809', '6000', 'Hat 4'),	/*Mystic Black Silk Ribbon*/
('1003808', '3400', 'Hat 4'),	/*Mystic Black Silk Hat*/
('1003839', '4900', 'Hat 4'),	/*Goin' Nuclear Wig*/
('1003838', '3200', 'Hat 4'),	/*Wacky Olympus Wig*/
('1003837', '5200', 'Hat 4'),	/*Colorstream Wig*/
('1003836', '3400', 'Hat 4'),	/*Wild Spike Wig*/
('1003831', '7400', 'Hat 4'),	/*Ramling Hair Pin*/
/*Page 15*/
('1003830', '3400', 'Hat 4'),	/*Blue Love Bonnet*/
('1003829', '5600', 'Hat 4'),	/*Bunny Top Hat*/
('1003827', '3600', 'Hat 4'),	/*Miko Wig*/
('1003826', '5000', 'Hat 4'),	/*Samurai Hair-do*/
('1003825', '3200', 'Hat 4'),	/*The Bladed Falcon's Helm*/
('1003855', '5000', 'Hat 4'),	/*Leaf Hat*/
('1003853', '5600', 'Hat 4'),	/*Blavy Angel Wing*/
('1003852', '7600', 'Hat 4'),	/*Cute Shrimp Nigiri*/
('1003851', '7600', 'Hat 4'),	/*Tangy Fish Egg Nigiri*/
/*Page 16*/
('1003850', '8800', 'Hat 4'),	/*Chewy Octopus Nigiri*/
('1003849', '7600', 'Hat 4'),	/*Fresh Salmon Nigiri*/
('1003848', '3400', 'Hat 4'),	/*Crisp Egg Nigiri*/
('1003847', '8800', 'Hat 4'),	/*Slayer Wig*/
('1003846', '5200', 'Hat 4'),	/*Danjin Hat*/
('1003845', '4000', 'Hat 4'),	/*Lorna and Pan Hat*/
('1003844', '8800', 'Hat 4'),	/*Nao Hat*/
('1003843', '5600', 'Hat 4'),	/*Bizarre Fox Mask*/
('1003842', '3600', 'Hat 4'),	/*Succubus Hat*/
/*Page 17*/
('1003867', '6300', 'Hat 4'),	/*Nice Shot Visor*/
('1003865', '6400', 'Hat 4'),	/*Starlight Wings*/
('1003862', '3200', 'Hat 4'),	/*Teddy Ribbon*/
('1003861', '7400', 'Hat 4'),	/*Funky Mini Crown*/
('1003860', '4000', 'Hat 4'),	/*Seria Wig*/
('1003859', '3600', 'Hat 4'),	/*Iris Psyche*/
('1003884', '4900', 'Hat 4'),	/*Cute Wire Hair Band*/
('1003883', '7600', 'Hat 4'),	/*Blue Bow Beret*/
('1003882', '7400', 'Hat 4'),	/*Giant Bear Cap*/
/*Page 18*/
('1003881', '7600', 'Hat 4'),	/*Paper Boat Hat*/
('1003878', '7400', 'Hat 4'),	/*Pepe Hat*/
('1003877', '5200', 'Hat 4'),	/*Yeti Hat*/
('1003876', '8800', 'Hat 4'),	/*Lupin Hat*/
('1003875', '5000', 'Hat 4'),	/*Jr. Cellion Hat*/
('1003874', '7600', 'Hat 4'),	/*Blue Mossy Mom Hat*/
('1003873', '5600', 'Hat 4'),	/*Water Thief Hat*/
('1003903', '6000', 'Hat 4'),	/*Angelic Navy Cap*/
('1003902', '4700', 'Hat 4'),	/*Pretty Teddy*/
/*Page 19*/
('1003901', '7100', 'Hat 4'),	/*Courageous Bunny Hat*/
('1003900', '8800', 'Hat 4'),	/*Blue Heart Transparent Hat*/
('1003899', '6000', 'Hat 4'),	/*Pirate Captain's Hat*/
('1003897', '6300', 'Hat 4'),	/*Indian Chief Hat*/
('1003892', '5000', 'Hat 4'),	/*Leaf Diamond*/
('1003890', '7400', 'Hat 4'),	/*GM Sori's Fedora*/
('1003889', '5200', 'Hat 4'),	/*I'm Controlled!*/
('1003919', '8800', 'Hat 4'),	/*Plait-Knitted Hat*/
('1003918', '4700', 'Hat 4'),	/*Winged Cap*/
/*Page 20*/
('1003917', '3200', 'Hat 4'),	/*Pink Sunglasses Hat*/
('1003915', '3200', 'Hat 4'),	/*Pancake Hat*/
('1003914', '4900', 'Hat 4'),	/*Marine Tinia Shades*/
('1003913', '3600', 'Hat 4'),	/*Red Bow*/
('1003912', '6000', 'Hat 4'),	/*Puppy Ears*/
('1003910', '4700', 'Hat 4'),	/*Petite Diablo*/
('1003909', '4700', 'Hat 4'),	/*Pink Soda Cap*/
('1003907', '8800', 'Hat 4'),	/*Tenacious Zakum Helmet*/
('1003906', '6400', 'Hat 4'),	/*Triumphant Zakum Hat*/
/*Page 21*/
('1003905', '6400', 'Hat 4'),	/*Tenacious Ribbon Pig Hat*/
('1003904', '3800', 'Hat 4'),	/*Triumphant Ribbon Pig Hat*/
('1003935', '3200', 'Hat 4'),	/*Anima Ears*/
('1003934', '8800', 'Hat 4'),	/*Shadow Hood*/
('1003920', '3800', 'Hat 4'),	/*Hawaiian Sunhat*/
('1003951', '6300', 'Hat 4'),	/*Odette Tiara*/
('1003950', '7400', 'Hat 4'),	/*Plump Bear Hood*/
('1003949', '5600', 'Hat 4'),	/*フューチャーロイドヘッドセンサー*/
('1003948', '3400', 'Hat 4'),	/*フューチャーロイドヘッドセンサー*/
/*Page 22*/
('1003945', '7100', 'Hat 4'),	/*Superstar Crown*/
('1003944', '7400', 'Hat 4'),	/*Blue Polka Dot Bow*/
('1003943', '5000', 'Hat 4'),	/*Sleepy Turkey*/
('1003942', '5600', 'Hat 4'),	/*Blue Cheer*/
('1003941', '3400', 'Hat 4'),	/*Pink Cheer*/
('1003940', '6300', 'Hat 4'),	/*Curly Rabbit Poof*/
('1003937', '3800', 'Hat 4'),	/*Romantic Bamboo Hat*/
('1003936', '3600', 'Hat 4'),	/*Azalea Hair Pin*/
('1003967', '6300', 'Hat 4'),	/*Chocoram Doll Hat*/
/*Page 23*/
('1003966', '4900', 'Hat 4'),	/*Camellia Hairpin*/
('1003965', '6300', 'Hat 4'),	/*Chicken Hataroo*/
('1003964', '4900', 'Hat 4'),	/*Star Checkered Cap*/
('1003963', '5200', 'Hat 4'),	/*PSY Hat*/
('1003962', '4900', 'Hat 4'),	/*Checkered Bonnet*/
('1003958', '4700', 'Hat 4'),	/*Pink Mochi Ice*/
('1003957', '4700', 'Hat 4'),	/*Mint Mochi Ice*/
('1003955', '5600', 'Hat 4'),	/*Romance Rose*/
('1003954', '5000', 'Hat 4'),	/*Head Cooler*/
/*Page 24*/
('1003953', '4000', 'Hat 4'),	/*Rhinne Luster*/
('1003952', '6000', 'Hat 4'),	/*Odile Tiara*/
('1003975', '6300', 'Hat 4'),	/*Princess of Time Veil*/
('1003972', '6400', 'Hat 4'),	/*Powder Lace Band*/
('1003971', '4700', 'Hat 4'),	/*Powder Fedora*/
('1003968', '3600', 'Hat 4'),	/*Puffram Hat*/
('1003998', '7400', 'Hat 4'),	/*White Choco Bunny*/
('1004015', '6300', 'Hat 4'),	/*Freud's Face(M)*/
('1004014', '3600', 'Hat 4'),	/*Grab N' Pull*/
/*Page 25*/
('1004004', '5200', 'Hat 4'),	/*Grey Nero Hoodie*/
('1004003', '7600', 'Hat 4'),	/*Pink Nero Hoodie*/
('1004002', '7600', 'Hat 4'),	/*Shadow Hood*/
('1004001', '6400', 'Hat 4'),	/*Vampire Phantom Hat*/
('1004000', '6400', 'Hat 4'),	/*Dark Devil Hat*/
('1004029', '7100', 'Hat 4'),	/*Snow Bear Beanie*/
('1004028', '3600', 'Hat 4'),	/*Orange Cat Beanie*/
('1004027', '7600', 'Hat 4'),	/*Sky Blue Cat Beanie*/
('1004026', '6000', 'Hat 4'),	/*Black Cat Beanie*/
/*Page 26*/
('1004024', '4900', 'Hat 4'),	/*Cheese Hat*/
('1004018', '3400', 'Hat 4'),	/*Brave Aran's Helmet*/
('1004017', '5000', 'Hat 4'),	/*Aran's Helmet*/
('1004016', '5600', 'Hat 4'),	/*Freud's Face(F)*/
('1004047', '7100', 'Hat 4'),	/*Beast Tamer Animal Ears 8*/
('1004046', '6000', 'Hat 4'),	/*Beast Tamer Animal Ears 7*/
('1004045', '5000', 'Hat 4'),	/*Beast Tamer Animal Ears 6*/
('1004044', '3800', 'Hat 4'),	/*Bear Ears*/
('1004043', '3400', 'Hat 4'),	/*Puppy Ears*/
/*Page 27*/
('1004042', '6400', 'Hat 4'),	/*Deluxe Rabbit Ears*/
('1004041', '5000', 'Hat 4'),	/*Chipmunk Ears*/
('1004040', '3800', 'Hat 4'),	/*Red Panda Ears*/
('1004039', '3800', 'Hat 4'),	/*Eunwol Fox Ears*/
('1004038', '5600', 'Hat 4'),	/*Ice Hat*/
('1004036', '8800', 'Hat 4'),	/*Mr. K's Cat Hat*/
('1004035', '4900', 'Hat 4'),	/*Snake Snapback Hat*/
('1004034', '6400', 'Hat 4'),	/*Study Break*/

/*Page 28*/
('1004048', '5600', 'Hat 4'),	/*Rudi's Hat*/
('1004074', '7400', 'Hat 4'),	/*Year of Horse Hat (Blue)*/
('1004073', '3200', 'Hat 4'),	/*Year of Horse Hat (Peach)*/
('1004094', '3600', 'Hat 4'),	/*White Choco Bunny*/
('1004093', '5600', 'Hat 4'),	/*Yellow Knitted Beanie*/
('1004092', '6400', 'Hat 4'),	/*Cutie Horse Hat*/
('1004091', '7100', 'Hat 4'),	/*Deer Headband*/
('1004090', '7600', 'Hat 4'),	/*Explorer Cap*/

/*Page 29*/

('1004081', '6300', 'Hat 4'),	/*Dawn Bear Hoodie*/
('1004111', '6000', 'Hat 4'),	/*Red Ribbon Hairband*/



/*Page 1*/
('1004110', '4000', 'Hat 5'),	/*Blue Ribbon Hairband*/
('1004109', '4000', 'Hat 5'),	/*Transparent Hat*/
('1004108', '6300', 'Hat 5'),	/*Fancy Magician Hat*/
('1004106', '3600', 'Hat 5'),	/*Guardian Head Band*/
('1004099', '6000', 'Hat 5'),	/*Christmas Antlers*/
('1004126', '7600', 'Hat 5'),	/*Rainbow Hat*/
('1004125', '4900', 'Hat 5'),	/*Pineapple Hat*/
('1004124', '7100', 'Hat 5'),	/*Strawberry Headgear*/
('1004123', '7600', 'Hat 5'),	/*Contemporary Chic Hat*/
/*Page 2*/
('1004122', '5200', 'Hat 5'),	/*Chef Hat*/
('1004120', '3200', 'Hat 5'),	/*Strawberry Fairy*/
('1004117', '4700', 'Hat 5'),	/*Candy Candy*/
('1004113', '7600', 'Hat 5'),	/*Ghost Bride's Antique Wedding Veil*/
('1004143', '4900', 'Hat 5'),	/*Commander Magnus Mask*/
('1004142', '4000', 'Hat 5'),	/*Commander Lucid Mask*/
('1004141', '6000', 'Hat 5'),	/*Commander Damien Mask*/
('1004140', '5600', 'Hat 5'),	/*Commander Lotus Mask*/
('1004139', '8800', 'Hat 5'),	/*Pink Panda Hat*/
/*Page 3*/
('1004137', '6400', 'Hat 5'),	/*Rabbit and Bear Hat*/
('1004136', '4900', 'Hat 5'),	/*Nurse Cap*/
('1004158', '7100', 'Hat 5'),	/*LED Mouse Band*/
('1004157', '5000', 'Hat 5'),	/*Heart Headset*/
('1004156', '3200', 'Hat 5'),	/*Starry Earmuff*/
('1004148', '3400', 'Hat 5'),	/*Commander Hilla Mask*/
('1004147', '7100', 'Hat 5'),	/*Commander Will Mask*/
('1004146', '3200', 'Hat 5'),	/*Commander Orchid Mask*/
('1004145', '7100', 'Hat 5'),	/*Commander Arkarium Mask*/
/*Page 4*/
('1004144', '6400', 'Hat 5'),	/*Commander Von Leon Mask*/
('1004175', '5200', 'Hat 5'),	/*Angelic Melody*/
('1004171', '3600', 'Hat 5'),	/*Dancing Carousel*/
('1004170', '8800', 'Hat 5'),	/*Colorful Marble Parfait*/
('1004169', '5200', 'Hat 5'),	/*Fried Egg Head*/
('1004168', '5600', 'Hat 5'),	/*Cat Hat*/
('1004167', '6000', 'Hat 5'),	/*Dinosaur Snapback*/
('1004166', '3400', 'Hat 5'),	/*Black Butterfly Ribbon Headband*/
('1004165', '5000', 'Hat 5'),	/*Spring Rose*/
/*Page 5*/
('1004164', '7400', 'Hat 5'),	/*Targa Silk Hat*/
('1004191', '5200', 'Hat 5'),	/*粉红天使翅膀帽*/
('1004190', '4700', 'Hat 5'),	/*Island Travel Headphones*/
('1004181', '3600', 'Hat 5'),	/*Candy Party Ribbon Hairpin*/
('1004180', '3200', 'Hat 5'),	/*Disease Control STAR*/
('1004179', '4700', 'Hat 5'),	/*Red Elf Hat*/
('1004178', '6000', 'Hat 5'),	/*Slab*/
('1004177', '5200', 'Hat 5'),	/*Dark Cygnus*/
('1004176', '3400', 'Hat 5'),	/*Rabbit Mask*/
/*Page 6*/
('1004205', '4000', 'Hat 5'),	/*Red Pony Hat*/
('1004204', '4700', 'Hat 5'),	/*Blue Pony Hat*/
('1004203', '5200', 'Hat 5'),	/*Kitty Kitty Hat*/
('1004202', '5200', 'Hat 5'),	/*隐武士战盔*/
('1004201', '3800', 'Hat 5'),	/*暗夜精灵战盔*/
('1004200', '3200', 'Hat 5'),	/*Sweet Summer Cap*/
('1004199', '6400', 'Hat 5'),	/*Ayame's Hairpin*/
('1004198', '7100', 'Hat 5'),	/*태극 머리띠*/
('1004197', '6300', 'Hat 5'),	/*Stop It Mr. Shark*/
/*Page 7*/
('1004195', '5200', 'Hat 5'),	/*축구공 머리띠*/
('1004194', '4900', 'Hat 5'),	/*Prim Ribbon Beret*/
('1004193', '7600', 'Hat 5'),	/*Sparkling Goggles Cap*/
('1004192', '6000', 'Hat 5'),	/*Do-re-mi Headphone*/
('1004213', '3200', 'Hat 5'),	/*Hula Feather Decoration*/
('1004212', '5200', 'Hat 5'),	/*[[FROZEN CONTENT]] Frozen Dressy Ribbon*/
('1004211', '6400', 'Hat 5'),	/*Baby Earmuffs*/
('1004209', '5600', 'Hat 5'),	/*Peach Camellia Hairpin*/
('1004239', '5200', 'Hat 5'),	/*Peach Fairy*/
/*Page 8*/
('1004254', '5000', 'Hat 5'),	/*Master-o-Bingo Hat*/
('1004253', '7100', 'Hat 5'),	/*Old School Uniform Hat*/
('1004252', '6000', 'Hat 5'),	/*Dark Devil Horns*/
('1004251', '5000', 'Hat 5'),	/*Bright Angel's Halo*/
('1004250', '3400', 'Hat 5'),	/*Star Candy Popsicle*/
('1004269', '3200', 'Hat 5'),	/*Apple Stalk Puffy Hat*/
('1004268', '3800', 'Hat 5'),	/*Flower of Life*/
('1004285', '3200', 'Hat 5'),	/*Pink Mustache Cap*/
('1004284', '3200', 'Hat 5'),	/*Orange Mustache Cap*/
/*Page 9*/
('1004283', '5200', 'Hat 5'),	/*Aqua Mustache Cap*/
('1004282', '5600', 'Hat 5'),	/*Polka-Dot Red Ribbon*/
('1004281', '4900', 'Hat 5'),	/*Propeller Cap*/
('1004279', '6400', 'Hat 5'),	/*Squirrel Fedora*/
('1004276', '4900', 'Hat 5'),	/*Kemdi Mask*/
('1004275', '4900', 'Hat 5'),	/*Lucky Lucky Hat*/
('1004303', '4000', 'Hat 5'),	/*Slab*/
('1004302', '6400', 'Hat 5'),	/*Neville, the Legend*/
('1004301', '7100', 'Hat 5'),	/*Disease Control STAR*/
/*Page 10*/
('1004299', '3400', 'Hat 5'),	/*Brown Puppy Hat*/
('1004298', '7400', 'Hat 5'),	/*White Puppy Hat*/
('1004296', '8800', 'Hat 5'),	/*Lovey Chick Hat*/
('1004295', '3200', 'Hat 5'),	/*Singing Chick Hat*/
('1004294', '3800', 'Hat 5'),	/*Sweet Persimmon Hat*/
('1004319', '5200', 'Hat 5'),	/*Commander Orchid Mask*/
('1004318', '5200', 'Hat 5'),	/*Commander Arkarium Mask*/
('1004317', '4000', 'Hat 5'),	/*Commander Von Leon Mask*/
('1004316', '4000', 'Hat 5'),	/*Commander Magnus Mask*/
/*Page 11*/
('1004315', '6000', 'Hat 5'),	/*Commander Lucid Mask*/
('1004314', '4900', 'Hat 5'),	/*Commander Damien Mask*/
('1004313', '7100', 'Hat 5'),	/*Commander Lotus Mask*/
('1004312', '3600', 'Hat 5'),	/*Werebeast*/
('1004311', '7600', 'Hat 5'),	/*Old Hockey Mask*/
('1004310', '7400', 'Hat 5'),	/*Scarface Mask*/
('1004309', '4900', 'Hat 5'),	/*Slab*/
('1004308', '3600', 'Hat 5'),	/*Neville, the Legend*/
('1004307', '8800', 'Hat 5'),	/*Disease Control STAR*/
/*Page 12*/
('1004306', '4900', 'Hat 5'),	/*Werebeast*/
('1004305', '6400', 'Hat 5'),	/*Old Hockey Mask*/
('1004304', '6000', 'Hat 5'),	/*Scarface Mask*/
('1004332', '3800', 'Hat 5'),	/*Brown Puppy Hat*/
('1004329', '4900', 'Hat 5'),	/*Blue Baseball Cap*/
('1004328', '3800', 'Hat 5'),	/*Pink Baseball Cap*/
('1004327', '5000', 'Hat 5'),	/*Starry Headband*/
('1004326', '3200', 'Hat 5'),	/*Neville, the Legend*/
('1004325', '3800', 'Hat 5'),	/*Disease Control STAR*/
/*Page 13*/
('1004324', '4900', 'Hat 5'),	/*Gas Mask*/
('1004323', '4700', 'Hat 5'),	/*Slab*/
('1004322', '3800', 'Hat 5'),	/*Rose Hat*/
('1004321', '7600', 'Hat 5'),	/*Commander Hilla Mask*/
('1004320', '3800', 'Hat 5'),	/*Commander Will Mask*/
('1004343', '5600', 'Hat 5'),	/*Skull Hat*/
('1004342', '4700', 'Hat 5'),	/*Witch Hat*/
('1004341', '3400', 'Hat 5'),	/*Messy Wig*/
('1004340', '3400', 'Hat 5'),	/*Worn Skull Hat*/
/*Page 14*/
('1004339', '7400', 'Hat 5'),	/*Worn Witch Hat*/
('1004338', '4700', 'Hat 5'),	/*Worn Messy Wig*/
('1004337', '6400', 'Hat 5'),	/*Ill Orchid Wig*/
('1004336', '3800', 'Hat 5'),	/*Raging Lotus Wig*/
('1004399', '3400', 'Hat 5'),	/*Sitting Mikasa*/
('1004398', '4700', 'Hat 5'),	/*Sitting Eren*/
('1004397', '4000', 'Hat 5'),	/*Cleaning Bandanna*/
('1004396', '4700', 'Hat 5'),	/*Levi Face*/
('1004395', '3800', 'Hat 5'),	/*Armin Face*/
/*Page 15*/
('1004394', '6400', 'Hat 5'),	/*Mikasa Face*/
('1004393', '7100', 'Hat 5'),	/*Eren Face*/
('1004386', '3600', 'Hat 5'),	/*Reindeer Fawn Hat*/
('1004385', '6000', 'Hat 5'),	/*Pumpkin Cake Hat*/
('1004384', '3600', 'Hat 5'),	/*Dinofrog Hat*/
('1004414', '3600', 'Hat 5'),	/*Warm Bao*/
('1004413', '4700', 'Hat 5'),	/*Red Rudolph Horns*/
('1004411', '7400', 'Hat 5'),	/*Whipping Strawberry*/
('1004408', '3800', 'Hat 5'),	/*Icy Hat*/
/*Page 16*/
('1004407', '7600', 'Hat 5'),	/*Ear Muffs and Pom Pom Beanie*/
('1004406', '7600', 'Hat 5'),	/*Humanity's Strongest Face*/
('1004405', '3400', 'Hat 5'),	/*Rawrin' Tiger Hat*/
('1004403', '7600', 'Hat 5'),	/*Cutie Bunny Hat*/
('1004402', '7600', 'Hat 5'),	/*Sitting Colossal Titan*/
('1004401', '7400', 'Hat 5'),	/*Sitting Levi*/
('1004400', '6400', 'Hat 5'),	/*Sitting Armin*/
('1004431', '3200', 'Hat 5'),	/*GS25 Jeonju Bibimbap*/
('1004430', '3800', 'Hat 5'),	/*GS25 Hot Fire Chicken*/
/*Page 17*/
('1004429', '4700', 'Hat 5'),	/*GS25 Tuna Mayo*/
('1004428', '6400', 'Hat 5'),	/*Blue Bird Hat*/
('1004419', '5000', 'Hat 5'),	/*Aether Snow*/
('1004418', '4000', 'Hat 5'),	/*Unleashed Snow*/
('1004417', '7400', 'Hat 5'),	/*Pinnacle Snow*/
('1004416', '8800', 'Hat 5'),	/*Cutie Birk Hat*/
('1004447', '6000', 'Hat 5'),	/*Friendly Hat*/
('1004446', '3400', 'Hat 5'),	/*Loyal Hat*/
('1004445', '5200', 'Hat 5'),	/*Aurora Hat*/
/*Page 18*/
('1004443', '3200', 'Hat 5'),	/*Snowman Mask*/
('1004442', '4000', 'Hat 5'),	/*Loyal Hat*/
('1004441', '3400', 'Hat 5'),	/*Friendly Hat*/
('1004440', '7100', 'Hat 5'),	/*Zodiac Ram Cake*/
('1004439', '6300', 'Hat 5'),	/*Silver Wolf Ears*/
('1004438', '8800', 'Hat 5'),	/*Fluffy Ram Earmuff*/
('1004434', '7100', 'Hat 5'),	/*GS25 Jeonju Bibimbap*/
('1004433', '6300', 'Hat 5'),	/*GS25 Hot Fire Chicken*/
('1004432', '8800', 'Hat 5'),	/*GS25 Tuna Mayo*/
/*Page 19*/
('1004463', '8800', 'Hat 5'),	/*Star Planet Mascot Hat*/
('1004462', '3400', 'Hat 5'),	/*Pink Ram Horn Hat*/
('1004461', '6000', 'Hat 5'),	/*Blue Ram Horn Hat*/
('1004460', '7400', 'Hat 5'),	/*네네 오리엔탈파닭*/
('1004459', '6000', 'Hat 5'),	/*네네 쇼킹핫양념치킨*/
('1004458', '6400', 'Hat 5'),	/*네네 스노윙 치킨*/
('1004456', '3400', 'Hat 5'),	/*Lovely Princess Bonnet*/
('1004455', '7400', 'Hat 5'),	/*Cottontail Rabbit Hat*/
('1004454', '8800', 'Hat 5'),	/*Snow Raccoon Hat*/
/*Page 20*/
('1004453', '7600', 'Hat 5'),	/*Snow Bunny Beret*/
('1004450', '7100', 'Hat 5'),	/*Cross Wing Hair Pin*/
('1004448', '4900', 'Hat 5'),	/*Happy Mouse Hat*/
('1004479', '6300', 'Hat 5'),	/*Hoi Poi Hat*/
('1004478', '8800', 'Hat 5'),	/*BOY Hat*/
('1004472', '6000', 'Hat 5'),	/*Devil Wolf Seduction*/
('1004471', '7400', 'Hat 5'),	/*Crystal Cat Ribbon*/
('1004470', '7600', 'Hat 5'),	/*Fluffy Trapper Hat*/
('1004469', '7100', 'Hat 5'),	/*Love Message*/
/*Page 21*/
('1004468', '3600', 'Hat 5'),	/*Bubblecone Hat*/
('1004467', '4700', 'Hat 5'),	/*Giant Floppy Heart Hat*/
('1004490', '6000', 'Hat 5'),	/*Spike Headphone*/
('1004489', '5200', 'Hat 5'),	/*Skull Hairpin*/
('1004488', '3400', 'Hat 5'),	/*Healing Ribbon*/
('1004487', '5000', 'Hat 5'),	/*Starlight Hoodie*/
('1004486', '4900', 'Hat 5'),	/*Spring Crown*/
('1004483', '6300', 'Hat 5'),	/*Akatsuki's Hair-Tie*/
('1004482', '8800', 'Hat 5'),	/*Akarin's Butterfly Hairpin*/
/*Page 22*/
('1004480', '3200', 'Hat 5'),	/*Naughty Boy Hat*/
('1004511', '4000', 'Hat 5'),	/*Orange Mushroom Cap Hat*/
('1004510', '4900', 'Hat 5'),	/*Bold Slime Hat*/
('1004508', '3400', 'Hat 5'),	/*The Kingdom Crown of Queen*/
('1004506', '5600', 'Hat 5'),	/*Cottontail Rabbit Hairband*/
('1004505', '6000', 'Hat 5'),	/*Pink Blossom Ribbon*/
('1004504', '6400', 'Hat 5'),	/*Noble Blossom Casquette*/
('1004503', '7600', 'Hat 5'),	/*Cat Hood*/
('1004502', '4000', 'Hat 5'),	/*Moonbeam Fox Ears*/
/*Page 23*/
('1004501', '3600', 'Hat 5'),	/*The Kindom Crown of King*/
('1004500', '3200', 'Hat 5'),	/*Puffy Red Carp Hat*/
('1004499', '6300', 'Hat 5'),	/*Puffy Blue Carp Hat*/
('1004527', '5200', 'Hat 5'),	/*Soaring Goggles*/
('1004526', '4700', 'Hat 5'),	/*(Boiling)*/
('1004525', '3800', 'Hat 5'),	/*Hair Roll*/
('1004524', '7400', 'Hat 5'),	/*한입 덥썩 돼지바*/
('1004515', '5000', 'Hat 5'),	/*Candy Party Ribbon Hairpin*/
('1004513', '4900', 'Hat 5'),	/*Clingy Pepe Hat*/
/*Page 24*/
('1004512', '3200', 'Hat 5'),	/*Happy Pink Bean Hat*/
('1004543', '5600', 'Hat 5'),	/*Polka-Dot Ribbon*/
('1004541', '8800', 'Hat 5'),	/*Tea Party Ribbon*/
('1004540', '5200', 'Hat 5'),	/*Evening Orchid Hoodie*/
('1004539', '7400', 'Hat 5'),	/*Tenacious Ribbon Pig Hat*/
('1004538', '6400', 'Hat 5'),	/*Triumphant Ribbon Pig Hat*/
('1004537', '5200', 'Hat 5'),	/*Tenacious Zakum Helmet*/
('1004536', '5000', 'Hat 5'),	/*Triumphant Zakum Hat*/
('1004535', '7600', 'Hat 5'),	/*Schwarzer Beret*/
/*Page 25*/
('1004534', '6000', 'Hat 5'),	/*Modern Farm Straw Hat*/
('1004533', '3200', 'Hat 5'),	/*Gaming Moonbeam*/
('1004532', '7400', 'Hat 5'),	/*The Empress is Watching*/
('1004530', '3800', 'Hat 5'),	/*Blue Panda Doll Hat*/
('1004528', '7600', 'Hat 5'),	/*Silver Lotus Wig*/
('1004559', '8800', 'Hat 5'),	/*Beginner Chef Hat*/
('1004558', '4000', 'Hat 5'),	/*Hoya Hat*/
('1004557', '6400', 'Hat 5'),	/*Twinkling Star Helmet*/
('1004548', '6300', 'Hat 5'),	/*Crown Hat Tiger*/
/*Page 26*/
('1004547', '7400', 'Hat 5'),	/*Red Ribbon Panda Hat*/
('1004546', '4000', 'Hat 5'),	/*Gentleman Bunny Hat*/
('1004545', '4700', 'Hat 5'),	/*Pink Ribbon Sheep Hat*/
('1004544', '6000', 'Hat 5'),	/*Fedora Hat Cat*/
('1004575', '3400', 'Hat 5'),	/*Romantic Rose*/
('1004574', '5600', 'Hat 5'),	/*Reboot Hat*/
('1004571', '7600', 'Hat 5'),	/*Black Sailor Ribbon Hat*/
('1004570', '7100', 'Hat 5'),	/*Black Sailor Hat*/
('1004569', '6000', 'Hat 5'),	/*Rainbow Flower Pin*/
/*Page 27*/
('1004568', '3800', 'Hat 5'),	/*Mousy Bunny Hat*/
('1004566', '4700', 'Hat 5'),	/*Strawberry Shaved Ice Hat*/
('1004565', '6400', 'Hat 5'),	/*Mango Shaved Ice Hat*/
('1004564', '7600', 'Hat 5'),	/*Melon Shaved Ice Hat*/
('1004563', '5000', 'Hat 5'),	/*Chef Hat*/
('1004562', '3800', 'Hat 5'),	/*Sous-Chef Hat*/
('1004561', '7600', 'Hat 5'),	/*Advanced Chef Hat*/
('1004560', '5000', 'Hat 5'),	/*Intermediate Chef Hat*/
('1004591', '3800', 'Hat 5'),	/*White Time*/
/*Page 28*/
('1004590', '7600', 'Hat 5'),	/*Blueberry Jewel Pin*/
('1004589', '6000', 'Hat 5'),	/*Jay's Sterilized Kitty Eye Patch*/
('1004581', '7600', 'Hat 5'),	/*Dango Dango Hat*/
('1004580', '6300', 'Hat 5'),	/*Yeonhwa School Sapphire Ornament*/
('1004578', '6000', 'Hat 5'),	/*Royal Crown*/
('1004577', '3600', 'Hat 5'),	/*Pink Soda Cap*/
('1004576', '6300', 'Hat 5'),	/*LED Mouse Band*/
('1004603', '4700', 'Hat 5'),	/*Star-Spangled Banner Hat*/
('1004602', '3800', 'Hat 5'),	/*Farmer's Treasure*/
/*Page 29*/
('1004601', '6400', 'Hat 5'),	/*Baby Penguin Hat*/
('1004600', '6400', 'Hat 5'),	/*British Marine Hat*/
('1004599', '3800', 'Hat 5'),	/*Black Ursus Hat*/



/*Page 1*/
('1004598', '3200', 'Hat 6'),	/*Brown Ursus Hat*/
('1004597', '6400', 'Hat 6'),	/*White Ursus Hat*/
('1004592', '3200', 'Hat 6'),	/*Black Time*/
('1004620', '5600', 'Hat 6'),	/*Chestnut Rice Cake Hat*/
('1004619', '7100', 'Hat 6'),	/*Bean Rice Cake Hat*/
('1004618', '8800', 'Hat 6'),	/*Honey Rice Cake Hat*/
('1004614', '4900', 'Hat 6'),	/*Salmon Bowl Hat*/
('1004613', '3600', 'Hat 6'),	/*Pork Bowl Hat*/
('1004612', '6000', 'Hat 6'),	/*Eel Bowl Hat*/
/*Page 2*/
('1004610', '6400', 'Hat 6'),	/*Flower Butterfly*/
('1004609', '4700', 'Hat 6'),	/*Head Sakura*/
('1004639', '4900', 'Hat 6'),	/*Arctic Hood*/
('1004638', '3600', 'Hat 6'),	/*Mr. Orlov Hat*/
('1004636', '8800', 'Hat 6'),	/*Banana Outing Hat*/
('1004635', '7600', 'Hat 6'),	/*Festive Gumball*/
('1004634', '7100', 'Hat 6'),	/*Midnight Black Cat*/
('1004633', '3400', 'Hat 6'),	/*Ghost Fedora*/

/*Page 3*/



('1004643', '6400', 'Hat 6'),	/*Blue Marine Cap*/
('1004642', '6000', 'Hat 6'),	/*Shining Light*/
('1004641', '6400', 'Hat 6'),	/*Fairy's Flower Bud*/
('1004640', '6300', 'Hat 6'),	/*Block Party Cap*/
('1004671', '6000', 'Hat 6'),	/*Modern Farm Straw Hat*/
('1004665', '3200', 'Hat 6'),	/*Warm Rabbit Fur Hat*/
/*Page 4*/
('1004662', '3200', 'Hat 6'),	/*Monkey Mochi Hat*/
('1004661', '4900', 'Hat 6'),	/*Fluffy Fox Ears (Silver)*/
('1004660', '3200', 'Hat 6'),	/*Fluffy Fox Ears (Gold)*/
('1004659', '5200', 'Hat 6'),	/*Polar Bear Hood*/
('1004687', '4700', 'Hat 6'),	/*Secret Shade*/
('1004686', '5000', 'Hat 6'),	/*Secret Shade*/
('1004685', '3800', 'Hat 6'),	/*Split Luminous*/
('1004684', '7100', 'Hat 6'),	/*Split Luminous*/
('1004683', '3600', 'Hat 6'),	/*Winter Aran*/
/*Page 5*/
('1004682', '3800', 'Hat 6'),	/*Winter Aran*/
('1004681', '5200', 'Hat 6'),	/*Mystic Phantom*/
('1004680', '6400', 'Hat 6'),	/*Mystic Phantom*/
('1004679', '3600', 'Hat 6'),	/*Royal Mercedes*/
('1004678', '8800', 'Hat 6'),	/*Royal Mercedes*/
('1004677', '5600', 'Hat 6'),	/*Evan Golden Wings*/
('1004676', '6400', 'Hat 6'),	/*Evan Golden Wings*/
('1004673', '5200', 'Hat 6'),	/*Time Mistress Hat*/
('1004672', '7400', 'Hat 6'),	/*Time Master Hat*/
/*Page 6*/
('1004703', '4900', 'Hat 6'),	/*Damien Snapback*/
('1004702', '8800', 'Hat 6'),	/*Fairy Knit Hat*/
('1004701', '6000', 'Hat 6'),	/*Winter Deer*/
('1004700', '8800', 'Hat 6'),	/*Beaky Owl Mask*/
('1004698', '6000', 'Hat 6'),	/*Black Time*/
('1004697', '3200', 'Hat 6'),	/*White Time*/
('1004694', '3400', 'Hat 6'),	/*Reindeer Hat*/
('1004693', '5000', 'Hat 6'),	/*Santa Hat*/
('1004692', '4700', 'Hat 6'),	/*Kid Snowman*/
/*Page 7*/
('1004691', '3400', 'Hat 6'),	/*Fantastic Blue Rose*/
('1004690', '7600', 'Hat 6'),	/*Facewashing Band*/
('1004689', '6000', 'Hat 6'),	/*Rolled Towel*/
('1004688', '7400', 'Hat 6'),	/*Slumbering Dragon Snapback*/
('1004718', '5000', 'Hat 6'),	/*Eckhart Doll Hat*/
('1004717', '7100', 'Hat 6'),	/*Baby Binkie Bonnet*/
('1004716', '6300', 'Hat 6'),	/*Concert Muse Tiara*/
('1004714', '7100', 'Hat 6'),	/*Black Mage Snapback*/
('1004713', '7400', 'Hat 6'),	/*Kurama Ear Accessory*/
/*Page 8*/
('1004712', '3600', 'Hat 6'),	/*Smile Seed Hat*/
('1004711', '7400', 'Hat 6'),	/*Oz Doll Hat*/
('1004708', '3400', 'Hat 6'),	/*Cozy Fur Hat */
('1004706', '7400', 'Hat 6'),	/*Winter Garden Hat*/
('1004734', '7400', 'Hat 6'),	/*Melon Shaved Ice Hat*/
('1004733', '6300', 'Hat 6'),	/*Gaming Moonbeam*/
('1004732', '5000', 'Hat 6'),	/*Calico Head Cat*/
('1004731', '8800', 'Hat 6'),	/*Adorable Gold Nyanya*/
('1004730', '3400', 'Hat 6'),	/*Hungry Moon Bunny*/
/*Page 9*/
('1004729', '3400', 'Hat 6'),	/*Wee Moon Bunny's Rice Drop Soup*/
('1004728', '3800', 'Hat 6'),	/*Lady Moon Bunny's Rice Drop Soup*/
('1004727', '4900', 'Hat 6'),	/*Shade's Game of Yut*/
('1004726', '7600', 'Hat 6'),	/*Moonbeam's Game of Yut*/
('1004725', '4000', 'Hat 6'),	/*Pink Bean Likes Meat*/
('1004724', '5600', 'Hat 6'),	/*Bright New Year Hat*/
('1004723', '3400', 'Hat 6'),	/*Transcendence Stone Snapback*/
('1004722', '7600', 'Hat 6'),	/*Moon Dancer's Bandana*/
('1004721', '4900', 'Hat 6'),	/*Flower Dancer's Butterfly Pin*/
/*Page 10*/
('1004720', '5200', 'Hat 6'),	/*Umbral Cap*/
('1004751', '5000', 'Hat 6'),	/*Windswept Rainbow Wig*/
('1004750', '5000', 'Hat 6'),	/*Casentino Rainbow Wig*/

('1004748', '8800', 'Hat 6'),	/*Couples Army Helm*/
('1004747', '4700', 'Hat 6'),	/*Singles Army Helm*/
('1004739', '3600', 'Hat 6'),	/*Rainbow Clover*/
('1004738', '7400', 'Hat 6'),	/*Baby Ghost Hat*/
('1004737', '6000', 'Hat 6'),	/*Black Ribbon Hairpin*/
/*Page 11*/
('1004763', '7600', 'Hat 6'),	/*Star Crusher Cap*/
('1004762', '6400', 'Hat 6'),	/*Cunning Sweet Pig Hat*/
('1004761', '3800', 'Hat 6'),	/*Mischievous Sweet Pig Hat*/
('1004760', '6300', 'Hat 6'),	/*Monster Kindergarten Hat*/
('1004759', '3600', 'Hat 6'),	/*Red Flame Phoenix Plume*/
('1004758', '3400', 'Hat 6'),	/*Blue Flame Phoenix Plume*/
('1004757', '7100', 'Hat 6'),	/*Cat in a Hat*/
('1004756', '3400', 'Hat 6'),	/*Shark Hoodie*/
('1004754', '4900', 'Hat 6'),	/*Fishtail Rainbow Wig*/
/*Page 12*/
('1004753', '7100', 'Hat 6'),	/*Front Ponytail Rainbow Wig*/
('1004752', '8800', 'Hat 6'),	/*Rainbow Spore Wig*/
('1004779', '6000', 'Hat 6'),	/*Flower Garden Morning*/
('1004778', '4900', 'Hat 6'),	/*Noble Maple Crown*/
('1004777', '7400', 'Hat 6'),	/*Flower Garden Morning*/
('1004776', '6400', 'Hat 6'),	/*Spring Scene Rain Hat*/
('1004775', '3400', 'Hat 6'),	/*Lace Berry*/
('1004774', '6400', 'Hat 6'),	/*Bunny Bon Bon*/
('1004799', '6300', 'Hat 6'),	/*Carrot Rabbit Hairpin*/
/*Page 13*/
('1004798', '3800', 'Hat 6'),	/*Scout Ribbon Beret*/
('1004797', '5200', 'Hat 6'),	/*Decorated Scout Beret*/
('1004796', '6000', 'Hat 6'),	/*Racing Elephant Hat*/
('1004795', '3200', 'Hat 6'),	/*Rose Blossom*/
('1004794', '4000', 'Hat 6'),	/*Rose Bud*/
('1004793', '4900', 'Hat 6'),	/*Lalala Earphones*/
('1004792', '3800', 'Hat 6'),	/*Hydrangea Lace Hairband*/
('1004791', '3800', 'Hat 6'),	/*Hydrangea Hair Pin*/
('1004790', '3400', 'Hat 6'),	/*Detective Hat*/
/*Page 14*/
('1004789', '3600', 'Hat 6'),	/*Chicky Suds Hat*/
('1004788', '5200', 'Hat 6'),	/*Bubble Leaf Hat*/
('1004787', '5000', 'Hat 6'),	/*Chicken Cutie Hat*/
('1004815', '3800', 'Hat 6'),	/*Hearty Heart Antenna*/
('1004814', '7600', 'Hat 6'),	/*Kitty Hoodie Bandana*/
('1004813', '3200', 'Hat 6'),	/*Dragon Emperor's Helm*/
('1004807', '5600', 'Hat 6'),	/*Starfish and Clam*/
('1004806', '5000', 'Hat 6'),	/*Villain's Mask (Hat)*/
('1004805', '3200', 'Hat 6'),	/*Sky-blue Straw Hat*/
/*Page 15*/
('1004804', '4000', 'Hat 6'),	/*Blaster Hat*/
('1004803', '3600', 'Hat 6'),	/*Blaster Hat*/
('1004802', '4900', 'Hat 6'),	/*Strawberry Headphone Hat*/
('1004801', '7100', 'Hat 6'),	/*Banana Headphone Hat*/
('1004800', '3200', 'Hat 6'),	/*Watermelon Headphone Hat*/
('1004831', '7600', 'Hat 6'),	/*Moon Bunny Bell Wig (F)*/
('1004830', '8800', 'Hat 6'),	/*Moon Bunny Bell Wig (M)*/
('1004829', '6000', 'Hat 6'),	/*Red Beret (M)*/
('1004828', '6300', 'Hat 6'),	/*Green Beret (F)*/
/*Page 16*/
('1004827', '5000', 'Hat 6'),	/*Kamaitachi Hat*/
('1004826', '5600', 'Hat 6'),	/*Straw Cat Hat*/
('1004825', '4000', 'Hat 6'),	/*Time-Traveling Anniversary Headband*/
('1004824', '6000', 'Hat 6'),	/*Well-Liked Barrette*/

('1004821', '8800', 'Hat 6'),	/*Wafer Fedora*/
('1004820', '7600', 'Hat 6'),	/*Goggled Watermelon Hat*/
('1004819', '3400', 'Hat 6'),	/*Watermelon Hat*/
('1004818', '3800', 'Hat 6'),	/*Queen's Ice Cream*/
/*Page 17*/
('1004817', '5600', 'Hat 6'),	/*Round Wafer Ears*/
('1004816', '7600', 'Hat 6'),	/*Starry Night Orchid*/
('1004847', '3400', 'Hat 6'),	/*Fairy Dew Cap*/
('1004846', '6400', 'Hat 6'),	/*Midnight Bloom*/
('1004845', '3800', 'Hat 6'),	/*Midnight Legacy*/
('1004844', '4000', 'Hat 6'),	/*Poofy Bichon Hat*/
('1004843', '7400', 'Hat 6'),	/*Pumpkin-Colored Witch Hat*/
('1004842', '6400', 'Hat 6'),	/*Jack-o'-lantern Hat*/
('1004841', '6400', 'Hat 6'),	/*Ghost Hat*/
/*Page 18*/
('1004840', '4700', 'Hat 6'),	/*Floral Veil*/
('1004839', '5200', 'Hat 6'),	/*Diamond Veil*/
('1004835', '3200', 'Hat 6'),	/*Chained Princess Ribbon*/
('1004834', '6000', 'Hat 6'),	/*Dark Musician Headphones*/
('1004833', '6000', 'Hat 6'),	/*Rudi's Hat*/
('1004832', '4700', 'Hat 6'),	/*White Combat Veil*/
('1004863', '3800', 'Hat 6'),	/*Winter Bunny Hat (Pink)*/
('1004862', '5200', 'Hat 6'),	/*Winter Bunny Hat (Teal)*/
('1004861', '7100', 'Hat 6'),	/*Vampire Phantom Mask*/
/*Page 19*/
('1004860', '4700', 'Hat 6'),	/*Elsa's Flower Accessory*/
('1004859', '7600', 'Hat 6'),	/*Felt's Ribbon*/
('1004858', '3800', 'Hat 6'),	/*Puck's Hat*/
('1004857', '5600', 'Hat 6'),	/*Shadow Warrior's Veil*/
('1004856', '6400', 'Hat 6'),	/*소생의 니은*/
('1004855', '7600', 'Hat 6'),	/*Orange Mushroom Beret*/
('1004854', '5600', 'Hat 6'),	/*Devil Bear Cap*/
('1004853', '6000', 'Hat 6'),	/*Flutter Flower Panama*/
('1004852', '5000', 'Hat 6'),	/*Sweetheart Hat*/
/*Page 20*/
('1004851', '7600', 'Hat 6'),	/*Japanese Veil*/
('1004850', '3200', 'Hat 6'),	/*Lucid's Silk Hat*/
('1004849', '4700', 'Hat 6'),	/*Cozy Penguin Hood*/
('1004848', '4000', 'Hat 6'),	/*Sweet Pengyin Hood*/
('1004879', '8800', 'Hat 6'),	/*Festive Lovers Hat (M)*/
('1004878', '3400', 'Hat 6'),	/*Cat Monster Hat*/
('1004877', '3200', 'Hat 6'),	/*Love Bell Hat*/
('1004876', '6000', 'Hat 6'),	/*Broken Egg Hat*/
('1004875', '6400', 'Hat 6'),	/*Halloween Cat-O-Lantern Mask*/
/*Page 21*/
('1004874', '4700', 'Hat 6'),	/*Blushing Reindeer Hat*/
('1004873', '7100', 'Hat 6'),	/*Pumpkin Planet*/
('1004872', '6400', 'Hat 6'),	/*Alicorn Hat*/
('1004871', '4000', 'Hat 6'),	/*Purple Porker Cap*/
('1004870', '3200', 'Hat 6'),	/*Hallowkitty's Witch Hat*/
('1004869', '5200', 'Hat 6'),	/*Halloween Mummy Mask*/
('1004868', '5600', 'Hat 6'),	/*Halloween Werewolf Mask*/
('1004867', '6300', 'Hat 6'),	/*Halloween Skull Mask*/
('1004866', '5000', 'Hat 6'),	/*Halloween Frankenstein Mask*/
/*Page 22*/
('1004865', '6000', 'Hat 6'),	/*Halloween Dracula Mask*/
('1004895', '6000', 'Hat 6'),	/*Star's Song*/
('1004894', '5000', 'Hat 6'),	/*Star's Melody*/
('1004893', '6000', 'Hat 6'),	/*Maple 5000-Day Hat*/

('1004891', '3200', 'Hat 6'),	/*Glorious Red Bean Treat Hat*/
('1004890', '7100', 'Hat 6'),	/*White Night Horns*/
('1004889', '3400', 'Hat 6'),	/*Chicken Mochi Hat*/

/*Page 23*/
('1004887', '6000', 'Hat 6'),	/*Nutcracker Hat*/
('1004885', '6300', 'Hat 6'),	/*Big Expedition Hat*/
('1004884', '5200', 'Hat 6'),	/*Idol Ribbon Snapback*/
('1004883', '7400', 'Hat 6'),	/*Fried Chicken God's Angel*/
('1004882', '3200', 'Hat 6'),	/*Sweet Lace Ears*/
('1004881', '5000', 'Hat 6'),	/*Sweet Wiggly Ears*/
('1004880', '7600', 'Hat 6'),	/*Festive Lovers Hat (F)*/
('1004911', '6300', 'Hat 6'),	/*Sweet Fresh Cream Cake Hat*/
('1004910', '3400', 'Hat 6'),	/*Sweet Chocolate Cake Hat*/
/*Page 24*/
('1004909', '7400', 'Hat 6'),	/*Polar Explorer Winter Hat*/
('1004900', '8800', 'Hat 6'),	/*Snow Queen*/
('1004899', '7400', 'Hat 6'),	/*Snow King*/
('1004898', '6000', 'Hat 6'),	/*Snow Queen*/
('1004897', '4700', 'Hat 6'),	/*Snow King*/
('1004927', '8800', 'Hat 6'),	/*Blue Penguin Winter Cap*/
('1004926', '5600', 'Hat 6'),	/*Pompom Knit Hat*/
('1004925', '5600', 'Hat 6'),	/*Rocket Hat*/
('1004924', '6400', 'Hat 6'),	/*Teddy Earmuffs*/
/*Page 25*/
('1004923', '5000', 'Hat 6'),	/*Moonlight Sticky Cake*/
('1004922', '3400', 'Hat 6'),	/*Strawberry Latte Hat*/
('1004921', '8800', 'Hat 6'),	/*Caramel Latte Hat*/
('1004920', '3600', 'Hat 6'),	/*Green Tea Latte Hat*/
('1004919', '4700', 'Hat 6'),	/*Manji's Bamboo Hat*/
('1004916', '3600', 'Hat 6'),	/*Nova Enchanter Hat*/
('1004912', '6300', 'Hat 6'),	/*Yellow Chick Hat*/
('1004942', '3400', 'Hat 6'),	/*Huge Flower Hat*/
('1004941', '3800', 'Hat 6'),	/*Wildflower Mini Flower Pin*/
/*Page 26*/
('1004940', '3400', 'Hat 6'),	/*Mini Mini Flower Pin*/
('1004939', '6000', 'Hat 6'),	/*Bell Mini Flower Pin*/
('1004938', '3400', 'Hat 6'),	/*Braided Mini Flower Pin*/
('1004937', '6300', 'Hat 6'),	/*Rose Mini Flower Pin*/
('1004936', '6400', 'Hat 6'),	/*Ladybug Hat*/
('1004935', '4000', 'Hat 6'),	/*White M-Forcer Helmet*/
('1004934', '7100', 'Hat 6'),	/*Strawberry Hairpin*/
('1004933', '3600', 'Hat 6'),	/*Cape Beret (F)*/
('1004932', '7400', 'Hat 6'),	/*Cape Beret (M)*/
/*Page 27*/
('1004931', '4000', 'Hat 6'),	/*Spring Fairy Fur Hat*/
('1004929', '6300', 'Hat 6'),	/*Hand-knitted Chicken*/
('1004928', '3400', 'Hat 6'),	/*Pink Penguin Winter Cap*/
('1004959', '3400', 'Hat 6'),	/*Napoleonic Hat*/
('1004958', '7100', 'Hat 6'),	/*Elizabethan Hat*/
('1004957', '7400', 'Hat 6'),	/*Sprinkle Sprout*/
('1004956', '3200', 'Hat 6'),	/*Froggy Rainhat*/
('1004955', '3400', 'Hat 6'),	/*Cherry Bomb*/
('1004954', '7400', 'Hat 6'),	/*Cherry on Top*/
/*Page 28*/
('1004953', '4700', 'Hat 6'),	/*Deerstalker Cap*/
('1004952', '8800', 'Hat 6'),	/*Foxy Teacher Hat*/
('1004951', '7600', 'Hat 6'),	/*Pop Star Hat (M)*/
('1004950', '6000', 'Hat 6'),	/*Pop Star Hat (F)*/
('1004949', '3200', 'Hat 6'),	/*Starlit Dreams Cowl*/
('1004948', '7100', 'Hat 6'),	/*Silky Skater Band*/
('1004947', '4700', 'Hat 6'),	/*Smiley Skater Lid*/
('1004946', '7400', 'Hat 6'),	/*Silver Flower Child Hat*/
('1004945', '4900', 'Hat 6'),	/*Pandora Hat*/
/*Page 29*/
('1004975', '5200', 'Hat 6'),	/*Preppy Sprout Hat*/
('1004974', '6000', 'Hat 6'),	/*Maple Galaxy Helmet*/
('1004973', '7100', 'Hat 6'),	/*Steely Meow Helmet*/



/*Page 1*/
('1004972', '5600', 'Hat 7'),	/*Steely Blue Kitty Ears*/
('1004971', '6400', 'Hat 7'),	/*Steely Pink Kitty Ears*/
('1004970', '7600', 'Hat 7'),	/*Steely Pink Bunny Ears*/
('1004969', '7400', 'Hat 7'),	/*Pink Elephant Hat*/
('1004968', '5200', 'Hat 7'),	/*Mallow Fluff Cloud Hat*/
('1004967', '3600', 'Hat 7'),	/*Mustachioed Ballcap*/
('1004966', '4900', 'Hat 7'),	/*Mustachioed Bowler*/
('1004965', '8800', 'Hat 7'),	/*Apricot Bloom*/
('1004964', '4700', 'Hat 7'),	/*Iron Mace Uniform Hat*/
/*Page 2*/
('1004961', '7400', 'Hat 7'),	/*Dango Set Hat*/

('1004989', '6300', 'Hat 7'),	/*Watermelon Cat Ears*/
('1004988', '4900', 'Hat 7'),	/*Floral Wave*/

('1004985', '5600', 'Hat 7'),	/*Rock Spirit Hat*/
('1004984', '7100', 'Hat 7'),	/*Blue Polka Dot Sunglasses*/
('1004983', '6000', 'Hat 7'),	/*Red Polka Dot Sunglasses*/
('1004982', '4000', 'Hat 7'),	/*Golden Laurel Crown*/
/*Page 3*/
('1004981', '3800', 'Hat 7'),	/*Summer Pom Pom Straw Hat*/

('1004978', '4000', 'Hat 7'),	/*Bunny Kit Hat*/
('1004977', '6300', 'Hat 7'),	/*Carrot Top*/
('1004976', '3200', 'Hat 7'),	/*Bananappeal Hat*/
('1005007', '4700', 'Hat 7'),	/*Mapo Tofu Hat*/
('1005006', '6300', 'Hat 7'),	/*Bulgogi Hat*/
('1005005', '4900', 'Hat 7'),	/*Rock Spirit*/
('1005004', '7100', 'Hat 7'),	/*Falling Darkness Hat*/
/*Page 4*/
('1005003', '6400', 'Hat 7'),	/*Feather Messenger Hat*/
('1005002', '5000', 'Hat 7'),	/*Sunny Dino Head Wrap*/
('1005001', '5600', 'Hat 7'),	/*Mellow Dino Head Wrap*/
('1005000', '3600', 'Hat 7'),	/*Stitched Teddy Cap*/
('1004999', '7600', 'Hat 7'),	/*Dragon Pop Headband*/
('1004998', '5000', 'Hat 7'),	/*Summer Flower Fairy Hat*/
('1004997', '6400', 'Hat 7'),	/*Refreshing Lemon Hat*/
('1004996', '4000', 'Hat 7'),	/*Shampoo Cap*/
('1004995', '6000', 'Hat 7'),	/*Charlotte Sun Hat*/
/*Page 5*/
('1004994', '5600', 'Hat 7'),	/*GS25 Jeonju Bibimbap*/
('1004993', '6300', 'Hat 7'),	/*GS25 Hot Fire Chicken*/
('1004992', '7100', 'Hat 7'),	/*GS25 Tuna Mayo*/
('1005023', '4900', 'Hat 7'),	/*Dog Rice Cake*/
('1005022', '5200', 'Hat 7'),	/*Shiba Hat*/
('1005021', '5200', 'Hat 7'),	/*Laziness Dog Hat*/
('1005020', '5600', 'Hat 7'),	/*Shadow Tactician Hat*/
('1005019', '3200', 'Hat 7'),	/*Cat Cafe Hat*/
('1005018', '4700', 'Hat 7'),	/*Apple Bunny Hat*/
/*Page 6*/
('1005017', '5200', 'Hat 7'),	/*Sensible Denim Denim Hat*/
('1005016', '6400', 'Hat 7'),	/*Flowery Cat Hat*/
('1005015', '6000', 'Hat 7'),	/*Kitty Cat Ears Hat*/
('1005014', '6400', 'Hat 7'),	/*Custom Kitty Hat*/
('1005013', '7400', 'Hat 7'),	/*Tom Yum Soup Hat*/
('1005012', '8800', 'Hat 7'),	/*Hamburger Hat*/
('1005011', '6400', 'Hat 7'),	/*Spaghetti Hat*/
('1005010', '6400', 'Hat 7'),	/*Chili Crab Hat*/
('1005009', '7400', 'Hat 7'),	/*Beef Noodle Soup Hat*/
/*Page 7*/
('1005008', '5000', 'Hat 7'),	/*Sushi Hat*/
('1005039', '3800', 'Hat 7'),	/*Crimson Fate Rosette*/
('1005038', '3600', 'Hat 7'),	/*Crimson Fate Rosette*/
('1005037', '4000', 'Hat 7'),	/*Crimson Fate Mark*/
('1005036', '4900', 'Hat 7'),	/*Ryude Hat*/
('1005035', '6400', 'Hat 7'),	/*Suit Heart Hair Clip*/
('1005034', '8800', 'Hat 7'),	/*Tree Spirit*/
('1005033', '3400', 'Hat 7'),	/*Ornate Gold-trimmed Memory*/
('1005032', '4000', 'Hat 7'),	/*Golden Memory*/
/*Page 8*/
('1005027', '5600', 'Hat 7'),	/*Puppy Love Samurai Hat (F)*/
('1005026', '4000', 'Hat 7'),	/*Puppy Love Samurai Hat (M)*/
('1005025', '7400', 'Hat 7'),	/*Mushroom Dog Hat*/
('1005024', '6000', 'Hat 7'),	/*Pug Hat*/
('1005055', '3200', 'Hat 7'),	/*Cabbie Spiegelmask*/
('1005054', '5200', 'Hat 7'),	/*Cassandra Spitfire Mask*/
('1005053', '7600', 'Hat 7'),	/*Detective Kemdi Mask*/
('1005052', '3200', 'Hat 7'),	/*Dumpling Hat*/

/*Page 9*/
('1005050', '6400', 'Hat 7'),	/*Snowflake Ski Hat (M)*/
('1005049', '3600', 'Hat 7'),	/*Snowflake Ski Hat (F)*/
('1005048', '7100', 'Hat 7'),	/*Warm Blue Bear Hat*/
('1005047', '3800', 'Hat 7'),	/*Warm Pink Bear Hat*/
('1005046', '8800', 'Hat 7'),	/*Christmas Bunny Hat*/
('1005045', '7400', 'Hat 7'),	/*Meow Hat*/
('1005044', '3800', 'Hat 7'),	/*Pair of Peas*/
('1005043', '5600', 'Hat 7'),	/*Cat Diamond*/
('1005042', '4700', 'Hat 7'),	/*Cattail Hat*/
/*Page 10*/
('1005041', '3600', 'Hat 7'),	/*Eggplant Top*/
('1005040', '3200', 'Hat 7'),	/*Crimson Fate Mark*/
('1005071', '4900', 'Hat 7'),	/*Sweet Choco Cake Slice*/
('1005070', '3800', 'Hat 7'),	/*Cherry Sundae Beanie*/
('1005069', '7400', 'Hat 7'),	/*Marshmallow Heart Earmuffs*/
('1005068', '6400', 'Hat 7'),	/*Shiny Crown*/
('1005067', '6400', 'Hat 7'),	/*Jawbreaker Ribbon*/
('1005066', '7400', 'Hat 7'),	/*Serene Heart Hair*/
('1005065', '3600', 'Hat 7'),	/*Drifting Heart Hair*/
/*Page 11*/
('1005064', '6400', 'Hat 7'),	/*Snowy Mountain Hat*/
('1005063', '3800', 'Hat 7'),	/*Cadena Platinum Wig (M)*/
('1005062', '7100', 'Hat 7'),	/*Cadena Platinum Wig (F)*/
('1005061', '6000', 'Hat 7'),	/*Fried Hat*/
('1005060', '7400', 'Hat 7'),	/*Busy Penguin Hat*/
('1005058', '4000', 'Hat 7'),	/*Strawberry Hair Pin*/
('1005057', '3200', 'Hat 7'),	/*Pink Bean Hairband*/
('1005056', '6000', 'Hat 7'),	/*MFF Moon Bunny Mask*/
('1005087', '6000', 'Hat 7'),	/*Worn Skull Hat*/
/*Page 12*/
('1005086', '5000', 'Hat 7'),	/*Worn Witch Hat*/
('1005085', '3600', 'Hat 7'),	/*Worn Messy Wig*/
('1005084', '5600', 'Hat 7'),	/*Flushed Puppy*/
('1005083', '5000', 'Hat 7'),	/*Mist Puppy*/
('1005082', '7100', 'Hat 7'),	/*4th Angel Hat*/
('1005081', '3800', 'Hat 7'),	/*Asuka Hat*/
('1005080', '6000', 'Hat 7'),	/*Angel Halo*/
('1005079', '3200', 'Hat 7'),	/*Tiger Cub Hat*/
('1005078', '3600', 'Hat 7'),	/*Cow Mask*/
/*Page 13*/
('1005077', '6000', 'Hat 7'),	/*Lab Server Master Crown*/
('1005076', '8800', 'Hat 7'),	/*Paper Bag*/
('1005075', '3200', 'Hat 7'),	/*Soft Earmuffs*/
('1005074', '7400', 'Hat 7'),	/*Jiangshi Hat*/
('1005073', '3200', 'Hat 7'),	/*Ghost Mask*/
('1005072', '7100', 'Hat 7'),	/*Fresh Strawberry Cake Slice*/
('1005103', '4000', 'Hat 7'),	/*Slime Cuddle Fuzz Hat*/
('1005102', '7400', 'Hat 7'),	/*Pink Bean Cuddle Fuzz Hat*/
('1005101', '7400', 'Hat 7'),	/*Royal Guard Hat (M)*/
/*Page 14*/
('1005100', '7400', 'Hat 7'),	/*Royal Guard Hat (F)*/
('1005099', '6000', 'Hat 7'),	/*Lunar New Year VIP Hat (M)*/
('1005098', '6400', 'Hat 7'),	/*Lunar New Year VIP Hat (F)*/
('1005097', '4000', 'Hat 7'),	/*Lunar New Year VIP Hat I*/
('1005096', '5600', 'Hat 7'),	/*Chunky Cable-Knit Hat*/
('1005095', '5600', 'Hat 7'),	/*Lunar New Year Fireworks Mask*/
('1005094', '3400', 'Hat 7'),	/*Oceanic Heart*/
('1005093', '4900', 'Hat 7'),	/*Oceanic Wings*/
('1005092', '4000', 'Hat 7'),	/*Soft Snow Hat*/
/*Page 15*/
('1005091', '4900', 'Hat 7'),	/*Vengeful Nyen's Hat*/
('1005090', '5000', 'Hat 7'),	/*Nyen's Hat*/
('1005089', '6400', 'Hat 7'),	/*Bunny Snapback*/
('1005088', '5600', 'Hat 7'),	/*Santa Hat*/
('1005119', '3600', 'Hat 7'),	/*Girlfriend Hat (M)*/
('1005118', '3400', 'Hat 7'),	/*Butterfly Hat*/
('1005117', '8800', 'Hat 7'),	/*Tennis Hat (F)*/
('1005116', '6400', 'Hat 7'),	/*Tennis Hat (M)*/
('1005115', '4900', 'Hat 7'),	/*World of Pink Hat (F)*/
/*Page 16*/
('1005114', '5000', 'Hat 7'),	/*World of Pink Hat (M)*/
('1005113', '7600', 'Hat 7'),	/*Red Bear Winter Hoodie*/
('1005112', '4900', 'Hat 7'),	/*Pink Horn Beanie*/
('1005111', '5200', 'Hat 7'),	/*Plum Blossom Bonnet*/
('1005110', '7100', 'Hat 7'),	/*Plum Blossom Petals*/
('1005135', '8800', 'Hat 7'),	/*Mochi Puppy*/

('1005131', '5200', 'Hat 7'),	/*Alchemist Gem*/
('1005130', '4900', 'Hat 7'),	/*Treasure Ship Kemdi Mask*/
/*Page 17*/
('1005129', '8800', 'Hat 7'),	/*Polar Kemdi Mask*/
('1005128', '3200', 'Hat 7'),	/*Desert Kemdi Mask*/
('1005127', '3600', 'Hat 7'),	/*Holiday Kemdi Mask*/
('1005126', '8800', 'Hat 7'),	/*Golden Kemdi Mask*/
('1005125', '7400', 'Hat 7'),	/*Golden Oink-Oink Beanie*/
('1005124', '5200', 'Hat 7'),	/*Gentle Bunny Hat*/
('1005123', '7400', 'Hat 7'),	/*Pink Bear Winter Hoodie*/
('1005122', '3800', 'Hat 7'),	/*Maple Blitzer Strategist Cap*/
('1005121', '8800', 'Hat 7'),	/*Cozy Bunny Hat*/
/*Page 18*/
('1005120', '4900', 'Hat 7'),	/*Girlfriend Hat (F)*/
('1005151', '4700', 'Hat 7'),	/*Little Darling Beret*/
('1005150', '3200', 'Hat 7'),	/*Pink Hipster Baseball Cap*/
('1005149', '6400', 'Hat 7'),	/*Yellow Hipster Baseball Cap*/
('1005145', '6400', 'Hat 7'),	/*Fluffy Pink Ribbon*/
('1005144', '5200', 'Hat 7'),	/*Carbon Wing Cap*/
('1005143', '5200', 'Hat 7'),	/*Kiddy Crayon Cap*/
('1005142', '3800', 'Hat 7'),	/*Green Hipster Baseball Cap*/
('1005141', '7100', 'Hat 7'),	/*Easter Bonnet*/
/*Page 19*/
('1005140', '5000', 'Hat 7'),	/*Flower Ribbon Headband*/
('1005139', '5600', 'Hat 7'),	/*Homeless Cat Hat*/
('1005138', '7600', 'Hat 7'),	/*Sakura's Hair Ornament*/
('1005137', '8800', 'Hat 7'),	/*Doggy Biscuit*/
('1005136', '4700', 'Hat 7'),	/*Floppy Puppy*/
('1005167', '7400', 'Hat 7'),	/*Jailbird Cap*/
('1005166', '7100', 'Hat 7'),	/*Candy Tiara*/
('1005164', '4000', 'Hat 7'),	/*Witchy Spring*/
('1005163', '4700', 'Hat 7'),	/*Upbeat Heart Hairband*/
/*Page 20*/
('1005162', '5200', 'Hat 7'),	/*Catty Hilla*/
('1005161', '5000', 'Hat 7'),	/*Catty Von Leon*/
('1005160', '6300', 'Hat 7'),	/*Catty Arkarium*/
('1005159', '3400', 'Hat 7'),	/*Catty Magnus*/
('1005158', '4900', 'Hat 7'),	/*Catty Will*/
('1005157', '7100', 'Hat 7'),	/*Ark Hat*/
('1005156', '6000', 'Hat 7'),	/*Super Summer Hat (M)*/
('1005155', '6000', 'Hat 7'),	/*Super Summer Hat (F)*/
('1005154', '3600', 'Hat 7'),	/*Tennis Ribbon*/
/*Page 21*/
('1005153', '6400', 'Hat 7'),	/*Tennis Cap*/
('1005152', '5200', 'Hat 7'),	/*Squirrel Hairband*/
('1005183', '5200', 'Hat 7'),	/*Hipster Baseball Cap*/
('1005182', '3600', 'Hat 7'),	/*Syaoran Battle Costume Hat*/
('1005181', '5000', 'Hat 7'),	/*Perched Tomoyo-chan*/
('1005180', '5200', 'Hat 7'),	/*Perched Syaoran-kun*/
('1005179', '7600', 'Hat 7'),	/*Perched Sakura-chan*/
('1005178', '4000', 'Hat 7'),	/*Perched Kero-chan*/
('1005177', '5000', 'Hat 7'),	/*Sakura Battle Costume Hat*/
/*Page 22*/
('1005176', '3400', 'Hat 7'),	/*Kero-chan Hood*/
('1005174', '5200', 'Hat 7'),	/*Erda Hat*/
('1005173', '3200', 'Hat 7'),	/*Happy Ghost Hat*/
('1005172', '3600', 'Hat 7'),	/*Picnic Snapback Hat*/
('1005171', '7100', 'Hat 7'),	/*Picnic Musubi Hairpin*/
('1005170', '3800', 'Hat 7'),	/*Picnic Fruit Hairpin*/
('1005169', '7400', 'Hat 7'),	/*Picnic Maki Hairpin*/
('1005168', '3600', 'Hat 7'),	/*Maple Gumshoe's Fedora*/
('1005195', '7100', 'Hat 7'),	/*Watermelon Top*/
/*Page 23*/
('1005194', '4900', 'Hat 7'),	/*Catkerchief Doll Hat*/
('1005193', '8800', 'Hat 7'),	/*Spring Green Ribbon*/
('1005192', '4000', 'Hat 7'),	/*Super Summer Snorkel*/
('1005190', '3400', 'Hat 7'),	/*Kitty Planet Posh Hat*/
('1005189', '6300', 'Hat 7'),	/*Summer Story Hat (M)*/
('1005188', '7100', 'Hat 7'),	/*Summer Story Hat (F)*/

('1005186', '6000', 'Hat 7'),	/*Ballpark Buddies Hat*/
('1005185', '7400', 'Hat 7'),	/*Guardian Knight Hat*/
/*Page 24*/
('1005184', '3600', 'Hat 7'),	/*Elite Knight Hat*/
('1005212', '4000', 'Hat 7'),	/*Misty Starlight*/
('1005211', '3600', 'Hat 7'),	/*Misty Moonlight*/
('1005210', '3400', 'Hat 7'),	/*Misty Starlight*/
('1005209', '6400', 'Hat 7'),	/*Misty Moonlight*/
('1005208', '5200', 'Hat 7'),	/*Flicker Mask*/
('1005207', '7400', 'Hat 7'),	/*Iron Rabbit Mask*/
('1005206', '5600', 'Hat 7'),	/*Ursie Mask*/
('1005205', '5600', 'Hat 7'),	/*Gym Cat Mask*/
/*Page 25*/
('1005204', '4000', 'Hat 7'),	/*Nautically Pink Hat*/
('1005203', '5600', 'Hat 7'),	/*Maple Alliance Hat*/
('1005231', '7400', 'Hat 7'),	/*Detective Chappeau*/
('1005228', '7400', 'Hat 7'),	/*Hey, Corgi! Hat*/
('1005224', '7600', 'Hat 7'),	/*Custom Puppy Hat*/
('1005223', '6000', 'Hat 7'),	/*Alliance Commander Tiara*/
('1005222', '3200', 'Hat 7'),	/*One-Eyed Grim Reaper Hat*/
('1005221', '8800', 'Hat 7'),	/*Starry Summer Night Hat*/
('1005220', '6300', 'Hat 7'),	/*Carnation Diving Mask*/
/*Page 26*/
('1005219', '5200', 'Hat 7'),	/*Cerulean Diving Mask*/
('1005218', '3200', 'Hat 7'),	/*Seafoam Ribbon*/
('1005217', '6000', 'Hat 7'),	/*Coral Ribbon*/
('1005247', '3600', 'Hat 7'),	/*Cluckhead*/
('1005246', '5000', 'Hat 7'),	/*Cloud Crown*/
('1005245', '7100', 'Hat 7'),	/*Lovely Plaid Cap*/
('1005244', '4900', 'Hat 7'),	/*Empress's Light*/
('1005243', '8800', 'Hat 7'),	/*Shinsoo's Light*/
('1005239', '3800', 'Hat 7'),	/*Happy Bear Hat (M)*/
/*Page 27*/
('1005238', '6300', 'Hat 7'),	/*Happy Bear Hat (F)*/
('1005237', '4900', 'Hat 7'),	/*Delinquent Bear Hat*/
('1005236', '3400', 'Hat 7'),	/*Heavenly Prayer Hat*/
('1005235', '8800', 'Hat 7'),	/*Lucid Fedora*/
('1005234', '5000', 'Hat 7'),	/*Dawn Fascinator*/
('1005233', '5200', 'Hat 7'),	/*Alliance Commander Tiara*/
('1005232', '3800', 'Hat 7'),	/*Detective Millinery*/
('1005263', '4900', 'Hat 7'),	/*Roar Snapback*/
('1005262', '4700', 'Hat 7'),	/*Romantic Vagabond*/
/*Page 28*/
('1005261', '5000', 'Hat 7'),	/*Glided Veil*/
('1005260', '5600', 'Hat 7'),	/*Gilded Cap*/
('1005259', '6300', 'Hat 7'),	/*Tri-color Hat (M)*/

('1005257', '7100', 'Hat 7'),	/*Warm Winter Bear Hat*/
('1005256', '6000', 'Hat 7'),	/*Warm Winter Bunny Hat*/
('1005255', '6300', 'Hat 7'),	/*Warm Winter Penguin Hat*/
('1005254', '8800', 'Hat 7'),	/*Little Star Cocoon Hat*/
('1005253', '3200', 'Hat 7'),	/*Sweet Deer Hood*/
/*Page 29*/
('1005252', '3200', 'Hat 7'),	/*Tri-color Hat (F)*/
('1005279', '6000', 'Hat 7'),	/*Lunar New Year Pudgy Piggy Hat*/
('1005278', '6000', 'Hat 7'),	/*Cobalt Filigree Comb (F)*/



/*Page 1*/
('1005277', '7400', 'Hat 8'),	/*Cobalt Filigree Pin (M)*/
('1005276', '3800', 'Hat 8'),	/*Silly Blue Pajama Hat (M)*/
('1005275', '3200', 'Hat 8'),	/*Frilly Pink Pajama Hat (F)*/
('1005274', '5000', 'Hat 8'),	/*Regal Romance Hat (M)*/
('1005273', '6400', 'Hat 8'),	/*Crown Fitness Ballcap*/
('1005272', '7100', 'Hat 8'),	/*Snowflake Earmuffs*/
('1005271', '4000', 'Hat 8'),	/*Forest Reindeer Hat*/
('1005270', '6400', 'Hat 8'),	/*Winterberry Snowman Hat*/
('1005269', '8800', 'Hat 8'),	/*Regal Romance Hat (F)*/
/*Page 2*/
('1005268', '3600', 'Hat 8'),	/*Cozy Winter Clothes Hat*/
('1005267', '6000', 'Hat 8'),	/*Hallowkitty's Neon Hat*/
('1005295', '5200', 'Hat 8'),	/*Hunny Bun Bear Baubles*/
('1005294', '5000', 'Hat 8'),	/*Hunny Bun Bear Hood*/
('1005293', '5000', 'Hat 8'),	/*Mustachioed Strawberry Bauble*/
('1005292', '3600', 'Hat 8'),	/*Twinkle Lights Halo*/
('1005291', '6000', 'Hat 8'),	/*Candy Mane Hairpin*/
('1005290', '3400', 'Hat 8'),	/*Frost Flower Hairpin*/
('1005289', '5600', 'Hat 8'),	/*Santa Strawberry Bauble*/
/*Page 3*/
('1005288', '4900', 'Hat 8'),	/*Glittering Rudolf Antlers*/
('1005287', '3200', 'Hat 8'),	/*Neckerchief Fascinator (Ivory)*/
('1005286', '3800', 'Hat 8'),	/*Neckerchief Fascinator (Red)*/
('1005285', '3800', 'Hat 8'),	/*Neckerchief Fascinator (Purple)*/
('1005284', '3400', 'Hat 8'),	/*Neckerchief Fascinator (Navy)*/
('1005283', '5000', 'Hat 8'),	/*Neckerchief Fascinator (Black)*/
('1005281', '3400', 'Hat 8'),	/*Camelia Tea Bonnet*/
('1005280', '4700', 'Hat 8'),	/*Camelia Newsboy Cap*/
('1005311', '6000', 'Hat 8'),	/*Spring Ducky Rain Hat*/
/*Page 4*/
('1005310', '3200', 'Hat 8'),	/*Sugarsweet Candy Hat*/
('1005309', '3400', 'Hat 8'),	/*Peach Bloom Effect Hat*/
('1005308', '3800', 'Hat 8'),	/*Spring Cleaning Head Scarf*/
('1005307', '3400', 'Hat 8'),	/*Grim Reaper Mask*/
('1005301', '3800', 'Hat 8'),	/*Blushing Reindeer Hat*/
('1005300', '7600', 'Hat 8'),	/*Snowman Mask*/
('1005299', '6400', 'Hat 8'),	/*Blushing Reindeer Hat*/
('1005298', '3200', 'Hat 8'),	/*Santa Hat*/
('1005297', '6400', 'Hat 8'),	/*Reindeer Hat*/
/*Page 5*/
('1005296', '6400', 'Hat 8'),	/*Mustachioed Beanie*/
('1005326', '3800', 'Hat 8'),	/*Sky Blue Bow*/
('1005325', '3400', 'Hat 8'),	/*Cursed Hunter Hood*/
('1005324', '3600', 'Hat 8'),	/*Wish Fulfiller Hat*/
('1005323', '6300', 'Hat 8'),	/*Midnight Magician Hat*/
('1005322', '7400', 'Hat 8'),	/*Sunny Songbird Hat*/
('1005320', '6400', 'Hat 8'),	/*Pink Bean Hooded Scarf*/
('1005319', '3400', 'Hat 8'),	/*Lavender Shearling Band*/
('1005318', '3600', 'Hat 8'),	/*Snoozing SALLY Topper*/
/*Page 6*/
('1005317', '6300', 'Hat 8'),	/*SALLY Hood*/
('1005316', '4700', 'Hat 8'),	/*LEONARD Hood*/
('1005315', '3800', 'Hat 8'),	/*Piggy Pal Headpiece*/
('1005314', '6400', 'Hat 8'),	/*CONY Hood*/
('1005312', '5000', 'Hat 8'),	/*Super Coif*/
('1005342', '7600', 'Hat 8'),	/*Tulip Mini Hairpin*/
('1005341', '5600', 'Hat 8'),	/*Azalea Mini Hairpin*/
('1005340', '5600', 'Hat 8'),	/*Lilac Mini Hairpin*/
('1005339', '4700', 'Hat 8'),	/*Pearblossom Mini Hairpin*/
/*Page 7*/
('1005338', '3200', 'Hat 8'),	/*Forsythia Mini Hairpin*/
('1005337', '5600', 'Hat 8'),	/*Starry Light Topper*/
('1005336', '7600', 'Hat 8'),	/*Starry Light Fascinator*/
('1005335', '7100', 'Hat 8'),	/*Blue Flame Hellion Hat*/
('1005334', '4000', 'Hat 8'),	/*Bunny Ear Beret*/
('1005333', '6300', 'Hat 8'),	/*Bunny Ear Beret*/
('1005358', '7400', 'Hat 8'),	/*Ice Cream Hat*/
('1005353', '6300', 'Hat 8'),	/*Springtime Sprout Hood*/
('1005352', '5600', 'Hat 8'),	/*Fox Fire Ears*/
/*Page 8*/
('1005363', '4000', 'Hat 8'),	/*Red Lotus Spirit Walker's Flower*/





/*  Face  */
/*Page 1*/
('1010009', '3400', 'Face'),	/*Black Blae Face Accessory*/
('1010008', '5200', 'Face'),	/*Blue Blae Face Accessory*/
('1010007', '3800', 'Face'),	/*Cold Make-up*/
('1010006', '7400', 'Face'),	/*Yakuza Scar*/
('1010005', '3800', 'Face'),	/*General's Mustache (2)*/
('1010004', '4900', 'Face'),	/*General's Mustache (1)*/
('1010003', '4700', 'Face'),	/*5 O'Clock Shadow*/
('1010002', '4700', 'Face'),	/*Ninja Mask for Men*/
('1010001', '4000', 'Face'),	/*Goatee*/
/*Page 2*/
('1010000', '7600', 'Face'),	/*Long Brown Beard*/
('1011007', '6400', 'Face'),	/*Blue Blair Face Accessory*/
('1011006', '5000', 'Face'),	/*Soulful Make-up*/
('1011003', '3600', 'Face'),	/*Freckles*/
('1011002', '6300', 'Face'),	/*Heart*/
('1011001', '7100', 'Face'),	/*SF Ninja Mask*/
('1011000', '3800', 'Face'),	/*Ninja Mask for Women*/
('1011008', '7600', 'Face'),	/*Black Blair Face Accessory*/
('1012010', '3800', 'Face'),	/*Hinomaru*/
/*Page 3*/
('1012009', '7600', 'Face'),	/*Kiss Mark*/
('1012008', '4900', 'Face'),	/*Censor*/
('1012007', '5600', 'Face'),	/*Santa Beard*/
('1012006', '6000', 'Face'),	/*Rose*/
('1012005', '4000', 'Face'),	/*Bruise*/
('1012004', '7100', 'Face'),	/*Camo Face Paint*/
('1012003', '3800', 'Face'),	/*Rouge*/
('1012002', '3200', 'Face'),	/*Leather Mask*/
('1012001', '5600', 'Face'),	/*Bindi*/
/*Page 4*/
('1012000', '5200', 'Face'),	/*Battle Scar*/
('1012031', '6000', 'Face'),	/*Leaf*/
('1012030', '4700', 'Face'),	/*Eye Scar*/
('1012029', '4700', 'Face'),	/*Jester Mask*/
('1012028', '3600', 'Face'),	/*Blush*/
('1012027', '3400', 'Face'),	/*Bandage Strip*/
('1012026', '4700', 'Face'),	/*Guan Yu Beard*/
('1012025', '7600', 'Face'),	/*War Paint*/
('1012024', '6000', 'Face'),	/*Gentleman's Mustache*/
/*Page 5*/
('1012023', '5600', 'Face'),	/*Yellow Kabuki Mask*/
('1012022', '4000', 'Face'),	/*Red Kabuki Mask*/
('1012021', '5000', 'Face'),	/*White Kabuki Mask*/
('1012047', '3800', 'Face'),	/*Fu Manchu*/
('1012044', '7400', 'Face'),	/*Mummy Mask*/
('1012043', '4000', 'Face'),	/*Australia Face Painting*/
('1012042', '7400', 'Face'),	/*Aztec Paint (Mexico)*/
('1012041', '6300', 'Face'),	/*Star Spangled Paint (USA)*/
('1012040', '4000', 'Face'),	/*Heart Face Painting*/
/*Page 6*/
('1012039', '7600', 'Face'),	/*Taegeuk Paint (Korea)*/
('1012038', '8800', 'Face'),	/*Rising Sun Paint (Japan)*/
('1012037', '4900', 'Face'),	/*Armillary Shield Paint (Portugal)*/
('1012036', '3400', 'Face'),	/*Bundes Paint (Germany)*/
('1012035', '3400', 'Face'),	/*Brazillian Paint (Brazil)*/
('1012034', '7600', 'Face'),	/*Tri-color Paint (France)*/
('1012033', '4000', 'Face'),	/*England Face Painting*/
('1012032', '3400', 'Face'),	/*White Bread*/
('1012063', '6400', 'Face'),	/*Kitty Paint*/
/*Page 7*/
('1012062', '4700', 'Face'),	/*Mild Pink Lipstick*/
('1012057', '7100', 'Face'),	/*Transparent Face Accessory*/
('1012056', '6300', 'Face'),	/*Doggy Mouth*/
('1012055', '4000', 'Face'),	/*Allergic Reaction*/
('1012054', '8800', 'Face'),	/*Purple Rage*/
('1012053', '7600', 'Face'),	/*Unmanaged Anger*/
('1012052', '3200', 'Face'),	/*Tongue Twister Scroll*/
('1012051', '6400', 'Face'),	/*Dark Jester*/
('1012050', '6300', 'Face'),	/*Maple-Stein Face*/
/*Page 8*/
('1012049', '6300', 'Face'),	/*Ogre Mask*/
('1012048', '3200', 'Face'),	/*Dark Jack's Scar*/
('1012075', '6300', 'Face'),	/*Cold Sweat*/
('1012074', '6400', 'Face'),	/*Mocking Laughter*/
('1012090', '7400', 'Face'),	/*Facial Powder*/
('1012085', '6300', 'Face'),	/*Cherry Bubblegum*/
('1012083', '3200', 'Face'),	/*Dollish Pink*/
('1012082', '6000', 'Face'),	/*Ice Cold Red*/
('1012081', '4000', 'Face'),	/*MV Mask*/
/*Page 9*/
('1012080', '7600', 'Face'),	/*Fat Lips*/
('1012105', '6400', 'Face'),	/*Super Sucker*/
('1012104', '5000', 'Face'),	/*Transparent Face Accessory*/
('1012100', '4000', 'Face'),	/*Facial Powder(red)*/
('1012099', '7400', 'Face'),	/*Facial Powder(blue)*/
('1012097', '3200', 'Face'),	/*Purple Noisemaker*/
('1012096', '3600', 'Face'),	/*Apple Bubble Gum*/
('1012127', '4000', 'Face'),	/*Crescent Paint (Singapore)*/
('1012126', '7100', 'Face'),	/*Yellow Star Paint (Vietnam)*/
/*Page 10*/
('1012125', '3400', 'Face'),	/*Chakra Paint (Thailand)*/
('1012124', '4000', 'Face'),	/*Union Paint (UK)*/
('1012123', '7100', 'Face'),	/*Holland Paint (Netherlands)*/
('1012122', '8800', 'Face'),	/*Gold Nordic Paint (Sweden)*/
('1012121', '7400', 'Face'),	/*Coat of Arms Paint (Spain)*/
('1012114', '6400', 'Face'),	/*5-Starred Red Paint (China)*/
('1012113', '3200', 'Face'),	/*ROC Paint (Taiwan)*/
('1012112', '7600', 'Face'),	/*Bauhinia Paint (Hong Kong)*/
('1012137', '5600', 'Face'),	/*Star Face Painting*/
/*Page 11*/
('1012134', '4000', 'Face'),	/*Tear Drop Face Tattoo*/

('1012131', '3400', 'Face'),	/*Smiling Face*/
('1012129', '6000', 'Face'),	/*Maple Leaf Paint  (Canada)*/
('1012128', '6300', 'Face'),	/*Jalur Gemilang Paint (Malaysia)*/
('1012159', '6300', 'Face'),	/*Foxy Mask*/
('1012147', '4700', 'Face'),	/*Immortal Mask*/
('1012166', '7400', 'Face'),	/*Villain Mask*/
('1012165', '7100', 'Face'),	/*Clown Nose*/
/*Page 12*/

('1012180', '3200', 'Face'),	/*Chocolate Heart*/
('1012179', '3800', 'Face'),	/*Reindeer Red Nose*/
('1012176', '4700', 'Face'),	/*Orange Blush*/
('1012192', '5600', 'Face'),	/*Shadow Mask*/
('1012208', '7400', 'Face'),	/*Lovely Smile*/
('1012253', '3400', 'Face'),	/*Heart Pounding Lip Gloss*/
('1012275', '6000', 'Face'),	/*6th Anniversary Party Glasses*/
('1012298', '3200', 'Face'),	/*Hand Mark*/
/*Page 13*/
('1012289', '5600', 'Face'),	/*Transparent Face Accessory*/
('1012315', '6300', 'Face'),	/*Adhesive Bandage*/
('1012366', '3200', 'Face'),	/*Zombie Hunter Mask*/
('1012379', '4700', 'Face'),	/*Flushed Cheeks*/
('1012374', '5200', 'Face'),	/*Heartbreaker Lips*/
('1012390', '7100', 'Face'),	/*Peruvian Flag Face Paint*/
('1012388', '5000', 'Face'),	/*Clown*/
('1012384', '5200', 'Face'),	/*Playful Band*/
('1012415', '6000', 'Face'),	/*Blingin' Red Lipstick*/
/*Page 14*/
('1012413', '5200', 'Face'),	/*Naked Face*/
('1012412', '4000', 'Face'),	/*Bloody Tears*/
('1012431', '5200', 'Face'),	/*Straight Face Accessory*/
('1012430', '4700', 'Face'),	/*Bear Nose Face Accessory*/
('1012429', '6300', 'Face'),	/*Round Eyes-And-Mouth Face Accessory*/
('1012428', '7400', 'Face'),	/*Thick Eyebrows Face Accessory*/
('1012427', '5000', 'Face'),	/*Surprised Face Accessory*/
('1012437', '8800', 'Face'),	/*Palm Print Mask*/
('1012436', '7100', 'Face'),	/*Enlightened Face Accessory*/
/*Page 15*/
('1012435', '6300', 'Face'),	/*Gross Face Accessory*/
('1012434', '4000', 'Face'),	/*Mustache Face Accessory*/
('1012433', '7400', 'Face'),	/*Animal Face Accessory*/
('1012432', '5200', 'Face'),	/*Cat-Mouth Face Accessory*/
('1012462', '6300', 'Face'),	/*Ghost Bride's Shining Dark Eyes*/
('1012450', '5200', 'Face'),	/*Choco Candy Cookie*/
('1012479', '7600', 'Face'),	/*Ruddy Kitten Nose*/
('1012475', '8800', 'Face'),	/*태극 페인팅*/
('1012474', '5200', 'Face'),	/*Clobber*/
/*Page 16*/
('1012473', '3800', 'Face'),	/*So Angry!!! Face*/
('1012472', '5600', 'Face'),	/*레인보우 페인팅*/
('1012468', '7100', 'Face'),	/*Yummy Candy*/
('1012495', '5600', 'Face'),	/*Skull Mask*/
('1012494', '7400', 'Face'),	/*Worn Skull Mask*/
('1012489', '6300', 'Face'),	/*LaLaLa Megaphone*/
('1012488', '4000', 'Face'),	/*Clobber*/
('1012487', '5000', 'Face'),	/*Oozer*/
('1012486', '3200', 'Face'),	/*Hothead*/
/*Page 17*/
('1012485', '5600', 'Face'),	/*Sweet Persimmon Blush*/
('1012482', '8800', 'Face'),	/*Quack Quack*/
('1012511', '7600', 'Face'),	/*Cleaning Mask*/
('1012510', '7400', 'Face'),	/*Sasha's Delicious Bread*/
('1012509', '7400', 'Face'),	/*Hange's Glasses*/



('1012502', '3200', 'Face'),	/*Frosty Frozen Face*/
/*Page 18*/
('1012501', '7100', 'Face'),	/*No Biting!*/
('1012527', '7600', 'Face'),	/*Blushing Yeti*/
('1012526', '5000', 'Face'),	/*So Delish Ice Cream*/
('1012525', '5200', 'Face'),	/*Smile! It's the Sweet Maple Festival!*/
('1012518', '3200', 'Face'),	/*Von Bon Mask*/
('1012517', '4900', 'Face'),	/*Vellum Mask*/
('1012515', '4000', 'Face'),	/*Strawberry Cake*/
('1012514', '7600', 'Face'),	/*Heart-Shaped Chocolate*/
('1012534', '3800', 'Face'),	/*Rainbow Face Paint*/
/*Page 19*/
('1012533', '4000', 'Face'),	/*Spring Cloud Piece*/
('1012528', '7100', 'Face'),	/*Boss Lotus Eyes*/
('1012557', '6000', 'Face'),	/*Nom Nom Oz*/
('1012556', '8800', 'Face'),	/*Vampire Eyes (Ruby)*/
('1012555', '3200', 'Face'),	/*Vampire Eyes (Sapphire)*/
('1012552', '8800', 'Face'),	/*Pink Bean Sadface*/
('1012551', '5000', 'Face'),	/*The Mighty Face*/
('1012544', '4700', 'Face'),	/*Culnesis*/
('1012573', '5600', 'Face'),	/*Pretty Pretty Smile*/
/*Page 20*/
('1012572', '6300', 'Face'),	/*Little Kitten Face Accessory*/
('1012571', '3600', 'Face'),	/*Sleepy Zees*/
('1012569', '3600', 'Face'),	/*Baby Binkie*/
('1012568', '3600', 'Face'),	/*Shocked Eyes*/
('1012567', '3400', 'Face'),	/*Touched Tears*/
('1012562', '5200', 'Face'),	/*Heartbeam Face*/
('1012589', '7100', 'Face'),	/*So Sleepy*/
('1012579', '4900', 'Face'),	/*Pouty Face*/
('1012607', '3600', 'Face'),	/*>:3*/
/*Page 21*/
('1012603', '4700', 'Face'),	/*Hangover Make-up*/
('1012602', '4000', 'Face'),	/*Squinting Toothy Smirk*/
('1012601', '8800', 'Face'),	/*Toothy Smirk*/
('1012597', '4900', 'Face'),	/*ㅅ ㅅ 얼굴장식*/
('1012596', '4900', 'Face'),	/*ㅅ_ㅅ 얼굴장식*/
('1012595', '7400', 'Face'),	/*ㅅㅂㅅ 얼굴장식*/
('1012594', '3600', 'Face'),	/*ㅇㅅㅇ 얼굴장식*/
('1012593', '3200', 'Face'),	/*ㅇ_ㅇ 얼굴장식*/
('1012592', '5600', 'Face'),	/*ㅇㅂㅇ 얼굴장식*/
/*Page 22*/
('1012623', '7100', 'Face'),	/*Puppy Face Accessory*/
('1012621', '5000', 'Face'),	/*Grumpy Face*/
('1012620', '7100', 'Face'),	/*Squishy Face*/
('1012619', '7600', 'Face'),	/*Calabash Pipe*/
('1012618', '7400', 'Face'),	/*Strawberry Pastry*/
('1012617', '8800', 'Face'),	/*Strawberry Cake*/
('1012616', '3800', 'Face'),	/*Chocolate Heart*/
('1012615', '8800', 'Face'),	/*Furtive Smile Face*/
('1012614', '6000', 'Face'),	/*Face of Longing*/
/*Page 23*/
('1012612', '4700', 'Face'),	/*Snowy Eyeliner (M)*/
('1012611', '5000', 'Face'),	/*Snowy Eyeliner (F)*/
('1012610', '4900', 'Face'),	/*Enigma*/
('1012609', '6300', 'Face'),	/*White Night Tattoo*/
('1012608', '4700', 'Face'),	/*Chocolate Overload Face Accessory*/
('1012639', '3200', 'Face'),	/*Rainbow Blush*/
('1012638', '7100', 'Face'),	/*중얼중얼 혼잣말*/
('1012637', '8800', 'Face'),	/*Worn Skull Mask*/
('1012636', '5000', 'Face'),	/*Dark Silence*/
/*Page 24*/
('1012635', '7600', 'Face'),	/*Teeth Brushing Time*/
('1012634', '3600', 'Face'),	/*Nyan Nose*/
('1012633', '6000', 'Face'),	/*Lost in Thought*/
('1012631', '3400', 'Face'),	/*Bandaged Lips*/
('1012630', '6300', 'Face'),	/*Dreamy Cat Eyes Mask*/
('1012629', '7400', 'Face'),	/*Starshine Cat Eyes Mask*/
('1012628', '7100', 'Face'),	/*Custom Kitty Whiskers*/
('1012626', '4900', 'Face'),	/*Cadena Mask*/
('1012625', '7600', 'Face'),	/*Hearty Flush*/
/*Page 25*/
('1012624', '6400', 'Face'),	/*Round Pucker Face*/
('1012654', '7600', 'Face'),	/*Steamed Bun Face*/
('1012653', '3800', 'Face'),	/*Topsy-turvy Face*/

('1012651', '7400', 'Face'),	/*Teared Up*/
('1012650', '6000', 'Face'),	/*Scribbleface*/
('1012649', '6400', 'Face'),	/*So Angry*/
('1012647', '5000', 'Face'),	/*So Yummy*/
('1012646', '5200', 'Face'),	/*So Tasty*/
/*Page 26*/
('1012645', '4700', 'Face'),	/*Skeleton Surgeon Mask*/
('1012644', '4000', 'Face'),	/*Teddy Surgeon Mask*/
('1012642', '6000', 'Face'),	/*Bubble Pup Mask*/
('1012641', '3600', 'Face'),	/*Peach Blossom*/
('1012669', '8800', 'Face'),	/*Lavender Doll*/
('1012668', '5600', 'Face'),	/*Purple Doll*/
('1012663', '5600', 'Face'),	/*Custom Puppy Whiskers*/
('1012662', '7400', 'Face'),	/*Ushishishi Face Accessory*/
('1012661', '7600', 'Face'),	/*Reaper's Face*/
/*Page 27*/
('1012660', '4700', 'Face'),	/*Naturally Luminous*/
('1012659', '4000', 'Face'),	/*Pure Nostalgia*/
('1012658', '6300', 'Face'),	/*Antagonist Face*/
('1012657', '6400', 'Face'),	/*Hawalu's Teary-Eyed Face Accessory*/
('1012656', '6300', 'Face'),	/*Round Blush*/
('1012687', '8800', 'Face'),	/*Fox Fire Grin*/
('1012686', '6000', 'Face'),	/*Blue Flame Hellion Face Accessory*/
('1012685', '3800', 'Face'),	/*Stubborn Face*/
('1012683', '3200', 'Face'),	/*Blushing Frosty*/
/*Page 28*/
('1012682', '5200', 'Face'),	/*Googly Face Accessory*/
('1012681', '6000', 'Face'),	/*Cobalt Filigree Face Accessory (M)*/
('1012680', '3400', 'Face'),	/*Cobalt Filigree Face Accessory (F)*/
('1012679', '4700', 'Face'),	/*Pajama Eye Mask*/
('1012678', '4900', 'Face'),	/*Regal Romance Eyes (M)*/
('1012677', '3400', 'Face'),	/*Regal Romance Eyes (F)*/
('1012676', '3200', 'Face'),	/*Black Moon*/
('1012675', '7100', 'Face'),	/*White Moon*/
('1012674', '8800', 'Face'),	/*Tri-color Face Accessory*/
/*Page 29*/
('1012673', '6300', 'Face'),	/*Strawberry Glow*/
('1012672', '3200', 'Face'),	/*Grapefruit Glow*/
('1012690', '6300', 'Face'),	/*Mopey Face*/





/*  Eye  */
/*Page 1*/
('1020000', '5600', 'Eye'),	/*Aqua Toy Shades*/
('1021000', '3800', 'Eye'),	/*Pink Toy Shades*/
('1022015', '7400', 'Eye'),	/*Black Aviator Shades*/
('1022014', '4900', 'Eye'),	/*Brown Aviator Shades*/
('1022013', '4000', 'Eye'),	/*Black Eye Guard*/
('1022012', '7400', 'Eye'),	/*Blue Eye Guard*/
('1022011', '5200', 'Eye'),	/*Red Eye Guard*/
('1022010', '4900', 'Eye'),	/*Blue & Red Eye Guard*/
('1022009', '4900', 'Eye'),	/*Dark Shades*/
/*Page 2*/
('1022008', '5600', 'Eye'),	/*Orange Hard-Rimmed Glasses*/
('1022007', '8800', 'Eye'),	/*Green Hard-Rimmed Glasses*/
('1022006', '3800', 'Eye'),	/*Blue Hard-Rimmed Glasses*/
('1022005', '8800', 'Eye'),	/*Red Hard-Rimmed Glasses*/
('1022004', '4900', 'Eye'),	/*Black Sunglasses*/
('1022003', '7100', 'Eye'),	/*Green Shades*/
('1022002', '8800', 'Eye'),	/*Yellow Shades*/
('1022001', '4700', 'Eye'),	/*Blue Shades*/
('1022000', '7100', 'Eye'),	/*Orange Shades*/
/*Page 3*/
('1022031', '6400', 'Eye'),	/*White Toy Shades*/
('1022030', '8800', 'Eye'),	/*Hot Teacher Glasses*/
('1022029', '8800', 'Eye'),	/*Spinning Piglet*/
('1022028', '4000', 'Eye'),	/*Spinning Groucho*/
('1022027', '3600', 'Eye'),	/*Medical Eye Patch*/
('1022026', '5000', 'Eye'),	/*Purple Starred Eye Patch*/
('1022025', '8800', 'Eye'),	/*Red Hearted Eye Patch*/
('1022024', '6000', 'Eye'),	/*Skull Patch*/
('1022023', '7600', 'Eye'),	/*Crested Eye Patch*/
/*Page 4*/
('1022022', '4000', 'Eye'),	/*Blue Head-Spinning Glasses*/
('1022021', '5200', 'Eye'),	/*Red Head-Spinning Glasses*/
('1022020', '3200', 'Eye'),	/*Metal Shades*/
('1022019', '4000', 'Eye'),	/*Old-School Glasses*/
('1022018', '5000', 'Eye'),	/*Classic Masquerade Mask*/
('1022017', '5600', 'Eye'),	/*Purple Aviator Shades*/
('1022016', '3600', 'Eye'),	/*Blue Aviator Shades*/
('1022047', '4000', 'Eye'),	/*Owl Ball Mask*/
('1022046', '4900', 'Eye'),	/*Butterfly Ball Mask*/
/*Page 5*/
('1022045', '5200', 'Eye'),	/*Red Bushido Bandana*/
('1022044', '3200', 'Eye'),	/*Nerdy Glasses*/
('1022043', '3400', 'Eye'),	/*Head Bandage*/
('1022042', '6400', 'Eye'),	/*Scouter*/
('1022041', '3800', 'Eye'),	/*Cyclist Shades*/
('1022040', '5200', 'Eye'),	/*Lead Monocle*/
('1022039', '3400', 'Eye'),	/*Orange Round Shades*/
('1022038', '3400', 'Eye'),	/*Purple Round Shades*/
('1022037', '4700', 'Eye'),	/*Frameless Glasses*/
/*Page 6*/
('1022036', '3200', 'Eye'),	/*Green Sports Goggle*/
('1022035', '6300', 'Eye'),	/*Orange Sports Goggle*/
('1022034', '4900', 'Eye'),	/*Bizarre Monocle*/
('1022033', '6300', 'Eye'),	/*Politician Glasses*/
('1022032', '5200', 'Eye'),	/*Yellow Toy Shades*/
('1022063', '4700', 'Eye'),	/*Flat Mini Glasses*/
('1022062', '4900', 'Eye'),	/*Black Skull Eye Patch*/
('1022061', '4000', 'Eye'),	/*Redbeard's Pirate Eye Patch*/
('1022059', '7600', 'Eye'),	/*Black Shades*/
/*Page 7*/
('1022057', '3800', 'Eye'),	/*Pop-Eye*/
('1022056', '6400', 'Eye'),	/*Pink Aviator Sunglasses*/
('1022055', '4700', 'Eye'),	/*Pink Sunglasses*/
('1022054', '4900', 'Eye'),	/*Round Shield Shades*/
('1022053', '6300', 'Eye'),	/*Futuristic Shades*/
('1022052', '4900', 'Eye'),	/*Future Vision Shades*/
('1022051', '7100', 'Eye'),	/*Red Half-Rim Glasses*/
('1022050', '7100', 'Eye'),	/*Vintage Glasses*/
('1022049', '8800', 'Eye'),	/*Green-Rimmed Glasses*/
/*Page 8*/
('1022048', '5200', 'Eye'),	/*Transparent Eye Accessory*/
('1022079', '7100', 'Eye'),	/*Clear Glasses*/
('1022075', '8800', 'Eye'),	/*Twinkling Eyes*/
('1022074', '7100', 'Eye'),	/*Gaga Glasses*/
('1022072', '5200', 'Eye'),	/*Yellow Shutter Shades*/
('1022071', '5000', 'Eye'),	/*Red Shutter Shades*/
('1022070', '5000', 'Eye'),	/*Green Shutter Shades*/
('1022069', '6400', 'Eye'),	/*Orange Shutter Shades*/
('1022068', '3400', 'Eye'),	/*White Shades*/
/*Page 9*/
('1022066', '6000', 'Eye'),	/*Star Spectacles*/
('1022065', '7600', 'Eye'),	/*Alphabet Glasses*/
('1022064', '3800', 'Eye'),	/*Big Red Glasses*/
('1022095', '8800', 'Eye'),	/*I Like Money*/
('1022090', '4900', 'Eye'),	/*Gaga Glasses*/
('1022087', '7400', 'Eye'),	/*Green Eye Mask*/
('1022086', '3400', 'Eye'),	/*Blue Eye Mask*/
('1022085', '6400', 'Eye'),	/*Pink Eye Mask*/
('1022084', '8800', 'Eye'),	/*Eye Mask (Red)*/
/*Page 10*/
('1022083', '7100', 'Eye'),	/*Hitman Sunglasses*/
('1022081', '3800', 'Eye'),	/*Cracked Glasses*/
('1022110', '4700', 'Eye'),	/*Big White Sunglasses*/
('1022109', '3400', 'Eye'),	/*Pink Two-Toned Shades*/
('1022108', '3200', 'Eye'),	/*Yellow Two-Toned Shades*/
('1022104', '8800', 'Eye'),	/*3D Glasses*/
('1022102', '5000', 'Eye'),	/*LED Sunglasses*/
('1022122', '6400', 'Eye'),	/*6th B-Day Party Glasses*/
('1022121', '3400', 'Eye'),	/*Vision Google*/
/*Page 11*/
('1022142', '5000', 'Eye'),	/*Yellow Shutter Shades*/
('1022158', '5000', 'Eye'),	/*[MS Discount] Black Sunglasses*/
('1022174', '3600', 'Eye'),	/*3D Glasses*/
('1022173', '6400', 'Eye'),	/*Silky Black Eye Patch*/
('1022188', '4700', 'Eye'),	/*Blank Eye Patch*/
('1022187', '5600', 'Eye'),	/*Broken Up Today*/
('1022184', '8800', 'Eye'),	/*Frozen Eye*/
('1022183', '6000', 'Eye'),	/*Blazing Eyes*/
('1022177', '5200', 'Eye'),	/*Star Sunglasses*/
/*Page 12*/
('1022176', '7400', 'Eye'),	/*Cyclops Bandana*/
('1022207', '5200', 'Eye'),	/*PSY Sunglasses*/
('1022201', '6000', 'Eye'),	/*フューチャーロイド VR バイザー*/
('1022196', '3600', 'Eye'),	/*Money Lover*/
('1022194', '3600', 'Eye'),	/*Blaze Black Eye*/
('1022223', '5200', 'Eye'),	/*Romantic LED Sunglasses*/
('1022230', '6300', 'Eye'),	/*Bunny Glasses*/
('1022229', '3800', 'Eye'),	/*VIP Glasses*/
('1022227', '8800', 'Eye'),	/*Aviator Shades*/
/*Page 13*/
('1022250', '7400', 'Eye'),	/*투시안경*/
('1022249', '4700', 'Eye'),	/*스마트안경*/
('1022248', '7100', 'Eye'),	/*Pineapple Glasses*/
('1022247', '3400', 'Eye'),	/*Black Sunglasses*/
('1022244', '6300', 'Eye'),	/*Damien's Eyepatch*/
('1022243', '4900', 'Eye'),	/*Donut Glasses*/
('1022270', '4900', 'Eye'),	/*Oversized Sunglasses*/
('1022269', '3200', 'Eye'),	/*Chained Princess Face Accessory*/
('1022267', '5200', 'Eye'),	/*Personal Info Protection Stick*/
/*Page 14*/
('1022266', '5600', 'Eye'),	/*Strange Uncle Glasses*/
('1022265', '3400', 'Eye'),	/*Black Diamond*/
('1022263', '7100', 'Eye'),	/* Sleepy Eye Patch*/
('1022262', '5600', 'Eye'),	/*Scouter*/
('1022259', '6000', 'Eye'),	/*Bandage Blindfold*/
('1022258', '8800', 'Eye'),	/*Bat Wing Monocle*/
('1022257', '3200', 'Eye'),	/*Scouter*/
('1022285', '5600', 'Eye'),	/*Round Glasses*/
('1022284', '5000', 'Eye'),	/*Data Collection Goggles*/
/*Page 15*/
('1022283', '6000', 'Eye'),	/*Street Smart Sunglasses*/
('1022282', '4000', 'Eye'),	/*33 Glasses*/
('1022280', '3200', 'Eye'),	/*Heart Eye Patch*/
('1022279', '5200', 'Eye'),	/*Black Eye Bandages*/
('1022276', '3800', 'Eye'),	/*Banana Peel Specs*/
('1022275', '4900', 'Eye'),	/*Polar Explorer Sunglasses*/
('1022274', '4000', 'Eye'),	/*Purple Rose Eye Patch*/





/*  Earrings  */
/*Page 1*/
('1032029', '6400', 'Earrings'),	/*Silver Earrings*/
('1032024', '7400', 'Earrings'),	/*Transparent Earrings*/
('1032038', '4900', 'Earrings'),	/*Snow Earrings*/
('1032036', '5200', 'Earrings'),	/*Beaded Cross Earrings*/
('1032034', '4900', 'Earrings'),	/*Coke Earrings*/
('1032063', '5200', 'Earrings'),	/*Wireless Headset*/
('1032054', '8800', 'Earrings'),	/*Rainbow Earrings*/
('1032053', '6000', 'Earrings'),	/*Clover Earrings*/
('1032052', '6000', 'Earrings'),	/*Slime Earrings*/
/*Page 2*/
('1032051', '6000', 'Earrings'),	/*Diamond Earrings*/
('1032074', '4700', 'Earrings'),	/*Heart Rainbow Earrings*/
('1032073', '6300', 'Earrings'),	/*Wind Bell Earrings*/
('1032072', '3400', 'Earrings'),	/*Shiny Altair Earrings*/
('1032071', '3800', 'Earrings'),	/*Altair Earrings*/
('1032138', '3200', 'Earrings'),	/*Dragon Spirit Earrings*/
('1032145', '6000', 'Earrings'),	/*Crab Earrings*/
('1032175', '6300', 'Earrings'),	/*Faraway Earring*/
('1032204', '3200', 'Earrings'),	/*フューチャーロイドサイバーイヤリング*/
/*Page 3*/
('1032192', '6300', 'Earrings'),	/*Broken Up Today*/
('1032234', '3600', 'Earrings'),	/*Cold-hearted Earrings*/
('1032233', '5000', 'Earrings'),	/*Warm-hearted Earrings*/
('1032228', '7600', 'Earrings'),	/*Halloweenroid Sensor*/
('1032255', '7100', 'Earrings'),	/*White Earphones*/
('1032264', '7100', 'Earrings'),	/*Hydrangea Earrings*/
('1032262', '5200', 'Earrings'),	/*Umbral Earrings*/
('1032260', '5200', 'Earrings'),	/*Golden Bell Drops*/
('1032310', '6000', 'Earrings'),	/*EVA Android Hat*/
/*Page 4*/
('1033000', '4000', 'Earrings'),	/*Lucid's Earrings*/





/*  Overall  */
/*Page 1*/
('1050004', '7100', 'Overall'),	/*Blue Officer Uniform*/
('1050012', '3400', 'Overall'),	/*Grey Skull Overall*/
('1050013', '4000', 'Overall'),	/*Red Skull Overall*/
('1050014', '8800', 'Overall'),	/*Green Skull Overall*/
('1050015', '6400', 'Overall'),	/*Blue Skull Overall*/
('1050032', '5000', 'Overall'),	/*Silver Officer Uniform*/
('1050033', '5600', 'Overall'),	/*Black Officer Uniform*/
('1050034', '5200', 'Overall'),	/*Red Officer Uniform*/
('1050040', '3400', 'Overall'),	/*Red Swimming Trunk*/
/*Page 2*/
('1050041', '4900', 'Overall'),	/*Blue Swimming Trunk*/
('1050042', '3200', 'Overall'),	/*Fine Brown Hanbok*/
('1050043', '6300', 'Overall'),	/*Fine Black Hanbok*/
('1050044', '7600', 'Overall'),	/*Fine Blue Hanbok*/
('1050016', '8800', 'Overall'),	/*Orange Skull Overall*/
('1050017', '8800', 'Overall'),	/*Yellow Tights*/
('1050019', '7600', 'Overall'),	/*Santa Costume*/
('1050020', '8800', 'Overall'),	/*Paper Box*/
('1050065', '4900', 'Overall'),	/*Blue Celebration Hanbok*/
/*Page 3*/
('1050066', '5000', 'Overall'),	/*Green Celebration Hanbok*/
('1050071', '4900', 'Overall'),	/*Men's Ninja Overall*/
('1050079', '4700', 'Overall'),	/*Black Coat of Death*/
('1050050', '3200', 'Overall'),	/*Dark Suit*/
('1050057', '7600', 'Overall'),	/*Ghost Costume*/
('1050101', '6300', 'Overall'),	/*Western Cowboy*/
('1050109', '4700', 'Overall'),	/*Green Picnicwear*/
('1050110', '4000', 'Overall'),	/*Sky Blue Picnicwear*/
('1050111', '7400', 'Overall'),	/*Boxing Trunks*/
/*Page 4*/
('1050084', '5600', 'Overall'),	/*Red M-Forcer*/
('1050085', '5000', 'Overall'),	/*Blue M-Forcer*/
('1050086', '5200', 'Overall'),	/*Green M-Forcer*/
('1050087', '3400', 'Overall'),	/*Black M-Forcer*/
('1050128', '6400', 'Overall'),	/*Go! Korea!*/
('1050129', '4000', 'Overall'),	/*Korean Martial Art Uniform*/
('1050135', '3800', 'Overall'),	/*Beau Tuxedo*/
('1050136', '6300', 'Overall'),	/*Black Male Fur Coat*/
('1050137', '5600', 'Overall'),	/*White Male Fur Coat*/
/*Page 5*/
('1050138', '6400', 'Overall'),	/*School Uniform with Hoody Jumper*/
('1050139', '8800', 'Overall'),	/*Boys Uniform*/
('1050140', '3400', 'Overall'),	/*Thai Formal Dress*/
('1050141', '6000', 'Overall'),	/*Blue Kitty Hood*/
('1050142', '8800', 'Overall'),	/*Hooded Korean Traditional Costume*/
('1050143', '3600', 'Overall'),	/*Retro School Uniform*/
('1050112', '6400', 'Overall'),	/*Wedding Dress*/
('1050113', '5600', 'Overall'),	/*Wedding Tuxedo*/
('1050114', '6300', 'Overall'),	/*Poseidon Armor*/
/*Page 6*/
('1050115', '8800', 'Overall'),	/*Sea Hermit Robe*/
('1050116', '6400', 'Overall'),	/*Race Ace Suit*/
('1050117', '5600', 'Overall'),	/*Tiny Blue Swimshorts*/
('1050118', '7400', 'Overall'),	/*Tiny Black Swimshorts*/
('1050119', '6400', 'Overall'),	/*Santa Boy Overall*/
('1050120', '3600', 'Overall'),	/*Horoscope Overall*/
('1050121', '5200', 'Overall'),	/*Oriental Bridegroom Suit*/
('1050122', '3800', 'Overall'),	/*Unseemly Wedding Suit*/
('1050123', '5200', 'Overall'),	/*Royal Hanbok*/
/*Page 7*/
('1050124', '6300', 'Overall'),	/*Lunar Festivities Suit*/
('1050125', '7400', 'Overall'),	/*Brown Casual Look*/
('1050126', '4900', 'Overall'),	/*Imperial Uniform*/
('1050160', '4700', 'Overall'),	/*Nya-ong's Long Hood T-shirt*/
('1050161', '7100', 'Overall'),	/*Bunny Boy*/
('1050168', '7400', 'Overall'),	/*Evan Elegant Suit*/
('1050170', '3400', 'Overall'),	/*Napoleon Uniform*/
('1050171', '3400', 'Overall'),	/*Evan Outfit*/
('1050145', '7100', 'Overall'),	/*Violet Tunic*/
/*Page 8*/
('1050146', '6000', 'Overall'),	/*Buddy Overall Jeans*/
('1050147', '7100', 'Overall'),	/*Princess Korean Traditional Costume*/
('1050148', '6400', 'Overall'),	/*Shin-Hwa High Uniform*/
('1050152', '5600', 'Overall'),	/*Sailor Outfit*/
('1050153', '5200', 'Overall'),	/*Exotic Festival Outfit*/
('1050154', '5600', 'Overall'),	/*Seraphim Suit*/
('1050156', '3600', 'Overall'),	/*Blue Towel*/
('1050157', '4900', 'Overall'),	/*Cutie Boy Overall*/
('1050158', '6400', 'Overall'),	/*Brown Casual Look*/
/*Page 9*/
('1050159', '7400', 'Overall'),	/*Black Male Fur Coat*/
('1050193', '7400', 'Overall'),	/*Red Overall Pants*/
('1050177', '6000', 'Overall'),	/*Maple Boy School Uniform*/
('1050178', '5600', 'Overall'),	/*Napoleon Uniform*/
('1050179', '7600', 'Overall'),	/*Holiday Party Gear*/
('1050186', '7400', 'Overall'),	/*Rookie Maple Boy School Uniform*/
('1050187', '5000', 'Overall'),	/*Blue Snow Flower Wear*/
('1050188', '6000', 'Overall'),	/*Flower Heir Hanbok*/
('1050190', '8800', 'Overall'),	/*Military Pop Star*/
/*Page 10*/
('1050226', '3600', 'Overall'),	/*Imperial Garnet Suit*/
('1050227', '3800', 'Overall'),	/*Mint Snow Outfit*/
('1050228', '3200', 'Overall'),	/*Elven Spirit Coat*/
('1050229', '8800', 'Overall'),	/*Gentle Hanbok*/
('1050232', '8800', 'Overall'),	/*Chamomile Tea Time*/
('1050234', '5000', 'Overall'),	/*Magic Star Suit*/
('1050235', '5600', 'Overall'),	/*Prince Charming*/
('1050208', '3200', 'Overall'),	/*Schoolboy Formals*/
('1050209', '3800', 'Overall'),	/*Moonlight Serenade Get-Up*/
/*Page 11*/
('1050210', '5600', 'Overall'),	/*Light Cotton Candy Overalls*/
('1050215', '7100', 'Overall'),	/*Maple Doctor's Scrubs (M)*/
('1050220', '7400', 'Overall'),	/*Dark Force Mail (M) */
('1050221', '5200', 'Overall'),	/*Elven Spirit Coat (M) */
('1050256', '5600', 'Overall'),	/*Alps Boy Overall*/
('1050241', '7400', 'Overall'),	/*Jett's Outfit(M)*/
('1050242', '3800', 'Overall'),	/*Opening Star*/
('1050246', '4900', 'Overall'),	/*Saint Luminous*/
('1050247', '6400', 'Overall'),	/*Evergreen Magistrate Outfit*/
/*Page 12*/
('1050248', '6300', 'Overall'),	/*Halloween Leopard Costume*/
('1050255', '3600', 'Overall'),	/*Dark Force Suit*/
('1050291', '4900', 'Overall'),	/*Tinky Baseball*/
('1050292', '3200', 'Overall'),	/*Blue Swimming Trunks*/
('1050293', '3400', 'Overall'),	/*Beach Bum Outfit*/
('1050296', '5000', 'Overall'),	/*Superstar Suit*/
('1050297', '4700', 'Overall'),	/*Rising Star*/
('1050298', '6000', 'Overall'),	/*Alpha Seraphim*/
('1050299', '3800', 'Overall'),	/*Baby Doll Puka*/
/*Page 13*/
('1050300', '6400', 'Overall'),	/*Fresh Ice*/
('1050301', '7600', 'Overall'),	/*Blue Checkered Vacation*/
('1050302', '4900', 'Overall'),	/*Powder Butler's Digs (M)*/
('1050303', '8800', 'Overall'),	/*Ribbon Boy School Look*/
('1050283', '5600', 'Overall'),	/*Magic Star Suit*/
('1050284', '3800', 'Overall'),	/*Golden Bell Outfit*/
('1050285', '4700', 'Overall'),	/*Thermidor*/
('1050321', '5200', 'Overall'),	/*[[FROZEN CONTENT]] Kristoff Coat*/
('1050322', '3200', 'Overall'),	/*Party Prince*/
/*Page 14*/
('1050335', '5600', 'Overall'),	/*Melody Boy*/
('1050304', '3800', 'Overall'),	/*Splash Choco Boy*/
('1050305', '6400', 'Overall'),	/*Bloody Leo*/
('1050310', '3200', 'Overall'),	/*Shiny Shopper*/
('1050311', '3400', 'Overall'),	/*Moonlight Costume*/
('1050312', '4900', 'Overall'),	/*Grand Pony*/
('1050314', '7100', 'Overall'),	/*Plop! Chocolate Boy*/
('1050315', '6000', 'Overall'),	/*Blue Shiny Suit*/
('1050316', '6400', 'Overall'),	/*Balloon Overalls*/
/*Page 15*/
('1050318', '7100', 'Overall'),	/*White Proposal*/
('1050319', '6000', 'Overall'),	/*Sky Blue Picnic*/
('1050353', '5600', 'Overall'),	/*Sweet Patissier*/
('1050354', '5200', 'Overall'),	/*Blue Snow Flower Wear*/
('1050355', '4000', 'Overall'),	/*Santa Boy Overall*/
('1050356', '4900', 'Overall'),	/*Gothic Boys Uniform*/
('1050359', '6000', 'Overall'),	/*Cool Snow Flower*/
('1050360', '8800', 'Overall'),	/*Ryan D Suit*/
('1050361', '7100', 'Overall'),	/*Mr. Love Messenger Outfit*/
/*Page 16*/
('1050362', '3400', 'Overall'),	/*Little Trainer Outfit (M)*/
('1050364', '3400', 'Overall'),	/*Leafy Love Outfit*/
('1050365', '7400', 'Overall'),	/*Starlight Outfit*/
('1050366', '6400', 'Overall'),	/*The Kingdom Suit of King*/

('1050336', '7100', 'Overall'),	/*축구선수 유니폼*/
('1050337', '5200', 'Overall'),	/*Hawaiian Couple*/
('1050338', '4000', 'Overall'),	/*Maple Leaf High Swimsuit (M)*/
('1050339', '3400', 'Overall'),	/*Glowy Light*/
/*Page 17*/
('1050340', '5200', 'Overall'),	/*Gentle Ice Boy*/
('1050341', '5600', 'Overall'),	/*In-forest Camping Look*/
('1050343', '5000', 'Overall'),	/*Gentle Dylan*/
('1050346', '4900', 'Overall'),	/*Cheer Uniform*/
('1050351', '6300', 'Overall'),	/*Mad Doctor Gown*/
('1050384', '6000', 'Overall'),	/*Penguin Doll Outfit*/
('1050385', '3200', 'Overall'),	/*Time Getup*/
('1050386', '3600', 'Overall'),	/*Sparkling Bluebird (M)*/
('1050387', '3800', 'Overall'),	/*British Marine Outfit (M)*/
/*Page 18*/
('1050388', '4900', 'Overall'),	/*Ursus Gentleman's Suit*/
('1050389', '5000', 'Overall'),	/*Cutie Farmer Apron*/
('1050390', '7600', 'Overall'),	/*Polka-Dot Bell Bottoms*/

('1050392', '7400', 'Overall'),	/*Bloody Guardian*/
('1050393', '5200', 'Overall'),	/*Banana Outing Clothes*/
('1050394', '5200', 'Overall'),	/*Bubbly Traveler*/
('1050395', '4700', 'Overall'),	/*Blue Marine Uniform (M)*/

/*Page 19*/



('1050368', '5200', 'Overall'),	/*Romantic Sky*/
('1050370', '6300', 'Overall'),	/*Mint Kitty Tea Party*/
('1050371', '7100', 'Overall'),	/*Blue Crystal*/
('1050372', '5200', 'Overall'),	/*Mousy Bunny Trousers*/
('1050373', '3800', 'Overall'),	/*Tim Gentleman Suit*/
('1050375', '3200', 'Overall'),	/*Baby Doll Puka*/
/*Page 20*/
('1050376', '8800', 'Overall'),	/*Party Prince*/
('1050377', '3800', 'Overall'),	/*Tinky Baseball*/
('1050378', '6300', 'Overall'),	/*Yeonhwa School Uniform*/
('1050380', '4000', 'Overall'),	/*Kinesis Uniform*/
('1050381', '4700', 'Overall'),	/*Kinesis Uniform*/
('1050382', '3400', 'Overall'),	/*Millionaire Suit*/
('1050383', '4900', 'Overall'),	/*Teddy Suspenders*/
('1050416', '4000', 'Overall'),	/*Time Tailcoat*/
('1050417', '3400', 'Overall'),	/*Ice Deer Parka*/
/*Page 21*/
('1050418', '3400', 'Overall'),	/*Cozy Fur Outfit*/
('1050419', '4000', 'Overall'),	/*Winter Garden Outfit (M)*/


('1050422', '3400', 'Overall'),	/*Concert Muse (Tenor)*/
('1050423', '7400', 'Overall'),	/*Hopeful Spring Outing*/
('1050424', '6400', 'Overall'),	/*Dot Bon Bon*/
('1050426', '3200', 'Overall'),	/*Black & White*/
('1050427', '4900', 'Overall'),	/*Pure Dew*/
/*Page 22*/
('1050428', '7400', 'Overall'),	/*Pure Dew*/
('1050429', '5000', 'Overall'),	/*Rose Prince Suit*/
('1050430', '5000', 'Overall'),	/*Black Boy Scout*/
('1050431', '4900', 'Overall'),	/*Orchid Light Dana*/
('1050400', '7600', 'Overall'),	/*Nutcracker Uniform*/
('1050401', '3400', 'Overall'),	/*Time Master*/
('1050402', '3800', 'Overall'),	/*Evan Dragon Suit*/
('1050403', '3200', 'Overall'),	/*Evan Dragon Suit*/
('1050404', '5200', 'Overall'),	/*Royal Mercedes Suit*/
/*Page 23*/
('1050405', '4000', 'Overall'),	/*Royal Mercedes Suit*/
('1050406', '5600', 'Overall'),	/*Mystic Phantom Suit*/
('1050407', '3600', 'Overall'),	/*Mystic Phantom Suit*/
('1050408', '8800', 'Overall'),	/*Winter Aran Suit*/
('1050409', '3600', 'Overall'),	/*Winter Aran Suit*/
('1050410', '7400', 'Overall'),	/*Chiaroscuro Luminous Suit*/
('1050411', '3400', 'Overall'),	/*Chiaroscuro Luminous Suit*/
('1050412', '6000', 'Overall'),	/*Secret Shade Suit*/
('1050413', '7400', 'Overall'),	/*Secret Shade Suit*/
/*Page 24*/
('1050414', '4000', 'Overall'),	/*Cozy Bathrobe*/
('1050415', '5600', 'Overall'),	/*Santa Costume*/
('1050451', '8800', 'Overall'),	/*Manji's Uniform*/
('1050452', '4000', 'Overall'),	/*Moonlight Sorbet*/
('1050453', '3200', 'Overall'),	/*Sweet Choco Suit*/
('1050454', '3600', 'Overall'),	/*Skater Fanboy*/

('1050456', '7400', 'Overall'),	/*Cherry Trimmings*/
('1050461', '6000', 'Overall'),	/*Aqua Phoenix Robe*/
/*Page 25*/
('1050462', '4700', 'Overall'),	/*Spaceyman*/
('1050463', '3200', 'Overall'),	/*Preppy Sprout Suit*/
('1050432', '6000', 'Overall'),	/*King's Ice Trunks*/
('1050433', '8800', 'Overall'),	/*Grand Pony Get-up*/

('1050435', '6300', 'Overall'),	/*Midnight Suit*/
('1050436', '5600', 'Overall'),	/*Shadow Warrior's Tunic*/
('1050437', '4000', 'Overall'),	/*Lucid Dream*/
('1050438', '7600', 'Overall'),	/*Sweetheart Shirt*/
/*Page 26*/
('1050439', '5000', 'Overall'),	/*Orange Mushroom Overalls*/
('1050440', '3800', 'Overall'),	/*Green Pig Outfit*/
('1050441', '7400', 'Overall'),	/*Pumpkin Galaxy*/
('1050442', '5000', 'Overall'),	/*Sweet Chocolate Suspenders*/
('1050443', '3600', 'Overall'),	/*Warm Rabbit Fur Coat*/
('1050444', '7600', 'Overall'),	/*Star's Serenade*/
('1050445', '4900', 'Overall'),	/*King's Banquet*/
('1050446', '3400', 'Overall'),	/*King's Banquet*/
('1050447', '3400', 'Overall'),	/*Maple M Shirt and Pants*/
/*Page 27*/
('1050480', '6400', 'Overall'),	/*Santa Costume*/
('1050481', '5000', 'Overall'),	/*Snow Blossom Coat*/
('1050482', '3400', 'Overall'),	/*Oh My Captain*/
('1050484', '6400', 'Overall'),	/*Plum Blossom Robe*/
('1050485', '5600', 'Overall'),	/*Maple Blitzer Strategist Uniform*/
('1050486', '3800', 'Overall'),	/*Lemon Wafer Coat*/
('1050487', '7400', 'Overall'),	/*Carbon Wing Coat*/
('1050488', '3800', 'Overall'),	/*Little Darling Outfit*/
('1050489', '5000', 'Overall'),	/*Tennis Uniform*/
/*Page 28*/
('1050491', '7100', 'Overall'),	/*Candy Darling*/
('1050492', '4900', 'Overall'),	/*Clockwork Knight Ensemble*/
('1050493', '4900', 'Overall'),	/*Ballpark Buddy Uniform*/
('1050495', '7600', 'Overall'),	/*Spring Green Finery*/
('1050464', '4900', 'Overall'),	/*Sky Sea Polo Outfit (M)*/
('1050468', '3200', 'Overall'),	/*Teddy Land Outfit*/
('1050469', '3400', 'Overall'),	/*Fab Beachwear*/
('1050470', '5600', 'Overall'),	/*Midnight Moonlight Outfit*/
('1050471', '4700', 'Overall'),	/*Suit Heart Outfit*/
/*Page 29*/
('1050472', '8800', 'Overall'),	/*Crimson Fate Topcoat*/
('1050473', '3400', 'Overall'),	/*Crimson Fate Topcoat*/
('1050474', '7600', 'Overall'),	/*Tamino's Aria*/



/*Page 1*/
('1050475', '5600', 'Overall 2'),	/*Spooky Shorts*/
('1050476', '4900', 'Overall 2'),	/*Halloween Pumpkin Suit*/
('1050477', '3800', 'Overall 2'),	/*Soft Mist*/
('1050478', '7400', 'Overall 2'),	/*Iron Mace Officer*/
('1050479', '4000', 'Overall 2'),	/*Bunny Jersey*/
('1050512', '3400', 'Overall 2'),	/*Rudolf Hoody*/
('1050513', '7100', 'Overall 2'),	/*Rudolf Hoody*/
('1050514', '6300', 'Overall 2'),	/*Lavender Shearling Longcoat*/
('1050515', '7600', 'Overall 2'),	/*Piggy Pal Romper*/
/*Page 2*/
('1050516', '3800', 'Overall 2'),	/*Wish Fulfiller Uniform*/
('1050518', '5600', 'Overall 2'),	/*Springtime Sprout Outfit (M)*/
('1050519', '6000', 'Overall 2'),	/*Retro Swimsuit (M)*/
('1050496', '3200', 'Overall 2'),	/*Nautically Pink Dress*/
('1050497', '7100', 'Overall 2'),	/*Majestic Moonlight Attire*/
('1050498', '7400', 'Overall 2'),	/*Majestic Moonlight Attire*/
('1050499', '5200', 'Overall 2'),	/*Seafoam Coral Coordinates*/
('1050500', '5200', 'Overall 2'),	/*Scuba Savvy Suit*/
('1050502', '7600', 'Overall 2'),	/*Firefly Firelight Top*/
/*Page 3*/
('1050503', '5200', 'Overall 2'),	/*Shinsoo's Descent*/
('1050504', '7100', 'Overall 2'),	/*Lovely Plaid*/
('1050505', '6300', 'Overall 2'),	/*Necrotic*/
('1050506', '7100', 'Overall 2'),	/*Romantic Warrior*/
('1050507', '8800', 'Overall 2'),	/*Snowflake Pea Coat*/
('1050508', '7600', 'Overall 2'),	/*Forever Young Outfit*/
('1050509', '4900', 'Overall 2'),	/*Camelia Tea Livery*/
('1050510', '7600', 'Overall 2'),	/*Santa Boy Overall*/
('1050511', '5000', 'Overall 2'),	/*Santa Costume*/
/*Page 4*/
('1051002', '6400', 'Overall 2'),	/*Cat Suit*/
('1051028', '5000', 'Overall 2'),	/*White Swimming Suit*/
('1051029', '3800', 'Overall 2'),	/*Red Swimming Suit*/
('1051035', '4900', 'Overall 2'),	/*Fine Red Hanbok*/
('1051036', '4700', 'Overall 2'),	/*Fine Blue Hanbok*/
('1051018', '3800', 'Overall 2'),	/*Purple Skull Overall*/
('1051019', '7100', 'Overall 2'),	/*Orange Skull Overall*/
('1051020', '7100', 'Overall 2'),	/*Green Skull Overall*/
('1051021', '3200', 'Overall 2'),	/*Blue Skull Overall*/
/*Page 5*/
('1051022', '3400', 'Overall 2'),	/*Grey Skull Overall*/
('1051059', '5200', 'Overall 2'),	/*Pink Nurse Uniform*/
('1051060', '6300', 'Overall 2'),	/*White Nurse Uniform*/
('1051061', '7100', 'Overall 2'),	/*Women's Ninja Uniform*/
('1051070', '5200', 'Overall 2'),	/*Bunny Costume*/
('1051071', '4700', 'Overall 2'),	/*Pink Kimono*/
('1051040', '8800', 'Overall 2'),	/*Dark Enamel Suit*/
('1051048', '5600', 'Overall 2'),	/*Witch Clothes*/
('1051049', '8800', 'Overall 2'),	/*Mrs. Claus Costume*/
/*Page 6*/
('1051050', '8800', 'Overall 2'),	/*Blue Celeberation Hanbok*/
('1051051', '4900', 'Overall 2'),	/*Pink Celebration Hanbok*/
('1051088', '3400', 'Overall 2'),	/*Yellow M-Forcer*/
('1051089', '6300', 'Overall 2'),	/*Black M-Forcer*/
('1051099', '3400', 'Overall 2'),	/*Prep Uniform*/
('1051100', '7400', 'Overall 2'),	/*Western Cowgirl*/
('1051072', '3200', 'Overall 2'),	/*White Kimono*/
('1051073', '6000', 'Overall 2'),	/*Red Kimono*/
('1051074', '5200', 'Overall 2'),	/*Yellow Kimono*/
/*Page 7*/
('1051075', '4900', 'Overall 2'),	/*Blue Swimming Suit*/
('1051076', '5000', 'Overall 2'),	/*Ghost Suit*/
('1051081', '4900', 'Overall 2'),	/*Pink Kimono*/
('1051086', '3200', 'Overall 2'),	/*Ragged Hanbok*/
('1051087', '5000', 'Overall 2'),	/*Pink M-Forcer*/
('1051120', '4900', 'Overall 2'),	/*Flight Attendant Uniform*/
('1051121', '7100', 'Overall 2'),	/*Tropical Dress*/
('1051122', '5200', 'Overall 2'),	/*White Cat Costume*/
('1051123', '7400', 'Overall 2'),	/*Violet Strapless Dress*/
/*Page 8*/
('1051124', '5600', 'Overall 2'),	/*Purple Ring One Piece*/
('1051125', '3800', 'Overall 2'),	/*Black Cat Costume*/
('1051126', '3600', 'Overall 2'),	/*Red Chinese Dress*/
('1051127', '4900', 'Overall 2'),	/*Maid Uniform*/
('1051128', '3200', 'Overall 2'),	/*Horoscope Overall*/
('1051129', '6000', 'Overall 2'),	/*Oriental Princess Gown*/
('1051130', '5600', 'Overall 2'),	/*Unseemly Wedding Dress*/
('1051131', '3400', 'Overall 2'),	/*Santa Girl Overall*/
('1051132', '5200', 'Overall 2'),	/*White Coat*/
/*Page 9*/
('1051133', '4000', 'Overall 2'),	/*Rough Coat*/
('1051134', '5000', 'Overall 2'),	/*Leopard Print Coat*/
('1051135', '4700', 'Overall 2'),	/*Ruffled Coat*/
('1051108', '4700', 'Overall 2'),	/*Pink Picnic Dress*/
('1051109', '3600', 'Overall 2'),	/*Yellow Picnic Dress*/
('1051110', '8800', 'Overall 2'),	/*Purple Frill One Piece*/
('1051111', '7600', 'Overall 2'),	/*Blue Frill One Piece*/
('1051112', '3200', 'Overall 2'),	/*Boxing Gear*/
('1051113', '3200', 'Overall 2'),	/*Wedding Tuxedo (F)*/
/*Page 10*/
('1051114', '4900', 'Overall 2'),	/*Wedding Dress*/
('1051115', '6400', 'Overall 2'),	/*Sea Queen Dress*/
('1051116', '6000', 'Overall 2'),	/*Race Queen Uniform*/
('1051117', '4700', 'Overall 2'),	/*Diao Chan Dress*/
('1051118', '5600', 'Overall 2'),	/*Pink Strapless Bikini*/
('1051119', '5200', 'Overall 2'),	/*Blue Strapless Bikini*/
('1051154', '5000', 'Overall 2'),	/*Princess Isis*/
('1051155', '4700', 'Overall 2'),	/*Queen Mary*/
('1051156', '3600', 'Overall 2'),	/*Black Female Fur Coat*/
/*Page 11*/
('1051157', '3600', 'Overall 2'),	/*White Female Fur Coat*/
('1051158', '7400', 'Overall 2'),	/*School Uniform with Hoody Jumper*/
('1051159', '6000', 'Overall 2'),	/*Girls Uniform*/
('1051160', '6300', 'Overall 2'),	/*Pink-Striped Dress*/
('1051161', '6000', 'Overall 2'),	/*Thai Formal Dress*/
('1051162', '4000', 'Overall 2'),	/*Cute Sailor Dress*/
('1051163', '7100', 'Overall 2'),	/*Gothic Overall*/
('1051164', '3800', 'Overall 2'),	/*Kitty Hoodie*/
('1051166', '5200', 'Overall 2'),	/*Dressu Korean Traditional Costume*/
/*Page 12*/
('1051167', '3600', 'Overall 2'),	/*Black Rockabilly Dress*/
('1051136', '7600', 'Overall 2'),	/*Royal Palace Hanbok*/
('1051137', '5200', 'Overall 2'),	/*Rabbit Fur Dress*/
('1051138', '5600', 'Overall 2'),	/*Lunar Festivities Dress*/
('1051139', '6000', 'Overall 2'),	/*White Ribboned Sailor Dress*/
('1051141', '5200', 'Overall 2'),	/*Female Shaman Costume*/
('1051142', '5000', 'Overall 2'),	/*Vibrant Yellow Dress*/
('1051143', '3800', 'Overall 2'),	/*Race Queen Tank Top & Skirt*/
('1051144', '4900', 'Overall 2'),	/*Elegant Blue One Piece*/
/*Page 13*/
('1051145', '3800', 'Overall 2'),	/*Royal Maid Uniform*/
('1051146', '4700', 'Overall 2'),	/*Royal Nurse Uniform*/
('1051147', '4000', 'Overall 2'),	/*Street Cred Ensemble*/
('1051148', '6300', 'Overall 2'),	/*Navy Blue Au Luxe*/
('1051149', '4700', 'Overall 2'),	/*Princess Dress*/
('1051185', '3200', 'Overall 2'),	/*Maid Dress*/
('1051188', '4900', 'Overall 2'),	/*Blue Daisy Dress*/
('1051189', '3200', 'Overall 2'),	/*Yellow Anticipation*/
('1051190', '4900', 'Overall 2'),	/*Seraphim Suit*/
/*Page 14*/
('1051192', '7100', 'Overall 2'),	/*Blue Marine Girl*/
('1051193', '3600', 'Overall 2'),	/*Orange Towel*/
('1051195', '6000', 'Overall 2'),	/*Cutie Girl Overall*/
('1051196', '4000', 'Overall 2'),	/*Black Top Dress*/
('1051197', '3800', 'Overall 2'),	/*Nya-ong's Long Hood T-shirt*/
('1051198', '4000', 'Overall 2'),	/*Pink mini dress*/
('1051169', '6400', 'Overall 2'),	/*Sky Blue Picnicwear [F]*/
('1051170', '6400', 'Overall 2'),	/*Buddy Overall Jeans*/
('1051171', '4700', 'Overall 2'),	/*Royal Costume*/
/*Page 15*/
('1051173', '4900', 'Overall 2'),	/*Purple Dorothy Dress*/
('1051174', '5000', 'Overall 2'),	/*Bikini*/
('1051175', '4000', 'Overall 2'),	/*Strawberry Milk Dress*/
('1051176', '7400', 'Overall 2'),	/*Shin-Hwa High Uniform*/
('1051179', '5000', 'Overall 2'),	/*Pretty Girl*/
('1051180', '3400', 'Overall 2'),	/*Sailor Outfit*/
('1051182', '8800', 'Overall 2'),	/*Exotic Festival Outfit*/
('1051183', '5200', 'Overall 2'),	/*Night Fever Ensemble*/
('1051218', '4700', 'Overall 2'),	/*Maple Girl School Uniform*/
/*Page 16*/
('1051219', '8800', 'Overall 2'),	/*Rainbow Mini Dress*/
('1051220', '5200', 'Overall 2'),	/*Elizabeth Dress*/
('1051221', '6400', 'Overall 2'),	/*Holiday Party Dress*/
('1051227', '5600', 'Overall 2'),	/*Rookie Maple Girl School Uniform*/
('1051228', '3400', 'Overall 2'),	/*Pink Snow Flower Wear*/
('1051229', '5600', 'Overall 2'),	/*Flower Heiress Hanbok*/
('1051231', '3800', 'Overall 2'),	/*Alps Girl*/
('1051200', '4900', 'Overall 2'),	/*Bunny Girl*/
('1051206', '4700', 'Overall 2'),	/*Retro School Uniform*/
/*Page 17*/
('1051208', '5000', 'Overall 2'),	/*Strawberry Girl*/
('1051209', '3800', 'Overall 2'),	/*Evan Great Suit*/
('1051211', '8800', 'Overall 2'),	/*Elizabeth Dress*/
('1051212', '6000', 'Overall 2'),	/*Evan Outfit*/
('1051252', '7600', 'Overall 2'),	/*Pink Angel Uniform*/
('1051253', '7400', 'Overall 2'),	/*Little Red Riding Dress*/
('1051254', '3400', 'Overall 2'),	/*Schoolgirl Formals*/
('1051255', '6400', 'Overall 2'),	/*Golden Moonlight Dress*/
('1051256', '5000', 'Overall 2'),	/*Light Chiffon Dress*/
/*Page 18*/
('1051261', '8800', 'Overall 2'),	/*Marchen Fantasy*/
('1051262', '6300', 'Overall 2'),	/*Maple Doctor's Scrubs (F)*/
('1051232', '3600', 'Overall 2'),	/*Pink Shock Pop Star*/
('1051233', '3200', 'Overall 2'),	/*Taxi Costume*/
('1051235', '5200', 'Overall 2'),	/*Orange Checked Squares*/

('1051280', '4900', 'Overall 2'),	/*Gentle Hanbok*/
('1051282', '7100', 'Overall 2'),	/*Rosemary Tea*/
('1051284', '5000', 'Overall 2'),	/*Magic Star Dress*/
/*Page 19*/
('1051285', '7100', 'Overall 2'),	/*Princess Charming*/
('1051290', '3800', 'Overall 2'),	/*Jett's Outfit(F)*/
('1051292', '8800', 'Overall 2'),	/*Opening Angel*/
('1051294', '4000', 'Overall 2'),	/*Lyrical Dress*/
('1051295', '3600', 'Overall 2'),	/*Magical Dress*/
('1051264', '7600', 'Overall 2'),	/*Silver Angora Fur Dress*/
('1051265', '8800', 'Overall 2'),	/*Gold Angora Fur Dress*/
('1051270', '5000', 'Overall 2'),	/*Dark Force Mail (F) */
('1051271', '5000', 'Overall 2'),	/*Elven Spirit Coat (F) */
/*Page 20*/
('1051276', '7100', 'Overall 2'),	/*Imperial Garnet Suit*/
('1051277', '3200', 'Overall 2'),	/*Cygnus Dress*/
('1051278', '4000', 'Overall 2'),	/*Cherry Snow Outfit*/
('1051279', '6000', 'Overall 2'),	/*Elven Spirit Coat*/
('1051312', '5000', 'Overall 2'),	/*Alps Girl Dress*/
('1051296', '4000', 'Overall 2'),	/*Cynical Dress*/
('1051297', '4900', 'Overall 2'),	/*Little Red Riding Dress*/
('1051301', '6400', 'Overall 2'),	/*Saint Luminous*/
('1051302', '4700', 'Overall 2'),	/*Pink Fluffy Hanbok*/
/*Page 21*/
('1051304', '4000', 'Overall 2'),	/*Halloween Leopard Dress*/
('1051311', '3400', 'Overall 2'),	/*Dark Force Suit*/
('1051345', '3400', 'Overall 2'),	/*Fluffy Cat Outfit*/
('1051347', '8800', 'Overall 2'),	/*Magic Star Dress*/
('1051348', '3200', 'Overall 2'),	/*Ellinia Magic Academy Uniform*/
('1051349', '5200', 'Overall 2'),	/*Succubus Dress*/
('1051350', '6300', 'Overall 2'),	/*Golden Bell Dress*/
('1051351', '6000', 'Overall 2'),	/*GM Nori's Uniform*/
('1051352', '4900', 'Overall 2'),	/*Thermidor*/
/*Page 22*/
('1051357', '5000', 'Overall 2'),	/*Pinky Baseball*/
('1051358', '5000', 'Overall 2'),	/*Pink Cutie Bikini*/
('1051359', '7100', 'Overall 2'),	/*Beach Babe Outfit*/
('1051332', '5600', 'Overall 2'),	/*Logical Dress*/
('1051333', '6000', 'Overall 2'),	/*Miracle Dress*/
('1051376', '6000', 'Overall 2'),	/*Halloweenroid Dress*/
('1051382', '4900', 'Overall 2'),	/*Lovely Shopper*/
('1051383', '4700', 'Overall 2'),	/*Moonlight Outfit*/
('1051384', '6300', 'Overall 2'),	/*Glory Pony*/
/*Page 23*/
('1051385', '3400', 'Overall 2'),	/*Plop! Chocolate Girl*/
('1051386', '5600', 'Overall 2'),	/*Blue Shiny Dress*/
('1051387', '6400', 'Overall 2'),	/*Pink Picnic Dress*/
('1051389', '3400', 'Overall 2'),	/*White Fiancee*/
('1051390', '4700', 'Overall 2'),	/*Forsythia Picnic*/
('1051391', '3200', 'Overall 2'),	/*Icy Dress*/
('1051362', '6300', 'Overall 2'),	/*Superstar Dress*/
('1051363', '5000', 'Overall 2'),	/*Rising Angel*/
('1051365', '4900', 'Overall 2'),	/*Beta Seraphim*/
/*Page 24*/
('1051366', '7600', 'Overall 2'),	/*Baby Doll Linka*/
('1051367', '4000', 'Overall 2'),	/*Fresh Ice*/
('1051368', '4700', 'Overall 2'),	/*Fresh Checkered Vacation*/
('1051369', '3800', 'Overall 2'),	/*Powder Maid's Getup (F)*/
('1051370', '5600', 'Overall 2'),	/*Passionate Qi Pao*/
('1051371', '3200', 'Overall 2'),	/*Ribbon Girl School Look*/
('1051372', '6300', 'Overall 2'),	/*Splash Choco Girl*/
('1051373', '4700', 'Overall 2'),	/*Bloody Jeanne*/
('1051374', '4900', 'Overall 2'),	/*Odette Tutu*/
/*Page 25*/
('1051375', '5600', 'Overall 2'),	/*Odile Tutu*/
('1051408', '7100', 'Overall 2'),	/*Shiny Light*/
('1051409', '6000', 'Overall 2'),	/*Gentle Ice Girl*/
('1051410', '7100', 'Overall 2'),	/*In-forest Camping Look*/
('1051411', '4700', 'Overall 2'),	/*Lady Rosalia*/
('1051415', '7100', 'Overall 2'),	/*Cheer Uniform*/
('1051420', '8800', 'Overall 2'),	/*Ribbon Angel Uniform*/
('1051422', '6000', 'Overall 2'),	/*Sweet Patissiere*/
('1051423', '7100', 'Overall 2'),	/*Pink Snow Flower Wear*/
/*Page 26*/
('1051392', '6300', 'Overall 2'),	/*Party Princess*/
('1051405', '6000', 'Overall 2'),	/*Melody Girl*/
('1051406', '7600', 'Overall 2'),	/*Hawaiian Couple*/
('1051407', '6400', 'Overall 2'),	/*Maple Leaf High Swimsuit (F)*/
('1051440', '3200', 'Overall 2'),	/*Pink Sapphire*/
('1051441', '3800', 'Overall 2'),	/*Mousy Bunny Romper*/
('1051442', '5000', 'Overall 2'),	/*Momo Maid Dress*/
('1051444', '7100', 'Overall 2'),	/*Baby Doll Linka*/
('1051445', '4000', 'Overall 2'),	/*Party Princess*/
/*Page 27*/
('1051446', '4700', 'Overall 2'),	/*Pinky Baseball*/
('1051447', '3400', 'Overall 2'),	/*Odette Tutu*/
('1051448', '7600', 'Overall 2'),	/*Yeonhwa School Uniform*/
('1051450', '3600', 'Overall 2'),	/*Kinesis Uniform*/
('1051451', '3800', 'Overall 2'),	/*Kinesis Uniform*/
('1051452', '6000', 'Overall 2'),	/*Orange Day*/
('1051453', '6300', 'Overall 2'),	/*Teddy Suspenders*/
('1051454', '8800', 'Overall 2'),	/*Penguin Doll Outfit*/
('1051455', '5000', 'Overall 2'),	/*Time Cantabile*/
/*Page 28*/
('1051424', '6300', 'Overall 2'),	/*Santa Girl Overall*/
('1051425', '4700', 'Overall 2'),	/*Big Ribbon Yellow Dress*/
('1051426', '4700', 'Overall 2'),	/*Gothic Girls Uniform*/
('1051429', '6000', 'Overall 2'),	/*Sweet Snow Flower*/
('1051430', '8800', 'Overall 2'),	/*Sierra Grace Dress*/
('1051431', '7600', 'Overall 2'),	/*Ms. Love Messenger Outfit*/
('1051432', '5200', 'Overall 2'),	/*Little Trainer Outfit (F)*/
('1051434', '3200', 'Overall 2'),	/*Blooming Leafy Love Outfit*/
('1051435', '3200', 'Overall 2'),	/*Starlight Outfit*/
/*Page 29*/
('1051436', '4700', 'Overall 2'),	/*The Kingdom Dress of Queen*/
('1051437', '4900', 'Overall 2'),	/*Pink Romance*/
('1051439', '5000', 'Overall 2'),	/*Mint Kitty Tea Party*/



/*Page 1*/
('1051472', '7100', 'Overall 3'),	/*Evan Dragon Suit*/
('1051473', '7100', 'Overall 3'),	/*Royal Mercedes Suit*/
('1051474', '5200', 'Overall 3'),	/*Royal Mercedes Suit*/
('1051475', '5600', 'Overall 3'),	/*Mystic Phantom Suit*/
('1051476', '7400', 'Overall 3'),	/*Mystic Phantom Suit*/
('1051477', '6000', 'Overall 3'),	/*Winter Aran Suit*/
('1051478', '3200', 'Overall 3'),	/*Winter Aran Suit*/
('1051479', '7600', 'Overall 3'),	/*Chiaroscuro Luminous Suit*/
('1051480', '3800', 'Overall 3'),	/*Chiaroscuro Luminous Suit*/
/*Page 2*/
('1051481', '7100', 'Overall 3'),	/*Secret Shade Suit*/
('1051482', '7100', 'Overall 3'),	/*Secret Shade Suit*/
('1051483', '4900', 'Overall 3'),	/*Frilly Bathrobe*/
('1051484', '6000', 'Overall 3'),	/*Santa Costume*/
('1051485', '4900', 'Overall 3'),	/*Time Cantabile*/
('1051486', '4900', 'Overall 3'),	/*Snow Deer Parka*/
('1051487', '5600', 'Overall 3'),	/*Cozy Fur Outfit*/
('1051456', '3800', 'Overall 3'),	/*Sparkling Bluebird (F)*/
('1051457', '3200', 'Overall 3'),	/*British Marine Outfit (F)*/
/*Page 3*/
('1051458', '4900', 'Overall 3'),	/*Ursus Lady's Suit*/
('1051459', '5600', 'Overall 3'),	/*Pure Farmer One-piece*/
('1051460', '5200', 'Overall 3'),	/*Polka Dot Dress*/
('1051461', '3400', 'Overall 3'),	/*Giant Ribbon Outfit (F)*/

('1051463', '6400', 'Overall 3'),	/*Bloody Bride*/
('1051464', '3800', 'Overall 3'),	/*Banana Outing Clothes*/
('1051465', '8800', 'Overall 3'),	/*Shy Traveler*/
('1051466', '7100', 'Overall 3'),	/*Blue Marine Uniform (F)*/
/*Page 4*/


('1051469', '3200', 'Overall 3'),	/*Nutcracker Uniform*/
('1051470', '8800', 'Overall 3'),	/*Time Mistress*/
('1051471', '4700', 'Overall 3'),	/*Evan Dragon Suit*/
('1051504', '4700', 'Overall 3'),	/*Lucid Dream*/
('1051505', '7100', 'Overall 3'),	/*Shadow Warrior's Tunic*/
('1051506', '3400', 'Overall 3'),	/*Sweetheart Dress*/
('1051507', '7600', 'Overall 3'),	/*Orange Mushroom Skirt*/
/*Page 5*/
('1051508', '3800', 'Overall 3'),	/*Purple Porker Skirt*/
('1051509', '3600', 'Overall 3'),	/*Pumpkin Milky Way*/
('1051510', '4700', 'Overall 3'),	/*Sweet Chocolate Dress*/
('1051511', '3800', 'Overall 3'),	/*Warm Rabbit Fur Coat*/
('1051512', '7400', 'Overall 3'),	/*Star's Serenade*/
('1051513', '4700', 'Overall 3'),	/*Queen's Evening Party*/
('1051514', '3800', 'Overall 3'),	/*Queen's Evening Party*/
('1051515', '7400', 'Overall 3'),	/*Maple M Dress*/
('1051519', '8800', 'Overall 3'),	/*Moonlight Sherbet*/
/*Page 6*/
('1051488', '3400', 'Overall 3'),	/*Winter Garden Outfit (F)*/

('1051490', '7600', 'Overall 3'),	/*Concert Muse (Soprano)*/
('1051491', '6400', 'Overall 3'),	/*Fragrant Spring Outing*/
('1051492', '4900', 'Overall 3'),	/*Dot Berry*/
('1051494', '6400', 'Overall 3'),	/*Lady Black*/
('1051495', '5000', 'Overall 3'),	/*Crystal Dew*/
('1051496', '7100', 'Overall 3'),	/*Crystal Dew*/
('1051497', '7100', 'Overall 3'),	/*Rose Princess Gown*/
/*Page 7*/
('1051498', '5600', 'Overall 3'),	/*Black Girl Scout*/
('1051499', '8800', 'Overall 3'),	/*Orchid Light Hanga*/
('1051500', '6000', 'Overall 3'),	/*Queen Mary*/
('1051501', '8800', 'Overall 3'),	/*Grand Pony Get-up*/

('1051503', '7100', 'Overall 3'),	/*Pitch Dark Poem*/
('1051536', '3400', 'Overall 3'),	/*Fab Beachwear*/
('1051537', '4900', 'Overall 3'),	/*Midnight Moonlight Outfit*/
('1051538', '7600', 'Overall 3'),	/*Suit Heart Outfit*/
/*Page 8*/
('1051539', '5200', 'Overall 3'),	/*Crimson Fate Topcoat*/
('1051540', '7100', 'Overall 3'),	/*Crimson Fate Topcoat*/
('1051541', '7400', 'Overall 3'),	/*Pamina's Aria*/
('1051542', '3800', 'Overall 3'),	/*Spooky Skirt*/
('1051543', '7600', 'Overall 3'),	/*Halloween Pumpkin Suit*/
('1051544', '5200', 'Overall 3'),	/*Soft Blushed*/
('1051545', '6000', 'Overall 3'),	/*Iron Mace Officer*/
('1051546', '5000', 'Overall 3'),	/*Bunny Jersey*/
('1051547', '5200', 'Overall 3'),	/*Santa Costume*/
/*Page 9*/
('1051548', '5000', 'Overall 3'),	/*Snow Blossom Coat*/
('1051549', '6300', 'Overall 3'),	/*Oh My Captain*/
('1051551', '3800', 'Overall 3'),	/*Plum Blossom Dress*/
('1051520', '8800', 'Overall 3'),	/*Soft Cream Dress*/
('1051521', '6400', 'Overall 3'),	/*Skater Fangirl*/

('1051523', '6000', 'Overall 3'),	/*Cherry Glaze*/
('1051528', '8800', 'Overall 3'),	/*Aqua Phoenix Dress*/
('1051529', '6000', 'Overall 3'),	/*Spaceywoman*/
/*Page 10*/
('1051530', '6000', 'Overall 3'),	/*Preppy Sprout Garb*/
('1051531', '3800', 'Overall 3'),	/*Cloud Sea Outfit (F)*/
('1051535', '7600', 'Overall 3'),	/*Teddy Land Outfit*/
('1051568', '4000', 'Overall 3'),	/*Scuba Savvy Suit*/
('1051572', '4900', 'Overall 3'),	/*Firefly Firelight Top*/
('1051573', '3400', 'Overall 3'),	/*Audience with the Empress*/
('1051574', '6300', 'Overall 3'),	/*Lovely Plaid*/
('1051575', '8800', 'Overall 3'),	/*Necrotic*/
('1051576', '4700', 'Overall 3'),	/*Romantic Warrior*/
/*Page 11*/
('1051577', '7400', 'Overall 3'),	/*Snowflake Pea Coat*/
('1051578', '3200', 'Overall 3'),	/*Forever Young Dress*/
('1051579', '3800', 'Overall 3'),	/*Camelia Tea Dress*/
('1051580', '3800', 'Overall 3'),	/*Santa Girl Overall*/
('1051581', '3600', 'Overall 3'),	/*Santa Costume*/
('1051582', '3400', 'Overall 3'),	/*Rudolf Hoody*/
('1051583', '4000', 'Overall 3'),	/*Rudolf Hoody*/
('1051552', '6400', 'Overall 3'),	/*Maple Blitzer Strategist Uniform*/
('1051553', '4000', 'Overall 3'),	/*Lemon Wafer Coat*/
/*Page 12*/
('1051554', '3400', 'Overall 3'),	/*Carbon Wing Coat*/
('1051555', '6000', 'Overall 3'),	/*Little Darling Outfit*/
('1051556', '5200', 'Overall 3'),	/*Tennis Uniform*/
('1051559', '3400', 'Overall 3'),	/*Candy Darling*/
('1051560', '3200', 'Overall 3'),	/*Clockwork Knight Ensemble*/
('1051561', '4900', 'Overall 3'),	/*Ballpark Buddy Uniform*/
('1051563', '7600', 'Overall 3'),	/*Spring Green Finery*/
('1051564', '3200', 'Overall 3'),	/*Nautically Pink Dress*/
('1051565', '4700', 'Overall 3'),	/*Shimmering Starlight Attire*/
/*Page 13*/
('1051566', '3800', 'Overall 3'),	/*Shimmering Starlight Attire*/
('1051567', '4700', 'Overall 3'),	/*Seafoam Coral Coordinates*/
('1051584', '4900', 'Overall 3'),	/*Lavender Shearling Longcoat*/
('1051585', '5200', 'Overall 3'),	/*Piggy Pal Romper*/
('1051586', '3400', 'Overall 3'),	/*Wish Fulfiller Uniform*/
('1051588', '5200', 'Overall 3'),	/*Retro Swimsuit (F)*/
('1051591', '4000', 'Overall 3'),	/*Springtime Sprout Outfit (F)*/
('1052016', '5200', 'Overall 3'),	/*Brown Shinsengumi Uniform*/
('1052017', '3400', 'Overall 3'),	/*Orange Life-Jacket*/
/*Page 14*/
('1052018', '8800', 'Overall 3'),	/*Green Life-Jacket*/
('1052019', '5600', 'Overall 3'),	/*Blue Life-Jacket*/
('1052020', '7100', 'Overall 3'),	/*White Body Tights*/
('1052021', '4000', 'Overall 3'),	/*Black Body Tights*/
('1052022', '3200', 'Overall 3'),	/*White Holed Tights*/
('1052023', '6300', 'Overall 3'),	/*Black Holed Tights*/
('1052024', '6000', 'Overall 3'),	/*Big Kimono*/
('1052025', '3800', 'Overall 3'),	/*Denim Overall*/
('1052026', '6300', 'Overall 3'),	/*Grey Full Coat*/
/*Page 15*/
('1052027', '7400', 'Overall 3'),	/*Red Full Coat*/
('1052028', '7400', 'Overall 3'),	/*Forest Samurai Outfit*/
('1052029', '7600', 'Overall 3'),	/*Premium Trenchcoat*/
('1052030', '6000', 'Overall 3'),	/*Toga*/
('1052031', '6300', 'Overall 3'),	/*Reindeer Suit*/
('1052000', '6000', 'Overall 3'),	/*Recycled Box*/
('1052001', '5000', 'Overall 3'),	/*Paper Box*/
('1052002', '4000', 'Overall 3'),	/*Cardboard Box*/
('1052003', '5000', 'Overall 3'),	/*Blue Chinese Undead Costume*/
/*Page 16*/
('1052004', '5000', 'Overall 3'),	/*Maroon Chinese Undead Costume*/
('1052005', '5600', 'Overall 3'),	/*Yellow Raincoat*/
('1052006', '3400', 'Overall 3'),	/*Sky Blue Raincoat*/
('1052007', '7100', 'Overall 3'),	/*Red Raincoat*/
('1052008', '6300', 'Overall 3'),	/*Green Raincoat*/
('1052009', '8800', 'Overall 3'),	/*Orange Overall*/
('1052010', '7600', 'Overall 3'),	/*Pink Overall*/
('1052011', '5200', 'Overall 3'),	/*Blue Overall*/
('1052012', '6300', 'Overall 3'),	/*Green Overall*/
/*Page 17*/
('1052013', '3600', 'Overall 3'),	/*Graduation Gown*/
('1052014', '4700', 'Overall 3'),	/*Ducky Costume*/
('1052015', '5200', 'Overall 3'),	/*Blue Shinsengumi Uniform*/
('1052048', '5000', 'Overall 3'),	/*Brown Snowboard Overall*/
('1052049', '3600', 'Overall 3'),	/*Yang In*/
('1052050', '5600', 'Overall 3'),	/*Red Hip Hop*/
('1052051', '7100', 'Overall 3'),	/*Blue Hip Hop*/
('1052052', '4000', 'Overall 3'),	/*Musashi Costume*/
('1052053', '3800', 'Overall 3'),	/*Teddy Bear Costume*/
/*Page 18*/
('1052054', '7100', 'Overall 3'),	/*Welder Look*/
('1052055', '5000', 'Overall 3'),	/*Enamer*/
('1052056', '3400', 'Overall 3'),	/*Soccer Uniform*/
('1052057', '5600', 'Overall 3'),	/*Soccer Uniform (No.7)*/
('1052058', '4000', 'Overall 3'),	/*Soccer Uniform (No.10)*/
('1052059', '3600', 'Overall 3'),	/*Soccer Uniform (No.14)*/
('1052060', '3600', 'Overall 3'),	/*England Soccer Uniform(No.8)*/
('1052061', '3800', 'Overall 3'),	/*Brazil Soccer Uniform(No.9)*/
('1052062', '4700', 'Overall 3'),	/*France Soccer Uniform(No.10)*/
/*Page 19*/
('1052063', '6000', 'Overall 3'),	/*USA Soccer Uniform(No.17)*/
('1052032', '8800', 'Overall 3'),	/*Red Bruma*/
('1052033', '3600', 'Overall 3'),	/*Green Bruma*/
('1052034', '3600', 'Overall 3'),	/*Blue Bruma*/
('1052035', '3600', 'Overall 3'),	/*Guan Yu Armor*/
('1052036', '3800', 'Overall 3'),	/*Zhu-Ge-Liang Gown*/
('1052037', '6000', 'Overall 3'),	/*Patissier Uniform*/
('1052038', '8800', 'Overall 3'),	/*Blue Robot Pilotgear*/
('1052039', '7100', 'Overall 3'),	/*Liu Bei Robe*/
/*Page 20*/
('1052040', '3800', 'Overall 3'),	/*Cao Cao Robe*/
('1052041', '3600', 'Overall 3'),	/*Sun Quan Robe*/
('1052042', '6000', 'Overall 3'),	/*Pink Robot Pilotgear*/
('1052043', '3400', 'Overall 3'),	/*Hip Hop Sweats*/
('1052044', '7600', 'Overall 3'),	/*Scuba Diving Suit*/
('1052045', '3400', 'Overall 3'),	/*Mink Coat*/
('1052046', '7100', 'Overall 3'),	/*Snowman Costume*/
('1052047', '4700', 'Overall 3'),	/*Black Snowboard Overall*/
('1052082', '4700', 'Overall 3'),	/*Elf Overall*/
/*Page 21*/
('1052083', '4000', 'Overall 3'),	/*Sun Wukong Robe*/
('1052084', '8800', 'Overall 3'),	/*Golden Armor*/
('1052085', '6400', 'Overall 3'),	/*Red Amorian Apron*/
('1052086', '6300', 'Overall 3'),	/*Blue Amorian Apron*/
('1052087', '6000', 'Overall 3'),	/*Dark Blue Robes*/
('1052089', '6400', 'Overall 3'),	/*Black Overcoat of Doom*/
('1052090', '7100', 'Overall 3'),	/*Rompers*/
('1052091', '8800', 'Overall 3'),	/*Sachiel Armor*/
('1052092', '8800', 'Overall 3'),	/*Veamoth Armor*/
/*Page 22*/
('1052093', '4900', 'Overall 3'),	/*Janus Armor*/
('1052094', '4000', 'Overall 3'),	/*Zhu Ba Jie Overall*/
('1052064', '4700', 'Overall 3'),	/*Soccer Uniform(No.4)*/
('1052065', '7100', 'Overall 3'),	/*Soccer Uniform(No.21)*/
('1052066', '3400', 'Overall 3'),	/*Mexico Soccer Uniform(No.9)*/
('1052067', '4900', 'Overall 3'),	/*Mummy Suit*/
('1052068', '3800', 'Overall 3'),	/*Skull Suit*/
('1052069', '3600', 'Overall 3'),	/*Flamboyant Autumn Gear*/
('1052070', '6300', 'Overall 3'),	/*Golden Armor*/
/*Page 23*/
('1052073', '7600', 'Overall 3'),	/*White Rabbit Suit*/
('1052074', '8800', 'Overall 3'),	/*Nero Bell Outfit*/
('1052077', '6400', 'Overall 3'),	/*Moon Bunny Costume*/
('1052078', '3400', 'Overall 3'),	/*Soap Bubble Bonanza*/
('1052079', '5200', 'Overall 3'),	/*Prince of Darkness*/
('1052144', '7400', 'Overall 3'),	/*Luxurious Padded Coat*/
('1052145', '7400', 'Overall 3'),	/*Christmas Party Suit*/
('1052147', '5000', 'Overall 3'),	/*Chinese Lion Costume*/
('1052151', '6300', 'Overall 3'),	/*Bosshunter Armor*/
/*Page 24*/
('1052152', '6000', 'Overall 3'),	/*Bosshunter Gi*/
('1052153', '4700', 'Overall 3'),	/*Red Viska for Transformation*/
('1052154', '3200', 'Overall 3'),	/*Tiger Cub Costume*/
('1052135', '4900', 'Overall 3'),	/*Centaurus Legs*/
('1052136', '3800', 'Overall 3'),	/*2nd Anniversary Mushroom Suit*/
('1052137', '4900', 'Overall 3'),	/*Tomato Suit*/
('1052142', '7600', 'Overall 3'),	/*Shorts with Suspenders*/
('1052143', '3600', 'Overall 3'),	/*Sky Blue Padded Coat*/
('1052176', '6400', 'Overall 3'),	/*Fashionable Checkerwear*/
/*Page 25*/
('1052178', '7400', 'Overall 3'),	/*Snowflake Knit*/
('1052179', '3800', 'Overall 3'),	/*Cow Costume*/
('1052180', '6000', 'Overall 3'),	/*Denim Overalls*/
('1052182', '7100', 'Overall 3'),	/*Galactic Hero Suit*/
('1052183', '3600', 'Overall 3'),	/*Stealth Suit*/

('1052168', '3800', 'Overall 3'),	/*Cutie Birk Outfit*/
('1052169', '5600', 'Overall 3'),	/*Gaga Suit*/
('1052170', '7100', 'Overall 3'),	/*Noob Overall*/
/*Page 26*/
('1052171', '3400', 'Overall 3'),	/*Baby Chick Apron*/
('1052172', '7600', 'Overall 3'),	/*Pumpkin Suit*/
('1052174', '4900', 'Overall 3'),	/*Fox Outfit*/
('1052175', '3800', 'Overall 3'),	/*Coastal Winter Wear*/
('1052209', '7600', 'Overall 3'),	/*Royal Navy Uniform*/
('1052210', '6000', 'Overall 3'),	/*Alchemist Overall*/
('1052211', '6000', 'Overall 3'),	/*Fire Shadow Suit*/
('1052212', '5600', 'Overall 3'),	/*Cherry Blossom Suit*/
('1052213', '6300', 'Overall 3'),	/*Chaos Armor*/
/*Page 27*/
('1052214', '6000', 'Overall 3'),	/*Maple Racing Suit*/
('1052218', '7600', 'Overall 3'),	/*Clown Suit*/
('1052192', '5000', 'Overall 3'),	/*Bombacha*/
('1052193', '5600', 'Overall 3'),	/*Honeybee Costume*/
('1052194', '3600', 'Overall 3'),	/*Ugabuga*/
('1052195', '4700', 'Overall 3'),	/*Aran Armor*/
('1052196', '8800', 'Overall 3'),	/*Aran Armor*/
('1052197', '3800', 'Overall 3'),	/*Brave Soldier Armor*/
('1052198', '7100', 'Overall 3'),	/*Pink Bean Suit*/
/*Page 28*/
('1052199', '6400', 'Overall 3'),	/*Blade Overall*/
('1052200', '7100', 'Overall 3'),	/*Lolli Pink Suit*/
('1052201', '5000', 'Overall 3'),	/*Shiny Sailor Uniform*/
('1052203', '7600', 'Overall 3'),	/*One Summer Night */
('1052204', '7400', 'Overall 3'),	/*Marine Girl Dress*/
('1052205', '6300', 'Overall 3'),	/*Pluto Hero Suit*/
('1052206', '7400', 'Overall 3'),	/*Gaga Suit*/
('1052207', '3400', 'Overall 3'),	/*Cursed Golden Armor*/
('1052245', '6400', 'Overall 3'),	/*Green Leaf Overall*/
/*Page 29*/
('1052246', '6000', 'Overall 3'),	/*Cat Set Suit*/
('1052248', '4900', 'Overall 3'),	/*Dual Blade Suit*/
('1052253', '5000', 'Overall 3'),	/*Green Overall Shorts*/



/*Page 1*/
('1052255', '6300', 'Overall 4'),	/*Hawkeye Captain Suit*/
('1052224', '4700', 'Overall 4'),	/*Strawberry Baby*/
('1052225', '3800', 'Overall 4'),	/*Lolita Butterfly Dress*/
('1052228', '3800', 'Overall 4'),	/*Layered Long Skull Tee*/
('1052229', '4900', 'Overall 4'),	/*Qi-pao Dress*/
('1052231', '7600', 'Overall 4'),	/*Little Prince*/
('1052232', '3800', 'Overall 4'),	/*Pink Fur Ribbon Dress*/
('1052233', '5600', 'Overall 4'),	/*White Fur Ribbon Dress*/
('1052234', '7100', 'Overall 4'),	/*Stylish Layered Plaid*/
/*Page 2*/
('1052236', '6000', 'Overall 4'),	/*Petite School Shawl*/
('1052275', '6400', 'Overall 4'),	/*Royal Rainbow Zip-Up Jacket*/
('1052282', '3800', 'Overall 4'),	/*Oz Magic Robe*/
('1052283', '3400', 'Overall 4'),	/*Henesys Academy Uniform (with skirt)*/
('1052284', '3200', 'Overall 4'),	/*Henesys Academy Uniform (with pants)*/
('1052286', '7100', 'Overall 4'),	/*Pilot Suit*/
('1052268', '5000', 'Overall 4'),	/*Violet Tunic*/
('1052306', '6300', 'Overall 4'),	/*Japanesque Dress*/
('1052309', '3600', 'Overall 4'),	/*Trench Coat*/
/*Page 3*/
('1052289', '6400', 'Overall 4'),	/*Wild Hunter Suit*/
('1052290', '6400', 'Overall 4'),	/*Battle Mage Suit*/
('1052291', '4900', 'Overall 4'),	/*Hooded Track Suit*/
('1052292', '5000', 'Overall 4'),	/*King Crow Suit*/
('1052293', '7400', 'Overall 4'),	/*Sanctus Combat Dress*/
('1052294', '8800', 'Overall 4'),	/*Sanctus Combat Suit*/
('1052295', '8800', 'Overall 4'),	/*Maid Dress (Pink)*/
('1052296', '7600', 'Overall 4'),	/*Maid Dress (Blue)*/
('1052298', '5000', 'Overall 4'),	/*Rabbit Ear Dress*/
/*Page 4*/
('1052338', '4000', 'Overall 4'),	/*Red's Dress*/
('1052339', '6300', 'Overall 4'),	/*Lab Gear (F)*/
('1052340', '4900', 'Overall 4'),	/*Lab Gear (M)*/

('1052345', '3200', 'Overall 4'),	/*Winter 2010 Moon Bunny Outfit*/
('1052348', '6300', 'Overall 4'),	/*Commander Captain*/
('1052349', '7600', 'Overall 4'),	/*Belt Coat*/
('1052324', '6300', 'Overall 4'),	/*Paypal Suit*/
('1052329', '3400', 'Overall 4'),	/*Pitch Dark Outfit*/
/*Page 5*/
('1052330', '6400', 'Overall 4'),	/*Blue Mage Gear*/
('1052331', '8800', 'Overall 4'),	/*Red Mage Gear*/
('1052332', '6400', 'Overall 4'),	/*Christmas Casual Outfit*/
('1052368', '7100', 'Overall 4'),	/*Starling Suit*/
('1052369', '6300', 'Overall 4'),	/* MSE 4 Years & Unstoppable Overall*/
('1052370', '5200', 'Overall 4'),	/*Victorian Vampire Suit*/
('1052372', '6000', 'Overall 4'),	/*Blue Arabian Outfit*/
('1052373', '3400', 'Overall 4'),	/*Red Arabian Outfit*/
('1052354', '5000', 'Overall 4'),	/*Rising Star Baggy Digs*/
/*Page 6*/
('1052355', '3600', 'Overall 4'),	/*Rookie Yellow Raincoat*/
('1052356', '7400', 'Overall 4'),	/*Military Pop Star*/
('1052367', '4900', 'Overall 4'),	/*Crow Suit*/
('1052408', '3400', 'Overall 4'),	/*Kerning Engineering School Uniform*/
('1052410', '7400', 'Overall 4'),	/*Ribboned Justice Dress*/
('1052411', '4700', 'Overall 4'),	/*Alchemist Overall*/
('1052412', '6300', 'Overall 4'),	/*Toy Prince*/
('1052435', '7100', 'Overall 4'),	/*Princess Hakama*/
('1052438', '5000', 'Overall 4'),	/*Ganache Chocolate Suit*/
/*Page 7*/
('1052439', '4700', 'Overall 4'),	/*Ellinia Magic School Uniform*/
('1052440', '7100', 'Overall 4'),	/*Mu Lung Dojo Uniform*/
('1052442', '6000', 'Overall 4'),	/*Combat Fatigues*/
('1052443', '6300', 'Overall 4'),	/*Taisho Romance*/
('1052445', '4700', 'Overall 4'),	/*Intergalactic Armor*/
('1052446', '4900', 'Overall 4'),	/*Light Chiffon Dress*/
('1052447', '3800', 'Overall 4'),	/*Light Cotton Candy Overalls*/
('1052416', '4700', 'Overall 4'),	/*Orchid's Black Wing Uniform*/
('1052417', '7600', 'Overall 4'),	/*Honeybee Suit*/
/*Page 8*/
('1052418', '3200', 'Overall 4'),	/*Princely Daywear*/
('1052419', '7600', 'Overall 4'),	/*Pink Lolita Outfit*/
('1052421', '7400', 'Overall 4'),	/*Urban Pirate Outfit*/
('1052423', '6300', 'Overall 4'),	/*Hades Overall*/
('1052424', '5000', 'Overall 4'),	/*Fancy Noblesse Robe*/
('1052425', '6400', 'Overall 4'),	/*White Combat Tunic*/
('1052426', '3800', 'Overall 4'),	/*White Combat Habit*/
('1052474', '4900', 'Overall 4'),	/*The Onmyouji Ceremonial Robes*/
('1052448', '7400', 'Overall 4'),	/*Tomato Outfit*/
/*Page 9*/
('1052449', '5200', 'Overall 4'),	/*Sausage Outfit*/
('1052455', '7600', 'Overall 4'),	/*Honeybee Suit*/
('1052458', '7100', 'Overall 4'),	/*Lucia Overall*/
('1052459', '3800', 'Overall 4'),	/*Blue Angel Uniform*/
('1052503', '4900', 'Overall 4'),	/*Cool Summer Look*/
('1052531', '4000', 'Overall 4'),	/*The Bladed Falcon's Armor*/
('1052536', '7100', 'Overall 4'),	/*Marine Stripe*/
('1052537', '5200', 'Overall 4'),	/*Hyper Honeybee Suit*/
('1052538', '5000', 'Overall 4'),	/*Ghost Costume*/
/*Page 10*/
('1052539', '4000', 'Overall 4'),	/*Blue Jiangshi Costume*/
('1052540', '5000', 'Overall 4'),	/*Cow Costume*/
('1052541', '6300', 'Overall 4'),	/*Tiger Cub Costume*/
('1052542', '6300', 'Overall 4'),	/*Angel Costume*/
('1052543', '7400', 'Overall 4'),	/*Paper Box*/
('1052565', '5200', 'Overall 4'),	/*Reindeer Suit*/
('1052566', '7600', 'Overall 4'),	/*Snowman Costume*/
('1052567', '3400', 'Overall 4'),	/*Santa Costume*/
('1052568', '5600', 'Overall 4'),	/*Decked-Out Santa Outfit*/
/*Page 11*/
('1052571', '3400', 'Overall 4'),	/*Dark Devil Outfit*/
('1052574', '3800', 'Overall 4'),	/*Flowing Flame Robes*/
('1052575', '4000', 'Overall 4'),	/*Pious Shaman Robes*/
('1052544', '5000', 'Overall 4'),	/*Hyper Rising Star Baggy Digs*/
('1052549', '6300', 'Overall 4'),	/*[MS Custom] Orange Life-Jacket*/
('1052550', '4900', 'Overall 4'),	/*Seal Costume*/
('1052551', '8800', 'Overall 4'),	/*Yellow Bell Robe*/
('1052552', '7100', 'Overall 4'),	/*Gray Bell Robe*/
('1052554', '3800', 'Overall 4'),	/*Cat Lolita Outfit*/
/*Page 12*/
('1052594', '4700', 'Overall 4'),	/*Green Dinosaur Overall*/
('1052595', '7400', 'Overall 4'),	/*Purple Dinosaur Onesie*/
('1052597', '3200', 'Overall 4'),	/*Hilla Robe*/
('1052598', '4700', 'Overall 4'),	/*Hawkeye Captain Suit*/
('1052599', '3800', 'Overall 4'),	/*Oz Magic Robe*/
('1052601', '7400', 'Overall 4'),	/*Kerning Technical High Uniform*/
('1052602', '3400', 'Overall 4'),	/*Black Duster*/
('1052603', '5200', 'Overall 4'),	/*Mu Lung Academy Uniform*/
('1052604', '7600', 'Overall 4'),	/*Blue Love Dress*/
/*Page 13*/
('1052605', '4000', 'Overall 4'),	/*Ramling PJs*/
('1052576', '6000', 'Overall 4'),	/*Lotus's Black Wings Uniform*/
('1052577', '5600', 'Overall 4'),	/*Stylish Layered Plaid*/
('1052579', '3200', 'Overall 4'),	/*Xenon NeoTech Suit*/
('1052585', '6000', 'Overall 4'),	/*Fluffy Cat Outfit*/
('1052586', '4000', 'Overall 4'),	/*Lucia Outfit*/
('1052587', '5600', 'Overall 4'),	/*Harp Seal Doll Outfit*/
('1052624', '7600', 'Overall 4'),	/*GM Haku's Pirate Gear*/
('1052626', '8800', 'Overall 4'),	/*Splash Wave*/
/*Page 14*/
('1052627', '7100', 'Overall 4'),	/*Pirate Captain's Coat*/
('1052628', '7100', 'Overall 4'),	/*Blue Officer Uniform*/
('1052629', '3200', 'Overall 4'),	/*Silver Officer Uniform*/
('1052630', '3800', 'Overall 4'),	/*Black Officer Uniform*/
('1052634', '8800', 'Overall 4'),	/*Man's Shirts*/
('1052636', '5200', 'Overall 4'),	/*Patissier Uniform*/
('1052610', '5000', 'Overall 4'),	/*Succubus Dress*/
('1052618', '5600', 'Overall 4'),	/*Blue Kitty Hoodie*/
('1052619', '7600', 'Overall 4'),	/*Pink Kitty Hoodie*/
/*Page 15*/
('1052656', '5600', 'Overall 4'),	/*White Swan Ballet Outfit*/
('1052657', '7600', 'Overall 4'),	/*Black Swan Ballet Outfit*/
('1052660', '3800', 'Overall 4'),	/*Balloon Overalls*/
('1052661', '8800', 'Overall 4'),	/*Chicken Coataroo*/
('1052662', '5600', 'Overall 4'),	/*Camellia Flower Lovely Night Clothes*/
('1052663', '4900', 'Overall 4'),	/*Flowing Wind Robe*/
('1052664', '5600', 'Overall 4'),	/*Gentleman Bow Tie Suit*/
('1052665', '3400', 'Overall 4'),	/*Gentleman Suit*/
('1052666', '6300', 'Overall 4'),	/*Chocoram Doll Outfit*/
/*Page 16*/
('1052667', '5000', 'Overall 4'),	/*Puffram Onesie*/
('1052668', '3200', 'Overall 4'),	/*Princess of Time Dress*/
('1052671', '3800', 'Overall 4'),	/*Oversized Oxford*/
('1052643', '7400', 'Overall 4'),	/*Bloody Jeanne*/
('1052644', '6300', 'Overall 4'),	/*Shadow Executer*/
('1052654', '3400', 'Overall 4'),	/*フューチャーロイドネオンアーマー*/
('1052655', '5600', 'Overall 4'),	/*Dawn Bear Outfit*/
('1052692', '7400', 'Overall 4'),	/*Mr. K's Cat Outfit*/
('1052693', '6400', 'Overall 4'),	/*Rudi's Outfit*/
/*Page 17*/

('1052674', '3400', 'Overall 4'),	/*Dark Devil Outfit*/
('1052675', '6000', 'Overall 4'),	/*Vampire Phantom Suit*/
('1052676', '3600', 'Overall 4'),	/*Kirito's Outfit*/
('1052677', '6000', 'Overall 4'),	/*Asuna's Dress*/
('1052678', '6300', 'Overall 4'),	/*Leafa's Dress*/
('1052679', '8800', 'Overall 4'),	/*Freud's Robe*/
('1052680', '6400', 'Overall 4'),	/*Aran's Armor(M)*/
('1052681', '6400', 'Overall 4'),	/*Aran's Armor(F)*/
/*Page 18*/
('1052682', '5600', 'Overall 4'),	/*Brave Aran's Armor*/
('1052684', '4700', 'Overall 4'),	/*Heathcliff's Armor*/
('1052685', '6300', 'Overall 4'),	/*Yui's Dress*/
('1052724', '3400', 'Overall 4'),	/*Cutie Horse Suit*/
('1052725', '7100', 'Overall 4'),	/*Fancy Magician Overall*/
('1052726', '3800', 'Overall 4'),	/*Ghost Bride Wedding Dress*/
('1052727', '4700', 'Overall 4'),	/*Refreshing Male Outfit*/
('1052728', '6400', 'Overall 4'),	/*Refreshing Female Cardigan Outfit*/

/*Page 19*/

('1052754', '5000', 'Overall 4'),	/*Pink Panda Outfit*/
('1052762', '5200', 'Overall 4'),	/*Banana Overalls*/
('1052746', '3800', 'Overall 4'),	/*Chef Overall*/
('1052747', '7100', 'Overall 4'),	/*Contemporary Chic Outfit*/
('1052749', '6400', 'Overall 4'),	/*Nurse Dress*/
('1052750', '4900', 'Overall 4'),	/*Doctor Suit*/
('1052771', '6000', 'Overall 4'),	/*Ayame Overall Armor*/
('1052772', '3800', 'Overall 4'),	/*2014 유니폼*/
/*Page 20*/
('1052773', '7100', 'Overall 4'),	/*暗夜精灵铠甲*/
('1052774', '5200', 'Overall 4'),	/*隐武士铠甲*/
('1052779', '8800', 'Overall 4'),	/*Peach Camellia Kimono*/
('1052780', '5600', 'Overall 4'),	/*Red Wind Robes*/
('1052781', '4700', 'Overall 4'),	/*Red Pony Overalls*/
('1052782', '7400', 'Overall 4'),	/*Blue Pony Overalls*/
('1052811', '6300', 'Overall 4'),	/*Bright Angel Coat*/
('1052812', '7100', 'Overall 4'),	/*Dark Devil Coat*/
('1052852', '6000', 'Overall 4'),	/*Raging Lotus Gown*/
/*Page 21*/
('1052853', '7100', 'Overall 4'),	/*Ill Orchid Gown*/
('1052854', '7100', 'Overall 4'),	/*Worn Ghost Suit*/
('1052855', '7400', 'Overall 4'),	/*Worn Witch Outfit*/
('1052856', '3800', 'Overall 4'),	/*Worn Skull Outfit*/
('1052857', '7100', 'Overall 4'),	/*Ghost Suit*/
('1052858', '3800', 'Overall 4'),	/*Witch Outfit*/
('1052859', '4900', 'Overall 4'),	/*Skull Outfit*/
('1052837', '4000', 'Overall 4'),	/*Gym Teacher's Suit*/
('1052838', '3400', 'Overall 4'),	/*Student Swimsuit*/
/*Page 22*/
('1052841', '3400', 'Overall 4'),	/*Sweet Persimmon Suit*/
('1052842', '4900', 'Overall 4'),	/*White Puppy Outfit*/
('1052843', '3800', 'Overall 4'),	/*Brown Puppy Outfit*/
('1052844', '4700', 'Overall 4'),	/*Corn Overalls*/
('1052845', '4000', 'Overall 4'),	/*Loose-fit Homecoming Duds*/
('1052846', '3200', 'Overall 4'),	/*Peach Overalls*/
('1052891', '6300', 'Overall 4'),	/*Blue Bird Overall*/
('1052892', '7400', 'Overall 4'),	/*Cutie Bunny Overall*/
('1052894', '4000', 'Overall 4'),	/*Romantic Dress*/
/*Page 23*/
('1052895', '6000', 'Overall 4'),	/*Silver Wolf Outfit*/
('1052864', '3600', 'Overall 4'),	/*Pumpkin Bat Outfit*/
('1052865', '3200', 'Overall 4'),	/*Dinofrog Outfit*/
('1052870', '7100', 'Overall 4'),	/*Cadet Corps Uniform*/
('1052871', '6000', 'Overall 4'),	/*Scout Regiment Uniform*/
('1052872', '7600', 'Overall 4'),	/*Red Ribbon Dress*/
('1052873', '3600', 'Overall 4'),	/*Mikasa's Scout Regiment Uniform*/
('1052874', '7400', 'Overall 4'),	/*Levi's Scout Regiment Uniform*/
('1052876', '5000', 'Overall 4'),	/*Eren's Scout Regiment Uniform*/
/*Page 24*/
('1052912', '4900', 'Overall 4'),	/*Quilting Fashion King*/
('1052916', '3600', 'Overall 4'),	/*Akarin's Flowery Dress*/
('1052917', '3600', 'Overall 4'),	/*Akatsuki's Dark Suit*/
('1052920', '4000', 'Overall 4'),	/*Red Mouse Hooded Onesie*/
('1052921', '3600', 'Overall 4'),	/*Bubbly Blue Carp Outfit*/
('1052922', '5000', 'Overall 4'),	/*Bubbly Red Carp Outfit*/
('1052923', '4900', 'Overall 4'),	/*Feline Blue Sleeves*/
('1052924', '3600', 'Overall 4'),	/*Noble Blossom Coat*/
('1052925', '3400', 'Overall 4'),	/*Pink Blossom Dress*/
/*Page 25*/
('1052926', '6000', 'Overall 4'),	/*Cottontail Rabbit Dress*/
('1052896', '5600', 'Overall 4'),	/*Cutie Birk Outfit*/
('1052897', '5600', 'Overall 4'),	/*Snowman Costume*/
('1052898', '6300', 'Overall 4'),	/*Scuba Diving Suit*/
('1052899', '4900', 'Overall 4'),	/*Black Mouse Hooded Onesie*/
('1052901', '3200', 'Overall 4'),	/*Hipster*/
('1052902', '3600', 'Overall 4'),	/*Jumpsuit*/
('1052903', '3800', 'Overall 4'),	/*White Servant Tux*/
('1052904', '5000', 'Overall 4'),	/*Lovely Princess Dress*/
/*Page 26*/
('1052909', '6400', 'Overall 4'),	/*Honeybee Coat*/
('1052910', '6300', 'Overall 4'),	/*Crystal Cat Outfit (M)*/
('1052911', '3200', 'Overall 4'),	/*Crystal Cat Outfit (F)*/
('1052946', '8800', 'Overall 4'),	/*Schwarzer Cross*/
('1052947', '3200', 'Overall 4'),	/*Red Mouse Hooded Onesie*/
('1052948', '8800', 'Overall 4'),	/*Evening Orchid*/
('1052949', '5600', 'Overall 4'),	/*Haku Cloth*/
('1052951', '6000', 'Overall 4'),	/*Polka Dot Dress*/
('1052954', '8800', 'Overall 4'),	/*Deep Sky*/
/*Page 27*/
('1052955', '7600', 'Overall 4'),	/*Assistant Chef Outfit*/
('1052956', '3600', 'Overall 4'),	/*Beginner Chef Outfit*/
('1052957', '6000', 'Overall 4'),	/*Intermediate Chef Outfit*/
('1052958', '6400', 'Overall 4'),	/*Advanced Chef Outfit*/
('1052959', '6400', 'Overall 4'),	/*Sous-Chef Outfit*/
('1052939', '7100', 'Overall 4'),	/*돼지바 탱글딸기*/
('1052940', '3400', 'Overall 4'),	/*Spring Sunlight Pullover*/
('1052941', '7100', 'Overall 4'),	/*Dark Lotus Uniform*/
('1052942', '6300', 'Overall 4'),	/*Blue Panda Doll Outfit*/
/*Page 28*/
('1052976', '5000', 'Overall 4'),	/*Clear Blue*/
('1052977', '5200', 'Overall 4'),	/*Pink Cardigan*/
('1052960', '3800', 'Overall 4'),	/*Chef Outfit*/
('1052961', '3200', 'Overall 4'),	/*Scuba Diving Suit*/
('1052965', '7600', 'Overall 4'),	/*Black Sailor Dress*/
('1052966', '6000', 'Overall 4'),	/*Hilla Android Uniform*/
('1052967', '4900', 'Overall 4'),	/*Magnus Android Uniform*/
('1052970', '3600', 'Overall 4'),	/*Toy Prince*/
('1052975', '3600', 'Overall 4'),	/*Preppy Suspenders*/
/*Page 29*/
('1053014', '4700', 'Overall 4'),	/*Urban Pirate Outfit*/
('1053015', '4700', 'Overall 4'),	/*Reindeer Suit*/
('1053016', '6300', 'Overall 4'),	/*Show me the Meso*/



/*Page 1*/
('1053017', '7400', 'Overall 5'),	/*Undertaker*/
('1053018', '6400', 'Overall 5'),	/*Beaky Owl Outfit*/
('1053022', '7400', 'Overall 5'),	/*Umbral Attire*/
('1053023', '5000', 'Overall 5'),	/*Umbral Coat*/
('1052994', '6000', 'Overall 5'),	/*Abyss Burgunt*/
('1052995', '7600', 'Overall 5'),	/*Nyanya Steward Tuxedo*/
('1052996', '3200', 'Overall 5'),	/*Undertaker*/
('1052997', '7400', 'Overall 5'),	/*Arctic Suit*/
('1052998', '7600', 'Overall 5'),	/*Show me the Meso*/
/*Page 2*/
('1052999', '5600', 'Overall 5'),	/*Polar Fur-Trimmed Dress*/
('1053000', '5000', 'Overall 5'),	/*Enari's Cow Outfit*/
('1053001', '4000', 'Overall 5'),	/*Flutter-sleeve Bell Suit*/
('1053006', '5600', 'Overall 5'),	/*Yarn Bunny Outfit*/
('1053040', '3400', 'Overall 5'),	/*Shark Bodysuit*/
('1053041', '5000', 'Overall 5'),	/*Blue Phoenix Toga*/
('1053042', '4000', 'Overall 5'),	/*Red Phoenix Toga*/
('1053045', '5200', 'Overall 5'),	/*Kitty Overall (Male)*/
('1053046', '7400', 'Overall 5'),	/*Winged Kitty Dress (Female)*/
/*Page 3*/
('1053047', '5000', 'Overall 5'),	/*Mischievous Sweet Pig Outfit*/
('1053048', '6300', 'Overall 5'),	/*Cunning Sweet Pig Outfit*/
('1053049', '3600', 'Overall 5'),	/*Spring Scene Raincoat*/
('1053050', '3600', 'Overall 5'),	/*Noble Maple Suit*/
('1053051', '7100', 'Overall 5'),	/*Chicken Cutie Outfit*/
('1053052', '8800', 'Overall 5'),	/*Bubble Leaf Pants*/
('1053053', '7400', 'Overall 5'),	/*Bubble Leaf Skirt*/
('1053054', '3600', 'Overall 5'),	/*Hydrangea Kimono*/
('1053055', '3600', 'Overall 5'),	/*Racing Elephant Outfit*/
/*Page 4*/
('1053024', '3200', 'Overall 5'),	/*Flower Dancer's Dress*/
('1053025', '3800', 'Overall 5'),	/*Moon Dancer's Attire*/
('1053028', '8800', 'Overall 5'),	/*Baby Binkie Spacesuit*/


('1053033', '6300', 'Overall 5'),	/*Damien Coat*/
('1053034', '5600', 'Overall 5'),	/*Alicia Dress*/
('1053035', '3400', 'Overall 5'),	/*Oversized Shirt*/
('1053038', '6000', 'Overall 5'),	/*Singles Army Combat Armor*/
/*Page 5*/
('1053039', '3200', 'Overall 5'),	/*Couples Army Combat Armor*/
('1053082', '8800', 'Overall 5'),	/*Oversized Beach Gown*/
('1053083', '7100', 'Overall 5'),	/*Super Miracle Cube Outfit*/
('1053084', '7100', 'Overall 5'),	/*Violet Cube Outfit*/
('1053085', '3600', 'Overall 5'),	/*Black Cube Outfit*/
('1053086', '3400', 'Overall 5'),	/*Kamaitachi Outfit*/
('1053087', '7600', 'Overall 5'),	/*Formal Brown Shorts*/
('1053056', '5600', 'Overall 5'),	/*Blaster Outfit (M)*/
('1053057', '7400', 'Overall 5'),	/*Blaster Outfit (F)*/
/*Page 6*/
('1053058', '5600', 'Overall 5'),	/*Sky-blue Overalls*/
('1053059', '7100', 'Overall 5'),	/*Villain's Cool Tights (Outfit)*/
('1053060', '6400', 'Overall 5'),	/*Colorful Bikini*/
('1053061', '5200', 'Overall 5'),	/*Colorful Beach Pants*/

('1053068', '6000', 'Overall 5'),	/*Lake Monster Invincible Armor*/
('1053069', '6400', 'Overall 5'),	/*Kitty Bell*/
('1053104', '7600', 'Overall 5'),	/*Emilia Overall Outfit*/
('1053105', '3400', 'Overall 5'),	/*Subaru's Track Suit*/
/*Page 7*/
('1053106', '6400', 'Overall 5'),	/*Felt Overall Outfit*/
('1053107', '3600', 'Overall 5'),	/*Priscilla's Dress*/
('1053108', '7100', 'Overall 5'),	/*Vampire Phantom Suit*/
('1053109', '5000', 'Overall 5'),	/*Winter Bunny Coat (Teal)*/
('1053110', '3600', 'Overall 5'),	/*Winter Bunny Coat (Pink)*/
('1053114', '4900', 'Overall 5'),	/*Alicorn Costume*/
('1053115', '5000', 'Overall 5'),	/*Egg Outfit*/
('1053116', '7400', 'Overall 5'),	/*Cat Monster Outfit*/
('1053117', '6400', 'Overall 5'),	/*Love Tree Outfit*/
/*Page 8*/
('1053118', '3600', 'Overall 5'),	/*Festive Lovers Outfit (M)*/
('1053119', '3600', 'Overall 5'),	/*Festive Lovers Outfit (F)*/
('1053088', '3400', 'Overall 5'),	/*Formal Brown Skirt*/
('1053089', '6300', 'Overall 5'),	/*Moon Bunny Outfit (M)*/
('1053090', '7100', 'Overall 5'),	/*Moon Bunny Outfit (F)*/
('1053091', '5600', 'Overall 5'),	/*Dark Musician Coat*/
('1053092', '7100', 'Overall 5'),	/*Chained Princess Coat*/
('1053093', '5200', 'Overall 5'),	/*Halloween Festival Costume (M)*/
('1053094', '7100', 'Overall 5'),	/*Halloween Festival Costume (F)*/
/*Page 9*/
('1053095', '6000', 'Overall 5'),	/*Bichon Outfit*/
('1053096', '7600', 'Overall 5'),	/*Roundy Round Fairy Outfit*/
('1053097', '4900', 'Overall 5'),	/*Sweet Penguin Doll Outfit*/
('1053098', '8800', 'Overall 5'),	/*Fresh Penguin Doll Outfit*/
('1053099', '3200', 'Overall 5'),	/*Flutter Flower Knit*/
('1053102', '5600', 'Overall 5'),	/*우리네 멜빵바지*/
('1053103', '3200', 'Overall 5'),	/*Red Cube Outfit*/
('1053138', '3200', 'Overall 5'),	/*Nova Enchanter Clothes*/
('1053141', '5000', 'Overall 5'),	/*Spring Fairy Outfit*/
/*Page 10*/
('1053142', '5600', 'Overall 5'),	/*Rocket Outfit*/
('1053144', '6000', 'Overall 5'),	/*Cape Uniform (M)*/
('1053145', '8800', 'Overall 5'),	/*Cape Uniform (F)*/
('1053146', '7600', 'Overall 5'),	/*Heart Fur Coat*/
('1053147', '6300', 'Overall 5'),	/*White M-Forcer*/
('1053148', '5200', 'Overall 5'),	/*Ladybug Clothes*/
('1053120', '7600', 'Overall 5'),	/*Fried Chicken God's Garb (M)*/
('1053121', '3400', 'Overall 5'),	/*Fried Chicken God's Garb (F)*/
('1053124', '3200', 'Overall 5'),	/*Snow Moon Flower Outfit*/
/*Page 11*/
('1053125', '6300', 'Overall 5'),	/*White Night Outfit*/
('1053126', '5200', 'Overall 5'),	/*Polka Dot Heart Dress (F)*/
('1053127', '5000', 'Overall 5'),	/*Heart Jumpsuit (M)*/
('1053130', '3800', 'Overall 5'),	/*Polar Explorer Winter Clothes*/
('1053131', '5000', 'Overall 5'),	/*Sweet Chocolate Dessert Suit*/
('1053132', '7400', 'Overall 5'),	/*Sweet Fresh Cream Dessert Dress*/
('1053133', '3200', 'Overall 5'),	/*Yellow Chick Clothes (M)*/
('1053134', '7100', 'Overall 5'),	/*Yellow Chick Clothes (F)*/
('1053168', '5200', 'Overall 5'),	/*Aspire Uniform*/
/*Page 12*/
('1053169', '4700', 'Overall 5'),	/*Aster's Bon Bon Ribbon*/
('1053170', '3800', 'Overall 5'),	/*Rhea's Classical Dream*/
('1053171', '3600', 'Overall 5'),	/*Kat's Magic Kit*/
('1053172', '3800', 'Overall 5'),	/*Anastasia's Royal Serenade*/
('1053173', '3600', 'Overall 5'),	/*Roxxy's Full Moon Rhapsody*/
('1053174', '6400', 'Overall 5'),	/*Apollo's Steampunk Look*/
('1053175', '3400', 'Overall 5'),	/*Jax's Sonata Tuxedo*/
('1053176', '6300', 'Overall 5'),	/*Sarim's Midnight Show*/
('1053177', '6400', 'Overall 5'),	/*Detective Suit*/
/*Page 13*/
('1053180', '7600', 'Overall 5'),	/*Dango Set Outfit*/
('1053183', '5200', 'Overall 5'),	/*Iron Mace Uniform Outfit (M)*/
('1053155', '8800', 'Overall 5'),	/*Silver Flower Child Outfit (F)*/
('1053156', '4000', 'Overall 5'),	/*Silver Flower Child Outfit (M)*/
('1053157', '4900', 'Overall 5'),	/*Pandora Outfit*/
('1053158', '6000', 'Overall 5'),	/*Eastern Inspiration*/
('1053159', '6400', 'Overall 5'),	/*Starlit Dreams Robe*/
('1053162', '3800', 'Overall 5'),	/*Pop Star Long Coat (F)*/
('1053163', '5200', 'Overall 5'),	/*Pop Star Long Coat (M)*/
/*Page 14*/
('1053164', '7100', 'Overall 5'),	/*Foxy Teacher Outfit*/
('1053165', '7600', 'Overall 5'),	/*Froggy Raincoat*/
('1053166', '6400', 'Overall 5'),	/*Elizabethan Dress*/
('1053167', '8800', 'Overall 5'),	/*Napoleonic Uniform*/
('1053200', '7400', 'Overall 5'),	/*Watermelon Overalls (M)*/
('1053201', '5200', 'Overall 5'),	/*Simple Swimsuit (F)*/
('1053202', '8800', 'Overall 5'),	/*Simple Swimsuit (M)*/
('1053203', '6400', 'Overall 5'),	/*Watermelon Splash Suit*/
('1053205', '7400', 'Overall 5'),	/*Black and White Heart Outfit*/
/*Page 15*/
('1053207', '4700', 'Overall 5'),	/*Loose-fit Flower Print Shirt*/
('1053208', '4900', 'Overall 5'),	/*Refreshing Lemon Outfit*/
('1053209', '6400', 'Overall 5'),	/*Summer Flower Fairy Outfit (F)*/
('1053210', '7400', 'Overall 5'),	/*Summer Flower Fairy Outfit (M)*/
('1053215', '6000', 'Overall 5'),	/*Mellow Dino Gown*/
('1053184', '3800', 'Overall 5'),	/*Iron Mace Uniform Outfit (F)*/
('1053186', '3600', 'Overall 5'),	/*Pink Elephant Outfit*/
('1053187', '6400', 'Overall 5'),	/*Maple Galaxy Space Suit*/
('1053192', '6300', 'Overall 5'),	/*Carrot Julius Overalls*/
/*Page 16*/
('1053194', '5200', 'Overall 5'),	/*Summer Off-the-shoulder Outfit*/
('1053195', '3600', 'Overall 5'),	/*Forest Breeze Nightgown*/
('1053196', '7600', 'Overall 5'),	/*Forest Breeze Vest*/
('1053197', '5600', 'Overall 5'),	/*Red Striped Swimsuit*/
('1053198', '8800', 'Overall 5'),	/*Blue Striped Swimsuit*/
('1053199', '4700', 'Overall 5'),	/*Watermelon Dress (F)*/
('1053232', '6000', 'Overall 5'),	/*Sensible Denim Outfit (M)*/
('1053234', '5200', 'Overall 5'),	/*Ryude Robe*/
('1053239', '6400', 'Overall 5'),	/*Aspiring Aubergine*/
/*Page 17*/
('1053240', '3800', 'Overall 5'),	/*Leafy*/
('1053241', '7100', 'Overall 5'),	/*Pea Pod*/
('1053242', '7600', 'Overall 5'),	/*Meow Outfit*/
('1053243', '5200', 'Overall 5'),	/*Christmas Bunny Outfit (F)*/
('1053244', '3400', 'Overall 5'),	/*Christmas Bunny Outfit (M)*/
('1053245', '4700', 'Overall 5'),	/*Warm Pink Bear Outfit*/
('1053246', '7400', 'Overall 5'),	/*Warm Blue Bear Outfit*/
('1053247', '5200', 'Overall 5'),	/*Fall Oversized Jacket*/
('1053216', '3200', 'Overall 5'),	/*Sunny Dino Gown*/
/*Page 18*/
('1053217', '6400', 'Overall 5'),	/*Rabble Rouser Outfit (F)*/
('1053218', '6400', 'Overall 5'),	/*Rabble Rouser Outfit (M)*/
('1053219', '4000', 'Overall 5'),	/*Feather Messenger Outfit (F)*/
('1053220', '5000', 'Overall 5'),	/*Feather Messenger Outfit (M)*/
('1053221', '6000', 'Overall 5'),	/*Falling Darkness Outfit (F)*/
('1053222', '3200', 'Overall 5'),	/*Falling Darkness Outfit (M)*/
('1053225', '7100', 'Overall 5'),	/*Puppy Love Samurai Outfit (F)*/
('1053226', '7600', 'Overall 5'),	/*Puppy Love Samurai Outfit (M)*/
('1053227', '5000', 'Overall 5'),	/*Shadow Tactician Outfit*/
/*Page 19*/
('1053228', '7600', 'Overall 5'),	/*Cat Cafe Outfit (F)*/
('1053229', '8800', 'Overall 5'),	/*Cat Cafe Outfit (M)*/
('1053230', '3800', 'Overall 5'),	/*Apple Bunny Shirt*/
('1053231', '8800', 'Overall 5'),	/*Sensible Denim Outfit (F)*/
('1053264', '4000', 'Overall 5'),	/*Paper Box*/
('1053265', '3200', 'Overall 5'),	/*Worn Ghost Suit*/
('1053266', '5000', 'Overall 5'),	/*Worn Witch Outfit*/
('1053267', '4000', 'Overall 5'),	/*Worn Skull Outfit*/
('1053268', '5600', 'Overall 5'),	/*아틀리에 비서로이드 근무복*/
/*Page 20*/
('1053269', '8800', 'Overall 5'),	/*Adam's Aspire Personal Assistant Suit*/
('1053270', '3800', 'Overall 5'),	/*Eve's Aspire Personal Assistant Dress*/
('1053277', '6400', 'Overall 5'),	/*EVA Android Longcoat*/
('1053278', '5000', 'Overall 5'),	/*Asuka Plugsuit*/
('1053279', '7400', 'Overall 5'),	/*Rei Plugsuit*/
('1053250', '5600', 'Overall 5'),	/*Busy Girl Penguin Outfit*/
('1053251', '6000', 'Overall 5'),	/*Busy Boy Penguin Outfit*/
('1053252', '4900', 'Overall 5'),	/*Fried Suit*/
('1053253', '7100', 'Overall 5'),	/*Cadena Agent Suit (F)*/
/*Page 21*/
('1053254', '7600', 'Overall 5'),	/*Cadena Agent Suit (M)*/
('1053255', '3600', 'Overall 5'),	/*Winter Fantasy Cape*/
('1053256', '7600', 'Overall 5'),	/*Snowy Mountain Outfit*/
('1053257', '3800', 'Overall 5'),	/*Doll's Nightwear*/
('1053258', '7600', 'Overall 5'),	/*Beware the Pumpkin*/
('1053259', '7400', 'Overall 5'),	/*Ghost Costume*/
('1053260', '7100', 'Overall 5'),	/*Blue Jiangshi Costume*/
('1053261', '5200', 'Overall 5'),	/*Angel Costume*/
('1053262', '8800', 'Overall 5'),	/*Tiger Cub Costume*/
/*Page 22*/
('1053263', '6400', 'Overall 5'),	/*Cow Costume*/
('1053296', '8800', 'Overall 5'),	/*Pink Bear Winter Padded Coat*/
('1053301', '5200', 'Overall 5'),	/*Homeless Cat Outfit*/
('1053302', '5600', 'Overall 5'),	/*Green Hipster Track Suit*/
('1053303', '3800', 'Overall 5'),	/*Yellow Hipster Track Suit*/
('1053304', '7600', 'Overall 5'),	/*Pink Hipster Track Suit*/
('1053305', '7400', 'Overall 5'),	/*Raindrop Raincoat*/
('1053306', '3600', 'Overall 5'),	/*Super Summer Outfit (F)*/
('1053307', '3600', 'Overall 5'),	/*Super Summer Outfit (M)*/
/*Page 23*/
('1053308', '8800', 'Overall 5'),	/*Ark Outfit (F)*/
('1053309', '5600', 'Overall 5'),	/*Ark Outfit (M)*/
('1053310', '5000', 'Overall 5'),	/*Draped Clothes*/
('1053280', '5200', 'Overall 5'),	/*Lunar New Year VIP Outfit (F)*/
('1053281', '6000', 'Overall 5'),	/*Lunar New Year VIP Outfit (M)*/
('1053282', '6300', 'Overall 5'),	/*Royal Guard Outfit (F)*/
('1053283', '5200', 'Overall 5'),	/*Royal Guard Outfit (M)*/
('1053285', '5600', 'Overall 5'),	/*Red Bear Winter Padded Coat*/
('1053286', '3600', 'Overall 5'),	/*World of Pink Outfit (M)*/
/*Page 24*/
('1053287', '7100', 'Overall 5'),	/*World of Pink Outfit (F)*/
('1053288', '3800', 'Overall 5'),	/*Butterfly Outfit (M)*/
('1053289', '7400', 'Overall 5'),	/*Butterfly Outfit (F)*/
('1053290', '5600', 'Overall 5'),	/*Boyfriend Outfit (M)*/
('1053291', '3800', 'Overall 5'),	/*Girlfriend Outfit (F)*/
('1053292', '5600', 'Overall 5'),	/*Oversized Floral Shirt*/
('1053293', '7400', 'Overall 5'),	/*Valentine's Outfit (M)*/
('1053294', '8800', 'Overall 5'),	/*Valentine's Outfit (F)*/
('1053295', '7400', 'Overall 5'),	/*Alchemist Cloth*/
/*Page 25*/
('1053328', '6000', 'Overall 5'),	/*Innocent Outfit*/
('1053329', '4700', 'Overall 5'),	/*Summer Story Outfit Package (F)*/
('1053330', '3600', 'Overall 5'),	/*Summer Story Outfit Package (M)*/
('1053335', '3200', 'Overall 5'),	/*Crispy Carrot Duds*/
('1053336', '5000', 'Overall 5'),	/*Catkerchief Doll Outfit*/
('1053338', '4700', 'Overall 5'),	/*Traditional Thai Attire Outfit (F)*/
('1053339', '4000', 'Overall 5'),	/*Traditional Thai Attire Outfit (M)*/
('1053343', '6400', 'Overall 5'),	/*Maple Alliance Suit*/
('1053314', '5000', 'Overall 5'),	/*Maple Gumshoe's Coat*/
/*Page 26*/
('1053315', '3200', 'Overall 5'),	/*Jailbird Uniform*/
('1053316', '5000', 'Overall 5'),	/*Happy Ghost Outfit (F)*/
('1053317', '6300', 'Overall 5'),	/*Happy Ghost Outfit (M)*/
('1053318', '5000', 'Overall 5'),	/*Erda Outfit*/
('1053321', '7400', 'Overall 5'),	/*Kero-chan Bodysuit*/
('1053322', '4700', 'Overall 5'),	/*Sakura Battle Costume Outfit*/
('1053323', '7600', 'Overall 5'),	/*Syaoran Battle Costume Outfit*/
('1053324', '5200', 'Overall 5'),	/*Tomoeda Middle School Female Winter Uniform*/
('1053325', '4700', 'Overall 5'),	/*Tomoeda Middle School Male Winter Uniform*/
/*Page 27*/
('1053363', '7400', 'Overall 5'),	/*Cluckbottom*/
('1053364', '3400', 'Overall 5'),	/*Tri-color Outfit (F)*/
('1053365', '5600', 'Overall 5'),	/*Tri-color Outfit (M)*/
('1053366', '7600', 'Overall 5'),	/*Sweet Deer Outfit*/
('1053367', '7600', 'Overall 5'),	/*Little Star Cocoon Outfit*/
('1053371', '4000', 'Overall 5'),	/*Cozy Winter Clothes Outfit (F)*/
('1053372', '6000', 'Overall 5'),	/*Cozy Winter Clothes Outfit (M)*/
('1053373', '7100', 'Overall 5'),	/*Regal Romance Outfit (F)*/
('1053374', '5000', 'Overall 5'),	/*Regal Romance Outfit (M)*/
/*Page 28*/
('1053375', '3800', 'Overall 5'),	/*Blue Flame Magician Robe*/
('1053344', '3400', 'Overall 5'),	/*Starry Summer Night Outfit (F) */
('1053345', '6300', 'Overall 5'),	/*Starry Summer Night Outfit (M) */
('1053346', '3800', 'Overall 5'),	/*Reaper's Robe*/
('1053347', '5000', 'Overall 5'),	/*Alliance Commander Suit*/
('1053351', '7100', 'Overall 5'),	/*Cutie Pie Coat*/
('1053352', '6400', 'Overall 5'),	/*Veritas Attire*/
('1053353', '5600', 'Overall 5'),	/*Heavenly Prayer Outfit (F) */
('1053354', '4000', 'Overall 5'),	/*Heavenly Prayer Outfit (M) */
/*Page 29*/
('1053355', '5000', 'Overall 5'),	/*Delinquent Bear Outfit*/
('1053356', '8800', 'Overall 5'),	/*Happy Bear Outfit (F)*/
('1053357', '5600', 'Overall 5'),	/*Happy Bear Outfit (M)*/



/*Page 1*/
('1053393', '7100', 'Overall 6'),	/*Snowman Costume*/
('1053397', '7400', 'Overall 6'),	/*Spring Cleaning Coveralls*/
('1053398', '4700', 'Overall 6'),	/*Spring Chicky Sportswear*/
('1053399', '4000', 'Overall 6'),	/*Sugarsweet Candy Boy Outfit (M)*/
('1053400', '3200', 'Overall 6'),	/*Sugarsweet Candy Girl Outfit (F)*/
('1053401', '3600', 'Overall 6'),	/*Spring Ducky Raincoat*/
('1053403', '7100', 'Overall 6'),	/*CONY Onesie*/
('1053404', '3400', 'Overall 6'),	/*Cursed Hunter Suit*/
('1053405', '4700', 'Overall 6'),	/*LEONARD Onesie*/
/*Page 2*/
('1053406', '6400', 'Overall 6'),	/*SALLY Onesie*/
('1053376', '6400', 'Overall 6'),	/*Crown Fitness Track Suit*/
('1053377', '6400', 'Overall 6'),	/*Frilly Pink Pajamas (F)*/
('1053378', '4000', 'Overall 6'),	/*Silly Blue Pajamas (M)*/
('1053379', '5600', 'Overall 6'),	/*Cobalt Filigree Outfit (F)*/
('1053380', '6400', 'Overall 6'),	/*Cobalt Filigree Outfit (M)*/
('1053381', '6300', 'Overall 6'),	/*Lunar New Year Pudgy Piggy Outfit (F)*/
('1053382', '6400', 'Overall 6'),	/*Lunar New Year Pudgy Piggy Outfit (M)*/
('1053383', '7400', 'Overall 6'),	/*Sweet Shipmate Uniform (F)*/
/*Page 3*/
('1053384', '5000', 'Overall 6'),	/*Sweet Shipmate Uniform (M)*/
('1053385', '7400', 'Overall 6'),	/*Outsized Sailor Coat (Brown & Black)*/
('1053386', '6400', 'Overall 6'),	/*Outsized Sailor Coat (Black & Ivory)*/
('1053387', '7600', 'Overall 6'),	/*Outsized Sailor Coat (Navy & White)*/
('1053388', '6000', 'Overall 6'),	/*Outsized Sailor Coat (White & Purple)*/
('1053389', '7400', 'Overall 6'),	/*Outsized Sailor Coat (White & Red)*/
('1053390', '6300', 'Overall 6'),	/*Hunny Bun Bear Onesie*/
('1053391', '3600', 'Overall 6'),	/*Reindeer Suit*/
('1053424', '3600', 'Overall 6'),	/*Starry Light Suit*/
/*Page 4*/
('1053434', '5200', 'Overall 6'),	/*Fox Fire Shirt*/
('1053411', '4000', 'Overall 6'),	/*Springtime Boho Tunic*/
('1053412', '4900', 'Overall 6'),	/*Sunny Songbird Outfit (M)*/
('1053413', '7100', 'Overall 6'),	/*Sunny Songbird Outfit (F)*/
('1053414', '8800', 'Overall 6'),	/*Midnight Magician Outfit (M)*/
('1053415', '4000', 'Overall 6'),	/*Midnight Magician Outfit (F)*/
('1053420', '5200', 'Overall 6'),	/*Bunny Ear Outfit*/
('1053421', '7400', 'Overall 6'),	/*Bunny Ear Outfit*/
('1053422', '4000', 'Overall 6'),	/*Blue Flame Hellion Outfit*/
/*Page 5*/
('1053423', '7600', 'Overall 6'),	/*Starry Light Gown*/
('1053441', '3800', 'Overall 6'),	/*Red Lotus Spirit Walker's Attire*/





/*  Top  */
/*Page 1*/
('1049000', '3600', 'Top'),	/*Friendship Shirt*/
('1040005', '4900', 'Top'),	/*Orange Baseball Jacket*/
('1040001', '3400', 'Top'),	/*Black Blazer*/
('1040027', '5000', 'Top'),	/*Old School Blazer*/
('1040047', '3400', 'Top'),	/*Dark Rider*/
('1040046', '3400', 'Top'),	/*Shine Rider*/
('1040045', '4900', 'Top'),	/*Red Rider*/
('1040056', '3600', 'Top'),	/*Original Disco Shirt*/
('1040055', '4900', 'Top'),	/*Orange Disco Shirt*/
/*Page 2*/
('1040054', '6300', 'Top'),	/*Green Disco Shirt*/
('1040053', '5600', 'Top'),	/*Orange Striped Trainer*/
('1040052', '4000', 'Top'),	/*Green Striped Trainer*/
('1040051', '8800', 'Top'),	/*Blue Striped Trainer*/
('1040078', '3200', 'Top'),	/*Pre-School Uniform Top*/
('1040077', '5600', 'Top'),	/*Cowboy Top*/
('1040066', '4900', 'Top'),	/*Red Wild Top*/
('1040065', '4000', 'Top'),	/*Brown Wild Top*/
('1040064', '5200', 'Top'),	/*Wild Top*/
/*Page 3*/
('1040101', '3200', 'Top'),	/*Skull T-Shirt*/
('1040127', '4700', 'Top'),	/*Blue Heart Tanktop*/
('1040126', '4000', 'Top'),	/*Yellow Frill Sleeveless*/
('1040125', '3800', 'Top'),	/*Military Cargo Jacket*/
('1040124', '6000', 'Top'),	/*Crusader T-Shirt*/
('1040123', '5000', 'Top'),	/*Prep School Uniform*/
('1040119', '4900', 'Top'),	/*Ragged Top*/
('1040114', '5600', 'Top'),	/*Hawaiian Shirt*/
('1040143', '6000', 'Top'),	/*Pink Top*/
/*Page 4*/
('1040141', '4000', 'Top'),	/*Blue Sailor Shirt*/
('1040140', '4000', 'Top'),	/*Pink Mimi Blouse*/
('1040139', '6400', 'Top'),	/*Island Beads (M)*/
('1040138', '8800', 'Top'),	/*Mercury Leather Jacket (M)*/
('1040137', '3200', 'Top'),	/*Tania Tailored Jacket*/
('1040135', '7100', 'Top'),	/*Muscle Man T*/
('1040134', '5200', 'Top'),	/*Orange Puffy Jacket*/
('1040133', '4900', 'Top'),	/*Long Blue Shirt*/
('1040132', '4700', 'Top'),	/*Palm Tree Tanktop*/
/*Page 5*/
('1040131', '8800', 'Top'),	/*Pink Tie Casual Suit*/
('1040130', '4700', 'Top'),	/*Green Tie Casual Suit*/
('1040129', '6300', 'Top'),	/*Red Casual Suit*/
('1040128', '3400', 'Top'),	/*Blue Line Tanktop*/
('1040154', '8800', 'Top'),	/*Pre-School Top*/
('1040148', '3200', 'Top'),	/*Retro School Uniform Jacket*/
('1040144', '3800', 'Top'),	/*Bulletproof Vest*/
('1040191', '4000', 'Top'),	/*[MS Custom] Orange Disco Shirt*/
('1040190', '6400', 'Top'),	/*[MS Custom] Orange Striped Trainer*/
/*Page 6*/
('1040186', '6400', 'Top'),	/*Cowboy Shirt*/
('1040197', '3600', 'Top'),	/*Lalala Sleeveless Shirt*/
('1040196', '5000', 'Top'),	/*Smile Seed Top*/
('1040195', '3400', 'Top'),	/*Sleeveless Purple Mustache Shirt (M)*/
('1040194', '6400', 'Top'),	/*Guys Pineapple Tank top*/
('1040193', '8800', 'Top'),	/*RED T-shirt*/
('1040192', '3600', 'Top'),	/*Green Bunny T-Shirt*/
('1041005', '3600', 'Top'),	/*Pink Mimi Blouse*/
('1041001', '4700', 'Top'),	/*Blue Sailor Shirt*/
/*Page 7*/
('1041000', '4700', 'Top'),	/*Blue Frill Blouse*/
('1041009', '8800', 'Top'),	/*Red Sailor Shirt*/
('1041071', '5600', 'Top'),	/*Yellow Mimi Blouse*/
('1041070', '3400', 'Top'),	/*Sky Blue Mimi Blouse*/
('1041073', '5200', 'Top'),	/*Pre-School Uniform Top*/
('1041072', '6000', 'Top'),	/*Cowboy Top*/
('1041090', '3600', 'Top'),	/*Pink Top*/
('1041114', '4900', 'Top'),	/*Hawaiian Shirt*/
('1041113', '5000', 'Top'),	/*Pink Frill Pajama Top*/
/*Page 8*/
('1041112', '6000', 'Top'),	/*Black Trainer Jacket*/
('1041111', '3600', 'Top'),	/*Pink Trainer Jacket*/
('1041110', '7600', 'Top'),	/*Sky Blue Trainer Jacket*/
('1041109', '4700', 'Top'),	/*Red Trainer Jacket*/
('1041108', '6000', 'Top'),	/*SF Ninja Top*/
('1041104', '4900', 'Top'),	/*Old School Uniform Top*/
('1041135', '5000', 'Top'),	/*Tube-Top Jacket*/
('1041134', '8800', 'Top'),	/*Angora Mustang*/
('1041133', '5000', 'Top'),	/*Grey Cardigan*/
/*Page 9*/
('1041132', '4000', 'Top'),	/*Pink Frill Camisole*/
('1041131', '6400', 'Top'),	/*Pink Ribboned Janie*/
('1041130', '3800', 'Top'),	/*Blue Frill Camisole*/
('1041129', '5600', 'Top'),	/*Yellow Frill Camisole*/
('1041128', '6300', 'Top'),	/*Cross Sleeveless*/
('1041127', '7600', 'Top'),	/*Heart Sleeveless*/
('1041126', '7600', 'Top'),	/*Skull T-Shirt*/
('1041125', '3600', 'Top'),	/*Rainbow Knit*/
('1041147', '8800', 'Top'),	/*Muscle Man*/
/*Page 10*/
('1041146', '3200', 'Top'),	/*Old School Blazer [F]*/
('1041144', '7100', 'Top'),	/*Retro School Uniform Jacket*/
('1041143', '6000', 'Top'),	/*Green Tie Casual Suit*/
('1041142', '6400', 'Top'),	/*Ribbon Frilled top*/
('1041140', '6400', 'Top'),	/*Island Beads (F)*/
('1041139', '5200', 'Top'),	/*Mercury Leather Jacket (F)*/
('1041138', '6000', 'Top'),	/*Tania Bolero*/
('1041137', '5600', 'Top'),	/*Pink-Dotted Top*/
('1041136', '3400', 'Top'),	/*Pink Vest Blouse*/
/*Page 11*/
('1041156', '7600', 'Top'),	/*Pre-School Top*/
('1041199', '6000', 'Top'),	/*Lalala Pink T-shirt*/
('1041198', '8800', 'Top'),	/*Smile Seed Top*/
('1041197', '7400', 'Top'),	/*Pink Mustache T-Shirt (F)*/
('1041196', '3600', 'Top'),	/*Girls Pineapple Tank top*/
('1041195', '4700', 'Top'),	/*RED T-shirt*/
('1041194', '6300', 'Top'),	/*Pink Bunny T-Shirt*/
('1041193', '3200', 'Top'),	/*Tania Tailored Jacket*/
('1041189', '5200', 'Top'),	/*Cowgirl Shirt*/
/*Page 12*/
('1042015', '7400', 'Top'),	/*Blue Layered Combo*/
('1042014', '7600', 'Top'),	/*Yellow Layered Combo*/
('1042013', '8800', 'Top'),	/*Green Snowboard Top*/
('1042012', '6000', 'Top'),	/*Yellow Snowboard Top*/
('1042011', '7100', 'Top'),	/*Wildcats Baseball Shirt (Alternate)*/
('1042010', '5600', 'Top'),	/*Baseball Shirt (Away)*/
('1042009', '3400', 'Top'),	/*Baseball Shirt (Home)*/
('1042008', '5000', 'Top'),	/*Wildcats Baseball Shirt (Basic)*/
('1042007', '7400', 'Top'),	/*Blue Camping Shirt*/
/*Page 13*/
('1042006', '3400', 'Top'),	/*Green Camping Shirt*/
('1042005', '6400', 'Top'),	/*Pink Camping Shirt*/
('1042004', '4900', 'Top'),	/*Pink Hooded Vest*/
('1042002', '5000', 'Top'),	/*Red Hooded Vest*/
('1042001', '8800', 'Top'),	/*Black Hooded Vest*/
('1042000', '7100', 'Top'),	/*Orange Hooded Vest*/
('1042031', '3800', 'Top'),	/*Orange Mushroom T-Shirt*/
('1042030', '3600', 'Top'),	/*Slime T-Shirt*/
('1042029', '6000', 'Top'),	/*Octopus T-Shirt*/
/*Page 14*/
('1042028', '6400', 'Top'),	/*Orange B-Ball Jersey*/
('1042027', '8800', 'Top'),	/*Blue B-Ball Jersey*/
('1042026', '7400', 'Top'),	/*Flowery Dress Shirt*/
('1042025', '6300', 'Top'),	/*Prisoner Top*/
('1042024', '3200', 'Top'),	/*Red Polka-Dot Pajama Top*/
('1042023', '3600', 'Top'),	/*Blue Polka-Dot Pajama Top*/
('1042022', '5600', 'Top'),	/*Camouflaged Uniform*/
('1042021', '4900', 'Top'),	/*Starry Layered Combo*/
('1042020', '6000', 'Top'),	/*Old Military Uniform*/
/*Page 15*/
('1042019', '3600', 'Top'),	/*M Layered T-Shirt*/
('1042018', '6400', 'Top'),	/*Red T-Shirt w/ Heart*/
('1042017', '6400', 'Top'),	/*Sky Blue Snowboard Top*/
('1042016', '6000', 'Top'),	/*Pink Snowboard Top*/
('1042047', '5600', 'Top'),	/*Star-Patterned Yellow Shirt*/
('1042046', '4900', 'Top'),	/*White Casual Suit*/
('1042045', '3800', 'Top'),	/*Bowling Shirt*/
('1042044', '5600', 'Top'),	/*Pink Striped Rugby Tee*/
('1042043', '6000', 'Top'),	/*Green Striped Rugby Tee*/
/*Page 16*/
('1042042', '3400', 'Top'),	/*White Hooded Vest*/
('1042041', '4700', 'Top'),	/*Black Allstar*/
('1042040', '5000', 'Top'),	/*Pink Allstar*/
('1042039', '3200', 'Top'),	/*Sky Blue Allstar*/
('1042038', '3400', 'Top'),	/*Red Sweater*/
('1042037', '7600', 'Top'),	/*Snowman Padded Jacket*/
('1042036', '8800', 'Top'),	/*Christmas Padded Jacket*/
('1042035', '3600', 'Top'),	/*Red Double-Coat*/
('1042034', '7600', 'Top'),	/*Green Double-Coat*/
/*Page 17*/
('1042033', '5600', 'Top'),	/*Beige Double-Coat*/
('1042032', '5000', 'Top'),	/*Beetle Longsleeve*/
('1042063', '6400', 'Top'),	/*Red Turtleneck Sweater*/
('1042062', '6000', 'Top'),	/*Stitched Leather Jacket*/
('1042061', '7600', 'Top'),	/*Ball Zone Jumper*/
('1042060', '6300', 'Top'),	/*Pola Sweater*/
('1042059', '3800', 'Top'),	/*Preppy Red and White*/
('1042058', '6400', 'Top'),	/*Red Half*/
('1042056', '3400', 'Top'),	/*Beat Shirt*/
/*Page 18*/
('1042055', '5200', 'Top'),	/*Pink Down Parka*/
('1042054', '3600', 'Top'),	/*Pink Wool Jacket*/
('1042053', '5000', 'Top'),	/*Blue Wool Jacket*/
('1042052', '3600', 'Top'),	/*Blue Down Parka*/
('1042051', '4700', 'Top'),	/*Bomber Jacket*/
('1042050', '6000', 'Top'),	/*Baseball Jumper*/
('1042049', '3600', 'Top'),	/*Short Denim Jacket*/
('1042048', '3800', 'Top'),	/*Purple Star Shirt*/
('1042078', '5200', 'Top'),	/*White & Blue Sailor Top*/
/*Page 19*/
('1042077', '5600', 'Top'),	/*Rainbow T*/
('1042076', '4000', 'Top'),	/*Dotted Disco Shirt*/
('1042075', '5000', 'Top'),	/*Pink Pluto T*/
('1042074', '8800', 'Top'),	/*White Longsleeve With Star*/
('1042073', '7100', 'Top'),	/*Navy Blue Dress Shirt*/
('1042072', '6000', 'Top'),	/*Red Layered Hooded Shirt*/
('1042071', '8800', 'Top'),	/*Pastel Layered Hooded Shirt*/
('1042070', '4700', 'Top'),	/*Sky Blue Big-Belt Shirt*/
('1042069', '8800', 'Top'),	/*Pink Big-Belt Shirt*/
/*Page 20*/
('1042068', '6400', 'Top'),	/*Drill Muffler*/
('1042067', '3800', 'Top'),	/*Orange Hooded Zip-Up*/
('1042066', '6400', 'Top'),	/*Orange Hooded Shirt*/
('1042065', '5200', 'Top'),	/*Football Top (Away)*/
('1042064', '4000', 'Top'),	/*Football Jersey (Home)*/
('1042095', '3800', 'Top'),	/*Vintage Hooded Shirt*/
('1042094', '7400', 'Top'),	/*Orange Snowflake Sweater*/
('1042093', '6300', 'Top'),	/*Pointed Double Coat*/
('1042092', '3400', 'Top'),	/*Pelvis Hoodie*/
/*Page 21*/
('1042091', '6400', 'Top'),	/*Pink Skull Hooded Vest*/
('1042090', '4700', 'Top'),	/*Red Skull Hooded Vest*/
('1042089', '6400', 'Top'),	/*Blue Skull Hooded Vest*/
('1042088', '4700', 'Top'),	/*Black Skull Hooded Vest*/
('1042087', '4700', 'Top'),	/*Skull Shirt*/
('1042086', '6300', 'Top'),	/*Tourist T*/
('1042085', '8800', 'Top'),	/*Canary Heart T*/
('1042084', '3800', 'Top'),	/*Army General Hoodie*/
('1042083', '4900', 'Top'),	/*Rainbow Hooded Pancho*/
/*Page 22*/
('1042082', '5000', 'Top'),	/*Black Cardigan Set*/
('1042081', '6300', 'Top'),	/*Cherry Layered T*/
('1042080', '3200', 'Top'),	/*Red Hot Racer T*/
('1042110', '7400', 'Top'),	/*Red Hooded Coat*/
('1042109', '4700', 'Top'),	/*Yellow & Red-Striped Jacket*/
('1042108', '3800', 'Top'),	/*Purple Tank*/
('1042107', '7600', 'Top'),	/*Pink Flower T-shirt*/
('1042106', '3400', 'Top'),	/*Rainbow-Striped Hoodie*/
('1042105', '4700', 'Top'),	/*Crown Hooded T*/
/*Page 23*/
('1042104', '3600', 'Top'),	/*Lime Green Sleeveless*/
('1042103', '3600', 'Top'),	/*White Outlaw Shirt*/
('1042102', '5600', 'Top'),	/*Aqua Road T*/
('1042101', '3400', 'Top'),	/*Blanc Rose Top*/
('1042100', '4000', 'Top'),	/*Checkered Casual Suit*/
('1042099', '4000', 'Top'),	/*Striped Hooded Shirt*/
('1042098', '8800', 'Top'),	/*Camo Hooded Jacket*/
('1042097', '4700', 'Top'),	/*Print Layered Hoody*/
('1042096', '7600', 'Top'),	/*M Shirt*/
/*Page 24*/
('1042127', '3400', 'Top'),	/*Green Suspenders*/
('1042126', '5200', 'Top'),	/*Red and Black Blazer*/
('1042125', '6400', 'Top'),	/*Yellow Longsleeve with Bunny Bag*/
('1042122', '4700', 'Top'),	/*Bowtie Jacket*/
('1042121', '5000', 'Top'),	/*Opera Pink Double Coat*/
('1042120', '6000', 'Top'),	/*Celeste Blue Double Coat*/
('1042119', '7600', 'Top'),	/*Vintage Muffler Jacket*/
('1042118', '6000', 'Top'),	/*Red Checkered Shirt*/
('1042117', '5000', 'Top'),	/*Green Baseball Jacket*/
/*Page 25*/
('1042116', '6400', 'Top'),	/*Orange Pea Coat*/
('1042143', '7400', 'Top'),	/*Disco Tank Top*/
('1042142', '6300', 'Top'),	/*Rainbow Top*/
('1042141', '3600', 'Top'),	/*Pink Star Glow*/
('1042140', '4900', 'Top'),	/*Slick Agent Top*/
('1042138', '4700', 'Top'),	/*The White Tee*/
('1042137', '7600', 'Top'),	/*Dark Tech Top*/
('1042136', '7400', 'Top'),	/*Red Legolesse for Transformation*/
('1042135', '3600', 'Top'),	/*Dark Master Sergeant for Transformation*/
/*Page 26*/
('1042134', '3200', 'Top'),	/*Yellow Shirt with Pads*/
('1042133', '4000', 'Top'),	/*Striped Hoodie Shirt*/
('1042132', '4900', 'Top'),	/*Aqua Green Star*/
('1042131', '3200', 'Top'),	/*Preppy Black Vest*/
('1042130', '7400', 'Top'),	/*Gold Chainz*/
('1042129', '7400', 'Top'),	/*"Black Tie Affair" Dress Shirt*/
('1042128', '4000', 'Top'),	/*Apple-Green Sweater*/
('1042159', '5200', 'Top'),	/*Animal One Piece*/
('1042158', '5000', 'Top'),	/*Baseball Classic*/
/*Page 27*/
('1042157', '5000', 'Top'),	/*Lovely Pink Heart T-Shirt*/
('1042156', '4700', 'Top'),	/*Galaxy T-Shirt*/
('1042155', '3200', 'Top'),	/*Sky Rider Jacket*/
('1042154', '5000', 'Top'),	/*Bohemian Hooded Jacket*/
('1042153', '7600', 'Top'),	/*Red Plaid Duffle Coat*/
('1042152', '3800', 'Top'),	/*Rainbow Knitted Top*/
('1042151', '6300', 'Top'),	/*Brown Argyle Sweater*/
('1042150', '7400', 'Top'),	/*Black "Hit Me" Shirt*/
('1042149', '7400', 'Top'),	/*80's Knit Pullover*/
/*Page 28*/
('1042147', '4700', 'Top'),	/*Preppy Knit Vest*/
('1042146', '5200', 'Top'),	/*Superstar Hoodie*/
('1042145', '4700', 'Top'),	/*Layered Duckie T*/
('1042144', '6400', 'Top'),	/*Checkered Resort Shirt*/
('1042174', '3800', 'Top'),	/*Camping Shirt*/
('1042173', '7600', 'Top'),	/*Green Polo*/
('1042172', '8800', 'Top'),	/*Preppy Blue Shirt*/
('1042171', '3400', 'Top'),	/*Idol Star Vest*/
('1042170', '7100', 'Top'),	/*Cool Summer Shirt*/
/*Page 29*/
('1042169', '5600', 'Top'),	/*Rainbow Tie-Dye Shirt*/
('1042168', '5200', 'Top'),	/*Lightning T-Shirt*/
('1042166', '6400', 'Top'),	/*Leather Biker Jacket*/



/*Page 1*/
('1042165', '8800', 'Top 2'),	/*Pink Bowtie & White Vest*/
('1042164', '6300', 'Top 2'),	/*Green Tie & Shirt*/
('1042163', '4000', 'Top 2'),	/*Pink Heart T-Shirt & Muffler*/
('1042162', '5600', 'Top 2'),	/*Blue-Striped Undershirt*/
('1042161', '3200', 'Top 2'),	/*Yellow Spring Jacket*/
('1042160', '5000', 'Top 2'),	/*Navy Hoodie*/
('1042190', '8800', 'Top 2'),	/*Dual-Color Heart Tee*/
('1042189', '4000', 'Top 2'),	/*Lamb Wool Top*/
('1042188', '3800', 'Top 2'),	/*Puffy Raglan Tee*/
/*Page 2*/
('1042187', '3600', 'Top 2'),	/*Pink Sweater*/
('1042186', '3200', 'Top 2'),	/*Fur Vest*/
('1042185', '6400', 'Top 2'),	/*JM's Street Gear*/
('1042184', '5600', 'Top 2'),	/*Tiger-Print Scarf & Top*/
('1042183', '3400', 'Top 2'),	/*Pink Argyle Plaid*/
('1042182', '4000', 'Top 2'),	/*Denim Hoodie*/
('1042181', '5200', 'Top 2'),	/*Napoleon Jacket*/
('1042178', '3400', 'Top 2'),	/*Puppy Tee*/
('1042177', '5200', 'Top 2'),	/*Vintage Hoodie Jacket*/
/*Page 3*/
('1042176', '7600', 'Top 2'),	/*I Love CN Top*/
('1042207', '7400', 'Top 2'),	/*Star Trainer Jacket*/
('1042206', '6400', 'Top 2'),	/*Black Rider Jacket*/
('1042204', '3400', 'Top 2'),	/*Hamburger Tee*/
('1042203', '7400', 'Top 2'),	/*Orange Scarf Tee*/
('1042202', '4000', 'Top 2'),	/*Penguin Tee*/
('1042200', '5200', 'Top 2'),	/*Blue Smiley Tee*/
('1042199', '3200', 'Top 2'),	/*Pink Smiley Tee*/
('1042198', '4000', 'Top 2'),	/*Rainbow Tee*/
/*Page 4*/
('1042194', '7400', 'Top 2'),	/*White Collared Shirt*/
('1042193', '3600', 'Top 2'),	/*Padded Vest*/
('1042192', '8800', 'Top 2'),	/*Green Tie Casual Suit*/
('1042222', '3400', 'Top 2'),	/*Lemon Freshness*/
('1042221', '3200', 'Top 2'),	/*Joyous 8th T-Shirt*/
('1042220', '7100', 'Top 2'),	/*Shiny Training Top*/
('1042219', '4900', 'Top 2'),	/*Blue Stars T-Shirt*/
('1042218', '3200', 'Top 2'),	/*Raspberry Candy T-Shirt*/
('1042217', '5000', 'Top 2'),	/*Black Viva Baseball*/
/*Page 5*/
('1042216', '8800', 'Top 2'),	/*Red Viva Baseball*/
('1042215', '6000', 'Top 2'),	/*Jester Sweater*/
('1042214', '4700', 'Top 2'),	/*Spring Sweater Set*/
('1042213', '5600', 'Top 2'),	/*Pink Spring Jacket*/
('1042212', '6300', 'Top 2'),	/*Blue Spring Jacket*/
('1042210', '7100', 'Top 2'),	/*Mustang Vest Pink Tee*/
('1042209', '6300', 'Top 2'),	/*Mustang Vest Green Tee*/
('1042208', '8800', 'Top 2'),	/*Elephant Hoody*/
('1042239', '6000', 'Top 2'),	/*Cutie Raincoat*/
/*Page 6*/
('1042238', '5600', 'Top 2'),	/*Pink Bunny Sweater*/
('1042237', '6400', 'Top 2'),	/*Gold Tailor Vest*/
('1042236', '4000', 'Top 2'),	/*Green Apple Sweater*/
('1042235', '4000', 'Top 2'),	/*Rabbit Top*/
('1042232', '4000', 'Top 2'),	/*Bat Costume Sweater*/
('1042230', '6300', 'Top 2'),	/*Cutie Raincoat*/
('1042229', '6300', 'Top 2'),	/*I Love MY Top*/
('1042228', '5600', 'Top 2'),	/*I Love SG Top*/
('1042252', '8800', 'Top 2'),	/*Cute Sleeveless Shirt*/
/*Page 7*/
('1042251', '5600', 'Top 2'),	/*Slither Style Hoodie*/
('1042250', '7600', 'Top 2'),	/*Hyper Spring Sweater Set*/
('1042249', '4700', 'Top 2'),	/*[MS Custom] Red Double-Coat*/
('1042248', '5200', 'Top 2'),	/*[MS Custom] Beetle Longsleeve*/
('1042247', '3600', 'Top 2'),	/*[MS Custom] Baseball Shirt (Away)*/
('1042246', '6300', 'Top 2'),	/*Hyper Green Suspenders*/
('1042245', '6400', 'Top 2'),	/*Hyper Spring Jealousy*/
('1042242', '3400', 'Top 2'),	/*Summer Picnic*/
('1042241', '4900', 'Top 2'),	/*Flying Violet*/
/*Page 8*/
('1042240', '3800', 'Top 2'),	/*Colorful T-Shirt*/
('1042271', '8800', 'Top 2'),	/*Meow T-shirt*/
('1042270', '6300', 'Top 2'),	/*Pink Bunny Sweater*/
('1042269', '5600', 'Top 2'),	/*Ribbon Days*/
('1042268', '5200', 'Top 2'),	/*Cutie Raincoat*/
('1042267', '3600', 'Top 2'),	/*Exciting Hoodie*/
('1042266', '3800', 'Top 2'),	/*Muscle Man*/
('1042265', '5600', 'Top 2'),	/*Strawberry Shirt*/
('1042264', '8800', 'Top 2'),	/*Colored Golf Shirt*/
/*Page 9*/
('1042263', '8800', 'Top 2'),	/*Funky Jumper*/
('1042262', '6400', 'Top 2'),	/*Colorful T-Shirt*/
('1042261', '3800', 'Top 2'),	/*Black Viva Baseball*/
('1042260', '3200', 'Top 2'),	/*Loose Fit Sweater*/
('1042259', '7600', 'Top 2'),	/*Lemon Freshness*/
('1042287', '3600', 'Top 2'),	/*Red Check Rider*/
('1042286', '3600', 'Top 2'),	/*Athletic Hood*/
('1042285', '3200', 'Top 2'),	/*Pastel Dot Tee*/
('1042282', '7100', 'Top 2'),	/*Eum T-Shirt*/
/*Page 10*/
('1042281', '3600', 'Top 2'),	/*Jeong T-Shirt*/
('1042280', '6400', 'Top 2'),	/*Min T-Shirt*/
('1042279', '3600', 'Top 2'),	/*Hun T-Shirt*/
('1042278', '7400', 'Top 2'),	/*Denim Hoodie*/
('1042277', '3800', 'Top 2'),	/*Star T-Shirt*/
('1042275', '5200', 'Top 2'),	/*Frog Raindrop*/
('1042272', '8800', 'Top 2'),	/*Slick Agent Top*/
('1042294', '5600', 'Top 2'),	/*Thumping Heart Vest*/
('1042293', '4700', 'Top 2'),	/*Guardian Clothing*/
/*Page 11*/
('1042292', '8800', 'Top 2'),	/*Banana Cardigan*/
('1042291', '7100', 'Top 2'),	/*Vibrant Yellow Knit*/
('1042290', '6300', 'Top 2'),	/*White Cherry Knit*/
('1042319', '5600', 'Top 2'),	/*Hoi Poi T-shirt*/

('1042315', '4900', 'Top 2'),	/*Bubbly Elephant Shirt*/
('1042314', '7100', 'Top 2'),	/*Rabbit and Bear Shirt*/
('1042313', '5600', 'Top 2'),	/*Full of Hearts T-Shirt*/
('1042312', '6300', 'Top 2'),	/*Blue Mushroom T-Shirt*/
/*Page 12*/
('1042311', '7400', 'Top 2'),	/*Rainbow T-shirt*/
('1042335', '6400', 'Top 2'),	/*Pink Marine T-shirt*/
('1042334', '8800', 'Top 2'),	/*Green Kitty Shirt*/
('1042333', '4000', 'Top 2'),	/*Pink Kitty Sweatshirt*/
('1042332', '3800', 'Top 2'),	/*Red Ribbon Kitty Top*/
('1042330', '7400', 'Top 2'),	/*Charming Baby*/
('1042329', '7100', 'Top 2'),	/*Sweet Summer Shirt*/
('1042320', '8800', 'Top 2'),	/*Island Travel T-Shirt*/
('1042351', '6300', 'Top 2'),	/*Hoya T-shirt*/
/*Page 13*/

('1042349', '4000', 'Top 2'),	/*All About Black*/
('1042348', '8800', 'Top 2'),	/*Boldly Colored Polo*/
('1042347', '7100', 'Top 2'),	/*Naughty Boy T-Shirt*/
('1042346', '3600', 'Top 2'),	/*Baby Ram Pullover (Pink)*/
('1042345', '5600', 'Top 2'),	/*Baby Ram Pullover (Blue)*/
('1042344', '4700', 'Top 2'),	/*Gold Fur-Lined Jacket*/
('1042343', '4000', 'Top 2'),	/*New School Hoodie*/
('1042342', '7400', 'Top 2'),	/*Rawrin' Tiger Top*/
/*Page 14*/
('1042341', '6400', 'Top 2'),	/*Hatchling T-shirt*/
('1042339', '6000', 'Top 2'),	/*White Kitty Pink Top*/
('1042338', '3400', 'Top 2'),	/*Brown Teddy Top*/
('1042337', '7100', 'Top 2'),	/*Teddy Picnic Shirt*/
('1042336', '3600', 'Top 2'),	/*Corny Top*/
('1042367', '7600', 'Top 2'),	/*Devil Bear T-Shirt*/
('1042364', '6300', 'Top 2'),	/*Marigold V Tee*/
('1042363', '3600', 'Top 2'),	/*Fuchsia V Tee */
('1042362', '7400', 'Top 2'),	/*Emerald V Tee*/
/*Page 15*/
('1042361', '7100', 'Top 2'),	/*Red Cloud Top*/
('1042360', '4900', 'Top 2'),	/*Cherry Rabbit Hood*/
('1042359', '7100', 'Top 2'),	/*Hyper Spring Sweater Set*/
('1042358', '7600', 'Top 2'),	/*Soft Olive Knitwear*/
('1042357', '5600', 'Top 2'),	/*Cloud Prison*/
('1042356', '5000', 'Top 2'),	/*Chenghuiwan Effect T-Shirt*/
('1042355', '5200', 'Top 2'),	/*Ranbingluan Effect T-Shirt*/
('1042354', '5600', 'Top 2'),	/*Duang Effect T-Shirt*/
('1042383', '6000', 'Top 2'),	/*Tennis Top (M)*/
/*Page 16*/
('1042382', '5600', 'Top 2'),	/*Chunky Cable-Knit Top*/
('1042381', '3600', 'Top 2'),	/*Khaki Field Coat*/
('1042380', '5600', 'Top 2'),	/*Strawberry Training Shirt*/
('1042379', '3200', 'Top 2'),	/*Shoulder Freedom*/
('1042378', '7100', 'Top 2'),	/*Tattoo Look*/
('1042376', '6300', 'Top 2'),	/*Snazzy Bunny Outfit*/
('1042375', '6300', 'Top 2'),	/*Strawberry Training Shirt*/
('1042386', '7600', 'Top 2'),	/*Rounded Tanktop*/
('1042385', '6400', 'Top 2'),	/*Trendy Denim Jacket*/
/*Page 17*/
('1042384', '6000', 'Top 2'),	/*Tennis Top (F)*/
('1048002', '3600', 'Top 2'),	/*Carrot T-shirt*/
('1048001', '3400', 'Top 2'),	/*Bunny Love T-Shirt*/
('1048000', '7400', 'Top 2'),	/*Couple Shirt*/





/*  Bottom  */
/*Page 1*/
('1060001', '5000', 'Bottom'),	/*Black Suit Pants*/
('1060003', '7100', 'Bottom'),	/*Military Shorts*/
('1060048', '5000', 'Bottom'),	/*Green Disco Pants*/
('1060049', '8800', 'Bottom'),	/*Blue Disco Pants*/
('1060053', '4700', 'Bottom'),	/*Wild Pants*/
('1060054', '3400', 'Bottom'),	/*Brown Wild Pants*/
('1060055', '5600', 'Bottom'),	/*Red Wild Pants*/
('1060034', '3200', 'Bottom'),	/*Blue Rider Pants*/
('1060035', '5200', 'Bottom'),	/*Shine Rider Pants*/
/*Page 2*/
('1060036', '4000', 'Bottom'),	/*Dark Rider Pants*/
('1060040', '7600', 'Bottom'),	/*Blue Trainer Pants*/
('1060041', '3600', 'Bottom'),	/*Green Trainer Pants*/
('1060042', '5200', 'Bottom'),	/*Orange Trainer Pants*/
('1060047', '4000', 'Bottom'),	/*Original Disco Pants*/
('1060066', '7600', 'Bottom'),	/*Cowboy Pants*/
('1060067', '6400', 'Bottom'),	/*Pre-School Pants*/
('1060112', '3800', 'Bottom'),	/*Prep School Uniform Pants*/
('1060113', '5600', 'Bottom'),	/*Blue Leggings*/
/*Page 3*/
('1060114', '7100', 'Bottom'),	/*Washed Jeans*/
('1060116', '3400', 'Bottom'),	/*Military Cargo Shorts*/
('1060117', '7100', 'Bottom'),	/*Tropical Shorts*/
('1060118', '3200', 'Bottom'),	/*Orange Puffy Pants*/
('1060119', '7400', 'Bottom'),	/*Denim Wrinkled Skirt*/
('1060120', '5200', 'Bottom'),	/*Tania Tartan Pants*/
('1060121', '5200', 'Bottom'),	/*Mercury Washed Jeans*/
('1060122', '6300', 'Bottom'),	/*Pink Miniskirt*/
('1060123', '5600', 'Bottom'),	/*Blue Sailor Skirt*/
/*Page 4*/
('1060125', '5200', 'Bottom'),	/*Blue Skirt (m)*/
('1060126', '6400', 'Bottom'),	/*Black Wakeboard Pants*/
('1060096', '7600', 'Bottom'),	/*Old School Uniform Pants*/
('1060103', '3200', 'Bottom'),	/*Hawaiian Skirt*/
('1060108', '3800', 'Bottom'),	/*Torn-Up Jeans*/
('1060145', '7600', 'Bottom'),	/*Pre-School Pants*/
('1060139', '8800', 'Bottom'),	/*Retro School Uniform Pants*/
('1060178', '5600', 'Bottom'),	/*[MS Custom] Orange Trainer Pants*/
('1060179', '7400', 'Bottom'),	/*Golf Shorts*/
/*Page 5*/
('1060180', '6300', 'Bottom'),	/*Puffy Puff Pants*/
('1060181', '3400', 'Bottom'),	/*Star Shorts*/
('1060182', '7100', 'Bottom'),	/*Golf Shorts*/
('1060187', '3200', 'Bottom'),	/*Green Rolled-Up Shorts*/
('1060188', '3200', 'Bottom'),	/*White Hot Pants*/
('1060189', '6000', 'Bottom'),	/*Smile Seed Pants*/
('1060190', '6000', 'Bottom'),	/*Lalala Dot Pants*/
('1060174', '4000', 'Bottom'),	/*Cowboy Pants*/
('1061000', '8800', 'Bottom'),	/*Blue Bell Dress*/
/*Page 6*/
('1061001', '7100', 'Bottom'),	/*Blue Sailor Skirt*/
('1061004', '3200', 'Bottom'),	/*Pink Miniskirt*/
('1061005', '6000', 'Bottom'),	/*Roll-Up Jean*/
('1061007', '8800', 'Bottom'),	/*Red Sailor Skirt*/
('1061072', '4000', 'Bottom'),	/*Red Trainer Pants*/
('1061073', '5200', 'Bottom'),	/*Sky Blue Trainer Pants*/
('1061074', '6400', 'Bottom'),	/*Pink Trainer Pants*/
('1061075', '5200', 'Bottom'),	/*Black Trainer Pants*/
('1061065', '3400', 'Bottom'),	/*Sky Blue Miniskirt*/
/*Page 7*/
('1061066', '3600', 'Bottom'),	/*Yellow Mimi Skirt*/
('1061067', '3400', 'Bottom'),	/*Cowboy Shorts*/
('1061068', '3200', 'Bottom'),	/*Pre-School Uniform Skirt*/
('1061107', '6400', 'Bottom'),	/*SF Ninja Pants*/
('1061108', '6400', 'Bottom'),	/*Red Training Shorts*/
('1061109', '7100', 'Bottom'),	/*Sky Blue Training Shorts*/
('1061110', '7100', 'Bottom'),	/*Pink Training Shorts*/
('1061111', '6000', 'Bottom'),	/*Black Training Shorts*/
('1061112', '7100', 'Bottom'),	/*Pink Frill Pajama Bottom*/
/*Page 8*/
('1061113', '8800', 'Bottom'),	/*Hawaiian Skirt*/
('1061089', '3200', 'Bottom'),	/*Blue Skirt*/
('1061103', '7400', 'Bottom'),	/*Old School Uniform (Skirt)*/
('1061136', '3600', 'Bottom'),	/*Long Khaki Skirt*/
('1061137', '4700', 'Bottom'),	/*Dark Denim Skirt*/
('1061138', '6300', 'Bottom'),	/*Pink Heart Hot Pants*/
('1061139', '8800', 'Bottom'),	/*Military Cargo Shorts*/
('1061140', '7600', 'Bottom'),	/*Denim Skirt & Striped Sox*/
('1061141', '4900', 'Bottom'),	/*Tania Tartan Skirt*/
/*Page 9*/
('1061142', '3800', 'Bottom'),	/*Mercury Jean Skirt*/
('1061143', '3600', 'Bottom'),	/*Amorian Pink Skirt*/
('1061144', '6400', 'Bottom'),	/*Blue Jeans*/
('1061145', '7100', 'Bottom'),	/*Retro School Uniform Pants*/
('1061147', '4900', 'Bottom'),	/*Old School Uniform Pants (F)*/
('1061148', '3600', 'Bottom'),	/*Pink Frill Swim Skirt*/
('1061124', '5200', 'Bottom'),	/*Red Leggings*/
('1061126', '8800', 'Bottom'),	/*Plitz Skirt*/
('1061127', '3600', 'Bottom'),	/*Blue Diamond Bootcuts*/
/*Page 10*/
('1061128', '7100', 'Bottom'),	/*Pink Diamond Bootcuts*/
('1061129', '4700', 'Bottom'),	/*Butterfly Skirt*/
('1061130', '6000', 'Bottom'),	/*Green Long Skirt*/
('1061131', '7600', 'Bottom'),	/*Blue Slit Skirt*/
('1061132', '5000', 'Bottom'),	/*Skirt with Tights*/
('1061133', '6000', 'Bottom'),	/*Orange Long Skirt*/
('1061134', '4000', 'Bottom'),	/*Denim Miniskirt*/
('1061135', '5600', 'Bottom'),	/*Pink Layered Skirt*/
('1061170', '4700', 'Bottom'),	/*Bright Frilly Shorts*/
/*Page 11*/
('1061166', '7100', 'Bottom'),	/*Pre-School Skirt*/
('1061203', '6000', 'Bottom'),	/*Puffy Puff Dress*/
('1061204', '6300', 'Bottom'),	/*Golf Skirt*/
('1061206', '5600', 'Bottom'),	/*Golf Skirt*/
('1061207', '6300', 'Bottom'),	/*Star Skirt*/
('1061210', '7400', 'Bottom'),	/*Check Skirt*/
('1061211', '5600', 'Bottom'),	/*Green Skirt*/
('1061212', '5000', 'Bottom'),	/*White Hot Pants*/
('1061213', '6400', 'Bottom'),	/*Smile Seed Skirt*/
/*Page 12*/
('1061214', '5200', 'Bottom'),	/*Lalala Dot Skirt*/
('1061198', '5200', 'Bottom'),	/*Cowgirl Pants*/
('1062003', '3600', 'Bottom'),	/*Red Hip-Hop Pants*/
('1062005', '7600', 'Bottom'),	/*Lined Hip-Hop Pants*/
('1062008', '5000', 'Bottom'),	/*Pink Camping Shorts*/
('1062009', '4700', 'Bottom'),	/*Green Camping Shorts*/
('1062010', '6400', 'Bottom'),	/*Blue Camping Shorts*/
('1062011', '7400', 'Bottom'),	/*Wildcats Baseball Pants (Basic)*/
('1062012', '5000', 'Bottom'),	/*Baseball Pants (Home)*/
/*Page 13*/
('1062013', '4000', 'Bottom'),	/*Baseball Pants (Away)*/
('1062014', '7600', 'Bottom'),	/*Wildcats Baseball Pants (Alternate)*/
('1062015', '4700', 'Bottom'),	/*Ripped Jeans*/
('1062032', '6300', 'Bottom'),	/*Cargo Pants*/
('1062033', '5200', 'Bottom'),	/*Red Checkered Pants*/
('1062034', '6000', 'Bottom'),	/*White Checkered Pants*/
('1062035', '3200', 'Bottom'),	/*Bone Buckled Slacks*/
('1062038', '6000', 'Bottom'),	/*Hip Hop Jeans*/
('1062039', '3600', 'Bottom'),	/*White Jeans*/
/*Page 14*/
('1062040', '3600', 'Bottom'),	/*Washed Denim Cargos*/
('1062041', '6400', 'Bottom'),	/*Denim Cargos*/
('1062042', '3600', 'Bottom'),	/*Jeans with Chain*/
('1062043', '7600', 'Bottom'),	/*Black Leather Pants*/
('1062044', '4900', 'Bottom'),	/*Red Starrium*/
('1062045', '3200', 'Bottom'),	/*Patched Denim Jeans*/
('1062046', '3200', 'Bottom'),	/*Vintage Pocket Pants*/
('1062047', '5600', 'Bottom'),	/*Brisk*/
('1062016', '6400', 'Bottom'),	/*Yellow Snowboard Pants*/
/*Page 15*/
('1062017', '8800', 'Bottom'),	/*Green Snowboard Pants*/
('1062018', '4900', 'Bottom'),	/*Bell-Bottomed Faded Jeans*/
('1062019', '7400', 'Bottom'),	/*Pink Snowboard Pants*/
('1062020', '5600', 'Bottom'),	/*Sky Blue Snowboard Pants*/
('1062021', '4000', 'Bottom'),	/*Jean Shorts*/
('1062022', '7100', 'Bottom'),	/*Old Army Pants*/
('1062023', '5000', 'Bottom'),	/*Baggy Jeans*/
('1062024', '6400', 'Bottom'),	/*Camouflaged Army Pants*/
('1062025', '4000', 'Bottom'),	/*Blue Polka-Dot Pajama Pants*/
/*Page 16*/
('1062026', '3600', 'Bottom'),	/*Red Polka-Dot Pajama Pants*/
('1062027', '4700', 'Bottom'),	/*Prisoner Pants*/
('1062028', '4000', 'Bottom'),	/*Picnic Jean Shorts*/
('1062029', '5200', 'Bottom'),	/*Blue B-Ball Shorts*/
('1062030', '3800', 'Bottom'),	/*Orange B-Ball Shorts*/
('1062031', '3400', 'Bottom'),	/*Checkered Shorts*/
('1062064', '5000', 'Bottom'),	/*Checks Point Pants*/
('1062065', '3200', 'Bottom'),	/*White-Striped Trainer Shorts*/
('1062066', '3200', 'Bottom'),	/*Vintage Sky Blue Jeans*/
/*Page 17*/
('1062067', '6400', 'Bottom'),	/*Summer Capris*/
('1062068', '6400', 'Bottom'),	/*Rainbow Shorts*/
('1062069', '3200', 'Bottom'),	/*Brown Chained Pants*/
('1062070', '3800', 'Bottom'),	/*Painted Blue Jeans*/
('1062071', '6300', 'Bottom'),	/*Low-Rise Ripped Jeans*/
('1062072', '5200', 'Bottom'),	/*Relaxed Fit Jeans*/
('1062073', '3600', 'Bottom'),	/*Olive Pumpkin Pants*/
('1062074', '7100', 'Bottom'),	/*Brown Pumpkin Pants*/
('1062075', '6400', 'Bottom'),	/*Vintage Black Pants*/
/*Page 18*/
('1062076', '6000', 'Bottom'),	/*Light Blue Ripped Jeans*/
('1062077', '5200', 'Bottom'),	/*Brown Bubble Jeans*/
('1062048', '5000', 'Bottom'),	/*Brown Checkered Pants*/
('1062049', '4000', 'Bottom'),	/*Football Pants (Home)*/
('1062050', '7100', 'Bottom'),	/*Football Bottom (Away)*/
('1062051', '5200', 'Bottom'),	/*All-Star Blue Jeans*/
('1062052', '6400', 'Bottom'),	/*White Faded Jeans*/
('1062053', '3200', 'Bottom'),	/*Pink-Lined Shorts*/
('1062054', '6400', 'Bottom'),	/*Busy Bee Shorts*/
/*Page 19*/
('1062055', '6000', 'Bottom'),	/*Jailbird Shorts*/
('1062056', '3200', 'Bottom'),	/*Military Cargo Pants*/
('1062057', '5600', 'Bottom'),	/*Scottish Pants*/
('1062058', '5000', 'Bottom'),	/*Inferno Jeans*/
('1062059', '6400', 'Bottom'),	/*Vintage Black Jeans*/
('1062060', '3600', 'Bottom'),	/*Blue Skinny Jeans*/
('1062061', '6000', 'Bottom'),	/*Olive Skinny Jeans*/
('1062062', '3800', 'Bottom'),	/*Red Wine Skinny Jeans*/
('1062063', '5600', 'Bottom'),	/*Dark Rocker Jeans*/
/*Page 20*/
('1062096', '6300', 'Bottom'),	/*Practical Linen Trousers*/
('1062097', '4700', 'Bottom'),	/*Ella Blue Denim*/
('1062098', '3800', 'Bottom'),	/*Aqua Jeans*/
('1062100', '5600', 'Bottom'),	/*Rolled-Up Baggy Jeans*/
('1062101', '4700', 'Bottom'),	/*Rolled-Up Skinny Jeans*/
('1062102', '4700', 'Bottom'),	/*Twinkle Star Blue Jeans*/
('1062103', '6000', 'Bottom'),	/*Baggy Glow-in-the-Dark Pants*/
('1062104', '3800', 'Bottom'),	/*Dark Purple Jeans*/
('1062105', '3400', 'Bottom'),	/*Plaid Roll-Up Jeans*/
/*Page 21*/
('1062106', '7400', 'Bottom'),	/*Bunny Frill Pants*/
('1062107', '6000', 'Bottom'),	/*Shooting Star Jeans*/
('1062108', '5000', 'Bottom'),	/*Vintage Jeans*/
('1062109', '8800', 'Bottom'),	/*Neon Skinny Jeans*/
('1062110', '6300', 'Bottom'),	/*Baby Pink Pants*/
('1062111', '5200', 'Bottom'),	/*Blue Ribbon Shorts*/
('1062080', '7400', 'Bottom'),	/*Amorian Pink Skirt*/
('1062081', '4700', 'Bottom'),	/*Bunny-Padded Snowboard Pants*/
('1062082', '3200', 'Bottom'),	/*Red and Black Warm-ups*/
/*Page 22*/
('1062083', '6300', 'Bottom'),	/*Brown Pocket Shorts*/
('1062084', '3600', 'Bottom'),	/*Jewel Chain Jeans*/
('1062085', '5200', 'Bottom'),	/*"Black Tie Affair" Dress Pants*/
('1062086', '3600', 'Bottom'),	/*Dark Master Sergeant Skirt for Transformation*/
('1062087', '5000', 'Bottom'),	/*Red Legolia Pants for Transformation*/
('1062088', '3400', 'Bottom'),	/*Dark Night Pants for Transformation*/
('1062089', '7600', 'Bottom'),	/*Pink Heart Boxers*/
('1062091', '3800', 'Bottom'),	/*Black Checkered Shorts*/
('1062092', '5200', 'Bottom'),	/*Pink 80s Slacks*/
/*Page 23*/
('1062093', '6400', 'Bottom'),	/*Moss Green Pants*/
('1062094', '7600', 'Bottom'),	/*Ruby-Buckled Shorts*/
('1062095', '3800', 'Bottom'),	/*Milan Jeans*/
('1062129', '5000', 'Bottom'),	/*Red Spotted Shorts*/
('1062130', '4700', 'Bottom'),	/*Blue Spotted Shorts*/
('1062131', '5600', 'Bottom'),	/*White Jeans*/
('1062133', '3800', 'Bottom'),	/*Star Trainer Pants*/
('1062134', '4000', 'Bottom'),	/*Super Pop Shorts*/
('1062135', '7400', 'Bottom'),	/*Shiny Gold Pants*/
/*Page 24*/
('1062136', '3400', 'Bottom'),	/*Layered Denim Pants*/
('1062137', '7400', 'Bottom'),	/*Plum Sherbet Pants*/
('1062138', '4000', 'Bottom'),	/*Mint Sherbet Pants*/
('1062139', '3400', 'Bottom'),	/*Deep Blue Sea Knee Socks*/
('1062112', '3400', 'Bottom'),	/*Underpants*/
('1062113', '7100', 'Bottom'),	/*Crayon Shorts*/
('1062114', '5200', 'Bottom'),	/*Pink Heart Shorts*/
('1062116', '8800', 'Bottom'),	/*Star Beach Shorts*/
('1062117', '5200', 'Bottom'),	/*Idol Star Chain Pants*/
/*Page 25*/
('1062118', '4700', 'Bottom'),	/*Stone Washed Jeans*/
('1062119', '6300', 'Bottom'),	/*Technicolour Funky Pants*/
('1062121', '8800', 'Bottom'),	/*Tiger-Print Leggings*/
('1062122', '6300', 'Bottom'),	/*Plaid-Cuffed Jeans*/
('1062123', '7600', 'Bottom'),	/*High-Rider*/
('1062124', '7100', 'Bottom'),	/*Saruel Pants*/
('1062126', '8800', 'Bottom'),	/*Pink Sprite Pants*/
('1062160', '4700', 'Bottom'),	/*Hyper Funky Xylophone Leggings*/
('1062161', '6300', 'Bottom'),	/*[MS Custom] Red Checkered Pants*/
/*Page 26*/
('1062162', '3800', 'Bottom'),	/*Hyper Deep Blue Sea Knee Socks*/
('1062163', '5200', 'Bottom'),	/*Slither Style Pants*/
('1062170', '6400', 'Bottom'),	/*Aqua Jeans*/
('1062171', '5000', 'Bottom'),	/*Stocking Shorts*/
('1062172', '3800', 'Bottom'),	/*Checkered Tights*/
('1062173', '5200', 'Bottom'),	/*Funky Shorts*/
('1062174', '6300', 'Bottom'),	/*Hearts Tights*/
('1062175', '3400', 'Bottom'),	/*Pink Skinny Jeans*/
('1062145', '3800', 'Bottom'),	/*Funky Xylophone Leggings*/
/*Page 27*/
('1062147', '5200', 'Bottom'),	/*Sky Rainbow Shorts [temp]*/
('1062151', '5200', 'Bottom'),	/*Rabbit Bottom*/
('1062152', '4000', 'Bottom'),	/*Neon Pink Pants*/
('1062153', '6400', 'Bottom'),	/*Vacation Denim Pants*/
('1062154', '4000', 'Bottom'),	/*Layered Denim Pants*/
('1062155', '7100', 'Bottom'),	/*Oceanic Sandblasted Jeans*/
('1062156', '8800', 'Bottom'),	/*Mosaic Purple*/
('1062157', '6000', 'Bottom'),	/*Chocolate Strawberry Pants*/
('1062159', '3400', 'Bottom'),	/*Hyper Chocolate Strawberry Pants*/
/*Page 28*/
('1062203', '6000', 'Bottom'),	/*Otherworldly Slacks*/
('1062204', '3800', 'Bottom'),	/*Rainbow Pants*/
('1062207', '7100', 'Bottom'),	/*Hoi Poi Shorts*/
('1062176', '4900', 'Bottom'),	/*Plum Sherbet Pants*/
('1062179', '7100', 'Bottom'),	/*Little Bunny Pants*/
('1062182', '3600', 'Bottom'),	/*Sapphire Jeans*/
('1062183', '3400', 'Bottom'),	/*Hot Pink Overalls*/
('1062184', '6400', 'Bottom'),	/*Cargo Hiphop Pants*/
('1062185', '5600', 'Bottom'),	/*Violet Dot Jeans*/
/*Page 29*/
('1062188', '5000', 'Bottom'),	/*Roll-Up Jean*/
('1062189', '4700', 'Bottom'),	/*Guardian Pants*/
('1062225', '3200', 'Bottom'),	/*Heart Patch Knit Pants*/



/*Page 1*/
('1062226', '5600', 'Bottom 2'),	/*Rawrin' Tiger Pants*/
('1062228', '4700', 'Bottom 2'),	/*White Rainbow Leggings*/
('1062229', '6000', 'Bottom 2'),	/*Naughty Boy Pants*/
('1062230', '3200', 'Bottom 2'),	/*White Rainbow Leggings*/
('1062231', '3200', 'Bottom 2'),	/*All About Jeans*/
('1062232', '3800', 'Bottom 2'),	/*Hoya Shorts*/
('1062233', '3600', 'Bottom 2'),	/*Dark Slate Jeans*/
('1062234', '8800', 'Bottom 2'),	/*Saggy Pants*/
('1062235', '3800', 'Bottom 2'),	/*Red Cloud Bottom*/
/*Page 2*/
('1062236', '7100', 'Bottom 2'),	/*Red Cloud Bottom*/
('1062237', '3200', 'Bottom 2'),	/*Celebrity Pants: Fashion*/
('1062238', '6000', 'Bottom 2'),	/*Celebrity Pants: Amour*/
('1062239', '5200', 'Bottom 2'),	/*Celebrity Pants: Defi*/
('1062208', '7100', 'Bottom 2'),	/*Bunny Patch Pants*/
('1062209', '4700', 'Bottom 2'),	/*Mini Bunny Pants*/
('1062210', '6000', 'Bottom 2'),	/*Island Travel Shorts*/
('1062211', '4000', 'Bottom 2'),	/*Sweet Summer Shorts*/
('1062212', '5600', 'Bottom 2'),	/*Heart Hot Pants*/
/*Page 3*/
('1062213', '7600', 'Bottom 2'),	/*Baby Purple Shorts*/
('1062214', '3200', 'Bottom 2'),	/*Teddy Hip Pants*/
('1062216', '3200', 'Bottom 2'),	/*Mismatched Shorts*/
('1062217', '5600', 'Bottom 2'),	/*Polka-Dot A Line Skirt*/
('1062218', '6300', 'Bottom 2'),	/*Green Speckled Sweatpants*/
('1062219', '3800', 'Bottom 2'),	/*Colorful Blue Pants*/
('1062220', '4700', 'Bottom 2'),	/*White Shorts*/
('1062221', '4000', 'Bottom 2'),	/*Teddy Picnic Pants*/
('1062222', '6300', 'Bottom 2'),	/*Brown Teddy Capris Pants*/
/*Page 4*/
('1062223', '7600', 'Bottom 2'),	/*Pink Kitty Denim Skirt*/
('1062244', '3400', 'Bottom 2'),	/*Strawberry Training Pants*/
('1062245', '6300', 'Bottom 2'),	/*Denim Shorts*/
('1062247', '4900', 'Bottom 2'),	/*Knee Freedom*/
('1062248', '3400', 'Bottom 2'),	/*Strawberry Training Pants*/
('1062249', '5000', 'Bottom 2'),	/*Chunky Cable-Knit Bottoms*/
('1062250', '5000', 'Bottom 2'),	/*Tennis Bottom (M)*/
('1062251', '8800', 'Bottom 2'),	/*Tennis Bottom (F)*/
('1062252', '8800', 'Bottom 2'),	/*Scallion Leggings*/





/*  Shoes  */
/*Page 1*/
('1070000', '3400', 'Shoes'),	/*꽃 고무신*/
('1070001', '7600', 'Shoes'),	/*Black Santa Boots*/
('1070002', '6000', 'Shoes'),	/*Kimono Shoes (M)*/
('1070003', '5600', 'Shoes'),	/*Black Shoes of Death*/
('1070004', '7600', 'Shoes'),	/*Blue Western Walkers*/
('1070005', '3600', 'Shoes'),	/*Santa Boy Boots*/
('1070006', '6300', 'Shoes'),	/*Royal Costume Shoes*/
('1070007', '3600', 'Shoes'),	/*Lunar Celebration Shoes*/
('1070008', '5000', 'Shoes'),	/*Korean Martial Arts Shoes*/
/*Page 2*/
('1070009', '6300', 'Shoes'),	/*Paris Wingtips*/
('1070014', '3600', 'Shoes'),	/*Veras Heels [m]*/
('1070015', '6300', 'Shoes'),	/*Bunny Boots [m]*/
('1070016', '5600', 'Shoes'),	/*Dandy Silver Sneaks*/
('1070018', '8800', 'Shoes'),	/*Napoleon Shoes */
('1070019', '6400', 'Shoes'),	/*Napoleon Boots*/
('1070020', '4900', 'Shoes'),	/*Twinkling Boy Glow Shoes*/
('1070024', '6000', 'Shoes'),	/*Garnet-Studded Boots*/
('1070028', '3800', 'Shoes'),	/*Evergreen Magistrate Pretty Shoes*/
/*Page 3*/
('1070031', '6000', 'Shoes'),	/*Alps Boy Shoes*/
('1070064', '3200', 'Shoes'),	/*Mad Doctor Boots*/
('1070065', '5200', 'Shoes'),	/*Blue Macaron Shoes*/
('1070066', '4000', 'Shoes'),	/*Santa Boy Boots*/
('1070067', '4000', 'Shoes'),	/*Cozy Snow Flower*/
('1070068', '7400', 'Shoes'),	/*The Kingdom Dress Shoes of King*/
('1070069', '6000', 'Shoes'),	/*Soaring Sky*/
('1070070', '5600', 'Shoes'),	/*Yeonhwa School Shoes*/
('1070071', '4700', 'Shoes'),	/*Mr. Time Shoes*/
/*Page 4*/
('1070072', '3600', 'Shoes'),	/*Cutie Farmer Sneakers*/
('1070073', '4700', 'Shoes'),	/*Bloody Sneakers*/
('1070074', '5600', 'Shoes'),	/*Soldier Shoes*/
('1070075', '3400', 'Shoes'),	/*Time Master Shoes*/
('1070076', '4000', 'Shoes'),	/*Red Santa Boots*/
('1070077', '3800', 'Shoes'),	/*Mr. Time Shoes*/
('1070078', '7100', 'Shoes'),	/*Concert Muse Shoes*/
('1070079', '5600', 'Shoes'),	/*Sky Blue Spring Sandals*/
('1070057', '8800', 'Shoes'),	/*Shadow Sandals*/
/*Page 5*/
('1070059', '3600', 'Shoes'),	/*Rainbow Picnic Shoes*/
('1070060', '4700', 'Shoes'),	/*[[FROZEN CONTENT]] Kristoff Shoes*/
('1070061', '8800', 'Shoes'),	/*Glass Sneakers*/
('1070096', '6000', 'Shoes'),	/*Santa Boots*/
('1070097', '6400', 'Shoes'),	/*Flowery Path*/
('1070098', '4700', 'Shoes'),	/*Carbon Wing Boots*/
('1070099', '5200', 'Shoes'),	/*Little Darling Shoes*/
('1070100', '6300', 'Shoes'),	/*Majestic Moonlight Oxfords*/
('1070101', '3600', 'Shoes'),	/*Majestic Moonlight Oxfords*/
/*Page 6*/
('1070103', '7400', 'Shoes'),	/*Shinsoo's Steps*/
('1070105', '4900', 'Shoes'),	/*Necrotic Boots*/
('1070106', '4000', 'Shoes'),	/*Warrior's Steps*/
('1070107', '5600', 'Shoes'),	/*Camelia Loafers*/
('1070108', '3200', 'Shoes'),	/*Santa Boy Boots*/
('1070109', '5600', 'Shoes'),	/*Santa Boots*/
('1070110', '4000', 'Shoes'),	/*Snappy Delivery Shoes*/
('1070080', '3600', 'Shoes'),	/*Navillera Loafers*/
('1070081', '7600', 'Shoes'),	/*Navillera Loafers*/
/*Page 7*/
('1070082', '6300', 'Shoes'),	/*Pure Angel Shoes*/
('1070083', '3200', 'Shoes'),	/*Blueberry Shoes*/
('1070084', '4000', 'Shoes'),	/*Sweet Sugar Shoes*/
('1070085', '7400', 'Shoes'),	/*Constellation Shoes*/
('1070086', '4900', 'Shoes'),	/*Fancy Dance Shoes*/
('1070087', '3400', 'Shoes'),	/*Fancy Dance Shoes*/
('1070088', '4900', 'Shoes'),	/*Cherry Strut*/
('1070089', '5000', 'Shoes'),	/*Light as a Feather*/
('1070090', '7600', 'Shoes'),	/*Wedding Loafers*/
/*Page 8*/
('1070091', '3800', 'Shoes'),	/*Crimson Fate Shoes*/
('1070092', '6300', 'Shoes'),	/*Crimson Fate Shoes*/
('1070093', '5200', 'Shoes'),	/*Music Stairs*/
('1070094', '8800', 'Shoes'),	/*Spooky Shoes*/
('1070095', '5600', 'Shoes'),	/*Iron Mace Shoes*/
('1071000', '5200', 'Shoes'),	/*Blue Loose Sox*/
('1071001', '4700', 'Shoes'),	/*Red Loose Sox*/
('1071002', '3400', 'Shoes'),	/*Blue Gomushin*/
('1071003', '7400', 'Shoes'),	/*Red Santa Boots*/
/*Page 9*/
('1071004', '7100', 'Shoes'),	/*Pink Nurse Shoes*/
('1071005', '4000', 'Shoes'),	/*White Nurse Shoes*/
('1071006', '7400', 'Shoes'),	/*SF Ninja Shoes*/
('1071007', '6300', 'Shoes'),	/*Bunny Boots*/
('1071024', '5000', 'Shoes'),	/*Black Dress Shoes [f]*/
('1071025', '5200', 'Shoes'),	/*Paris Wingtips [F]*/
('1071026', '6400', 'Shoes'),	/*White High Top*/
('1071030', '3600', 'Shoes'),	/*Twinkling Girl Glow Shoes*/
('1071031', '7600', 'Shoes'),	/*Pink Angel Wing Shoes*/
/*Page 10*/
('1071032', '7600', 'Shoes'),	/*Red Ribbon Shoes*/
('1071036', '3800', 'Shoes'),	/*Garnet-Studded Boots*/
('1071037', '6000', 'Shoes'),	/*Cygnus Sandals*/
('1071008', '3200', 'Shoes'),	/*Kimono Shoes (F)*/
('1071009', '3600', 'Shoes'),	/*Blue Western Walkers*/
('1071010', '6400', 'Shoes'),	/*Sea Queen Sandals*/
('1071011', '4700', 'Shoes'),	/*Race Queen Boots*/
('1071012', '7400', 'Shoes'),	/*Diao Chan Shoes*/
('1071013', '7600', 'Shoes'),	/*White Cat Shoes*/
/*Page 11*/
('1071014', '8800', 'Shoes'),	/*Black Cat Shoes*/
('1071015', '5000', 'Shoes'),	/*Maid Shoes*/
('1071016', '5200', 'Shoes'),	/*Santa Girl Boots*/
('1071017', '3600', 'Shoes'),	/*Leopard Print Shoes*/
('1071018', '6000', 'Shoes'),	/*Brown Leather Boots*/
('1071019', '3400', 'Shoes'),	/*Lunar Celebration Pumps*/
('1071020', '4900', 'Shoes'),	/*Veras Heels*/
('1071021', '4000', 'Shoes'),	/*Gothic Boots*/
('1071040', '8800', 'Shoes'),	/*Red Ribbon Shoes*/
/*Page 12*/
('1071044', '6300', 'Shoes'),	/*Pinky Pretty Gomushin*/
('1071048', '4700', 'Shoes'),	/*Alps Girl Shoes*/
('1071088', '5000', 'Shoes'),	/*Ms. Time Shoes*/
('1071089', '5600', 'Shoes'),	/*Pure Farmer Sandals*/
('1071090', '7400', 'Shoes'),	/*Bloody Heels*/
('1071091', '5200', 'Shoes'),	/*Soldier Shoes*/
('1071092', '6400', 'Shoes'),	/*Time Mistress Shoes*/
('1071093', '8800', 'Shoes'),	/*Red Santa Boots*/
('1071094', '3200', 'Shoes'),	/*Ms. Time Shoes*/
/*Page 13*/
('1071095', '7600', 'Shoes'),	/*Concert Muse Heels*/
('1071096', '4000', 'Shoes'),	/*Pink Spring Sandals*/
('1071097', '3800', 'Shoes'),	/*Navillera Flats*/
('1071098', '3800', 'Shoes'),	/*Navillera Flats*/
('1071099', '6000', 'Shoes'),	/*Angel Rose Shoes*/
('1071100', '5600', 'Shoes'),	/*Lemon Shoes*/
('1071101', '4900', 'Shoes'),	/*Sweet Sugar Shoes*/
('1071102', '6000', 'Shoes'),	/*Constellation Heels*/
('1071103', '7400', 'Shoes'),	/*Brilliant Dance Shoes*/
/*Page 14*/
('1071074', '6000', 'Shoes'),	/*Shadow Garter*/
('1071076', '7600', 'Shoes'),	/*Colorful Picnic Shoes*/
('1071077', '5600', 'Shoes'),	/*[[FROZEN CONTENT]] Elsa Heels*/
('1071078', '3800', 'Shoes'),	/*Glass Slippers*/
('1071080', '3800', 'Shoes'),	/*Ribbon Angel Shoes*/
('1071081', '3200', 'Shoes'),	/*Pink Macaron Shoes*/
('1071082', '7400', 'Shoes'),	/*Santa Girl Boots*/
('1071083', '4900', 'Shoes'),	/*Cozy Snow Flower*/
('1071084', '5000', 'Shoes'),	/*The Kingdom Blue Heels of Queen*/
/*Page 15*/
('1071085', '3400', 'Shoes'),	/*Soaring Cloud*/
('1071087', '5200', 'Shoes'),	/*Yeonhwa School Shoes*/
('1071120', '4000', 'Shoes'),	/*Empress's Steps*/
('1071121', '4700', 'Shoes'),	/*Necrotic Shoes*/
('1071122', '5000', 'Shoes'),	/*Warrior's Steps*/
('1071123', '7100', 'Shoes'),	/*Camelia Pumps*/
('1071124', '4700', 'Shoes'),	/*Santa Girl Boots*/
('1071125', '5600', 'Shoes'),	/*Santa Boots*/
('1071126', '3200', 'Shoes'),	/*Snappy Delivery Shoes*/
/*Page 16*/
('1071104', '4000', 'Shoes'),	/*Brilliant Dance Shoes*/
('1071105', '6400', 'Shoes'),	/*Cherry Sashay*/
('1071106', '6400', 'Shoes'),	/*Swift as a Bird*/
('1071107', '5000', 'Shoes'),	/*Wedding Pumps*/
('1071108', '5600', 'Shoes'),	/*Crimson Fate Shoes*/
('1071109', '4900', 'Shoes'),	/*Crimson Fate Shoes*/
('1071110', '7100', 'Shoes'),	/*Music Stairs*/
('1071111', '5600', 'Shoes'),	/*Spooky Heels*/
('1071112', '6000', 'Shoes'),	/*Iron Mace Shoes*/
/*Page 17*/
('1071113', '3200', 'Shoes'),	/*Santa Boots*/
('1071114', '3600', 'Shoes'),	/*Flowery Path*/
('1071115', '4000', 'Shoes'),	/*Carbon Wing Boots*/
('1071116', '3800', 'Shoes'),	/*Little Darling Shoes*/
('1071117', '5600', 'Shoes'),	/*Shimmering Starlight Pumps*/
('1071118', '8800', 'Shoes'),	/*Shimmering Starlight Pumps*/
('1072010', '6300', 'Shoes'),	/*Black Dress Shoes*/
('1072013', '8800', 'Shoes'),	/*Red Air H's*/
('1072014', '4900', 'Shoes'),	/*Camping Boots*/
/*Page 18*/
('1072057', '3600', 'Shoes'),	/*Blue Air H's*/
('1072058', '7600', 'Shoes'),	/*Black Air H's*/
('1072088', '5200', 'Shoes'),	/*Cowboy Boots*/
('1072092', '3200', 'Shoes'),	/*Yellow Flippers*/
('1072093', '3800', 'Shoes'),	/*Blue Flippers*/
('1072094', '3400', 'Shoes'),	/*Yellow Rain Boots*/
('1072095', '5200', 'Shoes'),	/*Sky Blue Rain Boots*/
('1072096', '3400', 'Shoes'),	/*Red Rain Boots*/
('1072097', '7400', 'Shoes'),	/*Green Rain Boots*/
/*Page 19*/
('1072098', '5600', 'Shoes'),	/*Blue Baseball Cleats*/
('1072099', '4700', 'Shoes'),	/*Red Baseball Cleats*/
('1072100', '6300', 'Shoes'),	/*Black Baseball Cleats*/
('1072111', '7600', 'Shoes'),	/*Black Leather Boots*/
('1072153', '3800', 'Shoes'),	/*Transparent Shoes*/
('1072176', '4900', 'Shoes'),	/*Military Boots*/
('1072180', '5000', 'Shoes'),	/*Flipper Boots*/
('1072181', '7400', 'Shoes'),	/*Green Ting Slippers*/
('1072186', '4900', 'Shoes'),	/*Gold Kitty Slippers*/
/*Page 20*/
('1072187', '5600', 'Shoes'),	/*Blue Marble Slippers*/
('1072188', '5200', 'Shoes'),	/*Red Marble Slippers*/
('1072189', '6300', 'Shoes'),	/*Bunny Slippers*/
('1072190', '6400', 'Shoes'),	/*Blue B-ball Sneakers*/
('1072191', '6300', 'Shoes'),	/*Orange B-ball Sneakers*/
('1072175', '7100', 'Shoes'),	/*Ninja Shoes*/
('1072217', '6000', 'Shoes'),	/*Beige Golashes*/
('1072218', '6400', 'Shoes'),	/*Sky Blue Golashes*/
('1072219', '6300', 'Shoes'),	/*Pink Golashes*/
/*Page 21*/
('1072199', '3600', 'Shoes'),	/*Ragged Gomushin*/
('1072200', '7600', 'Shoes'),	/*Brown Dress Shoes*/
('1072201', '5600', 'Shoes'),	/*Red Leather Boots*/
('1072202', '7400', 'Shoes'),	/*M-Forcer Boots*/
('1072240', '7400', 'Shoes'),	/*Big Rabbit Feet*/
('1072241', '7600', 'Shoes'),	/*Liu Bei Shoes*/
('1072242', '3400', 'Shoes'),	/*Cao Cao Shoes*/
('1072243', '6000', 'Shoes'),	/*Sun Quan Shoes*/
('1072244', '8800', 'Shoes'),	/*Red Enamel Shoes*/
/*Page 22*/
('1072245', '5600', 'Shoes'),	/*Blue Enamel Shoes*/
('1072246', '8800', 'Shoes'),	/*Pink Sneakers*/
('1072247', '5000', 'Shoes'),	/*Hunting Boots*/
('1072250', '5200', 'Shoes'),	/*Horoscope Shoes*/
('1072251', '4700', 'Shoes'),	/*Pro-Cat Sticker*/
('1072252', '6400', 'Shoes'),	/*Snowboard Boots*/
('1072253', '3200', 'Shoes'),	/*Red Santa Shoes*/
('1072254', '7600', 'Shoes'),	/*Football Cleats (Home)*/
('1072255', '8800', 'Shoes'),	/*Football Cleats (Away)*/
/*Page 23*/
('1072230', '5200', 'Shoes'),	/*Black Boxing Shoes*/
('1072231', '4000', 'Shoes'),	/*Blue Boxing Shoes*/
('1072232', '4900', 'Shoes'),	/*Red Boxing Shoes*/
('1072233', '3200', 'Shoes'),	/*Bear Shoes*/
('1072234', '6300', 'Shoes'),	/*Bubbling Slippers*/
('1072235', '7400', 'Shoes'),	/*Slime Slippers*/
('1072236', '7600', 'Shoes'),	/*Guan Yu Shoes*/
('1072237', '5200', 'Shoes'),	/*Zhu-Ge-Liang Shoes*/
('1072274', '5200', 'Shoes'),	/*Moon Bunny Paws*/
/*Page 24*/
('1072276', '3800', 'Shoes'),	/*Booster Shoes*/
('1072277', '5000', 'Shoes'),	/*Red Elf shoes*/
('1072278', '8800', 'Shoes'),	/*Rudolph Slippers*/
('1072279', '6000', 'Shoes'),	/*Super Booster Shoes*/
('1072280', '6300', 'Shoes'),	/*Golden Shoes*/
('1072281', '3800', 'Shoes'),	/*Sachiel Shoes*/
('1072282', '3800', 'Shoes'),	/*Veamoth Shoes*/
('1072283', '7400', 'Shoes'),	/*Janus Shoes*/
('1072284', '6300', 'Shoes'),	/*Zhu Ba Jie Shoes*/
/*Page 25*/
('1072256', '5200', 'Shoes'),	/*Teddy Bear Shoes*/
('1072257', '7600', 'Shoes'),	/*Puppy Slippers*/
('1072258', '8800', 'Shoes'),	/*Gray Kitty Slippers*/
('1072259', '7400', 'Shoes'),	/*Chick Slippers*/
('1072260', '8800', 'Shoes'),	/*Penguin Slippers*/
('1072265', '3800', 'Shoes'),	/*Blue Soccer Cleats*/
('1072266', '5600', 'Shoes'),	/*Black Soccer Cleats*/
('1072267', '7100', 'Shoes'),	/*Red Soccer Cleats*/
('1072270', '4900', 'Shoes'),	/*White Rabbit Shoes*/
/*Page 26*/
('1072271', '4000', 'Shoes'),	/*Black Cat Shoes*/
('1072336', '4900', 'Shoes'),	/*Soccer Cleats*/
('1072337', '5600', 'Shoes'),	/*Fluffy Slippers*/
('1072341', '3800', 'Shoes'),	/*Orange Sneakz*/
('1072347', '6300', 'Shoes'),	/*Olive Green Kicks*/
('1072348', '5000', 'Shoes'),	/*Elephant Slippers*/
('1072349', '3800', 'Shoes'),	/*Green Sneakz*/
('1072350', '6300', 'Shoes'),	/*Black High Tops*/
('1072351', '8800', 'Shoes'),	/*Green Ankle Boots for Transformation*/
/*Page 27*/
('1072322', '5600', 'Shoes'),	/*Rollerskates*/
('1072323', '5200', 'Shoes'),	/*Starry Slippers*/
('1072324', '4000', 'Shoes'),	/*Piggy Slippers*/
('1072325', '3800', 'Shoes'),	/*Red Slime Slippers*/
('1072326', '6300', 'Shoes'),	/*Yellow Slime Slippers*/
('1072327', '6300', 'Shoes'),	/*Tania En Fuego*/
('1072328', '3800', 'Shoes'),	/*Mercury Lightning*/
('1072329', '4000', 'Shoes'),	/*Flipped Blue High Top*/
('1072330', '3800', 'Shoes'),	/*Black Classic Sneakers*/
/*Page 28*/
('1072331', '3800', 'Shoes'),	/*Velcro High Tops*/
('1072332', '4900', 'Shoes'),	/*Black Enamel Shoes*/
('1072333', '6300', 'Shoes'),	/*Green Classic Sneakers*/
('1072334', '4900', 'Shoes'),	/*Red Checkered Sneakers*/
('1072335', '7400', 'Shoes'),	/*Natural Golashes*/
('1072370', '6000', 'Shoes'),	/*Gaga Shoes*/
('1072371', '4700', 'Shoes'),	/*Custom Blue High Tops*/
('1072373', '6000', 'Shoes'),	/*Purple Rainbow Sneaks*/
('1072374', '6300', 'Shoes'),	/*Lace Long Boots*/
/*Page 29*/
('1072377', '5200', 'Shoes'),	/*Treacherous Wolf Shoes*/
('1072379', '6300', 'Shoes'),	/*Yellow Rainbow Sneaks*/
('1072380', '4700', 'Shoes'),	/*White & Blue Sandals*/



/*Page 1*/
('1072381', '7600', 'Shoes 2'),	/*Aran Combat Shoes*/
('1072382', '4000', 'Shoes 2'),	/*Brave Soldier Shoes */
('1072352', '3800', 'Shoes 2'),	/*Red Silky Boots for Transformation*/
('1072353', '3800', 'Shoes 2'),	/*White Ninja Sandals for Transformation*/
('1072354', '5600', 'Shoes 2'),	/*Black Voyson Shoes for Transformation*/
('1072367', '5200', 'Shoes 2'),	/*Cutie Birk Shoes*/
('1072404', '6000', 'Shoes 2'),	/*Alchemist Shoes*/
('1072405', '7600', 'Shoes 2'),	/*Ninja Shoes*/
('1072406', '5000', 'Shoes 2'),	/*Chaos Metallic Shoes*/
/*Page 2*/
('1072407', '3400', 'Shoes 2'),	/*Kawaii Kitty Shoes*/
('1072408', '6300', 'Shoes 2'),	/*Maple Racing Shoes*/
('1072410', '3400', 'Shoes 2'),	/*Super Booster Shoes*/
('1072384', '3200', 'Shoes 2'),	/*Bling Bling Shoes*/
('1072385', '4700', 'Shoes 2'),	/*White Slipshoes*/
('1072386', '5200', 'Shoes 2'),	/*Black Geda*/
('1072387', '6300', 'Shoes 2'),	/*Pink Geda*/
('1072388', '4000', 'Shoes 2'),	/*Stripe Knee Socks*/
('1072389', '6000', 'Shoes 2'),	/*Black Platform Boots*/
/*Page 3*/
('1072392', '7100', 'Shoes 2'),	/*Red Ankle-Strap Shoes*/
('1072393', '4700', 'Shoes 2'),	/*We Care! Shoes*/
('1072394', '7400', 'Shoes 2'),	/*Pink Polka-Dotted Boots*/
('1072395', '5600', 'Shoes 2'),	/*Mix-n-Match Sneakers*/
('1072396', '6300', 'Shoes 2'),	/*Gaga Shoes*/
('1072397', '5200', 'Shoes 2'),	/*Idol Star Snickers*/
('1072398', '6000', 'Shoes 2'),	/*Cursed Golden shoes*/
('1072433', '6300', 'Shoes 2'),	/*Passionate Flats*/
('1072437', '4000', 'Shoes 2'),	/*Pink Bean Shoes*/
/*Page 4*/
('1072438', '3800', 'Shoes 2'),	/*Green Leaf Shoes*/
('1072439', '4000', 'Shoes 2'),	/*Strawberry Shoes*/
('1072440', '8800', 'Shoes 2'),	/*Cat Set Boots*/
('1072441', '6000', 'Shoes 2'),	/*Dual Blade Boots*/
('1072443', '4700', 'Shoes 2'),	/*Evan Golden Boots*/
('1072444', '7600', 'Shoes 2'),	/*Hawkeye Ocean Boots*/
('1072417', '6000', 'Shoes 2'),	/*Clown Shoes*/
('1072425', '6300', 'Shoes 2'),	/*Freud's Shoes*/
('1072426', '4000', 'Shoes 2'),	/*Shiny Anklet*/
/*Page 5*/
('1072464', '6300', 'Shoes 2'),	/*Combat Boots*/
('1072465', '5200', 'Shoes 2'),	/*King Crow Kimono Shoes*/
('1072466', '6300', 'Shoes 2'),	/*Henesys Academy Shoes*/
('1072467', '4900', 'Shoes 2'),	/*Pilot Boots*/
('1072468', '5600', 'Shoes 2'),	/*Lolita Knee Socks Shoes*/
('1072469', '3600', 'Shoes 2'),	/*Striped Leggings (Pink)*/
('1072470', '3800', 'Shoes 2'),	/*Striped Leggings (Blue)*/
('1072478', '4900', 'Shoes 2'),	/*Brown Ankle Boots*/
('1072448', '6300', 'Shoes 2'),	/*Rainbow Boots*/
/*Page 6*/
('1072454', '4000', 'Shoes 2'),	/*Oz Magic Boots*/
('1072456', '5200', 'Shoes 2'),	/*Evan Boots*/
('1072457', '6000', 'Shoes 2'),	/*Blue Slip-Ons*/
('1072461', '6400', 'Shoes 2'),	/*Battle Mage Boots*/
('1072462', '4900', 'Shoes 2'),	/*Wild Hunter Boots*/
('1072507', '8800', 'Shoes 2'),	/*Pearl Anklet*/
('1072508', '3800', 'Shoes 2'),	/*Winter 2010 Moon Bunny Shoes*/
('1072509', '6300', 'Shoes 2'),	/*Red's Shoes*/
('1072482', '5600', 'Shoes 2'),	/*Panda Slippers*/
/*Page 7*/
('1072483', '7100', 'Shoes 2'),	/*White Boots*/
('1072484', '6000', 'Shoes 2'),	/*Black Kitty Slippers*/
('1072495', '7600', 'Shoes 2'),	/*Blue Sneakers*/
('1072529', '3200', 'Shoes 2'),	/*Pink Elephant Slippers*/
('1072531', '3600', 'Shoes 2'),	/*Koala Slippers*/
('1072532', '7600', 'Shoes 2'),	/*MSE 4 Years & Unstoppable Shoes*/
('1072536', '3400', 'Shoes 2'),	/*Starling Shoes*/
('1072537', '4700', 'Shoes 2'),	/*Crow Shoes*/
('1072514', '8800', 'Shoes 2'),	/*Pink Winged Shoes*/
/*Page 8*/
('1072515', '4000', 'Shoes 2'),	/*Furry Lion Slippers*/
('1072516', '5200', 'Shoes 2'),	/*Rookie Chick Slippers*/
('1072517', '6000', 'Shoes 2'),	/*Winged Shoes*/
('1072520', '4700', 'Shoes 2'),	/*6th Anniversary Item*/

('1072627', '8800', 'Shoes 2'),	/*Dark Force Boots */
('1072628', '8800', 'Shoes 2'),	/*Elven Spirit Boots */
('1072631', '8800', 'Shoes 2'),	/*Urban Pirate Shoes*/
('1072632', '5200', 'Shoes 2'),	/*Ebony Pimpernel Boots*/
/*Page 9*/
('1072633', '3200', 'Shoes 2'),	/*GM Haku's Pirate Shoes*/
('1072637', '4000', 'Shoes 2'),	/*Hades Shoes*/
('1072609', '6400', 'Shoes 2'),	/*Ribboned Justice Boots*/
('1072613', '8800', 'Shoes 2'),	/*Western Cowboy Boots*/
('1072622', '5000', 'Shoes 2'),	/*Orchid's Black Wing Shoes*/
('1072658', '8800', 'Shoes 2'),	/*Glowing Foot Ring*/
('1072662', '3600', 'Shoes 2'),	/*Lucia Shoes*/
('1072663', '6300', 'Shoes 2'),	/*GM Nori's Wing Shoes*/
('1072646', '5200', 'Shoes 2'),	/*Elven Spirit Boots*/
/*Page 10*/
('1072647', '6400', 'Shoes 2'),	/*Kerning Engineering School Shoes*/
('1072648', '4900', 'Shoes 2'),	/*Ellinia Magic School Shoes*/
('1072649', '6400', 'Shoes 2'),	/*Mu Lung Dojo Training Shoes*/
('1072650', '4700', 'Shoes 2'),	/*Blue Dragon Shoes*/
('1072651', '3800', 'Shoes 2'),	/*Red Dragon Shoes*/
('1072652', '3800', 'Shoes 2'),	/*Intergalactic Shoes*/
('1072676', '3200', 'Shoes 2'),	/*The Onmyouji's Shoes*/
('1072680', '3200', 'Shoes 2'),	/*Blue Arabian Shoes*/
('1072681', '3400', 'Shoes 2'),	/*Red Arabian Shoes*/
/*Page 11*/
('1072729', '6000', 'Shoes 2'),	/*Jett's Boots*/
('1072708', '3400', 'Shoes 2'),	/*Cool Summer Flippers*/
('1072756', '8800', 'Shoes 2'),	/*Hyper Kitten Mittens*/
('1072757', '4900', 'Shoes 2'),	/*[MS Custom] Red Rain Boots*/
('1072758', '3200', 'Shoes 2'),	/*Kitty Slippers*/
('1072759', '7600', 'Shoes 2'),	/*Chick Slippers*/
('1072760', '3400', 'Shoes 2'),	/*Halloween Leopard Shoes*/
('1072742', '3200', 'Shoes 2'),	/*Nero Paws*/
('1072748', '3600', 'Shoes 2'),	/*Exotic Festival Shoes*/
/*Page 12*/
('1072749', '6300', 'Shoes 2'),	/*Bubble Bubble Chocolate Shoes*/
('1072750', '3600', 'Shoes 2'),	/*The Bladed Falcon's Shoes*/
('1072791', '6400', 'Shoes 2'),	/*Green Dinosaur Shoes*/
('1072770', '5600', 'Shoes 2'),	/*Dark Devil Shoes*/
('1072771', '6400', 'Shoes 2'),	/*Slither Style High-Tops*/
('1072772', '5200', 'Shoes 2'),	/*Pious Shaman Stockings*/
('1072773', '6400', 'Shoes 2'),	/*Red Strap Clogs*/
('1072778', '7400', 'Shoes 2'),	/*Dark Force Boots*/
('1072779', '8800', 'Shoes 2'),	/*Featherly Angel Shoes*/
/*Page 13*/
('1072780', '7100', 'Shoes 2'),	/*Blue Point Kitty Shoes*/
('1072781', '6000', 'Shoes 2'),	/*Kitty Shoes*/
('1072782', '3800', 'Shoes 2'),	/*Xenon Neo-Tech Shoes*/
('1072783', '6000', 'Shoes 2'),	/*Lotus's Black Wing Shoes*/
('1072816', '6000', 'Shoes 2'),	/*Succubus Shoes*/
('1072817', '5000', 'Shoes 2'),	/*Blavy Angel Shoes*/
('1072820', '8800', 'Shoes 2'),	/*Funky Shoes*/
('1072821', '3200', 'Shoes 2'),	/*Golden Bell Shoes*/
('1072823', '5600', 'Shoes 2'),	/*Golf Shoes*/
/*Page 14*/
('1072824', '4000', 'Shoes 2'),	/*Angel Wing Shoes*/
('1072830', '3400', 'Shoes 2'),	/*GM Daejang's Lucia Shoes*/
('1072831', '5000', 'Shoes 2'),	/*Flame Boots*/
('1072800', '7400', 'Shoes 2'),	/*Mid High Golf Shoes*/
('1072803', '3200', 'Shoes 2'),	/*Purple Dinosaur Shoes*/
('1072807', '5200', 'Shoes 2'),	/*Hilla's Shoes*/
('1072808', '3800', 'Shoes 2'),	/*Ramling Slippers*/
('1072809', '5600', 'Shoes 2'),	/*Kerning Technical High Shoes*/
('1072810', '5000', 'Shoes 2'),	/*Ellinia Magic Academy Shoes*/
/*Page 15*/
('1072811', '4700', 'Shoes 2'),	/*Mu Lung Academy Training Shoes*/
('1072812', '7400', 'Shoes 2'),	/*Kimono Sandals*/
('1072813', '8800', 'Shoes 2'),	/*Kimono Shoes*/
('1072848', '4700', 'Shoes 2'),	/*Bloody Garter*/
('1072851', '3200', 'Shoes 2'),	/*Bubble Bubble Shoes*/
('1072852', '3800', 'Shoes 2'),	/*Superstar Shoes*/
('1072854', '3600', 'Shoes 2'),	/*フューチャーロイドスキンシューズ*/
('1072855', '3800', 'Shoes 2'),	/*フューチャーロイドネオンブーツ*/
('1072856', '6300', 'Shoes 2'),	/*Dawn Bear Comfy Boots*/
/*Page 16*/
('1072857', '6400', 'Shoes 2'),	/*Odette Ballet Slippers*/
('1072858', '3200', 'Shoes 2'),	/*Odile Ballet Slippers*/
('1072859', '3200', 'Shoes 2'),	/*Cobalt Zero Shoes*/
('1072860', '3800', 'Shoes 2'),	/*Star Winkle*/
('1072862', '5000', 'Shoes 2'),	/*Heart Pudding Slippers*/
('1072863', '3600', 'Shoes 2'),	/*Stirkandbock Sandals*/
('1072832', '7100', 'Shoes 2'),	/*Pink Bean Slippers*/
('1072836', '4700', 'Shoes 2'),	/*Baseball Shoes*/
('1072838', '5600', 'Shoes 2'),	/*Panda Slippers*/
/*Page 17*/
('1072839', '4900', 'Shoes 2'),	/*Shoes of Life*/
('1072840', '3400', 'Shoes 2'),	/*Shoes of Destruction*/
('1072843', '3400', 'Shoes 2'),	/*Bubble Flip Flops*/
('1072880', '7600', 'Shoes 2'),	/*Aran's Boots*/
('1072881', '3400', 'Shoes 2'),	/*Brave Aran's Boots*/
('1072883', '3200', 'Shoes 2'),	/*Heathcliff's Boots*/
('1072884', '7100', 'Shoes 2'),	/*Yui's Anklet*/
('1072889', '5600', 'Shoes 2'),	/*Snake High-tops*/
('1072890', '3600', 'Shoes 2'),	/*Mr. K's Cat Shoes*/
/*Page 18*/
('1072864', '7600', 'Shoes 2'),	/*PSY Shoes*/
('1072865', '5000', 'Shoes 2'),	/*Camellia Flower Geta*/
('1072866', '5600', 'Shoes 2'),	/*Chocoram Doll Shoes*/
('1072867', '3400', 'Shoes 2'),	/*Puffram Shoes*/
('1072868', '6400', 'Shoes 2'),	/*Powder Flats*/
('1072869', '3200', 'Shoes 2'),	/*Princess of Time Heels*/
('1072871', '7400', 'Shoes 2'),	/*Halloweenroid Boots*/
('1072873', '7600', 'Shoes 2'),	/*Asuna's Shoes*/
('1072875', '4900', 'Shoes 2'),	/*Leafa's Shoes*/
/*Page 19*/
('1072876', '4000', 'Shoes 2'),	/*Cacao Bear Shoes*/
('1072877', '5200', 'Shoes 2'),	/*Dark Devil Shoes*/
('1072878', '6400', 'Shoes 2'),	/*Vampire Phantom Boots*/
('1072879', '6400', 'Shoes 2'),	/*Freud's Shoes*/
('1072913', '4900', 'Shoes 2'),	/*Blue Slippers*/
('1072916', '6300', 'Shoes 2'),	/*Guardian Shoes*/
('1072917', '4900', 'Shoes 2'),	/*Cutie Horse Shoes*/
('1072918', '4900', 'Shoes 2'),	/*Pink Flowery Shoes*/
('1072919', '6000', 'Shoes 2'),	/*Blue Butterfly Shoes*/
/*Page 20*/
('1072920', '3200', 'Shoes 2'),	/*Ghost Bride's Shoes*/
('1072921', '3600', 'Shoes 2'),	/*Fancy Magician Shoes*/
('1072922', '3600', 'Shoes 2'),	/*Chef Shoes*/
('1072923', '6000', 'Shoes 2'),	/*Contemporary Chic Shoes*/
('1072924', '4900', 'Shoes 2'),	/*Nurse Boots*/
('1072925', '3600', 'Shoes 2'),	/*Doctor Boots*/
('1072926', '8800', 'Shoes 2'),	/*Colorful Sneakers*/
('1072897', '4700', 'Shoes 2'),	/*Blue Moccasin*/
('1072901', '4900', 'Shoes 2'),	/*Moonlight Marble Shoes*/
/*Page 21*/
('1072908', '7100', 'Shoes 2'),	/*Pony Wing Shoes*/
('1072909', '7400', 'Shoes 2'),	/*Purple Rainbow Sneaks*/
('1072910', '5000', 'Shoes 2'),	/*Cacao Bear Shoes*/
('1072944', '5600', 'Shoes 2'),	/*暗夜精灵战靴*/
('1072945', '5200', 'Shoes 2'),	/*隐武士战靴*/
('1072949', '4700', 'Shoes 2'),	/*Red Pony Sneakers*/
('1072950', '7400', 'Shoes 2'),	/*Blue Pony Sneakers*/
('1072951', '8800', 'Shoes 2'),	/*Hula Hula Beaded Anklet*/
('1072934', '3200', 'Shoes 2'),	/*Rainbow Sneakers*/
/*Page 22*/
('1072942', '7600', 'Shoes 2'),	/*Island Travel Shoes*/
('1072943', '6300', 'Shoes 2'),	/*Humming Shoes*/
('1072978', '4700', 'Shoes 2'),	/*Glowy Leather Shoes*/
('1072979', '7600', 'Shoes 2'),	/*Bright Angel Boots*/
('1072980', '7600', 'Shoes 2'),	/*Dark Devil Boots*/
('1073008', '7400', 'Shoes 2'),	/*Scarlet Sneakers*/
('1073009', '3400', 'Shoes 2'),	/*Corn Shoes*/
('1073011', '7400', 'Shoes 2'),	/*Cheerleader Shoes*/
('1073013', '6000', 'Shoes 2'),	/*Wiggly Puppy Shoes*/
/*Page 23*/
('1073014', '3600', 'Shoes 2'),	/*Pink Puppy Shoes*/
('1073019', '3400', 'Shoes 2'),	/*Dinofrog Shoes*/
('1073022', '3800', 'Shoes 2'),	/*Pink Kitty Blue Sneakers*/
('1072998', '5200', 'Shoes 2'),	/*Rabbit-Bear Slippers*/
('1072999', '5600', 'Shoes 2'),	/*Ribbon Red Shoes*/
('1073040', '5000', 'Shoes 2'),	/*Maple Mouse Shoes*/
('1073041', '3800', 'Shoes 2'),	/*Black Forte Boots*/
('1073044', '6300', 'Shoes 2'),	/*-*/
('1073046', '7100', 'Shoes 2'),	/*Baby Ram Slippers (Blue)*/
/*Page 24*/
('1073047', '6400', 'Shoes 2'),	/*Baby Ram Slippers (Pink)*/
('1073050', '7400', 'Shoes 2'),	/*Ring Sneakers*/
('1073051', '3800', 'Shoes 2'),	/*Ryan D Shoes*/
('1073052', '6300', 'Shoes 2'),	/*Sierra Grace Boots */
('1073055', '7100', 'Shoes 2'),	/*Akarin's Flowery Shoes*/
('1073024', '4700', 'Shoes 2'),	/*Red Shoes*/
('1073025', '5600', 'Shoes 2'),	/*Hatchling Shoes*/
('1073027', '7400', 'Shoes 2'),	/*ODM Gear*/
('1073036', '5600', 'Shoes 2'),	/*Blue Bird Shoes*/
/*Page 25*/
('1073037', '7600', 'Shoes 2'),	/*Cutie Bunny Shoes*/
('1073038', '7400', 'Shoes 2'),	/*Soft Pink Boots*/
('1073039', '4700', 'Shoes 2'),	/*Cutie Birk Shoes*/
('1073074', '4700', 'Shoes 2'),	/*Schwarzer Boots*/
('1073075', '5000', 'Shoes 2'),	/*Mint Kitty Slippers*/
('1073079', '3800', 'Shoes 2'),	/*Mousy Bunny Jelly Flops*/
('1073080', '5000', 'Shoes 2'),	/*Black Sailor Shoes*/
('1073082', '7600', 'Shoes 2'),	/*Odette Ballet Slippers*/
('1073084', '4900', 'Shoes 2'),	/*Kinesis Shoes*/
/*Page 26*/
('1073085', '7600', 'Shoes 2'),	/*Kinesis Shoes*/
('1073056', '4000', 'Shoes 2'),	/*Blooming Spring*/
('1073058', '6300', 'Shoes 2'),	/*Naughty Boy Shoes*/
('1073059', '6400', 'Shoes 2'),	/*Cat Knee Socks*/
('1073060', '7600', 'Shoes 2'),	/*Noble Blossom Shoes*/
('1073061', '7400', 'Shoes 2'),	/*Pink Blossom Shoes*/
('1073062', '3400', 'Shoes 2'),	/*Cottontail Rabbit Shoes*/
('1073105', '5000', 'Shoes 2'),	/*Exciting Kicks*/
('1073106', '5200', 'Shoes 2'),	/*Polar Booties*/
/*Page 27*/
('1073107', '4700', 'Shoes 2'),	/*Wooden Bell Shoes*/
('1073108', '3200', 'Shoes 2'),	/*Flutter Bell Sandals*/
('1073115', '6000', 'Shoes 2'),	/*Evan Dragon Boots*/
('1073116', '4900', 'Shoes 2'),	/*Evan Dragon Boots*/
('1073117', '3600', 'Shoes 2'),	/*Royal Mercedes Shoes*/
('1073118', '7100', 'Shoes 2'),	/*Royal Mercedes Shoes*/
('1073119', '4700', 'Shoes 2'),	/*Mystic Phantom Shoes*/
('1073088', '6300', 'Shoes 2'),	/*Bluebird Shoes*/
('1073090', '5600', 'Shoes 2'),	/*White Ursus Slippers*/
/*Page 28*/
('1073091', '7600', 'Shoes 2'),	/*Brown Ursus Slippers*/
('1073092', '5600', 'Shoes 2'),	/*Black Ursus Slippers*/
('1073096', '5000', 'Shoes 2'),	/*Little Vampire Shoes*/

('1073098', '6000', 'Shoes 2'),	/*Arctic Snow Shoes*/
('1073144', '3400', 'Shoes 2'),	/*Shark Bite Shoes*/
('1073145', '8800', 'Shoes 2'),	/*Kitty Follower*/
('1073148', '3200', 'Shoes 2'),	/*Berry Shoes*/
('1073149', '3400', 'Shoes 2'),	/*Noble Maple Shoes*/
/*Page 29*/
('1073150', '4900', 'Shoes 2'),	/*Chicken Cutie Shoes*/
('1073151', '4000', 'Shoes 2'),	/*Hydrangea Shoes*/
('1073120', '7100', 'Shoes 2'),	/*Mystic Phantom Shoes*/



/*Page 1*/
('1073121', '6000', 'Shoes 3'),	/*Winter Aran Boots*/
('1073122', '8800', 'Shoes 3'),	/*Winter Aran Boots*/
('1073123', '6400', 'Shoes 3'),	/*Chiaroscuro Luminous Shoes*/
('1073124', '5000', 'Shoes 3'),	/*Chiaroscuro Luminous Shoes*/
('1073125', '3800', 'Shoes 3'),	/*Secret Shade Boots*/
('1073126', '3400', 'Shoes 3'),	/*Secret Shade Boots*/
('1073127', '3400', 'Shoes 3'),	/*Cozy Fluffy Slippers*/
('1073128', '3800', 'Shoes 3'),	/*Snow Boots*/
('1073129', '8800', 'Shoes 3'),	/*Cozy Fur Shoes*/
/*Page 2*/
('1073132', '3600', 'Shoes 3'),	/*Umbral Shoes*/
('1073133', '3400', 'Shoes 3'),	/*Umbral Boots*/
('1073134', '5000', 'Shoes 3'),	/*Flower Dancer's Sandals*/
('1073135', '7100', 'Shoes 3'),	/*Moon Dancer's Boots*/
('1073168', '3800', 'Shoes 3'),	/*Chained Princess Shoes*/
('1073169', '8800', 'Shoes 3'),	/*Bichon Shoes*/
('1073170', '8800', 'Shoes 3'),	/*Midnight Steps*/
('1073171', '3600', 'Shoes 3'),	/*Midnight Tiptoes*/
('1073172', '3200', 'Shoes 3'),	/*Vampire Phantom Boots*/
/*Page 3*/
('1073175', '5200', 'Shoes 3'),	/*Sweetheart Shoes*/
('1073176', '7600', 'Shoes 3'),	/*Sweetheart Slippers*/
('1073177', '7100', 'Shoes 3'),	/*Emilia's Heels*/
('1073178', '4700', 'Shoes 3'),	/*Subaru's Sneakers*/
('1073179', '6000', 'Shoes 3'),	/*Felt's Shoes*/
('1073180', '5600', 'Shoes 3'),	/*Priscilla's Heels*/
('1073181', '6400', 'Shoes 3'),	/*Winter Bunny Boots (Teal)*/
('1073182', '5200', 'Shoes 3'),	/*Winter Bunny Boots (Pink)*/
('1073183', '5600', 'Shoes 3'),	/*Pumpkin Cookie*/
/*Page 4*/
('1073152', '6000', 'Shoes 3'),	/*Black Scout Shoes*/
('1073153', '3600', 'Shoes 3'),	/*Blaster Shoes*/
('1073154', '3800', 'Shoes 3'),	/*Blaster Shoes*/
('1073155', '5600', 'Shoes 3'),	/*Villain Shoes*/
('1073156', '3600', 'Shoes 3'),	/*Colorful Beach Sandals*/
('1073157', '7100', 'Shoes 3'),	/*Red Cloud Shoes*/
('1073163', '3200', 'Shoes 3'),	/*Starry Night Flowers*/
('1073164', '4000', 'Shoes 3'),	/*Winged Pony Boots*/

/*Page 5*/
('1073167', '3200', 'Shoes 3'),	/*Dark Musician Shoes*/
('1073200', '5000', 'Shoes 3'),	/*Soft Moon*/
('1073201', '7100', 'Shoes 3'),	/*Spring Fairy Shoes*/
('1073203', '7400', 'Shoes 3'),	/*Cape Shoes (M)*/
('1073204', '6300', 'Shoes 3'),	/*Cape Shoes (F)*/
('1073205', '6400', 'Shoes 3'),	/*Strawberry Sneakers*/
('1073212', '6000', 'Shoes 3'),	/*Retro Roller Skates*/
('1073213', '3200', 'Shoes 3'),	/*Starlit Dreamwalkers*/

/*Page 6*/
('1073215', '3200', 'Shoes 3'),	/*Pop Star Shoes (F)*/
('1073184', '8800', 'Shoes 3'),	/*Pumpkin Soup*/
('1073185', '7600', 'Shoes 3'),	/*Chick Shoes*/
('1073186', '5200', 'Shoes 3'),	/*Cluck Cluck Slippers*/
('1073188', '6000', 'Shoes 3'),	/*White Night Sandals*/
('1073189', '6400', 'Shoes 3'),	/*Snow Moon Flower Sandals*/
('1073192', '7600', 'Shoes 3'),	/*Polar Explorer Boots*/
('1073193', '7400', 'Shoes 3'),	/*Sweet Chocolate Dessert Shoes*/
('1073194', '6400', 'Shoes 3'),	/*Sweet Fresh Cream Dessert Shoes*/
/*Page 7*/
('1073195', '7100', 'Shoes 3'),	/*Yellow Chick Shoes (M)*/
('1073196', '7100', 'Shoes 3'),	/*Yellow Chick Shoes (F)*/
('1073237', '6000', 'Shoes 3'),	/*Stitched Sneakers*/
('1073238', '4900', 'Shoes 3'),	/*Rabble Rouser Shoes (F)*/
('1073239', '6000', 'Shoes 3'),	/*Rabble Rouser Shoes (M)*/
('1073240', '5000', 'Shoes 3'),	/*Cat Cafe Kicks (M)*/
('1073241', '3600', 'Shoes 3'),	/*Cat Cafe Kicks (F)*/
('1073242', '3800', 'Shoes 3'),	/*Shadow Tactician Shoes*/
('1073243', '3600', 'Shoes 3'),	/*Puppy Love Samurai Shoes (M)*/
/*Page 8*/
('1073244', '4700', 'Shoes 3'),	/*Puppy Love Samurai Shoes (F)*/
('1073246', '4000', 'Shoes 3'),	/*Gold Strap Shoes*/
('1073247', '5000', 'Shoes 3'),	/*Meow Shoes*/
('1073216', '4700', 'Shoes 3'),	/*Pop Star Shoes (M)*/
('1073217', '6300', 'Shoes 3'),	/*Oxford Shoes*/
('1073218', '3400', 'Shoes 3'),	/*Froggy Rainboots*/
('1073219', '6400', 'Shoes 3'),	/*Napoleonic Boots*/
('1073222', '5600', 'Shoes 3'),	/*Iron Mace Uniform Shoes (F)*/
('1073223', '5200', 'Shoes 3'),	/*Iron Mace Uniform Shoes (M)*/
/*Page 9*/
('1073226', '6400', 'Shoes 3'),	/*Preppy Sprout Boaters*/
('1073228', '6400', 'Shoes 3'),	/*Forest Breeze Sandals*/
('1073229', '4000', 'Shoes 3'),	/*Red Flipflops*/
('1073230', '8800', 'Shoes 3'),	/*Blue Flipflops*/
('1073264', '5600', 'Shoes 3'),	/*Royal Guard Shoes (M)*/
('1073265', '7400', 'Shoes 3'),	/*Red Bear Winter Boots*/
('1073266', '3200', 'Shoes 3'),	/*World of Pink Shoes (M)*/
('1073267', '6300', 'Shoes 3'),	/*World of Pink Shoes (F)*/
('1073268', '5200', 'Shoes 3'),	/*Pink Bear Winter Boots*/
/*Page 10*/
('1073269', '4000', 'Shoes 3'),	/*Alchemist Shoes*/
('1073270', '8800', 'Shoes 3'),	/*Homeless Cat Shoes*/
('1073271', '4900', 'Shoes 3'),	/*Kiddy Crayon Shoes*/
('1073272', '4000', 'Shoes 3'),	/*Tennis Shoes*/
('1073273', '7600', 'Shoes 3'),	/*Raindrop Boots*/
('1073274', '5600', 'Shoes 3'),	/*Super Summer Shoes (F)*/
('1073275', '8800', 'Shoes 3'),	/*Super Summer Shoes (M)*/
('1073276', '3400', 'Shoes 3'),	/*Ark Shoes*/
('1073250', '4700', 'Shoes 3'),	/*Warm Pink Bear Shoes*/
/*Page 11*/
('1073251', '4900', 'Shoes 3'),	/*Warm Blue Bear Shoes*/
('1073252', '3200', 'Shoes 3'),	/*Strawberry Sneakers*/
('1073253', '6400', 'Shoes 3'),	/*Cadena Agent Shoes*/
('1073254', '4000', 'Shoes 3'),	/*Half Bandage*/
('1073255', '8800', 'Shoes 3'),	/*Soft Boots*/
('1073258', '3600', 'Shoes 3'),	/*Soft Snow Slippers*/
('1073259', '6400', 'Shoes 3'),	/*Captain Boots*/
('1073261', '4900', 'Shoes 3'),	/*Chunky Cable-Knit Shoes*/
('1073262', '5200', 'Shoes 3'),	/*Lunar New Year VIP Shoes*/
/*Page 12*/
('1073263', '3200', 'Shoes 3'),	/*Royal Guard Shoes (F)*/
('1073297', '5200', 'Shoes 3'),	/*Crispy Carrot Flippers*/
('1073298', '4700', 'Shoes 3'),	/*Spring Green Slip-Ons*/
('1073299', '3400', 'Shoes 3'),	/*Traditional Thai Attire Shoes*/
('1073302', '3200', 'Shoes 3'),	/*Seafoam Coral Anklet*/
('1073303', '7600', 'Shoes 3'),	/*Starry Summer Night Shoes*/
('1073304', '7100', 'Shoes 3'),	/*Rock Spirit Slippers*/
('1073305', '4700', 'Shoes 3'),	/*Alliance Commander Shoes*/
('1073308', '7100', 'Shoes 3'),	/*Cutie Pie Trainers*/
/*Page 13*/
('1073309', '4900', 'Shoes 3'),	/*Cutie Pie High-Tops*/
('1073310', '8800', 'Shoes 3'),	/*Heavenly Prayer Shoes*/
('1073280', '5600', 'Shoes 3'),	/*Candy Shoes*/
('1073281', '4900', 'Shoes 3'),	/*Maple Gumshoe's Gumshoes*/
('1073282', '7400', 'Shoes 3'),	/*Ball and Chain*/
('1073283', '3800', 'Shoes 3'),	/*Erda Shoes*/
('1073284', '5600', 'Shoes 3'),	/*Sakura Battle Costume Shoes*/
('1073285', '5200', 'Shoes 3'),	/*Tomoeda Middle School Male Shoes*/
('1073286', '3200', 'Shoes 3'),	/*Tomoeda Middle School Female Shoes*/
/*Page 14*/
('1073287', '3200', 'Shoes 3'),	/*Syaoran Battle Costume Shoes*/
('1073290', '3400', 'Shoes 3'),	/*Clockwork Knight Shoes*/
('1073291', '3600', 'Shoes 3'),	/*Buddy Sneakers*/
('1073292', '3400', 'Shoes 3'),	/*Summer Story Shoes*/
('1073328', '3400', 'Shoes 3'),	/*Lunar New Year Pudgy Piggy Shoes*/
('1073329', '6000', 'Shoes 3'),	/*Sweet Shipmate Boaters (F)*/
('1073330', '8800', 'Shoes 3'),	/*Sweet Shipmate Boaters (M)*/
('1073331', '7600', 'Shoes 3'),	/*Hunny Bun Bear Slippers*/
('1073335', '7400', 'Shoes 3'),	/*Lavender Shearling Boots*/
/*Page 15*/
('1073338', '5600', 'Shoes 3'),	/*Midnight Magician Shoes (M)*/
('1073339', '7100', 'Shoes 3'),	/*Midnight Magician Shoes (F)*/
('1073341', '3200', 'Shoes 3'),	/*Cursed Hunter Shoes*/
('1073312', '7400', 'Shoes 3'),	/*Delinquent Bear Shoes*/
('1073315', '4900', 'Shoes 3'),	/*Lovely Plaid Shoes*/
('1073316', '7400', 'Shoes 3'),	/*Sweet Deer Anklet*/
('1073317', '5000', 'Shoes 3'),	/*Little Star Cocoon Anklet*/
('1073319', '7100', 'Shoes 3'),	/*Cozy Winter Clothes Shoes*/
('1073320', '5200', 'Shoes 3'),	/*Regal Romance Shoes*/
/*Page 16*/
('1073321', '7600', 'Shoes 3'),	/*Throwback Loafers*/
('1073322', '7400', 'Shoes 3'),	/*Snowflake Snowboots*/
('1073323', '7400', 'Shoes 3'),	/*Crown Fitness Trainers*/
('1073324', '5600', 'Shoes 3'),	/*Frilly Pink Pajama Slippers (F) */
('1073325', '4700', 'Shoes 3'),	/*Silly Blue Pajama Slippers (M)*/
('1073326', '3200', 'Shoes 3'),	/*Cobalt Filigree Mary Janes (F)*/
('1073327', '7100', 'Shoes 3'),	/*Cobalt Filigree Oxfords (M)*/
('1073347', '6400', 'Shoes 3'),	/*Blue Flame Hellion Shoes*/
('1073348', '6000', 'Shoes 3'),	/*Starry Light Wedges*/
/*Page 17*/
('1073349', '6400', 'Shoes 3'),	/*Starry Light Boots*/
('1073354', '6000', 'Shoes 3'),	/*Fox Fire Anklet*/
('1073357', '6400', 'Shoes 3'),	/*Red Lotus Spirit Walker's Geta*/





/*  Glove  */
/*Page 1*/
('1082040', '7600', 'Glove'),	/*Red Boxing Gloves*/
('1082041', '7100', 'Glove'),	/*Blue Boxing Gloves*/
('1082077', '4900', 'Glove'),	/*White Bandage*/
('1082078', '8800', 'Glove'),	/*Brown Bandage*/
('1082079', '7600', 'Glove'),	/*Black Bandage*/
('1082057', '3200', 'Glove'),	/*Brown Baseball Glove*/
('1082058', '7100', 'Glove'),	/*Blue Baseball Glove*/
('1082101', '8800', 'Glove'),	/*Santa Gloves*/
('1082102', '3600', 'Glove'),	/*Transparent Gloves*/
/*Page 2*/
('1082113', '4900', 'Glove'),	/*Hair-Cutter Gloves*/
('1082124', '7600', 'Glove'),	/*M-Forcer Gloves*/
('1082161', '5600', 'Glove'),	/*Star Gloves*/
('1082162', '8800', 'Glove'),	/*Love Gloves*/
('1082165', '7100', 'Glove'),	/*White Rabbit Gloves*/
('1082166', '3800', 'Glove'),	/*Nero Gloves*/
('1082169', '4000', 'Glove'),	/*Moon Bunny Gloves*/
('1082170', '5000', 'Glove'),	/*Rose Crystal Watch*/
('1082171', '5600', 'Glove'),	/*Blue Watch*/
/*Page 3*/
('1082172', '3800', 'Glove'),	/*Snowflake Gloves*/
('1082173', '3200', 'Glove'),	/*Lightning Gloves*/
('1082155', '3200', 'Glove'),	/*Snowman Gloves*/
('1082156', '4000', 'Glove'),	/*Teddy Bear Gloves*/
('1082157', '4700', 'Glove'),	/*Skull Gloves*/
('1082224', '7400', 'Glove'),	/*Tania Gloves*/
('1082225', '3800', 'Glove'),	/*Mercury Gloves*/
('1082227', '7600', 'Glove'),	/*Skull Tattoo*/
('1082229', '8800', 'Glove'),	/*Heart Ribbon Glove*/
/*Page 4*/
('1082231', '3200', 'Glove'),	/*Luxury Wristwatch*/
('1082233', '3400', 'Glove'),	/*Moomoo Gloves*/
('1082261', '6400', 'Glove'),	/*Freud's Gloves*/
('1082263', '8800', 'Glove'),	/*Bunny Gloves*/
('1082267', '7600', 'Glove'),	/*Cat Set Mittens*/
('1082268', '3600', 'Glove'),	/*Dual Blade Gloves*/
('1082247', '3200', 'Glove'),	/*Cutie Birk Gloves*/
('1082249', '7400', 'Glove'),	/*Neon Amulet*/
('1082250', '7400', 'Glove'),	/*Treacherous Wolf Gloves*/
/*Page 5*/
('1082251', '4000', 'Glove'),	/*Rock Chain Armlet*/
('1082253', '5200', 'Glove'),	/*Neon Sign Amulet*/
('1082255', '4900', 'Glove'),	/*Maple Racing Glove*/
('1082272', '4900', 'Glove'),	/*Evan Golden Gloves*/
('1082273', '6300', 'Glove'),	/*Hawkeye Ocean Gloves*/
('1082274', '5200', 'Glove'),	/*Evan Gloves*/
('1082282', '3400', 'Glove'),	/*Battle Mage Gloves*/
('1082310', '6000', 'Glove'),	/*Winter 2011 Moon Bunny Gloves*/
('1082312', '3600', 'Glove'),	/*Rainbow Bracelet*/
/*Page 6*/
('1082421', '8800', 'Glove'),	/*Blue Dragon Gloves*/
('1082422', '3200', 'Glove'),	/*Red Dragon Gloves*/
('1082423', '5200', 'Glove'),	/*Intergalactic Gloves*/
('1082407', '8800', 'Glove'),	/*Dark Force Gloves */
('1082408', '3400', 'Glove'),	/*Elven Spirit Gloves*/
('1082448', '4700', 'Glove'),	/*Arabian Gold Bracelet*/
('1082493', '6000', 'Glove'),	/*Harp Seal Doll Gloves*/
('1082495', '6400', 'Glove'),	/*Cat Lolita Gloves*/
('1082517', '6000', 'Glove'),	/*Golf Gloves*/
/*Page 7*/
('1082519', '3200', 'Glove'),	/*Purple Dinosaur Gloves*/
('1082520', '3200', 'Glove'),	/*Ramling Fur Glove*/

('1082524', '5000', 'Glove'),	/*Blavy Angel Bangle*/
('1082525', '3200', 'Glove'),	/*Succubus Gloves*/
('1082527', '5200', 'Glove'),	/*Golf Gloves*/
('1082500', '7400', 'Glove'),	/*Dark Devil Gloves*/
('1082501', '6300', 'Glove'),	/*Dark Force Gloves*/
('1082502', '7400', 'Glove'),	/*Blue Point Kitty Gloves*/
/*Page 8*/
('1082503', '5600', 'Glove'),	/*Featherly Angel Gloves*/
('1082504', '4000', 'Glove'),	/*Kitty Gloves*/
('1082505', '6000', 'Glove'),	/*Xenon Neo-Tech Gloves*/
('1082511', '3600', 'Glove'),	/*Green Dinosaur Gloves*/
('1082548', '5600', 'Glove'),	/*Star Bracelet*/
('1082549', '7100', 'Glove'),	/*Chicken Glovaroo*/
('1082550', '3600', 'Glove'),	/*White Ghostly Cloth*/
('1082551', '7600', 'Glove'),	/*Chocoram Doll Gloves*/
('1082552', '3800', 'Glove'),	/*Puffram Gloves*/
/*Page 9*/
('1082554', '8800', 'Glove'),	/*Princess of Time Gloves*/
('1082555', '4900', 'Glove'),	/*Fairy Spark*/
('1082558', '3600', 'Glove'),	/*Kirito's Gloves*/


('1082580', '3400', 'Glove'),	/*Pony Gloves*/
('1082581', '5600', 'Glove'),	/*Chocolate Ribbon*/
('1082585', '5600', 'Glove'),	/*Guardian Gloves*/
('1082587', '8800', 'Glove'),	/*Pink Panda Gloves*/
/*Page 10*/
('1082588', '6300', 'Glove'),	/*Rainbow Marbles*/

('1082560', '7100', 'Glove'),	/*Dark Devil Gloves*/
('1082561', '7600', 'Glove'),	/*Freud's Gloves*/
('1082563', '7600', 'Glove'),	/*Heathcliff's Gloves*/
('1082564', '7600', 'Glove'),	/*Yui's Gloves*/
('1082565', '7600', 'Glove'),	/*Chocolate Ribbon*/
('1082571', '3800', 'Glove'),	/*Mr. K's Cat Gloves*/
('1082620', '5600', 'Glove'),	/*Aloha Flower Accessory*/
/*Page 11*/
('1082623', '7100', 'Glove'),	/*Bright Angel Gloves*/
('1082592', '3800', 'Glove'),	/*Burning Ghost Wristband*/
('1082641', '3400', 'Glove'),	/*Blue Bird Gloves*/
('1082642', '5600', 'Glove'),	/*Snowman Gloves*/
('1082643', '4000', 'Glove'),	/*Cutie Birk Gloves*/
('1082624', '3200', 'Glove'),	/*Dark Devil Gloves*/
('1082631', '8800', 'Glove'),	/*Glowing Bracelet*/
('1082632', '5000', 'Glove'),	/*Worn Skull Gloves*/
('1082633', '4900', 'Glove'),	/*Skull Gloves*/
/*Page 12*/
('1082634', '6400', 'Glove'),	/*Dinofrog Gloves*/
('1082672', '6400', 'Glove'),	/*Arctic Mittens*/
('1082683', '5000', 'Glove'),	/*Santa Gloves*/
('1082684', '5600', 'Glove'),	/*Beaky Owl Gloves*/
('1082685', '7100', 'Glove'),	/*Winter Garden Gloves*/
('1082657', '3200', 'Glove'),	/*Blue Panda Doll Gloves*/
('1082666', '4700', 'Glove'),	/*White Ursus Gloves*/
('1082667', '7100', 'Glove'),	/*Brown Ursus Gloves*/
('1082668', '7600', 'Glove'),	/*Black Ursus Gloves*/
/*Page 13*/
('1082704', '3400', 'Glove'),	/*Sweet Penguin Gloves*/
('1082705', '6000', 'Glove'),	/*Cozy Penguin Gloves*/
('1082712', '7100', 'Glove'),	/*Felt's Gloves*/
('1082713', '6300', 'Glove'),	/*Winter Bunny Gloves (Teal)*/
('1082714', '6400', 'Glove'),	/*Winter Bunny Gloves (Pink)*/
('1082715', '4700', 'Glove'),	/*Chick Gloves*/
('1082717', '6300', 'Glove'),	/*Yellow Chick Gloves (M)*/
('1082718', '7600', 'Glove'),	/*Yellow Chick Gloves (F)*/
('1082689', '5600', 'Glove'),	/*Paw Gloves*/
/*Page 14*/
('1082690', '5000', 'Glove'),	/*Flower Star*/
('1082691', '3200', 'Glove'),	/*Flower Star*/
('1082692', '3600', 'Glove'),	/*Candybear Watch*/
('1082693', '7600', 'Glove'),	/*Racing Elephant Gloves*/
('1082694', '4900', 'Glove'),	/*Villain Gloves*/
('1082700', '5200', 'Glove'),	/*Bubble Bands*/
('1082701', '5000', 'Glove'),	/*Pony Gloves*/
('1082702', '3400', 'Glove'),	/*Kamaitachi Gloves*/
('1082703', '4900', 'Glove'),	/*Bichon Gloves*/
/*Page 15*/
('1082737', '7600', 'Glove'),	/*LEONARD Gloves*/
('1082738', '7400', 'Glove'),	/*SALLY Gloves*/
('1082741', '7600', 'Glove'),	/*Fox Fire Sweeping Sleeves*/
('1082720', '6400', 'Glove'),	/*Elizabethan Gloves*/
('1082721', '5600', 'Glove'),	/*Pink Elephant Gloves*/
('1082722', '4700', 'Glove'),	/*Meow Gloves*/
('1082723', '5600', 'Glove'),	/*Busy Penguin Gloves*/
('1082724', '7400', 'Glove'),	/*Worn Skull Gloves*/
('1082725', '6300', 'Glove'),	/*Santa Gloves*/
/*Page 16*/
('1082726', '7400', 'Glove'),	/*Royal Guard Gloves (F)*/
('1082727', '6400', 'Glove'),	/*Royal Guard Gloves (M)*/
('1082728', '3600', 'Glove'),	/*Homeless Cat Gloves*/
('1082730', '5000', 'Glove'),	/*Catkerchief Gloves*/
('1082731', '6000', 'Glove'),	/*Delinquent Bear Gloves*/
('1082733', '8800', 'Glove'),	/*Santa Gloves*/
('1082734', '3800', 'Glove'),	/*Snowman Gloves*/
('1082735', '6300', 'Glove'),	/*CONY Gloves*/
('1080000', '3600', 'Glove'),	/*White Ninja Gloves*/
/*Page 17*/
('1080001', '4900', 'Glove'),	/*Wedding Gloves*/
('1080007', '8800', 'Glove'),	/*크리스토프 장갑*/
('1080008', '6300', 'Glove'),	/*Whip Cream Pon Pon*/
('1080009', '4000', 'Glove'),	/*Penguin Gloves*/
('1081013', '3400', 'Glove'),	/*엘사 장갑*/
('1081014', '3800', 'Glove'),	/*Whip Cream Bon Bon*/
('1081015', '4700', 'Glove'),	/*Penguin Gloves*/
('1081000', '6400', 'Glove'),	/*Red Ninja Gloves*/
('1081001', '6000', 'Glove'),	/*Blue Ninja Gloves*/
/*Page 18*/
('1081002', '3200', 'Glove'),	/*Wedding Gloves*/
('1081003', '7100', 'Glove'),	/*White Cat Gloves*/
('1081004', '8800', 'Glove'),	/*Black Cat Gloves*/
('1081006', '4900', 'Glove'),	/*Elizabeth Gloves*/
('1081007', '5200', 'Glove'),	/*Elizabeth Gloves*/





/*  Ring  */
/*Page 1*/
('1114401', '7100', 'Ring'),	/*The Ring of Torment*/
('1114400', '5600', 'Ring'),	/*Firestarter Ring*/
('1115003', '3200', 'Ring'),	/*Carrot Rabbit Chat Ring*/
('1115005', '7400', 'Ring'),	/*Pineapple Chat Ring*/
('1115004', '7400', 'Ring'),	/*Honey Bee Chat Ring*/
('1115007', '4000', 'Ring'),	/*Black Hat Chat Ring*/
('1115006', '4000', 'Ring'),	/*Princess Diary Chat Ring*/
('1115025', '3800', 'Ring'),	/*Shark Chat Ring*/
('1115024', '3400', 'Ring'),	/*Colorbug Chat Ring*/
/*Page 2*/
('1115027', '5200', 'Ring'),	/*Red Cloud Chat Ring*/
('1115026', '3200', 'Ring'),	/*Cat Skein Chat Ring*/
('1115029', '7100', 'Ring'),	/*DJ JM Chat Ring*/
('1115028', '3200', 'Ring'),	/*V Chat Ring*/
('1115031', '6000', 'Ring'),	/*Pink Bean Chat Ring*/
('1115030', '5000', 'Ring'),	/*Pink Bean Chocolate Chat Ring*/
('1115033', '8800', 'Ring'),	/*진지한 말풍선 반지*/
('1115032', '3200', 'Ring'),	/*Spider Chat Ring*/
('1115035', '5600', 'Ring'),	/*Starry Night Orchid Chat Ring*/
/*Page 3*/
('1115034', '7400', 'Ring'),	/*Christmas Chat Ring*/
('1115037', '4700', 'Ring'),	/*Christmas Wreath Chat Ring*/
('1115036', '6300', 'Ring'),	/*Override Chat Ring*/
('1115039', '7600', 'Ring'),	/*Ladybug Chat Ring*/
('1115038', '8800', 'Ring'),	/*Baby Chick Chat Ring*/
('1115009', '6400', 'Ring'),	/*Blue Hat Chat Ring*/
('1115008', '4900', 'Ring'),	/*Green Hat Chat Ring*/
('1115011', '6400', 'Ring'),	/*Rascally Monster Chat Ring*/
('1115010', '7400', 'Ring'),	/*Good Night Monster Chat Ring*/
/*Page 4*/
('1115013', '3200', 'Ring'),	/*MVP Chat Ring (Gold)*/
('1115012', '5200', 'Ring'),	/*MVP Chat Ring (Silver)*/
('1115015', '7100', 'Ring'),	/*Snowman's Red Scarf Chat Ring*/
('1115014', '8800', 'Ring'),	/*MVP Chat Ring (Diamond)*/
('1115017', '5000', 'Ring'),	/*Christmas Chat Ring*/
('1115016', '7400', 'Ring'),	/*Heroes Slumbering Dragon Island Chat Ring*/
('1115019', '8800', 'Ring'),	/*Heroes Damien Chat Ring*/
('1115018', '5200', 'Ring'),	/*Mighty Banana Chat Ring*/
('1115021', '7100', 'Ring'),	/*Heroes Black Mage Chat Ring*/
/*Page 5*/
('1115020', '3600', 'Ring'),	/*Heroes Transcendence Stone Chat Ring*/
('1115023', '4000', 'Ring'),	/*Sunshine Ranch Chat Ring*/
('1115022', '5000', 'Ring'),	/*Bunny Chat Ring*/
('1115057', '4700', 'Ring'),	/*Little Poodle Chat Ring*/
('1115056', '5600', 'Ring'),	/*Ark Chat Ring*/
('1115059', '5600', 'Ring'),	/*Plum Blossom Chat Ring*/
('1115058', '3600', 'Ring'),	/*Corgi Chat Ring*/
('1115061', '3400', 'Ring'),	/*Choco Chat Ring*/
('1115060', '4700', 'Ring'),	/*White Chocolate Chat Ring*/
/*Page 6*/
('1115063', '3200', 'Ring'),	/*Baby Chick Chat Ring*/
('1115062', '4000', 'Ring'),	/*Children's Day Chat Ring*/
('1115065', '5000', 'Ring'),	/*Clockwork Knight Chat Ring*/
('1115066', '3600', 'Ring'),	/*Note Chat Ring*/
('1115069', '8800', 'Ring'),	/*Peach Blossom Fox Chat Ring*/
('1115068', '8800', 'Ring'),	/*Frosty Cherry Blossom Chat Ring*/
('1115071', '5000', 'Ring'),	/*Golden Piggy Chat Ring*/
('1115041', '4700', 'Ring'),	/*Camellia's Sword Chat Ring*/
('1115040', '5000', 'Ring'),	/*Dark Magician Chat Ring*/
/*Page 7*/
('1115043', '7600', 'Ring'),	/*보안관 말풍선 반지*/
('1115042', '3400', 'Ring'),	/*Mustache Chat Ring*/
('1115045', '6000', 'Ring'),	/*Nova Chat Ring*/
('1115044', '7400', 'Ring'),	/*Glistening Eluna Chat Ring*/
('1115047', '3600', 'Ring'),	/*Jam Chat Ring*/
('1115046', '8800', 'Ring'),	/*Honey Pot Chat Ring*/
('1115049', '4700', 'Ring'),	/*Wave Chat Ring*/
('1115048', '3400', 'Ring'),	/*Falling Darkness Chat Ring*/
('1115051', '6400', 'Ring'),	/*Palm Leaf Chat Ring*/
/*Page 8*/
('1115050', '5000', 'Ring'),	/*Flutterby Dream Chat Ring*/
('1115053', '3600', 'Ring'),	/*Pea Chat Ring*/
('1115052', '5600', 'Ring'),	/*Moonlight Bunny Chat Ring*/
('1115055', '7400', 'Ring'),	/*Mobile Mansion Chat Ring*/
('1115054', '3600', 'Ring'),	/*Vampire Chat Ring*/
('1115101', '4900', 'Ring'),	/*MVP Label Ring (Gold)*/
('1115100', '3800', 'Ring'),	/*MVP Label Ring (Silver)*/
('1115103', '3600', 'Ring'),	/*Heroes Slumbering Dragon Island Label Ring*/
('1115102', '3600', 'Ring'),	/*MVP Label Ring (Diamond)*/
/*Page 9*/
('1115073', '7600', 'Ring'),	/*Adventure Chat Ring*/
('1115072', '3400', 'Ring'),	/*'80s Jelly Heart Chat Ring*/
('1115075', '3800', 'Ring'),	/*CONY Chat Ring*/
('1115074', '5000', 'Ring'),	/*BROWN Chat Ring*/
('1115077', '7600', 'Ring'),	/*CHOCO Chat Ring*/
('1115076', '7400', 'Ring'),	/*LEONARD Chat Ring*/
('1115079', '5000', 'Ring'),	/*Grim Reaper Chat Ring*/
('1115078', '7400', 'Ring'),	/*SALLY Chat Ring*/
('1115081', '6400', 'Ring'),	/*Drowsy Rabbit Chat Ring*/
/*Page 10*/
('1115080', '5600', 'Ring'),	/*Bunny Blossom Chat Ring*/
('1115082', '3200', 'Ring'),	/*Piggy Chat Ring*/
('1115121', '3600', 'Ring'),	/*Spider Label Ring*/
('1115120', '4900', 'Ring'),	/*Pink Bean Label Ring*/
('1115123', '3600', 'Ring'),	/*Christmas Label Ring*/
('1115122', '5600', 'Ring'),	/*진지한 명찰 반지*/
('1115125', '6300', 'Ring'),	/*Override Label Ring*/
('1115124', '7400', 'Ring'),	/*Starry Night Orchid Label Ring*/
('1115127', '6400', 'Ring'),	/*Baby Chick Label Ring*/
/*Page 11*/
('1115126', '7600', 'Ring'),	/*Christmas Wreath Label Ring*/
('1115129', '7100', 'Ring'),	/*Dark Magician Label Ring*/
('1115128', '6400', 'Ring'),	/*Ladybug Label Ring*/
('1115131', '4000', 'Ring'),	/*Mustache Label Ring*/
('1115130', '3200', 'Ring'),	/*Camellia's Sword Label Ring*/
('1115133', '5600', 'Ring'),	/*Glistening Eluna Label Ring*/
('1115132', '4900', 'Ring'),	/*보안관 명찰 반지*/
('1115135', '6300', 'Ring'),	/*Honey Pot Label Ring*/
('1115134', '7600', 'Ring'),	/*Nova Label Ring*/
/*Page 12*/
('1115105', '5200', 'Ring'),	/*Mighty Banana Label Ring*/
('1115104', '3600', 'Ring'),	/*Christmas Label Ring*/
('1115109', '5600', 'Ring'),	/*Heroes Transcendence Stone Label Ring*/
('1115108', '5600', 'Ring'),	/*Heroes Damien Label Ring*/
('1115111', '7400', 'Ring'),	/*Bunny Label Ring*/
('1115110', '6400', 'Ring'),	/*Heroes Black Mage Label Ring*/
('1115113', '6300', 'Ring'),	/*Colorbug Label Ring*/
('1115112', '4000', 'Ring'),	/*Sunshine Ranch Label Ring*/
('1115115', '7400', 'Ring'),	/*Cat Skein Label Ring*/
/*Page 13*/
('1115114', '8800', 'Ring'),	/*Shark Label Ring*/
('1115117', '5600', 'Ring'),	/*V Label Ring*/
('1115116', '5000', 'Ring'),	/*Red Cloud Label Ring*/
('1115119', '3800', 'Ring'),	/*Pink Bean Chocolate Label Ring*/
('1115118', '8800', 'Ring'),	/*DJ JM Label Ring*/
('1115152', '6000', 'Ring'),	/*Baby Chick Label Ring*/
('1115155', '6400', 'Ring'),	/*Note Label Ring*/
('1115154', '8800', 'Ring'),	/*Clockwork Knight Label Ring*/
('1115157', '4700', 'Ring'),	/*Snowy Cherry Blossom Label Ring*/
/*Page 14*/
('1115158', '4000', 'Ring'),	/*Peach Blossom Fox Label Ring*/
('1115161', '7100', 'Ring'),	/*'80s Jelly Heart Label Ring*/
('1115160', '4700', 'Ring'),	/*Golden Piggy Label Ring*/
('1115163', '7600', 'Ring'),	/*BROWN Label Ring*/
('1115162', '4900', 'Ring'),	/*Adventure Label Ring*/
('1115165', '4900', 'Ring'),	/*LEONARD Label Ring*/
('1115164', '3200', 'Ring'),	/*CONY Label Ring*/
('1115167', '4700', 'Ring'),	/*SALLY Label Ring*/
('1115166', '3800', 'Ring'),	/*CHOCO Label Ring*/
/*Page 15*/
('1115137', '3400', 'Ring'),	/*Falling Darkness Label Ring*/
('1115136', '7600', 'Ring'),	/*Jam Label Ring*/
('1115139', '4900', 'Ring'),	/*Flutterby Dream Label Ring*/
('1115138', '8800', 'Ring'),	/*Wave Label Ring*/
('1115141', '6000', 'Ring'),	/*Moonlight Bunny Label Ring*/
('1115140', '6300', 'Ring'),	/*Palm Leaf Label Ring*/
('1115143', '7100', 'Ring'),	/*Vampire Label Ring*/
('1115142', '4900', 'Ring'),	/*Pea Label Ring*/
('1115145', '6400', 'Ring'),	/*Ark Label Ring*/
/*Page 16*/
('1115144', '3400', 'Ring'),	/*Mobile Mansion Label Ring*/
('1115147', '7600', 'Ring'),	/*Corgi Label Ring*/
('1115146', '7400', 'Ring'),	/*Little Poodle Label Ring*/
('1115149', '6000', 'Ring'),	/*White Chocolate Label Ring*/
('1115148', '8800', 'Ring'),	/*Plum Blossom Label Ring*/
('1115151', '6000', 'Ring'),	/*Children's Day Label Ring*/
('1115150', '5200', 'Ring'),	/*Choco Label Ring*/
('1115169', '5600', 'Ring'),	/*Bunny Blossom Label Ring*/
('1115168', '5600', 'Ring'),	/*Grim Reaper Label Ring*/
/*Page 17*/
('1115171', '3400', 'Ring'),	/*Piggy Label Ring*/
('1115170', '4900', 'Ring'),	/*Drowsy Rabbit Label Ring*/
('1112016', '4700', 'Ring'),	/*Snowflake Ring*/
('1112000', '5000', 'Ring'),	/*Sparkling Ring*/
('1112001', '5000', 'Ring'),	/*Promise Ring*/
('1112002', '5200', 'Ring'),	/*Cloud Promise Ring*/
('1112003', '3400', 'Ring'),	/*Cupid Ring*/
('1112005', '7600', 'Ring'),	/*Venus Fireworks*/
('1112006', '7600', 'Ring'),	/*Crossed Hearts*/
/*Page 18*/
('1112007', '7600', 'Ring'),	/*Mistletoe Promise Ring*/
('1112012', '5600', 'Ring'),	/*Rose Promise Ring*/
('1112013', '6400', 'Ring'),	/*Firery Love String Promise Ring*/
('1112014', '6400', 'Ring'),	/*Flaming Red Lips Ring*/
('1112015', '3200', 'Ring'),	/*Illumination Couples Ring*/
('1112112', '3600', 'Ring'),	/*Beach Label Ring*/
('1112113', '7400', 'Ring'),	/*Chocolate Label Ring*/
('1112114', '6000', 'Ring'),	/*Pink Candy Label Ring*/
('1112115', '5200', 'Ring'),	/*MapleBowl Label Ring */
/*Page 19*/
('1112116', '5200', 'Ring'),	/*White Cloud Label Ring*/
('1112117', '3600', 'Ring'),	/*Rainbow Label Ring*/
('1112118', '6300', 'Ring'),	/*Rainbow Label RingaCoke Label Ring*/
('1112119', '3800', 'Ring'),	/*Coke (Red) Label Ring*/
('1112120', '3200', 'Ring'),	/*Coke (White) Label Ring*/
('1112121', '3400', 'Ring'),	/*Gingerman Label Ring*/
('1112122', '6000', 'Ring'),	/*Deluxe Rainbow Label Ring*/
('1112123', '5200', 'Ring'),	/*Red Pencil Label Ring*/
('1112124', '3400', 'Ring'),	/*Blue Pencil Label Ring*/
/*Page 20*/
('1112125', '7100', 'Ring'),	/*Green Pencil Label Ring*/
('1112126', '6300', 'Ring'),	/*Brown Teddy Label Ring*/
('1112127', '3800', 'Ring'),	/*Welcome Back Ring*/
('1112100', '7400', 'Ring'),	/*White Label Ring*/
('1112101', '7600', 'Ring'),	/*Blue Label Ring*/
('1112102', '7600', 'Ring'),	/*Blue Label Ring 2*/
('1112103', '3400', 'Ring'),	/*The Legendary Gold Ring*/
('1112104', '5200', 'Ring'),	/*Bubbly Label Ring*/
('1112105', '8800', 'Ring'),	/*Pink-Ribboned Label Ring*/
/*Page 21*/
('1112106', '5000', 'Ring'),	/*Blue-Ribboned Label Ring*/
('1112107', '7100', 'Ring'),	/*Skull Label Ring*/
('1112108', '5600', 'Ring'),	/*Butterfly Label Ring*/
('1112109', '7100', 'Ring'),	/*Scoreboard Label Ring*/
('1112110', '4900', 'Ring'),	/*SK Basketball Team Label Ring*/
('1112111', '4000', 'Ring'),	/*KTF Basketball Team Label Ring*/
('1112144', '7100', 'Ring'),	/*Cat-ger Label Ring*/
('1112145', '4700', 'Ring'),	/*Romantic Lace Label Ring*/
('1112146', '3400', 'Ring'),	/*Green Apple Label Ring*/
/*Page 22*/
('1112148', '3200', 'Ring'),	/*Mister Mustache Label Ring*/
('1112149', '4700', 'Ring'),	/*Naver Label Ring*/
('1112150', '3200', 'Ring'),	/*Angel Label Ring*/
('1112151', '5600', 'Ring'),	/*Strawberry Cake Label Ring*/
('1112152', '3400', 'Ring'),	/*Blue Strawberry Basket Label Ring*/
('1112153', '3400', 'Ring'),	/*Strawberry Label Ring*/
('1112154', '7400', 'Ring'),	/*Moon Bunny Label Ring*/
('1112155', '5600', 'Ring'),	/*Frog Label Ring*/
('1112156', '6300', 'Ring'),	/*Oink Label Ring*/
/*Page 23*/
('1112157', '4700', 'Ring'),	/*Blue Beard Label Ring*/
('1112159', '3200', 'Ring'),	/*Diamond Label Ring*/
('1112129', '6000', 'Ring'),	/*German Label Ring*/
('1112130', '6400', 'Ring'),	/*Dutch Label Ring*/
('1112131', '4900', 'Ring'),	/*French Label Ring*/
('1112132', '4900', 'Ring'),	/*British Label Ring*/
('1112134', '4900', 'Ring'),	/*Bamboo Name Tag Ring*/
('1112135', '6000', 'Ring'),	/*Ink-and-Wash Painting Name Tag Ring*/
('1112136', '4700', 'Ring'),	/*Sausage Ring*/
/*Page 24*/
('1112137', '6000', 'Ring'),	/*Mountain Dew Label Ring*/
('1112141', '4000', 'Ring'),	/*Red Rose Label Ring*/
('1112142', '7400', 'Ring'),	/*Mummy Label Ring*/
('1112143', '7100', 'Ring'),	/*Luxury Pearl Label Ring*/
('1112176', '6300', 'Ring'),	/*G Clef Label Ring*/
('1112177', '3800', 'Ring'),	/*Attack on Titan Label Ring*/
('1112178', '5600', 'Ring'),	/*Snow Day Dream Label Ring*/
('1112179', '7100', 'Ring'),	/*Snowy Christmas Label Ring*/
('1112180', '6400', 'Ring'),	/*Kinship Label Ring*/
/*Page 25*/
('1112181', '7100', 'Ring'),	/*Sheep Label Ring*/
('1112182', '4000', 'Ring'),	/*Baby Label Ring*/
('1112183', '8800', 'Ring'),	/*Meadow Sheep Label Ring*/
('1112184', '6000', 'Ring'),	/*Squishy Pink Label Ring*/
('1112190', '7400', 'Ring'),	/*Carrot Rabbit Label Ring*/
('1112191', '6400', 'Ring'),	/*Honey Bee Label Ring*/
('1112160', '3600', 'Ring'),	/*Watermelon Label Ring*/
('1112161', '5000', 'Ring'),	/*Quack Quack Label Ring*/
('1112162', '3400', 'Ring'),	/*Island Travel Name Tag Ring*/
/*Page 26*/
('1112163', '5200', 'Ring'),	/*Starring Me Label Ring*/
('1112164', '5000', 'Ring'),	/*Sweet Summer Label Ring*/
('1112165', '5200', 'Ring'),	/*Green Forest Label Ring*/
('1112166', '3800', 'Ring'),	/*Baby Label Ring*/
('1112170', '4000', 'Ring'),	/*Star Label Ring*/
('1112171', '8800', 'Ring'),	/*White Puppy Label Ring*/
('1112172', '5600', 'Ring'),	/*Brown Puppy Label Ring*/
('1112173', '7400', 'Ring'),	/*Bunny Label Ring*/
('1112208', '7400', 'Ring'),	/*Skull Quote Ring*/
/*Page 27*/
('1112209', '3200', 'Ring'),	/*Blue-Hearted Quote Ring*/
('1112210', '3400', 'Ring'),	/*Gold-Yellow Quote Ring*/
('1112211', '6000', 'Ring'),	/*Pink Lady Quote Ring*/
('1112212', '7100', 'Ring'),	/*Silver-Blue Quote Ring*/
('1112213', '4900', 'Ring'),	/*Gold-Yellow Quote Ring 2*/
('1112214', '6400', 'Ring'),	/*Pink Lady Quote Ring 2*/
('1112215', '7400', 'Ring'),	/*Blue Marine Quote Ring*/
('1112216', '6000', 'Ring'),	/*Kitty Quote Ring*/
('1112217', '3200', 'Ring'),	/*Paw-Print Quote Ring*/
/*Page 28*/
('1112218', '6400', 'Ring'),	/*Teddy Bear Quote Ring*/
('1112219', '4000', 'Ring'),	/*Scoreboard Quote Ring*/
('1112220', '3200', 'Ring'),	/*SK Basketball Team Quote Ring*/
('1112221', '3200', 'Ring'),	/*KTF Basketball Team Quote Ring*/
('1112222', '7100', 'Ring'),	/*Starflower Ring*/
('1112223', '4000', 'Ring'),	/*Beach Quote Ring*/
('1112192', '7100', 'Ring'),	/*Pineapple Label Ring*/
('1112193', '4700', 'Ring'),	/*Princess Diary Label Ring*/
('1112194', '6400', 'Ring'),	/*Black Hat Label Ring*/
/*Page 29*/
('1112195', '5000', 'Ring'),	/*Green Hat Label Ring*/
('1112196', '5000', 'Ring'),	/*Blue Hat Label Ring*/
('1112197', '4900', 'Ring'),	/*Good Night Monster Label Ring*/



/*Page 1*/
('1112198', '7100', 'Ring 2'),	/*Rascally Monster Label Ring*/
('1112199', '5200', 'Ring 2'),	/*Snowman's Red Scarf Label Ring*/
('1112200', '4000', 'Ring 2'),	/*Pink Quote Ring*/
('1112201', '8800', 'Ring 2'),	/*Pink-Hearted Quote Ring*/
('1112202', '4000', 'Ring 2'),	/*Blue Quote Ring*/
('1112203', '3400', 'Ring 2'),	/*The Golden Fly Ring*/
('1112204', '4000', 'Ring 2'),	/*Pink-Flowered Quote Ring*/
('1112205', '3800', 'Ring 2'),	/*Blue-Flowered Quote Ring*/
('1112206', '4900', 'Ring 2'),	/*Pink-Ribboned Quote Ring*/
/*Page 2*/
('1112207', '5000', 'Ring 2'),	/*Blue-Ribboned Quote Ring*/
('1112240', '4000', 'Ring 2'),	/*Mountain Dew Quote Ring*/
('1112244', '4700', 'Ring 2'),	/*Darkness Bat Ring*/
('1112248', '7600', 'Ring 2'),	/*[MS Custom] Pink Quote Ring*/
('1112249', '7600', 'Ring 2'),	/*[MS Custom] Blue-Flowered Quote Ring*/
('1112250', '7100', 'Ring 2'),	/*[MS Custom]Pink-Flowered Quote Ring*/
('1112252', '7600', 'Ring 2'),	/*Red Rose Chat Ring*/
('1112253', '5200', 'Ring 2'),	/*Mummy Word Bubble Ring*/
('1112254', '5600', 'Ring 2'),	/*Luxury Pearl Word Bubble Ring*/
/*Page 3*/
('1112224', '3800', 'Ring 2'),	/*Chocolate Quote Ring*/
('1112225', '5200', 'Ring 2'),	/*Pink Candy Quote Ring*/
('1112226', '5600', 'Ring 2'),	/*White Cloud Quote Ring*/
('1112227', '7600', 'Ring 2'),	/*Rainbow Quote Ring*/
('1112228', '3200', 'Ring 2'),	/*Coke Quote Ring*/
('1112229', '5200', 'Ring 2'),	/*Coke (Red) Quote Ring*/
('1112230', '5200', 'Ring 2'),	/*Coke (White) Quote Ring*/
('1112231', '6000', 'Ring 2'),	/*Gingerman Quote Ring*/
('1112232', '5200', 'Ring 2'),	/*Deluxe Rainbow Quote Ring*/
/*Page 4*/
('1112233', '6400', 'Ring 2'),	/*Red Notebook Quote Ring*/
('1112234', '7600', 'Ring 2'),	/*Blue Notebook Quote Ring*/
('1112235', '6000', 'Ring 2'),	/*Green Notebook Quote Ring*/
('1112236', '7100', 'Ring 2'),	/*Brown Teddy Quote Ring*/
('1112237', '6300', 'Ring 2'),	/*Bamboo Thought Bubble Ring*/
('1112238', '5200', 'Ring 2'),	/*Ink-and-Wash Thought Bubble Ring*/
('1112272', '7600', 'Ring 2'),	/*Watermelon Chat Bubble Ring*/
('1112273', '3600', 'Ring 2'),	/*Quack Quack Word Bubble Ring*/
('1112274', '7600', 'Ring 2'),	/*Island Travel Speech Bubble Ring*/
/*Page 5*/
('1112275', '8800', 'Ring 2'),	/*Me From the Star Word Bubble Ring*/
('1112276', '5200', 'Ring 2'),	/*Sweet Summer Chat Ring*/
('1112277', '3400', 'Ring 2'),	/*Green Forest Chat Ring*/
('1112278', '6400', 'Ring 2'),	/*Baby Chat Ring*/
('1112282', '6000', 'Ring 2'),	/*Star Word Bubble Ring*/
('1112283', '6400', 'Ring 2'),	/*White Puppy Chat Ring*/
('1112284', '5000', 'Ring 2'),	/*Brown Puppy Chat Ring*/
('1112285', '4900', 'Ring 2'),	/*Bunny Word Bubble Ring*/
('1112256', '4000', 'Ring 2'),	/*Kitty Word Bubble Ring*/
/*Page 6*/
('1112257', '4000', 'Ring 2'),	/*Romantic Lace Word Bubble Ring*/
('1112258', '3600', 'Ring 2'),	/*Green Apple Word Bubble Ring*/
('1112259', '4900', 'Ring 2'),	/*Mister Mustache Word Bubble Ring*/
('1112260', '5600', 'Ring 2'),	/*Guild Word Bubble Ring*/
('1112261', '6300', 'Ring 2'),	/*Naver Word Bubble Ring*/
('1112262', '8800', 'Ring 2'),	/*Angel Word Bubble Ring*/
('1112263', '6000', 'Ring 2'),	/*Strawberry Cake Word Bubble Ring*/
('1112264', '3800', 'Ring 2'),	/*Blue Strawberry Basket Chat Ring*/
('1112265', '3200', 'Ring 2'),	/*Red Strawberry Basket Chat Ring*/
/*Page 7*/
('1112266', '7400', 'Ring 2'),	/*Moon Bunny Word Bubble Ring*/
('1112267', '5000', 'Ring 2'),	/*Frog Word Bubble Ring*/
('1112268', '8800', 'Ring 2'),	/*Oink Word Bubble Ring*/
('1112269', '4700', 'Ring 2'),	/*Blue Beard Quote Ring*/
('1112271', '7600', 'Ring 2'),	/*Diamond Quote Ring*/
('1112288', '4000', 'Ring 2'),	/*G Clef Word Bubble Ring*/
('1112289', '6400', 'Ring 2'),	/*Attack on Titan Word Bubble Ring*/
('1112290', '4000', 'Ring 2'),	/*Snow Day Dream Word Bubble Ring*/
('1112291', '4700', 'Ring 2'),	/*Snowy Christmas Chat Ring*/
/*Page 8*/
('1112292', '3200', 'Ring 2'),	/*Silver Guild Word Bubble Ring*/
('1112293', '5600', 'Ring 2'),	/*Kinship Word Bubble Ring*/
('1112294', '4700', 'Ring 2'),	/*Sheep Word Bubble Ring*/
('1112295', '6300', 'Ring 2'),	/*Baby Word Bubble Ring*/
('1112296', '5200', 'Ring 2'),	/*Meadow Sheep Chat Bubble Ring*/
('1112724', '8800', 'Ring 2'),	/*I'm New Ring*/
('1112728', '7100', 'Ring 2'),	/*Mapler Ring*/
('1112757', '4000', 'Ring 2'),	/*Grin Ring*/
('1112741', '5200', 'Ring 2'),	/*Welcome Back Ring*/
/*Page 9*/
('1112816', '3400', 'Ring 2'),	/*Snow Dome Friendship Ring*/
('1112817', '3800', 'Ring 2'),	/*Psyche Special Friendship Ring*/
('1112820', '7100', 'Ring 2'),	/*Friendship Ring: Dragon and Pheonix*/
('1112823', '5200', 'Ring 2'),	/*Promise Ring*/
('1112800', '5000', 'Ring 2'),	/*Friendship Ring: Clover*/
('1112801', '3800', 'Ring 2'),	/*Friendship Ring: Flower Petal*/
('1112802', '6400', 'Ring 2'),	/*Friendship Ring: Star*/
('1112808', '6300', 'Ring 2'),	/*MapleBowl Quote Ring */
('1112810', '4900', 'Ring 2'),	/*Christmas Night Bells Friendship Ring*/
/*Page 10*/
('1112811', '3800', 'Ring 2'),	/*Christmas Party Friendship Ring*/
('1112812', '6300', 'Ring 2'),	/*Shared Umbrella Ring*/
('1112916', '4700', 'Ring 2'),	/*Solo Ring*/
('1112917', '7400', 'Ring 2'),	/*I'm New Ring*/
('1112918', '5200', 'Ring 2'),	/*Welcome Back Ring*/
('1112919', '5200', 'Ring 2'),	/*Mapler Ring*/
('1112924', '3400', 'Ring 2'),	/*Lemon Shooting Star Ring*/
('1112925', '7400', 'Ring 2'),	/*Blue Shooting Star Ring*/
('1112926', '4900', 'Ring 2'),	/*Pink Shooting Star Ring*/
/*Page 11*/
('1112900', '7100', 'Ring 2'),	/*Lalala Ring*/
('1112901', '7600', 'Ring 2'),	/*Starry Spotlight Ring*/
('1112902', '6400', 'Ring 2'),	/*Baby Blue*/
('1112903', '8800', 'Ring 2'),	/*Amorian Aura Ring*/
('1112904', '4700', 'Ring 2'),	/*Rainbow Star Ring*/
('1112905', '6000', 'Ring 2'),	/*Bright Hot Pink Heart*/
('1112906', '3200', 'Ring 2'),	/*Baby Pink Heart*/
('1112908', '5600', 'Ring 2'),	/*Aura Ring*/
('1112909', '7400', 'Ring 2'),	/*Aura Ring*/
/*Page 12*/
('1112910', '3600', 'Ring 2'),	/*Aura Ring*/
('1112945', '8800', 'Ring 2'),	/*Always Craving Sweet N' Sour BBQ*/
('1112946', '4700', 'Ring 2'),	/*Rainbow Jewelry*/
('1112948', '7100', 'Ring 2'),	/*Couture Critic Ring*/
('1112949', '7400', 'Ring 2'),	/*Melody Ring*/
('1112953', '7600', 'Ring 2'),	/*Blue Shooting Star Ring*/
('1112954', '6300', 'Ring 2'),	/*Pink Shooting Star Ring*/
('1112955', '3600', 'Ring 2'),	/*Best Friends Ring*/
('1112956', '6300', 'Ring 2'),	/*Shining Star Ring*/
/*Page 13*/
('1112958', '7400', 'Ring 2'),	/*Honey Bee Flower Effect Ring*/
('1112959', '5200', 'Ring 2'),	/*Butterfly Flower Effect Ring*/
('1112928', '8800', 'Ring 2'),	/*Peach Shooting Star Ring*/
('1112929', '7400', 'Ring 2'),	/*Von Leon Ring*/
('1112930', '5600', 'Ring 2'),	/*Tomato Ring*/
('1112935', '5200', 'Ring 2'),	/*Lalala Ring*/
('1112937', '7400', 'Ring 2'),	/*Sleepy Zzz*/
('1112940', '5600', 'Ring 2'),	/*Mapler Ring*/
('1112941', '6400', 'Ring 2'),	/*Welcome Back Ring*/
/*Page 14*/
('1112943', '7400', 'Ring 2'),	/*Fashion Week Ring*/
('1112976', '5000', 'Ring 2'),	/*Dreamland Thief Effect Ring*/
('1112960', '5000', 'Ring 2'),	/*Memory Guide Ring*/
('1112961', '4000', 'Ring 2'),	/*Cheery Yeti Ring*/
('1112964', '3400', 'Ring 2'),	/*White Christmas Ring*/
('1112965', '5200', 'Ring 2'),	/*Lucid Butterfly Ring*/
('1112969', '5000', 'Ring 2'),	/*Beyond the Clouds Effect Ring*/
('1112970', '3200', 'Ring 2'),	/*Rainy Day Doll Ring*/
('1112971', '3200', 'Ring 2'),	/*Clear Day Doll Ring*/
/*Page 15*/
('1112972', '5200', 'Ring 2'),	/*Aurora Winter Night Ring*/
('1112973', '3600', 'Ring 2'),	/*Summer Constellation Ring*/
('1112974', '3200', 'Ring 2'),	/*Autumn Constellation Ring*/
('1112975', '3600', 'Ring 2'),	/*New Year Party People Effect Ring*/
('1113003', '7100', 'Ring 2'),	/*Dark Devil Ring*/
('1113171', '3800', 'Ring 2'),	/*Grin Ring*/
('1113298', '7100', 'Ring 2'),	/*Spirit Ring*/
('1113299', '7600', 'Ring 2'),	/*Determination Ring*/

/*Page 16*/
('1113289', '5600', 'Ring 2'),	/*Busy Penguin Ring*/
('1114000', '5200', 'Ring 2'),	/*Kinship Ring*/





/*  Cape  */
/*Page 1*/
('1100000', '4000', 'Cape'),	/*Napoleon Cape*/
('1100001', '5600', 'Cape'),	/*Napoleon Cape*/
('1100004', '7400', 'Cape'),	/*Mad Doctor Stethoscope*/
('1101000', '6000', 'Cape'),	/*Ribbon Angel Syringe*/
('1102005', '3200', 'Cape'),	/*Baby Angel Wings*/
('1102006', '8800', 'Cape'),	/*Devil Wings*/
('1102007', '3800', 'Cape'),	/*Yellow Star Cape*/
('1102008', '8800', 'Cape'),	/*Blue Star Cape*/
('1102009', '6400', 'Cape'),	/*Red Star Cape*/
/*Page 2*/
('1102010', '8800', 'Cape'),	/*Black Star Cape*/
('1102036', '4700', 'Cape'),	/*Red Landcell Pack*/
('1102037', '7100', 'Cape'),	/*Black Landcell Pack*/
('1102038', '7400', 'Cape'),	/*Blue Landcell Pack*/
('1102039', '7600', 'Cape'),	/*Transparent Cape*/
('1102044', '5600', 'Cape'),	/*Red G-Wing Jetpack*/
('1102045', '7400', 'Cape'),	/*Blue G-Wing Jetpack*/
('1102019', '7100', 'Cape'),	/*Korean-Flagged Cape*/
('1102020', '4700', 'Cape'),	/*Turtle Shell*/
/*Page 3*/
('1102025', '3200', 'Cape'),	/*Red Hood Cape*/
('1102065', '3600', 'Cape'),	/*Christmas Cape*/
('1102066', '6400', 'Cape'),	/*Dracula Cloak*/
('1102067', '7400', 'Cape'),	/*Tiger Tail*/
('1102068', '6300', 'Cape'),	/*Harpie Cape*/
('1102069', '3800', 'Cape'),	/*Pink Wings*/
('1102070', '8800', 'Cape'),	/*Blue Book Bag*/
('1102072', '4900', 'Cape'),	/*Yellow-Green Backpack*/
('1102073', '7400', 'Cape'),	/*Hot Pink Backpack*/
/*Page 4*/
('1102074', '4700', 'Cape'),	/*Dragonfly Wings*/
('1102075', '3400', 'Cape'),	/*Bat's Bane*/
('1102076', '3400', 'Cape'),	/*Newspaper Cape*/
('1102077', '7600', 'Cape'),	/*Cotton Blanket*/
('1102049', '5200', 'Cape'),	/*Blue Nymph Wing*/
('1102050', '7400', 'Cape'),	/*Green Nymph Wing*/
('1102051', '7400', 'Cape'),	/*Yellow Nymph Wing*/
('1102052', '6300', 'Cape'),	/*Pink Nymph Wing*/
('1102058', '7100', 'Cape'),	/*Gargoyle Wings*/
/*Page 5*/
('1102059', '4000', 'Cape'),	/*Michael Wings*/
('1102060', '6400', 'Cape'),	/*Pink Ribbon*/
('1102062', '7100', 'Cape'),	/*Martial Cape*/
('1102063', '7600', 'Cape'),	/*Fallen Angel Wings*/
('1102096', '8800', 'Cape'),	/*Sachiel Wings*/
('1102097', '7600', 'Cape'),	/*Janus Wings*/
('1102098', '4700', 'Cape'),	/*Coffin of Gloom*/
('1102107', '3600', 'Cape'),	/*Rocket Booster*/
('1102108', '4000', 'Cape'),	/*Fallen Angel Tail*/
/*Page 6*/
('1102110', '5000', 'Cape'),	/*Chipmunk Tail*/
('1102111', '4700', 'Cape'),	/*Elephant Balloon*/
('1102091', '4700', 'Cape'),	/*Summer Kite*/
('1102092', '6300', 'Cape'),	/*Cuddle Bear*/
('1102093', '5000', 'Cape'),	/*Heart Balloon*/
('1102094', '6000', 'Cape'),	/*Sun Wu Kong Tail*/
('1102095', '3800', 'Cape'),	/*Veamoth Wings*/
('1102137', '6300', 'Cape'),	/*Orange Mushroom Balloon*/
('1102138', '6000', 'Cape'),	/*Pink Wing Bag*/
/*Page 7*/
('1102141', '3400', 'Cape'),	/*Pepe Balloon*/
('1102142', '5000', 'Cape'),	/*The Flaming Cape*/
('1102112', '6000', 'Cape'),	/*Bunny Doll*/
('1102160', '5600', 'Cape'),	/*Baby Lupin Cape*/
('1102162', '7100', 'Cape'),	/*Baby White Monkey Balloon*/
('1102164', '3800', 'Cape'),	/*Maple MSX Guitar*/
('1102169', '5200', 'Cape'),	/*Blue Wing Bag*/
('1102171', '5200', 'Cape'),	/*3rd Anniversary Balloon*/
('1102175', '7100', 'Cape'),	/*Cutie Birk Wings*/
/*Page 8*/
('1102144', '6300', 'Cape'),	/*Sage Cape*/
('1102148', '4000', 'Cape'),	/*Tania Cloak*/
('1102149', '5000', 'Cape'),	/*Mercury Cloak*/
('1102150', '4700', 'Cape'),	/*Count Dracula Cape*/
('1102151', '6000', 'Cape'),	/*Lost Kitty*/
('1102152', '4000', 'Cape'),	/*Pirate Emblem Flag*/
('1102153', '6400', 'Cape'),	/*Sunfire Wings*/
('1102154', '8800', 'Cape'),	/*Zakum Arms*/
('1102155', '3400', 'Cape'),	/*My Buddy Rex*/
/*Page 9*/
('1102156', '3800', 'Cape'),	/*Aerial Wave Cape*/
('1102157', '4000', 'Cape'),	/*Puppet Strings*/
('1102158', '4900', 'Cape'),	/*Peacock Feather Cape*/
('1102159', '6300', 'Cape'),	/*White Monkey Balloon*/
('1102196', '3600', 'Cape'),	/*Snowflake Scarf*/
('1102197', '6400', 'Cape'),	/*Yellow Canary*/
('1102202', '6000', 'Cape'),	/*Galactic Flame Cape*/
('1102203', '5200', 'Cape'),	/*Super Rocket Booster*/
('1102204', '4900', 'Cape'),	/*Romantic Rose*/
/*Page 10*/
('1102184', '8800', 'Cape'),	/*Aurora Happy Wing*/
('1102185', '5200', 'Cape'),	/*Rainbow Scarf*/
('1102186', '6400', 'Cape'),	/*Kitty Parachute*/
('1102187', '6000', 'Cape'),	/*Golden Fox Tail*/
('1102188', '7400', 'Cape'),	/*Silver Fox Tail*/
('1102224', '8800', 'Cape'),	/*Lamby Cape*/
('1102229', '4700', 'Cape'),	/*Bear Cape*/
('1102230', '5000', 'Cape'),	/*Penguin Sled*/
('1102232', '7100', 'Cape'),	/*Celestial Star*/
/*Page 11*/
('1102233', '4900', 'Cape'),	/*Snowman Cape*/
('1102238', '6400', 'Cape'),	/*Cat Set Tail*/
('1102239', '6000', 'Cape'),	/*Dual Blade Cape*/
('1102208', '5600', 'Cape'),	/*Slime Effect Cape*/
('1102209', '4900', 'Cape'),	/*Baby White Monkey Balloon*/
('1102210', '5200', 'Cape'),	/*Honeybee's Sting*/
('1102211', '3400', 'Cape'),	/*Bound Wings*/
('1102212', '5200', 'Cape'),	/*Lost Child*/
('1102213', '7400', 'Cape'),	/*Pink Bean Tail*/
/*Page 12*/
('1102214', '3200', 'Cape'),	/*Pink Bean Balloon*/
('1102215', '6000', 'Cape'),	/*Balloon Bouquet*/
('1102216', '6300', 'Cape'),	/*Brown Dog Tail*/
('1102217', '4900', 'Cape'),	/*Goblin Cat*/
('1102218', '3400', 'Cape'),	/*Pink Floating Ribbon*/
('1102220', '8800', 'Cape'),	/*Pachinko Marble-box Cape*/
('1102221', '5000', 'Cape'),	/*Pluto Flame Cape*/
('1102222', '4900', 'Cape'),	/*Seraphim Cape*/
('1102223', '6300', 'Cape'),	/*Star Tail*/
/*Page 13*/
('1102257', '3600', 'Cape'),	/*Marines Maple Balloon*/
('1102258', '4700', 'Cape'),	/*Teddy Bear Balloon*/
('1102259', '3400', 'Cape'),	/*Flying Dragon Cape*/
('1102261', '5200', 'Cape'),	/*Equalizer Scarf*/
('1102267', '5600', 'Cape'),	/*Friendly Nine-Tailed Fox Tails*/
('1102270', '5000', 'Cape'),	/*Moon and Sun Cape*/
('1102271', '4000', 'Cape'),	/*Lovely Chocolate Balloons*/
('1102240', '7400', 'Cape'),	/*Royal Cape*/
('1102242', '7100', 'Cape'),	/*Hawkeye Ocean Cape*/
/*Page 14*/
('1102243', '7100', 'Cape'),	/*Dunas Cape*/
('1102245', '3800', 'Cape'),	/*Sun Cape*/
('1102249', '6300', 'Cape'),	/*Oz Magic Cape*/
('1102250', '4700', 'Cape'),	/*Murgoth's Feather*/
('1102251', '8800', 'Cape'),	/*World Cup Towel*/
('1102252', '3200', 'Cape'),	/*Phoenix Wing*/
('1102253', '6300', 'Cape'),	/*Purple Wings*/
('1102254', '7600', 'Cape'),	/*Wild Hunter Cape*/
('1102255', '5600', 'Cape'),	/*Battle Mage Cape*/
/*Page 15*/
('1102288', '6300', 'Cape'),	/*Piggyback Snowman*/
('1102290', '6400', 'Cape'),	/*Silken Flower Cape*/
('1102291', '7600', 'Cape'),	/*Nekomata*/
('1102292', '5000', 'Cape'),	/*Twinkling Rainbow*/
('1102296', '3200', 'Cape'),	/*Gray Puppy Tail*/
('1102300', '5600', 'Cape'),	/*6th B-Day Party Ball*/
('1102301', '3800', 'Cape'),	/*Traveler's Cape*/
('1102273', '3400', 'Cape'),	/*Lucifer Half Wing*/
('1102285', '7400', 'Cape'),	/*Pink Teru Cape*/
/*Page 16*/
('1102286', '6000', 'Cape'),	/*Blue Teru Cape*/
('1102287', '3600', 'Cape'),	/*Yellow Teru Cape*/
('1102323', '7400', 'Cape'),	/*Legends Pink Balloon*/
('1102324', '3400', 'Cape'),	/*Legends Twin Balloons*/
('1102325', '3800', 'Cape'),	/*Harmony Wings*/
('1102326', '7600', 'Cape'),	/*Angelic Feathers*/
('1102307', '5200', 'Cape'),	/*New Sachiel Wings*/
('1102308', '4000', 'Cape'),	/*New Veamoth Wings*/
('1102309', '7400', 'Cape'),	/*New Janus Wings*/
/*Page 17*/
('1102310', '4900', 'Cape'),	/*Fairytale Mantle*/
('1102318', '7100', 'Cape'),	/*Demon Wings*/
('1102319', '5000', 'Cape'),	/*Legends Balloon*/
('1102355', '5600', 'Cape'),	/*Jewel Blizzard*/
('1102356', '4900', 'Cape'),	/*Angelic Emerald*/
('1102357', '5200', 'Cape'),	/*Pretty Pink Bean Balloon*/
('1102358', '6300', 'Cape'),	/*Round-We-Go Mirror Ball*/
('1102359', '8800', 'Cape'),	/*Floaty Snowman Balloon*/
('1102367', '6400', 'Cape'),	/*Elven Spirit Cape*/
/*Page 18*/
('1102336', '5600', 'Cape'),	/*Alchemist Cape*/
('1102338', '7600', 'Cape'),	/*Honeybee Wings */
('1102343', '6000', 'Cape'),	/*Dark Force Cape */
('1102344', '6000', 'Cape'),	/*Elven Spirit Cape*/
('1102349', '5200', 'Cape'),	/*Fairy Wing Cape*/
('1102350', '8800', 'Cape'),	/*Pink Teru Cape*/
('1102385', '5200', 'Cape'),	/*Lux Cherubim*/
('1102386', '8800', 'Cape'),	/*Nox Cherubim*/
('1102387', '5200', 'Cape'),	/*Blue Dragon Tail*/
/*Page 19*/
('1102388', '6000', 'Cape'),	/*Red Dragon Tail*/
('1102389', '6000', 'Cape'),	/*Aurora Pharady*/
('1102390', '3800', 'Cape'),	/*Aurora Angel*/
('1102391', '4700', 'Cape'),	/*Honeybee Wings */
('1102392', '6300', 'Cape'),	/*Dainty Cape*/
('1102396', '7100', 'Cape'),	/*Ebony Pimpernel Cape*/
('1102368', '7600', 'Cape'),	/*Floating Silken Flower Cape*/
('1102373', '6300', 'Cape'),	/*Lucia Cape*/
('1102374', '3800', 'Cape'),	/*Monkey Cape*/
/*Page 20*/
('1102376', '6300', 'Cape'),	/*Psyche Flora*/
('1102377', '5000', 'Cape'),	/*Psyche Mystic*/
('1102378', '4900', 'Cape'),	/*Psyche Melody*/
('1102380', '5200', 'Cape'),	/*Frog Cronies*/
('1102381', '8800', 'Cape'),	/*Imperial Duke Wing*/
('1102419', '6400', 'Cape'),	/*Lucia Cape*/
('1102420', '3200', 'Cape'),	/*Magic Star Cape*/
('1102421', '5000', 'Cape'),	/*Lemon Floating Smile*/
('1102423', '3800', 'Cape'),	/*Euro Balloon (PL)*/
/*Page 21*/
('1102424', '7100', 'Cape'),	/*Euro Balloon (GR)*/
('1102425', '3800', 'Cape'),	/*Euro Balloon (RU)*/
('1102426', '8800', 'Cape'),	/*Euro Balloon (CZ)*/
('1102427', '4900', 'Cape'),	/*Euro Balloon (NL)*/
('1102428', '4700', 'Cape'),	/*Euro Balloon (DK)*/
('1102429', '4700', 'Cape'),	/*Euro Balloon (DE)*/
('1102430', '5200', 'Cape'),	/*Euro Balloon (PT)*/
('1102431', '8800', 'Cape'),	/*Euro Balloon (ES)*/
('1102450', '7100', 'Cape'),	/*Heavenly Aura*/
/*Page 22*/
('1102451', '4000', 'Cape'),	/*Void Aura*/
('1102452', '4000', 'Cape'),	/*Fairy Aura*/
('1102453', '6000', 'Cape'),	/*Dryad*/
('1102461', '3600', 'Cape'),	/*Valentine's Cape*/
('1102432', '4900', 'Cape'),	/*Euro Balloon (IT)*/
('1102433', '5000', 'Cape'),	/*Euro Balloon (IE)*/
('1102434', '3600', 'Cape'),	/*Euro Balloon (HR)*/
('1102435', '7600', 'Cape'),	/*Euro Balloon (UA)*/
('1102436', '4900', 'Cape'),	/*Euro Balloon (SE)*/
/*Page 23*/
('1102437', '5600', 'Cape'),	/*Euro Balloon (FR)*/
('1102438', '5200', 'Cape'),	/*Euro Balloon (GB)*/
('1102486', '6400', 'Cape'),	/*BasilMarket Billionaire Balloon*/
('1102487', '6000', 'Cape'),	/*Luminous Cherubim*/
('1102488', '4700', 'Cape'),	/*Cupcake Balloon*/
('1102491', '4900', 'Cape'),	/*Sunny Day*/
('1102465', '6000', 'Cape'),	/*Jett's Cape*/
('1102466', '6000', 'Cape'),	/*Flying Nobilitas*/
('1102512', '6000', 'Cape'),	/*Dark Force Cape*/
/*Page 24*/
('1102513', '8800', 'Cape'),	/*Flying Nobilitas*/
('1102496', '5000', 'Cape'),	/*Hyper Honeybee Wings*/
('1102501', '4000', 'Cape'),	/*[MS Discount] Nine-Tailed Fox Tails*/
('1102503', '6000', 'Cape'),	/*Frisky Cat Tail*/
('1102508', '3800', 'Cape'),	/*Koala Cape*/
('1102510', '3200', 'Cape'),	/*Ribbon Kitty Tail*/
('1102511', '8800', 'Cape'),	/*Angel Cherub*/
('1102544', '3800', 'Cape'),	/*Albatross Cape*/
('1102545', '5000', 'Cape'),	/*Albatross Cape*/
/*Page 25*/
('1102546', '4000', 'Cape'),	/*Blue Bird Dream Wings*/
('1102547', '3600', 'Cape'),	/*Amethyst Dream Wings*/
('1102548', '3600', 'Cape'),	/*Leafy Dream Wings*/
('1102549', '6400', 'Cape'),	/*Steward Cat*/
('1102550', '3400', 'Cape'),	/*Lime Green Wings*/
('1102551', '7100', 'Cape'),	/*Sapphire Wings*/
('1102554', '4900', 'Cape'),	/*Succubus Wings*/
('1102555', '4900', 'Cape'),	/*Angelic White Wings*/
('1102532', '4900', 'Cape'),	/*Light Wing Cherubim*/
/*Page 26*/
('1102537', '7400', 'Cape'),	/*Magic Star Cape*/
('1102542', '7400', 'Cape'),	/*Hawkeye Ocean Cape*/
('1102543', '3200', 'Cape'),	/*Oz Magic Cape*/
('1102576', '4900', 'Cape'),	/*Nox Cherubim*/
('1102577', '7600', 'Cape'),	/*Aurora Angel*/
('1102582', '5600', 'Cape'),	/*GM Daejang's Lucia Cape*/
('1102583', '5000', 'Cape'),	/*Baby Dragon Pobi*/
('1102587', '4900', 'Cape'),	/*Heavenly Aura*/
('1102588', '4900', 'Cape'),	/*Void Aura*/
/*Page 27*/
('1102589', '3200', 'Cape'),	/*Fairy Aura*/
('1102591', '3400', 'Cape'),	/*Battle Monster Victory Cape*/
('1102564', '5000', 'Cape'),	/*Angel's Ribbon*/
('1102572', '7400', 'Cape'),	/*Gratias Aura*/
('1102574', '3600', 'Cape'),	/*Chicky Pile*/
('1102575', '3800', 'Cape'),	/*Lux Cherubim*/
('1102608', '7100', 'Cape'),	/*Superstar Mirror Ball*/
('1102609', '3600', 'Cape'),	/*Psyche Flora*/
('1102610', '7400', 'Cape'),	/*Psyche Mystic*/
/*Page 28*/
('1102611', '4700', 'Cape'),	/*Psyche Melody*/
('1102613', '4900', 'Cape'),	/*Futuroid Tail Sensor*/
('1102614', '5200', 'Cape'),	/*Futuroid Tail Sensor*/
('1102615', '6000', 'Cape'),	/*Clocktower Wind-up Doll*/
('1102616', '6000', 'Cape'),	/*Lapis's Spirit*/
('1102617', '6300', 'Cape'),	/*Lazuli's Spirit*/
('1102619', '6400', 'Cape'),	/*Icy Sweet Penguin*/
('1102620', '7400', 'Cape'),	/*My Own Fireworks*/
('1102621', '4900', 'Cape'),	/*Nagging Megaphone*/
/*Page 29*/
('1102622', '5000', 'Cape'),	/*Princess of Time Pocket Watch*/
('1102592', '4900', 'Cape'),	/*Battle Monster Consolation Cape*/
('1102593', '4700', 'Cape'),	/*Floaty Baseball*/



/*Page 1*/
('1102604', '6300', 'Cape 2'),	/*Gear Wing*/
('1102605', '3400', 'Cape 2'),	/*Shadow Peacemaker*/
('1102640', '3200', 'Cape 2'),	/*Aran's Cape*/
('1102641', '4000', 'Cape 2'),	/*Yui's Spirit*/
('1102642', '5000', 'Cape 2'),	/*Yui's Wings*/
('1102643', '5200', 'Cape 2'),	/*Golden Age*/
('1102644', '4700', 'Cape 2'),	/*Pretty Pixie*/
('1102648', '5000', 'Cape 2'),	/*Mr. K's Cat Tail*/
('1102650', '6000', 'Cape 2'),	/*Eunwol Foxtail*/
/*Page 2*/
('1102651', '6400', 'Cape 2'),	/*Red Panda Tail*/
('1102652', '6000', 'Cape 2'),	/*Chipmunk Tail*/
('1102653', '5600', 'Cape 2'),	/*Deluxe Rabbit Tail*/
('1102654', '5200', 'Cape 2'),	/*Puppy Tail*/
('1102655', '4900', 'Cape 2'),	/*Bear Tail*/
('1102624', '7400', 'Cape 2'),	/*Aeolus Aura*/
('1102625', '3800', 'Cape 2'),	/*Snail Shell*/
('1102629', '4700', 'Cape 2'),	/*Pink Cherubim*/
('1102630', '8800', 'Cape 2'),	/*Romantic Wing Cherubim*/
/*Page 3*/
('1102631', '4000', 'Cape 2'),	/*Vampire Phantom Cape*/
('1102632', '3400', 'Cape 2'),	/*Shadow Peacemaker*/
('1102673', '4900', 'Cape 2'),	/*Ghost Balloon*/
('1102674', '4900', 'Cape 2'),	/*Food Escape*/
('1102675', '6400', 'Cape 2'),	/*Candy Party Ribbon*/
('1102682', '5000', 'Cape 2'),	/*Nurse Syringe*/
('1102683', '6300', 'Cape 2'),	/*Rabbit and Bear Book Bag*/
('1102684', '5600', 'Cape 2'),	/*Doctor Stethoscope*/
('1102685', '7600', 'Cape 2'),	/*Baby Pink Panda Cape*/
/*Page 4*/
('1102656', '7400', 'Cape 2'),	/*Bunny Tail*/
('1102657', '6000', 'Cape 2'),	/*Cat o' Nine Tails*/
('1102658', '7400', 'Cape 2'),	/*Cute Kitty Tail*/
('1102667', '7100', 'Cape 2'),	/*Magical Misty Moon*/
('1102668', '4000', 'Cape 2'),	/*Night Angel Wings*/
('1102669', '3800', 'Cape 2'),	/*Royal Spoiled Fairy*/
('1102705', '3400', 'Cape 2'),	/*Island Travel Bags*/
('1102706', '3800', 'Cape 2'),	/*Melodic Aurora*/
('1102707', '3200', 'Cape 2'),	/*Dreaming Conch*/
/*Page 5*/
('1102708', '8800', 'Cape 2'),	/*Blushy Conch*/
('1102709', '3200', 'Cape 2'),	/*Lumina Flutter*/
('1102712', '6300', 'Cape 2'),	/*Long-awaited Resort*/
('1102688', '4000', 'Cape 2'),	/*Boom Boom Fireworks*/
('1102694', '3200', 'Cape 2'),	/*Mini-Mini Slime*/
('1102695', '6300', 'Cape 2'),	/*Spirited Nine Tails*/
('1102697', '6400', 'Cape 2'),	/*Ruby Dragonfly Wings*/
('1102698', '4900', 'Cape 2'),	/*Emerald Dragonfly Wings*/
('1102699', '5000', 'Cape 2'),	/*Magma Wings*/
/*Page 6*/
('1102700', '3800', 'Cape 2'),	/*Petit Ciel*/
('1102702', '6400', 'Cape 2'),	/*Ruby Monarch*/
('1102703', '6400', 'Cape 2'),	/*Jade Monarch*/
('1102747', '4900', 'Cape 2'),	/*Cutie Pandas*/
('1102748', '4700', 'Cape 2'),	/*Rabbit-Bear Camping Bag*/
('1102749', '8800', 'Cape 2'),	/*Starland Balloon*/
('1102723', '3800', 'Cape 2'),	/*Giant Bright Angel Wings*/
('1102724', '3400', 'Cape 2'),	/*Giant Dark Devil Wings*/
('1102725', '8800', 'Cape 2'),	/*Flopping Baby Sea Otter*/
/*Page 7*/
('1102726', '4900', 'Cape 2'),	/*Carrot Cape*/
('1102729', '4700', 'Cape 2'),	/*Glowing Lights*/
('1102730', '5200', 'Cape 2'),	/*Glorious Aura*/
('1102768', '4900', 'Cape 2'),	/*Worn Witch Cape*/
('1102769', '6000', 'Cape 2'),	/*Witch Cape*/
('1102772', '3800', 'Cape 2'),	/*Worn Ghost Cape*/
('1102773', '5600', 'Cape 2'),	/*Ghost Cape*/
('1102774', '5200', 'Cape 2'),	/*Total Lunar Eclipse Cape*/
('1102778', '6000', 'Cape 2'),	/*Lolli Lolli Lollipop*/
/*Page 8*/
('1102779', '3800', 'Cape 2'),	/*Gold Wing*/
('1102780', '6300', 'Cape 2'),	/*With Eren*/
('1102781', '6300', 'Cape 2'),	/*With Mikasa*/
('1102782', '7600', 'Cape 2'),	/*With Annie*/
('1102783', '5600', 'Cape 2'),	/*With Sasha*/
('1102754', '7400', 'Cape 2'),	/*Idol of the Birds*/
('1102755', '7100', 'Cape 2'),	/*Boom Star Balloon*/
('1102756', '7100', 'Cape 2'),	/*Corn Cape*/
('1102758', '3200', 'Cape 2'),	/*Victory Wings*/
/*Page 9*/
('1102759', '6300', 'Cape 2'),	/*Ball Buddies*/
('1102766', '7400', 'Cape 2'),	/*Raging Lotus Aura*/
('1102767', '5600', 'Cape 2'),	/*Ill Orchid IV*/
('1102800', '6300', 'Cape 2'),	/*Fluffy Bell Cape*/
('1102801', '3200', 'Cape 2'),	/*Silver Wolf Coat*/
('1102802', '3800', 'Cape 2'),	/*Round-We-Go Mirror Ball*/
('1102803', '3400', 'Cape 2'),	/*Pretty Pink Bean Balloon*/
('1102804', '7400', 'Cape 2'),	/*Pink Teru Cape*/
('1102805', '4000', 'Cape 2'),	/*Floaty Snowman Balloon*/
/*Page 10*/
('1102806', '5000', 'Cape 2'),	/*Cutie Birk Wings*/
('1102808', '4000', 'Cape 2'),	/*Loved Mouse Couple*/
('1102809', '3200', 'Cape 2'),	/*Death Waltz Cloak*/
('1102811', '6000', 'Cape 2'),	/*Snow Bloom*/
('1102812', '7100', 'Cape 2'),	/*Blizzard Drive*/
('1102813', '4900', 'Cape 2'),	/*Shoulder Blanche*/
('1102815', '5200', 'Cape 2'),	/*Lucky Charm Bag*/
('1102784', '4900', 'Cape 2'),	/*With Christa*/
('1102785', '4000', 'Cape 2'),	/*With Levi*/
/*Page 11*/
('1102786', '6400', 'Cape 2'),	/*Titan Escape*/
('1102787', '7600', 'Cape 2'),	/*Scout Regiment Cape*/
('1102788', '6000', 'Cape 2'),	/*With Armin*/
('1102789', '6300', 'Cape 2'),	/*Snow Bear Scarf*/
('1102798', '4700', 'Cape 2'),	/*Blue Bird Wings*/
('1102832', '5200', 'Cape 2'),	/*Machine Cape*/
('1102835', '6400', 'Cape 2'),	/*Schwarzer Coat*/
('1102836', '7400', 'Cape 2'),	/*Wonder Kitty*/
('1102837', '6400', 'Cape 2'),	/*Dreams Within Dreams*/
/*Page 12*/
('1102839', '6300', 'Cape 2'),	/*Pink Zakum Arms*/
('1102841', '7400', 'Cape 2'),	/*Iris Pearl*/
('1102842', '5000', 'Cape 2'),	/*Pineapple Bag*/
('1102843', '6400', 'Cape 2'),	/*Pink Cherubim*/
('1102844', '6400', 'Cape 2'),	/*Mousy Bunny Bouncy Buddies*/
('1102845', '3800', 'Cape 2'),	/*Blue Panda*/
('1102847', '7400', 'Cape 2'),	/*Yeonhwa School Guardian Soul Fire*/
('1102816', '5000', 'Cape 2'),	/*Fairy Bell*/
('1102818', '4900', 'Cape 2'),	/*Crystal Cat Star Cape*/
/*Page 13*/
('1102819', '4700', 'Cape 2'),	/*Naughty Boy Backpack*/
('1102820', '4000', 'Cape 2'),	/*Hazy Night Tassel*/
('1102822', '3200', 'Cape 2'),	/*Flowery Breeze*/
('1102823', '6000', 'Cape 2'),	/*Petite Devil Wings*/
('1102824', '6000', 'Cape 2'),	/*Halfblood Wings*/
('1102827', '5600', 'Cape 2'),	/*The Kingdom Cape of King*/
('1102830', '3600', 'Cape 2'),	/*돼지바의 요정*/
('1102831', '7400', 'Cape 2'),	/*Soaring High*/
('1102864', '4000', 'Cape 2'),	/*Farmer's Grace*/
/*Page 14*/
('1102865', '4700', 'Cape 2'),	/*Thundercrash Cape*/
('1102868', '3200', 'Cape 2'),	/*Triple Bat Cape*/
('1102869', '3400', 'Cape 2'),	/*Bloody Rose*/
('1102870', '7600', 'Cape 2'),	/*Midnight Black Coffin*/
('1102872', '7600', 'Cape 2'),	/*Shining Noblesse*/
('1102873', '3600', 'Cape 2'),	/*Eternal Noblesse*/
('1102874', '6000', 'Cape 2'),	/*Lumin Wings*/
('1102875', '7400', 'Cape 2'),	/*Amnesiac Alien*/
('1102876', '7600', 'Cape 2'),	/*Selfie Time*/
/*Page 15*/
('1102877', '5600', 'Cape 2'),	/*Blue Marine Sunshine*/


('1102848', '5200', 'Cape 2'),	/*Gravity*/
('1102849', '4700', 'Cape 2'),	/*Gravity*/
('1102857', '5200', 'Cape 2'),	/*Legendary Fish Man*/
('1102858', '7400', 'Cape 2'),	/*Eternal Clockwork*/
('1102859', '4000', 'Cape 2'),	/*Sapphire Snow*/
('1102860', '5000', 'Cape 2'),	/*British Weather Cape*/
/*Page 16*/
('1102861', '6400', 'Cape 2'),	/*Ursus Light*/
('1102863', '3400', 'Cape 2'),	/*Sparkly Rainbow Cape*/
('1102896', '4900', 'Cape 2'),	/*Chiaroscuro Luminous Cape*/
('1102897', '4900', 'Cape 2'),	/*Chiaroscuro Luminous Cape*/
('1102898', '3800', 'Cape 2'),	/*Secret Shade Cape*/
('1102899', '4900', 'Cape 2'),	/*Secret Shade Cape*/
('1102900', '7400', 'Cape 2'),	/*Lumpy Snowflakes*/
('1102901', '3400', 'Cape 2'),	/*Sparkler*/
('1102902', '3200', 'Cape 2'),	/*Dokidoki*/
/*Page 17*/
('1102903', '6300', 'Cape 2'),	/*Floating Monkey*/
('1102904', '3200', 'Cape 2'),	/*Eternal Clockwork*/
('1102905', '6300', 'Cape 2'),	/*Today's Sunshine Cape*/
('1102906', '5000', 'Cape 2'),	/*Snug Black Nero*/
('1102907', '8800', 'Cape 2'),	/*Ice Flower Wing*/
('1102908', '3200', 'Cape 2'),	/*Winter Garden Cape*/
('1102910', '6400', 'Cape 2'),	/*Smile Seed Cape*/


/*Page 18*/


('1102884', '5000', 'Cape 2'),	/*Angelic Polar Cape*/
('1102885', '8800', 'Cape 2'),	/*Fluffy Fox Tail (Gold)*/
('1102886', '6300', 'Cape 2'),	/*Fluffy Fox Tail (Silver)*/
('1102888', '3800', 'Cape 2'),	/*Evan Dragon Cape*/
('1102889', '7100', 'Cape 2'),	/*Evan Dragon Cape*/
('1102890', '7100', 'Cape 2'),	/*Royal Mercedes Cape*/
('1102891', '3600', 'Cape 2'),	/*Royal Mercedes Cape*/
/*Page 19*/
('1102892', '7100', 'Cape 2'),	/*Mystic Phantom Cape*/
('1102893', '4700', 'Cape 2'),	/*Mystic Phantom Cape*/
('1102894', '7400', 'Cape 2'),	/*Winter Aran Cape*/
('1102895', '6400', 'Cape 2'),	/*Winter Aran Cape*/
('1102928', '5200', 'Cape 2'),	/*Sweet Berry*/
('1102929', '6300', 'Cape 2'),	/*Jet Black Devil*/
('1102930', '6300', 'Cape 2'),	/*Spring Rain Drippy-drop*/
('1102931', '3400', 'Cape 2'),	/*Noble Maple Cape*/
('1102932', '4000', 'Cape 2'),	/*Alicia's Flower Wings*/
/*Page 20*/
('1102933', '5600', 'Cape 2'),	/*Alicia's Flower Wings*/
('1102934', '6000', 'Cape 2'),	/*Snow Angel*/
('1102935', '6300', 'Cape 2'),	/*Whistling Wind*/
('1102936', '3800', 'Cape 2'),	/*Forest Whisper*/
('1102937', '6300', 'Cape 2'),	/*Cats All Over Cape*/
('1102938', '8800', 'Cape 2'),	/*Fantastic Beach Cape*/
('1102939', '3400', 'Cape 2'),	/*Red Cloud Cape*/
('1102912', '3800', 'Cape 2'),	/*Umbral Cloak*/
('1102913', '5600', 'Cape 2'),	/*Flower Dancer's Red Cape*/
/*Page 21*/
('1102914', '3400', 'Cape 2'),	/*Dancing Blue Butterflies*/
('1102915', '8800', 'Cape 2'),	/*Concert Muse*/
('1102916', '6000', 'Cape 2'),	/*Baby Binkie Toys*/
('1102917', '8800', 'Cape 2'),	/*Angel's Cookie Backpack*/
('1102918', '7400', 'Cape 2'),	/*Blazing Aura*/
('1102919', '5000', 'Cape 2'),	/*Tag-along Baby Duck*/
('1102920', '5600', 'Cape 2'),	/*Spring Energy*/
('1102923', '7600', 'Cape 2'),	/*Singles Army Combat Cape*/
('1102924', '5200', 'Cape 2'),	/*Couples Army Combat Cape*/
/*Page 22*/
('1102926', '5000', 'Cape 2'),	/*Shark Cape*/
('1102927', '7100', 'Cape 2'),	/*Windy Paw Scarf*/
('1102960', '7100', 'Cape 2'),	/*Pure Dream Wings*/
('1102961', '5000', 'Cape 2'),	/*Alert! Alert!*/
('1102964', '3400', 'Cape 2'),	/*Sweetheart's Affection*/
('1102965', '5600', 'Cape 2'),	/*Dancing Fireflies*/
('1102966', '5000', 'Cape 2'),	/*Puck's Cape*/
('1102967', '5000', 'Cape 2'),	/*Vampire Phantom Cloak*/
('1102968', '5600', 'Cape 2'),	/*Icicle Wings*/
/*Page 23*/
('1102969', '7400', 'Cape 2'),	/*Clear Skies*/
('1102970', '5200', 'Cape 2'),	/*Rainy Day*/
('1102971', '4700', 'Cape 2'),	/*Lightning Storm*/
('1102972', '6000', 'Cape 2'),	/*Winter Wings*/
('1102973', '6300', 'Cape 2'),	/*Trace of the Alicorn*/
('1102974', '5200', 'Cape 2'),	/*Stellar Specters*/
('1102975', '5200', 'Cape 2'),	/*Cat Balloon Cape*/
('1102945', '6400', 'Cape 2'),	/*Kitty Wuv*/
('1102946', '3600', 'Cape 2'),	/*Sarasa*/
/*Page 24*/
('1102947', '5000', 'Cape 2'),	/*New Angelic Emerald*/
('1102948', '7600', 'Cape 2'),	/*Lil Neon Wings*/
('1102949', '6400', 'Cape 2'),	/*Bubble Wings*/
('1102950', '3400', 'Cape 2'),	/*Bu-bu-bubbles!*/
('1102951', '6300', 'Cape 2'),	/*Popsicle Pals*/

('1102953', '3200', 'Cape 2'),	/*Kamaitachi Tail*/
('1102954', '5200', 'Cape 2'),	/*Owl Balloon*/
('1102955', '6000', 'Cape 2'),	/*Moon Bunny Cape*/
/*Page 25*/
('1102956', '8800', 'Cape 2'),	/*Dark Musician Scarf*/
('1102957', '7400', 'Cape 2'),	/*Chained Princess Chain*/
('1102958', '3200', 'Cape 2'),	/*Light Bulb Wings*/
('1102959', '5600', 'Cape 2'),	/*Sultry Dream Wings*/
('1102992', '5000', 'Cape 2'),	/*Star Shadow*/
('1102995', '4900', 'Cape 2'),	/*Nova Enchanter Winged Cape*/
('1102998', '4900', 'Cape 2'),	/*Fuzzy Cotton Tail*/
('1102999', '6000', 'Cape 2'),	/*Mischievous Rainbow*/
('1103000', '6300', 'Cape 2'),	/*Rocket Fuse Cape*/
/*Page 26*/
('1103001', '4000', 'Cape 2'),	/*Korean Kite Cape*/
('1103003', '5200', 'Cape 2'),	/*Heart Bling Wings*/
('1103004', '4700', 'Cape 2'),	/*Pthbttt Cape*/
('1103007', '4900', 'Cape 2'),	/*Demon Bag*/
('1102976', '4000', 'Cape 2'),	/*Sweet Sugar Powder*/
('1102977', '4700', 'Cape 2'),	/*Fried Chicken God's Blessings*/
('1102978', '3800', 'Cape 2'),	/*Christmas Tree Ornament Cape*/
('1102980', '4700', 'Cape 2'),	/*Lil Dark Angel Wings*/
('1102981', '8800', 'Cape 2'),	/*Wrapped with Love Cape*/
/*Page 27*/
('1102982', '6400', 'Cape 2'),	/*Phoenix Wing Cape*/
('1102983', '5000', 'Cape 2'),	/*Snowflake Umbrella*/
('1102984', '3400', 'Cape 2'),	/*White Night Cape*/
('1102987', '7100', 'Cape 2'),	/*Maple Galaxy Cape*/
('1102988', '3200', 'Cape 2'),	/*Winter's Whisper*/
('1102989', '6000', 'Cape 2'),	/*Winter's Whisper*/
('1102991', '8800', 'Cape 2'),	/*Polar Explorer Backpack*/
('1103024', '7100', 'Cape 2'),	/*Fantabulous Fruit*/
('1103026', '5000', 'Cape 2'),	/*Forest Breeze Floral Effect*/
/*Page 28*/
('1103027', '5600', 'Cape 2'),	/*Underwater Essentials Cape*/
('1103028', '3800', 'Cape 2'),	/*Elunite Elemental Cape*/
('1103029', '7600', 'Cape 2'),	/*Floral Bubbles*/
('1103031', '7400', 'Cape 2'),	/*Charlotte's Garden*/
('1103032', '4900', 'Cape 2'),	/*Round-We-Go Mirror Ball*/
('1103033', '7400', 'Cape 2'),	/*Refreshing Lemon Cape*/
('1103034', '6000', 'Cape 2'),	/*Summer Flower Fairy Cape*/
('1103035', '4000', 'Cape 2'),	/*Teddy Picnic*/
('1103036', '6000', 'Cape 2'),	/*Shooting Stars*/
/*Page 29*/
('1103037', '3400', 'Cape 2'),	/*Feather Messenger Cape*/
('1103038', '7100', 'Cape 2'),	/*Falling Darkness Cape (F)*/
('1103039', '3200', 'Cape 2'),	/*Black Shadow Cape*/



/*Page 1*/
('1103008', '7100', 'Cape 3'),	/*Pandora Cape*/
('1103009', '4700', 'Cape 3'),	/*Silver Flower Child Cape*/
('1103010', '6000', 'Cape 3'),	/*Skater Love Cape*/
('1103011', '4000', 'Cape 3'),	/*The Erda Flow*/
('1103012', '5000', 'Cape 3'),	/*Detective Mush Cape*/
('1103013', '6000', 'Cape 3'),	/*Blossom Breeze*/
('1103014', '5600', 'Cape 3'),	/*Weightless Sparkle*/
('1103015', '3600', 'Cape 3'),	/*Napoleonic Cape*/
('1103018', '5000', 'Cape 3'),	/*Water Dance*/
/*Page 2*/
('1103019', '5000', 'Cape 3'),	/*Chooble Fluff*/
('1103020', '4700', 'Cape 3'),	/*Space Fluffs*/
('1103021', '5600', 'Cape 3'),	/*Prism Wings*/
('1103022', '3800', 'Cape 3'),	/*Maple Galaxy Cape*/
('1103023', '4000', 'Cape 3'),	/*Dream Breeze*/
('1103056', '4700', 'Cape 3'),	/*Dreamland*/
('1103057', '7400', 'Cape 3'),	/*Fairy Pearl*/
('1103058', '4700', 'Cape 3'),	/*Christmas Bunny Cape*/
('1103060', '5600', 'Cape 3'),	/*Warm Pink Bear Cape*/
/*Page 3*/
('1103061', '4900', 'Cape 3'),	/*Warm Blue Bear Cape*/
('1103065', '5000', 'Cape 3'),	/*Chain Wings*/
('1103066', '7400', 'Cape 3'),	/*Outburst*/
('1103067', '5600', 'Cape 3'),	/*Bound by Dreams*/
('1103068', '3600', 'Cape 3'),	/*Royal Mantle*/
('1103069', '6300', 'Cape 3'),	/*Worn Ghost Cape*/
('1103070', '5200', 'Cape 3'),	/*Worn Witch Cape*/
('1103071', '4900', 'Cape 3'),	/*AT Field*/
('1103040', '3200', 'Cape 3'),	/*I Heart Cats Cape*/
/*Page 4*/
('1103041', '5600', 'Cape 3'),	/*Pastel Wings*/
('1103042', '4700', 'Cape 3'),	/*Falling Darkness Cape (M)*/
('1103043', '7600', 'Cape 3'),	/*Custom Kitty Tail*/
('1103044', '6000', 'Cape 3'),	/*Swishing Kitty Tail*/
('1103045', '8800', 'Cape 3'),	/*Flowery Cat Balloon*/
('1103046', '4000', 'Cape 3'),	/*Shadow Tactician Cape*/
('1103047', '5000', 'Cape 3'),	/*Excavation Dog*/
('1103048', '7600', 'Cape 3'),	/*Puppy Love Samurai Tail (M)*/
('1103049', '3400', 'Cape 3'),	/*Puppy Love Samurai Tail (F)*/
/*Page 5*/
('1103050', '7400', 'Cape 3'),	/*Golden Flash*/
('1103051', '5000', 'Cape 3'),	/*Golden Wind-up*/
('1103052', '4000', 'Cape 3'),	/*Angelic Silk*/
('1103053', '3800', 'Cape 3'),	/*Crimson Fate Seal*/
('1103054', '3400', 'Cape 3'),	/*Crimson Fate Seal*/
('1103055', '5200', 'Cape 3'),	/*Magic Bandmaster*/
('1103089', '6400', 'Cape 3'),	/*Choco Pup*/
('1103090', '5200', 'Cape 3'),	/*Fluff Pup*/
('1103092', '5600', 'Cape 3'),	/*Homeless Cat Cape*/
/*Page 6*/
('1103093', '8800', 'Cape 3'),	/*Anniversary Cape*/
('1103094', '6400', 'Cape 3'),	/*Field of Flowers*/
('1103095', '3600', 'Cape 3'),	/*Shadow Self*/
('1103096', '5000', 'Cape 3'),	/*Squirrel Tail*/
('1103097', '5200', 'Cape 3'),	/*Effusive Exclamations*/
('1103098', '3600', 'Cape 3'),	/*Super Summer Cape (F)*/
('1103099', '8800', 'Cape 3'),	/*Super Summer Cape (M)*/
('1103100', '3400', 'Cape 3'),	/*Ark Cape*/
('1103101', '7400', 'Cape 3'),	/*Cotton Candy Wings*/
/*Page 7*/
('1103102', '4900', 'Cape 3'),	/*Down in the Dumps*/
('1103103', '4700', 'Cape 3'),	/*Maple Gumshoe's File Fairy*/
('1103072', '5000', 'Cape 3'),	/*Soft Cloud*/

('1103074', '6000', 'Cape 3'),	/*First Snow*/
('1103075', '3800', 'Cape 3'),	/*Shoulder Squatter*/
('1103076', '8800', 'Cape 3'),	/*Lunar New Year VIP Cape*/
('1103077', '4900', 'Cape 3'),	/*Royal Guard Cape*/
('1103079', '4700', 'Cape 3'),	/*Plum Blossom Perfume Pouch*/
/*Page 8*/
('1103080', '5200', 'Cape 3'),	/*Ark Aura*/
('1103081', '6300', 'Cape 3'),	/*Happy Puppy*/
('1103082', '4900', 'Cape 3'),	/*World of Pink Cape*/
('1103083', '3400', 'Cape 3'),	/*Butterfly Cape*/
('1103084', '3400', 'Cape 3'),	/*Boyfriend Cape (M)*/
('1103085', '3600', 'Cape 3'),	/*Girlfriend Cape (F)*/
('1103086', '8800', 'Cape 3'),	/*Alchemist Backpack*/
('1103087', '8800', 'Cape 3'),	/*Chocoverse*/
('1103124', '7400', 'Cape 3'),	/*Maple Alliance Cape*/
/*Page 9*/
('1103126', '4900', 'Cape 3'),	/*Misty Nocturne*/
('1103127', '5600', 'Cape 3'),	/*Misty Fantasia*/
('1103128', '3800', 'Cape 3'),	/*Misty Nocturne*/
('1103129', '4000', 'Cape 3'),	/*Misty Fantasia*/
('1103130', '5200', 'Cape 3'),	/*Seafoam Coral Brilliance*/
('1103131', '3200', 'Cape 3'),	/*Reaper's Cape*/
('1103133', '4700', 'Cape 3'),	/*Custom Puppy Tail*/
('1103104', '6000', 'Cape 3'),	/*Picnic Balloons*/
('1103105', '3400', 'Cape 3'),	/*Rock Spirit Triplets*/
/*Page 10*/
('1103106', '7600', 'Cape 3'),	/*Picnic Time*/
('1103107', '6300', 'Cape 3'),	/*Happy Ghost Cape*/
('1103108', '4900', 'Cape 3'),	/*Erda Cape*/
('1103110', '3600', 'Cape 3'),	/*Mechanic Wings*/
('1103111', '3200', 'Cape 3'),	/*14th Anniversary Cape*/
('1103112', '6400', 'Cape 3'),	/*Superfan Tomoyo*/
('1103114', '4900', 'Cape 3'),	/*Clockwork Wings*/
('1103115', '6400', 'Cape 3'),	/*Burning Desire*/

/*Page 11*/
('1103117', '7600', 'Cape 3'),	/*Rain Puddle Cape*/
('1103118', '5000', 'Cape 3'),	/*Spring Green Morning*/
('1103119', '3200', 'Cape 3'),	/*Catkerchief Sack*/
('1103152', '6400', 'Cape 3'),	/*Glimmering Snowflakes*/
('1103153', '7400', 'Cape 3'),	/*Starstruck*/
('1103154', '3800', 'Cape 3'),	/*Floating Golden Piggy*/
('1103155', '3400', 'Cape 3'),	/*Cobalt Filigree Cape*/
('1103156', '3800', 'Cape 3'),	/*Lunar New Year Pudgy Piggy Cape*/
('1103157', '4000', 'Cape 3'),	/*Glittering Elegance Cape*/
/*Page 12*/
('1103158', '3200', 'Cape 3'),	/*Plaid Pashmina (Brown)*/
('1103159', '7600', 'Cape 3'),	/*Plaid Pashmina (Green)*/
('1103160', '6000', 'Cape 3'),	/*Plaid Pashmina (Pink)*/
('1103161', '8800', 'Cape 3'),	/*Plaid Pashmina (Purple)*/
('1103162', '3400', 'Cape 3'),	/*Plaid Pashmina (Red)*/
('1103163', '5000', 'Cape 3'),	/*Plaid Pashmina (Fuschia)*/
('1103164', '4900', 'Cape 3'),	/*Plaid Pashmina (Ivory)*/
('1103138', '4700', 'Cape 3'),	/*Detective's Key*/
('1103139', '4900', 'Cape 3'),	/*Crystal Wings*/
/*Page 13*/
('1103140', '5600', 'Cape 3'),	/*Pastel Angel Wings*/
('1103141', '3200', 'Cape 3'),	/*Heavenly Prayer Cape*/
('1103142', '6000', 'Cape 3'),	/*Alliance Commander Cape*/
('1103144', '4700', 'Cape 3'),	/*Fairy Tale Ballad*/
('1103147', '8800', 'Cape 3'),	/*Tri-color Cape*/
('1103148', '7400', 'Cape 3'),	/*Necrotic Flow*/
('1103149', '3800', 'Cape 3'),	/*Defender's Stone*/
('1103150', '6000', 'Cape 3'),	/*Regal Romance Cape*/
('1103151', '5000', 'Cape 3'),	/*Black Mage's Aura*/
/*Page 14*/
('1103184', '5600', 'Cape 3'),	/*Springtime Sprout Cape*/
('1103189', '4900', 'Cape 3'),	/*Destroyer Cape*/
('1103190', '3600', 'Cape 3'),	/*Creator Cape*/
('1103195', '6300', 'Cape 3'),	/*Red Lotus Spirit Walker's Dark Tent*/
('1103168', '3400', 'Cape 3'),	/*Adventurer's Wings*/
('1103169', '5600', 'Cape 3'),	/*Sugarsweet Candy Cape*/
('1103170', '5600', 'Cape 3'),	/*Peek-a-Boo CONY*/
('1103171', '7400', 'Cape 3'),	/*Lavender Melody*/
('1103172', '5200', 'Cape 3'),	/*Sunny Songbird Cape*/
/*Page 15*/
('1103173', '7600', 'Cape 3'),	/*Midnight Magician Cape*/
('1103175', '6300', 'Cape 3'),	/*Speedy Petite Wings*/
('1103176', '6300', 'Cape 3'),	/*Cursed Hunter Cape*/
('1103179', '7600', 'Cape 3'),	/*Adventurer Cape*/
('1103181', '3800', 'Cape 3'),	/*Blue Flame Hellion Cape*/
('1103182', '4000', 'Cape 3'),	/*Starry Light Cape*/
('1103183', '3600', 'Cape 3'),	/*Fox Fire Spectral Tail*/





/*Page 1*/
('1702585', '3700', 'Transparent'),      /*Universal Transparent Weapon*/
('1002186', '6800', 'Transparent'),      /*Transparent Hat*/
('1012057', '6300', 'Transparent'),      /*Transparent Face Accessory*/
('1022048', '3700', 'Transparent'),      /*Transparent Eye Accessory*/
('1032024', '7800', 'Transparent'),      /*Transparent Earrings*/
('1072153', '6500', 'Transparent'),      /*Transparent Shoes*/
('1082102', '5700', 'Transparent'),      /*Transparent Gloves*/
('1092064', '6500', 'Transparent'),      /*Transparent Shield*/
('1102039', '6300', 'Transparent'),      /*Transparent Cape*/
/*Page 2*/
('1342069', '6300', 'Transparent'),      /*Transparent Katara*/





/*		APPEARANCE		*/

/*Page 1*/
('5160000', '3700', 'Facial Expressions'),      /*Queasy*/
('5160001', '6300', 'Facial Expressions'),      /*Panicky*/
('5160002', '5000', 'Facial Expressions'),      /*Sweetness*/
('5160003', '2700', 'Facial Expressions'),      /*Smoochies*/
('5160004', '6300', 'Facial Expressions'),      /*Wink*/
('5160005', '7400', 'Facial Expressions'),      /*Ouch*/
('5160006', '6300', 'Facial Expressions'),      /*Sparkling Eyes*/
('5160007', '4300', 'Facial Expressions'),      /*Flaming*/
('5160008', '2700', 'Facial Expressions'),      /*Ray*/
/*Page 2*/
('5160009', '2700', 'Facial Expressions'),      /*Goo Goo*/
('5160010', '2700', 'Facial Expressions'),      /*Whoa Whoa*/
('5160011', '3700', 'Facial Expressions'),      /*Constant Sigh*/
('5160012', '5600', 'Facial Expressions'),      /*Drool*/
('5160013', '3200', 'Facial Expressions'),      /*Dragon Breath*/
('5160014', '3200', 'Facial Expressions'),      /*Bleh*/
('5160015', '3700', 'Facial Expressions'),      /*Dizzy*/
('5160016', '2700', 'Facial Expressions'),      /*Awkward*/
('5160017', '6800', 'Facial Expressions'),      /*Villainous*/
/*Page 3*/
('5160019', '6800', 'Facial Expressions'),      /*Queasy*/
('5160020', '6300', 'Facial Expressions'),      /*Panicky*/
('5160021', '4300', 'Facial Expressions'),      /*Sweetness*/
('5160022', '7400', 'Facial Expressions'),      /*Smoochies*/
('5160023', '5600', 'Facial Expressions'),      /*Wink*/
('5160024', '5000', 'Facial Expressions'),      /*Ouch*/
('5160025', '3700', 'Facial Expressions'),      /*Sparkling Eyes*/
('5160026', '6300', 'Facial Expressions'),      /*Flaming*/
('5160027', '3700', 'Facial Expressions'),      /*Ray*/
/*Page 4*/
('5160028', '4300', 'Facial Expressions'),      /*Goo Goo*/
('5160029', '2700', 'Facial Expressions'),      /*Whoa Whoa*/
('5160030', '7400', 'Facial Expressions'),      /*Constant Sigh*/
('5160031', '7400', 'Facial Expressions'),      /*Drool*/
('5160032', '3200', 'Facial Expressions'),      /*Dragon Breath*/
('5160033', '6800', 'Facial Expressions'),      /*Bleh*/
('5160034', '5600', 'Facial Expressions'),      /*Nosebleed*/
('5160035', '6800', 'Facial Expressions'),      /*Awesome*/
('5160036', '7400', 'Facial Expressions'),      /*Troll*/



/*Page 1*/
('5010000', '5000', 'Effect'),      /*Sunny Day*/
('5010001', '2700', 'Effect'),      /*Moon & the Stars*/
('5010002', '2700', 'Effect'),      /*Colorful Rainbow*/
('5010003', '4700', 'Effect'),      /*Little Devil*/
('5010004', '3200', 'Effect'),      /*Underwater*/
('5010005', '3700', 'Effect'),      /*Looking for Love*/
('5010006', '4700', 'Effect'),      /*Baby Angel*/
('5010007', '7400', 'Effect'),      /*Fugitive*/
('5010008', '6800', 'Effect'),      /*Mr. Jackpot*/
/*Page 2*/
('5010009', '5000', 'Effect'),      /*Martial Effect*/
('5010010', '2700', 'Effect'),      /*Play with Me*/
('5010011', '2700', 'Effect'),      /*Loner*/
('5010012', '3200', 'Effect'),      /*Equalizer*/
('5010013', '5000', 'Effect'),      /*Fireworks*/
('5010014', '4700', 'Effect'),      /*Stormy Cloud*/
('5010015', '3700', 'Effect'),      /*777 Effect*/
('5010016', '2700', 'Effect'),      /*Siren*/
('5010017', '3700', 'Effect'),      /*Twinkling Star*/
/*Page 3*/
('5010018', '3700', 'Effect'),      /*Smile*/
('5010019', '6300', 'Effect'),      /*Heart*/
('5010020', '6300', 'Effect'),      /*Go! Korea!*/
('5010021', '4300', 'Effect'),      /*Skeleton of Horror*/
('5010022', '2700', 'Effect'),      /*Star Trail*/
('5010023', '4700', 'Effect'),      /*Pumping Heart*/
('5010024', '4300', 'Effect'),      /*The Flocking Ducks*/
('5010025', '4700', 'Effect'),      /*Silent Spectre*/
('5010026', '3700', 'Effect'),      /*Bat Manager Effect*/
/*Page 4*/
('5010027', '3200', 'Effect'),      /*Hot Head*/
('5010028', '5600', 'Effect'),      /*Indigo Flames*/
('5010029', '5600', 'Effect'),      /*Demonfyre*/
('5010030', '4300', 'Effect'),      /*Nuclear Fire*/
('5010031', '5000', 'Effect'),      /*My Boyfriend*/
('5010032', '4300', 'Effect'),      /*My Girlfriend*/
('5010033', '5600', 'Effect'),      /*Sheer Fear*/
('5010034', '5600', 'Effect'),      /*Christmas Tree*/
('5010035', '5000', 'Effect'),      /*Snowman*/
/*Page 5*/
('5010038', '6800', 'Effect'),      /*Shower Power*/
('5010039', '5000', 'Effect'),      /*Spotlight*/
('5010041', '3700', 'Effect'),      /*Super Symphony*/
('5010042', '3700', 'Effect'),      /*Busy Bee*/
('5010043', '4700', 'Effect'),      /*Eyelighter*/
('5010044', '2700', 'Effect'),      /*Shadow Style*/
('5010045', '4300', 'Effect'),      /*Struck by Lightning*/
('5010046', '5600', 'Effect'),      /*Maple Champion*/
('5010048', '4700', 'Effect'),      /*Maple Champion*/
/*Page 6*/
('5010049', '6300', 'Effect'),      /*Maple Champion*/
('5010051', '7400', 'Effect'),      /*O Maplemas Tree*/
('5010052', '3700', 'Effect'),      /*Santa Sled*/
('5010053', '6300', 'Effect'),      /*Mistletoe*/
('5010054', '4700', 'Effect'),      /*Jingling Santa*/
('5010055', '5000', 'Effect'),      /*UFO*/
('5010056', '5600', 'Effect'),      /*Garden Trail*/
('5010057', '6800', 'Effect'),      /*Flower Fairy*/
('5010059', '5600', 'Effect'),      /*Trail of Darkness Effect*/
/*Page 7*/
('5010060', '5600', 'Effect'),      /*Happy Winter Effect*/
('5010061', '3200', 'Effect'),      /*Ace of Hearts*/
('5010064', '4300', 'Effect'),      /*Rock Band Effect*/
('5010065', '5000', 'Effect'),      /*Scoreboard Effect*/
('5010066', '2700', 'Effect'),      /*Disco Effect*/
('5010068', '4300', 'Effect'),      /*Return of Angel Wing*/
('5010069', '6800', 'Effect'),      /*Seraphim's Dark Wings*/
('5010070', '6300', 'Effect'),      /*Sprite Wings*/
('5010073', '2700', 'Effect'),      /*Miss Popular*/
/*Page 8*/
('5010074', '4700', 'Effect'),      /*Mr. Popular*/
('5010075', '6800', 'Effect'),      /*I'm in London*/
('5010076', '4700', 'Effect'),      /*PARIS Je T'aime*/
('5010078', '4300', 'Effect'),      /*Owl Effect*/
('5010079', '5000', 'Effect'),      /*Cygnus Effect*/
('5010080', '3200', 'Effect'),      /*Spring Rain*/
('5010081', '3200', 'Effect'),      /*Peacock Effect*/
('5010082', '3700', 'Effect'),      /*Shining Star*/
('5010083', '5600', 'Effect'),      /*Winter Wonderland*/
/*Page 9*/
('5010095', '3200', 'Effect'),      /*[Sale] Winter Wonderland*/
('5010096', '2700', 'Effect'),      /*[Sale] Shining Star*/
('5010097', '5600', 'Effect'),      /*[Sale] Echo Wings*/
('5010098', '4300', 'Effect'),      /*[Sale]  Long Lost Angel Wing*/
('5010099', '4300', 'Effect'),      /*[Special] Shadow Style*/
('5010100', '5000', 'Effect'),      /*Maple Style Effect*/
('5010101', '3200', 'Effect'),      /*Rainbow Wings*/
('5010102', '2700', 'Effect'),      /*Sorry!*/
('5010103', '7400', 'Effect'),      /*Friends Plz*/
/*Page 10*/
('5010104', '2700', 'Effect'),      /*Party Plz*/
('5010106', '5600', 'Effect'),      /*Shining Effect*/
('5010109', '3200', 'Effect'),      /*Je t'aime Paris*/
('5010110', '2700', 'Effect'),      /*Rhinne's Protection*/
('5010111', '3700', 'Effect'),      /*Tropical Beach*/
('5010112', '5000', 'Effect'),      /*London Night Effect*/
('5010113', '3200', 'Effect'),      /*PSY Effect*/





/*		PET		*/

/*Page 1*/
('5000000', '11700', 'Pets'),      /*Brown Kitty*/
('5000001', '9800', 'Pets'),      /*Brown Puppy*/
('5000002', '8400', 'Pets'),      /*Pink Bunny*/
('5000003', '11700', 'Pets'),      /*Mini Kargo*/
('5000004', '7500', 'Pets'),      /*Black Kitty*/
('5000005', '7500', 'Pets'),      /*White Bunny*/
('5000006', '9600', 'Pets'),      /*Husky*/
('5000007', '6000', 'Pets'),      /*Black Pig*/
('5000008', '8400', 'Pets'),      /*Panda*/
/*Page 2*/
('5000009', '10600', 'Pets'),      /*Dino Boy*/
('5000010', '9800', 'Pets'),      /*Dino Girl*/
('5000011', '7500', 'Pets'),      /*Monkey*/
('5000012', '8700', 'Pets'),      /*White Tiger*/
('5000013', '9600', 'Pets'),      /*Elephant*/
('5000014', '9800', 'Pets'),      /*Rudolph*/
('5000015', '7500', 'Pets'),      /*Dasher*/
('5000017', '9600', 'Pets'),      /*Robot*/
('5000018', '8400', 'Pets'),      /*Husky*/
/*Page 3*/
('5000020', '10600', 'Pets'),      /*Mini Yeti*/
('5000021', '6600', 'Pets'),      /*Monkey*/
('5000022', '8700', 'Pets'),      /*Turkey*/
('5000023', '6600', 'Pets'),      /*Penguin*/
('5000024', '10600', 'Pets'),      /*Jr. Balrog*/
('5000025', '9600', 'Pets'),      /*Golden Pig*/
('5000026', '10600', 'Pets'),      /*Sun Wu Kong*/
('5000028', '14400', 'Pets'),      /*Dragon*/
('5000029', '6000', 'Pets'),      /*Baby Dragon*/
/*Page 4*/
('5000030', '9800', 'Pets'),      /*Green Dragon*/
('5000031', '10600', 'Pets'),      /*Red Dragon*/
('5000032', '6000', 'Pets'),      /*Blue Dragon*/
('5000033', '10600', 'Pets'),      /*Black Dragon*/
('5000034', '14400', 'Pets'),      /*Black Bunny*/
('5000036', '8700', 'Pets'),      /*Jr. Reaper*/
('5000037', '8700', 'Pets'),      /*Husky*/
('5000038', '9600', 'Pets'),      /*White Monkey*/
('5000039', '9600', 'Pets'),      /*Porcupine*/
/*Page 5*/
('5000041', '6000', 'Pets'),      /*Snowman*/
('5000042', '9600', 'Pets'),      /*Kino*/
('5000044', '6600', 'Pets'),      /*Orange Tiger*/
('5000045', '7500', 'Pets'),      /*Skunk*/
('5000047', '9600', 'Pets'),      /*Robo*/
('5000048', '14400', 'Pets'),      /*Baby Robo*/
('5000049', '7500', 'Pets'),      /*Blue Robo*/
('5000050', '6600', 'Pets'),      /*Red Robo*/
('5000051', '7500', 'Pets'),      /*Green Robo*/
/*Page 6*/
('5000052', '9800', 'Pets'),      /*Gold Robo*/
('5000053', '11700', 'Pets'),      /*Gorilla Robo*/
('5000054', '8400', 'Pets'),      /*Snail*/
('5000055', '9800', 'Pets'),      /*Crys.Rudolph*/
('5000056', '14400', 'Pets'),      /*Toucan*/
('5000058', '9600', 'Pets'),      /*White Duck*/
('5000060', '14400', 'Pets'),      /*Pink Bean*/
('5000066', '6000', 'Pets'),      /*Baby Tiger*/
('5000067', '6000', 'Pets'),      /*Weird Alien*/
/*Page 7*/
('5000070', '7500', 'Pets'),      /*Mir*/
('5000071', '11700', 'Pets'),      /*Ruby*/
('5000074', '9800', 'Pets'),      /*Bing Monkey*/
('5000076', '14400', 'Pets'),      /*Corgi Pup*/
('5000078', '6000', 'Pets'),      /*Monkey*/
('5000079', '11700', 'Pets'),      /*Black Kitty*/
('5000080', '11700', 'Pets'),      /*Penguin*/
('5000082', '6000', 'Pets'),      /*Baby Tiger*/
('5000083', '7500', 'Pets'),      /*Persian Cat*/
/*Page 8*/
('5000084', '10600', 'Pets'),      /*Esel*/
('5000085', '9800', 'Pets'),      /*Cake*/
('5000086', '7500', 'Pets'),      /*Pie*/
('5000087', '14400', 'Pets'),      /*Black Bunny*/
('5000088', '7500', 'Pets'),      /*Black Bunny*/
('5000089', '11700', 'Pets'),      /*Tiel*/
('5000090', '8700', 'Pets'),      /*Galiel*/
('5000091', '8400', 'Pets'),      /*Esel*/
('5000092', '9600', 'Pets'),      /*Tiel*/
/*Page 9*/
('5000093', '8700', 'Pets'),      /*Galiel*/
('5000096', '8700', 'Pets'),      /*Dummbo*/
('5000098', '9800', 'Pets'),      /*Shark*/
('5000100', '10600', 'Pets'),      /*Kino*/
('5000101', '11700', 'Pets'),      /*White Tiger*/
('5000102', '6000', 'Pets'),      /*Mini Yeti*/
('5000103', '14400', 'Pets'),      /*Chroma Bean*/
('5000105', '14400', 'Pets'),      /*Baby Tiger*/
('5000106', '8700', 'Pets'),      /*Ruby*/
/*Page 10*/
('5000107', '6600', 'Pets'),      /*Black Pig*/
('5000108', '14400', 'Pets'),      /*Cake*/
('5000109', '8400', 'Pets'),      /*Pie*/
('5000110', '6000', 'Pets'),      /*Corgi Pup*/
('5000111', '8700', 'Pets'),      /*Persian Cat*/
('5000114', '8400', 'Pets'),      /*Rudolph*/
('5000116', '6000', 'Pets'),      /*Jr. Reaper*/
('5000117', '9600', 'Pets'),      /*White Bunny*/
('5000118', '6600', 'Pets'),      /*Mir*/
/*Page 11*/
('5000120', '6000', 'Pets'),      /*Tiel*/
('5000121', '10600', 'Pets'),      /*Esel*/
('5000122', '9800', 'Pets'),      /*Galiel*/
('5000130', '11700', 'Pets'),      /*Metus*/
('5000131', '8700', 'Pets'),      /*Mors*/
('5000132', '8400', 'Pets'),      /*Invidia*/
('5000133', '9800', 'Pets'),      /*Storm Dragon*/
('5000134', '9600', 'Pets'),      /*Fennec Fox*/
('5000135', '6000', 'Pets'),      /*Gingerbready*/
/*Page 12*/
('5000136', '9600', 'Pets'),      /*Ice Knight*/
('5000138', '9600', 'Pets'),      /*Merlion Pet*/
('5000139', '10600', 'Pets'),      /*Butterfly*/
('5000141', '8700', 'Pets'),      /*Shark*/
('5000142', '10600', 'Pets'),      /*Puffram*/
('5000143', '10600', 'Pets'),      /*Craw*/
('5000144', '6000', 'Pets'),      /*Adriano*/
('5000145', '14400', 'Pets'),      /*Bonkey*/
('5000146', '10600', 'Pets'),      /*Harp Seal*/
/*Page 13*/
('5000147', '6600', 'Pets'),      /*Penguin*/
('5000148', '8400', 'Pets'),      /*White Duck*/
('5000149', '11700', 'Pets'),      /*Silver Husky*/
('5000150', '11700', 'Pets'),      /*Pink Yeti*/
('5000151', '8700', 'Pets'),      /*Bandit*/
('5000152', '6000', 'Pets'),      /*Miracle Cat*/
('5000155', '10600', 'Pets'),      /*Abel*/
('5000156', '10600', 'Pets'),      /*Axel*/
('5000161', '9600', 'Pets'),      /*Pink*/
/*Page 14*/
('5000162', '8700', 'Pets'),      /*Aaron*/
('5000163', '6000', 'Pets'),      /*Mint*/
('5000167', '9600', 'Pets'),      /*Starwing*/
('5000168', '8400', 'Pets'),      /*Stickman*/
('5000170', '14400', 'Pets'),      /*PSY*/
('5000171', '6600', 'Pets'),      /*MagiCookie*/
('5000172', '6000', 'Pets'),      /*Mini Yeti*/
('5000176', '8700', 'Pets'),      /*Kangaroo*/
('5000193', '14400', 'Pets'),      /*Von Soup*/
/*Page 15*/
('5000194', '8700', 'Pets'),      /*Pink Bunny*/
('5000195', '14400', 'Pets'),      /*Black Bunny*/
('5000197', '6000', 'Pets'),      /*Sassy Snake*/
('5000198', '8400', 'Pets'),      /*Lil Moonbeam*/
('5000199', '10600', 'Pets'),      /*Adel*/
('5000201', '14400', 'Pets'),      /*Pink Bean*/
('5000202', '10600', 'Pets'),      /*Corgi Pup*/
('5000203', '7500', 'Pets'),      /*Craw*/
('5000204', '6600', 'Pets'),      /*Adriano*/
/*Page 16*/
('5000205', '14400', 'Pets'),      /*Bonkey*/
('5000206', '9600', 'Pets'),      /*Fennec Fox*/
('5000207', '10600', 'Pets'),      /*Corgi Pup*/
('5000209', '9800', 'Pets'),      /*Storm Dragon*/
('5000210', '8700', 'Pets'),      /*Penguin*/
('5000211', '14400', 'Pets'),      /*Scurvy Bird*/
('5000212', '9800', 'Pets'),      /*Metus*/
('5000213', '11700', 'Pets'),      /*Mors*/
('5000214', '6600', 'Pets'),      /*Invidia*/
/*Page 17*/
('5000215', '14400', 'Pets'),      /*Chunky */
('5000216', '6600', 'Pets'),      /*Brown Burro*/
('5000217', '10600', 'Pets'),      /*Blackheart*/
('5000221', '14400', 'Pets'),      /*Harp Seal*/
('5000225', '8700', 'Pets'),      /*Puffram*/
('5000228', '6600', 'Pets'),      /*Demon Metus*/
('5000229', '10600', 'Pets'),      /*Demon Mors*/
('5000230', '9600', 'Pets'),      /*Invidia*/
('5000231', '9800', 'Pets'),      /*Demon Metus*/
/*Page 18*/
('5000232', '8700', 'Pets'),      /*Demon Mors*/
('5000233', '8400', 'Pets'),      /*Invidia*/
('5000234', '7500', 'Pets'),      /*Metus*/
('5000235', '9600', 'Pets'),      /*Mors*/
('5000236', '14400', 'Pets'),      /*Invidia*/
('5000237', '8700', 'Pets'),      /*Starwing*/
('5000238', '9800', 'Pets'),      /*Baby Tiger*/
('5000239', '6600', 'Pets'),      /*Shark*/
('5000240', '10600', 'Pets'),      /*Pink Bean*/
/*Page 19*/
('5000241', '9600', 'Pets'),      /*Puffram*/
('5000243', '6000', 'Pets'),      /*Pink Dragon*/
('5000244', '7500', 'Pets'),      /*Ice Dragon*/
('5000245', '7500', 'Pets'),      /*Red Dragon*/
('5000246', '9800', 'Pets'),      /*Chroma Bean*/
('5000249', '6000', 'Pets'),      /*Fluffy Teddy*/
('5000250', '6000', 'Pets'),      /*Cutie Teddy*/
('5000251', '11700', 'Pets'),      /*Puffy Teddy*/
('5000254', '9600', 'Pets'),      /*Red Elly*/
/*Page 20*/
('5000255', '14400', 'Pets'),      /*Blue Burro*/
('5000256', '11700', 'Pets'),      /*Pumpkin Jack*/
('5000257', '9600', 'Pets'),      /*Pumpkin Zack*/
('5000258', '14400', 'Pets'),      /*Pumpkin Mack*/
('5000261', '6000', 'Pets'),      /*Royal Thumpy*/
('5000262', '6000', 'Pets'),      /*Merlion*/
('5000263', '6600', 'Pets'),      /*Butterfly*/
('5000264', '8400', 'Pets'),      /*Kangaroo*/
('5000265', '11700', 'Pets'),      /*Crys.Rudolph*/
/*Page 21*/
('5000266', '9800', 'Pets'),      /*Pink Bean*/
('5000269', '6000', 'Pets'),      /*Hedgehog*/
('5000270', '10600', 'Pets'),      /*Fennec Fox*/
('5000271', '14400', 'Pets'),      /*Frumpy Koala*/
('5000272', '6600', 'Pets'),      /*Grumpy Koala*/
('5000273', '9800', 'Pets'),      /*Nerdy Koala*/
('5000275', '7500', 'Pets'),      /*Chippermunk*/
('5000276', '8400', 'Pets'),      /*Chipmunch*/
('5000277', '7500', 'Pets'),      /*Chubmunk*/
/*Page 22*/
('5000281', '6000', 'Pets'),      /*Vile Metus*/
('5000282', '7500', 'Pets'),      /*Dire Mors*/
('5000283', '8400', 'Pets'),      /*Wild Invidia*/
('5000290', '8700', 'Pets'),      /*Honey Angel*/
('5000291', '8700', 'Pets'),      /*Lime Angel*/
('5000292', '9600', 'Pets'),      /*Peach Angel*/
('5000293', '9600', 'Pets'),      /*Roo-A*/
('5000294', '9600', 'Pets'),      /*Roo-B*/
('5000295', '14400', 'Pets'),      /*Roo-C*/
/*Page 23*/
('5000296', '14400', 'Pets'),      /*Toasty Devil*/
('5000297', '7500', 'Pets'),      /*Icy Devil*/
('5000298', '8700', 'Pets'),      /*Miasma Devil*/
('5000299', '10600', 'Pets'),      /*Gingerhead*/
('5000300', '6000', 'Pets'),      /*Devil Ipos*/
('5000301', '11700', 'Pets'),      /*Devil Shaz*/
('5000302', '10600', 'Pets'),      /*Devil Ose*/
('5000303', '6000', 'Pets'),      /*Devil Iros*/
('5000304', '8700', 'Pets'),      /*Devil Maz*/
/*Page 24*/
('5000305', '8700', 'Pets'),      /*Devil Fose*/
('5000306', '8400', 'Pets'),      /*Devil Imos*/
('5000307', '10600', 'Pets'),      /*Devil Gaz*/
('5000308', '6600', 'Pets'),      /*Devil Tose*/
('5000309', '9600', 'Pets'),      /*Mini Queen*/
('5000310', '10600', 'Pets'),      /*Von Bon*/
('5000311', '6000', 'Pets'),      /*Pierre*/
('5000312', '14400', 'Pets'),      /*Blue Dragon*/
('5000314', '9800', 'Pets'),      /*Sun Wu Kong*/
/*Page 25*/
('5000316', '11700', 'Pets'),      /*Sassy Snake*/
('5000317', '10600', 'Pets'),      /*Ice Knight*/
('5000318', '6000', 'Pets'),      /*Yeti Robot*/
('5000320', '6000', 'Pets'),      /*Pinkadillo*/
('5000321', '8700', 'Pets'),      /*Yellowdillo*/
('5000322', '9600', 'Pets'),      /*Greenadillo*/
('5000328', '8700', 'Pets'),      /*Von Soup*/
('5000329', '9800', 'Pets'),      /*Red Dragon*/
('5000330', '9800', 'Pets'),      /*Jr. Von Leon*/
/*Page 26*/
('5000331', '9800', 'Pets'),      /*Jr. Orchid*/
('5000332', '8400', 'Pets'),      /*Jr. Hilla*/
('5000337', '10600', 'Pets'),      /*PSY*/
('5000341', '9800', 'Pets'),      /*Punchyroo*/
('5000342', '11700', 'Pets'),      /*Unripe Nut*/
('5000343', '6000', 'Pets'),      /*Chestnut*/
('5000344', '8400', 'Pets'),      /*Burnt Nut*/
('5000345', '6600', 'Pets'),      /*Tiny Gollux*/
('5000352', '9800', 'Pets'),      /*White Candle*/
/*Page 27*/
('5000353', '14400', 'Pets'),      /*Blue Candle*/
('5000354', '6000', 'Pets'),      /*Grape Candle*/
('5000362', '6600', 'Pets'),      /*RED Rudolph*/
('5000363', '8700', 'Pets'),      /*RED Yeti*/
('5000364', '9800', 'Pets'),      /*RED Penguin*/
('5000365', '6600', 'Pets'),      /*Kiwi Puff*/
('5000366', '11700', 'Pets'),      /*Berry Puff*/
('5000367', '8400', 'Pets'),      /*Mango Puff*/
('5000368', '9800', 'Pets'),      /*Happy Bean*/
/*Page 28*/
('5000369', '8700', 'Pets'),      /*Li'l Lai*/
('5000370', '7500', 'Pets'),      /*Li'l Fort*/
('5000371', '10600', 'Pets'),      /*L'il Arby*/
('5000375', '6600', 'Pets'),      /*Pink Pengy*/
('5000376', '9800', 'Pets'),      /*Purple Pengy*/
('5000377', '6600', 'Pets'),      /*Blue Pengy*/
('5000381', '11700', 'Pets'),      /*Toto*/
('5000382', '8700', 'Pets'),      /*Frankie*/
('5000383', '10600', 'Pets'),      /*Lil Moonbeam*/
/*Page 29*/
('5000384', '6600', 'Pets'),      /*Petite Mario*/
('5000385', '7500', 'Pets'),      /*Abel*/
('5000386', '11700', 'Pets'),      /*Axel*/



/*Page 1*/
('5000387', '6600', 'Pets 2'),      /*Adel*/
('5000402', '14400', 'Pets 2'),      /*Ballet Lyn*/
('5000403', '9600', 'Pets 2'),      /*Soldier Hong*/
('5000404', '6600', 'Pets 2'),      /*Soldier Chun*/
('5000405', '6000', 'Pets 2'),      /*Green Chad*/
('5000406', '9800', 'Pets 2'),      /*Pink Mel*/
('5000407', '7500', 'Pets 2'),      /*Orange Leon*/
('5000408', '6000', 'Pets 2'),      /*Jr. Sierra*/
('5000409', '8700', 'Pets 2'),      /*Jr. Ryan*/
/*Page 2*/
('5000414', '10600', 'Pets 2'),      /*Lil' Bobble*/
('5000415', '14400', 'Pets 2'),      /*Lil' Lotus*/
('5000416', '6600', 'Pets 2'),      /*Ill Orchid*/
('5000417', '9600', 'Pets 2'),      /*Gelimer*/
('5000424', '9800', 'Pets 2'),      /*Sheep*/
('5000428', '9600', 'Pets 2'),      /*Holoyeti*/
('5000429', '6000', 'Pets 2'),      /*Pink Seal*/
('5000430', '14400', 'Pets 2'),      /*New Seal*/
('5000431', '6600', 'Pets 2'),      /*Newer Seal*/
/*Page 3*/
('5000432', '9600', 'Pets 2'),      /*Pinker Seal*/
('5000433', '14400', 'Pets 2'),      /*War Sheep*/
('5000434', '9600', 'Pets 2'),      /*Mage Sheep*/
('5000435', '9800', 'Pets 2'),      /*Cleric Sheep*/
('5000437', '6000', 'Pets 2'),      /*Orange*/
('5000442', '6600', 'Pets 2'),      /*Gelimer*/
('5000443', '9600', 'Pets 2'),      /*Furry Elwin*/
('5000444', '10600', 'Pets 2'),      /*Fluffy Lily*/
('5000445', '9800', 'Pets 2'),      /*Baby Nero*/
/*Page 4*/
('5000446', '6600', 'Pets 2'),      /*Strawbear*/
('5000447', '14400', 'Pets 2'),      /*Bananabear*/
('5000448', '7500', 'Pets 2'),      /*Cookiebear*/
('5000449', '9800', 'Pets 2'),      /*Gengerbready*/
('5000451', '8700', 'Pets 2'),      /*Gorilla Robo*/
('5000452', '10600', 'Pets 2'),      /*Squishy Bean*/
('5000456', '6000', 'Pets 2'),      /*Macha Man*/
('5000457', '7500', 'Pets 2'),      /*Lady Hot Tea*/
('5000458', '6000', 'Pets 2'),      /*Captain Cafe*/
/*Page 5*/
('5000459', '8700', 'Pets 2'),      /*Black Kitty*/
('5000460', '6000', 'Pets 2'),      /*Sailor Seal*/
('5000461', '10600', 'Pets 2'),      /*Admiral Seal*/
('5000462', '9800', 'Pets 2'),      /*Steward Seal*/
('5000463', '8700', 'Pets 2'),      /*Burnt Nut*/
('5000464', '8700', 'Pets 2'),      /*Gingerhead*/
('5000465', '6600', 'Pets 2'),      /*Orange*/
('5000466', '10600', 'Pets 2'),      /*Ducky*/
('5000468', '11700', 'Pets 2'),      /*Starwing*/
/*Page 6*/
('5000469', '6600', 'Pets 2'),      /*Tiny Nero*/
('5000470', '9600', 'Pets 2'),      /*Cheesy Cat*/
('5000471', '6600', 'Pets 2'),      /*Samson Cat*/
('5000473', '11700', 'Pets 2'),      /*Little Ursus*/
('5000474', '9600', 'Pets 2'),      /*Moist Cake*/
('5000475', '6000', 'Pets 2'),      /*Nutty Pie*/
('5000476', '11700', 'Pets 2'),      /*Sweet Candy*/
('5000479', '9800', 'Pets 2'),      /*Lil Zakum*/
('5000480', '11700', 'Pets 2'),      /*Ice Dragon*/
/*Page 7*/
('5000482', '7500', 'Pets 2'),      /*Galiel*/
('5000483', '8700', 'Pets 2'),      /*Mouse Monkey*/
('5000484', '10600', 'Pets 2'),      /*Lil Evan*/
('5000485', '11700', 'Pets 2'),      /*Lil Aran*/
('5000486', '14400', 'Pets 2'),      /*Lil Phantom*/
('5000490', '10600', 'Pets 2'),      /*Lil Luminous*/
('5000491', '9600', 'Pets 2'),      /*Lil Mercedes*/
('5000492', '14400', 'Pets 2'),      /*Lil Shade*/
('5000493', '11700', 'Pets 2'),      /*Persian Cat*/
/*Page 8*/
('5000494', '9600', 'Pets 2'),      /*Lil Damien*/
('5000495', '6000', 'Pets 2'),      /*Lil Alicia*/
('5000496', '9600', 'Pets 2'),      /*Lil Lilin*/
('5000497', '11700', 'Pets 2'),      /*Invidia*/
('5000498', '9800', 'Pets 2'),      /*Fennec Fox*/
('5000499', '7500', 'Pets 2'),      /*Stickman*/
('5000500', '9800', 'Pets 2'),      /*Turkey*/
('5000501', '9600', 'Pets 2'),      /*Pumpkin Jack*/
('5000502', '7500', 'Pets 2'),      /*Pumpkin O'*/
/*Page 9*/
('5000503', '7500', 'Pets 2'),      /*Pumpkin L*/
('5000505', '11700', 'Pets 2'),      /*Frankie*/
('5000507', '10600', 'Pets 2'),      /*Blue Husky*/
('5000508', '10600', 'Pets 2'),      /*Crys.Rudolph*/
('5000509', '14400', 'Pets 2'),      /*Snowman*/
('5000510', '10600', 'Pets 2'),      /*Fluffram*/
('5000511', '14400', 'Pets 2'),      /*Jr. Von Leon*/
('5000513', '7500', 'Pets 2'),      /*Jr. Hilla*/
('5000514', '6000', 'Pets 2'),      /*Macha Man*/
/*Page 10*/
('5000515', '9800', 'Pets 2'),      /*Lady Hot Tea*/
('5000516', '6600', 'Pets 2'),      /*Captain Cafe*/
('5000517', '8700', 'Pets 2'),      /*Hekaton*/
('5000518', '6600', 'Pets 2'),      /*Hekaton S*/
('5000519', '6000', 'Pets 2'),      /*Hekaton E*/
('5000520', '6600', 'Pets 2'),      /*Hekaton A*/
('5000521', '7500', 'Pets 2'),      /*Lil' Ninja*/
('5000522', '9600', 'Pets 2'),      /*Toucan*/
('5000524', '11700', 'Pets 2'),      /*Alpaca*/
/*Page 11*/
('5000525', '10600', 'Pets 2'),      /*Lil' Lotus*/
('5000526', '8400', 'Pets 2'),      /*Ill Orchid*/
('5000527', '9800', 'Pets 2'),      /*Gelimer*/
('5000528', '7500', 'Pets 2'),      /*Meerkat Mob*/
('5000529', '8400', 'Pets 2'),      /*Pudgycat*/
('5000530', '6600', 'Pets 2'),      /*War Sheep*/
('5000531', '7500', 'Pets 2'),      /*Mage Sheep*/
('5000532', '7500', 'Pets 2'),      /*Cleric Sheep*/
('5000533', '6000', 'Pets 2'),      /*Furry Elwin*/
/*Page 12*/
('5000534', '8400', 'Pets 2'),      /*Fluffy Lily*/
('5000535', '6600', 'Pets 2'),      /*Baby Nero*/
('5000536', '7500', 'Pets 2'),      /*Strawbear*/
('5000537', '11700', 'Pets 2'),      /*Bananabear*/
('5000538', '6600', 'Pets 2'),      /*Cookiebear*/
('5000545', '14400', 'Pets 2'),      /*Black Bean*/
('5000546', '8700', 'Pets 2'),      /*Skunk*/
('5000547', '11700', 'Pets 2'),      /*Porcupine*/
('5000548', '11700', 'Pets 2'),      /*Roo-A*/
/*Page 13*/
('5000549', '9800', 'Pets 2'),      /*Roo-B*/
('5000550', '11700', 'Pets 2'),      /*Roo-C*/
('5000551', '14400', 'Pets 2'),      /*Pink Dragon*/
('5000552', '8400', 'Pets 2'),      /*Ice Dragon*/
('5000553', '7500', 'Pets 2'),      /*Red Dragon*/
('5000554', '6600', 'Pets 2'),      /*Kiwi Puff*/
('5000555', '14400', 'Pets 2'),      /*Berry Puff*/
('5000556', '7500', 'Pets 2'),      /*Mango Puff*/
('5000557', '10600', 'Pets 2'),      /*Tiny Nero*/
/*Page 14*/
('5000558', '9800', 'Pets 2'),      /*Cheesy Cat*/
('5000559', '6000', 'Pets 2'),      /*Samson Cat*/
('5000561', '9600', 'Pets 2'),      /*Monkey*/
('5000563', '6600', 'Pets 2'),      /*Mouse Monkey*/
('5000568', '8400', 'Pets 2'),      /*Purple Cake*/
('5000569', '7500', 'Pets 2'),      /*Stjartmes*/
('5000570', '9800', 'Pets 2'),      /*Lil Tutu*/
('5000571', '7500', 'Pets 2'),      /*Lil Nene*/
('5000572', '6600', 'Pets 2'),      /*Lil Lingling*/
/*Page 15*/
('5000573', '9600', 'Pets 2'),      /*Lil Evan*/
('5000574', '6000', 'Pets 2'),      /*Lil Aran*/
('5000575', '6000', 'Pets 2'),      /*Lil Phantom*/
('5000576', '6600', 'Pets 2'),      /*Lil Luminous*/
('5000577', '10600', 'Pets 2'),      /*Lil Mercedes*/
('5000578', '6000', 'Pets 2'),      /*Lil Shade*/
('5000579', '9600', 'Pets 2'),      /*Gorilla Robo*/
('5000580', '14400', 'Pets 2'),      /*Lil Damien*/
('5000581', '10600', 'Pets 2'),      /*Lil Alicia*/
/*Page 16*/
('5000582', '10600', 'Pets 2'),      /*Lil Lilin*/
('5000585', '14400', 'Pets 2'),      /*Lil Tengu*/
('5000586', '7500', 'Pets 2'),      /*Beagle*/
('5000587', '7500', 'Pets 2'),      /*Salem Cat*/
('5000588', '8700', 'Pets 2'),      /*Binx Cat*/
('5000589', '14400', 'Pets 2'),      /*Kit Cat*/
('5000590', '8700', 'Pets 2'),      /*Bichon*/
('5000600', '7500', 'Pets 2'),      /*Ursie*/
('5000601', '7500', 'Pets 2'),      /*Punch Cat*/
/*Page 17*/
('5000602', '8400', 'Pets 2'),      /*Iron Rabbit*/
('5000604', '6000', 'Pets 2'),      /*Brown Kitty*/
('5000605', '8700', 'Pets 2'),      /*Lil Zakum*/
('5000606', '9600', 'Pets 2'),      /*Stjartmes*/
('5000607', '8400', 'Pets 2'),      /*Lil Tutu*/
('5000608', '6600', 'Pets 2'),      /*Lil Nene*/
('5000609', '8400', 'Pets 2'),      /*Lil Lingling*/
('5000610', '10600', 'Pets 2'),      /*Moist Cake*/
('5000611', '7500', 'Pets 2'),      /*Purple Cake*/
/*Page 18*/
('5000612', '6000', 'Pets 2'),      /*Lil Moonbeam*/
('5000613', '11700', 'Pets 2'),      /*Jr. Hilla*/
('5000614', '11700', 'Pets 2'),      /*Jr. Orchid*/
('5000615', '9600', 'Pets 2'),      /*Jr. Von Leon*/
('5000617', '8400', 'Pets 2'),      /*Stjartmes*/
('5000618', '9600', 'Pets 2'),      /*Stjartmes*/
('5000623', '8700', 'Pets 2'),      /*Esel*/
('5000626', '9600', 'Pets 2'),      /*Lil Damien*/
('5000627', '14400', 'Pets 2'),      /*Lil Alicia*/
/*Page 19*/
('5000628', '6000', 'Pets 2'),      /*Lil Lilin*/
('5000629', '14400', 'Pets 2'),      /*Merlion Pet*/
('5000630', '9800', 'Pets 2'),      /*Toucan*/
('5000631', '10600', 'Pets 2'),      /*Lil Tengu*/
('5000632', '14400', 'Pets 2'),      /*Beagle*/
('5000636', '6000', 'Pets 2'),      /*Salem Cat*/
('5000637', '6600', 'Pets 2'),      /*Binx Cat*/
('5000638', '6000', 'Pets 2'),      /*Kit Cat*/
('5000639', '6600', 'Pets 2'),      /*Bichon*/
/*Page 20*/
('5000644', '6000', 'Pets 2'),      /*Craw*/
('5000645', '8700', 'Pets 2'),      /*Adriano*/
('5000646', '9800', 'Pets 2'),      /*Bonkey*/
('5000647', '14400', 'Pets 2'),      /*Mage Sheep*/
('5000648', '11700', 'Pets 2'),      /*War Sheep*/
('5000649', '10600', 'Pets 2'),      /*Cleric Sheep*/
('5000650', '8400', 'Pets 2'),      /*Furry Elwin*/
('5000651', '9600', 'Pets 2'),      /*Fluffy Lily*/
('5000652', '9600', 'Pets 2'),      /*Baby Nero*/



/*Page 1*/
('1802000', '2700', 'Pet Appearance'),      /*Red Ribbon*/
('1802001', '7400', 'Pet Appearance'),      /*Yellow Hat*/
('1802002', '7400', 'Pet Appearance'),      /*Red Hat*/
('1802003', '3200', 'Pet Appearance'),      /*Black Hat*/
('1802004', '3700', 'Pet Appearance'),      /*Pink Laced Cap*/
('1802005', '5000', 'Pet Appearance'),      /*Sky Blue Lace Cap*/
('1802006', '4700', 'Pet Appearance'),      /*Blue Top Hat*/
('1802007', '4300', 'Pet Appearance'),      /*Red Top Hat*/
('1802008', '5600', 'Pet Appearance'),      /*Rudolph's Hat*/
/*Page 2*/
('1802009', '6300', 'Pet Appearance'),      /*Tree Hat*/
('1802010', '6300', 'Pet Appearance'),      /*Mushroom Suit*/
('1802011', '4700', 'Pet Appearance'),      /*Red Fur Coat*/
('1802012', '6800', 'Pet Appearance'),      /*Chestnut Cap*/
('1802013', '3200', 'Pet Appearance'),      /*Red Scarf*/
('1802014', '7400', 'Pet Appearance'),      /*Mini Kargo Wings*/
('1802015', '2700', 'Pet Appearance'),      /*Dino King & Queen*/
('1802016', '3700', 'Pet Appearance'),      /*Husky's Yellow Tights*/
('1802017', '5000', 'Pet Appearance'),      /*Monkey Sack*/
/*Page 3*/
('1802018', '7400', 'Pet Appearance'),      /*Panda's Clown Costume*/
('1802019', '4300', 'Pet Appearance'),      /*Rudolph's Sleigh*/
('1802020', '4300', 'Pet Appearance'),      /*White Tiger's Thief Suit*/
('1802021', '6800', 'Pet Appearance'),      /*Elephant Hat*/
('1802022', '6800', 'Pet Appearance'),      /*Aladin Vest*/
('1802023', '6300', 'Pet Appearance'),      /*Pelvis Hair*/
('1802024', '5600', 'Pet Appearance'),      /*White Tiger the Wizard*/
('1802025', '5600', 'Pet Appearance'),      /*Bunny Suit*/
('1802026', '3200', 'Pet Appearance'),      /*Prince Pepe*/
/*Page 4*/
('1802027', '6800', 'Pet Appearance'),      /*Husky's Bare Bones*/
('1802028', '2700', 'Pet Appearance'),      /*Dino Ghosty*/
('1802029', '5000', 'Pet Appearance'),      /*Panda's Pet-o-Lantern*/
('1802030', '5000', 'Pet Appearance'),      /*Penguin Earmuff Set*/
('1802031', '4700', 'Pet Appearance'),      /*Cowboy Kargo*/
('1802032', '4300', 'Pet Appearance'),      /*Snowboard Gear*/
('1802033', '2700', 'Pet Appearance'),      /*Crimson Mask*/
('1802034', '6800', 'Pet Appearance'),      /*White Angel*/
('1802035', '5600', 'Pet Appearance'),      /*Cute Beggar Overall*/
/*Page 5*/
('1802036', '6300', 'Pet Appearance'),      /*Golden Pig Fortune Pouch*/
('1802037', '5600', 'Pet Appearance'),      /*Husky's Oinker Suit*/
('1802038', '4300', 'Pet Appearance'),      /*Mini Celestial Wand*/
('1802039', '6800', 'Pet Appearance'),      /*Golden Pig Lucky Sack*/
('1802042', '6800', 'Pet Appearance'),      /*Baby Turkey Carriage*/
('1802044', '7400', 'Pet Appearance'),      /*Dragon's soul*/
('1802045', '7400', 'Pet Appearance'),      /*Jr. Reaper's Guitar */
('1802046', '7400', 'Pet Appearance'),      /*Rabbit Ears*/
('1802047', '5600', 'Pet Appearance'),      /*Porcupine Sunglasses*/
/*Page 6*/
('1802048', '7400', 'Pet Appearance'),      /*Dragon Armor*/
('1802049', '6800', 'Pet Appearance'),      /*Jr. Reaper's Sign (I'm with stoopid)*/
('1802050', '3700', 'Pet Appearance'),      /*Jr. Reaper's Sign (<--Noob)*/
('1802051', '4300', 'Pet Appearance'),      /*Jr. Reaper's Sign (cc plz)*/
('1802052', '4700', 'Pet Appearance'),      /*Jr. Reaper's Sign (I love pie)*/
('1802053', '2700', 'Pet Appearance'),      /*Snowman Gear*/
('1802054', '6300', 'Pet Appearance'),      /*Kino's Green Mushroom Hat*/
('1802055', '3200', 'Pet Appearance'),      /*Gas Mask*/
('1802059', '3200', 'Pet Appearance'),      /*Jail Bird Pet Costume*/
/*Page 7*/
('1802060', '6300', 'Pet Appearance'),      /*Crystal Rudolph's Wings*/
('1802061', '3700', 'Pet Appearance'),      /*Scuba Mask*/
('1802062', '4700', 'Pet Appearance'),      /*Starry Stereo Headset*/
('1802063', '3700', 'Pet Appearance'),      /*Baby Tiger Wings*/
('1802064', '4300', 'Pet Appearance'),      /*Alien's Pet*/
('1802065', '5600', 'Pet Appearance'),      /*Baby Tiger Wings*/
('1802066', '5000', 'Pet Appearance'),      /*Dragon Egg Shell*/
('1802067', '6800', 'Pet Appearance'),      /*Scuba Mask*/
('1802068', '2700', 'Pet Appearance'),      /*Gas Mask*/
/*Page 8*/
('1802070', '2700', 'Pet Appearance'),      /*Pilot's Cat*/
('1802071', '4700', 'Pet Appearance'),      /*Pink Oxygen Tank*/
('1802072', '4700', 'Pet Appearance'),      /*Caught Fish*/
('1802073', '6800', 'Pet Appearance'),      /*Blue Birdy*/
('1802077', '6800', 'Pet Appearance'),      /*Mango Creampuff Wing's*/
('1802078', '6300', 'Pet Appearance'),      /*Esel's Coronet*/
('1802079', '3200', 'Pet Appearance'),      /*B-Day Candle*/
('1802080', '6300', 'Pet Appearance'),      /*Tiel's Tiara*/
('1802081', '4300', 'Pet Appearance'),      /*Galiel's Angel Star*/
/*Page 9*/
('1802082', '6800', 'Pet Appearance'),      /*Pink Yeti's Blue BFF*/
('1802083', '5600', 'Pet Appearance'),      /*Silver Husky's Hip Glasses*/
('1802084', '2700', 'Pet Appearance'),      /*Dummbo's Hat*/
('1802085', '6800', 'Pet Appearance'),      /*Red Ribbon*/
('1802086', '3700', 'Pet Appearance'),      /*Mini Kargo Wings*/
('1802087', '6800', 'Pet Appearance'),      /*Blue Top Hat*/
('1802088', '5000', 'Pet Appearance'),      /*Red Top Hat*/
('1802089', '3700', 'Pet Appearance'),      /*Yellow Hat*/
('1802090', '5000', 'Pet Appearance'),      /*Red Hat*/
/*Page 10*/
('1802091', '4700', 'Pet Appearance'),      /*Black Hat*/
('1802092', '4300', 'Pet Appearance'),      /*Pink Laced Cap*/
('1802093', '6300', 'Pet Appearance'),      /*Sky Blue Laced Cap*/
('1802094', '5000', 'Pet Appearance'),      /*Red Scarf*/
('1802095', '3200', 'Pet Appearance'),      /*Mushroom Suit*/
('1802096', '5600', 'Pet Appearance'),      /*Husky Yellow Tights*/
('1802097', '7400', 'Pet Appearance'),      /*Dino King & Queen*/
('1802098', '7400', 'Pet Appearance'),      /*Bunny Suit*/
('1802099', '6300', 'Pet Appearance'),      /*Monkey Sack*/
/*Page 11*/
('1802100', '4700', 'Pet Appearance'),      /*Pet Collar*/
('1802101', '4700', 'Pet Appearance'),      /*Pet Label Ring*/
('1802300', '4700', 'Pet Appearance'),      /*Bare Bones*/
('1802301', '3200', 'Pet Appearance'),      /*Ghosty*/
('1802302', '4700', 'Pet Appearance'),      /*Pet-o-Lantern*/
('1802303', '5600', 'Pet Appearance'),      /*Clown Dress*/
('1802304', '5600', 'Pet Appearance'),      /*Penguin Earmuff Set*/
('1802305', '4300', 'Pet Appearance'),      /*White Tiger Suit*/
('1802306', '3200', 'Pet Appearance'),      /*Oinker Suit*/
/*Page 12*/
('1802307', '3200', 'Pet Appearance'),      /*Pelvis Hair*/
('1802308', '6800', 'Pet Appearance'),      /*Prince Pepe*/
('1802309', '6300', 'Pet Appearance'),      /*Crimson Mask*/
('1802310', '7400', 'Pet Appearance'),      /*Cowboy Kargo*/
('1802311', '7400', 'Pet Appearance'),      /*White Angel*/
('1802312', '5600', 'Pet Appearance'),      /*Guitar */
('1802313', '4700', 'Pet Appearance'),      /*Cute Beggar Overall*/
('1802314', '7400', 'Pet Appearance'),      /*Baby Turkey Carriage*/
('1802315', '3200', 'Pet Appearance'),      /*Dragon Armor*/
/*Page 13*/
('1802316', '6800', 'Pet Appearance'),      /*Porcupine Sunglasses*/
('1802317', '3200', 'Pet Appearance'),      /*Jr. Reaper Sign (I'm with stoopid)*/
('1802318', '2700', 'Pet Appearance'),      /*Jr. Reaper Sign (cc plz)*/
('1802319', '4300', 'Pet Appearance'),      /*Snowman Gear*/
('1802320', '6300', 'Pet Appearance'),      /*Jr. Reaper Sign (<--Noob)*/
('1802321', '3200', 'Pet Appearance'),      /*Jr. Reaper Sign (I love pie)*/
('1802322', '4300', 'Pet Appearance'),      /*Chestnut Cap*/
('1802323', '4700', 'Pet Appearance'),      /*Gas Mask*/
('1802324', '5000', 'Pet Appearance'),      /*Jail Bird Pet Costume*/
/*Page 14*/
('1802325', '5000', 'Pet Appearance'),      /*Scuba Mask*/
('1802326', '3200', 'Pet Appearance'),      /*Kino's Green Mushroom Hat*/
('1802327', '6800', 'Pet Appearance'),      /*Starry Stereo Headset*/
('1802328', '2700', 'Pet Appearance'),      /*Baby Tiger Wings*/
('1802329', '6300', 'Pet Appearance'),      /*Alien's Pet*/
('1802330', '6800', 'Pet Appearance'),      /*Dragon Egg Shell*/
('1802331', '2700', 'Pet Appearance'),      /*Rabbit Ears*/
('1802332', '5600', 'Pet Appearance'),      /*Pink Oxygen Tank*/
('1802333', '3200', 'Pet Appearance'),      /*B-Day Candle*/
/*Page 15*/
('1802334', '4700', 'Pet Appearance'),      /*Fish*/
('1802335', '4300', 'Pet Appearance'),      /*Blue Birdy*/
('1802336', '5000', 'Pet Appearance'),      /*Mini Celestial Wand*/
('1802337', '5600', 'Pet Appearance'),      /*Tube*/
('1802338', '4300', 'Pet Appearance'),      /*Pink Bean's Headset*/
('1802339', '3700', 'Pet Appearance'),      /*Blue Birdy*/
('1802340', '5000', 'Pet Appearance'),      /*Craw's Pirate Hat*/
('1802341', '2700', 'Pet Appearance'),      /*Adriano's Hat*/
('1802342', '6800', 'Pet Appearance'),      /*Bonkey's Ammunition Box*/
/*Page 16*/
('1802343', '6800', 'Pet Appearance'),      /*Starry Muffler*/
('1802344', '5600', 'Pet Appearance'),      /*Parrot Admiral Hat*/
('1802345', '4300', 'Pet Appearance'),      /*Penguin Earmuff Set*/
('1802346', '5600', 'Pet Appearance'),      /*Ghost of Fear*/
('1802347', '6800', 'Pet Appearance'),      /*Ghost of Death*/
('1802348', '6800', 'Pet Appearance'),      /*Ghost of Jealousy*/
('1802349', '5600', 'Pet Appearance'),      /*Dragon Orb*/
('1802350', '2700', 'Pet Appearance'),      /*Caught Fish*/
('1802351', '5000', 'Pet Appearance'),      /*Bean's Headset*/
/*Page 17*/
('1802352', '4700', 'Pet Appearance'),      /*Bandit Goggles*/
('1802353', '6800', 'Pet Appearance'),      /*Sanchito's Carrot*/
('1802354', '2700', 'Pet Appearance'),      /*Black-hearted Earrings*/
('1802365', '5000', 'Pet Appearance'),      /*Harp Seal Hat*/
('1802366', '4700', 'Pet Appearance'),      /*Puffram's Golden Horn*/
('1802367', '2700', 'Pet Appearance'),      /*Gingerbready Bow Tie*/
('1802368', '5600', 'Pet Appearance'),      /*Frost Mallet*/
('1802369', '5600', 'Pet Appearance'),      /*Tiny Fright*/
('1802370', '5600', 'Pet Appearance'),      /*Tiny Sadness*/
/*Page 18*/
('1802371', '6800', 'Pet Appearance'),      /*Tiny Envy*/
('1802372', '3200', 'Pet Appearance'),      /*Sunglass*/
('1802373', '6300', 'Pet Appearance'),      /*Rose*/
('1802375', '4300', 'Pet Appearance'),      /*Starwing's Star Trail*/
('1802378', '3200', 'Pet Appearance'),      /*Shark's Mini Tube*/
('1802380', '4700', 'Pet Appearance'),      /*Blue Light Ring*/
('1802381', '5600', 'Pet Appearance'),      /*Golden Light Ring*/
('1802382', '7400', 'Pet Appearance'),      /*Purple Light Ring*/
('1802384', '6300', 'Pet Appearance'),      /*Fluffy Teddy's Bunny Ears*/
/*Page 19*/
('1802385', '3200', 'Pet Appearance'),      /*Cutie Teddy's Baby Bonnet*/
('1802386', '6300', 'Pet Appearance'),      /*Puffy Teddy's Crown*/
('1802387', '6300', 'Pet Appearance'),      /*Red Elly's Dress Hat*/
('1802388', '6800', 'Pet Appearance'),      /*Blue Burro's Toy Carrot*/
('1802389', '3700', 'Pet Appearance'),      /*Pumpkin Jack's Magic Lantern*/
('1802390', '2700', 'Pet Appearance'),      /*Pumpkin Zack's Magic Lantern*/
('1802391', '7400', 'Pet Appearance'),      /*Pumpkin Mack's Magic Lantern*/
('1802392', '3200', 'Pet Appearance'),      /*Boxing Gloves*/
('1802394', '5600', 'Pet Appearance'),      /*Baby Frumpy Koala*/
/*Page 20*/
('1802395', '3700', 'Pet Appearance'),      /*Baby Grumpy Koala*/
('1802396', '2700', 'Pet Appearance'),      /*Baby Nerdy Koala*/
('1802418', '4700', 'Pet Appearance'),      /*Chippermunk's Acorn */
('1802419', '4700', 'Pet Appearance'),      /*Chipmunch's Acorn*/
('1802420', '2700', 'Pet Appearance'),      /*Chubmunk's Acorn*/
('1802424', '5000', 'Pet Appearance'),      /*Honey Halo*/
('1802425', '5600', 'Pet Appearance'),      /*Lime Halo*/
('1802426', '6300', 'Pet Appearance'),      /*Peach Halo*/
('1802427', '4700', 'Pet Appearance'),      /*Roo-A Baby Bonnet*/
/*Page 21*/
('1802428', '7400', 'Pet Appearance'),      /*Roo-B Baby Bonnet*/
('1802429', '4700', 'Pet Appearance'),      /*Roo-C Baby Bonnet*/
('1802430', '5000', 'Pet Appearance'),      /*Yellow Devil's Collar*/
('1802431', '4700', 'Pet Appearance'),      /*Red Devil's Collar*/
('1802432', '4300', 'Pet Appearance'),      /*Blue Devil's Collar*/
('1802433', '4300', 'Pet Appearance'),      /*Blazing Horns*/
('1802434', '3200', 'Pet Appearance'),      /*Chilling Horns*/
('1802435', '4300', 'Pet Appearance'),      /*Miasmic Horns*/
('1802436', '2700', 'Pet Appearance'),      /*Gingerbread Bow Tie*/
/*Page 22*/
('1802444', '7400', 'Pet Appearance'),      /*Alluring Mirror*/
('1802445', '4700', 'Pet Appearance'),      /*Von Bon's Staff*/
('1802446', '3200', 'Pet Appearance'),      /*Pierre's Umbrella*/
('1802447', '3700', 'Pet Appearance'),      /*Snake's Pink Bow*/
('1802448', '3700', 'Pet Appearance'),      /*Ice Stick*/
('1802449', '7400', 'Pet Appearance'),      /*Yeti Robot Antenna*/
('1802450', '6800', 'Pet Appearance'),      /*Pinkadillo Star Ball*/
('1802451', '5600', 'Pet Appearance'),      /*Yellowdillow Circus Ball*/
('1802452', '3200', 'Pet Appearance'),      /*Greenadillo Soccer Ball*/
/*Page 23*/
('1802458', '2700', 'Pet Appearance'),      /*Hot Pot Von Bon's Staff*/
('1802459', '5600', 'Pet Appearance'),      /*Ifia's Rose*/
('1802460', '7400', 'Pet Appearance'),      /*Orchid's Hat*/
('1802461', '6800', 'Pet Appearance'),      /*Hilla's Blackheart*/
('1802462', '5000', 'Pet Appearance'),      /*Gentleman Bow Tie*/
('1802463', '5000', 'Pet Appearance'),      /*Kangaroo Boxing Gloves*/
('1802464', '5600', 'Pet Appearance'),      /*Unripe Chestnut Leaf*/
('1802465', '6800', 'Pet Appearance'),      /*Chestnut Leaf*/
('1802466', '5000', 'Pet Appearance'),      /*Burnt Chestnut Leaf*/
/*Page 24*/
('1802467', '6800', 'Pet Appearance'),      /*Gollux's Halo*/
('1802471', '2700', 'Pet Appearance'),      /*Purple Kid Pumpkin*/
('1802472', '2700', 'Pet Appearance'),      /*Green Kid Pumpkin*/
('1802473', '4700', 'Pet Appearance'),      /*Black Kid Pumpkin*/
('1802474', '4300', 'Pet Appearance'),      /*Little RED Admin*/
('1802475', '6300', 'Pet Appearance'),      /*Kiwi Puff Wings*/
('1802476', '2700', 'Pet Appearance'),      /*Berry Puff Wings*/
('1802477', '4700', 'Pet Appearance'),      /*Mango Puff Wings*/
('1802478', '6800', 'Pet Appearance'),      /*Happy Bean's Hat*/
/*Page 25*/
('1802479', '6800', 'Pet Appearance'),      /*Li'l Lai's Necklace*/
('1802480', '6800', 'Pet Appearance'),      /*Li'l Fort's Scarf*/
('1802481', '6800', 'Pet Appearance'),      /*Li'l Arby's Bell*/
('1802482', '6800', 'Pet Appearance'),      /*Pink Pengy Hat*/
('1802483', '5000', 'Pet Appearance'),      /*Purple Pengy Hat*/
('1802484', '4300', 'Pet Appearance'),      /*Blue Pengy Hat*/
('1802488', '4700', 'Pet Appearance'),      /*Cloud Bag*/
('1802489', '7400', 'Pet Appearance'),      /*Frankie's Halo*/
('1802490', '6800', 'Pet Appearance'),      /*Devil Bat*/
/*Page 26*/
('1802491', '6800', 'Pet Appearance'),      /*Lil Moonbeam's Hairband*/
('1802492', '7400', 'Pet Appearance'),      /*Helium Filled Dreams*/
('1802493', '3700', 'Pet Appearance'),      /*Cute Rabbit Hat*/
('1802497', '4300', 'Pet Appearance'),      /*Moon Miho*/
('1802500', '6300', 'Pet Appearance'),      /*Lyn's Tiara*/
('1802501', '4300', 'Pet Appearance'),      /*Hong's Heart*/
('1802502', '4300', 'Pet Appearance'),      /*Chun's Ambition*/
('1802503', '7400', 'Pet Appearance'),      /*Chameleon's Rainbow*/
('1802504', '2700', 'Pet Appearance'),      /*Orange Electronic Display*/
/*Page 27*/
('1802505', '5000', 'Pet Appearance'),      /*Purple Electronic Display*/
('1802509', '3700', 'Pet Appearance'),      /*Lil' Bobble Hat*/
('1802510', '4300', 'Pet Appearance'),      /*Lotus's Aura*/
('1802511', '6800', 'Pet Appearance'),      /*Orchid's Tiny IV*/
('1802512', '6300', 'Pet Appearance'),      /*Gelimer's Teddy*/
('1802519', '4700', 'Pet Appearance'),      /*Fluffram Ribbon (Pet Equip)*/
('1802520', '2700', 'Pet Appearance'),      /*Matcha Man's Leaf*/
('1802521', '5600', 'Pet Appearance'),      /*Lady Hot Tea's Spoon*/
('1802522', '4300', 'Pet Appearance'),      /*Captain Cafe's Whipped Cream*/
/*Page 28*/
('1802524', '6300', 'Pet Appearance'),      /*New Pink Harp Seal Hat*/
('1802526', '4300', 'Pet Appearance'),      /*Warrior Sheep Sword*/
('1802527', '3200', 'Pet Appearance'),      /*Mage Sheep Cane*/
('1802528', '4300', 'Pet Appearance'),      /*Cleric Sheep Staff*/
('1802529', '2700', 'Pet Appearance'),      /*Orange Leaf*/
('1802530', '4300', 'Pet Appearance'),      /*Furry Elwin's Necklace*/
('1802531', '7400', 'Pet Appearance'),      /*Fluffy Lily's Ribbon*/
('1802532', '6300', 'Pet Appearance'),      /*Baby Nero's Ball of Yarn*/
('1802534', '6300', 'Pet Appearance'),      /*Strawbear Fork*/
/*Page 29*/
('1802535', '7400', 'Pet Appearance'),      /*Bananabear Fork*/
('1802536', '6300', 'Pet Appearance'),      /*Cookiebear Fork*/
('1802537', '7400', 'Pet Appearance'),      /*Fancy Fox Mask*/



/*Page 1*/
('1802538', '5000', 'Pet Appearance 2'),      /*Fox Mask*/
('1802539', '6300', 'Pet Appearance 2'),      /*Sailor Seal Star Glasses*/
('1802540', '7400', 'Pet Appearance 2'),      /*Admiral Seal Star Glasses*/
('1802541', '5600', 'Pet Appearance 2'),      /*Steward Seal Star Glass*/
('1802542', '7400', 'Pet Appearance 2'),      /*Ducky's Suave Ribbon*/
('1802543', '6800', 'Pet Appearance 2'),      /*Tiny Nero's Transformation Set*/
('1802544', '3700', 'Pet Appearance 2'),      /*Cheesy Cat's Purple Yarn*/
('1802545', '3700', 'Pet Appearance 2'),      /*Samson Cat's Emerald Yarn*/
('1802546', '7400', 'Pet Appearance 2'),      /*Meerkat Instrument*/
/*Page 2*/
('1802547', '5600', 'Pet Appearance 2'),      /*Pudgycat Fancytie*/
('1802548', '5600', 'Pet Appearance 2'),      /*Cake Temptation*/
('1802549', '4700', 'Pet Appearance 2'),      /*Pie Temptation*/
('1802550', '6300', 'Pet Appearance 2'),      /*Candy Temptation*/
('1802551', '6800', 'Pet Appearance 2'),      /*Lil Zakum's Black Sunglasses*/
('1802552', '3200', 'Pet Appearance 2'),      /*Mousy Overalls*/
('1802553', '7400', 'Pet Appearance 2'),      /*Evan's Halo*/
('1802554', '3200', 'Pet Appearance 2'),      /*Aran's Halo*/
('1802555', '6300', 'Pet Appearance 2'),      /*Phantom's Halo*/
/*Page 3*/
('1802556', '4300', 'Pet Appearance 2'),      /*Luminous's Halo*/
('1802557', '4700', 'Pet Appearance 2'),      /*Mercedes's Halo*/
('1802558', '6300', 'Pet Appearance 2'),      /*Shade's Halo*/
('1802559', '6800', 'Pet Appearance 2'),      /*Damien's Halo*/
('1802560', '2700', 'Pet Appearance 2'),      /*Alicia's Halo*/
('1802561', '5000', 'Pet Appearance 2'),      /*Lilin's Halo*/
('1802562', '2700', 'Pet Appearance 2'),      /*Ursie's Ribbon*/
('1802563', '4300', 'Pet Appearance 2'),      /*Gym Cat Dumbbell*/
('1802564', '6300', 'Pet Appearance 2'),      /*Iron Rabbit Engine*/
/*Page 4*/
('1802565', '4700', 'Pet Appearance 2'),      /*Cloud's Lollipop Ribbon*/
('1802566', '5600', 'Pet Appearance 2'),      /*Moss's Lollipop Ribbon*/
('1802567', '6300', 'Pet Appearance 2'),      /*Pinkie's Lollipop Ribbon*/
('1802568', '6300', 'Pet Appearance 2'),      /*Mini Stjartmes*/
('1802569', '5600', 'Pet Appearance 2'),      /*Lingling's Bell*/
('1802570', '4700', 'Pet Appearance 2'),      /*Nene's Flower*/
('1802571', '5600', 'Pet Appearance 2'),      /*TuTu's Umbrella*/
('1802572', '2700', 'Pet Appearance 2'),      /*Blue Ribbon*/
('1802573', '7400', 'Pet Appearance 2'),      /*Pink Ribbon*/
/*Page 5*/
('1802574', '5600', 'Pet Appearance 2'),      /*Purple Ribbon*/
('1802575', '4700', 'Pet Appearance 2'),      /*Baby Chickie*/
('1802576', '5600', 'Pet Appearance 2'),      /*Anguish Crow*/
('1802577', '6800', 'Pet Appearance 2'),      /*Fallen Angel Headband*/
('1802578', '3700', 'Pet Appearance 2'),      /*Fondue's Ribbon Collar*/
('1802579', '6800', 'Pet Appearance 2'),      /*Sasha's Ribbon Collar*/
('1802580', '3700', 'Pet Appearance 2'),      /*Coco's Ribbon Collar*/
('1802581', '6800', 'Pet Appearance 2'),      /*Witch's Red Ribbon*/
('1802582', '6800', 'Pet Appearance 2'),      /*Witch's Purple Ribbon*/
/*Page 6*/
('1802583', '3700', 'Pet Appearance 2'),      /*Witch's Pink Ribbon*/
('1802584', '7400', 'Pet Appearance 2'),      /*Red Bow Tie*/



/*Page 1*/
('5380000', '2700', 'Pet Use'),      /*The Rock of Evolution*/
('5170000', '2700', 'Pet Use'),      /*Pet Name Tag*/
('5180000', '3700', 'Pet Use'),      /*Water of Life*/
('5689000', '4100', 'Pet Use'),      /*Premium Water of Life*/
('5460000', '3300', 'Pet Use'),      /*Pet Snack*/
('5781000', '5700', 'Pet Use'),      /*Bean Dye Coupon*/
('5781001', '5700', 'Pet Use'),      /*Pink Bean Dye Coupon*/
('5781002', '5700', 'Pet Use'),      /*Demon Pet Dye Coupon*/
('5781004', '5700', 'Pet Use'),      /*Roo-bot Paint Coupon*/
/*Page 2*/
('5781006', '5700', 'Pet Use'),      /*Dillo Dye Coupon*/
('5781007', '5700', 'Pet Use'),      /*Chestnut Dye Coupon*/
('5781008', '5700', 'Pet Use'),      /*Candle Pet Dye Coupon*/
('5781009', '5700', 'Pet Use'),      /*Creampuff Pet Dye Coupon*/
('5781010', '5700', 'Pet Use'),      /*Pengy Pet Dye Coupon*/
('5781011', '5700', 'Pet Use'),      /*Chihuahua Dye Coupon*/
('5781013', '5700', 'Pet Use'),      /*Chameleon Pet Dye Coupon*/
('5781014', '5700', 'Pet Use'),      /*Chubmunk Pet Dye Coupon*/



/*Page 1*/
('5249000', '1000', 'Pet Food');      /*Premium Pet Food*/
insert into `cs_items` (`itemID`, `newPrice`, `category`, `bundleQuantity`) values ('5249000', '5400', 'Pet Food', '6');      /*Premium Pet Food (6)*/



/*Page 1*/
insert into `cs_items` (`itemID`, `newPrice`, `category`) values ('5190000', '6800', 'Pet Skills'),      /*Item Pick-up Skill*/
('5190001', '5000', 'Pet Skills'),      /*Auto HP Potion Skill*/
('5190002', '5600', 'Pet Skills'),      /*Expanded Auto Move Skill*/
('5190003', '7400', 'Pet Skills'),      /*Auto Move Skill*/
('5190004', '3200', 'Pet Skills'),      /*Expired Pickup Skill*/
('5190005', '4700', 'Pet Skills'),      /*Ignore Item Skill */
('5190006', '4300', 'Pet Skills'),      /*Auto MP Potion Skill*/
('5190009', '6800', 'Pet Skills'),      /*Auto All Cure Skill*/
('5190010', '3200', 'Pet Skills'),      /*Auto Buff Skill*/
/*Page 2*/
('5190011', '7400', 'Pet Skills'),      /*Auto Feed and Movement Skill*/
('5190012', '7400', 'Pet Skills'),      /*Fatten Up Skill*/





/*		MESSENGER AND SOCIAL		*/

/*Page 1*/
('5120000', '4300', 'Weather Effects'),      /*Snowy Snow*/
('5120001', '6800', 'Weather Effects'),      /*Sprinkled Flowers*/
('5120002', '4700', 'Weather Effects'),      /*Soap Bubbles*/
('5120003', '3700', 'Weather Effects'),      /*Snowflakes*/
('5120004', '7400', 'Weather Effects'),      /*Sprinkled Presents*/
('5120005', '3700', 'Weather Effects'),      /*Sprinkled Chocolate*/
('5120006', '3700', 'Weather Effects'),      /*Sprinkled Flower Petals*/
('5120007', '5000', 'Weather Effects'),      /*Sprinkled Candy*/
('5120008', '2700', 'Weather Effects'),      /*Sprinkled Maple Leaves*/
/*Page 2*/
('5120009', '4700', 'Weather Effects'),      /*Fireworks*/
('5120010', '3700', 'Weather Effects'),      /*Sprinkled Coke*/
('5120011', '4700', 'Weather Effects'),      /*Spirit Haunt*/
('5120012', '4700', 'Weather Effects'),      /*Holiday Sock*/
('5120014', '3200', 'Weather Effects'),      /*Christmas Socks*/
('5120015', '6800', 'Weather Effects');      /*Chinese Lantern Firecrackers*/


