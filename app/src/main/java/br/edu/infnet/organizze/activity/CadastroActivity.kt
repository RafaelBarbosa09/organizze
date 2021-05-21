package br.edu.infnet.organizze.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.edu.infnet.organizze.R
import br.edu.infnet.organizze.model.Usuario
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import java.lang.Exception

class CadastroActivity : AppCompatActivity() {

    var autenticacao: FirebaseAuth ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_NoBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val nome = findViewById<EditText>(R.id.editNome)
        val email = findViewById<EditText>(R.id.editEmail)
        val senha = findViewById<EditText>(R.id.editSenha)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            val textoNome = nome.text.toString()
            val textoEmail = email.text.toString()
            val textoSenha = senha.text.toString()

            when {
                textoNome.isEmpty() -> {
                    Toast.makeText(this, "Preencha o campo nome", Toast.LENGTH_SHORT).show()
                }
                textoEmail.isEmpty() -> {
                    Toast.makeText(this, "Preencha o campo e-mail", Toast.LENGTH_SHORT).show()
                }
                textoSenha.isEmpty() -> {
                    Toast.makeText(this, "Preencha o campo senha", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val usuario = Usuario()

                    usuario.nome = textoNome
                    usuario.email = textoEmail
                    usuario.senha = textoSenha

                    cadastrarUsuario(usuario)
                }
            }
        }
    }

    fun cadastrarUsuario(usuario: Usuario) {
        if(autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance()
        }
        autenticacao!!.createUserWithEmailAndPassword(
            usuario.email.toString(),
            usuario.senha.toString()
        ).addOnCompleteListener(this, OnCompleteListener {
            task ->
                if (task.isSuccessful) {
                        Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    var excecao: String = ""

                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        excecao = "Digite uma senha mais forte"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        excecao = "Digite um email válido"
                    } catch (e: FirebaseAuthUserCollisionException) {
                        excecao = "Email já cadastrado"
                    } catch (e: Exception) {
                        excecao = "Erro ao cadastrar usuário"
                    }
                    Toast.makeText(this, excecao, Toast.LENGTH_SHORT).show()
                }
        })
    }
}