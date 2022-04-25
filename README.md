##Tiles

#How to Use the Program
To run the program simply run the jar file. This program uses no command line 
arguments and has no player adjustable options.

#How it Works
At the start of each game a Board is created. Each board is created with 20 
empty tiles. After the tiles are created, the circles for each tile are created
and then shuffled. If the circles are not shuffled each tile has circles of all
the same colors but different sizes. Each circle is its own individual FacePiece
having a FacePiece object for each circle means that I could change the shape to 
be whatever I wanted it to be I would just have to change some things around in the
main Tiles.java to display a new picture. Score is kept using a Score object.

Main is located in Tiles.java this is where the game is initiated and where the GUI 
resides. To link the GUI and each of the tiles I used a Map. This way when a tile is
clicked on the correct tile in the back end is modified. Tiles are managed using rows
and columns. 

The GUI is made up of 20 squares to represent each tile with 4 circles on each tile,
however they are generated with the circles being located beneath each square. The 
fill color of each square was set to transparent. This way an event handler could be
placed on each square and the circles underneath could be manipulated without having to 
redraw anything. When a tile is clicked on the border of the tile is highlighted in yellow
The current combo and longest combo are displayed to the right of the 
main game board. When all of the tiles have been cleared a simple "Game Over" message
will appear under the scores.
