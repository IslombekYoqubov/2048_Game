package uz.gita.firstlesson.a2048game.screens

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import uz.gita.firstlesson.a2048game.R

class MainScreen : Fragment(R.layout.main_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
        if(pref.getInt("score", 0) == 0) view.findViewById<TextView>(R.id.btnResume).visibility = GONE
        view.findViewById<TextView>(R.id.btnResume).setOnClickListener {
            val args = Bundle()
            val fr = GameScreen()
            args.putInt("KEY", 1)
            fr.arguments = args
            parentFragmentManager.beginTransaction()
                .replace(R.id.myContainer, fr)
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<Button>(R.id.btnPlay).setOnClickListener {
            val args = Bundle()
            val fr = GameScreen()
            args.putInt("KEY", 0)
            fr.arguments = args
            parentFragmentManager.beginTransaction()
                .replace(R.id.myContainer, fr)
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnInfo).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.myContainer, InfoScreen())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnExit).setOnClickListener {
            requireActivity().finishAffinity()
        }
    }
}