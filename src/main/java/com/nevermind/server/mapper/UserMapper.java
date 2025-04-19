package com.nevermind.server.mapper;

import com.nevermind.server.domain.dto.auth.SignupOutbound;
import com.nevermind.server.domain.entity.Role;
import com.nevermind.server.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToOutbound")
    SignupOutbound toSignupOutbound(User user);

    @Named("rolesToOutbound")
    default Set<String> rolesToOutbound(Set<Role> roles) {
        if (roles == null) {
            return Set.of();
        }

        Set<String> result = new HashSet<>();
        for (Role role : roles) {
            result.add(role.getName());
        }
        return result;
    }
}
