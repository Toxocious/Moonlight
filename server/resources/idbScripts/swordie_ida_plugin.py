from idautils import *
from idaapi import *
from idc import *
from struct import *
from re import *
import os

print "Starting swordie_ida_plugin"
print "Loading in Swordie idapy methods"

START_ADDR = get_inf_attr(INF_MIN_EA)
END_ADDR = get_inf_attr(INF_MAX_EA)
encoding = 'utf-8'
errortype = 'strict'
idbVersion = get_root_filename().split("-")[0]
idbSubVersion = get_root_filename().split("-")[1].split(".")[0]
path = os.getcwd()
txtFileName = "%s-%s.txt" % (idbVersion, idbSubVersion)
completePath = os.path.join(path, txtFileName)
print("Detected version " + idbVersion + ", path = " + path)
stringPoolStringsDict = {}
onPacketDict = {}  # <OpValue, Tuple(OnPacket FuncName, Function Name)>

# Add StringPoolStrings to Dictionary

f = None
for file in os.listdir(path):
    if idbVersion in file and file.endswith(".h"):
        print("Opening file " + file)
        f = open(file, "r")

if f is None:
    print("Could not find a header file for this version.")
else:
    for line in f.readlines():
        if "\t" in line:
            cLine = line.replace("\t", "").replace("\n", "").replace(",", "")  # remove tabs, new lines and commas
            splitLine = cLine.split(" = ")
            strKey = splitLine[0]
            value = splitLine[1]

            if "_" in strKey:
                strKeySplit = strKey.split("_")
                del strKeySplit[0]
                del strKeySplit[0]

                cStrKey = "_".join(strKeySplit)
                stringPoolStringsDict[cStrKey] = value




# Function-related  ----------------------------------------------------------------------------------------------------


""" [Note]
    
    
    [Function]
    
    [Methods in Function]
        '__class__', '__del__', '__delattr__', '__dict__', '__doc__', 
        '__eq__', '__format__', '__getattribute__', '__gt__', '__hash__', 
        '__init__', '__lt__', '__module__', '__ne__', '__new__', 
        '__reduce__', '__reduce_ex__', '__repr__', '__setattr__', '__sizeof__', 
        '__str__', '__subclasshook__', '__swig_destroy__', '__weakref__', '_print', 
        'analyzed_sp', 'argsize', 'clear', 'color', 'compare', 
        'contains', 'does_return', 'empty', 'endEA', 'end_ea', 
        'extend', 'flags', 'fpd', 'frame', 'frregs', 'frsize', 
        'intersect', 'is_far', 'llabelqty', 'llabels', 'overlaps', 
        'owner', 'pntqty', 'points', 'referers', 'refqty', 
        'regargqty', 'regargs', 'regvarqty', 'regvars', 
        'size', 'startEA', 'start_ea', 'tailqty', 'tails', 
        'this', 'thisown'
"""


def get_function(ea):
    """Gets the Function of which the given Address is a part of.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: Function of the specified Address.
    :rtype: Function
    """
    return get_func(ea)


def get_function_name(ea):
    """Gets the given Address' Function's name.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: Name of the Function of the Address given.
    :rtype: str
    """
    return get_func_name(ea)


def get_list_func_items(ea):
    """Gets a list of Addresses within the Function of the given Address.

    :param func: An Address in the Fuction to be used.
    :type func: Address

    :return: List of Addresses in the Function of the given Address.
    :rtype: List<Address>
    """
    return list(FuncItems(ea))


def get_func_start_addr_by_addr(ea):
    """Gets the Function of the Address, then returns the starting Address of the Function.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: Starting Address of the given Address' Function.
    :rtype: Address
    """
    return get_func_attr(ea, FUNCATTR_START)


def get_func_start_addr_by_func(func):
    """Gets the Function's starting Address.

    :param func: The Function to be used.
    :type func: Function

    :return: Starting Address of the given Function.
    :rtype: Address
    """
    return func.start_ea


def get_func_end_addr_by_addr(ea):
    """Gets the Function of the Address, then returns the ending Address of the Function.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: Ending Address of the given Address' Function.
    :rtype: Address
    """
    return get_func_attr(ea, FUNCATTR_END)


def get_func_end_addr_by_func(func):
    """Gets the Function's ending Address.

    :param func: The Function to be used.
    :type func: Function

    :return: Ending Address of the given Function.
    :rtype: Address
    """
    return func.end_ea


def get_xrefs_func_name(ea):
    """Gets the cross-references (xrefs) Functions of the given Address' Function.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: The list of cross-reference Functions to the given Address' Function.
    :rtype: List<str>
    """
    list = []
    for xref in get_xrefs_addr(ea):
        list.append(get_function_name(xref))
    return list


def get_xref_amount(func_ea):
    """Gets the amount of cross-references to this Function.

    :param func_ea: The Address to check the amount of xrefs from.
    :type func_ea: Address

    :return: Amount of Xrefs.
    :rtype: int
    """
    return len(get_xrefs_addr(func_ea))


def rename_func(ea, name):
    """Renames the Function of the Address given to the name given.

    :param ea: The given Function's Address to be renamed.
    :type ea: Address
    :param name: The wanted name for the given Address.
    :type name: str

    :return: Boolean whether of not this Function has succeeded.
    :rtype: bool
    """
    return rename_addr(get_func_start_addr_by_addr(ea), name)


