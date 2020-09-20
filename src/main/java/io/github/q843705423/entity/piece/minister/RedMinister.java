package io.github.q843705423.entity.piece.minister;

public class RedMinister extends Minister {
    @Override
    public String englishName() {
        return "B";
    }

    @Override
    public String chinaName() {
        return "相";
    }


    @Override
    public boolean isRed() {
        return true;
    }

}
