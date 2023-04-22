#   [Job Adv] (5th job)   5th Job : Call of the erdas

sm.setSpeakerID(2140001)

if "1" == sm.getQRValue(parentID):
    sm.sendNext("#h #... I've heard many great things about you. \r\n\r\n I wanted to seee you today to tell you about the strange phenomenon that is taking place in this world. \r\n\r\n #L0##bWhat is it?#l")
    sm.sendNext("Have you heard about the energy that forms this world, #eErda#n? \r\n\r\n #L1##bErda?#l")
    sm.sendNext("Erda continuously comes into existence and perishes, forming and supporting this world. It's not just Maple World where Erda can be found, but also many other worlds in different dimensions. Long before you setpped into this world for the first time, Erda had existed in the soil and trees and in the light and darkness. \r\n\r\n #L2##bIt sounds important.#l")
    sm.sendNext("It is. Without erda, this world can't exist. A while ago, I've noticed this important Erda is vanishing little by little. \r\n\r\n #L3##bIt's vanishing?#l")
    sm.sendNext("You look doubtful. Seeing is believing. See for yourself, and your perspective of this world will change.")
    if sm.sendAskYesNo("If you don't mind, I can help you see the flow of Erda. Now, close your eyes... \r\n\r\n #b(Select Yes to follow the History Observer's instructions and concentrate your mind.)"):
        sm.warpInstanceIn(450000200)

elif "2" == sm.getQRValue(parentID):
    sm.sendNext("Do you see now? Do you understand the importance of the Erdas? \r\n\r\n #L4##bI... talked to them.#l")
    sm.sendNext("You spoke to the Erdas? Can this be true? I have observed the Erdas my whole life, but never have I communicated with them in any way.")
    sm.sendNext("If the Erdas spoke to you... Then you have a grander fate than I suspected. The Erdas wish to give you their power, so that you can protect them. \r\n\r\n #L5##bThat sounds sweet! How does that work?#l")
    sm.completeQuest(parentID)