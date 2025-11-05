package uz.gita.firstlesson.a2048game

import android.content.Context
import kotlin.random.Random

class AppRepository(private val context: Context) {
    private val prefs = context.getSharedPreferences("MYPREF", Context.MODE_PRIVATE)

    private var matrix = Array(4) { Array(4) { 0 } }
    private var score = 0
    private var maxScore = 0

    init {
        loadGame()
        if (matrix.all { row -> row.all { it == 0 } }) {
            addElement()
            addElement()
        }
    }

    fun getMatrix() = matrix
    fun getScore() = score
    fun getMaxScore() = maxScore
    fun moveLeft() { move { i, j -> Pair(i, j) } }
    fun moveRight() { move { i, j -> Pair(i, 3 - j) } }
    fun moveUp() { move { i, j -> Pair(j, i) } }
    fun moveDown() { move { i, j -> Pair(3 - j, i) } }

    private fun move(indexer: (Int, Int) -> Pair<Int, Int>) {
        val newMatrix = Array(4) { Array(4) { 0 } }
        for (i in 0 until 4) {
            val list = mutableListOf<Int>()
            var merged = false
            for (j in 0 until 4) {
                val (x, y) = indexer(i, j)
                val value = matrix[x][y]
                if (value == 0) continue
                if (list.isNotEmpty() && list.last() == value && !merged) {
                    list[list.lastIndex] *= 2
                    score += list[list.lastIndex]
                    if (score > maxScore) maxScore = score
                    merged = true
                } else {
                    list.add(value)
                    merged = false
                }
            }
            for (k in list.indices) {
                val (x, y) = indexer(i, k)
                newMatrix[x][y] = list[k]
            }
        }
        matrix = newMatrix
        if (!isGameOver()) addElement()
        saveGame()
    }
    private fun addElement() {
        val empty = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until 4) for (j in 0 until 4) if (matrix[i][j] == 0) empty.add(Pair(i, j))
        if (empty.isEmpty()) return
        val (x, y) = empty[Random.nextInt(empty.size)]
        matrix[x][y] = 2
        saveGame()
    }
    fun restartGame() {
        matrix = Array(4) { Array(4) { 0 } }
        score = 0
        addElement()
        addElement()
        saveGame()
    }
    private fun saveGame() {
        val editor = prefs.edit()
        editor.putString("matrix", matrix.joinToString(";") { it.joinToString(",") })
        editor.putInt("score", score)
        editor.putInt("maxScore", maxScore)
        editor.apply()
    }

     fun loadGame() {
        val matrixString = prefs.getString("matrix", null) ?: return
        val rows = matrixString.split(";")
        for (i in 0 until 4) {
            val cols = rows[i].split(",")
            for (j in 0 until 4) {
                matrix[i][j] = cols[j].toInt()
            }
        }
        score = prefs.getInt("score", 0)
        maxScore = prefs.getInt("maxScore", 0)

    }
    fun isGameOver(): Boolean {
        for (i in 0 until 4) for (j in 0 until 4) if (matrix[i][j] == 0) return false
        for (i in 0 until 4) for (j in 0 until 4) {
            val current = matrix[i][j]
            if (i < 3 && matrix[i + 1][j] == current) return false
            if (j < 3 && matrix[i][j + 1] == current) return false
        }
        return true
    }
}