package com.ysg.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Thaslim on 31/05/17.
 */
@Entity
@Table(name = "application")
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;
}
