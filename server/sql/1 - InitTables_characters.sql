set FOREIGN_KEY_CHECKS = 0;
drop table if exists
  users,
  accounts,
  damageskinsavedatas,
  matrix_records,
  friends,
  linkskills,
  monster_collection_rewards,
  monster_collection_mobs,
  monster_collection_explorations,
  monster_collections,
  macroskills,
  macros,
  familiars,
  stolenskills,
  chosenskills,
  skillcooltimes,
  hyperrockfields,
  characterpotentials,
  test,
  skills,
  characters,
  avatardata,
  alliance_gradenames,
  alliances,
  keymaps,
  funckeymap,
  offenses,
  offense_managers,
  characterstats,
  hairequips,
  unseenequips,
  petids,
  totems,
  spset,
  extendsp,
  noncombatstatdaylimit,
  systemtimes,
  charactercards,
  avatarlook,
  equips,
  petitems,
  items,
  auction_items,
  inventories,
  questprogressrequirements,
  questprogressitemrequirements,
  questprogresslevelrequirements,
  questprogressmoneyrequirements,
  questprogressmobrequirements,
  questlists,
  questmanagers,
  quests,
  questlists_ex,
  quests_ex,
  bbs_replies,
  bbs_records,
  gradenames,
  guildmembers,
  guildrequestors,
  guildskills,
  guildskill,
  guilds,
  monsterbookcards,
  monsterbookinfos,
  trunks,
  towerchairs,
  merchantitems,
  cashiteminfos,
  unions,
  employeetrunk,
  eventscooltimes,
  sdc,
  unionboards,
  unionmembers,
  achievements,
  dailychallenge,
  ignoreddrops,
  hottimerewards,
  bosscooldowns,
  beautyalbuminventory,
  charactercard
;

create table merchantitems (
	id bigint not null auto_increment,
	merchantitemid int,
    bundles int,
    price long,
	employeetrunkid int,
    primary key (id)
);

create table employeetrunk (
  id int(11) not null auto_increment,
  money bigint(20) default '0',
  primary key (id)
);

create table trunks
(
  id        int not null auto_increment,
  slotcount tinyint,
  money     bigint,
  primary key (id)
);

create table cashiteminfos
(
  id             bigint not null auto_increment,
  accountid      int,
  characterid    int,
  commodityid    int,
  buycharacterid varchar(255),
  paybackrate    int,
  discount       double,
  orderno        int,
  productno      int,
  refundable     boolean,
  sourceflag     tinyint,
  storebank      boolean,
  itemid         int,
  trunkid        int,
  position       int,
  primary key (id)
);

create table quests
(
  id            bigint not null auto_increment,
  qrkey         int,
  qrvalue       varchar(255),
  status        int,
  completedtime datetime,
  primary key (id)
);

create table quests_ex (
	id bigint not null auto_increment,
    charid int,
    questid int,
    qrValue varchar(255),
	primary key (id),
	foreign key (charid) references characters(id)
);

create table questmanagers
(
  id bigint not null auto_increment,
  primary key (id)
);

create table questlists
(
  questlist_id    bigint not null auto_increment,
  questmanager_id bigint,
  questid         int,
  fk_questid      bigint,
  primary key (questlist_id),
  foreign key (questmanager_id) references questmanagers (id) on delete cascade,
  foreign key (fk_questid) references quests (id)
);

create table questprogressrequirements
(
  id            bigint not null auto_increment,
  orderNum      int,
  progresstype  varchar(255),
  questid       bigint,
  unitid        int,
  requiredcount int,
  currentcount  int,
  primary key (id),
  foreign key (questid) references quests (id) on delete cascade
);

create table inventories
(
  id    int not null auto_increment,
  type  int,
  slots smallint,
  primary key (id)
);

create table items
(
  id                   bigint not null auto_increment,
  inventoryid          int, # item can be inside an inventory or storage, so cannot be a foreign key :(
  trunkid              int,
  itemid               int,
  bagindex             int,
  cashitemserialnumber bigint,
  dateexpire           datetime,
  invtype              int,
  type                 int,
  iscash               boolean,
  quantity             int,
  owner                varchar(255),
  primary key (id)
);

