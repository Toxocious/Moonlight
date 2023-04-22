# Portal for Rakeda Shingen (Sengoku Era) Questline | Near Momiji Hills 1 (811000001)
# Author: Tiger

if sm.hasQuestCompleted(58907): # has just completed Mouri's Quest
    if sm.hasQuestCompleted(58908): # has already meet Ayame
        sm.warp(811000008)
    else:
        sm.warpInstanceIn(811000007, 0) # Momiji Hills (First Meet Ayame)
else:
    sm.warp(811000001) # Momiji Hills : Near Momiji Hills 1
