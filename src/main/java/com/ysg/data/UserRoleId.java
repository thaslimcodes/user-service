package com.ysg.data;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Thaslim on 23/05/17.
 */
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class UserRoleId implements Serializable {

    @Column(name = "user_id")
    @Getter
    @Setter
    @NonNull
    private String userId;

    @Column(name = "role_id")
    @Getter
    @Setter
    @NonNull
    private String roleId;

    @Column(name = "app_id")
    @Getter
    @Setter
    @NonNull
    private String appId;
}
