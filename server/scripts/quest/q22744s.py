# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association

selections = ["#L1#What is the Magician Association?#l", "#L2#Why did you bring me here?#l", "#L3#What do you know about my powers?#l", "#L4#Who is the White Mage and what does he want?#l", "#L5#How bad is the damage from the sinkhole?#l", "#L6#Are Yuna and Jay safe?#l", "#L7#I have nothing else to ask about.#l"]
def removeSelText(sel):
    for i in range(len(selections)):
        label = "#L" + str(sel) + "#"
        if label in selections:
            selections.remove(selections[i])

KINESIS = 1531000
NERO = 1531003
THREE_MOON = 1531004
OLD_MAN = 1531012

sm.setIntroBoxChat(OLD_MAN)
sm.sendNext("You must be Kinesis. I am Grendel the Really Old, president of the Magician Association.")


sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face2#Oh man, thank goodness. Maybe you can help me get all this straight...")


sm.setIntroBoxChat(OLD_MAN)
sm.sendSay("I imagine so. I just came from that enormous sinkhole you were in.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face0#Wait, what? Sinkhole? So... I wasn't imagining that?")

sm.setIntroBoxChat(OLD_MAN)
sm.sendSay("Indeed. I will be completely honest with you. The White Mage of your world used your powers to create a sinkhole in your city.")

sm.setIntroBoxChat(KINESIS)
sm.sendSay("#face0#...!!")

sm.setIntroBoxChat(OLD_MAN)
sm.sendSay("By the stunned look on your face, I assume you have many questions. I will answer them as best I can.")



while len(selections) != 0:
    text = "What else are you curious about?\r\n#b\r\n"
    for i in range(len(selections)):
        text += selections[i] + "\r\n"

    selection = sm.sendNext(text)
    if selection == 1:
        sm.setIntroBoxChat(OLD_MAN)
        sm.sendNext("Hundreds of years ago, the magicians of Maple World learned a hard lesson when the evil Black Mage ravaged our lands. Knowledge of magic must be shared and preserved if we are to survive the many threats to our world.")
        sm.setIntroBoxChat(KINESIS)
        sm.sendSay("#b(Black Mage? Sounds even more evil than the White Mage. Wonder if they're related?)")
        sm.setIntroBoxChat(OLD_MAN)
        sm.sendSay("Therefore, the magicians of Maple World came together to create an organization where their research can be shared with all. That organization is the Magician Association.")
        sm.sendSay("I have many apprentices, and the child you call Nero is one of them.")
        sm.sendSay("Once we became aware of your world, we sent magicians to keep tabs on it. Nero was chosen to watch over your city.")
        sm.setIntroBoxChat(NERO)
        sm.sendSay("#face2#Uh, sir? You just called me Nero... ")
        removeSelText(selection)
    elif selection == 2:
        sm.setIntroBoxChat(OLD_MAN)
        sm.sendNext("We had no choice. As long as you remained in the sinkhole, it would have continued to grow. We're all lucky we pulled you out when we did.")
        sm.sendSay("It took the combined powers of all the Association summoners to get you here, but we managed. The sinkhole ceased its expansion the moment you arrived in Maple World.")
        sm.sendSay("Once you can control your powers completely, it will be safe for you to return. Until then, I'm afraid you must stay here. For your safety, and the safety of your friends.")
        removeSelText(selection)
    elif selection == 3:
        sm.setIntroBoxChat(OLD_MAN)
        sm.sendNext("In truth, we are just as curious about your powers as you are. Your psychokinesis is extremely rare here, despite how common magic is.")
        sm.sendSay("Even stranger is how you could have developed this ability when your world seems to lack even the most basic magic. Strange indeed...")
        sm.sendSay("But whatever the case, we know one thing for sure. The White Mage can seize control of your powers, and push them to untold heights.")
        sm.sendSay("That part we can help you with. Nero was the one who helped you control yourself after you lost consciousness at the school.")
        sm.setIntroBoxChat(NERO)
        sm.sendSay("#face0#Hee hee. I specialize in Restraining Magic. You can call me Nella Medionel Roonia, Paragon of Restraint!")
        removeSelText(selection)
    elif selection == 4:
        sm.setIntroBoxChat(OLD_MAN)
        sm.sendNext("You will come to learn of this in your travels, but our White Mage is someone who did the unthinkable here in Maple World several hundred years ago.")
        sm.sendSay("Your world and Maple World are somehow related. Something that happens here will be mirrored in your world, though a little differently.")
        sm.sendSay("If your White Mage is taking such drastic action, you may be facing a threat similar to the one that forever changed Maple World.")
        removeSelText(selection)
    elif selection == 5:
        sm.setIntroBoxChat(OLD_MAN)
        sm.sendNext("I think it best not to dwell on that right now. You will see the extent for yourself once you are ready. I will say that the damage will take time to recover from.")
        sm.sendSay("However, the laws of physics no longer apply around the sinkhole, making it impossible to address the damage.")
        sm.sendSay("Thousands of people are missing, but no details are known. It's too early to jump to any conclusions.")
        sm.sendSay("You should train to return to your world swiftly, but please, do not blame yourself. If anything we are to blame, for not stopping the White Mage in time.")
        removeSelText(selection)
    elif selection == 6:
        sm.setIntroBoxChat(OLD_MAN)
        sm.sendNext("They are all safe. The sinkhole did not reach your residences.")
        sm.sendSay("They are, however, very concerned for your safety.")
    elif selection == 7:
        sm.setIntroBoxChat(OLD_MAN)
        sm.sendNext("I wish that I could send you back immediately, but the risk is simply too great.")
        sm.sendSay("Unless you have complete mastery of your powers, the sinkhole will draw from them and continue to expand.")
        sm.sendSay("Stay here and train. That is the best thing you can do for yourself right now.")
        sm.sendSay("This isn't much, but you should take it. Your shoes won't last long in this world, I think. They don't look appropriate for the sand and mud and rocks.\r\n\r\n#b#i1072019# #t1072019#")
        sm.giveItem(1072019)
        sm.startQuest(parentID)
        sm.completeQuest(parentID)
        sm.giveExp(4700)
        sm.dispose()