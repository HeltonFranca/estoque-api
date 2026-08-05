package br.com.helton.estoque.security;
import br.com.helton.estoque.entity.Usuario;
import br.com.helton.estoque.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioDetailsService(

        UsuarioRepository usuarioRepository
    ){

        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(
        String username
    )throws UsernameNotFoundException{

        Usuario usuario = usuarioRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(
                                "Usuário não encontrado"
                        )
                    );

        return User
                .withUsername(usuario.getUsername())
                .password(usuario.getSenha())
                .authorities(Collections.emptyList())
                .build();
    }

    
}
