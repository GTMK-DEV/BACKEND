//package org.example.hmsspringboot.utils.web
//
//import org.example.hmsspringboot.utils.auth.JwtAuthenticationFilter
//import org.example.hmsspringboot.utils.auth.JwtTokenProvider
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.ComponentScan
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.web.AuthenticationEntryPoint
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//import org.springframework.web.cors.CorsConfiguration
//import org.springframework.web.cors.CorsConfigurationSource
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource
//
//@ComponentScan
//@Configuration
//@EnableWebSecurity
//class WebSecurityConfig(
//        private val jwtTokenProvider: JwtTokenProvider,
//        private val entryPoint: AuthenticationEntryPoint,
//) {
//
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//
//        return http
//            .formLogin { it.disable() }
//            .httpBasic { it.disable() }
//            .csrf { it.disable() }
//            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//            .cors { it.configurationSource(corsConfigurationSource()) } // CORS 관련 설정
//            .authorizeHttpRequests {
//                it.requestMatchers("/actuator/health", "/users", "/users/login", "/users/token/reissue", "/error").permitAll()
//                .anyRequest().authenticated()
//
////                        "/api/member/find/**", "/api/member/signup/**", "/api/member/login/oauth2", "/api/member/login", "/api/member/token/refresh/issue")
////                    .anonymous()
////                    .requestMatchers("/api/member/**").hasRole("MEMBER")
////                    .anyRequest().permitAll()
////                    .requestMatchers(
//            }
//            .exceptionHandling { it.authenticationEntryPoint(entryPoint) }
//            .addFilterBefore(
//                    JwtAuthenticationFilter(jwtTokenProvider),
//                    UsernamePasswordAuthenticationFilter::class.java
//            )
//            .build()
//    }
//
//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource{
//        val configuration = CorsConfiguration()
//
//        //허용 url, 먼저 local로 돌리고 이후 완전 적용된 이후에 바꿀것
////        configuration.addAllowedOrigin("http://localhost:3000");
////        configuration.addAllowedOrigin("https://dev.hms.hprobot.ai");
////        configuration.addAllowedOrigin("https://hms.hprobot.ai");
//        configuration.addAllowedOriginPattern("*")
//        configuration.addAllowedMethod("*") // 허용할 Http Method
//        configuration.addAllowedHeader("*")
//        configuration.allowCredentials = true // 내 서버가 응답할 때 json을 js에서 처리할 수 있게 설정
//        configuration.maxAge = 3600L
//        //        configuration.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER);
//        val source = UrlBasedCorsConfigurationSource()
//        //접속 패턴
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }
//
//    @Bean
//    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
//        return BCryptPasswordEncoder()
//    }
//
//}