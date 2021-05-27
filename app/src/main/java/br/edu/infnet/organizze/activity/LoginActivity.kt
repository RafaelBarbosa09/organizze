package br.edu.infnet.organizze.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.edu.infnet.organizze.R
import br.edu.infnet.organizze.model.Usuario
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.*
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    var autenticacao: FirebaseAuth ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NoBar)
        setContentView(R.layout.activity_login)

        var email = findViewById<EditText>(R.id.iptEmail)
        var senha = findViewById<EditText>(R.id.iptSenha)
        var btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val textoEmail = email.text.toString()
            val textoSenha = senha.text.toString()

            when {
                textoEmail.isEmpty() -> {
                    Toast.makeText(this, "Preencha o campo e-mail", Toast.LENGTH_SHORT).show()
                }
                textoSenha.isEmpty() -> {
                    Toast.makeText(this, "Preencha o campo senha", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val usuario = Usuario()

                    usuario.email = textoEmail
                    usuario.senha = textoSenha

                    fazerLogin(usuario)
                }
            }
        }
    }

    fun fazerLogin(usuario: Usuario) {

        if(autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance()
        }

        autenticacao!!.signInWithEmailAndPassword(
            usuario.email.toString(),
            usuario.senha.toString()
        ).addOnCompleteListener(this, OnCompleteListener {
            task ->
                if(task.isSuccessful) {
                    Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show()
                } else {
                    var excecao = try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidUserException) {
                        "Usuário não cadastrado"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        "Email ou senha não correspondem"
                    } catch (e: Exception) {
                        "Erro ao fazer login"
                    }
                    Toast.makeText(this, excecao, Toast.LENGTH_SHORT).show()
                }
        })
    }
}