def decompile_func(ea):
    """Decompiles the Function of the given Address.

    :param ea: Function's Address to decompile.
    :type ea: Address

    :return: The decompiled Function.
    :rtype: List<str>
    """
    if not init_hexrays_plugin():
        return False

    f = get_func(ea)
    if f is None:
        return False
    try:
        cfunc = decompile(f)
    except:
        return False
    if cfunc is None:
        # Failed to decompile
        return False

    lines = []
    sv = cfunc.get_pseudocode()
    for sline in sv:
        line = tag_remove(sline.line)
        lines.append(line)
    return lines


def xref_function_in_xrefs(func_ea, xref_func):
    """Check if given Address (xref_func) is in the xref list of a function (func_ea).

    :param func_ea: Function's Address of which the xref list will be taken.
    :param xref_func: Function's Address
    :return:
    """
    list = get_xrefs_start_addr(get_func_start_addr_by_addr(func_ea))
    if len(list) <= 0:
        return False
    return get_func_start_addr_by_addr(xref_func) in list


def func_contains(func_ea, aob):
    """Check if the given Address' Functions contains a certain aob.

    :param func_ea: Address' Function to be checked for the given aob.
    :type func_ea: Address

    :param aob: AoB to be checked.
    :type aob: str

    :return: Returns True if Function contains the given AoB, else returns False.
    :type: bool
    """
    contains = False
    cur_addr = get_func_start_addr_by_addr(func_ea)
    last_addr = get_func_end_addr_by_addr(func_ea)

    cur_addr = find_binary(cur_addr, SEARCH_DOWN, aob)
    if cur_addr == BADADDR or cur_addr > last_addr:
        return contains
    contains = True

    return contains


def func_makes_call_to(func_ea, call_ea):
    """Check if the given Address' Function makes a call to the given call Address' Function.

    :param func_ea: Address' Function to be checked for calls to call_ea.
    :type func_ea: Address
    :param call_ea: Call Address to be checked.
    :type call_ea: Address

    :return: Returns True if Function makes one or more calls to the given call Address, else returns False.
    :rtype: bool
    """
    return call_ea in get_all_calls_in_func(func_ea)


def get_func_call_to_amount(func_ea, call_ea):
    """Gets amount of Calls to the given Call Address' Function in the given Function's Address.

    :param func_ea: Function's Address to be counted for the amount of calls to the given Call Address made.
    :type func_ea: Address
    :param call_ea: Address to be counted for calls in the given Address' Function.
    :type call_ea: Address

    :return: Amount of Calls made to the given call Address.
    :rtype: int
    """
    cur_addr = get_func_start_addr_by_addr(func_ea)
    end_addr = get_func_end_addr_by_addr(func_ea)
    amountOfCalls = 0
    while cur_addr < end_addr:
        makesCall = get_called_addr(cur_addr) == get_func_start_addr_by_addr(call_ea)
        if makesCall:
            amountOfCalls += 1
        cur_addr = next_head(cur_addr) # equivalent to  i += 1  in standard while loops
    return amountOfCalls


def get_all_calls_in_func(func_ea):
    """Gets all the called to Addresses within a specified Function.

    :param func_ea: Address of the Function to be used.
    :type func_ea: Address

    :return: List of all the Addresses called to.
    :rtype: List<Address>
    """
    callList = []
    for addr in get_list_func_items(func_ea):
        if get_called_addr(addr) != BADADDR and not get_called_addr(addr) == False:
            callList.append(get_called_addr(addr))
    return callList


#def get_all_decodes_in_LP(start_func_ea, decode1_EA, decode2_EA, decode4_EA, decode8_EA, decodeBuffer_EA, decodeString_EA):


# look for param in called func
# go in function given
# init a list
# loop {
#   loop through each call and enter the called address' function
#       Check for decode calls
#       add decode calls to a list
# }


# enum for decode to number (ie decode 4 -> 4)
# count total decode number to get packet size




# Address-related   ----------------------------------------------------------------------------------------------------

""" [Note] 
    IDAPython uses 'ea' to depict an Address.
    
    [Address]
    
    [Methods in Address]
        '__abs__', '__add__', '__and__', '__class__', '__cmp__', 
        '__coerce__', '__delattr__', '__div__', '__divmod__', '__doc__', 
        '__float__', '__floordiv__', '__format__', '__getattribute__', 
        '__getnewargs__', '__hash__', '__hex__', '__index__', '__init__', 
        '__int__', '__invert__', '__long__', '__lshift__', '__mod__', 
        '__mul__', '__neg__', '__new__', '__nonzero__', '__oct__', '__or__', 
        '__pos__', '__pow__', '__radd__', '__rand__', '__rdiv__', '__rdivmod__', 
        '__reduce__', '__reduce_ex__', '__repr__', '__rfloordiv__', '__rlshift__', 
        '__rmod__', '__rmul__', '__ror__', '__rpow__', '__rrshift__', '__rshift__', 
        '__rsub__', '__rtruediv__', '__rxor__', '__setattr__', '__sizeof__', 
        '__str__', '__sub__', '__subclasshook__', '__truediv__', '__trunc__', 
        '__xor__', 'bit_length', 'conjugate', 'denominator', 'imag', 
        'numerator', 'real'
    
"""


def get_cur_addr():
    """Gets the address the cursor is placed on.

    :return: Address on which the cursor is placed.
    :rtype: Address
    """
    return here()