create table auction_items
(
  id           int not null auto_increment,
  type         int,
  accountid    int,
  charid       int,
  state        int,
  itemType     int,
  charName     varchar(255),
  price        bigint,
  secondprice  bigint,
  directprice  bigint,
  endDate      datetime,
  biduserid    int,
  bidusername  varchar(255),
  idk          int,
  bidworld     int,
  oid          int,
  regdate      datetime,
  deposit      bigint,
  sstype       int,
  idk2         int,
  idk3         int,
  unkdate      datetime,
  item         int,
  itemname     varchar(255),
  soldquantity int,
  primary key (id)
);

create table petitems
(
  itemid        bigint,
  name          varchar(255),
  level         tinyint,
  tameness      smallint,
  repleteness   tinyint,
  petattribute  smallint,
  petskill      int,
  datedead      datetime,
  remainlife    int,
  attribute     smallint,
  activestate   tinyint,
  autobuffskill int,
  pethue        int,
  giantrate     smallint,
  primary key (itemid),
  foreign key (itemid) references items (id) on delete cascade
);

create table guilds
(
  id            int not null auto_increment,
  name          varchar(255),
  leaderid      int,
  worldid       int,
  markbg        int,
  markbgcolor   int,
  mark          int,
  markcolor     int,
  maxmembers    int,
  notice        varchar(255),
  points        int,
  seasonpoints  int,
  allianceid    int,
  level         int,
  `rank`          int,
  ggp           int,
  appliable     boolean,
  joinsetting   int,
  reqlevel      int,
  bbsNotice     int,
  battleSp      int,
  fk_allianceid int,
  primary key (id)
);

create table equips
(
  serialnumber     bigint,
  itemid           bigint,
  title            varchar(255),
  equippeddate     datetime,
  prevbonusexprate int,
  options          varchar(255),
  sockets          varchar(255),
  tuc              smallint,
  cuc              smallint,
  istr             smallint,
  idex             smallint,
  iint             smallint,
  iluk             smallint,
  imaxhp           smallint,
  imaxmp           smallint,
  ipad             smallint,
  imad             smallint,
  ipdd             smallint,
  imdd             smallint,
  iacc             smallint,
  ieva             smallint,
  icraft           smallint,
  ispeed           smallint,
  ijump            smallint,
  attribute        smallint,
  leveluptype      smallint,
  level            smallint,
  exp              smallint,
  durability       smallint,
  iuc              smallint,
  ipvpdamage       smallint,
  ireducereq       smallint,
  specialattribute smallint,
  durabilitymax    smallint,
  iincreq          smallint,
  growthenchant    smallint,
  psenchant        smallint,
  hyperupgrade     smallint,
  bdr              smallint,
  imdr             smallint,
  damr             smallint,
  statr            smallint,
  cuttable         smallint,
  exgradeoption    bigint,
  itemstate        smallint,
  grade            smallint,
  chuc             smallint,
  souloptionid     smallint,
  soulsocketid     smallint,
  souloption       smallint,
  rstr             smallint,
  rdex             smallint,
  rint             smallint,
  rluk             smallint,
  rlevel           smallint,
  rjob             smallint,
  rpop             smallint,
  specialgrade     int,
  fixedpotential   boolean,
  tradeblock       boolean,
  isonly           boolean,
  notsale          boolean,
  attackspeed      int,
  price            int,
  charmexp         int,
  expireonlogout   boolean,
  setitemid        int,
  exitem           boolean,
  equiptradeblock  boolean,
  islot            varchar(255),
  vslot            varchar(255),
  fixedgrade       int,
  nopotential      tinyint,
  arc              smallint,
  symbolexp        int,
  symbollevel      smallint,
  bossreward       tinyint,
  fstr             smallint,
  fdex             smallint,
  fint             smallint,
  fluk             smallint,
  fatt             smallint,
  fmatt            smallint,
  fdef             smallint,
  fhp              smallint,
  fmp              smallint,
  fspeed           smallint,
  fjump            smallint,
  fallstat         smallint,
  fboss            smallint,
  fdamage          smallint,
  flevel           smallint,
  superioreqp      tinyint,
  android          int,
  androidgrade     int,
  primary key (itemid),
  foreign key (itemid) references items (id) on delete cascade
);

create table monsterbookinfos
(
  id      int not null auto_increment,
  setid   int,
  coverid int,
  primary key (id)
);

