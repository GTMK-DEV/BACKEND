package gtmk.server.repository.store

import gtmk.server.domain.Store

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : JpaRepository<Store, Long> {


    fun findByPhoneNumberAndPassword(phoneNumber: String, password: String): Store?

    fun findByPhoneNumber(phoneNumber: String): Store?

    fun existsByPhoneNumber(phoneNumber: String): Boolean


}
