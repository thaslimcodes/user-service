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
@Table(name = "state")
public class State implements Serializable {

    @Id
    @Getter
    @Setter
    private String id;


    @Getter
    @Setter
    private String name;

}
