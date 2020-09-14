package io.github.q843705423.entity.piece.minister;

public class BlackMinister extends Minister {
    @Override
    public String englishName() {
        return "b";
    }

    @Override
    public String chinaName() {
        return "象";
    }


    @Override
    public boolean isRed() {
        return false;
    }
}
