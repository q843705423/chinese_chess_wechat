package io.github.q843705423.entity.piece.house;

public class RedHouse extends House {
    @Override
    public String englishName() {
        return "N";
    }

    @Override
    public String chinaName() {
        return "马";
    }


    @Override
    public boolean isRed() {
        return true;
    }
}
