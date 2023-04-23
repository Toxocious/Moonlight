drop table if exists monster_collection_session_rewards;
drop table if exists monster_collection_group_rewards;
drop table if exists monster_collection;

create table monster_collection_session_rewards (
	region int,
    session int,
	rewardid int,
    quantity int,
    primary key (region, session)
);

create table monster_collection_group_rewards (
	region int,
    session int,
    groupid int,
	rewardid int,
    quantity int,
    primary key (region, session, groupid)
);

create table monster_collection (
	id int not null auto_increment,
    mobid int,
    region int,
    session int,
    position int,
    primary key (id)
);

# (monsterID, region, session, position)
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 0, 2028048, 1);
# /**
# * Victoria Island 1 - Reward: 2028048
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100004, 0, 0, 0), # OrangeMushroom
(2600208, 0, 0, 1), # Mushmom
(2600204, 0, 0, 2), # BlueMushroom
(2600205, 0, 0, 3), # CryingBlueMushroom
(9305103, 0, 0, 4); # BlueMushmom
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600200, 0, 0, 5), # GreenMushroom
(2600203, 0, 0, 6), # HornyMushroom
(2600206, 0, 0, 7), # ZombieMushroom
(2600207, 0, 0, 8), # AnnoyedZombieMushroom
(9305104, 0, 0, 9); # ZombieMushmom
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1210101, 0, 0, 10), # RibbonPig
(1210104, 0, 0, 11), # BlueRibbionPig
(1210111, 0, 0, 12), # StrangePig
(2230102, 0, 0, 13), # WildBoar
(0, 0, 0, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4090000, 0, 0, 15), # IronHog
(4230400, 0, 0, 16), # IronBoar
(3210100, 0, 0, 17), # FireBoar
(9300655, 0, 0, 18), # PortlyPig
(9300680, 0, 0, 19); # MrChomps
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 0, 4, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3000001, 0, 0, 20), # Fiary
(3000007, 0, 0, 21), # RoyalFairy
(210100, 0, 0, 22), # Slime
(1210103, 0, 0, 23), # Bubbling
(0, 0, 0, 24); # Empty
# Reward ID: 2434929
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 1, 2433928, 1);
# /**
# * Victoria Island 2 - Reward: 2433928
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 0, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230100, 0, 1, 0), # EvilEye
(3230100, 0, 1, 1), # CurseEye
(4230100, 0, 1, 2), # ColdEye
(2230113, 0, 1, 3), # SurgeonEye
(0, 0, 1, 4); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (130100, 0, 1, 5), # Stump
(1110101, 0, 1, 6), # DarkStump
(1130100, 0, 1, 7), # AxeStump
(2130100, 0, 1, 8), # DarkAxeStump
(0, 0, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1140100, 0, 1, 10), # GhostStump
(1140130, 0, 1, 11), # SmirkingGhostStump
(3220000, 0, 1, 12), # Stumpy
(2230110, 0, 1, 13), # WoodenMask
(2230111, 0, 1, 14); # RockyMask
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 3, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501000, 0, 1, 15), # FireflySlime
(3501001, 0, 1, 16), # FairySlime
(3501009, 0, 1, 17), # MysticWisp
(3501002, 0, 1, 18), # WaterSprite
(3501003, 0, 1, 19); # ForestSprite
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 1, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600209, 0, 1, 20), # StoneGolem
(2600210, 0, 1, 21), # DarkStoneGolem
(2600212, 0, 1, 22), # IcyMixedGolem
(2600213, 0, 1, 23), # FlamingMixedGolem
(9500150, 0, 1, 24); # IceGolem
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 2, 3017000, 1);
# /**
# * Victoria Island 3 - Reward: 3017000
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 0, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3501004, 0, 2, 0), # GrumpyTome
(3501005, 0, 2, 1), # RagingTome
(3501006, 0, 2, 2), # Oniony
(3501007, 0, 2, 3), # Turnipy
(3501008, 0, 2, 4); # MoleKing
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2300100, 0, 2, 5), # Stirge
(3230101, 0, 2, 6), # JrWraith
(4230102, 0, 2, 7), # Wraith
(5090000, 0, 2, 8), # Shade
(0, 0, 2, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401000, 0, 2, 10), # PalmTreeSlime
(3401001, 0, 2, 11), # CoconutSlime
(3401002, 0, 2, 12), # EmeraldClamSlime
(3401003, 0, 2, 13), # VioletClamSlime
(0, 0, 2, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 3, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401004, 0, 2, 15), # SeagullSlime
(3401005, 0, 2, 16), # RedTubeSlime
(3401006, 0, 2, 17), # BlueTubeSlime
(3401007, 0, 2, 18), # ShrimpSlime
(3401008, 0, 2, 19); # FlyingFishSlime
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 2, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3401011, 0, 2, 20), # CaptainDarkgoo
(3401009, 0, 2, 21), # StarfishOctopusSlime
(3401010, 0, 2, 22), # SeashellOctopusSlime
(0, 0, 2, 23), # Empty
(0, 0, 2, 24); # Empty
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 3, 2435509, 1);

