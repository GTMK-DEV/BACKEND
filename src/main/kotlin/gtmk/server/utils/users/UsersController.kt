package gtmk.server.utils.users

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
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
        @RequestBody req: UsersSignUpReq) {
        usersService.signUp(req)
    }

    @PostMapping("/login")
    fun signIn(
        @RequestBody req: UsersSignInReq) {
        usersService.signIn(req)
    }

}