using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ElGamal
{
    static class Program
    {
        public static Controller controller;

        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            //Create the controller with a UI window and encryption level (bits)
            Form1 mainWindow = new Form1();
            controller = new Controller(mainWindow, 256);

            //Display the window
            Application.Run(mainWindow);
        }
    }
}
