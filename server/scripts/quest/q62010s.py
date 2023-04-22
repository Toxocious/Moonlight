# [Shaolin Temple] Wiping Up Demons

CHENGXIN = 9310047
SILVER_GIANT = 9480027
SILVER_SPEARMAN = 9480029

sm.removeEscapeButton()
sm.setSpeakerID(CHENGXIN)
sm.setBoxChat()
sm.sendNext("Ah, ha! My support unit has arrived. On the double, defeat those demons!\r\n"
            "150 #r#o"+ str(SILVER_SPEARMAN) +"##k monsters and 100 #r#o"+ str(SILVER_GIANT) +"##k monsters!")
sm.startQuest(parentID)