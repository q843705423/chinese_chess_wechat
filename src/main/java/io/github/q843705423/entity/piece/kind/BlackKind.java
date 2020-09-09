package io.github.q843705423.entity.piece.kind;

public class BlackKind extends Kind {
    @Override
    public String englishName() {
        return "k";
    }

    @Override
    public String chinaName() {
        return "将";
    }


    @Override
    public boolean isRed() {
        return false;
    }
}
