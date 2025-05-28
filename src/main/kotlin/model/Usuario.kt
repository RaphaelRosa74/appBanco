package model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Usuario(
    val id: Int = 0,
    val nome: String,
    val email: String,
    val dataCriacao: String = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
) {
    init {
        require(nome.isNotBlank()) { "Nome não pode ser vazio" }
        require(email.isNotBlank()) { "Email não pode ser vazio" }
    }
}