# /**
# * Victoria Island 4 - Reward: 2435509
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 0, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2130103, 0, 3, 0), # JrNecki
(3110100, 0, 3, 1), # Ligator
(5130103, 0, 3, 2), # Croco
(2230115, 0, 3, 3), # MuddySproutMonster
(6220000, 0, 3, 4); # Dyle
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230125, 0, 3, 5), # Skeledog
(5150001, 0, 3, 6), # SkeletonSolider
(6230602, 0, 3, 7), # OfficerSkeleton
(7130103, 0, 3, 8), # CommanderSkeleton
(9300471, 0, 3, 9); # LordSkeleton
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 2, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300100, 0, 3, 10), # MushroomChandelier
(3300101, 0, 3, 11), # MushroomKnightArmor
(3300102, 0, 3, 12), # ExhaustedViking
(3300103, 0, 3, 13), # TiredViking
(0, 0, 3, 14); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300710, 0, 3, 15), # PrimeMinister
(3300104, 0, 3, 16), # WarmViking
(3300105, 0, 3, 17), # GenerousViking
(3300106, 0, 3, 18), # SolemnViking
(0, 0, 3, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 3, 4, 2434959, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3300110, 0, 3, 20), # BlackViking
(3300107, 0, 3, 21), # SeriousViking
(3300108, 0, 3, 22), # MasterSquid
(3300109, 0, 3, 23), # VikingSquad
(0, 0, 3, 24); # Empty
# Reward ID: 2434959
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (0, 4, 2350000, 1);
# /**
# * Victoria - 5 Sleepywood 1 - Reward: 2350000
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4130100, 0, 4, 0), # CopperDrake
(5130100, 0, 4, 1), # Drake
(6130100, 0, 4, 2), # RedDrake
(6230600, 0, 4, 3), # IceDrake
(6230601, 0, 4, 4); # DarkDrake
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6230100, 0, 4, 5), # WildKargo
(7130100, 0, 4, 6), # Tauromacis
(7130101, 0, 4, 7), # Taurospear
(8130100, 0, 4, 8), # JrBalrog
(8830000, 0, 4, 9); # Balrog
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (7120110, 0, 4, 10), # BlazingImp
(7120111, 0, 4, 11), # PointyImp
(7120112, 0, 4, 12), # EliteBlazingImp
(7120113, 0, 4, 13), # ElitePointyImp
(0, 0, 4, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8900100, 0, 4, 15), # Pierre
(8910100, 0, 4, 16), # VonBon
(8920100, 0, 4, 17), # CrimsonQueen
(8930100, 0, 4, 18), # Vellum
(0, 0, 4, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (0, 4, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8900000, 0, 4, 20), # ChaosPierre
(8910000, 0, 4, 21), # ChaosVonBon
(8920000, 0, 4, 22), # ChaosCrimsonQueen
(8930000, 0, 4, 23), # ChaosVellum
(0, 0, 4, 24); # Empty
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (1, 0, 3017001, 1);

# /**
# * Near Victoria 1 - Reward: 3017001
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100120, 1, 0, 0), # Tino
(100121, 1, 0, 1), # Tiv
(100122, 1, 0, 2), # Timu
(100123, 1, 0, 3), # Tiru
(100124, 1, 0, 4); # Tiguru
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (100130, 1, 0, 5), # Muru
(100131, 1, 0, 6), # Murupa
(100132, 1, 0, 7), # Murupia
(100133, 1, 0, 8), # Murumuru
(100134, 1, 0, 9); # Murukun
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 2, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2220000, 1, 0, 10), # Mano
(100100, 1, 0, 11), # Snail
(100101, 1, 0, 12), # BlueSnail
(100002, 1, 0, 13), # RedSnail
(120100, 1, 0, 14); # Shroom
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 3, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502000, 1, 0, 15), # AmmoniteGrumpil
(3502001, 1, 0, 16), # FishGrumpil
(3502002, 1, 0, 17), # CorrupterBarrels
(3502003, 1, 0, 18), # PolluterBarrel
(0, 1, 0, 19); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (1, 0, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3502004, 1, 0, 20), # PossiblyEvilSeal
(3502005, 1, 0, 21), # PossiblyEvilWalrus
(3502006, 1, 0, 22), # WarmerBot
(3502007, 1, 0, 23), # ShaverBot
(0, 1, 0, 24); # Empty
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (2, 0, 3017002, 0);

# /**
# * Edelstein - Reward: 3017002
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (150000, 2, 0, 0), # PottedSprout
(150001, 2, 0, 1), # PottedMorningGlory
(150002, 2, 0, 2), # GrapeJuiceBottle
(1150000, 2, 0, 3), # PatrolRobot
(1150001, 2, 0, 4); # StrangeSign
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (1150002, 2, 0, 5), # Serpent
(2150000, 2, 0, 6), # WaterThiefMonster
(2150001, 2, 0, 7), # DustBox
(2150002, 3, 0, 8), # Streetlight
(2150003, 2, 0, 9); # PatrolRobotS
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600409, 2, 0, 10), # SafetyFirst
(2600410, 2, 0, 11), # BabyBoulderMucher
(2600411, 2, 0, 12), # BigBoulderMucher
(6150000, 2, 0, 13), # GuardRobot
(7150004, 2, 0, 14); # GuardRobotL
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600414, 2, 0, 15), # BigSpider
(2600415, 2, 0, 16), # CartBear
(2600413, 2, 0, 17), # Racoco
(2600416, 2, 0, 18), # Racaroni
(2600417, 2, 0, 19); # Raco
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 0, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600421, 2, 0, 20), # SecuritySystem
(2600422, 2, 0, 21), # EnhancedSecuritySystem
(2600423, 2, 0, 22), # AFAndroid
(2600424, 2, 0, 23), # BrokenDFAndroid
(2600418, 2, 0, 24); # OreMuncher
# Reward ID: 2434931
# 



insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (2, 1, 2435779, 1);

# /**
# * Scrapyard - Reward: 2435779
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250000, 2, 1, 0), # ModdedScaredroid
(8250001, 2, 1, 1), # ModdedBrokenAndroid
(8250002, 2, 1, 2), # ModdedLaseroid
(8250003, 2, 1, 3), # ChaseroidRed
(8250004, 2, 1, 4); # ChaseroidBlue
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250005, 2, 1, 5), # HunterizerRed
(8250006, 2, 1, 6), # HunterizerBlue
(8250008, 2, 1, 7), # ModdedBuffroid
(8250009, 2, 1, 8), # SalvoroidRed
(8250028, 2, 1, 9); # SalvoroidBlue
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250007, 2, 1, 10), # ModdedDeliverbot
(8250010, 2, 1, 11), # OuterGuardEX
(8250011, 2, 1, 12), # InnerGuardEX
(8250012, 2, 1, 13), # Demolishizer
(8250013, 2, 1, 14); # Repairoid
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8250016, 2, 1, 15), # SteelXendroidDX
(8250018, 2, 1, 16), # ScrapXendroidDX
(8250022, 2, 1, 17), # SteelXenoroidEX
(8250023, 2, 1, 18), # SteelXendroidEX
(8250024, 2, 1, 19); # ScrapXendroidEX
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (2, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8950001, 2, 1, 20), # Lotus
(8250014, 2, 1, 21), # AlloyXendroidDX
(8250021, 2, 1, 22), # AlloyXendroidEX
(8250026, 2, 1, 23), # ModdedMegaroid
(0, 2, 1, 24); # Empty
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (3, 0, 2434993, 1);
# /**
# * Orbis - Reward: 2434993
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5200000, 3, 0, 0), # JrSentinel
(3000000, 3, 0, 1), # Sentinel
(5200001, 3, 0, 2), # IceSentinel
(5200002, 3, 0, 3), # FireSentinel
(6400006, 3, 0, 4); # CrimsonBalrog
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210200, 3, 0, 5), # JrCellion
(3210201, 3, 0, 6), # JrLioner
(3210202, 3, 0, 7), # JrGrupin
(6230401, 3, 0, 8), # JrLucida
(0, 3, 0, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5120001, 3, 0, 10), # Cellion
(5120002, 3, 0, 11), # Lioner
(5120003, 3, 0, 12), # Grupin
(7130000, 3, 0, 13), # Lucida
(8220000, 3, 0, 14); # Eliza
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230106, 3, 0, 15), # LunarPixie
(5120000, 3, 0, 16), # LusterPixie
(3230200, 3, 0, 17), # StarPixie
(9300038, 3, 0, 18), # GhostPixie
(9300039, 3, 0, 19); # PapaPixie
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 0, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4130102, 3, 0, 20), # DarkNependeath
(4230105, 3, 0, 21), # Nependeath
(5160003, 3, 0, 22), # GoldenScorpie
(6160002, 3, 0, 23), # GoldenMammoth
(6160003, 3, 0, 24); # Xerxes
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (3, 1, 2433943, 500);


# /**
# * El Nath 1 - Reward: 2433943 (x500)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5300000, 3, 1, 0), # Leatty
(5300001, 3, 1, 1), # DarkLeatty
(5400000, 3, 1, 2), # JrPepe
(9200018, 3, 1, 3), # JrYeti
(9800010, 3, 1, 4); # DarkJrYeti
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 1, 2434958, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6230200, 3, 1, 5), # DarkPepe
(5130104, 3, 1, 6), # Hector
(5140000, 3, 1, 7), # WhiteFang
(6090001, 3, 1, 8), # SnowWitch
(0, 3, 1, 9); # Empty
# Reward ID: 2434958
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (6300000, 3, 1, 10), # Yeti
(6400000, 3, 1, 11), # DarkYeti
(6130102, 3, 1, 12), # SeperatedPepe
(7130102, 3, 1, 13), # YetiAndPepe
(8140100, 3, 1, 14); # DarkYetiAndPepe
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8220001, 3, 1, 15), # Snowman
(7130200, 3, 1, 16), # Werewolf
(8140000, 3, 1, 17), # Lycanthrope
(6090000, 3, 1, 18), # Riche
(0, 3, 1, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300276, 3, 1, 20), # HoblinHector
(9300277, 3, 1, 21), # EliteHoblin
(9300279, 3, 1, 22), # CombatHoblin
(9300280, 3, 1, 23), # FerociousHoblin
(9300281, 3, 1, 24); # Rex
# Reward ID: 2434932
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (3, 2, 3017003, 1);

