# 
# @author sword fighting bakery
# @npc Dame Appropiation - Legion Manager
# @quest Talk to Dame Appropiation
# 
DAME = 9010106

sm.setSpeakerID(DAME)
# Hack to get intro to work for now (16013 isn't showing up :( )
if not sm.hasQuest(16013) and not sm.hasQuestCompleted(16013):
	if sm.getUnionLevel() < 500 or sm.getUnionCharacterCount() < 3:
		sm.sendSayOkay("You're not ready to join a Legion, warrior. Legions are reserved for battle-hardened warriors with a "
					   "#b#eCumulative Level#n of at least 500#k, and require #r#ea minimum of 3 characters#n#k.\r\n\r\n#eWhat "
					   "is Cumulative Level?#n\r\n#bYour Cumulative Level is the combined level of all your characters on a given "
					   "world #rwho are Lv. 60+ and have completed the 2nd Job Advancement#k. If you have 40 or more characters, "
					   "the only your 40 highest level characters will be counted. However, #rZero#k is a special case. Only your"
					   " highest level Zero will be counted.")
	else:
		sm.setQRValue(18793, "q0=1q1=0pq=0q2=0q1Date=" + sm.getCurrentDateAsString() + "pqDate=" +
					  sm.getCurrentDateAsString() + "q2Date=" + sm.getCurrentDateAsString()) # Legion quest
		rank = chr.getUnion().getUnionRank()
		if rank == 0:
			rank = 101
		sm.setQRValue(18771, "rank=" + str(rank)) # Legion rank
		sm.completeQuestNoRewards(16013)
		sm.sendNext("Hello #b#h0##k. Good to see you again.")
		sm.setPlayerAsSpeaker()
		sm.sendSay("Have we met?")
		sm.setSpeakerID(DAME)
		sm.sendSay("Ta-da! It's me, #b#eMs. Appropriation!#n#k Surprised? Frustrated with the everyday bureaucracy of the "
				   "workplace, I spent my free time getting totally ripped and hunting down dragons. And now I've been "
				   "knighted by Empress Cygnus!")
		sm.sendSay("For a while now, I've been matching Maplers with all kinds of #bpart-time jobs#k to help them build "
				   "character and level up.\r\n\r\nIt was an okay start, but the system didn't work out the way\r\nI had "
				   "hoped. Too much bureaucratic red tape.")
		sm.sendSay("#b#eBut now, I'M in charge.#n#k Wahahahaha!\r\nAnd the new system I've developed is a zillion times "
				   "better, with better rewards! Shall I tell you about it?")
		sm.sendSay("Several months ago, I went on my first vacation in years. But our cruise ship ran aground on an "
				   "#buncharted island#k full of #rterrible dragons#k.\r\nAfter the captain and crew were dragged off "
				   "into the jungle and devoured, we passengers realized that there was only one way we were getting off "
				   "the island alive...")
		sm.sendSay("We took up whatever arms we could find. Pots and pans, knives, and the odd broadsword from somebody's "
				   "luggage. When the dragons returned to carry us off, we charged forward together and beat them to death!")
		sm.sendSay("We ate well that night. And while we sat around the campfire, thinking of our fallen comrades, I "
				   "reached an epiphany.\r\n\r\nSome among us were seasoned warriors, but just as many were flabby tourists. "
				   "The only reason we survived is because we banded together..")
		sm.sendSay("I realized what was inherently wrong with the part-time job system.")
		sm.sendSay("Nothing is gained by going it alone. But working as a group, the strengths of one compensate for the "
				   "weaknesses of another.\r\n\r\n#bWhen people work together#k, even a pudgy sightseer can take down a "
				   "giant dragon.")
		sm.sendSay("After a few months on the island, we managed to capture and tame enough dragons to fly the remaining "
				   "survivors to safety. And when I returned to civilization, I knew exactly what I had to do. \r\n\r\n"
				   "First, I told my boss to suck an egg, and I quit. And then, I started my #bgrand new project#k.")
		sm.sendSay("And that project is, the #b#e<Legion System>!#n#k I'm going to help all those sad, weak, and flabby "
				   "Maplers reach their true potential by pairing them with other warriors!\r\n\r\nThe end result? "
				   "Everybody makes mad gains and gets swole like me! Oh and they'll probably level up faster.")
		sm.sendSay("#h0#! Don't you want to put together a #bLegion#k of swole bros to punch dragons in the face and "
				   "unlock stat bonuses?\r\nIf you're interested, or have any questions, just talk to me or my squire"
				   " and fellow cruise survivor, Pancho Sanza.")
		sm.progressMessageFont(3, 20, 20, 0, "You can now manage your Legion from the Menu.")



