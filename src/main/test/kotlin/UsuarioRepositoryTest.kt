package repository

import model.Usuario
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.DriverManager
import kotlin.test.*

class UsuarioRepositoryTest {
    private lateinit var dbManager: DatabaseManager
    private lateinit var repository: UsuarioRepository
    
    @BeforeEach
    fun setUp() {
        // Usar banco de dados em memória para testes
        val connection = DriverManager.getConnection("jdbc:sqlite::memory:")
        dbManager = DatabaseManager(connection)
        repository = UsuarioRepository(dbManager.getConnection())
    }
    
    @AfterEach
    fun tearDown() {
        dbManager.close()
    }
    
    @Test
    fun `adicionar usuario deve retornar true quando sucesso`() {
        val usuario = Usuario(nome = "Teste", email = "teste@teste.com")
        assertTrue(repository.adicionar(usuario))
    }
    
    @Test
    fun `buscar por id deve retornar usuario quando existir`() {
        val usuario = Usuario(nome = "Teste", email = "teste@teste.com")
        repository.adicionar(usuario)
        
        val usuarioEncontrado = repository.buscarPorId(1)
        assertNotNull(usuarioEncontrado)
        assertEquals("Teste", usuarioEncontrado.nome)
    }
    
    @Test
    fun `listar todos deve retornar lista vazia quando nao houver usuarios`() {
        val usuarios = repository.listarTodos()
        assertTrue(usuarios.isEmpty())
    }
    
    @Test
    fun `remover usuario deve retornar true quando usuario existir`() {
        val usuario = Usuario(nome = "Teste", email = "teste@teste.com")
        repository.adicionar(usuario)
        
        assertTrue(repository.remover(1))
    }
}