# /**
# * El Nath 2 - Reward: 3017003
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210000, 3, 2, 0), # CrockyTheGatekeeper
(8210001, 3, 2, 1), # Reindeer
(8210002, 3, 2, 2), # BloodReindeer
(8210003, 3, 2, 3), # Bearwolf
(8210004, 3, 2, 4); # GreyVulture
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8211003, 3, 2, 5), # GoldenBee
(8211000, 3, 2, 6), # FrozenRose
(8211002, 3, 2, 7), # GardenGolem
(8210005, 3, 2, 8), # CastleGolem
(8211004, 3, 2, 9); # KingCastleGolem
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 2, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8210006, 3, 2, 10), # PrisonGuardBoar
(8210007, 3, 2, 11), # PrisonGuardRhino
(8210013, 3, 2, 12), # PrisonGuardAni
(8211001, 3, 2, 13), # KeymasterRousseau
(8840000, 3, 2, 14); # VonLeon
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4230107, 3, 2, 15), # Flyeye
(5130107, 3, 2, 16), # CoolieZombie
(5130108, 3, 2, 17), # MinerZombie
(7130001, 3, 2, 18), # Cerebes
(8140500, 3, 2, 19); # Bain
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (3, 2, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8800013, 3, 2, 20), # Punco
(6400004, 3, 2, 21), # Opachu
(8800114, 3, 2, 22), # ChaosKusko
(8800002, 3, 2, 23), # Zakum
(8800102, 3, 2, 24); # ChaosZakum
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (4, 0, 3017004, 1);


# /**
# * Ludas Lake (no Near Ludas Lake) - Reward: 3017004
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230400, 4, 0, 0), # DrummingBunny
(3000005, 4, 0, 1), # BrownTeddy
(3110101, 4, 0, 2), # PinkTeddy
(3210203, 4, 0, 3), # PandaTeddy
(3210204, 4, 0, 4); # Roloduck
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3210206, 4, 0, 5), # Helly
(3230303, 4, 0, 6), # Propelly
(3230304, 4, 0, 7), # Planey
(3230307, 4, 0, 8), # Chirppy
(3230308, 4, 0, 9); # Tweeter
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3230302, 4, 0, 10), # Bloctopus
(3230103, 4, 0, 11), # KingBloctopus
(4130103, 4, 0, 12), # Rombot
(4230109, 4, 0, 13), # BlockGolem
(4230110, 4, 0, 14); # KingBlockGolem
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600604, 4, 0, 15), # ToyTrojan
(2600605, 4, 0, 16), # Robo
(2600606, 4, 0, 17), # MasterRobo
(2600613, 4, 0, 18), # Timer
(0, 4, 0, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 0, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (3110102, 4, 0, 20), # Ratz
(3210205, 4, 0, 21), # BlackRatz
(2230103, 4, 0, 22), # Trixter
(9300014, 4, 0, 23), # DarkEyeFromDimension
(9300012, 4, 0, 24); # Alishar
# Reward ID: 2434931
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (4, 1, 2450042, 3);


# /**
# * Ludas Lake 2 - Reward: 2450042 (x3)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600608, 4, 1, 0), # Tick
(2600609, 4, 1, 1), # TickTock
(2600610, 4, 1, 2), # Chronos
(2600611, 4, 1, 3), # PlatoonChronos
(2600612, 4, 1, 4); # MasterChronos
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600620, 4, 1, 5), # Buffy
(2600621, 4, 1, 6), # LazyBuffy
(2600618, 4, 1, 7), # Buffoon
(2600619, 4, 1, 8), # DeepBuffoon
(0, 4, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600616, 4, 1, 10), # SoulTeddy
(2600617, 4, 1, 11), # MasterSoulTeddy
(2600614, 4, 1, 12), # DeathTeddy
(2600615, 4, 1, 13), # MasterDeathTeddy
(0, 4, 1, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600623, 4, 1, 15), # GhostPirate
(2600624, 4, 1, 16), # DualGhostPirate
(2600627, 4, 1, 17), # SpiritViking
(2600628, 4, 1, 18), # GiganticSpiritViking
(0, 4, 1, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 1, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600629, 4, 1, 20), # PhantomWatch
(2600630, 4, 1, 21), # GrimPhantomWatch
(8160000, 4, 1, 22), # Gatekepper
(8170000, 4, 1, 23), # Thantos
(2600631, 4, 1, 24); # Paplatus
# Reward ID: 2434931
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (4, 2, 2432158, 3);

