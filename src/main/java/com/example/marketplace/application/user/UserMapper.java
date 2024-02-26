package com.example.marketplace.application.user;

import com.example.marketplace.domain.entity.User;
import com.example.marketplace.presentation.user.dto.commands.CreateUserCommand;
import com.example.marketplace.presentation.user.dto.commands.UpdateUserCommand;
import com.example.marketplace.presentation.user.dto.queries.UserQuery;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        suppressTimestampInGenerated = true,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserMapper {
    User createUserCommandToUser(CreateUserCommand createUserCommand);
    UserQuery userToUserQuery(User user);
    List<UserQuery> fromListUserQueryToListUser(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUpdateUserCommand(UpdateUserCommand updateUserCommand, @MappingTarget User user);
}
