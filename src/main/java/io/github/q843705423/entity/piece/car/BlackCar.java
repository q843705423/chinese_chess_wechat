package io.github.q843705423.entity.piece.car;

public class BlackCar extends Car {
    @Override
    public String englishName() {
        return "r";
    }

    @Override
    public String chinaName() {
        return "車";
    }


    @Override
    public boolean isRed() {
        return false;
    }
}
