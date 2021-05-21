package br.edu.infnet.organizze.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.infnet.organizze.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NoBar)
        setContentView(R.layout.activity_login)
    }
}