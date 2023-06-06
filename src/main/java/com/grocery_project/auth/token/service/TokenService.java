package com.grocery_project.auth.token.service;

import com.grocery_project.auth.token.dto.TokenDto;
import com.grocery_project.auth.token.entity.Token;
import com.grocery_project.core.constant.TokenType;
import com.grocery_project.auth.token.mapper.TokenMapper;
import com.grocery_project.auth.token.repository.TokenRepository;
import com.grocery_project.auth.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;

    public void revokeAllUserTokens(User user){
        var validTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if(validTokens.isEmpty()){
            return;
        }
        validTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        } );
        tokenRepository.saveAll(validTokens);
    }

    public Token saveUserToken(User savedUser, String jwtToken, Date expireDate) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .expireDate(expireDate)
                .build();
     return  tokenRepository.save(token);
    }

    public Optional<Token> findByToken(String jwt) {
        return tokenRepository.findByToken(jwt);
    }

    public void save(Token storedToken) {
        tokenRepository.save(storedToken);
    }

    public TokenDto toDto(Token token){
        return tokenMapper.toDto(token);
    }
}
