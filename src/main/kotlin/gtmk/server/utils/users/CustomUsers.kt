package gtmk.server.utils.users

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomUsers(
    userId: String,
    password: String,
    authorities: Collection<GrantedAuthority>
): User(userId, password, authorities) {
}