def get_full_line(ea):
    """Gets the full disassembled line and the address of the specified address.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: The full disassembled line with the address.
    :rtype: str
    """
    return "%s      %s" % (hex(ea), get_disasm_line(ea))


def get_disasm_line(ea):
    """Gets the full disassembled line of the specified Address.

    :param ea: Address of the line to be gotten.
    :type ea: Address

    :return: The full disassembled line.
    :rtype: str
    """
    return "%s" % (generate_disasm_line(ea, 0))


def get_xrefs_addr(ea):
    """Gets the cross-references (xrefs) Addresses of the given Address' Function.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: The list of cross-reference Addresses to the given Address' Function.
    :rtype: List<Address>
    """
    list = []
    for xref in XrefsTo(ea, 0):
        list.append(xref.frm)
    return list


def get_xrefs_start_addr(ea):
    """Gets the cross-references (xrefs) Addresses of the given Address' Function.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: The list of cross-reference Addresses to the given Address' Function.
    :rtype: List<Address>
    """
    list = []
    for xref in XrefsTo(ea, 0):
        list.append(get_func_start_addr_by_addr(xref.frm))
    return list


def get_first_xref_addr(ea): #
    """Gets the first cross-reference (xref) of the given Address' Function.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: The first cross-reference to the given Address' Function.
    :rtype: Address
    """
    for xref in XrefsTo(ea, 0):
        return (xref.frm)
    return BADADDR


def rename_addr(ea, name):
    """Renames the Address given to the name given.

    :param ea: The given Address to be renamed.
    :type ea: Address
    :param name: The wanted name for the given Address.
    :type name: str

    :return: Boolean whether of not this Function has succeeded.
    :rtype: bool
    """
    return set_name(ea, name, SN_NOWARN)


def get_called_addr(ea):
    """Gets the Address called on the given Address.

    :param ea: The given Address to be used.
    :type ea: Address

    :return: The Address that is called on the given Address.
    :rtype: Address
    """
    if ea == BADADDR:
        return False
    insn = get_aob_by_addr(ea)

    if not insn:
        return False
    insnSplit = insn.split(' ')

    if len(insnSplit) != 5 or insnSplit.pop(0) != "E8": # Call Op
        return False
    insnSplit.reverse()
    rel_EA = "".join(insnSplit)
    sint_rel_EA = struct.unpack('>i', rel_EA.decode('hex'))[0]

    next_EA = next_head(ea)
    return next_EA + sint_rel_EA


def find_first_addr_by_aob(aob):
    """Gets the first Address with the matching AoB.

    :param aob: string of characters to be matched with Address' AoB.
    :type aob: str

    :return: First Address that matches the given AoB.
    :rtype: Address
    """
    return find_binary(START_ADDR, SEARCH_DOWN, aob)


def find_first_addr_from_addr_by_aob(aob, fromAddr):
    """Gets the first Address with the matching AoB starting from the given fromAddress.

    :param aob: string of charactesr to be matched with the Address' AoB.
    :type aob: str
    :param fromAddr: Starting Address.
    :type fromAddr: Address

    :return: The first Address with the matching AoB, starting from the given fromAddress.
    :rtype: Address
    """
    return find_binary(next_head(fromAddr), SEARCH_DOWN, aob)


def find_all_addr_by_aob(aob):
    """Gets all the Addresses with the matching AoB.

    :param aob: string of characters to be matched with Address' AoB.
    :type aob: str

    :return: List of Addresses containing the given str.
    :rtype: List<Address>
    """
    list = []
    cur_addr = START_ADDR
    last_addr = END_ADDR
    while cur_addr < last_addr:
        cur_addr = find_binary(cur_addr, SEARCH_DOWN, aob)
        if cur_addr == BADADDR:
            continue
        list.append(cur_addr)
        cur_addr = next_head(cur_addr)
    return list


def find_first_addr_by_str(text):
    """Gets the first Address that contains the given str.

    :param text: The string to be searched for.
    :type text: str

    :return: The Address containing the given str.
    :rtype: Address
    """
    return find_first_addr_by_aob("\"{}\"".format(text.encode(encoding, errortype)))


def find_first_addr_from_addr_by_str(text, fromAddr):
    """Gets the first Address with the matching AoB starting from the given fromAddress.

    :param text: string to be searched for.
    :type text: str
    :param fromAddr: Starting Address.
    :type fromAddr: Address

    :return: The first Address containing the given str, starting from the fromAddress.
    :rtype: Address
    """
    return find_first_addr_from_addr_by_aob("\"{}\"".format(text.encode(encoding, errortype)), fromAddr)


def find_all_addr_by_str(text):
    """Gets all Addresses that contain the given str.

    :param text: The string to be searched for.
    :type text: str

    :return: List of Addresses containing the given str.
    :rtype: List<Address>
    """
    return find_all_addr_by_aob("\"{}\"".format(text.encode(encoding, errortype)))


def get_aob_by_addr(ea):
    insnList = GetManyBytes(ea, ItemSize(ea))
    aobStr = ""
    for chr in insnList:
        correctStr = format(ord(chr), 'x').upper()
        while len(correctStr) < 2:
            correctStr = "0" + correctStr
        aobStr += (correctStr + " ")
    return aobStr.rstrip(" ")



# Altering Functions ---------------------------------------------------------------------------------------------------


