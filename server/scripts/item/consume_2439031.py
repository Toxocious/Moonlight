# id 2439031 (Elva's Potion Notebook), field 867202300
sm.setSpeakerType(3)
sm.setParam(36)
sm.setColor(1)
sm.setInnerOverrideSpeakerTemplateID(9400601) # Elva
res = sm.sendNext("#e[Elva's Special Potion Recipe Notebook]#n\r\n#L9# Special Super Elixir#l")
res = sm.sendNext("#eSpecial Super Elixir#n\r\nInstantly increases stamina and energy!\r\n\r\n#e1. Ingredients#n\r\n  1) Eyeful antennae\r\n  #L0# [Habitat] Lives beneath the forest in tunnels. Attracted to shiny objects, particularly treasure.#l\r\n      [Effects] Strengthens the muscles and bones. Improves vitality.\r\n  2) Optusa leaves\r\n  #L1# [Habitat] Found near holes where warm air collects. #l\r\n      [Effects] Warms the body and calms the cardiovascular system.\r\n  3) Leatty Crystals\r\n  #L2# [Habitat] Follows snowstorms. Often found near yeti habitats or along frozen rivers.#l\r\n      [Effects] Speeds up natural healing processes.\r\n  4) Clear Actinops Blood \r\n  #L3# [Habitat] Lives near local lakes and rivers, but difficult to find due to its transparency.#l\r\n      [Effects] Reduces inflammation and helps wounds heal. Contains toxins. May cause paralysis if consumed excessively.\r\n#e2. Instructions#n\r\n  1) Roast the Optusa leaves over low heat.\r\n  2) Melt down 10 Leatty Crystals and mix thoroughly with 10ml of Actinops blood. Add the Optusa leaves and bring to a boil over low heat.\r\n  3) Add 20 Eyeful antennae once boiling. Boil until the antennae dissolve.")
sm.setParam(57)
sm.sendNext("#b(Wonder if people can really eat this stuff...) ")
sm.sendSay("#b(Well, she did ask for them.) ")
sm.createQuestWithQRValue(64114, "chk1=1")
sm.setParam(56)
res = sm.sendNext("#bIf I'm going to hunt Eyefuls, I should keep a close eye on my valuables.\r\n \r\n#L11# 'I'd better get going.' (You will be moved automatically once you accept.)#l\r\n#L12# Let's head out a little later.#l")
sm.warp(867202820)
