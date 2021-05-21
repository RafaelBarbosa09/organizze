package br.edu.infnet.organizze.model

class Usuario {
    var nome: String ?= null
    var email: String ?= null
    var senha: String ?= null

    override fun toString(): String {
        return "Usuario(nome=$nome, email=$email, senha=$senha)"
    }
}