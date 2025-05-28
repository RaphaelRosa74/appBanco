package util

import model.Usuario
import service.UsuarioService

class Menu(private val usuarioService: UsuarioService) {

    fun exibirMenu() {
        while (true) {
            println("\n=== SISTEMA DE USUÁRIOS ===")
            println("1. Cadastrar usuário")
            println("2. Listar usuários")
            println("3. Buscar usuário por ID")
            println("4. Atualizar usuário")
            println("5. Remover usuário")
            println("6. Sair")
            print("Escolha uma opção: ")

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> cadastrarUsuario()
                2 -> listarUsuarios()
                3 -> buscarUsuario()
                4 -> atualizarUsuario()
                5 -> removerUsuario()
                6 -> {
                    println("Saindo do sistema...")
                    return
                }
                else -> println("Opção inválida! Tente novamente.")
            }
        }
    }

    private fun cadastrarUsuario() {
        println("\n--- CADASTRAR USUÁRIO ---")
        print("Nome: ")
        val nome = readlnOrNull() ?: ""
        print("Email: ")
        val email = readlnOrNull() ?: ""

        if (usuarioService.cadastrarUsuario(nome, email)) {
            println("Usuário cadastrado com sucesso!")
        } else {
            println("Falha ao cadastrar usuário. Verifique os dados e tente novamente.")
        }
    }

    private fun listarUsuarios() {
        println("\n--- LISTA DE USUÁRIOS ---")
        val usuarios = usuarioService.listarUsuarios()
        
        if (usuarios.isEmpty()) {
            println("Nenhum usuário cadastrado.")
        } else {
            usuarios.forEach { println("${it.id}: ${it.nome} - ${it.email} (${it.dataCriacao})") }
        }
    }

    private fun buscarUsuario() {
        println("\n--- BUSCAR USUÁRIO ---")
        print("ID do usuário: ")
        val id = readlnOrNull()?.toIntOrNull() ?: 0
        
        val usuario = usuarioService.buscarUsuario(id)
        if (usuario != null) {
            println("\nDados do usuário:")
            println("ID: ${usuario.id}")
            println("Nome: ${usuario.nome}")
            println("Email: ${usuario.email}")
            println("Data de criação: ${usuario.dataCriacao}")
        } else {
            println("Usuário não encontrado.")
        }
    }

    private fun atualizarUsuario() {
        println("\n--- ATUALIZAR USUÁRIO ---")
        print("ID do usuário: ")
        val id = readlnOrNull()?.toIntOrNull() ?: 0
        
        val usuario = usuarioService.buscarUsuario(id)
        if (usuario == null) {
            println("Usuário não encontrado.")
            return
        }
        
        println("\nDados atuais:")
        println("Nome: ${usuario.nome}")
        println("Email: ${usuario.email}")
        
        print("\nNovo nome (deixe em branco para manter): ")
        val novoNome = readlnOrNull()?.takeIf { it.isNotBlank() } ?: usuario.nome
        
        print("Novo email (deixe em branco para manter): ")
        val novoEmail = readlnOrNull()?.takeIf { it.isNotBlank() } ?: usuario.email
        
        if (usuarioService.atualizarUsuario(id, novoNome, novoEmail)) {
            println("Usuário atualizado com sucesso!")
        } else {
            println("Falha ao atualizar usuário.")
        }
    }

    private fun removerUsuario() {
        println("\n--- REMOVER USUÁRIO ---")
        print("ID do usuário: ")
        val id = readlnOrNull()?.toIntOrNull() ?: 0
        
        if (usuarioService.removerUsuario(id)) {
            println("Usuário removido com sucesso!")
        } else {
            println("Falha ao remover usuário ou usuário não encontrado.")
        }
    }
}