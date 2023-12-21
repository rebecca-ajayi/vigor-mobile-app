package adapters.persistence

import domain.entities.User
import domain.repositiories.UserRepository
import java.sql.*

class UserPersistenceAdapter(private val dbConnection: Connection) : UserRepository {
    override fun saveUser(user: User): User {
        val sql = """
        INSERT INTO users (username, email, password_hash, first_name, last_name, dob, gender, ethnicity, sexuality, country)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """
        dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { statement ->
            statement.setString(1, user.username)
            statement.setString(2, user.email)
            statement.setString(3, user.passwordHash)
            statement.setString(4, user.firstName)
            statement.setString(5, user.lastName)
            statement.setDate(6, Date.valueOf(user.dob))
            statement.setString(7, user.gender)
            statement.setString(8, user.ethnicity)
            statement.setString(9, user.sexuality)
            statement.setString(10, user.country)

            statement.executeUpdate()
            val resultSet = statement.generatedKeys
            if (resultSet.next()) {
                val generatedId = resultSet.getInt(1)
                return user.copy(id = generatedId)
            } else {
                throw SQLException("Creating user failed, no ID obtained.")
            }
        }
    }


    override fun updateUser(user: User): Boolean {
        val sql =
            "UPDATE users SET username = ?, email = ?, password_hash = ?, " +
                    "first_name = ?, last_name = ?, dob = ?, gender = ?, " +
                    "ethnicity = ?, sexuality = ?, country = ? " +
                    "WHERE id = ?"
        dbConnection.prepareStatement(sql).use { statement ->
            statement.setString(1, user.username)
            statement.setString(2, user.email)
            statement.setString(3, user.passwordHash)
            statement.setString(4, user.firstName)
            statement.setString(5, user.lastName)
            statement.setDate(6, Date.valueOf(user.dob))
            statement.setString(7, user.gender)
            statement.setString(8, user.ethnicity)
            statement.setString(9, user.sexuality)
            statement.setString(10, user.country)
            statement.setInt(11, user.id)

            val affectedRows = statement.executeUpdate()
            return affectedRows > 0
        }
    }

    override fun deleteUser(userId: Int): Boolean {
        val sql = "DELETE * FROM users WHERE id = ${userId}"
        dbConnection.prepareStatement(sql).use { statement ->
            val affectedRows = statement.executeUpdate()
            return affectedRows > 0
        }
    }

    override fun findUserById(userId: Int): User? {
        val sql = "SELECT * FROM users WHERE id = ?"
        dbConnection.prepareStatement(sql).use { statement ->
            statement.setInt(1, userId)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return mapRowToUser(resultSet)
            }
        }
        return null
    }

    override fun findUserByUsername(userUsername: String): User? {
        val sql = "SELECT * FROM users WHERE username = ${userUsername}"
        dbConnection.prepareStatement(sql).use { statement ->
            statement.setString(1, userUsername)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return mapRowToUser(resultSet)
            }
        }
        return null
    }

    override fun findUserByEmail(userEmail: String): User? {
        val sql = "SELECT * FROM users WHERE email = ${userEmail}"
        dbConnection.prepareStatement(sql).use { statement ->
            statement.setString(1, userEmail)
            val resultSet = statement.executeQuery()
            if (resultSet.next()) {
                return mapRowToUser(resultSet)
            }
        }
        return null
    }
}

private fun mapRowToUser(resultSet: ResultSet): User {
    return User(
        id = resultSet.getInt("id"),
        username = resultSet.getString("username"),
        email = resultSet.getString("email"),
        passwordHash = resultSet.getString("password_hash"),
        firstName = resultSet.getString("first_name"),
        lastName = resultSet.getString("last_name"),
        dob = resultSet.getDate("dob").toLocalDate(),
        gender = resultSet.getString("gender"),
        ethnicity = resultSet.getString("ethnicity"),
        sexuality = resultSet.getString("sexuality"),
        country = resultSet.getString("country")
    )
}