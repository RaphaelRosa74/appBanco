package util

object Validacao {
    fun validarEmail(email: String): Boolean {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return regex.matches(email)
    }

    fun validarNome(nome: String): Boolean {
        return nome.length in 3..100 && nome.matches(Regex("^[a-zA-ZÀ-ú ]+$"))
    }
}