#!/bin/bash
(gcc BMAGprog4.c -o good_echo &&
chmod a+x good_echo &&
sudo cp good_echo /bin/good_echo &&
good_echo "good_echo installed. In fact, this message was written with good echo! You can now use the command 'good_echo' in the command line! (with or without arguments)") ||
echo "good_echo installation failed. You may still be able to run ./good_echo in this directory."