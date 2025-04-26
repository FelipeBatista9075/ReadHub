package dev.batist.ReadHub.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.batist.ReadHub.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${readhub.security.secret}")
    private String secret;

    public String generatedToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId",user.getId())
                .withClaim("Name",user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86000))
                .withIssuedAt(Instant.now())
                .withIssuer("ReadHub-livraria")
                .sign(algorithm);
    }

    public Optional<JWTUserData> validate(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData
                    .builder()
                    .id(decode.getClaim("UserId").asLong())
                    .name(decode.getClaim("name").asString())
                    .email(decode.getSubject())
                    .build());

        }catch (JWTVerificationException ex){
            return Optional.empty();
        }
    }

}