else:
	if sm.getUnionLevel() < 500 or sm.getUnionCharacterCount() < 3 or sm.getUnionRank() == 0:
		sm.sendSayOkay("You're not ready to join a Legion, warrior. Legions are reserved for battle-hardened warriors with "
					   "a #b#eCumulative Level#n of at least 500#k, and require #r#ea minimum of 3 characters#n#k.\r\n\r\n"
					   "#eWhat is Cumulative Level?#n\r\n#bYour Cumulative Level is the combined level of all your "
					   "characters on a given world #rwho are Lv. 60+ and have completed the 2nd Job Advancement#k. "
					   "If you have 40 or more characters, the only your 40 highest level characters will be counted. "
					   "However, #rZero#k is a special case. Only your highest level Zero will be counted.")
	else:
		nSel = sm.sendSay("It's a fine day to bludgeon some dragons!\r\nAre you here about your #bLegion#k?\r\n\r\n"
						  "#L0# #b<About My Legion>#l\r\n"
						  "#L1# #b<Raise Legion Rank>#l\r\n"
						  "#L2# #b<About the Legion System>#k#l\r\n"
						  "#L3# #b<Weekly Coin Rankings>#k#l")
		if nSel == 0:
			sm.sendSayOkay("Here's how your #eLegion#n stacks up.\r\n\r\n#eLegion Tier: #n#b#e<" + sm.getUnionRankName()
						   + ">#n#k\r\n#eLegion Rank: #n#b#e<" + str(sm.getUnionLevel()) + ">#n#k\r\n#eLegion-eligible "
						  "Characters: #n#b#e<" + str(sm.getUnionCharacterCount()) + ">#n#k\r\n#eLegion Members: #n#b#e<"
						   + str(sm.getUnionAssignedCharacterCount()) + " / " + str(sm.getUnionAssignedMaxCharacterCount())
						   + ">#n#k")
		elif nSel == 1:
			if sm.sendAskYesNo("Are you here to advance your Legion #eto the next rank#n?\r\n\r\n#eCurrent Tier & Rank: "
							   "#n#b#e<" + sm.getUnionRankName() + ">#n#k\r\n#eNext Tier & Rank: #n#b#e<"
							   + str(sm.getUnionNextRankName()) + ">#n#k\r\n#eMax Legion Members after Rank-up:#n #b#e<"
							   + str(sm.getUnionAssignedMaxCharacterCount()) + " --> "
							   + str(sm.getUnionAssignedNextMaxCharacterCount()) + ">#n#k\r\n\r\nYou must meet the following "
							  "requirements to rank up:\r\n\r\n#e<Legion Rank> #r#e" + str(sm.getUnionLevelReq()) + "#n#k #n"
							  "\r\n#e<Coins Needed> #b#e#t4310229# x" + str(sm.getUnionCoinReq()) + "#n#k\r\n\r\n Do you want "
							  "to #eadvance#n your Legion to the next rank?"):
				if sm.getQuantityOfItem(4310229) < sm.getUnionCoinReq():
					sm.sendSayOkay("You need more #rLegion Coins#k to rank up. \r\n\r\n#eLegion Coins: #n#r" +
								   str(sm.getQuantityOfItem(4310229)) + "#k\r\n#eRequired Coins: #n#b"
								   + str(sm.getUnionCoinReq()) + "#k")
				elif sm.getUnionLevel() < sm.getUnionLevelReq():
					sm.sendSayOkay("Your #rCumulative Level#k must be higher to rank up.\r\n\r\n#eCumulative Level: #n#r"
								   + str(sm.getUnionLevel()) + "#k\r\n#eRequired Level: #n#b" + str(sm.getUnionLevelReq()) + "#k")
				else:
					if sm.hasItem(4310229, sm.getUnionCoinReq()):
						sm.consumeItem(4310229, sm.getUnionCoinReq())
						sm.incrementUnionRank()
						sm.sendSayOkay("(Claps enthusiastically)\r\n#eYour Legion has ranked up#n! We've approved you for "
									   "additional member slots.\r\n\r\n#eNew Rank:#n #b#e<" + sm.getUnionNextRankName()
									   + ">#n#k\r\n#eMax Legion Members:#n #b#e" + str(sm.getUnionAssignedMaxCharacterCount())
									   + "#n#k\r\n\r\nKeep up the hard work!")
					else:
						sm.sendSayOkay("An error occured, please try again.")
			else:
				sm.sendSayOkay("Come back and talk to me when you want to take your legion to the next rank.")

		elif nSel == 2:
			nSel = sm.sendSay("Did you have questions about the #bLegion System#k?\r\nWhat do you want to know?\r\n#L0# "
							  "#bWhat are Legions?#k#l\r\n#L1# #bCumulative Level#k#l\r\n#L3# #bAssigned Members and Raid "
							  "Power#k#l\r\n#L4# #bSynergy Grid and Character Blocks#k#l\r\n#L9# #bSynergy Grid presets#k#l"
							  "\r\n#L5# #bLegion Raids#k#l\r\n#L6# #bWeekly Coin Rankings#k#l\r\n\r\n#L100# #eNevermind.#n#l")
			if nSel == 0:
				sm.sendNext("#e<What is a Legion?>#n\r\n\r\nA #eLegion#n is a group made up of #ball of your characters "
							   "on a given world who meet certain requirements#k\r\n.Legion-eligible characters are those "
							   "#rLv. 60 or higher#k who have\r\n#rcompleted their 2nd Job Advancement#k\r\n.#rIf you have "
							   "more than 40 characters within a single world,#k only the #btop 40 highest level characters"
							   "#k will be eligible to join your Legion\r\n\r\n.#bZero#k characters must be #rLv. 130 or "
							   "higher#k, and only one may be a member of a given Legion.")
				sm.sendSayOkay("#e<Character Ranks>#n\r\n\r\n#eEligible Legion Members#n each possess a #bcharacter rank#k "
							   "that advances with their #rlevel#k\r\n\r\n.#e<Rank Breakdown>#n\r\n#b#eB (60) -> A (100) ->"
							   " S (140) -> SS (200) -> SSS  (250)#n#k\r\n#e<Zero's Rank Breakdown>#n\r\n\r\n#b#eB (130) -> "
							   "A (160) -> S (180) -> SS (200) -> SSS (250)#n#k")
			elif nSel == 1:
				sm.sendSayOkay("#e<Cumulative Level>#n\r\n\r\n#eCumulative Level#n is the #rtotal level#k of your "
							   "#bLegion-eligible#k characters\r\n\r\n.Increasing your Cumulative Level is one of the "
							   "requirements for\r\n#radvancing#k to a higher #bLegion Rank#k and #bTier#k, and increasing "
							   "your Legion's power as a consequence\r\n\r\n.You can check out #bLegion Rankings#k on the "
							   "MapleStory homepage as well.")
			elif nSel == 3:
				sm.sendNext("#e<Assigned Members and Raid Power>#n\r\n\r\n#bAssigned Members#k are Legion-eligible "
							   "characters placed on the #bSynergy Grid#k, and thereby assigned to the legion\r\n\r\n.Only "
							   "#bAssigned Members#k can participate in #rLegion Raids#k, battles against powerful opponents "
							   "which reward #bLegion Coins#k\r\n\r\n.Characters assigned to the Synergy Grid also activate "
							   "character-specific #bMember Bonuses#k based on their class, and\r\n#bGrid Bonuses#k based on "
							   "their occupied tiles. These stat bonuses are applied to #rall your characters within the "
							   "same world#k.")
				sm.sendSayOkay("#e<Assigned Members and Raid Power>#n\r\n\r\n#bLegion Raid Power#k is calculated based on "
							   "the #rlevels#k and #rStar Force#k values of assigned legion members\r\n\r\n.The higher your "
							   "total #bRaid Power#k, the greater the damage you inflict to enemies during #rLegion Raids#k, "
							   "which means collecting Legion Coins more quickly.")
			elif nSel == 4:
				sm.sendNext("#e<Synergy Grid and Character Blocks>#n\r\n\r\nThe #bSynergy Grid#k is a grid composed of a "
							   "#etotal of 16 areas#n: #b8 inner#k and #r8 outer#k areas. Each area represents a #bunique "
							   "stat#k. Characters can be placed on the grid to provide a bonus to the stats corresponding "
							   "to their positions. The bonus amount is determined by the #rnumber of occupied spaces#k per "
							   "area\r\n.The #b8 inner areas#k can be #brearranged#k any way you like. But the\r\n#rstats "
							   "of the 8 areas outside#k are fixed.")
				sm.sendNext("#e<Synergy Grid and Character Blocks>#n\r\n\r\nWhen you #edrag and drop#n a character to "
							   "the #bSynergy Grid#k, the character is displayed as a #bblock#k occupying space on the "
							   "grid\r\n.The #echaracter block#n's shape is determined by the character's\r\n#blevel#k and "
							   "#bjob type#k.")
				sm.sendSayOkay("#e<Synergy Grid and Character Blocks>#n\r\n\r\nThe first character placed on the #bSynergy "
							   "Grid#k must occupy #bone of the 4 central areas#k\r\n\r\n.Subsequent blocks must touch a "
							   "previous block. Blocks may overlap, although you only receive a given square's bonus once. "
							   "Characters blocks may be #bflipped or rotated#k as you like, or removed by #bright-clicking#k.")
			elif nSel == 9:
				sm.sendNext("#e<Synergy Grid Presets>#n\r\n\r\nDifferent roles call for different stat focuses. "
							   "Thankfully, you can save #bmultiple Synergy Grid configurations#k and switch between them "
							   "as needed! Want to swap from a warrior to a magic user? Bam! New preset, no problem!")
				sm.sendNext("#e<Synergy Grid Presets>#n\r\n\r\n#b#i2436884:##t2436884##k items, available for purchase "
							   "at the #rCoin Shop#k, are used #bactivate#k presets! You can activate #rup to 3 presets at "
							   "once#k These presets must be re-activated #bevery 30 days#k.")
				sm.sendSayOkay("#e<Synergy Grid Presets>#n\r\n\r\nOnce you have activated a preset, #bclick on its number "
							   "to select it#k. Then press #eEdit Preset#k. Place your character blocks however you like, "
							   "then press #rSave Preset#k to save your new preset configuration!\r\nTo apply a saved "
							   "preset, just #bclick the its number while in Edit Mode and hit confirm.#k")
			elif nSel == 5:
				sm.sendNext("#e<Legion Raids>#n\r\n\r\n#bLegion Raids#k are battles where you fight alongside the "
							   "members of your Legion\r\n.Press the #e<Start Raid> button#n in the Legion menu to join "
							   "your assigned legion members in battle.")
				sm.sendNext("#e<Legion Raids>#n\r\n\r\n#b2 types of enemies#k appear inside #bLegion Raids#k. Your "
							   "#eprimary#n opponent is a #bHuge Dragon#k that appears in the middle of the battleground"
							   "\r\n\r\n.Your #bLegion members#k automatically attack the enemy, and your own character "
							   "can assist them\r\n\r\n.However, after the #bgreen section#k of the #rHuge Dragon's#k HP "
							   "gauge is gone, it generates a #bprotective shield#k that is immune to damage from your "
							   "active character. \r\n\r\nOnly damage by the other #bLegion members#k can defeat it. Your "
							   "Legion members #bcontinue#k to fight the Huge Dragon and collect\r\n#bLegion Coins#k\r\n"
							   "\r\n.#bLegion Coins#k are #rcalculated when you exit#k the raid. Check in periodically to "
							   "claim the coins they collect.")
				sm.sendNext("#e<Legion Raids>#n\r\n\r\nThe #esecond#n type of foe you will face is the #bDragon Whelp#k "
							   "which guards the Huge Dragon. Dragon Whelps can only be injured by #rthe character you are "
							   "actively playing#k. Defeating them fulfills #bdaily quests#k. Sometimes a rare #bGolden "
							   "Wyvern#k appears when Dragon Whelps are defeated.")
				sm.sendSayOkay("#e<Legion Raid>#n\r\n\r\nYou get 1 #bLegion Coin#k for every #e100 billion#n damage dealt "
							   "to the #bHuge Dragon#k. The total damage you have dealt #bresets daily#k\r\n\r\n.It's a "
							   "good idea to collect your coins regularly, as they will stop accumulating when you reach "
							   "the coin #rlimit#k for each Legion rank.")
			elif nSel == 6:
				sm.sendNext("#e<Weekly Coin Rankings>#n\r\n\r\nThe #bWeekly Coin Rankings#k are calculated based on the "
							   "number of #bLegion Coins#k your Legion obtains every week. #bCalculations are performed "
							   "between Sunday 11:30 PM - Monday 12:30 AM#k.")
				sm.sendSayOkay("#e<Weekly Coins Rankings>#n\r\n\r\nYour ranking will appear under the #bname of the "
							   "character#k you\r\n#blast used to update your coin ranking#k\r\n\r\n.The following week, "
							   "the #rtop 100 ranked Legions#k will receive a\r\n#bspecial gift#k\r\n\r\n.It's a good idea "
							   "to update often, as your coins from #bdaily quests#k will also be counted.")
		elif nSel == 3:
			sm.sendSayOkay("TODO: Ranking")
