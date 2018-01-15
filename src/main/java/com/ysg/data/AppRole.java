package com.ysg.data;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "app_role")
public class AppRole {
    @EmbeddedId
    @Getter
    @Setter
    @NonNull
    private AppRoleId id;

}
