using Swordie;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SwordieLauncher
{
    public partial class Form2 : Form
    {

        public Client client;

        public Form2()
        {
            InitializeComponent();
        }

        private void Label1_Click(object sender, EventArgs e)
        {

        }

        private void CreateButton_Click(object sender, EventArgs e)
        {
            this.usernameInfoLabel.Text = "";
            this.passwordInfoLabel.Text = "";
            this.emailInfoLabel.Text = "";
            string text1 = this.usernameTextBox.Text;
            string text2 = this.passwordTextBox.Text;
            string text3 = this.emailTextBox.Text;
            passwordTextBox.Text = "";
            if (text1.Length < 4)
            {
                this.usernameInfoLabel.Text = "Username must be at least 4 characters long.";
            }
            else if (text2.Length < 6)
            {
                this.passwordInfoLabel.Text = "Password must be at least 6 characters long.";
            }
            else if (text3 == null || text3.Equals("") || !this.IsValid(text3))
            {
                this.emailInfoLabel.Text = "Email is invalid.";
            }
            else
            {
                this.createAccount(text1, text2, text3);
            }
        }

        private void Label1_Click_1(object sender, EventArgs e)
        {

        }

        private async Task<bool> createAccount(string username, string password, string email)
        {
            byte request = await this.sendAccountCreateRequest(username, password, email);
            switch (request)
            {
                case 0:
                    int num1 = (int)MessageBox.Show("Account successfully created!", "Account creation success", MessageBoxButtons.OK, MessageBoxIcon.Asterisk);
                    break;
                case 1:
                    int num2 = (int)MessageBox.Show("Account name already taken!", "Account creation error", MessageBoxButtons.OK, MessageBoxIcon.Hand);
                    break;
                case 2:
                    int num3 = (int)MessageBox.Show("This IP has already created an account!", "Account creation error", MessageBoxButtons.OK, MessageBoxIcon.Hand);
                    break;
                case 3:
                    int num4 = (int)MessageBox.Show("Unknown error: client and server info are mismatched.", "Account creation error", MessageBoxButtons.OK, MessageBoxIcon.Hand);
                    break;
            }
            return request == (byte)0;
        }

        private async Task<byte> sendAccountCreateRequest(
          string username,
          string password,
          string email)
        {
            this.client.Send(OutPackets.CreateAccountRequest(username, password, email));
            InPacket inPacket = this.client.Receive();
            inPacket.readInt();
            int num = (int)inPacket.readShort();
            return inPacket.readByte();
        }

        public bool IsValid(string emailaddress)
        {
            try
            {
                MailAddress mailAddress = new MailAddress(emailaddress);
                return true;
            }
            catch (FormatException ex)
            {
                return false;
            }
        }
    }
}
