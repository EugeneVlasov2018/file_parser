package ua.vlasov_eugene.file_parser.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.vlasov_eugene.file_parser.dto.AdminUserDto;
import ua.vlasov_eugene.file_parser.entity.User;
import ua.vlasov_eugene.file_parser.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/admin/")
@AllArgsConstructor
public class AdminRestControllerV1 {
    private final UserService userService;

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