# /**
# * Ellin Forest - Reward: 2432158 (x3)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (4250000, 4, 2, 0), # MossySnail
(4250001, 4, 2, 1), # TreeRod
(5250000, 4, 2, 2), # MossyMushroom
(5250001, 4, 2, 3), # StoneBug
(0, 4, 2, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (5250002, 4, 2, 5), # PrimitiveBoar
(5250003, 4, 2, 6), # ViolentPrimitiveBoar
(5250004, 4, 2, 7), # ChaosCrimsonQueen
(0, 4, 2, 8), # Empty
(0, 4, 2, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8230003, 4, 2, 10), # EvilPoacher
(8230004, 4, 2, 11), # PoachersHawk
(8230005, 4, 2, 12), # HiddenGraveRobber
(8230006, 4, 2, 13), # GraveRobbersHuntingDog
(0, 4, 2, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (9300172, 4, 2, 15), # PoisonedLordTree
(9300173, 4, 2, 16), # PoisonedStoneBug
(9300174, 4, 2, 17), # PoisonFlower
(9300175, 4, 2, 18), # PoisonedSpright
(9300176, 4, 2, 19); # PoisonGolem
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (4, 2, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8230007, 4, 2, 20), # NeonBat
(8230009, 4, 2, 21), # PeaceSpirit
(5250005, 4, 2, 22), # AncientFairy
(5250006, 4, 2, 23), # ShiningFairy
(5250007, 4, 2, 24); # Ephenia
# Reward ID: 2434932
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (5, 0, 3017005, 1);

# * Aqua Road - Reward: 3017005
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230105, 5, 0, 0), # Seacle
(2230106, 5, 0, 1), # Cico
(3210450, 5, 0, 2), # ScubaPepe
(3230405, 5, 0, 3), # JrSeal
(2230108, 5, 0, 4); # Pinboom
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230109, 5, 0, 5), # BubbleFish
(2230200, 5, 0, 6), # FlowerFish
(3230104, 5, 0, 7), # MaskFish
(4230200, 5, 0, 8), # Poopa
(4230201, 5, 0, 9); # PoisonPoopa
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2230107, 5, 0, 10), # Krappy
(3000006, 5, 0, 11), # Krip
(4220000, 5, 0, 12), # Seruf
(4230123, 5, 0, 13), # Sparker
(4230124, 5, 0, 14); # Freezer
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8140555, 5, 0, 15), # BombingFishHouse
(7130020, 5, 0, 16), # Goby
(8140600, 5, 0, 17), # BoneFish
(8141300, 5, 0, 18), # Squid
(8142100, 5, 0, 19); # RisellSquid
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (5, 0, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8150100, 5, 0, 20), # Shark
(8150101, 5, 0, 21), # ColdShark
(8510000, 5, 0, 22), # Pianus
(8510100, 5, 0, 23), # BloodyBoom
(0, 5, 0, 24); # Empty
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (6, 0, 3017006, 1);
# /**
# /**
# * Nihal Desert - Reward: 3017006
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2100103, 6, 0, 0), # Cactus
(2100104, 6, 0, 1), # RoyalCactus
(2600105, 6, 0, 2), # Deo
(2600100, 6, 0, 3), # WhiteDesertRabbit
(2600101, 6, 0, 4); # BrownDesertRabbit
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600106, 6, 0, 5), # Bellamoa
(2600107, 6, 0, 6), # EarPlugPlead
(2600108, 6, 0, 7), # ScarfPlead
(2600109, 6, 0, 8), # Meerkat
(2600114, 6, 0, 9); # Kiyo
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600110, 6, 0, 10), # SandRat
(2600112, 6, 0, 11), # Scorpion
(2600113, 6, 0, 12), # SandDwarf
(2600115, 6, 0, 13), # DarkSandDwarf
(2600117, 6, 0, 14); # DesertGiant
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600122, 6, 0, 15), # Horus
(2600123, 6, 0, 16), # AdvisorMummy
(9305408, 6, 0, 17), # PharaohYeti
(9305413, 6, 0, 18), # PharaohSnake
(9305422, 6, 0, 19); # PharaohMummy
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 0, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2500500, 6, 0, 20), # DeadlyAlter
(2500100, 6, 0, 21), # SpearmanSkeleknight
(2500200, 6, 0, 22), # Bloodfang
(8870100, 6, 0, 23), # SilverHairedHilla
(9300627, 6, 0, 24); # GiantDarkheart
# Reward ID: 2434932
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (6, 1, 2028048, 1);

# /**
# * Magatia - Reward: 2028048
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600500, 6, 1, 0), # CubeSlime
(2600502, 6, 1, 1), # Rumo
(2600503, 6, 1, 2), # TripleRumo
(2600504, 6, 1, 3), # Rurumo
(0, 6, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600505, 6, 1, 5), # IronMutae
(2600506, 6, 1, 6), # ReinforcedIronMutae
(2600507, 6, 1, 7), # MithrilMutae
(2600508, 6, 1, 8), # ReinforcedMithrilMutae
(0, 6, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600510, 6, 1, 10), # Homuns
(2600511, 6, 1, 11), # Homunculus
(2600512, 6, 1, 12), # Homunscullo
(0, 6, 1, 13), # Empty
(0, 6, 1, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600515, 6, 1, 15), # Roid
(2600516, 6, 1, 16), # NeoHuroid
(2600509, 6, 1, 17), # SecurityCamera
(2600518, 6, 1, 18), # DeetandRoi
(2600517, 6, 1, 19); # DRoy
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (6, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600514, 6, 1, 20), # Chimera
(2600513, 6, 1, 21), # Saitie
(9300141, 6, 1, 22), # HomunofClosedLaboratory
(9300139, 6, 1, 23), # Frankenroid
(9300140, 6, 1, 24); # Angry
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (7, 0, 3017007, 1);

