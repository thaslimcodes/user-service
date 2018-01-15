package com.ysg.data;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Thaslim on 23/05/17.
 */
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class AppRoleId implements Serializable {


    @Getter
    @Setter
    @NonNull
    private String roleId;

    @Getter
    @Setter
    @NonNull
    private String appId;
}
