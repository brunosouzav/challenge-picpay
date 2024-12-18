package picpay.challenge.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import picpay.challenge.services.JwtService;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailService;

    protected void doFilterInternal(
    	    @NonNull HttpServletRequest request,
    	    @NonNull HttpServletResponse response,
    	    @NonNull FilterChain filterChain
    	) throws ServletException, IOException {
    	    final String authHeader = request.getHeader("Authorization");
    	    final String jwt;
    	    final String userEmail;

    	
    	    if (request.getServletPath().startsWith("/api/auth/")) {
    	        filterChain.doFilter(request, response);
    	        return;
    	    }

    	    
    	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
    	        filterChain.doFilter(request, response);
    	        return;
    	    }

    	   
    	    jwt = authHeader.substring(7);
    	    userEmail = jwtService.extractUsername(jwt);

    
    	    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    	        UserDetails userDetails = this.userDetailService.loadUserByUsername(userEmail);

    	        if (jwtService.validateToken(jwt, userDetails)) {
    	            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
    	                    userDetails,
    	                    null,
    	                    userDetails.getAuthorities()
    	            );
    	            authToken.setDetails(
    	                    new WebAuthenticationDetailsSource().buildDetails(request)
    	            );
    	            SecurityContextHolder.getContext().setAuthentication(authToken);
    	        }
    	    }

    	    filterChain.doFilter(request, response);
    	}
}