# /**
# * Mu Lung Garden 1 - Reward: 3017007
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600306, 7, 0, 0), # Chipmunk
(2600307, 7, 0, 1), # RedPorky
(2600308, 7, 0, 2), # BlackPorky
(0, 7, 0, 3), # Empty
(0, 7, 0, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600313, 7, 0, 5), # Reindeer
(2600314, 7, 0, 6), # PeachMonkey
(2600302, 7, 0, 7), # TheBookGhost
(2600303, 7, 0, 8), # SageCat
(2600305, 7, 0, 9); # KingSageCat
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600309, 7, 0, 10), # BlueFlowerSerpent
(2600310, 7, 0, 11), # RedFlowerSerpent
(2600311, 7, 0, 12), # GiantCentipede
(0, 7, 0, 13), # Empty
(0, 7, 0, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600317, 7, 0, 15), # Jar
(2600318, 7, 0, 16), # GinsengJar
(2600319, 7, 0, 17), # BellflowerRoot
(2600320, 7, 0, 18), # SrBellflowerRoot
(2600321, 7, 0, 19); # GiganticBellflowerRoot
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 0, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600312, 7, 0, 20), # Grizzly
(2600315, 7, 0, 21), # Panda
(2600316, 7, 0, 22), # TaeRoon
(9300215, 7, 0, 23), # MuGong
(0, 7, 0, 24); # Empty
# Reward ID: 2434932
# 


insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (7, 1, 2050004, 100);

# /**
# * Mu Lung Garden 2 - Reward: 2050004 (x100)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144000, 7, 1, 0), # WildMonkey
(8144001, 7, 1, 1), # MamaMonkey
(8144002, 7, 1, 2), # TeenyWhiteMonkey
(8144003, 7, 1, 3), # MeanMamaMonkey
(0, 7, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8144004, 7, 1, 5), # BlueGoblin
(8144005, 7, 1, 6), # RedGoblin
(8144006, 7, 1, 7), # StoneGoblin
(0, 7, 1, 8), # Empty
(0, 7, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8800200, 7, 1, 10), # Ravana
(8144007, 7, 1, 11), # StrongStoneGoblin
(8144008, 7, 1, 12), # Ganapati
(0, 7, 1, 13), # Empty
(0, 7, 1, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600300, 7, 1, 15), # StrawTargetDummy
(2600301, 7, 1, 16), # WoodenTargetDummy
(2600304, 7, 1, 17), # MasterDummy
(6090002, 7, 1, 18), # BambooWarrior
(0, 7, 1, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (7, 1, 4, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600326, 7, 1, 20), # LordPirate
(2600322, 7, 1, 21), # MrAlli
(2600323, 7, 1, 22), # Kru
(2600324, 7, 1, 23), # Captain
(0, 7, 1, 24); # Empty
# Reward ID: 2434931
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (8, 0, 2436272, 1);

# /**
# * Minar Forest - Reward: 2436272
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600000, 8, 0, 0), # Beetle
(2600001, 8, 0, 1), # DualBeetle
(2600003, 8, 0, 2), # Rash
(2600004, 8, 0, 3), # DarkRash
(0, 8, 0, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600002, 8, 0, 5), # Hankie
(2600005, 8, 0, 6), # Hobi
(2600006, 8, 0, 7), # GreenHobi
(9300479, 8, 0, 8), # MasterHoblin
(0, 8, 0, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600008, 8, 0, 10), # Harp
(2600009, 8, 0, 11), # BloodHarp
(9300480, 8, 0, 12), # MasterHarp
(2600007, 8, 0, 13), # Griffey
(0, 8, 0, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600010, 8, 0, 15), # BlackKentaurus
(2600011, 8, 0, 16), # RedKentaurus
(2600012, 8, 0, 17), # BlueKentaurus
(2600015, 8, 0, 18), # KentaurusKing
(0, 8, 0, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 0, 4, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600013, 8, 0, 20), # Birk
(2600014, 8, 0, 21), # DualBirk
(9300481, 8, 0, 22), # MasterBirk
(2600022, 8, 0, 23), # Manon
(0, 8, 0, 24); # Empty
# Reward ID: 2434930
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (8, 1, 3017008, 1);

# /**
# * Dragon Forest - Reward: 3017008
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600016, 8, 1, 0), # BlueDragonTurtle
(2600017, 8, 1, 1), # RedDragonTurtle
(2600018, 8, 1, 2), # Rexton
(2600019, 8, 1, 3), # Brexton
(0, 8, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600020, 8, 1, 5), # JrNewtie
(2600021, 8, 1, 6), # NestGolem
(2600026, 8, 1, 7), # GreenCornian
(2600027, 8, 1, 8), # DarkCornian
(0, 8, 1, 9); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 2, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600023, 8, 1, 10), # RedWyvern
(2600024, 8, 1, 11), # BlueWyvern
(2600025, 8, 1, 12), # DarkWyvern
(2600030, 8, 1, 13), # Leviathan
(0, 8, 1, 14); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8300000, 8, 1, 15), # SoaringHawk
(8300001, 8, 1, 16), # SoaringEagle
(8300005, 8, 1, 17), # SoaringGriffey
(8300006, 8, 1, 18), # Dragonoir
(8300007, 8, 1, 19); # Dragon
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8810018, 8, 1, 20), # Horntail
(8810118, 8, 1, 21), # ChaosHorntail
(2600028, 8, 1, 22), # Skelegon
(2600029, 8, 1, 23), # Skelosaurus
(0, 8, 1, 24); # Empty
# Reward ID: 2434932
# 
insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (8, 2, 2450042, 3);

