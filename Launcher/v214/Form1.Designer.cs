namespace MoonlightLauncher
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
			System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
			this.usernameTextBox = new System.Windows.Forms.TextBox();
			this.usernameLabel = new System.Windows.Forms.Label();
			this.passwordLabel = new System.Windows.Forms.Label();
			this.passwordTextBox = new System.Windows.Forms.TextBox();
			this.loginButton = new System.Windows.Forms.Button();
			this.createAccountButton = new System.Windows.Forms.Button();
			this.SuspendLayout();
			//
			// usernameTextBox
			//
			this.usernameTextBox.AcceptsTab = true;
			this.usernameTextBox.BackColor = System.Drawing.SystemColors.ControlDarkDark;
			this.usernameTextBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.usernameTextBox.CharacterCasing = System.Windows.Forms.CharacterCasing.Lower;
			this.usernameTextBox.ForeColor = System.Drawing.SystemColors.HighlightText;
			this.usernameTextBox.HideSelection = false;
			this.usernameTextBox.Location = new System.Drawing.Point(137, 71);
			this.usernameTextBox.Margin = new System.Windows.Forms.Padding(10);
			this.usernameTextBox.MaxLength = 21;
			this.usernameTextBox.Name = "usernameTextBox";
			this.usernameTextBox.Size = new System.Drawing.Size(200, 20);
			this.usernameTextBox.TabIndex = 0;
			//
			// usernameLabel
			//
			this.usernameLabel.AutoSize = true;
			this.usernameLabel.BackColor = System.Drawing.Color.Transparent;
			this.usernameLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.usernameLabel.Location = new System.Drawing.Point(134, 52);
			this.usernameLabel.Name = "usernameLabel";
			this.usernameLabel.Size = new System.Drawing.Size(70, 16);
			this.usernameLabel.TabIndex = 1;
			this.usernameLabel.Text = "Username";
			//
			// passwordLabel
			//
			this.passwordLabel.AutoSize = true;
			this.passwordLabel.BackColor = System.Drawing.Color.Transparent;
			this.passwordLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.passwordLabel.Location = new System.Drawing.Point(134, 98);
			this.passwordLabel.Name = "passwordLabel";
			this.passwordLabel.Size = new System.Drawing.Size(67, 16);
			this.passwordLabel.TabIndex = 2;
			this.passwordLabel.Text = "Password";
			//
			// passwordTextBox
			//
			this.passwordTextBox.AcceptsTab = true;
			this.passwordTextBox.BackColor = System.Drawing.SystemColors.ControlDarkDark;
			this.passwordTextBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.passwordTextBox.ForeColor = System.Drawing.SystemColors.HighlightText;
			this.passwordTextBox.Location = new System.Drawing.Point(137, 117);
			this.passwordTextBox.MaxLength = 256;
			this.passwordTextBox.Name = "passwordTextBox";
			this.passwordTextBox.Size = new System.Drawing.Size(200, 20);
			this.passwordTextBox.TabIndex = 3;
			this.passwordTextBox.UseSystemPasswordChar = true;
			//
			// loginButton
			//
			this.loginButton.BackColor = System.Drawing.Color.Transparent;
			this.loginButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.loginButton.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
			this.loginButton.Location = new System.Drawing.Point(137, 143);
			this.loginButton.Name = "loginButton";
			this.loginButton.Size = new System.Drawing.Size(90, 26);
			this.loginButton.TabIndex = 4;
			this.loginButton.Text = "Login";
			this.loginButton.UseVisualStyleBackColor = false;
			this.loginButton.Click += new System.EventHandler(this.LoginButton_Click);
			//
			// createAccountButton
			//
			this.createAccountButton.BackColor = System.Drawing.Color.Transparent;
			this.createAccountButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.createAccountButton.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
			this.createAccountButton.Location = new System.Drawing.Point(247, 143);
			this.createAccountButton.Name = "createAccountButton";
			this.createAccountButton.Size = new System.Drawing.Size(90, 26);
			this.createAccountButton.TabIndex = 5;
			this.createAccountButton.Text = "Register";
			this.createAccountButton.UseVisualStyleBackColor = false;
			this.createAccountButton.Click += new System.EventHandler(this.CreateAccountButton_Click);
			//
			// Form1
			//
			this.AcceptButton = this.loginButton;
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.BackColor = System.Drawing.SystemColors.ControlDark;
			this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
			this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
			this.ClientSize = new System.Drawing.Size(476, 217);
			this.Controls.Add(this.createAccountButton);
			this.Controls.Add(this.loginButton);
			this.Controls.Add(this.passwordTextBox);
			this.Controls.Add(this.passwordLabel);
			this.Controls.Add(this.usernameLabel);
			this.Controls.Add(this.usernameTextBox);
			this.ForeColor = System.Drawing.SystemColors.ActiveCaption;
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
			this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
			this.MaximizeBox = false;
			this.Name = "Form1";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Moonlight v214";
			this.TransparencyKey = System.Drawing.Color.MintCream;
			this.Load += new System.EventHandler(this.Form1_Load);
			this.ResumeLayout(false);
			this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox usernameTextBox;
        private System.Windows.Forms.Label usernameLabel;
        private System.Windows.Forms.Label passwordLabel;
        private System.Windows.Forms.TextBox passwordTextBox;
        private System.Windows.Forms.Button loginButton;
        private System.Windows.Forms.Button createAccountButton;
    }
}

