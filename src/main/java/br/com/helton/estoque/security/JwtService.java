package br.com.helton.estoque.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private final Algorithm algorithm;
    private final long expirationMs;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-ms}") long expirationMs
    ) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.expirationMs = expirationMs;
    }

    public String gerarToken(String username) {
        Instant agora = Instant.now();
        Instant expiracao = agora.plusMillis(expirationMs);

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(agora)
                .withExpiresAt(expiracao)
                .sign(algorithm);
    }

    public String extrairUsername(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }
}
