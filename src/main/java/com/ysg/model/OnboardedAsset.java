package com.ysg.model;

import com.ysg.util.ServiceUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Thaslim on 31/05/17.
 */
public class OnboardedAsset {

    @Getter
    @Setter
    @NonNull
    private String id;

    @Getter
    @Setter
    @NonNull
    private String brand;

    @Getter
    @Setter
    @NonNull
    private String model;

    @Getter
    @Setter
    @NonNull
    private String variant;

    @Getter
    @Setter
    @NonNull
    private String engineNumber;

    @Getter
    @Setter
    @NonNull
    private String chassisNumber;

    @Getter
    @Setter
    @NonNull
    private String availableFrom;

    public OnboardedAsset() {
    }

    public OnboardedAsset(String id, String brand, String model, String variant, String engineNumber, String chassisNumber, Date created) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.variant = variant;
        this.engineNumber = engineNumber;
        this.chassisNumber = chassisNumber;
        this.availableFrom = ServiceUtil.dateOf(created);
    }
}
