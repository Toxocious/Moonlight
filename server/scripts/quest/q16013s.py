#
# @author i baked yunos dick in my oven
# @npc Dame Appropiation - Legion Manager
# @quest [Legion] We Are Legion
#

DAME = 9010106
sm.setSpeakerID(DAME)
if sm.getUnionLevel() < 500 or sm.getUnionCharacterCount() < 3:
    sm.sendSayOkay("You're not ready to join a Legion, warrior. Legions are reserved for battle-hardened warriors with a "
           "#b#eCumulative Level#n of at least 500#k, and require #r#ea minimum of 3 characters#n#k.\r\n\r\n#eWhat "
           "is Cumulative Level?#n\r\n#bYour Cumulative Level is the combined level of all your characters on a given "
           "world #rwho are Lv. 60+ and have completed the 2nd Job Advancement#k. If you have 40 or more characters, "
           "the only your 40 highest level characters will be counted. However, #rZero#k is a special case. Only your"
           " highest level Zero will be counted.")
elif not sm.hasQuest(16013) and not sm.hasQuestCompleted(16013):
    sm.setQRValue(18793, "q0=1q1=0pq=0q2=0q1Date=" + sm.getCurrentDateAsString() + "pqDate=" + 
                  sm.getCurrentDateAsString() + "q2Date=" + sm.getCurrentDateAsString()) # Legion quest
    sm.incrementUnionRank()
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