def print_rename(origName, newName):
    if origName == "":
        print "Could not find the search requirement for the wanted function: %s!" % ( newName )
    elif origName == newName:
        print "'%s' is already named as '%s'." % ( origName, newName )
    else:
        print "Renamed  '%s'  to  '%s'." % ( origName, newName )


def print_could_not_find(searchReq):
    print "Could not find the search requirement of \"%s\"" % ( searchReq )


def print_and_rename_func(ea, newName):
    print_rename(get_function_name(ea), newName)
    rename_func(ea, newName)


def get_first_xref_and_rename_func(ea, name):
    """Gets the first cross-reference Address' Function of the given Address, and renames it to the given name.

    :param ea: Address to be cross-refenced.
    :type ea: Address
    :param name: Name to be given to the first cross-reference of the given Address' Function.
    :type name: str

    :return: cross-referenced Address' Function.
    :rtype: Address
    """
    if ea is None or ea == BADADDR:
        print("Could not find " + name + " because of invalid xref ea")
        return
    xrefEA = get_first_xref_addr(ea)
    if xrefEA == BADADDR:
        print_could_not_find(name)
        return BADADDR

    funcEA = get_func_start_addr_by_addr(xrefEA)
    if funcEA == BADADDR:
        print_could_not_find(name)
        return BADADDR

    print_and_rename_func(xrefEA, name)
    return funcEA


def find_first_aob_and_rename_func(aob, name):
    """Gets the first found Address containing the given aob, and renames the Address' Function to the given name.

    :param aob: AoB to be searched for.
    :type aob: str
    :param name: Name to be given to the resulting Function.
    :type name: str

    :return: First Function containing the given aob.
    :rtype: Address.
    """
    search_addr = find_first_addr_by_aob(aob)
    print_and_rename_func(search_addr, name)
    return get_func_start_addr_by_addr(search_addr)


def find_first_aob_and_rename_first_xref_func(aob, name):
    """Gets the first found Address containing the given aob, and renames the first cross-reference found to the given
        Address' Function to the given name.

    :param aob: AoB to be searched for.
    :type aob: str
    :param name: Name to be given to the resulting Function.
    :type name: str

    :return: First Function containing the given aob's first cross-referenced Function.
    :rtype: Address.
    """
    search_addr = find_first_addr_by_aob(aob)
    if search_addr == BADADDR:
        print_could_not_find(aob)
        return BADADDR

    xref_search_addr = get_first_xref_addr(search_addr)
    if xref_search_addr == BADADDR:
        print_could_not_find(aob)
        return BADADDR

    if get_func_start_addr_by_addr(xref_search_addr) == BADADDR:
        return BADADDR

    print_and_rename_func(xref_search_addr, name)

    return get_func_start_addr_by_addr(xref_search_addr)


def find_first_str_and_rename_first_xref_func(str, name):
    """Gets the first Address, with the given str,
        and renames the Address' first xref's Address' Function.

    :param str: String to search for
    :type str: str
    :param name: Name to be given to the found Function.
    :type name: str

    :return: Xref Address' Function's Address.
    :rtype: Address
    """
    search_addr = find_first_addr_by_str(str)
    if search_addr == BADADDR:
        print_could_not_find(str)
        return BADADDR

    xref_search_addr = get_first_xref_addr(search_addr)
    if xref_search_addr == BADADDR:
        print_could_not_find(str)
        return BADADDR

    if get_func_start_addr_by_addr(xref_search_addr) == BADADDR:
        return BADADDR

    print_and_rename_func(xref_search_addr, name)

    return get_func_start_addr_by_addr(xref_search_addr)


def find_first_func_by_multiple_aob_and_rename(firstaob, aobList, name): # AND
    """Searches for the first Function containing both the first AoB given as well as the list of AoBs given,
        and renames the Function to the given name.
        Function uses an AND approach.

    :param firstaob: Initial AoB to be searched for.
    :type firstaob: str
    :param aobList: List of AoBs to be checked if they all are present
                    inside the resulting Function of searching the first aob
    :type aobList: List<str>
    :param name: Name to be given to the resulting Fcuntion.
    :type name: str

    :return: First Address' Function found to be containing both the firstaob and every element in the aobList.
    :rtype: Address.
    """
    listMatchFuncEA = find_all_addr_by_aob(firstaob)

    for match in listMatchFuncEA:
        fullMatch = False
        for aob in aobList:
            if not func_contains(match, aob):
                fullMatch = False
                break
            fullMatch = True

        if fullMatch:
            print_and_rename_func(match, name)
            return get_func_start_addr_by_addr(match)
    return BADADDR


def find_first_func_and_rename_by_a_sp_and_string(str, getString_EA, name):
    """Searches for the first Function containing the given StringPool string.
        As well as having a StringPool::GetString call within 10 next heads of the StringPool value push.

    :param str: StringPool String to be searched for.
    :type str: str
    :param getString_EA: Address of StringPool::GetString or StringPool::GetStringW or StringPool::GetStringBSTR.
    :type getString_EA: Address
    :param name: Name to be given to the resultant Function.
    :type name: str

    :return: Starting Address of the resultant Function.
    :type: Address
    """
    print "searching by StringPoolStrings: \"%s\"." % ( str )
    if str not in stringPoolStringsDict:
        print("Could not find string " + str)
        return
    spValue = stringPoolStringsDict[str]
    aob = "68 " + to_little_endian(int(spValue)) # Push Specific StringPoolStrings Value
    for possibleFunc in find_all_addr_by_aob(aob):
        aob_ea = find_first_addr_from_addr_by_aob(aob, get_func_start_addr_by_addr(possibleFunc))
        start_ea = aob_ea
        i = 0
        while i < 10:
            if get_called_addr(start_ea) == getString_EA:
                func_start = get_func_start_addr_by_addr(possibleFunc)
                print_and_rename_func(func_start, name)
                return get_func_start_addr_by_addr(possibleFunc)
            start_ea = next_head(start_ea)
            i += 1
    return BADADDR