create table monsterbookcards
(
  id     bigint not null auto_increment,
  bookid int,
  cardid int,
  primary key (id),
  foreign key (bookid) references monsterbookinfos (id) on delete cascade
);

create table avatarlook
(
  id                    int not null auto_increment,
  gender                int,
  skin                  int,
  face                  int,
  hair                  int,
  weaponstickerid       int,
  weaponid              int,
  subweaponid           int,
  job                   int,
  drawelfear            boolean,
  demonslayerdeffaceacc int,
  xenondeffaceacc       int,
  beasttamerdeffaceacc  int,
  iszerobetalook        boolean,
  mixedhaircolor        int,
  mixhairpercent        int,
  ears                  int,
  tail                  int,
  primary key (id)
);

create table hairequips
(
  id      int not null auto_increment,
  alid    int,
  equipid int,
  primary key (id),
  foreign key (alid) references avatarlook (id) on delete cascade
);

create table unseenequips
(
  id      int not null auto_increment,
  alid    int,
  equipid int,
  primary key (id),
  foreign key (alid) references avatarlook (id) on delete cascade
);

create table petids
(
  id    int not null auto_increment,
  alid  int,
  petid int,
  primary key (id),
  foreign key (alid) references avatarlook (id) on delete cascade
);

create table totems
(
  id      int not null auto_increment,
  alid    int,
  totemid int,
  primary key (id),
  foreign key (alid) references avatarlook (id) on delete cascade
);

create table extendsp
(
  id int not null auto_increment,
  primary key (id)
);

create table spset
(
  id          int not null auto_increment,
  extendsp_id int,
  joblevel    tinyint,
  sp          int,
  primary key (id),
  foreign key (extendsp_id) references extendsp (id) on delete cascade
);

create table systemtimes
(
  id   int not null auto_increment,
  yr   int,
  mnth int,
  primary key (id)
);

create table noncombatstatdaylimit
(
  id                      int not null auto_increment,
  charisma                smallint,
  charm                   smallint,
  insight                 smallint,
  will                    smallint,
  craft                   smallint,
  sense                   smallint,
  lastupdatecharmbycashpr datetime,
  charmbycashpr           tinyint,
  primary key (id)
);

create table charactercards
(
  id          int not null auto_increment,
  characterid int,
  job         int,
  level       tinyint,
  primary key (id)
);

create table characterstats
(
  id                    int not null auto_increment,
  characterid           int,
  characteridforlog     int,
  worldidforlog         int,
  name                  varchar(255),
  gender                int,
  skin                  int,
  face                  int,
  hair                  int,
  mixbasehaircolor      int,
  mixaddhaircolor       int,
  mixhairbaseprob       int,
  level                 int,
  job                   int,
  str                   int,
  dex                   int,
  inte                  int,
  luk                   int,
  hp                    int,
  maxhp                 int,
  mp                    int,
  maxmp                 int,
  ap                    int,
  sp                    int,
  exp                   long,
  pop                   int,
  money                 long,
  wp                    int,
  extendsp              int,
  posmap                long,
  portal                int,
  subjob                int,
  deffaceacc            int,
  fatigue               int,
  lastfatigueupdatetime int,
  charismaexp           int,
  insightexp            int,
  willexp               int,
  craftexp              int,
  senseexp              int,
  charmexp              int,
  noncombatstatdaylimit int,
  pvpexp                int,
  pvpgrade              int,
  pvppoint              int,
  pvpmodelevel          int,
  pvpmodetype           int,
  eventpoint            int,
  pierce 				double not null default '0',
  albaactivityid        int,
  albastarttime         datetime,
  albaduration          int,
  albaspecialreward     int,
  burning               boolean,
  charactercard         int,
  accountlastlogout     int,
  lastlogout            datetime,
  gachexp               int,
  honorexp              int,
  nextavailablefametime datetime,
  primary key (id),
  foreign key (extendsp) references extendsp (id),
  foreign key (noncombatstatdaylimit) references noncombatstatdaylimit (id),
  foreign key (charactercard) references charactercards (id),
  foreign key (accountlastlogout) references systemtimes (id)
);

