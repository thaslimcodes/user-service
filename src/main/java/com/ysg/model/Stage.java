package com.ysg.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by Thaslim on 21/04/17.
 */
@EqualsAndHashCode
public class Stage implements Comparable<Stage> {

    @Getter
    @Setter
    @NonNull
    private int order;

    @Getter
    @Setter
    @NonNull
    private String key;

    @Getter
    @Setter
    @NonNull
    private String value;

    @Override
    public int compareTo(Stage o) {
        return order - o.getOrder();
    }
}
