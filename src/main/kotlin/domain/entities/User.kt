package domain.entities

import java.time.LocalDate

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val passwordHash: String,
    val firstName: String,
    val lastName: String,
    val dob: LocalDate,
    val gender: String,
    val ethnicity: String,
    val sexuality: String,
    val country: String
)
