package repository

import model.Usuario
import java.sql.SQLException

class UsuarioRepository(private val connection: Connection) {
    
    fun adicionar(usuario: Usuario): Boolean {
        val sql = "INSERT INTO usuarios (nome, email, data_criacao) VALUES (?, ?, ?)"
        
        return try {
            connection.prepareStatement(sql).use { stmt ->
                stmt.setString(1, usuario.nome)
                stmt.setString(2, usuario.email)
                stmt.setString(3, usuario.dataCriacao)
                stmt.executeUpdate() > 0
            }
        } catch (e: SQLException) {
            false
        }
    }

    fun listarTodos(): List<Usuario> {
        val sql = "SELECT id, nome, email, data_criacao FROM usuarios"
        val usuarios = mutableListOf<Usuario>()
        
        try {
            connection.createStatement().use { stmt ->
                stmt.executeQuery(sql).use { rs ->
                    while (rs.next()) {
                        usuarios.add(
                            Usuario(
                                id = rs.getInt("id"),
                                nome = rs.getString("nome"),
                                email = rs.getString("email"),
                                dataCriacao = rs.getString("data_criacao")
                            )
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            System.err.println("Erro ao listar usuários: ${e.message}")
        }
        
        return usuarios
    }

    fun buscarPorId(id: Int): Usuario? {
        val sql = "SELECT id, nome, email, data_criacao FROM usuarios WHERE id = ?"
        
        return try {
            connection.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        Usuario(
                            id = rs.getInt("id"),
                            nome = rs.getString("nome"),
                            email = rs.getString("email"),
                            dataCriacao = rs.getString("data_criacao")
                        )
                    } else {
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            System.err.println("Erro ao buscar usuário: ${e.message}")
            null
        }
    }

    fun atualizar(usuario: Usuario): Boolean {
        val sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?"
        
        return try {
            connection.prepareStatement(sql).use { stmt ->
                stmt.setString(1, usuario.nome)
                stmt.setString(2, usuario.email)
                stmt.setInt(3, usuario.id)
                stmt.executeUpdate() > 0
            }
        } catch (e: SQLException) {
            false
        }
    }

    fun remover(id: Int): Boolean {
        val sql = "DELETE FROM usuarios WHERE id = ?"
        
        return try {
            connection.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate() > 0
            }
        } catch (e: SQLException) {
            false
        }
    }
}