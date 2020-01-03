package ua.vlasov_eugene.file_parser.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.vlasov_eugene.file_parser.entity.User;
import ua.vlasov_eugene.file_parser.service.UserService;


/**
 * Нужен для того, чтобы предоставить из БД UserDetails
 * с данными о юзере для работы Security
 */
@Service
@Slf4j
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;
    //Как раз возвращает имплементацию UserDetails- набор инфы для Секьюрити
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
