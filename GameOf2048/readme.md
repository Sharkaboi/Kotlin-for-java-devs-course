## Game

The goal of this assignment is to implement one game: 
[Game 2048](https://en.wikipedia.org/wiki/2048_(video_game)).

The games are partly implemented already, so your task is to finish the
implementation by following the step by step guide and doing
small tasks on the way.
You need to reuse your implementation of the `GameBoard` interface from
the previous task.

After finishing the game you can play it yourself by running `main` function
in <a href="psi_element://PlayGame2048.kt">ui/PlayGame2048</a>.

### Game 2048

If you're unfamiliar with Game 2048, you can first spend some time playing it
[online](http://2048game.com/). Just don't forget about the assignment itself!

Initially, you see two numbers on the board (each one might be 2 or 4). 
The numbers can be moved to any of the four directions: up, down,
right or left. On each move, the neighboring numbers of the same value are
merged producing a new doubled number. After the move, a new element of
the value 2 or 4 is added to a random free cell.
Note that only the numbers which are equal to the power of 2 can be present
on the board.
If the board is full, the game is over.
The goal is to get 2048.
 
* Implement the functionality that moves the content in 
one row or column: removes empty cells and merges identical elements.
To get you familiar with lambdas and generics, this functionality is
declared as a generic function that accepts as argument, a method to merge two equal elements.  
Source: <a href="psi_element://Game2048Helper.kt">Game2048Helper.kt</a>; 
tests: <a href="psi_element://games.game2048.TestGame2048Helper">TestGame2048Helper.kt</a>.
 
* Specify how the new element should be added.
By the rules of the game 2048, the new cell with the value 2 or 4 
(the latter in 10% of the cases) is added to a random empty cell.  
Source: <a href="psi_element://Game2048Initializer.kt">Game2048Initializer.kt</a>; 
tests: <a href="psi_element://games.game2048.TestGame2048Initializer">TestGame2048Initializer.kt</a>. 

* You can find the main game process in the <a href="psi_element://games.game2048.Game2048">Game2048</a> class.
Implement the utility function `addNewValue` that adds a new value to 
a random free cell. The `initializer` parameter returns both a value and a cell
that the new value should be added to.  
Source: <a href="psi_element://Game2048.kt">Game2048.kt</a>; 
tests: <a href="psi_element://games.game2048.TestAddingValue">TestAddingValue.kt</a>. 

* Implement the utility function `moveValuesInRowOrColumn`, which 
updates the map contents by moving the elements only in one row or column.  
Source: <a href="psi_element://Game2048.kt">Game2048.kt</a>;
tests: <a href="psi_element://games.game2048.TestMoveValuesInRowOrColumn">TestMoveValuesInRowOrColumn.kt</a>.

* Implement the remaining function `moveValues`, which moves all the elements
in a board to a given direction following the rule games.  
Source: <a href="psi_element://Game2048.kt">Game2048.kt</a>;
tests:  <a href="psi_element://games.game2048.TestMoveValues">TestMoveValues.kt</a>. 