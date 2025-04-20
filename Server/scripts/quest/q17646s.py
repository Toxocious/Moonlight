# id 17646 ([Commerci Republic] Outlaw of the Sea), field 865010001
sm.setSpeakerID(9390244) # Leon Daniella
res = sm.sendAskYesNo("#h0#! Help me! Save my life!")
sm.setParam(5)
sm.setInnerOverrideSpeakerTemplateID(9390202) # Leon Daniella
sm.sendNext("Everybody calm down! Relax your core!")
sm.setParam(3)
sm.sendSay("Are you all right Leon? Oh, GROSS, there's kraken juice everywhere!")
sm.setParam(1)
sm.sendSay("Grosso Polpo's no joke! It ate my dad's favorite fruit barge last year. Don't let your guard down!")
sm.startQuest(parentID)
sm.startQuest(parentID)