def find_first_func_and_rename_by_multiple_sp_and_string(strList, getString_EA, name):
    """Searches for the first Function containing all of the given StringPool strings.
        As well as having a StringPool::GetString call within 10 next heads of the first StringPool string in the strList.

    :param strList: List of StringPool Strings to be searched for. (All need to match for a function to be given)
    :type strList: List<str>
    :param getString_EA: Address of StringPool::GetString or StringPool::GetStringW or StringPool::GetStringBSTR.
    :type getString_EA: Address
    :param name: Name to be given to the resultant Function.
    :type name: str

    :return: Starting Address of the resultant Function.
    :rtype: Address
    """
    print "searching by StringPoolStrings: %s." % ( strList )
    first_in_list = strList.pop(0)
    while not first_in_list in stringPoolStringsDict and len(strList) > 0:
        print("Could not find string " + first_in_list)
        first_in_list = strList.pop(0)

    if first_in_list not in stringPoolStringsDict:
        print("Could not find string " + first_in_list)
        return

    firstaob = "68 " + to_little_endian(int(stringPoolStringsDict[first_in_list])) # return first entry as well as remove it from the list.
    aobList = find_all_addr_by_aob(firstaob)
    check = False

    for possibleFunc in aobList:


        for aob in strList:
            if aob not in stringPoolStringsDict:
                continue
            real_aob = to_little_endian(int(stringPoolStringsDict[aob]))
            if not func_contains(possibleFunc, "68 " + real_aob):
                check = False
                break
            check = True
        if not check:
            continue

        aob_ea = find_first_addr_from_addr_by_aob(firstaob, get_func_start_addr_by_addr(possibleFunc))
        start_ea = aob_ea
        i = 0
        while i < 10:
            if get_called_addr(start_ea) == getString_EA:
                func_start = get_func_start_addr_by_addr(possibleFunc)
                print_and_rename_func(func_start, name)
                return get_func_start_addr_by_addr(possibleFunc)
            start_ea = next_head(start_ea)
            i += 1
    return BADADDR


def parseSwitchCasesInFunc(funcName, list):
    firstLine = list.pop(0)
    if "//" in firstLine: # for any comments on top of the func.. such as:
        #   // write access to const memory has been detected, the output may be wrong!
        firstLine = list.pop(0)

    paramsString = firstLine.split("(")[1].replace(")", "").strip()
    paramStringSplit = paramsString.split(",")
    inPacketParamInfo = paramStringSplit[len(paramStringSplit) - 1].strip()
    inPacketParamInfoSplit = inPacketParamInfo.split(" ")
    inPacketVariableName = inPacketParamInfoSplit[len(inPacketParamInfoSplit) - 1].replace("*", "").strip()

    inPacketVariables = []
    inPacketVariables.append(inPacketVariableName)


    containsSwitch = False
    for line in list:
        if "switch" in line:
            containsSwitch = True
            break

    # get all potential inPacket variable Names
    for line in list:
        for vars in inPacketVariables:
            if " = "+vars in line:
                inPacketVariables.append(line.split(" = ")[0].strip())

    for line in list:
        for vars in inPacketVariables:
            if vars in line:
                callLineIndex = list.index(line)
                for i in range(5):
                    curString = list[callLineIndex - i]
                    if "case" in curString:
                        try:
                            caseNumber = int(curString.replace("case ", "").replace(":", "").replace("u", "").strip(), 0)
                        except ValueError:
                            print "[If Parser] Failed to parse op value to Integer | Function: %s " % funcName
                            continue
                        callFunction = line.split("(")[0].replace("result", "").replace("return", "").replace("=", "").strip()
                        if "::" in callFunction:
                            callFunction = callFunction.split("::")[1]
                        elif "__" in callFunction:
                            callFunction = callFunction.split("__")[1]

                        caseTuple = (funcName, callFunction)    # Add  FuncName and Called Function  to Tuple
                        onPacketDict[caseNumber] = caseTuple    # Add  Tuple to onPacketDict
                        break
                    elif "break;" in curString or "return;" in curString:
                        break
                break
    if containsSwitch:
        parseIfChecks(funcName, list, inPacketVariables) # Utilises Data from the Switch statement
    else:
        parseIfCheck(funcName, list, inPacketVariables) # Stand-alone


