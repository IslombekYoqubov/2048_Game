package uz.gita.firstlesson.a2048game.animation

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import uz.gita.firstlesson.a2048game.R
import kotlin.math.sqrt

class AnimationScreen2 : Fragment(R.layout.animation_task1) {
    private var isClicked = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.plus_btn).setOnClickListener {
            if(!isClicked){
                view.findViewById<ImageView>(R.id.plus_btn).animate().rotation(90f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn1).animate().alpha(1f).translationY(-300f).translationX(-300f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn5).animate().alpha(1f).translationY((300 * sqrt(2.0)).toFloat()).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn6).animate().alpha(1f).translationY(-(300* sqrt(2.0)).toFloat()).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn7).animate().alpha(1f).translationX(-(300* sqrt(2.0)).toFloat()).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn8).animate().alpha(1f).translationX((300 * sqrt(2.0)).toFloat()).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn2).animate().alpha(1f).translationX(-300f).translationY(300f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn3).animate().alpha(1f).translationY(300f).translationX(300f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn4).animate().alpha(1f).translationX(300f).translationY(-300f).setDuration(1000).start()
            } else {
                view.findViewById<ImageView>(R.id.plus_btn).animate().rotation(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn8).animate().translationX(0f).alpha(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn7).animate().translationX(0f).alpha(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn6).animate().translationY(0f).alpha(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn5).animate().translationY(0f).alpha(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn1).animate().translationX(0f).alpha(0f).translationY(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn2).animate().translationY(0f).alpha(0f).translationX(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn3).animate().translationX(0f).alpha(0f).translationY(0f).setDuration(1000).start()
                view.findViewById<ImageView>(R.id.plus_btn4).animate().translationX(0f).alpha(0f).translationY(0f).setDuration(1000).start()
            }
            isClicked = !isClicked
        }
    }
}