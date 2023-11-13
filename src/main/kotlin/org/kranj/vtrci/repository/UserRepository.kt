package org.kranj.vtrci.repository


import org.kranj.vtrci.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByUsername(username: String): UserEntity?

    fun findByUsernameOrEmail(username: String, email: String): UserEntity?
}