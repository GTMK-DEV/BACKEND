package gtmk.server.repository.users

import gtmk.server.domain.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users, Long> {


    fun findByPhoneNumberAndPassword(phoneNumber: String, password: String): Users?

    fun findByPhoneNumber(phoneNumber: String): Users?

    fun existsByPhoneNumber(phoneNumber: String): Boolean


}
