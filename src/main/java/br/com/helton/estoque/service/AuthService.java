package br.com.helton.estoque.service;

import br.com.helton.estoque.dto.CadastroUsuarioRequest;
import br.com.helton.estoque.dto.LoginRequest;
import br.com.helton.estoque.dto.LoginResponse;
import br.com.helton.estoque.dto.UsuarioResponse;
import br.com.helton.estoque.entity.Usuario;
import br.com.helton.estoque.exception.CredenciaisInvalidasException;
import br.com.helton.estoque.exception.UsernameJaExisteException;
import br.com.helton.estoque.repository.UsuarioRepository;
import br.com.helton.estoque.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public UsuarioResponse cadastrar(CadastroUsuarioRequest request){


        if(usuarioRepository.existsByUsername(request.username())){


            throw new UsernameJaExisteException(request.username());


        }

        String senhaCriptografada =
            passwordEncoder.encode(request.senha());
        
        
        Usuario usuario = new Usuario(
            request.username(),
            senhaCriptografada
        );
        Usuario usuarioSalvo = usuarioRepository.save(usuario);


        return new UsuarioResponse(

            usuarioSalvo.getId(),
            usuarioSalvo.getUsername()
        );
    }


        public LoginResponse login(LoginRequest request){


            Usuario usuario = usuarioRepository
            .findByUsername(request.username())
            .orElseThrow(CredenciaisInvalidasException::new);


            boolean senhaCorreta = passwordEncoder.matches(
                request.senha(),
                usuario.getSenha()
            );


            if(!senhaCorreta){

                throw new CredenciaisInvalidasException();
                }
                        // Gera o token usando o username
        String token = jwtService.gerarToken(
                usuario.getUsername()
            );

            return new LoginResponse(token);
        
    }





}
