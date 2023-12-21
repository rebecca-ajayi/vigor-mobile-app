package adapters.web

//import application.dto.UserDataDTO
//import domain.UserService
//import io.ktor.client.request.*
//import io.ktor.client.statement.*
//import domain.entities.User
//import io.ktor.http.*

class UserController {

//    fun Route.userController(userService: UserService) {
//        post("/users/register") {
//            val registrationData = call.receive<User>() // Replace with your data class
//            try {
//                val user = userService.registerUser(User)
//                call.respond(HttpStatusCode.Created, user)
//            } catch (e: Exception) {
//                // Handle exceptions (e.g., user already exists)
//                call.respond(HttpStatusCode.BadRequest, e.message ?: "Unknown error")
//            }
//        }
//    }

//    fun register(userData: UserDataDTO): HttpResponse {
//        try {
//            val newUser = userService.register(mapToUserDomain(userData))
//            return HttpResponse.ok(newUser.id)
//        } catch (e: Exception) {
//            return HttpResponse.error("Registration failed: ${e.message}")
//        }
//    }
}