# Beauty Salon Tutorial
if sm.sendAskAccept("Hi! I'm Big Headward, the heart of fashion in Maple World. I would just LOVE to explain how my amazing Beaty Salon system works. Do you think you have a moment to hear about it?"):
    sm.removeEscapeButton()
    sm.sendNext("The Beauty Salon is where you can save or change your hair and face, any time you wish. See, isn't it amazing?")
    sm.sendSay("Those that are quick-witted might have already noticed the newly-added button in the Equip tab. Just like the picture below!\r\n#i3800642#")
    sm.sendSay("It is SO easy to use. You can #rSave#k your current Hair (Face), then use the #rChange#k button to mix it up! You can even #rDelete#k any style that you don't like. But once it is deleted you won't get it back so BE CAREFUL!\r\n#i3800643#")
    sm.sendSay("You can save up to 3 sets of faces and hairstyles. You can use NX to get more slots, too. That way you can collect all the amazing looks of MapleStory!\r\n#i3800644#")
    sm.sendSay("Rather than have me explain it a hundred times, why don't you try it out yourself? Here, I'll give you a coupon for a look that I created just for this. Give it a try right now.")
    if sm.sendAskAccept("Now that you've learned all about the Beauty Salon, would you like to try using these style coupons?\r\n#e#rMake sure you save your current style in the salon before you proceed!#k#n"):
        sm.giveItem(2543041)
        sm.giveItem(2432547)
        # The items should be expiration for 24hrs
        sm.completeQuestNoRewards(52930)
        sm.dispose()
    else:
        sm.sendSayOkay("Come back with the character you want to have the hot new look, and listen to the explanation.")
        sm.dispose()
else:
    sm.sendSayOkay("Come back to me whenever you would like to learn about it. I even have a special gift prepared for patient, attentive people, so don't miss out!")
    sm.dispose()