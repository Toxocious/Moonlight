using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Swordie;

namespace SwordieLauncher
{
    public partial class Form1 : Form
    {
        private Client client;

        public Form1()
        {
            InitializeComponent();
            this.client = new Client();
            this.client.Connect();
        }

        private void LoginButton_Click(object sender, EventArgs e)
        {
            LaunchMapleAsync();
        }



        private static String sDllPath = "v214_local.dll"; // dll name


        private static uint CREATE_SUSPENDED = 0x00000004;

        public struct PROCESS_INFORMATION
        {
            public IntPtr hProcess;
            public IntPtr hThread;
            public uint dwProcessId;
            public uint dwThreadId;
        }


        public struct STARTUPINFO
        {
            public uint cb;
            public string lpReserved;
            public string lpDesktop;
            public string lpTitle;
            public uint dwX;
            public uint dwY;
            public uint dwXSize;
            public uint dwYSize;
            public uint dwXCountChars;
            public uint dwYCountChars;
            public uint dwFillAttribute;
            public uint dwFlags;
            public short wShowWindow;
            public short cbReserved2;
            public IntPtr lpReserved2;
            public IntPtr hStdInput;
            public IntPtr hStdOutput;
            public IntPtr hStdError;
        }



        public struct SECURITY_ATTRIBUTES
        {
            public int length;
            public IntPtr lpSecurityDescriptor;
            public bool bInheritHandle;

        }

        [DllImport("kernel32.dll")]
        static extern bool CreateProcess(string lpApplicationName, string lpCommandLine, IntPtr lpProcessAttributes, IntPtr lpThreadAttributes,
                        bool bInheritHandles, uint dwCreationFlags, IntPtr lpEnvironment,
                        string lpCurrentDirectory, ref STARTUPINFO lpStartupInfo, out PROCESS_INFORMATION lpProcessInformation);


        [DllImport("kernel32.dll", SetLastError = true)]
        static extern uint ResumeThread(IntPtr hThread);

        [DllImport("kernel32.dll", SetLastError = true)]
        static extern IntPtr OpenProcess(uint dwDesiredAccess, int bInheritHandle, uint dwProcessId);

        [DllImport("kernel32.dll", SetLastError = true)]
        static extern int CloseHandle(IntPtr hObject);

        [DllImport("kernel32.dll", SetLastError = true)]
        static extern IntPtr GetProcAddress(IntPtr hModule, string lpProcName);

        [DllImport("kernel32.dll", SetLastError = true)]
        static extern IntPtr GetModuleHandle(string lpModuleName);

        [DllImport("kernel32.dll", SetLastError = true)]
        static extern IntPtr VirtualAllocEx(IntPtr hProcess, IntPtr lpAddress, IntPtr dwSize, uint flAllocationType, uint flProtect);

        [DllImport("kernel32.dll", SetLastError = true)]
        static extern int WriteProcessMemory(IntPtr hProcess, IntPtr lpBaseAddress, byte[] buffer, uint size, int lpNumberOfBytesWritten);

        [DllImport("kernel32.dll", SetLastError = true)]
        static extern IntPtr CreateRemoteThread(IntPtr hProcess, IntPtr lpThreadAttribute, IntPtr dwStackSize, IntPtr lpStartAddress,
            IntPtr lpParameter, uint dwCreationFlags, IntPtr lpThreadId);


        private static int GetProcessId(String proc)
        {
            int processID = -1;
            Process[] processes = Process.GetProcesses();
            for (int i = 0; i < processes.Length; i++)
            {
                if (processes[i].ProcessName == proc)
                {
                    processID = (int)processes[i].Id;

                    break;
                }
            }

            return processID;
        }


        private static int Inject(String exe, String dllPath)
        {
            int processID = GetProcessId(exe);
            if (processID == -1)
            {
                return 1;
            }

            IntPtr pLoadLibraryAddress = GetProcAddress(GetModuleHandle("Kernel32.dll"), "LoadLibraryA");
            if (pLoadLibraryAddress == (IntPtr)0)
            {
                return 2;
            }

            IntPtr processHandle = OpenProcess((0x2 | 0x8 | 0x10 | 0x20 | 0x400), 1, (uint)processID);
            if (processHandle == (IntPtr)0)
            {
                return 3;
            }

            IntPtr lpAddress = VirtualAllocEx(processHandle, (IntPtr)null, (IntPtr)dllPath.Length, (0x1000 | 0x2000), 0X40);
            if (lpAddress == (IntPtr)0)
            {
                return 4;
            }

            byte[] bytes = Encoding.ASCII.GetBytes(dllPath);
            if (WriteProcessMemory(processHandle, lpAddress, bytes, (uint)bytes.Length, 0) == 0)
            {
                return 5;
            }

            if (CreateRemoteThread(processHandle, (IntPtr)null, (IntPtr)0, pLoadLibraryAddress, lpAddress, 0, (IntPtr)null) == (IntPtr)0)
            {
                return 6;
            }

            CloseHandle(processHandle);

            return 0;
        }



        async Task<bool> LaunchMapleAsync()
        {
            STARTUPINFO si = new STARTUPINFO();
            PROCESS_INFORMATION pi = new PROCESS_INFORMATION();


            string text1 = this.usernameTextBox.Text;
            string text2 = this.passwordTextBox.Text;
            this.passwordTextBox.Clear();
            string token = await this.GetToken(text1, text2);
            bool flag = !token.Equals("");
            if (flag)
                try
                {
                    bool bCreateProc = CreateProcess("MapleStory.exe", $" WebStart {token}", IntPtr.Zero, IntPtr.Zero, false, CREATE_SUSPENDED, IntPtr.Zero, null, ref si, out pi);
                   

                    if (bCreateProc)
                    {
                        int bInject = Inject("MapleStory", sDllPath);
                        if (bInject == 0)
                        {
                            ResumeThread(pi.hThread);

                            CloseHandle(pi.hThread);
                            CloseHandle(pi.hProcess);
                        }
                        else
                        {
                            MessageBox.Show("Error code: " + bInject.ToString());
                        }
                    }
                }

                catch (Exception ex)
                {
                    int num = (int)MessageBox.Show("Could not start! Make sure the file is in your game folder and that this program is ran as admin.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Hand);
                }
        
        else
            {
                int num1 = (int)MessageBox.Show("Invalid username/password combination.");
    }
            return flag;
        }




public void Run()
        {

            LaunchMapleAsync();

        }





    private async Task<bool> authAndStuff()
        {
            string text1 = this.usernameTextBox.Text;
            string text2 = this.passwordTextBox.Text;
            this.passwordTextBox.Clear();
            string token = await this.GetToken(text1, text2);
            bool flag = !token.Equals("");
            if (flag)
            {
                try
                {
                    new Process()
                    {
                        StartInfo = {
                            FileName = "MapleStory.exe",
                            Arguments = $"WebStart admin"
                        }
                    }.Start();
                }
                catch (Exception ex)
                {
                    int num = (int)MessageBox.Show("Could not start! Make sure the file is in your game folder and that this programming is run as admin.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Hand);
                }
            }
            else
            {
                int num1 = (int)MessageBox.Show("Invalid username/password combination.");
            }
            return flag;
        }

        private async Task<string> GetToken(string username, string pwd)
        {
            this.client.Send(OutPackets.AuthRequest(username, pwd));
            return Handlers.getAuthTokenFromInput(this.client.Receive());
        }

        private void CreateAccountButton_Click(object sender, EventArgs e)
        {
            new Form2() { client = this.client }.Show();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
