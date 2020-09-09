package io.github.q843705423.entity.piece.house;

import io.github.q843705423.entity.piece.house.House;

public class BlackHouse extends House {
    @Override
    public String englishName() {
        return "n";
    }

    @Override
    public String chinaName() {
        return "馬";
    }


    @Override
    public boolean isRed() {
        return false;
    }
}
