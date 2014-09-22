Pet Of The Day
==============

A proof of concept code to demonstrate the capabilities of RESTful API based architecture.

The purpose of this application is to implement a Pet Of The Day demo app with the following functions:

TODO:
- update Gallery layout (showing items from top-down
- refresh of Gallery with swipe down
- submit vote with user name
- pressing back on Vote fragment going back to Gallery
- get push notification about the actual picture to see other users' votes
- upload stored picture via RESTful service with user name
- take a picture with camera and store

DONE:
- setup basic layout (main, navigation drawer, gallery, upload, vote)
- populate gallery grid layout in background from the server
- setup navigation drawer to swap between fragments
- gallery fragment call vote fragment with picture id
- after vote submission pick random picture from the server then rate with LIKE/DISLIKE
- added vote to Navigation Drawer with picking random image
- Vote fragment submitting like/dislike
- Vote invoked from Navigation Drawer loading a random picture