# /**
# * Kritias - Reward: 2450042 (x3)
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630000, 8, 2, 0), # FrozenSolitude
(8630005, 8, 2, 1), # BurningSolitude
(8630010, 8, 2, 2), # PermeatingSolitude
(0, 8, 2, 3), # Empty
(0, 8, 2, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630021, 8, 2, 5), # FrozenTerror
(8630026, 8, 2, 6), # BurningTerror
(8630031, 8, 2, 7), # PermeatingTerror
(0, 8, 2, 8), # Empty
(0, 8, 2, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630022, 8, 2, 10), # FrozenRage
(8630027, 8, 2, 11), # BurningRage
(8630032, 8, 2, 12), # PermeatingRage
(0, 8, 2, 13), # Empty
(0, 8, 2, 14); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630023, 8, 2, 15), # FrozenAnxiety
(8630028, 8, 2, 16), # BurningAnxiety
(8630033, 8, 2, 17), # PermeatingAnxiety
(8630035, 8, 2, 18), # CorruptedBasicMagician
(0, 8, 2, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (8, 2, 4, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8630024, 8, 2, 20), # FrozenVanity
(8630029, 8, 2, 21), # BurningVainity
(8630034, 8, 2, 22), # PermeatingVainity
(8630036, 8, 2, 23), # CorruptedIntermediateMagician
(8630037, 8, 2, 24); # CorruptedAdvancedMagician
# Reward ID: 2434930
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (9, 0, 2432300, 1);

# /**
# * Gate of the Past (Temple of Time) - Reward: 2432300
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600701, 9, 0, 0), # MemoryMonk
(2600702, 9, 0, 1), # MemoryMonkTrainee
(2600703, 9, 0, 2), # MemoryGuardian
(2600704, 9, 0, 3), # ChiefMemoryGuardian
(2600700, 9, 0, 4); # EyeofTime
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600706, 9, 0, 5), # QualmMonk
(2600707, 9, 0, 6), # QualmMonkTrainee
(2600708, 9, 0, 7), # QualmGuardian
(2600709, 9, 0, 8), # ChiefQualmGuardian
(2600705, 9, 0, 9); # Dodo
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2600711, 9, 0, 10), # OblivionMonk
(2600712, 9, 0, 11), # OblivionMonkTrainee
(2600713, 9, 0, 12), # OblivionGuard
(2600714, 9, 0, 13), # ChiefOblivionGuardian
(2600710, 9, 0, 14); # Lilynouch
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8860000, 9, 0, 15), # Arkarium
(8220020, 9, 0, 16), # CorruptedChiefMemoryGuardian
(8220021, 9, 0, 17), # CorruptedTimeMonkTrainee
(8860002, 9, 0, 18), # NetherworldMonk
(0, 9, 0, 19); # Empty
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 0, 4, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8820000, 9, 0, 20), # PinkBean
(8820100, 9, 0, 21), # ChaosPinkBean
(2600715, 9, 0, 22), # Lyka
(8820003, 9, 0, 23), # SolomontheWise
(8820006, 9, 0, 24); # Munin
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (9, 1, 2432158, 1);

# /**
# * Temple of Time: Gate to the Future - Reward: 2432158
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8600000, 9, 1, 0), # MutantSnail
(8600001, 9, 1, 1), # MutantOrangeMushroom
(8600002, 9, 1, 2), # MutantSlime
(8600003, 9, 1, 3), # MutantRibbonPig
(0, 9, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620000, 9, 1, 5), # SwollenStump
(8620001, 9, 1, 6), # SwollenDarkStump
(8620002, 9, 1, 7), # SwollenAxeStump
(8620012, 9, 1, 8), # GhostwoodStumpy
(0, 9, 1, 9); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620003, 9, 1, 10), # PillagingWildBoar
(8620004, 9, 1, 11), # PillagingIronBoar
(8620005, 9, 1, 12), # PillagingFireBoars
(8620006, 9, 1, 13), # SinisterWoodenMask
(8620007, 9, 1, 14); # SinisterRockyMask
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8620008, 9, 1, 15), # SinisterSteelMask
(8620009, 9, 1, 16), # AncientGolem
(8620010, 9, 1, 17), # AncientDarkGolem
(8620011, 9, 1, 18), # AncientMixedGolem
(0, 9, 1, 19); # Empty
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 1, 4, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8600004, 9, 1, 20), # MutantTino
(8600005, 9, 1, 21), # MutantTiru
(8600006, 9, 1, 22), # MutantTiguru
(0, 9, 1, 23), # Empty
(0, 9, 1, 24); # Empty
# Reward ID: 2434929
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (9, 2, 3017009, 1);

