package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.security.AccountCredentialsVO;
import br.com.erudio.data.vo.v1.security.TokenVO;
import br.com.erudio.repositories.UserRepository;
import br.com.erudio.security.jwt.JwtTokenProvider;

@Service
public class AuthService {
  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository repository;

  public ResponseEntity signIn(AccountCredentialsVO data) {
    try {

      var username = data.getUserName();
      var password = data.getPassword();

      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

      var user = repository.findbyUserName(username);

      var tokenResponse = new TokenVO();

      if (user != null) {
        tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
      } else {
        throw new UsernameNotFoundException("Username" + username + " not found!");
      }

      return ResponseEntity.ok(tokenResponse);
    } catch (Exception e) {
      throw new BadCredentialsException("Invalid username/password supplied!");
    }
  }
}
