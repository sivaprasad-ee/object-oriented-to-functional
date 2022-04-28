package com.sivalabs.usermanagement.connectors.server;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import com.sivalabs.usermanagement.domain.registration.UserRegistrationService;
import com.sivalabs.usermanagement.entities.User;
import com.sivalabs.usermanagement.entities.exceptions.EmptyRequiredFieldException;
import com.sivalabs.usermanagement.entities.exceptions.UserEmailExistsException;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserRegistrationService userRegistrationService;

  @PostMapping
  public ResponseEntity<Void> registerUser(@RequestBody final CreateUserRequest request) {
    User user = userRegistrationService.createUser(request);
    URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @ExceptionHandler(EmptyRequiredFieldException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(final EmptyRequiredFieldException e) {
    ErrorResponse response = new ErrorResponse(e.getMessage());
    return ResponseEntity.status(BAD_REQUEST).body(response);
  }

  @ExceptionHandler(UserEmailExistsException.class)
  public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(
      final UserEmailExistsException e) {
    ErrorResponse response = new ErrorResponse(e.getMessage());
    return ResponseEntity.status(CONFLICT).body(response);
  }
}
