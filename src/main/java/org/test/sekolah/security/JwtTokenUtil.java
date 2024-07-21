package org.test.sekolah.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.test.sekolah.bean.GuruToken;
import org.test.sekolah.entity.Guru;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_CREATED = "iat";
    private static final long serialVersionUID = -3301605591108950415L;
    private final Clock clock = DefaultClock.INSTANCE;


    @Value("${jwt.signing.key.secret}")
    private String jwtSecret;

    @Value("${jwt.token.expiration.in.seconds}")
    private String jwtTokenExpiration;

    public GuruToken getUserObjectFromToken(String token) throws SignatureException {
        Claims claims = getClaimObject(token);
        if (claims == null) return null;
        String nip = claims.get("nip").toString();
        String phone = claims.get("phone").toString();
        Long id = Long.parseLong(claims.get("id").toString());
        return new GuruToken(id, phone, nip);
    }

    public String getEmailFromToken(String token) throws SignatureException {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getNipFromToken(String token) throws SignatureException {
        Object nip = getClaimFromToken(token, claims -> claims.get("nip"));
        return nip.toString();
    }

    private Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims getClaimObject(String token) throws SignatureException {
        return getAllClaimsFromToken(token);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws SignatureException {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) throws SignatureException {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
//            System.out.println("Catch");
//            e.printStackTrace();
            return null;
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(UserDetails userDetails, boolean isHeadOffice) {
        Map<String, Object> claims = new HashMap<>();
            Guru guru = (Guru) userDetails;
            claims.put("id", guru.getId());
            claims.put("phone", guru.getPhone());
            claims.put("nip", guru.getNip());
        return doGenerateToken(claims, userDetails);
    }

    private String doGenerateToken(Map<String, Object> claims, UserDetails user) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);
        String subject = user.getUsername();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) &&
                !isTokenExpired(token) && userDetails.isEnabled());
    }

    private Date calculateExpirationDate(Date createdDate) {
        try {
            return new Date(createdDate.getTime() + Long.parseLong(jwtTokenExpiration) * 1000);
        } catch (NullPointerException e) {
            return new Date(createdDate.getTime() + 3600 * 1000);
        }
    }

    public String getUsernameFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getSubject);
    }

    public String getPhoneFromToken(String token) {
        Object phone = getClaimFromToken(token, claims -> claims.get("phone"));
        return String.valueOf(phone);
    }
}
