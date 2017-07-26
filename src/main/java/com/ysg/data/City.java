package com.ysg.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by vrathakumar on 3/29/2017.
 */
@Entity
@Table(name = "city")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    private String id;


    @Getter
    @Setter
    private String name;

    public City() {

    }

    public City(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
