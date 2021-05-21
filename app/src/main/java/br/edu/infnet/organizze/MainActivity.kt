package br.edu.infnet.organizze

import android.content.Intent
import android.os.Bundle
import android.view.View
import br.edu.infnet.organizze.activity.CadastroActivity
import br.edu.infnet.organizze.activity.LoginActivity
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide

class MainActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isButtonBackVisible = false
        isButtonNextVisible = false

        addSlide(
            FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_3)
            .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.cadastro)
                .canGoForward(false)
                .build()
        )
    }

    fun logar(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun cadastrar(view: View) {
        startActivity(Intent(this, CadastroActivity::class.java))
    }
}