from net.swordie.ms.enums import UIType

ALCHEMY_CRAFT_SKILL = 92040000

if sm.hasSkill(ALCHEMY_CRAFT_SKILL):
    sm.openUI(UIType.UI_MAKING_SKILL)
else:
    sm.systemMessage("Only Alchemists can use this.")
