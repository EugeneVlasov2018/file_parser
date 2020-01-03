package ua.vlasov_eugene.file_parser.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.vlasov_eugene.file_parser.entity.Role;
import ua.vlasov_eugene.file_parser.entity.User;
import ua.vlasov_eugene.file_parser.enumeration.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Генерим UserDetails фабрикой
 */
public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }

    //Парсим роли в Autorities (Для работы Секьюрити)
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}

