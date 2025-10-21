package com.g001.huertohogar_dam002d.data

class UserRepository(private val userDao: UserDao) {

    suspend fun register(user: User) {
        if (userDao.findByUsername(user.username) != null) {
            throw Exception("User already exists")
        }
        userDao.insert(user)
    }

    suspend fun login(username: String, password: String): User? {
        val user = userDao.findByUsername(username)
        if (user != null && user.password == password) {
            return user
        }
        return null
    }
}
