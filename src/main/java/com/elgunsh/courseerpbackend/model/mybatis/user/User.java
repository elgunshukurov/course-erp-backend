package com.elgunsh.courseerpbackend.model.mybatis.user;


import com.elgunsh.courseerpbackend.model.enums.user.UserStatus;
import com.elgunsh.courseerpbackend.model.mybatis.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity<Long> {

    String name;
    String surname;
    UserStatus status;
    Long roleId;
    String email;
    String phoneNumber;
    String password;

    public boolean isActive() {
        return UserStatus.ACTIVE.equals(status);
    }
}
