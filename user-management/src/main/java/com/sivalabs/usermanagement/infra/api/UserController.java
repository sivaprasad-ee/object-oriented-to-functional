package com.sivalabs.usermanagement.infra.api;

import com.sivalabs.usermanagement.infra.api.model.ErrorResponse;
import com.sivalabs.usermanagement.domain.UserCreationException;
import com.sivalabs.usermanagement.domain.UserAlreadyExistsException;
import com.sivalabs.usermanagement.domain.registration.CreateUserRequest;
import com.sivalabs.usermanagement.domain.User;
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

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(UserCreationException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), e.getErrors());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(UserAlreadyExistsException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), null);
        return ResponseEntity.status(CONFLICT).body(response);
    }
}
