package ua.vlasov_eugene.file_parser.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.vlasov_eugene.file_parser.dto.UserDto;
import ua.vlasov_eugene.file_parser.entity.User;
import ua.vlasov_eugene.file_parser.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/users/")
@AllArgsConstructor
public class UserRestControllerV1 {
    private final UserService userService;

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
