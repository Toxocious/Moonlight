from net.swordie.ms.enums import UIType

ACCESSORY_CRAFT_SKILL = 92030000

if sm.hasSkill(ACCESSORY_CRAFT_SKILL):
    sm.openUI(UIType.UI_MAKING_SKILL)
else:
    sm.systemMessage("Only Jewelers can use this.")
