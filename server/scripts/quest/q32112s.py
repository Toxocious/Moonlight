# id 32112 ([Ellinel Fairy Academy] Clue Number One), field 101072400
sm.setSpeakerID(1500021) # Hidey Hole
if sm.sendAskAccept("There's something weird over here. Should we check it out?"):
    sm.setParam(2)
    sm.sendNext("#i4033828# \r\n\r\n(You found... a stageplay. Did the kids write this thing? It's like 300 pages long! You'd probably better go ahead and read it... for research...)")
    sm.setParam(4)
    sm.setInnerOverrideSpeakerTemplateID(1500022) # Fairy Stageplay
    sm.sendSay("[Fairy Stageplay]\r\n\r\n- Act 3 -\r\n\r\n[The curtain rises as a solemn tune fills the air.]\r\n\r\nPHANTOM: (to audience) This world is a world of sadness and sorrow, and also sadness! I warn thee, commander of evil and friend to the Black Mage, I will never allow you to rest in peace, except when I make you rest in peace! I have stolen gems and masterpieces from millions of fancy people, but the last thing I will take... IS YOUR LIFE!")
    sm.setParam(2)
    sm.sendSay("This is amazing... I've got to read more")
    sm.setParam(4)
    sm.sendSay("[Fairy Stageplay]\r\n\r\nARAN: (Brave and wistful at the same time) Dear guardian of light, travel faster than light! I will ward off the evils of the enemy with my tornado arms!\r\nLUMINOUS: (Super lamenty) I swear by the mightiest of all the gods of time and light powers that I will defeat the Black Mage with my ultra light magic before your weapon can destroy its thousandth and one enemy! \r\n\r\n[The lights dim and Freud and Mercedes appear, stage left]\r\n\r\nFREUD: Oh dearest, fairest, cutest queen of elves! You are so pretty that I want to kiss your feet and then brush your hair! My honor is exploding with extra power from fighting at your side!\r\nMERCEDES: Oh, most dragonest of Dragon Masters, I will be proud to fight at your side, with my awesome hair flowing in the wind from your sweet dragon! The Black Mage will fall before us!")
    sm.setParam(2)
    sm.sendSay("Why in the world were the kids hiding this thing? I want to read more, but I have to show Cootie.\r\n(Talk to #b#p1500011##k.)")
    if sm.canHold(4033828):
        sm.giveItem(4033828)
        sm.startQuest(parentID)
    else:
        sm.sendNext("You can't pick up the Fairy Stageplay item, please make some space in your inventory.")
