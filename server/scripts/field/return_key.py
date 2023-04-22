# Sengoku Era Questline | Master Room (811000008)
# Author: Tiger

SUKUNO = 9130124
AYAME = 9130100

if sm.hasQuest(58908) or sm.hasQuestCompleted(58908):
    sm.lockInGameUI(False)
    sm.hideNpcByTemplateId(9130104, False, False) # respawns princess sakuno's npc
else:
    sm.hideNpcByTemplateId(9130104, True, True) # removes princess sakuno upon entering map
