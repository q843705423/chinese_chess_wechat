package io.github.q843705423.entity.piece.car;

public class RedCar extends Car {
    @Override
    public String englishName() {
        return "R";
    }

    @Override
    public String chinaName() {
        return "车";
    }



    @Override
    public boolean isRed() {
        return true;
    }
}
