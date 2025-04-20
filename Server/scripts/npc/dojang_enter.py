# So Gong (2091011) | Mu Lung Dojo Hall

dojoHall = 925020001

destinations = [
    ["Easy", 70, 925070100],
    ["Normal", 100, 925070200],
    ["Hard", 130, 925070300],
    ["Hell", 160, 925070400],
    ["Chaos", 160, 925070500],
    ["Crazy", 220, 925070600],

]

if sm.getFieldID() == dojoHall:
    selection = sm.sendNext("My master is the strongest person in Mu Lung, and YOU wish to challenge HIM? I have a feeling you'll regret this.\r\n#b"
                "#L0#I'd like to challenge Mu Lung Dojo.#l\r\n"
                "#L1#I'd like to distribute my points to someone else.#l\r\n"
                "#L2#I'd like to stop distributing my dojo points.#l\r\n"
                "#L3#I'd like to see who is distributing points to me.#l\r\n")

    if selection == 0:
        if sm.getFieldID() == dojoHall:
            def is_party_eligible(reqlevel, party):
                for member in party.getMembers():
                    if member.getLevel() < reqlevel:
                        return False

                return True

            sm.sendSayOkay

            dialog = "Please select a difficulty.\r\n"

            for i in range(len(destinations)):
                dialog += "#L%d##b%s Mode (Lv. %d+)#l\r\n" % (i, destinations[i][0], destinations[i][1])

            dialog += "#L99#Never mind."
            response = sm.sendSay(dialog)

            if sm.getParty() is None:
                sm.sendSayOkay("Please create a party before going in.")

            elif not sm.isPartyLeader():
                sm.sendSayOkay("Please have your party leader talk to me if you wish to challenge Mu Lung Dojo.")

            elif sm.checkParty() and response != 99:
                if is_party_eligible(destinations[response][1], sm.getParty()):
                    sm.warpInstanceIn(destinations[response][2], True)

                else:
                    sm.sendSayOkay("One or more party members are below level %d." % destinations[response][1])


    elif selection == 1:
        charName = sm.sendAskText("Who is it you would like to distribute your dojo points to?", "", 4, 20)
        percentage = sm.sendAskNumber("What percentage of your dojo points would you like to distribute to them?", 0, 1, 100)
        sm.addDojoLeader(charName, percentage)
        sm.sendNext("You are now sharing #r" + str(percentage) + "#b%#k of your points with #b" + str(charName) + ".\r\n#b")
    elif selection == 2:
        sm.removeDojoLeader()
        sm.sendNext("You are no longer sharing your points.\r\n#b")
    elif selection == 3:
        sm.sendNext(sm.getDojoContributorsList())
    elif selection == 4:
        sm.warpInstanceIn(706041650)
    elif selection == 99:
        sm.dispose()

elif not sm.hasMobsInField():
    chr.chatMessage("Please wait till the mob has spawned to leave the map.")
elif sm.hasMobsInField():
    sm.sendAskYesNo("Would you like to leave?")
    sm.warpInstanceOutParty(925020001)