create table avatardata
(
  id             int not null auto_increment,
  characterstat  int,
  avatarlook     int,
  zeroavatarlook int,
  primary key (id),
  foreign key (characterstat) references characterstats (id),
  foreign key (avatarlook) references avatarlook (id),
  foreign key (zeroavatarlook) references avatarlook (id)
);

create table funckeymap (
	id int not null auto_increment,
    primary key (id),
    charId int,
    ord int
);

create table keymaps (
	id int not null auto_increment,
    fkmapid int,
    idx int,
    type tinyint,
    val int,
    primary key (id),
    foreign key (fkmapid) references funckeymap(id)
);

create table alliances
(
  id           int not null auto_increment,
  name         varchar(255),
  maxmembernum int,
  notice       varchar(255),
  primary key (id)
);

create table alliance_gradenames
(
  id         int not null auto_increment,
  gradename  varchar(255),
  allianceid int,
  primary key (id),
  foreign key (allianceid) references alliances (id) on delete cascade
);

create table bbs_records
(
  id           int not null auto_increment,
  idforbbs     int,
  creatorid    int,
  subject      varchar(255),
  msg          text,
  creationdate datetime,
  icon         int,
  guildid      int,
  primary key (id)
);

create table bbs_replies
(
  id           int not null auto_increment,
  idforreply   int,
  creatorid    int,
  creationdate datetime,
  msg          text,
  recordid     int,
  primary key (id),
  foreign key (recordid) references bbs_records (id) on delete cascade
);

create table offense_managers
(
  id     int not null auto_increment,
  points int,
  primary key (id)
);

create table offenses
(
  id             bigint not null auto_increment,
  manager_id     int,
  charid         int,
  accountid      int,
  msg            text,
  type           varchar(255),
  issuedate      datetime,
  issuer_char_id int,
  primary key (id),
  foreign key (manager_id) references offense_managers (id) on delete cascade
);

create table monster_collections
(
  id int not null auto_increment,
  primary key (id)
);

create table users
(
  id                   int not null auto_increment,
  banExpireDate        datetime,
  banReason            varchar(255),
  chatBanExpireDate    datetime,
  chatBanReason        varchar(255),
  offensemanager       int,
  votepoints           int     default 0,
  reserveVotePoints    int     default 0,
  donationpoints       int     default 0,
  reserveDonationPoints int     default 0,
  maplePoints          int     default 0,
  nxPrepaid            int     default 0,
  name                 varchar(255),
  password             varchar(255),
  pic                  varchar(255),
  mac                  varchar(255),
  accounttype          int     default 0,
  age                  int     default 0,
  vipgrade             int     default 0,
  nblockreason         int     default 0,
  gender               tinyint default 0,
  msg2                 tinyint default 0,
  purchaseexp          tinyint default 0,
  pblockreason         tinyint default 3,
  chatunblockdate      bigint  default 0,
  hascensorednxloginid boolean default 0,
  gradecode            tinyint default 0,
  censorednxloginid    varchar(255),
  characterslots       int     default 4,
  creationdate         datetime,
  email                varchar(255),
  registerip           varchar(255),
  primary key (id),
  foreign key (offensemanager) references offense_managers (id)
);

create table accounts
(
  id                  int not null auto_increment,
  worldid             int,
  userid              int,
  trunkid             int,
  nxCredit            int default 0,
  friendshipPoints    int default 0,
  dojopoints          int default 0,
  shipLevel           int default 0,
  shipExp             int default 0,
  monstercollectionid int,
  employeetrunkid     int,
  unionid             int,
  primary key (id),
  foreign key (userid) references users (id) on delete cascade,
  foreign key (trunkid) references trunks (id),
  foreign key (monstercollectionid) references monster_collections (id),
  secondaryPendantEndDate datetime(3)
);

create table eventscooltimes (
	id int not null auto_increment,
    accid int,
    eventType int,
    amountDone int,
    nextresettime bigint,
    primary key (id),
    foreign key (accid) references accounts(id)
);

create table sdc (
	id int not null auto_increment,
    accountid int,
    sdcType int,
    timesCompleted int,
    collected int,
    primary key (id),
    foreign key (accountid) references accounts(id) on delete cascade
);


