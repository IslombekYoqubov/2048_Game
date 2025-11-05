package uz.gita.firstlesson.a2048game.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import uz.gita.firstlesson.a2048game.AppRepository
import uz.gita.firstlesson.a2048game.MyTouchListener
import uz.gita.firstlesson.a2048game.R
import uz.gita.firstlesson.a2048game.enums.SideEnum
import uz.gita.firstlesson.a2048game.getBackgroundRes

class GameScreen : Fragment(R.layout.game_screen) {
    private val views = ArrayList<ArrayList<TextView>>()
    private lateinit var repository : AppRepository
    private lateinit var container: LinearLayoutCompat

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = AppRepository(requireContext())
        container = view.findViewById(R.id.container)
        if (arguments?.getInt("KEY", 0) == 0) {
            repository.restartGame()
        } else {
            repository.loadGame()
        }
        view.findViewById<TextView>(R.id.tvCurrentScore).text = repository.getScore().toString()
        view.findViewById<TextView>(R.id.tvMaxScore).text = repository.getMaxScore().toString()
        view.findViewById<ImageView>(R.id.btnRestart).setOnClickListener {
            repository.restartGame()
            showMatrix()
        }
        loadViews()
        showMatrix()
        val myTouchListener = MyTouchListener(requireContext())
        myTouchListener.setMoveListener {
            when (it) {
                SideEnum.RIGHT -> repository.moveRight()
                SideEnum.LEFT -> repository.moveLeft()
                SideEnum.UP -> repository.moveUp()
                SideEnum.DOWN -> repository.moveDown()
            }
            view.findViewById<TextView>(R.id.tvCurrentScore).text = repository.getScore().toString()
            view.findViewById<TextView>(R.id.tvMaxScore).text = repository.getMaxScore().toString()
            showMatrix()
            if (repository.isGameOver()) {
                showDialog()
            }
        }
        container.setOnTouchListener(myTouchListener)
    }

    private fun loadViews() {
        for (i in 0 until 4) {
            val ls = ArrayList<TextView>(4)
            val linearRef = container.getChildAt(i) as LinearLayoutCompat
            for (j in 0 until 4) {
                ls.add(linearRef.getChildAt(j) as TextView)
            }
            views.add(ls)
        }
    }

    private fun showMatrix() {
        val matrix = repository.getMatrix()
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                views[i][j].text = if (matrix[i][j] != 0) matrix[i][j].toString() else ""
                views[i][j].setBackgroundResource(matrix[i][j].getBackgroundRes())
            }
        }
    }

    private fun showDialog(){
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.win_dialog, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(view)
            .setCancelable(false)
            .create()
        view.findViewById<ImageView>(R.id.home).setOnClickListener {
            parentFragmentManager.popBackStack()
            dialog.dismiss()
        }
        view.findViewById<ImageView>(R.id.restart).setOnClickListener {
            repository.restartGame()
            showMatrix()
            dialog.dismiss()
        }
        dialog.show()
    }
}