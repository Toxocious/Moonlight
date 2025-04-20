from net.swordie.ms.enums import UIType

HERBALISM_SKILL = 92000000

if sm.hasSkill(HERBALISM_SKILL):
    sm.openUI(UIType.UI_MAKING_SKILL)
else:
    sm.systemMessage("Only Herbalists can use this.")