def parseIfCheck(funcName, list, inPacketVariables):
    nTypeVariableName = ""

    containsIfStatement = False
    for line in list:
        if "if" in line:
            containsIfStatement = True
    if not containsIfStatement:
        return

    variableFound = False
    for line in list:

        if variableFound:
            break
        for var in inPacketVariables:

            if variableFound:
                break
            if var in line:
                callLineIndex = list.index(line)
                checkForSwitch = callLineIndex
                for i in range(checkForSwitch):
                    curString = list[checkForSwitch - i]
                    if "else if" in curString:
                        varName = curString.replace("else if", "").replace("(", "").replace(")", "").strip()
                        if "==" in varName:
                            insideOfElifStatement = varName.split("==")
                            if not "(" in insideOfElifStatement[0] and not ")" in insideOfElifStatement[0]:
                                nTypeVariableName = insideOfElifStatement[0].strip()
                                variableFound = True
                                break
                    elif "if" in curString:
                        varName = curString.replace("if", "").replace("(", "").replace(")", "").strip()
                        if "==" in varName:
                            insideOfIfStatement = varName.split("==")
                            if not "(" in insideOfIfStatement[0] and not ")" in insideOfIfStatement[0]:
                                nTypeVariableName = insideOfIfStatement[0].strip()
                                variableFound = True
                                break

    if not nTypeVariableName:
        print "[If Parser] Failed to identify the nType variable | Function: %s " % funcName
        return

    for line in list:
        for var in inPacketVariables:
            if var in line:
                callLineIndex = list.index(line)
                if nTypeVariableName in line:
                    break

                for i in range(6):
                    curString = list[callLineIndex - i]

                    if not nTypeVariableName in curString:
                        continue

                    if "case" in curString or "switch" in curString:
                        break

                    if "else if" in curString:
                        # else if  in curString
                        insideOfElifStatement = curString.replace("else if", "").replace("( ", "").replace(" )", "").strip()
                        if "==" in insideOfElifStatement:
                            splitElifStatement = insideOfElifStatement.split("==")
                            if "(" in splitElifStatement[0]:
                                print "warning, more stuff on the nType variable part"
                            try:
                                opVal = int(splitElifStatement[1].strip())
                            except ValueError:
                                print "[If Parser] Failed to parse op value to Integer | Function: %s " % funcName
                                continue

                            callFunction = line.split("(")[0].replace("result", "").replace("return", "").replace("=", "").strip()
                            if "::" in callFunction:
                                callFunction = callFunction.split("::")[1]
                            elif "__" in callFunction:
                                callFunction = callFunction.split("__")[1]

                            onPacketDict[opVal] = (funcName, callFunction)
                        break

                    elif "if" in curString:
                        # if  in curString
                        insideOfIfStatement = curString.replace("if", "").replace("( ", "").replace(" )", "").strip()
                        if "==" in insideOfIfStatement:
                            splitIfStatement = insideOfIfStatement.split("==")
                            if "(" in splitIfStatement[0]:
                                print "warning, more stuff on the nType variable part"
                            try:
                                opVal = int(splitIfStatement[1].strip())
                            except ValueError:
                                print "[If Parser] Failed to parse op value to Integer | Function: %s " % funcName
                                continue

                            callFunction = line.split("(")[0].replace("result", "").replace("return", "").replace("=", "").strip()
                            if "::" in callFunction:
                                callFunction = callFunction.split("::")[1]
                            elif "__" in callFunction:
                                callFunction = callFunction.split("__")[1]

                            onPacketDict[opVal] = (funcName, callFunction)
                        break


def parseIfChecks(funcName, list, inPacketVariables):
    nTypeVariableName = ""

    containsIfStatement = False
    for line in list:
        if "if" in line:
            containsIfStatement = True
    if not containsIfStatement:
        return

    variableFound = False
    # Identify the nType variable name
    for line in list:

        if variableFound:
            break
        for var in inPacketVariables:

            if variableFound:
                break
            if var in line:
                callLineIndex = list.index(line)
                foundInstanceOfSwitchCase = False
                for i in range(5):
                    curString = list[callLineIndex - i]
                    if "case" in curString:
                        foundInstanceOfSwitchCase = True
                        break

                if foundInstanceOfSwitchCase:
                    checkForSwitch = callLineIndex
                    for i in range(checkForSwitch):
                        curString = list[checkForSwitch - i]
                        if "switch" in curString:
                            nTypeVariableName = curString.replace("switch", "").replace("(", "").replace(")", "").strip()
                            variableFound = True
                            break
    if not nTypeVariableName:
        print "[If Parser] Failed to identify the nType variable | Function: %s " % funcName
        return

    for line in list:
        for var in inPacketVariables:
            if var in line:
                callLineIndex = list.index(line)
                if nTypeVariableName in line:
                    break

                for i in range(6):
                    curString = list[callLineIndex - i]

                    if not nTypeVariableName in curString:
                        continue

                    if "case" in curString or "switch" in curString:
                        break

                    if "else if" in curString:
                        # else if  in curString
                        insideOfElifStatement = curString.replace("else if", "").replace("( ", "").replace(" )", "").strip()
                        if "==" in insideOfElifStatement:
                            splitElifStatement = insideOfElifStatement.split("==")
                            if "(" in splitElifStatement[0]:
                                print "warning, more stuff on the nType variable part"

                            try:
                                opVal = int(splitElifStatement[1].strip())
                            except ValueError:
                                print "[If Parser] Failed to parse op value to Integer | Function: %s " % funcName
                                continue

                            callFunction = line.split("(")[0].replace("result", "").replace("return", "").replace("=", "").strip()
                            if "::" in callFunction:
                                callFunction = callFunction.split("::")[1]
                            elif "__" in callFunction:
                                callFunction = callFunction.split("__")[1]

                            onPacketDict[opVal] = (funcName, callFunction)
                        break

                    elif "if" in curString:
                        # if  in curString
                        insideOfIfStatement = curString.replace("if", "").replace("( ", "").replace(" )", "").strip()
                        if "==" in insideOfIfStatement:
                            splitIfStatement = insideOfIfStatement.split("==")
                            if "(" in splitIfStatement[0]:
                                print "warning, more stuff on the nType variable part"
                            try:
                                opVal = int(splitIfStatement[1].strip())
                            except ValueError:
                                print "[If Parser] Failed to parse op value to Integer | Function: %s " % funcName
                                continue

                            callFunction = line.split("(")[0].replace("result", "").replace("return", "").replace("=", "").strip()
                            if "::" in callFunction:
                                callFunction = callFunction.split("::")[1]
                            elif "__" in callFunction:
                                callFunction = callFunction.split("__")[1]

                            onPacketDict[opVal] = (funcName, callFunction)
                        break


