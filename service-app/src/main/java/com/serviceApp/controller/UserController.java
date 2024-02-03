package com.serviceApp.controller;

import com.serviceApp.dto.LoginDto;
import com.serviceApp.dto.SignupDto;
import com.serviceApp.entity.User;
import com.serviceApp.repo.UserRepository;
import com.serviceApp.response.AuthenticateResponse;
import com.serviceApp.service.UserService;
import com.serviceApp.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER="Authorization";

    @PostMapping("/signUp")
    public ResponseEntity<?> saveUser(@RequestBody SignupDto user){
        System.out.println("user role ==="+user.getEmail());
        SignupDto savedUser=userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }



    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken (@RequestBody LoginDto loginDto) throws IOException {
        //System.out.println("getEmail===="+ loginDto.getEmail());
        //HttpServletResponse response;
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Invalid email");
        }
        //System.out.println("getEmail===="+ loginDto.getEmail());
        final UserDetails userDetails=userDetailsService.loadUserByUsername(loginDto.getEmail());
        final String token =jwtUtil.generateToken(userDetails.getUsername());
        //System.out.println("token===="+ token);
        Optional<User> userExists= userRepository.findByEmail(userDetails.getUsername());
        //System.out.println("userExists===="+ userExists);
        AuthenticateResponse response =new AuthenticateResponse();
        if(userExists.isPresent()){
            /*response.getWriter().write(new JSONObject()
                    .put("userID",userExists.get().getId())
                    .toString());*/
            response.setId(userExists.get().getId());
             response.setJwt(token);
         }



       // response.addHeader("Access-Control-Expose-Headers", "Authorization");
       // response.setHeader("Access-Control-Allow-Headers","Authorization,X-PINGOTHER, X-Requested-Width,Content-Type,Accept,X-Custom-header");
        //System.out.println(TOKEN_PREFIX +token);

        //response.setHeader(HEADER, TOKEN_PREFIX +token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
