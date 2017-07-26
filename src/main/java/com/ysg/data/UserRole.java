package com.ysg.data;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Thaslim on 23/05/17.
 */
@Entity
@Table(name = "user_role")
@NoArgsConstructor
@RequiredArgsConstructor
public class UserRole {

    @EmbeddedId
    @Getter
    @Setter
    @NonNull
    private UserRoleId id;
}
