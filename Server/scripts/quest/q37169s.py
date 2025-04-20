# id 37169 ([Elodin] Thirst Quenching), field 101084400
sm.setSpeakerID(1501004) # Shimmer Songbird
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(1501015) # Shimmer Songbird
sm.sendNext("Cough...")
sm.sendSay("I'm sorry. Please excuse me. My throat's been a bit sore ever since... Nevermind.")
sm.setParam(2)
sm.sendSay("Can I help you with something?")
sm.setParam(4)
sm.sendSay("Actually, you can! Would you bring me #i4036505# #r#t4036505##k?")
res = sm.sendAskYesNo("The #b#o3501108#s#k and #b#o3501109#s#k nearby have some. I'd appreciate it if you could fill this small bottle and come back.")
sm.startQuest(parentID)
sm.startQuest(parentID)
sm.sendNext("Thank you! I think #r9 droplets of Pure Water#k should be enough. \r\n#rDouble-click to open the #i4220196:# #b#t4220196:##k, then drag the #i4036503:# #b#t4036503:##k over to fill it.#k")
sm.warp(101084000)
