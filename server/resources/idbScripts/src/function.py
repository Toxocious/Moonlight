from idautils import *
from idaapi import *
from idc import *
from swordie_ida_plugin import *


class Function:
    ida_func = None
    name = "NoName"
    start_ea = BADADDR
    end_ea = BADADDR

    call_list = []
    decompiled_func_str_list = []

    def __init__(self, ea):
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

        :return: This function's decompiled str, as a List of lines.
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
