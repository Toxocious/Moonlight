# id 37151 ([Elodin] Anne's Plea for Help), field 101082000
sm.setSpeakerID(1012110) # Anne
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1012110) # Anne
sm.sendNext("#fs10#Hello? Excuse me...")
sm.setParam(2)
sm.sendSay("Who just called me?")
sm.setParam(4)
sm.sendSay("You can hear me? Good!")
sm.sendSay("I'm Anne! I'm here in Ellinia with my mom, Dr. Betty, while she conducts research.")
sm.sendSay("But I need some help. Would you hear me out?")
res = sm.sendAskYesNo("If you're willing to help, please visit me.\r\n#r(If you accept, you'll automatically travel to Anne in Ellinia.)#k\r\n\r\n#b(The #rSecret Forest of Elodin#b is a special theme dungeon. It has a max level of #rLv. 59#b, and quest EXP and monsters near your level will be provided accordingly.)")
sm.startQuest(parentID)
sm.setParam(5)
sm.sendNext("I'm waiting for you here in Ellinia.")
sm.warp(101000000)
