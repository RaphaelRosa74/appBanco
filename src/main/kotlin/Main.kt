import repository.DatabaseManager
import repository.UsuarioRepository
import service.UsuarioService
import util.Menu

fun main() {
    val dbManager = DatabaseManager.create()
    val usuarioRepository = UsuarioRepository(dbManager.getConnection())
    val usuarioService = UsuarioService(usuarioRepository)
    val menu = Menu(usuarioService)

    try {
        menu.exibirMenu()
    } catch (e: Exception) {
        println("Ocorreu um erro inesperado: ${e.message}")
    } finally {
        dbManager.close()
    }
}