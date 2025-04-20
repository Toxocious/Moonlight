# id 3003208 (Protective Mask), field 450004000
sm.setSpeakerType(3)
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(3003208) # Protective Mask
res = sm.sendNext("#e<Boss: Lucid>#n\r\nSomething terrible will happen if Lucid is not stopped.\r\n#b \r\n#L0# I want to enter Boss: Lucid (Story).#l \r\n#L1# I want to enter Boss: Lucid (Normal).#l \r\n#L2# I want to enter Boss: Lucid (Hard).#l \r\n#L3# I want to enter Boss: Lucid (Normal) Practice Mode.#l \r\n#L4# I want to enter Boss: Lucid (Hard) Practice Mode.#l")
sm.warp(450004700)
