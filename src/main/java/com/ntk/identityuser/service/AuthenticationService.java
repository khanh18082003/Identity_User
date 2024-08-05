package com.ntk.identityuser.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.ntk.identityuser.dao.IUserRepository;
import com.ntk.identityuser.dto.request.AuthenticationRequest;
import com.ntk.identityuser.dto.request.IntrospectRequest;
import com.ntk.identityuser.dto.response.AuthenticationResponse;
import com.ntk.identityuser.dto.response.IntrospectResponse;
import com.ntk.identityuser.exception.AppException;
import com.ntk.identityuser.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class AuthenticationService {
    IUserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNED_KEY;

    public AuthenticationResponse isAuthenticated(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        boolean authenticated = encoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(generateToken(request.getUsername()))
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) {
        return IntrospectResponse.builder()
                .valid(verify(request.getToken()))
                .build();
    }

    private boolean verify(String token) {
        try {
            // Verify token
            // Create HMAC verifier
            JWSVerifier verifier = new MACVerifier(SIGNED_KEY);

            // Parse token
            // Parse the JWS and verify its HMAC
            SignedJWT signedJWT = SignedJWT.parse(token);

            // Check expiration time
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            return signedJWT.verify(verifier) && expirationTime.after(new Date());
        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateToken(String username) {
        // Generate token

        // Create HMAC signer
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        // Create JWT claims
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("https://identityuser.com")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 60 * 60 * 1000))
                .claim("scope", "read write")
                .build();
        // Create payload
        Payload payload = new Payload(claimsSet.toJSONObject());
        // Create JWS object
        JWSObject jwsObject = new JWSObject(header, payload);

        // Sign token
        try {
            jwsObject.sign(new MACSigner(SIGNED_KEY));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
