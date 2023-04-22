# Limbert's General Store (913070001)

# Constants
QUEST = 20031

if  not sm.hasQuest(QUEST) or not sm.hasQuestCompleted(QUEST):
    sm.lockInGameUI(True)
    sm.chatScript("Mr. Limbert's General Store")
    sm.chatScript("Month 3, Day 7")
    sm.progressMessageFont(3, 20, 20, 0, "Click on the lightbulb to start the quest. Quest Status Hotkey [Q] / Secondary Key [J]")
    sm.sendDelay(1500)
    
    sm.avatarOriented("UI/tutorial.img/34")
    sm.sendDelay(1200)

    sm.lockInGameUI(False)