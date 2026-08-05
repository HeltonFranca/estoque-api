package br.com.helton.estoque.controller;

import br.com.helton.estoque.dto.CadastroUsuarioRequest;
import br.com.helton.estoque.dto.LoginRequest;
import br.com.helton.estoque.dto.LoginResponse;
import br.com.helton.estoque.dto.UsuarioResponse;
import br.com.helton.estoque.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)

        public UsuarioResponse cadastrar(
            @Valid @RequestBody CadastroUsuarioRequest request
    ) {
        return authService.cadastrar(request);
    }

        @PostMapping("/login") 
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
}


