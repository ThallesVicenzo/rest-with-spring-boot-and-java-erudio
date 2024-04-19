package br.com.erudio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.security.AccountCredentialsVO;
import br.com.erudio.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication EndPoint")
public class AuthController {

  @Autowired
  AuthService authService;

  @Operation(summary = "Authenticates a user and returns a token")
  @PostMapping(value = "/signIn")
  public ResponseEntity signIn(@RequestBody AccountCredentialsVO data) {
    if (checkIfParamsIsNotNull(data))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request");

    var token = authService.signIn(data);

    if (token == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request");
    } else {
      return token;
    }
  }

  private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
    return data == null || data.getUserName() == null || data.getUserName().isBlank() || data.getPassword() == null
        || data.getPassword().isBlank();
  }

}
