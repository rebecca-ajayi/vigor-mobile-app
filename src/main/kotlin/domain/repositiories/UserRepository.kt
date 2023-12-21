package domain.repositiories

import domain.entities.User

interface UserRepository {
    fun saveUser(user: User) : User
    fun updateUser(user: User) : Boolean
    fun deleteUser(userId: Int) : Boolean
    fun findUserById(userId: Int) : User?
    fun findUserByUsername(userUsername: String) : User?
    fun findUserByEmail(userEmail: String) : User?
}