namespace ElGamal
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
            this.btn_encrypt = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // plainText
            // 
            this.plainText.Location = new System.Drawing.Point(93, 12);
            this.plainText.Name = "plainText";
            this.plainText.Size = new System.Drawing.Size(699, 499);
            this.plainText.TabIndex = 0;
            this.plainText.Text = "";
            // 
            // btn_encrypt
            // 
            this.btn_encrypt.Location = new System.Drawing.Point(12, 12);
            this.btn_encrypt.Name = "btn_encrypt";
            this.btn_encrypt.Size = new System.Drawing.Size(75, 23);
            this.btn_encrypt.TabIndex = 1;
            this.btn_encrypt.Text = "Generate";
            this.btn_encrypt.UseVisualStyleBackColor = true;
            this.btn_encrypt.Click += new System.EventHandler(this.btn_encrypt_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(820, 523);
            this.Controls.Add(this.btn_encrypt);
            this.Controls.Add(this.plainText);
            this.Name = "Form1";
            this.Text = "El Gamal Messenger";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.RichTextBox plainText;
        private System.Windows.Forms.Button btn_encrypt;
    }
}