# /**
# * Temple of Time 1 - Reward: 3017009
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 0, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610000, 9, 2, 0), # Dawn
(8610001, 9, 2, 1), # Blaze
(8610002, 9, 2, 2), # Wind
(8610003, 9, 2, 3), # Night
(8610004, 9, 2, 4); # Thunder
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610005, 9, 2, 5), # OfficialKnightA
(8610006, 9, 2, 6), # OfficialKnightB
(8610007, 9, 2, 7), # OfficialKnightC
(8610008, 9, 2, 8), # OfficialKnightD
(8610009, 9, 2, 9); # OfficialKnightE
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8610010, 9, 2, 10), # AdvancedKnightA
(8610011, 9, 2, 11), # AdvancedKnightB
(8610012, 9, 2, 12), # AdvancedKnightC
(8610013, 9, 2, 13), # AdvancedKnightD
(8610014, 9, 2, 14); # AdvancedKnightE
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 3, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8850000, 9, 2, 15), # Mihile
(8850001, 9, 2, 16), # Oz
(8850002, 9, 2, 17), # Irena
(8850003, 9, 2, 18), # Eckhart
(8850004, 9, 2, 19); # Hawkeye
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (9, 2, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (8850011, 9, 2, 20), # Cygnus
(8850013, 9, 2, 21), # Shinsoo
(8610015, 9, 2, 22), # Ifrit
(0, 9, 2, 23), # Empty
(0, 9, 2, 24); # Empty
# Reward ID: 2434932
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (10, 0, 2434993, 1);

# /**
# * Grandis 1 - Reward: 2434993
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400200, 10, 0, 0), # Caterpillar
(2400201, 10, 0, 1), # LadyBug
(2400202, 10, 0, 2), # Sparrow
(2400203, 10, 0, 3), # BalloonMouse
(2400204, 10, 0, 4); # RedPoisonFrog
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 1, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400205, 10, 0, 5), # GreenPoisonFrog
(2400206, 10, 0, 6), # DelinquentChick
(2400207, 10, 0, 7), # PunkChick
(2400208, 10, 0, 8), # ManeFurHyena
(2400209, 10, 0, 9); # FangHyena
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 2, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400000, 10, 0, 10), # SleepyGrobbler
(2400001, 10, 0, 11), # AlertGrobbler
(2400002, 10, 0, 12), # GrumpyGrobbler
(2400003, 10, 0, 13), # LimestoneTokka
(2400004, 10, 0, 14); # AmethystTokka
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 3, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400005, 10, 0, 15), # Laloong
(2400006, 10, 0, 16), # Kaloong
(2400007, 10, 0, 17), # NefariousMonkInitiate
(2400008, 10, 0, 18), # NefariousMonkApprentice
(2400009, 10, 0, 19); # NefariousMonkMaster
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 0, 4, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400010, 10, 0, 20), # HereticMonkInitiate
(2400011, 10, 0, 21), # HereticMonkApprentice
(2400012, 10, 0, 22), # HereticLeader
(2400013, 10, 0, 23), # OnyxStonegar
(2400014, 10, 0, 24); # Gravi
# Reward ID: 2434930
# 

insert into `monster_collection_session_rewards` (`region`, `session`, `rewardid`, `quantity`) values (10, 1, 3017010, 1);
# /**
# * Grandis 2 - Reward: 3017010
# */
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 0, 2434929, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400100, 10, 1, 0), # BlueSpeeyor
(2400101, 10, 1, 1), # RedSpeeyor
(2400102, 10, 1, 2), # YellowSpeeyor
(0, 10, 1, 3), # Empty
(0, 10, 1, 4); # Empty
# Reward ID: 2434929
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 1, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400103, 10, 1, 5), # Dinogoth
(2400104, 10, 1, 6), # Dinoram
(2400105, 10, 1, 7), # Dinodon
(2400106, 10, 1, 8), # SpecterBattleHound
(2400107, 10, 1, 9); # SpecterTamer
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 2, 2434930, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400108, 10, 1, 10), # SpecterMiner
(2400109, 10, 1, 11), # SpecterShieldbearer
(2400110, 10, 1, 12), # GuerrillaSpecter
(2400111, 10, 1, 13), # PowerSpecter
(2400112, 10, 1, 14); # SpecterEngineer
# Reward ID: 2434930
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 3, 2434931, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2400113, 10, 1, 15), # WarriorSpecter
(2400114, 10, 1, 16), # MagicianSpecter
(2400115, 10, 1, 17), # ReaperSpecter
(2400118, 10, 1, 18), # PurpleReagentRock
(2400116, 10, 1, 19); # RedTotemStaff
# Reward ID: 2434931
# 
insert into `monster_collection_group_rewards` (`region`, `session`, `groupid`, `rewardid`, `quantity`) values (10, 1, 4, 2434932, 1);
insert into `monster_collection` (`mobid`, `region`, `session`, `position`) values (2700029, 10, 1, 20), # Magnus
(8880004, 10, 1, 21), # Velderoth
(8880008, 10, 1, 22), # Treglow
(8880006, 10, 1, 23), # Victor
(2400116, 10, 1, 24); # RedTotemStaff
# Reward ID: 2434932
