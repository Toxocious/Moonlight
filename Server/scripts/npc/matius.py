# Matthias
if sm.hasQuest(20807):
   if sm.sendAskYesNo("Would you like to try the first test?"):
       sm.warp(913070800, 0)
       sm.createClockForMultiple(300, 913070800)
       sm.addEvent(sm.invokeAfterDelay(300 *1000, "warp", 913070800, 0))