namespace MoonlightLauncher
{
    partial class Form2
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
			System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form2));
			this.usernameTextBox = new System.Windows.Forms.TextBox();
			this.passwordTextBox = new System.Windows.Forms.TextBox();
			this.emailTextBox = new System.Windows.Forms.TextBox();
			this.usernameLabel = new System.Windows.Forms.Label();
			this.passwordLabel = new System.Windows.Forms.Label();
			this.emailLabel = new System.Windows.Forms.Label();
			this.createButton = new System.Windows.Forms.Button();
			this.usernameInfoLabel = new System.Windows.Forms.Label();
			this.passwordInfoLabel = new System.Windows.Forms.Label();
			this.emailInfoLabel = new System.Windows.Forms.Label();
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
			this.usernameTextBox.Location = new System.Drawing.Point(137, 52);
			this.usernameTextBox.MaxLength = 21;
			this.usernameTextBox.Name = "usernameTextBox";
			this.usernameTextBox.Size = new System.Drawing.Size(200, 20);
			this.usernameTextBox.TabIndex = 0;
			//
			// passwordTextBox
			//
			this.passwordTextBox.AcceptsTab = true;
			this.passwordTextBox.BackColor = System.Drawing.SystemColors.ControlDarkDark;
			this.passwordTextBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.passwordTextBox.ForeColor = System.Drawing.SystemColors.HighlightText;
			this.passwordTextBox.HideSelection = false;
			this.passwordTextBox.Location = new System.Drawing.Point(137, 96);
			this.passwordTextBox.Name = "passwordTextBox";
			this.passwordTextBox.Size = new System.Drawing.Size(200, 20);
			this.passwordTextBox.TabIndex = 1;
			this.passwordTextBox.UseSystemPasswordChar = true;
			//
			// emailTextBox
			//
			this.emailTextBox.AcceptsReturn = true;
			this.emailTextBox.AcceptsTab = true;
			this.emailTextBox.BackColor = System.Drawing.SystemColors.ControlDarkDark;
			this.emailTextBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.emailTextBox.ForeColor = System.Drawing.SystemColors.HighlightText;
			this.emailTextBox.HideSelection = false;
			this.emailTextBox.Location = new System.Drawing.Point(137, 138);
			this.emailTextBox.Name = "emailTextBox";
			this.emailTextBox.Size = new System.Drawing.Size(200, 20);
			this.emailTextBox.TabIndex = 2;
			//
			// usernameLabel
			//
			this.usernameLabel.AutoSize = true;
			this.usernameLabel.BackColor = System.Drawing.Color.Transparent;
			this.usernameLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.usernameLabel.ForeColor = System.Drawing.SystemColors.GradientActiveCaption;
			this.usernameLabel.Location = new System.Drawing.Point(134, 33);
			this.usernameLabel.Name = "usernameLabel";
			this.usernameLabel.Size = new System.Drawing.Size(70, 16);
			this.usernameLabel.TabIndex = 3;
			this.usernameLabel.Text = "Username";
			this.usernameLabel.Click += new System.EventHandler(this.Label1_Click);
			//
			// passwordLabel
			//
			this.passwordLabel.AutoSize = true;
			this.passwordLabel.BackColor = System.Drawing.Color.Transparent;
			this.passwordLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.passwordLabel.ForeColor = System.Drawing.SystemColors.GradientActiveCaption;
			this.passwordLabel.Location = new System.Drawing.Point(134, 77);
			this.passwordLabel.Name = "passwordLabel";
			this.passwordLabel.Size = new System.Drawing.Size(67, 16);
			this.passwordLabel.TabIndex = 4;
			this.passwordLabel.Text = "Password";
			//
			// emailLabel
			//
			this.emailLabel.AutoSize = true;
			this.emailLabel.BackColor = System.Drawing.Color.Transparent;
			this.emailLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.emailLabel.ForeColor = System.Drawing.SystemColors.GradientActiveCaption;
			this.emailLabel.Location = new System.Drawing.Point(134, 119);
			this.emailLabel.Name = "emailLabel";
			this.emailLabel.Size = new System.Drawing.Size(41, 16);
			this.emailLabel.TabIndex = 5;
			this.emailLabel.Text = "Email";
			//
			// createButton
			//
			this.createButton.BackColor = System.Drawing.Color.Transparent;
			this.createButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.createButton.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
			this.createButton.Location = new System.Drawing.Point(155, 166);
			this.createButton.Name = "createButton";
			this.createButton.Size = new System.Drawing.Size(171, 26);
			this.createButton.TabIndex = 6;
			this.createButton.Text = "Create Account";
			this.createButton.UseVisualStyleBackColor = false;
			this.createButton.Click += new System.EventHandler(this.CreateButton_Click);
			//
			// usernameInfoLabel
			//
			this.usernameInfoLabel.AutoSize = true;
			this.usernameInfoLabel.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
			this.usernameInfoLabel.Location = new System.Drawing.Point(12, 41);
			this.usernameInfoLabel.Name = "usernameInfoLabel";
			this.usernameInfoLabel.Size = new System.Drawing.Size(0, 13);
			this.usernameInfoLabel.TabIndex = 7;
			this.usernameInfoLabel.Click += new System.EventHandler(this.Label1_Click_1);
			//
			// passwordInfoLabel
			//
			this.passwordInfoLabel.AutoSize = true;
			this.passwordInfoLabel.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
			this.passwordInfoLabel.Location = new System.Drawing.Point(12, 93);
			this.passwordInfoLabel.Name = "passwordInfoLabel";
			this.passwordInfoLabel.Size = new System.Drawing.Size(0, 13);
			this.passwordInfoLabel.TabIndex = 8;
			//
			// emailInfoLabel
			//
			this.emailInfoLabel.AutoSize = true;
			this.emailInfoLabel.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
			this.emailInfoLabel.Location = new System.Drawing.Point(12, 145);
			this.emailInfoLabel.Name = "emailInfoLabel";
			this.emailInfoLabel.Size = new System.Drawing.Size(0, 13);
			this.emailInfoLabel.TabIndex = 9;
			//
			// Form2
			//
			this.AcceptButton = this.createButton;
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
			this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
			this.ClientSize = new System.Drawing.Size(480, 221);
			this.Controls.Add(this.emailInfoLabel);
			this.Controls.Add(this.passwordInfoLabel);
			this.Controls.Add(this.usernameInfoLabel);
			this.Controls.Add(this.createButton);
			this.Controls.Add(this.emailLabel);
			this.Controls.Add(this.passwordLabel);
			this.Controls.Add(this.usernameLabel);
			this.Controls.Add(this.emailTextBox);
			this.Controls.Add(this.passwordTextBox);
			this.Controls.Add(this.usernameTextBox);
			this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
			this.Name = "Form2";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Moonlight v214 - Create Account";
			this.ResumeLayout(false);
			this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox usernameTextBox;
        private System.Windows.Forms.TextBox passwordTextBox;
        private System.Windows.Forms.TextBox emailTextBox;
        private System.Windows.Forms.Label usernameLabel;
        private System.Windows.Forms.Label passwordLabel;
        private System.Windows.Forms.Label emailLabel;
        private System.Windows.Forms.Button createButton;
        private System.Windows.Forms.Label usernameInfoLabel;
        private System.Windows.Forms.Label passwordInfoLabel;
        private System.Windows.Forms.Label emailInfoLabel;
    }
}
