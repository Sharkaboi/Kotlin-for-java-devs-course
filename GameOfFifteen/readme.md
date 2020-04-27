## Game of 15

The goal of this assignment is to implement one game: 
[Game of Fifteen](https://en.wikipedia.org/wiki/15_puzzle).

The games are partly implemented already, so your task is to finish the
implementation by following the step by step guide and doing
small tasks on the way.
You need to reuse your implementation of the `GameBoard` interface from
the previous task.

After finishing the game you can play it yourself by running `main` function
<a href="psi_element://PlayGameOfFifteen.kt">ui/PlayGame2048</a>.

### Game of Fifteen

The board for the game of Fifteen is filled randomly with numbers from 1 to 15 and
one empty space. You can move the neighboring value to the empty space.
The goal is to get the sorted sequence from 1 to 15.

You can check the Game of Fifteen online 
[here](http://migo.sixbit.org/puzzles/fifteen/).
Note that in the implementation for this assignment, the values are moved
by arrows rather than mouse clicks.

* Game of Fifteen is solvable only if the initial permutation of numbers
is [even](https://en.wikipedia.org/wiki/Parity_of_a_permutation).
First, implement the function `isEven` declared in 
<a href="psi_element://GameOfFifteenHelper.kt">GameOfFifteenHelper.kt</a>
checking whether a permutation is even or odd.
Source: <a href="psi_element://GameOfFifteenHelper.kt">GameOfFifteenHelper.kt</a>;
tests:  <a href="psi_element://games.gameOfFifteen.TestGameOfFifteenHelper">TestGameOfFifteenHelper.kt</a>. 

You can use the following algorithm to check the given permutation.
Let `P` is a permutation function on a range of numbers `1..n`.
For a pair `(i, j)` of elements such that `i < j` , if `P(i) > P(j)`,
then the permutation is said to invert the order of `(i, j)`.
The number of such inverted pairs is the _parity_ of the permutation.
If permutation inverts even number of such pairs, it is an even permutation; else
it is an odd permutation.

* Use the `isEven` function to produce only solvable permutations as initial
permutations.
Source: <a href="psi_element://GameOfFifteenInitializer.kt">GameOfFifteenInitializer.kt</a>;
tests:  <a href="psi_element://games.gameOfFifteen.TestGameOfFifteenInitializer">TestGameOfFifteenInitializer.kt</a>.

* Implement the `GameOfFifteen` class from scratch describing the game process.
It should implement the `Game` interface and make use of `initializer` argument.
Note that this argument is used in tests to provide a different initial permutation.
Source: <a href="psi_element://GameOfFifteen.kt">GameOfFifteen.kt</a>;
tests:  <a href="psi_element://games.gameOfFifteen.TestGameOfFifteen">TestGameOfFifteen.kt</a>.