create table characters
(
  id                int not null auto_increment,
  accid             int,
  userid            int,
  avatardata        int,
  faceinventory     int,
  hairinventory     int,
  equippedinventory int,
  equipinventory    int,
  consumeinventory  int,
  etcinventory      int,
  installinventory  int,
  cashinventory     int,
  funckeymap_id     int,
  fieldid           int,
  questmanager      bigint,
  guild             int,
  rewardPoints      int,
  monsterbook       int,
  partyid           int,
  monsterparkcount  tinyint default 0,
  previousFieldID   bigint,
  quickslotKeys     varchar(255), # inlined array
  location          int,
  runeStoneCooldown bigint default 0,
  primary key (id),
  foreign key (accid) references accounts (id) on delete cascade,
  foreign key (avatardata) references avatardata (id),
  foreign key (faceinventory) references inventories(id),
  foreign key (hairinventory) references inventories(id),
  foreign key (equippedinventory) references inventories (id),
  foreign key (equipinventory) references inventories (id),
  foreign key (consumeinventory) references inventories (id),
  foreign key (etcinventory) references inventories (id),
  foreign key (installinventory) references inventories (id),
  foreign key (cashinventory) references inventories (id),
  foreign key (funckeymap_id) references funckeymap (id),
  foreign key (questmanager) references questmanagers (id),
  foreign key (monsterbook) references monsterbookinfos (id)
);

create table achievements (
	id int not null auto_increment,
    accountid int,
    ord int,
    achid int,
    primary key (id),
    foreign key (accountid) references accounts(id)
);

create table ignoreddrops (
	id bigint not null auto_increment,
    charid int,
    ord int,
    itemid int,
    primary key (id),
    foreign key (charid) references characters(id) on delete cascade
);

create table dailychallenge (
	id int not null auto_increment,
    charid int,
    sdcid int,
    timesUsed int,
    primary key (id),
    foreign key (charid) references characters(id) on delete cascade
);

create table familiars (
	id bigint not null auto_increment,
    charid int,
    idk1 int,
    familiarid int,
    name varchar(13),
    idk2 boolean,
    idk3 smallint,
    fatigue int,
    idk4 bigint,
    idk5 bigint,
    expiration datetime(3),
    vitality smallint,
    primary key (id),
    foreign key (charid) references characters(id) on delete cascade
);

create table stolenskills
(
  id        int not null auto_increment,
  charid    int,
  skillid   int,
  position  int,
  currentlv tinyint,
  primary key (id),
  foreign key (charid) references characters (id) on delete cascade
);

create table chosenskills
(
  id       int not null auto_increment,
  charid   int,
  skillid  int,
  position int,
  primary key (id),
  foreign key (charid) references characters (id) on delete cascade
);

create table skillcooltimes
(
  id             int not null auto_increment,
  charid         int,
  skillid        int,
  nextusabletime bigint,
  primary key (id),
  foreign key (charid) references characters (id) on delete cascade
);

create table bosscooldowns
(
  id int not null auto_increment,
  accid int,
  boss varchar(255),
  nextentrytime datetime,
  primary key (id),
  foreign key (accid) references accounts (id) on delete cascade
);

create table hyperrockfields
(
  id      bigint not null auto_increment,
  charid  int,
  ord     int,
  fieldid int,
  primary key (id),
  foreign key (charid) references characters (id) on delete cascade
);

create table towerchairs
(
  id      bigint not null auto_increment,
  charid  int,
  ord     int,
  chairid int,
  primary key (id),
  foreign key (charid) references characters (id) on delete cascade
);

create table characterpotentials
(
  id      bigint not null auto_increment,
  potkey  tinyint,
  skillid int,
  slv     int,
  grade   tinyint,
  charid  int,
  primary key (id),
  foreign key (charid) references characters (id) on delete cascade
);

create table guildskill
(
  id                  int not null auto_increment,
  skillid             int,
  level               int,
  expiredate          datetime,
  buycharactername    varchar(255),
  extendcharactername varchar(255),
  primary key (id)
);

create table guildskills
(
  guildskill_id   int not null auto_increment,
  skills_id       int,
  guild_id        int,
  skillid         int,
  fk_guildskillid int,
  primary key (guildskill_id),
  foreign key (guild_id) references guilds (id) on delete cascade,
  foreign key (fk_guildskillid) references guildskill (id) on delete cascade
);

