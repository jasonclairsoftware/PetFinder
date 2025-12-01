package dev.jcclair.PetFinderTest.services;

import dev.jcclair.PetFinderTest.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

/**
 * JWT service that authenticates and issues JWTs
 *
 * @author Jason Clair
 */
@Service
public class JwtService {

    //------------------------------------------------------------------------------------
    // START OF PROPERTIES
    //------------------------------------------------------------------------------------
    private SecretKey key;
    private int expiration;

    //------------------------------------------------------------------------------------
    // END OF PROPERTIES - START OF CONSTRUCTORS
    //------------------------------------------------------------------------------------

    public JwtService(@Value("${jwt.Secret}") String secret,
                      @Value("${jwt.ExpirationInMs}") int expiration) {
        // Correctly converts the String secret to a SecretKey
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;

    }

    //------------------------------------------------------------------------------------
    // END OF CONSTRUCTORS - START OF METHODS
    //------------------------------------------------------------------------------------

    /**
     * Creates a new JWT token
     * @param user - The user the token is for
     * @return - JWT as string
     */
    public String generateToken(UserModel user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    /**
     * Gets email from token
     * @param token - String of token email is in
     * @return - email
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Gets all claims from JWT
     * @param token - Token with claims
     * @param claimResolver - Resolver
     * @return - Claims
     * @param <T> - Not sure
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Validates if token is good or not
     * @param token - Token being validated
     * @param userDetails - userDetails
     * @return - True if and only if token is valid
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks tokens expiration date
     * @param token - Token
     * @return - True if and only if token is expired
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Will get expiration from token
     * @param token - Token
     * @return - Expiration Date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Will extract all claims
     * @param token - Token
     * @return - Claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    //------------------------------------------------------------------------------------
    // END OF METHODS
    //------------------------------------------------------------------------------------

}
