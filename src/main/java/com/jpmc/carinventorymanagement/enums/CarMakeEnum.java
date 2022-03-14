package com.jpmc.carinventorymanagement.enums;

public enum CarMakeEnum {
    KIA("Kia"),
    HYUNDAI("Hyundai"),
    HONDA("Honda"),
    FORD("Ford");

    private String carMake;

    CarMakeEnum(final String carMake) {
        this.carMake = carMake;
    }

    public static CarMakeEnum getCarMakeEnum(String carMake) {
        for (CarMakeEnum carMakeEnum : values()) {
            if (carMakeEnum.carMake.equalsIgnoreCase(carMake))
                return carMakeEnum;
        }
        return null;
    }

    public String getCarMake(){
        return this.carMake;
    }
}
