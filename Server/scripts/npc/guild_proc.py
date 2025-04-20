from net.swordie.ms.constants import GameConstants

guild = chr.getGuild()

if chr.isGuildMaster():
    if guild.getMaxMembers() < GameConstants.MAX_GUILD_MEMBERS:
        if sm.sendAskYesNo("Would you like to increase your guild's max capacity? You currently have "
                        + str(guild.getMaxMembers()) + " members, but I can increase it by 10 for 500,000 mesos."):
            if sm.getMesos() < 500000:
                sm.sendSayOkay("You do not have enough mesos.")
            else:
                sm.incrementMaxGuildMembers(10)
                sm.deductMesos(500000)
    else:
        sm.sendSayOkay("Be sure to come back if you change your mind!")

elif guild is None:
    if sm.sendAskYesNo("Would you like to create a guild? This will cost 5 million mesos."):
        if sm.getMesos() < 5000000:
            sm.sendSayOkay("You do not have enough mesos.")
        else:
            sm.showGuildCreateWindow()
else:
    sm.sendSayOkay("Hey there, I'm in charge of everything guild related. If you want anything done to your guild, "
                   "you can ask your guild master to come to me.")
