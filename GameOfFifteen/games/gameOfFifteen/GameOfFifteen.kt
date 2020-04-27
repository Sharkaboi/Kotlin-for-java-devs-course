package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game {
    return GameOfFifteen(initializer)
}

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        val listOfAllCells = board.getAllCells()
        val initialPermutation = initializer.initialPermutation
        for (i in listOfAllCells.indices) {
            try {
                board[listOfAllCells.elementAt(i)] =initialPermutation[i]
            } catch (e: Exception) {
                board[listOfAllCells.elementAt(i)] = null
            }
        }
    }

    override fun canMove(): Boolean {
        return board.any { it == null }
    }

    override fun hasWon(): Boolean {
        var count = 0
        for (i in 1 until 16) {
            if (board[board.getAllCells().elementAt(i - 1)] != i)
                count++
        }
        return count == 0
    }

    override fun processMove(direction: Direction) {
        val listOfNullCells = board.filter { it == null }.first()
        when (direction) {
            Direction.UP -> {
                if (board.getCellOrNull(listOfNullCells.i + 1, listOfNullCells.j) != null) {
                    val t = board[board.getCell(listOfNullCells.i + 1, listOfNullCells.j)]
                    board[listOfNullCells] = t
                    board[board.getCell(listOfNullCells.i + 1, listOfNullCells.j)] = null
                }
            }
            Direction.DOWN -> {
                if (board.getCellOrNull(listOfNullCells.i - 1, listOfNullCells.j) != null) {
                    val t = board[board.getCell(listOfNullCells.i - 1, listOfNullCells.j)]
                    board[listOfNullCells] = t
                    board[board.getCell(listOfNullCells.i - 1, listOfNullCells.j)] = null
                }
            }
            Direction.RIGHT -> {
                if (board.getCellOrNull(listOfNullCells.i, listOfNullCells.j - 1) != null) {
                    val t = board.get(board.getCell(listOfNullCells.i, listOfNullCells.j - 1))
                    board[listOfNullCells] = t
                    board[board.getCell(listOfNullCells.i, listOfNullCells.j - 1)] = null
                }
            }
            Direction.LEFT -> {
                if (board.getCellOrNull(listOfNullCells.i, listOfNullCells.j + 1) != null) {
                    val t = board[board.getCell(listOfNullCells.i, listOfNullCells.j + 1)]
                    board[listOfNullCells] = t
                    board[board.getCell(listOfNullCells.i, listOfNullCells.j + 1)] = null
                }
            }
        }
    }

    override fun get(i: Int, j: Int): Int? = board[board.getCell(i, j)]
}

private fun GameBoard<Int?>.initialize(initializer: GameOfFifteenInitializer) {

}

