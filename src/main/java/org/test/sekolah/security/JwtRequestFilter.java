package org.test.sekolah.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.test.sekolah.exception.AuthenticationException;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.service.GuruService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private GuruService guruService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                String jwtToken = requestTokenHeader.substring(7);
                String phone = jwtTokenUtil.getUsernameFromToken(jwtToken);
                String nip = jwtTokenUtil.getNipFromToken(jwtToken);
                UserDetails userDetails = guruService.findByPhoneAndNipJwt(phone, nip);

                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (SignatureException e) {
            logger.warn("JWT_SIGNATURE_ERROR");
        } catch (IllegalArgumentException e) {
            logger.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME");
        } catch (ExpiredJwtException e) {
            logger.warn("JWT_TOKEN_EXPIRED");
        } catch (DataNotFoundException e) {
            throw new AuthenticationException("User is not active");
        } catch (Exception e) {
//            logger.warn("UNCATCH ");
        }
        filterChain.doFilter(request, response);
    }
}
