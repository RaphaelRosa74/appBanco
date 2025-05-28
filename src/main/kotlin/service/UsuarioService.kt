package service

import model.Usuario
import repository.UsuarioRepository
import util.Validacao

class UsuarioService(private val repository: UsuarioRepository) {

    fun cadastrarUsuario(nome: String, email: String): Boolean {
        if (!Validacao.validarEmail(email)) {
            println("Email inválido!")
            return false
        }

        if (nome.isBlank()) {
            println("Nome não pode ser vazio!")
            return false
        }

        val usuario = Usuario(nome = nome, email = email)
        return repository.adicionar(usuario)
    }

    fun listarUsuarios(): List<Usuario> {
        return repository.listarTodos()
    }

    fun buscarUsuario(id: Int): Usuario? {
        return repository.buscarPorId(id)
    }

    fun atualizarUsuario(id: Int, novoNome: String, novoEmail: String): Boolean {
        if (!Validacao.validarEmail(novoEmail)) {
            println("Email inválido!")
            return false
        }

        if (novoNome.isBlank()) {
            println("Nome não pode ser vazio!")
            return false
        }

        val usuarioExistente = repository.buscarPorId(id)
        if (usuarioExistente == null) {
            println("Usuário não encontrado!")
            return false
        }

        val usuarioAtualizado = usuarioExistente.copy(
            nome = novoNome,
            email = novoEmail
        )

        return repository.atualizar(usuarioAtualizado)
    }

    fun removerUsuario(id: Int): Boolean {
        return repository.remover(id)
    }
}