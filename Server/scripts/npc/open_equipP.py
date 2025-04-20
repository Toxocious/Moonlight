from net.swordie.ms.enums import UIType

SMITHING_CRAFT_SKILL = 92020000

if sm.hasSkill(SMITHING_CRAFT_SKILL):
    sm.openUI(UIType.UI_MAKING_SKILL)
else:
    sm.systemMessage("Only Blacksmiths can use this.")

