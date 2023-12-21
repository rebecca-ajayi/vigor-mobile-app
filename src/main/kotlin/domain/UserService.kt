package domain

import domain.entities.User
import domain.repositiories.UserRepository
import org.mindrot.jbcrypt.BCrypt

class UserService(private val userRepository: UserRepository) {
    fun registerUser(user: User): User {
        validateUserData(user)
        val hashedPassword = hashPassword(user.passwordHash)
        val newUser = user.copy(passwordHash = hashedPassword)
        return userRepository.saveUser(newUser)
    }

    private fun validateUserData(user: User) {
        if (userRepository.findUserByUsername(user.username) != null) {
            throw IllegalArgumentException("Username already taken")
        }

        if (userRepository.findUserByEmail(user.email) != null) {
            throw IllegalArgumentException("Email already in use")
        }

        validatePasswordStrength(user.passwordHash)
    }

    private fun validatePasswordStrength(password: String) {
        if (password.length < 8) {
            throw IllegalArgumentException("Password must be at least 8 characters long")
        }

        if (!password.any { it.isDigit() }) {
            throw IllegalArgumentException("Password must contain at least one digit")
        }

        if (!password.any { it.isUpperCase() }) {
            throw IllegalArgumentException("Password must contain at least one uppercase letter")
        }

        if (!password.any { it.isLowerCase() }) {
            throw IllegalArgumentException("Password must contain at least one lowercase letter")
        }

        if (!password.any { !it.isLetterOrDigit() }) {
            throw IllegalArgumentException("Password must contain at least one special character")
        }
    }

    private fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
}