#
# author bakery shutdown halp
# npc Dame Appropiation - Legion Manager
# quest [Legion] Introducing Legion
#
DAME = 9010106
sm.setSpeakerID(DAME)
if chr.getLevel() < 60:
    sm.sendSayOkay("Characters must be #bLv. 60 or higher#k to carry out Legion tasks.")
else:
    sm.completeQuestNoRewards(16059)
    sm.sendNext("Hello! Good to see you again.\r\nYou're over level 60 now!")
    sm.setPlayerAsSpeaker()
    sm.sendSay("Have we met?")
    sm.setSpeakerID(DAME)
    sm.sendSay("My name is #b#eMs. Appropriation#n#k. \r\nI'm working on a #rnew project#k to help Maple World heroes over #rlevel 60#k grow.")
    sm.sendSay("This project lets me give out #e#rgreat rewards#k#n! \r\nShall I tell you about it?")
    sm.sendSay("After a long journey, our cruise ship ran aground on an #buncharted island#k full of #rterrible dragons#k.\r\nAfter the captain and crew were dragged off into the jungle and devoured, we passengers realized that there was only one way we were getting off the island alive.")
    sm.sendSay("We took up whatever arms we could find. Pots and pans, knives, and the odd broadsword from somebody's luggage. When the dragons returned to carry us off, we charged forward together and beat them to death!")
    sm.sendSay("We ate well that night. And while we sat around the campfire, thinking of our fallen comrades, I reached an epiphany.\r\n\r\nSome among us were seasoned warriors, but just as many were flabby tourists. The only reason we survived is because we banded together.")
    sm.sendSay("Suddenly, I knew!\r\nI figured out why we couldn't grow any stronger!")
    sm.sendSay("Nothing is gained by going it alone. But working as a group, the strengths of one compensate for the weaknesses of another.\r\n\r\n#bWhen people work together#k, even a pudgy sightseer can take down a giant dragon.")
    sm.sendSay("After a few months on the island, we managed to capture and tame enough dragons to fly the remaining survivors to safety. And when I returned to civilization, I knew exactly what I had to do. \r\nFirst, I told my boss to suck an egg, and I quit. Then I started my #bgrand new project#k.")
    sm.sendSay("And that project is the #b#eLegion System!#n#k I'm going to help all those sad, weak, and flabby Maplers reach their true potential by pairing them with other warriors!\r\n\r\nThe end result? Everybody makes mad gains and gets swole like me! Oh and they'll probably level up faster.")
    sm.sendSay("#h0#! Don't you want to put together a #b#eLegion#k#n of swole bros to punch dragons in the face and unlock stat bonuses?")
    sm.sendSay("Give me just a moment. \r\nI'll make preparations to create a Legion!")