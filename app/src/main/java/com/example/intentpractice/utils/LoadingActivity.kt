package com.example.intentpractice.utils

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.intentpractice.R
import com.example.intentpractice.databinding.ActivityLoadingBinding
import com.example.intentpractice.databinding.ActivityMainMenuBinding
import com.example.intentpractice.presentation.ui.activities.MainMenu
import kotlinx.coroutines.*

class LoadingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingBinding

    private lateinit var loadingImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingImage: ImageView = binding.silverware

        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        loadingImage.startAnimation(rotateAnimation)

        // Aguarde um tempo para simular a animação de carregamento
        GlobalScope.launch {
            delay(2000) // Duração da animação
            startActivity(Intent(this@LoadingActivity, MainMenu::class.java))
            finish()
        }
    }
}
