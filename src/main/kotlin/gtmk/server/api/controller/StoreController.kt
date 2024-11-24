package gtmk.server.api.controller

import gtmk.server.api.dto.users.*
import gtmk.server.service.UsersService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
class StoreController(
    val usersService: UsersService
) {

    @PostMapping("/signUp")
    fun singUp(
        @RequestBody req: StoreSignUpReq,
    ) {
        usersService.signUp("store",req)
    }

    @PostMapping("/login")
    fun signIn(
        @RequestBody req: StoreSignInReq,
    ):TokenInfoRes {
        return usersService.signIn("store",req)
    }

}