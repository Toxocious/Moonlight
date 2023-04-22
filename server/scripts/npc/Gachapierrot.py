# The Great Gachapierrot

from net.swordie.ms.world.gach.result import GachaponDlgType

answer = sm.sendNext("Try out the new and improved Great Gachapierrot! Which service would you like to use ?\r\n#b#L0#Use a Gachapon Ticket.#l\r\n#L3#Mount Gachapon Ticket Exchange.#l#k\r\n#r#L4#Use a Powergacha Ticket(Event Quest).")

if answer == 0:
    sm.sendGachaponDlg(GachaponDlgType.TOWN)
elif answer == 1:
    sm.sendGachaponDlg(GachaponDlgType.CHAIR)
elif answer == 2:
    sm.sendGachaponDlg(GachaponDlgType.NEBULITE)
elif answer == 3:
    sm.sendGachaponDlg(GachaponDlgType.MOUNT)
elif answer == 4:
    sm.sendGachaponDlg(GachaponDlgType.SPECIAL)
