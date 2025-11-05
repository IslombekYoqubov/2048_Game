package uz.gita.firstlesson.a2048game.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import uz.gita.firstlesson.a2048game.R


class AnimationScreen1 : Fragment(R.layout.animation_task) {
    private var isClicked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.plus_btn).setOnClickListener {
            if(!isClicked){
                view.findViewById<ImageView>(R.id.plus_btn).animate().rotation(90f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn1).animate().translationY(-600f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn2).animate().translationY(-450f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn3).animate().translationY(-300f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn4).animate().translationY(-150f).setDuration(1000).start()
            } else {
                view.findViewById<ImageView>(R.id.plus_btn).animate().rotation(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn1).animate().translationY(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn2).animate().translationY(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn3).animate().translationY(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn4).animate().translationY(0f).setDuration(1000).start()
            }
            isClicked = !isClicked
        }

        view.findViewById<ImageView>(R.id.plus_btn1).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.myContainer, AnimationScreen2())
                .addToBackStack(null)
                .commit()
        }
    }
}