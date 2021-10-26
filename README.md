# MineSweeper

to start the game open file src/application/Main.java 


The objective in Minesweeper is to find and mark all the mines hidden under the grey squares, in the shortest time possible. This is done by clicking on the squares to open them. Each square will have one of the following:

1) A mine, and if you click on it you'll lose the game.
2) A number, which tells you how many of its adjacent squares have mines in them.
3) Nothing. In this case you know that none of the adjacent squares have mines, and they will be automatically opened as well.


It is guaranteed that the first square you open won't contain a mine, so you can start by clicking any square. Often you'll hit on an empty square on the first try and then you'll open up a few adjacent squares as well, which makes it easier to continue. Then it's basically just looking at the numbers shown, and figuring out where the mines are.