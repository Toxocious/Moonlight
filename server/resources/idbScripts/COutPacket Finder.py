from idautils import *
from idaapi import *
from idc import *
from struct import *
from re import *

idb_version = 207
idb_sub_version = 1
coutpacket_ea_for_v = 11521424  # v205.1

print "Running COutPacket Function finder."
print "===+  Created by SwordieDevs  +===\n\n\n"
try:
    coutpacket_ea = AskAddr(coutpacket_ea_for_v, "Please enter the address of the COutPacket::COutPacket function."
                                                 "\nAuto filled address of COutPacket::COutPacket for "
                                                 "v" + str(idb_version) + "." + str(idb_sub_version))
    cp_value = AskLong(0, "Which OutPacket value do you want to search for?")
    result_list = []
    if cp_value < 0:
        print "invalid value."

    else:
        print "Searching for the CP value: " + str(cp_value) + "."
        pushedValue = "68 " + str(to_little_endian(cp_value))

        for found_ea in find_all_addr_by_aob(pushedValue):
            found = False
            start_ea = found_ea
            for i in range(5):
                if get_called_addr(start_ea) == coutpacket_ea:
                    found = True
                    break
                start_ea = next_head(start_ea)

            if found:
                result_list.append(found_ea)
        if len(result_list) <= 0:
            print "No results found with the CP value: " + str(cp_value) + "."
        else:
            print str(len(result_list)) + " result(s) found for the CP value: " + str(cp_value) + "."
            for result_ea in set(result_list):  # distinct entries
                print "" + get_function_name(result_ea) + " (" + str(hex(get_func_start_addr_by_addr(result_ea))) + ")"
            print "\nEnd of the result list."

except AttributeError:
    print "[Error] Please make sure you have the Swordie ida plugin."