def printSwitchCases():
    prevOnPacketFuncName = ""
    for key in sorted(onPacketDict.keys()):
        value = onPacketDict[key]
        onPacketFuncName = value[0]
        demangledName = Demangle(onPacketFuncName, GetShortPrm(INF_SHORT_DN))
        if not demangledName is None:
            onPacketFuncName = demangledName.split("(")[0]
        calledFuncName = value[1]
        if not prevOnPacketFuncName == onPacketFuncName and not prevOnPacketFuncName:
            if not prevOnPacketFuncName == "":
                print ""
            print "// *  %s  v%s.%s" % (onPacketFuncName, idbVersion, idbSubVersion)
        prevOnPacketFuncName = onPacketFuncName
        if not calledFuncName:
            print "%s(%d)," % ("(Virtual table shit)", key)
        else:
            print "%s(%d)," % (calledFuncName, key)


def writeSwitchCases(file):
    prevOnPacketFuncName = ""
    for key in sorted(onPacketDict.keys()):
        value = onPacketDict[key]
        onPacketFuncName = value[0]
        demangledName = Demangle(onPacketFuncName, GetShortPrm(INF_SHORT_DN))
        if not demangledName is None:
            onPacketFuncName = demangledName.split("(")[0]
        calledFuncName = value[1]
        if not prevOnPacketFuncName == onPacketFuncName:
            if not prevOnPacketFuncName == "":
                file.write("\n\n")
            file.write("// *  %s  v%s.%s\n" % (onPacketFuncName, idbVersion, idbSubVersion))
        prevOnPacketFuncName = onPacketFuncName
        if not calledFuncName:
            file.write("%s(%d),\n" % ("(Virtual Table)", key))
        else:
            file.write("%s(%d),\n" % (calledFuncName, key))
    file.close()


# General Functions ----------------------------------------------------------------------------------------------------

def to_little_endian(number):
    """Turns a Number into Little Endian Bytes (e.g: 550 -> 26 02 00 00)

    :param number: Number to be turned into Little Endian Bytes
    :type number: int

    :return: String of the Number in Little Endian.
    :rtype: str
    """
    hexNumber = format(number, 'x')
    strHexNumber = str(hexNumber).upper()
    while len(strHexNumber) < 8:
        strHexNumber = "0" + strHexNumber
    byteList = re.findall('..?', strHexNumber)
    byteList.reverse()
    return " ".join(byteList)


# Function -------------------------------------------------------------------------------------------------------------