create table guildmembers
(
  id                int not null auto_increment,
  charid            int,
  guildid           int,
  grade             int,
  alliancegrade     int,
  commitment        int,
  daycommitment     int,
  igp               int,
  commitmentinctime datetime,
  name              varchar(255),
  job               int,
  level             int,
  loggedin          boolean,
  primary key (id),
  foreign key (guildid) references guilds (id) on delete cascade
);

drop table if exists guildrequestors;
create table guildrequestors
(
  id       int not null auto_increment,
  charid   int,
  guildid  int,
  name     varchar(255),
  job      int,
  level    int,
  loggedin boolean,
  primary key (id),
  foreign key (guildid) references guilds (id) on delete cascade
);

create table gradenames
(
  id        int not null auto_increment,
  gradename varchar(255),
  guildid   int,
  primary key (id),
  foreign key (guildid) references guilds (id) on delete cascade
);


create table skills
(
  id           int not null auto_increment,
  charid       int,
  skillid      int,
  rootid       int,
  maxlevel     int,
  currentlevel int,
  masterlevel  int,
  primary key (id),
  foreign key (charid) references characters (id) on delete cascade
);

create table macros
(
  id     bigint not null auto_increment,
  charid int,
  muted  boolean,
  name   varchar(255),
  primary key (id),
  foreign key (charid) references characters (id) on delete cascade
);

create table macroskills
(
  id       bigint not null auto_increment,
  ordercol int,
  skillid  int,
  macroid  bigint,
  primary key (id),
  foreign key (macroid) references macros (id) on delete cascade
);


create table monster_collection_mobs
(
  id           int not null auto_increment,
  collectionid int,
  mobid        int,
  primary key (id)
);

create table monster_collection_explorations
(
  id            bigint not null auto_increment,
  collectionid  int,
  collectionkey int,
  monsterkey    varchar(255),
  endDate       datetime,
  position      int,
  primary key (id)
);

create table monster_collection_rewards
(
  region       int,
  session      int,
  groupid      int,
  collectionid int,
  primary key (region, session, groupid)
);



create table linkskills
(
  id          bigint not null auto_increment,
  accid       int,
  linkskillid int,
  level       int,
  originid    int,
  usingid     int,
  primary key (id),
  foreign key (accid) references accounts (id) on delete cascade
);

create table matrix_records
(
  id         bigint not null auto_increment,
  iconid     int,
  skillid1   int,
  skillid2   int,
  skillid3   int,
  slv        int,
  maxLevel   int,
  `row`        int,
  exp        int,
  crc        bigint,
  expiredate datetime,
  active     boolean,
  charid     int,
  position   int,
  primary key (id),
  foreign key (charid) references characters (id) on delete cascade
);

create table damageskinsavedatas
(
  id           bigint not null auto_increment,
  damageskinid int,
  itemid       int,
  notsave      boolean,
  description  varchar(255),
  accid        int,
  primary key (id),
  foreign key (accid) references accounts (id) on delete cascade
);

create table friends
(
  id              int not null auto_increment,
  ownerid         int,
  owneraccid      int,
  friendid        int,
  friendaccountid int,
  name            varchar(255),
  flag            tinyint,
  groupname       varchar(255),
  mobile          tinyint,
  nickname        varchar(255),
  memo            varchar(255),
  primary key (id)
);

create table unions
(
  id        bigint not null auto_increment,
  unionCoin int,
  unionRank int,
  presets   int,
  primary key (id)
);

create table unionboards
(
  id          bigint not null auto_increment,
  unionid     bigint,
  unionpower  int,
  uniondamage bigint,
  synergyGrid text,
  primary key (id),
  foreign key (unionid) references unions (id) on delete cascade
);

create table unionmembers
(
  id           bigint not null auto_increment,
  unionboardid bigint,
  type         int,
  charid       int,
  mobileName   varchar(30),
  gridpos      int,
  gridrotation int,
  primary key (id),
  foreign key (unionboardid) references unionboards (id) on delete cascade
);

create table hottimerewards
(
	id bigint not null auto_increment,
    charid int,
    starttime datetime,
    endtime datetime,
    rewardtype int,
    itemid int,
    quantity int,
    maplepointamount int,
    mesoamount int,
    expamount int,
    description varchar(255),
    primary key (id),
    foreign key (charid) references characters (id) on delete cascade
);