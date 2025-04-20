# id 61133 (Blackgate's New Gate), field 101070000
sm.setSpeakerID(9201428) # Angel
sm.sendNext("We need all the young, tough heroes we can get in the Blackgate Defense Force.")
sm.sendSay("#i3800847#\r\nBlackgate is a hub of industry and science, but these demons are putting a real crimp in our work.")
res = sm.sendAskYesNo("The BDF wants YOU!")
sm.sendNext("#i3800849#\r\nCome to Blackgate City if you want to help drive out the invaders and reclaim our city.")
sm.sendSay("You can use the Dimensional Mirror to come to Blackgate City whenever you can.")
sm.startQuest(parentID)
