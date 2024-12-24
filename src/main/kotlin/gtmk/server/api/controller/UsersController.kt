package gtmk.server.api.controller

import gtmk.server.api.dto.users.TokenInfoRes
import gtmk.server.service.UsersService
import gtmk.server.api.dto.users.UsersSignInReq
import gtmk.server.api.dto.users.UsersSignUpReq
import gtmk.server.domain.UserType
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UsersController(
    val usersService: UsersService
) {

    @PostMapping("/signUp")
    fun singUp(
        @RequestBody req: UsersSignUpReq
    ) {
        usersService.signUp(UserType.USER,req)
    }

    @PostMapping("/login")
    fun signIn(
        @RequestBody req: UsersSignInReq
    ):TokenInfoRes {
       return usersService.signIn(UserType.USER,req)
    }

}