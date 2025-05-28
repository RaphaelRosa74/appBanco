package repository

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseManager private constructor(private val connection: Connection) {
    companion object {
        private const val DATABASE_URL = "jdbc:sqlite:meubanco.db"
        
        fun create(): DatabaseManager {
            val connection = DriverManager.getConnection(DATABASE_URL)
            return DatabaseManager(connection).also { it.criarTabelas() }
        }
    }

    private fun criarTabelas() {
        val sql = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                data_criacao TEXT NOT NULL
            );
        """.trimIndent()
        
        try {
            connection.createStatement().execute(sql)
        } catch (e: SQLException) {
            System.err.println("Erro ao criar tabelas: ${e.message}")
            throw e
        }
    }

    fun getConnection(): Connection = connection

    fun close() {
        try {
            if (!connection.isClosed) {
                connection.close()
            }
        } catch (e: SQLException) {
            System.err.println("Erro ao fechar conexão: ${e.message}")
        }
    }
}