class Function:
    ida_func = None
    name = "NoName"
    start_ea = BADADDR
    end_ea = BADADDR

    call_list = []
    decompiled_func_str_list = []

    def __init__(self, ea):
        if not ea == BADADDR:
            func = get_func(ea)
            if func is not None:
                self.ida_func = func
                self.start_ea = func.start_ea
                self.end_ea = func.end_ea
                self.name = get_func_name(func.start_ea)
                self.call_list = get_all_calls_in_func(func.start_ea)

    def contains_ea(self, ea):
        """ Check if the function contains the given address (ea).

        :param ea: Address to be checked.
        :return: return True if the function contains the address, False if it does not.
        """
        return self.ida_func.contains(ea)

    def contains_ea_list(self, ea_list):
        """ Check if the function contains all elements in the given address list (ea_list).

        :param ea_list: List of Addresses to be checked.
        :return: return True if the function contains all addresses, otherwise return False.
        """
        for ea in ea_list:
            if not self.contains_ea(ea):
                return False
        return True

    def contains_str(self, str):
        """ Check if the function contains the given string (str).

        :param str: String to be checked.
        :return: return True if the function contains the string, False if it does not.
        """
        search_ea = find_first_addr_by_str(str)
        if search_ea == BADADDR:
            return False
        for xref in XrefsTo(search_ea, 0):
            if self.contains_ea(xref.frm):
                return True
        return False

    def contains_str_list(self, str_list):
        """ Check if the function contains all elements in the given string list (str_list).

        :param str_list: List of Strings to be checked.
        :return: return True if the function contains all strings, otherwise return False.
        """
        for str in str_list:
            if not self.contains_str(str):
                return False
        return True

    def contains_aob(self, aob):
        """ Check if the function contains the given Array of Bytes (aob).

        :param aob: The Array of Bytes to be checked.
        :return: return True if the function contains the Array of Bytes, False if it does not.
        """
        cur_addr = find_binary(self.start_ea, SEARCH_DOWN, aob)
        if cur_addr != BADADDR and cur_addr <= self.end_ea:
            return True
        else:
            return False

    def contains_aob_list(self, aob_list):
        """ Check if the function contains all elements in the given Array Of Bytes list (aob_list).

        :param aob_list: The Array Of Bytes List to be checked.
        :return: return True if the function contains all the AoBs, otherwise return False.
        """
        for aob in aob_list:
            if not self.contains_aob(aob):
                return False
        return True

    def contains_sp_str(self, sp_str):
        """ Check if the function contains the given StringPool string (sp_str).

        :param sp_str: The StringPool string to be checked.
        :return: return True if the function contains the StringPool string, False if it does not.
        """
        return self.contains_aob("68 " + to_little_endian(int(stringPoolStringsDict[sp_str])))

    def contains_sp_str_list(self, sp_str_list):
        """ Check if the function contains all elements in the given Array StringPool string list (sp_str_list).

        :param sp_str_list: The List of StringPool strings to be checked.
        :return: return True if the function contains all the StringPool strings, otherwise return False.
        """
        for sp_str in sp_str_list:
            if not self.contains_sp_str(sp_str):
                return False
        return True

    def set_decompiled_func_str_list(self):
        """ Populates the 'decompiled_func_str_list' instance variable in the class,
        with the decompiled function's list of lines.

        :return: This function's decompiled str, as a List of lines. If failed to decompile, return False.
        """
        list = decompile_func(self.start_ea)
        if not list == False:
            self.decompiled_func_str_list = list
            return self.decompiled_func_str_list
        return False

    def makes_call_to(self, call_ea):
        """ Checks if this function calls the given address (call_ea).

        :param call_ea: Called address.
        :return: return True if this function calls the given address, otherwise return False.
        """
        return call_ea in self.call_list

    def get_xrefs(self):
        """ Returns all addresses that call this function.

        :return: List of Addresses  that call this function.
        """
        return get_xrefs_addr(self.start_ea)

    def is_called_by(self, ea):
        """ Checks if this function is called by the given address (ea).

        :param ea: Caller address.
        :return: return True if this function is called by the given address, otherwise return False.
        """
        for xref in self.get_xrefs():
            xref_func_start_ea = get_func_start_addr_by_addr(xref)
            if xref_func_start_ea == get_func_start_addr_by_addr(ea) or xref == ea:
                return True
        return False

    def rename(self, name):
        """ Renames this function to the given string (name).

        :param name: New name of this function.
        :return: return the new name of this function.
        """
        self.name = name
        rename_addr(self.start_ea, name)
        return self.name

    def __str__(self):
        return "name= {self.name},  start_ea= {self.start_ea},  end_ea= {self.end_ea}".format(self=self)


def get_all_func_start_addr_from_ea_list(ea_list):
    return list(map(lambda x: (get_func_start_addr_by_addr(x)), ea_list))


def get_all_distinct_func_start_addr_from_ea_list(ea_list):
    return list(set(map(lambda x: (get_func_start_addr_by_addr(x)), ea_list)))


def get_all_functions_containing_aob(aob):
    list_function = []
    list_ea = list(set(map(lambda x: (get_func_start_addr_by_addr(x)), find_all_addr_by_aob(aob))))  # distinct entries
    for ea in list_ea:
        if ea == BADADDR:
            continue
        list_function.append(Function(ea))
    return list_function


def get_all_functions_containing_aob_list(aob_list):
    if len(aob_list) < 1:
        return []
    aob = aob_list[0]
    list_function = []
    list_ea = list(set(map(lambda x: (get_func_start_addr_by_addr(x)), find_all_addr_by_aob(aob))))  # distinct entries
    for ea in list_ea:
        if ea == BADADDR:
            continue
        f = Function(ea)
        if f.contains_aob_list(aob_list):
            list_function.append(f)
    return list_function


def get_all_functions_containing_str(str):
    list_function = []
    list_ea = list(set(map(lambda x: (get_func_start_addr_by_addr(x)), find_all_addr_by_str(str))))  # distinct entries
    for ea in list_ea:
        if ea == BADADDR:
            continue
        list_function.append(Function(ea))
    return list_function


def get_all_functions_containing_str_list(str_list):
    if len(str_list) < 1:
        return []
    str = str_list[0]
    list_function = []
    list_ea = list(set(map(lambda x: get_func_start_addr_by_addr(x), find_all_addr_by_str(str))))  # distinct entries
    for ea in list_ea:
        if ea == BADADDR:
            continue
        f = Function(ea)
        if f.contains_str_list(str_list):
            list_function.append(f)
    return list_function


def get_all_functions_containing_sp_str(sp_str):
    return get_all_functions_containing_aob("68 " + to_little_endian(int(stringPoolStringsDict[sp_str])))


def get_all_functions_containing_sp_str_list(sp_str_list):
    if len(sp_str_list) < 1:
        return []
    sp_str = sp_str_list[0]
    return list(filter(lambda x: (is_function(x) and x.contains_sp_str_list(sp_str_list)), get_all_functions_containing_sp_str(sp_str)))


def is_function(f):
    return isinstance(f, Function)


def rename_first_in_list(f_list, name):
    if len(f_list) == 1:
        f = f_list[0]
        if is_function(f):
            print "Named %s to %s" % (f.name, name)
            f.rename(str(name))
        else:
            print "[%s] | Given list did not comprise of Functions." % name
    else:
        print "[%s] | Found %d results, please fix the criteria given. Ensuring there is only 1 element in the list." % (name, len(f_list))
        print "[%s] | List of functions that match the given criteria:" % name
        for f in f_list:
            print f
        print ""


print "Successfully loaded in Swordie idapy methods"
