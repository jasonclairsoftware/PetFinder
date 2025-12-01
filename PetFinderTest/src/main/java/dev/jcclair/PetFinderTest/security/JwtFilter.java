package dev.jcclair.PetFinderTest.security;

import dev.jcclair.PetFinderTest.services.CustomUserDetailsService;
import dev.jcclair.PetFinderTest.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter chain for the JWT. This was a PAIN!!
 *
 * @author Jason Clair
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    //------------------------------------------------------------------------------------
    // START OF PROPERTIES
    //------------------------------------------------------------------------------------

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext context;

    // Clears paths that don't need JWT
    private static final String[] PUBLIC_PATHS = {
            "/api/users/login",
            "/api/users/register",
            "/api/users",
            "/api/pets"
    };

    //------------------------------------------------------------------------------------
    // END OF PROPERTIES - START OF METHODS
    //------------------------------------------------------------------------------------

    /**
     * Handling the filter for the Spring Filter chain
     * @param request - Rest Request
     * @param response - Rest Response
     * @param filterChain - Filter Chain for authorization
     * @throws ServletException - Servlet Exception
     * @throws IOException - IO Exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        final String requestPath = request.getRequestURI();

        // Overrides the filter chain
        for (String publicPath : PUBLIC_PATHS) {
            if (requestPath.startsWith(publicPath)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        // Gets the JWT
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractEmail(token);
        }

        // Updates the Filter chain
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = context.getBean(CustomUserDetailsService.class).loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    //------------------------------------------------------------------------------------
    // END OF METHODS
    //------------------------------------------------------------------------------------
}
