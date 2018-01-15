package com.ysg.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by vrathakumar on 3/29/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
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


    @Getter
    @Setter
    private String opsCityId;

}
