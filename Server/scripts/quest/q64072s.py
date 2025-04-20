# id 64072 ([MONAD: The First Omen] Into the Forest), field 867201100
sm.setSpeakerType(3)
sm.setParam(57)
sm.setColor(1)
sm.sendNext("#b(Now that I know where the cabin is, I should head out soon.)")
res = sm.sendNext("#b(In the end, perhaps it would be best to just get going right away.)\r\n#L0# 'I'd better get going.'#l")
sm.startQuest(parentID)
sm.warp(867201050)
