package io.saga.RestApp.controllers;

import io.saga.RestApp.dto.UserDTO;
import io.saga.RestApp.models.User;
import io.saga.RestApp.services.UsersService;
import io.saga.RestApp.util.UserErrorResponse;
import io.saga.RestApp.util.UserNotCreatedException;
import io.saga.RestApp.util.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sagadat Kuandykov
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return usersService.findAll().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList()); // Jackson will convert objects automatically to JSON.
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") int id) {
        return convertToUserDTO(usersService.findOne(id)); // Jackson automatically convert Object to JSON.
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMsg.toString());
        }
        usersService.save(convertToUser(userDTO));
        // Here we will send HTTP status 200 with empty field, like everything is OK
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "User with ID not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT FOUND 404
    }


    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e){
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // NOT FOUND 404
    }
    private User convertToUser(UserDTO userDTO) {

        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertToUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
}
