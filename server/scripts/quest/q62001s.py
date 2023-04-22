# [Shaolin Temple] Intercepted Messages

ELDER_JUNG = 9310049
WISE_CHIEF_PRIEST = 9310053

sm.removeEscapeButton()
sm.setSpeakerID(ELDER_JUNG)
sm.setBoxChat()
sm.sendNext("While the outside world moves and sways to its own rhythm, things here in the depths of Mount Song stay the same. "
            "Every tree, every blade of grass, we are still.")

sm.sendNext("But disturbing rumors have arisen lately... Of demons and petty theft. By which I mean, demons stealing children's shoes.")

sm.sendNext("I knew things had truly gone amiss when I saw the wild-haired Nine-Tailed Fox lurking in the woods.")

sm.sendNext("I've sent several messages tot he Shaolin Temple. "
            "It's their job to protect Mount Song, after all. But bandits keep intercepting the messages.")

response = sm.sendAskYesNo("That is why I sent for you... "
                "Will you retrieve my messages from the bandits and deliver them to my old friend, the #r#p"+ str(WISE_CHIEF_PRIEST) +"##k of Shaolin Temple?")

if response:
    sm.sendNext("Thank you. I will be here meditating until you return with good news.")
    sm.startQuest(parentID)