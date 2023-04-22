# id 2439170 (Mastery Box), field 863100003
sm.setSpeakerID(9010000) # Maple Administrator
sm.setParam(4)
sm.setInnerOverrideSpeakerTemplateID(2080009) # Illiad
res = sm.sendNext("What do you wish to do?\r\n #L0# Claim #i4000999##t4000999# x500#l\r\n #L1# Use #i2431935##t2431935##l\r\n #L2# Use #i2431936##t2431936##l")
sm.sendSayOkay("Either you haven't learned any 4th job skills, or you don't have any skills to use a book on. Check again.")
