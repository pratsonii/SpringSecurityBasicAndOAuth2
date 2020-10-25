package com.pratik.controllers;

import com.pratik.dto.UserRequestDto;
import com.pratik.dao.UserDao;
import com.pratik.dto.UserResponseDto;
import com.pratik.models.User;
import com.pratik.services.CustomUserDetailsService;
import com.pratik.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/app")
public class AppControllers {

    @Autowired
    JwtTokenUtil jwtUtil;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @GetMapping("/token")
    public ResponseEntity<Map<String, String>> getName(Authentication auth){
        String token = jwtUtil.createToken((User) auth.getPrincipal());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/userInfo")
    public ResponseEntity<UserResponseDto> getUserInfo(Authentication auth){
        UserResponseDto dto = new UserResponseDto((User) auth.getPrincipal());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto dto){

        return userDetailsService.saveNewUser(new User(dto));

        /*
        User user = User.builder()
                        .email("pratik@gmail.com")
                        .firstName("Pratik")
                        .lastName("Soni")
                        .authType("basic")
                        .password(PasswordUtil.encode("pass"))
                        .roles(List.of("USER"))
                        .build();
         */
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdminInfo(){
        return ResponseEntity.ok("Admin access");
    }

}
