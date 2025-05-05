package com.tosin.investire.user;


import com.tosin.investire.commons.exception.InvestireException;
import com.tosin.investire.dao.jooq.enums.UserStatus;
import com.tosin.investire.dao.jooq.tables.daos.UsersDao;
import com.tosin.investire.dao.jooq.tables.pojos.User;
import com.tosin.investire.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRepository {

    private final UsersDao usersDao;
    private final ModelMapper modelMapper;

    public UserDto createNewUser(UserDto userDto) {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setStatus(UserStatus.awaiting_verification);
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        try {
            usersDao.insert(user);
            return map(user, false);
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new InvestireException(HttpStatus.BAD_REQUEST, "Email already taken",
                    duplicateKeyException);
        }

    }

    public Optional<UserDto> findUserByEmail(String email) {

        Optional<User> optionalUser = usersDao.fetchOptionalByEmail(email);
        return optionalUser.map(user -> map(user, true));
    }


    private UserDto map(User user, boolean withPassword) {

        UserDto map = modelMapper.map(user, UserDto.class);
        if (!withPassword) {
            map.setPassword(null);
        }
        return map;
    }


}
