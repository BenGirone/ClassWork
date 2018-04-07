namespace ElGamalClient
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
            this.plainText = new System.Windows.Forms.RichTextBox();
            this.sendMessage = new System.Windows.Forms.Button();
            this.remoteIP = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.output = new System.Windows.Forms.RichTextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.readMessage = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // plainText
            // 
            this.plainText.Location = new System.Drawing.Point(127, 38);
            this.plainText.Name = "plainText";
            this.plainText.Size = new System.Drawing.Size(665, 322);
            this.plainText.TabIndex = 0;
            this.plainText.Text = "";
            // 
            // sendMessage
            // 
            this.sendMessage.Location = new System.Drawing.Point(12, 38);
            this.sendMessage.Name = "sendMessage";
            this.sendMessage.Size = new System.Drawing.Size(109, 23);
            this.sendMessage.TabIndex = 1;
            this.sendMessage.Text = "Send Message";
            this.sendMessage.UseVisualStyleBackColor = true;
            // 
            // remoteIP
            // 
            this.remoteIP.Location = new System.Drawing.Point(12, 80);
            this.remoteIP.Name = "remoteIP";
            this.remoteIP.Size = new System.Drawing.Size(109, 20);
            this.remoteIP.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 64);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(60, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Remote IP:";
            // 
            // output
            // 
            this.output.BackColor = System.Drawing.SystemColors.WindowText;
            this.output.Font = new System.Drawing.Font("Lucida Console", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.output.ForeColor = System.Drawing.SystemColors.Window;
            this.output.Location = new System.Drawing.Point(127, 399);
            this.output.Name = "output";
            this.output.Size = new System.Drawing.Size(665, 112);
            this.output.TabIndex = 4;
            this.output.Text = "";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(127, 22);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(77, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Message Text:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(127, 383);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(42, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Output:";
            // 
            // readMessage
            // 
            this.readMessage.Location = new System.Drawing.Point(12, 399);
            this.readMessage.Name = "readMessage";
            this.readMessage.Size = new System.Drawing.Size(109, 23);
            this.readMessage.TabIndex = 7;
            this.readMessage.Text = "Read a Message";
            this.readMessage.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(803, 523);
            this.Controls.Add(this.readMessage);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.output);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.remoteIP);
            this.Controls.Add(this.sendMessage);
            this.Controls.Add(this.plainText);
            this.Name = "Form1";
            this.Text = "El Gamal Messenger";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.RichTextBox plainText;
        private System.Windows.Forms.Button sendMessage;
        private System.Windows.Forms.TextBox remoteIP;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.RichTextBox output;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button readMessage;
    }
}

