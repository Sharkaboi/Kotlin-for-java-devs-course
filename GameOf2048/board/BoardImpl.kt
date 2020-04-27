package board

import board.Direction.*
import java.lang.IllegalArgumentException

fun createSquareBoard(width: Int): SquareBoard {
    val squareBoard = SquareBoardClass(width)
    squareBoard.array2d = createEmptyBoard(width)
    return squareBoard
}

fun <T> createGameBoard(width: Int): GameBoard<T> {
    val gameBoard = GameBoardClass<T>(width)
    gameBoard.array2d= createEmptyBoard(width)
    gameBoard.array2d.forEach {
        it.forEach { c ->
            gameBoard.cellValues[c] = null
        }
    }
    return gameBoard
}

class GameBoardClass<T>(override val width: Int) : SquareBoardClass(width), GameBoard<T> {

    var cellValues = mutableMapOf<Cell, T?>()

    override fun get(cell: Cell): T? {
        return cellValues[cell]
    }

    override fun set(cell: Cell, value: T?) {
        cellValues[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return cellValues.filterValues { predicate.invoke(it) }.keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return cellValues.filter { predicate.invoke(it.value) }.keys.first()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return cellValues.values.any(predicate)
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return cellValues.values.all(predicate)
    }


}

open class SquareBoardClass(override val width: Int) : SquareBoard {

    var array2d: Array<Array<Cell>> = arrayOf(arrayOf())

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return when {
            i > width || j > width || i == 0 || j == 0 -> null
            else -> array2d[i - 1][j - 1]
        }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return when {
            i > width || j > width || i == 0 || j == 0 -> throw IllegalArgumentException()
            else -> array2d[i - 1][j - 1]
        }
    }

    override fun getAllCells(): Collection<Cell> {
        val tempList = mutableListOf<Cell>()
        for (i in 0 until width) {
            for (j in 0 until width) {
                tempList.add(array2d[i][j])
            }
        }
        return tempList
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return when{
            jRange.last > width -> IntRange(jRange.first, width).map { j:Int -> getCell(i, j) }.toList()
            else -> jRange.map { j: Int -> getCell(i, j) }.toList()
        }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return when {
            iRange.last > width -> IntRange(iRange.first, width).map { i:Int -> getCell(i, j) }.toList()
            else -> iRange.map { i: Int -> getCell(i, j) }.toList()
        }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> getCellOrNull(i - 1, j)
            LEFT -> getCellOrNull(i, j - 1)
            DOWN -> getCellOrNull(i + 1, j)
            RIGHT -> getCellOrNull(i, j + 1)
        }
    }
}

private fun createEmptyBoard(w: Int): Array<Array<Cell>> {
    var final2dArray = arrayOf<Array<Cell>>()
    for (i in 1..w) {
        var tempList = arrayOf<Cell>()
        for (j in 1..w) {
            tempList += Cell(i, j)
        }
        final2dArray += tempList
    }
    return final2dArray
}