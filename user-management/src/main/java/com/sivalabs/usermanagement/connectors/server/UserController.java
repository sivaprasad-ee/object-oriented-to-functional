package com.sivalabs.usermanagement.connectors.server;

import com.sivalabs.usermanagement.entities.User;
import com.sivalabs.usermanagement.entities.exceptions.BadRequestException;
import com.sivalabs.usermanagement.entities.exceptions.UserEmailExistsException;
import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import com.sivalabs.usermanagement.domain.registration.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserRegistrationService userRegistrationService;

  @PostMapping
  public ResponseEntity<Void> registerUser(@RequestBody CreateUserRequest request) {
    User user = userRegistrationService.createUser(request);
    URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
    ErrorResponse response = new ErrorResponse(e.getMessage(), e.getErrors());
    return ResponseEntity.status(BAD_REQUEST).body(response);
  }

  @ExceptionHandler(UserEmailExistsException.class)
  public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(
      UserEmailExistsException e) {
    ErrorResponse response = new ErrorResponse(e.getMessage(), null);
    return ResponseEntity.status(CONFLICT).body(response);
  }
}
