package org.kranj.vtrci.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID


@Entity
@Table(name = "users")
class UserEntity(

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID(),

    var email: String = "",

    @ManyToOne(fetch = FetchType.EAGER)
    var organization: Organization?,

    private var username: String = "",

    private var password: String = "",

    @Enumerated(EnumType.STRING)
    var role: RoleEnum = RoleEnum.USER

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(this.role.name))
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}

enum class RoleEnum {
    USER, ADMIN, SCHOOL, PRODUCER, JUJE
}