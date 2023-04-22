from net.swordie.ms.enums import UIType

MINING_SKILL = 92010000

if sm.hasSkill(MINING_SKILL):
    sm.openUI(UIType.UI_MAKING_SKILL)
else:
    sm.systemMessage("Only Miners can use this.")
