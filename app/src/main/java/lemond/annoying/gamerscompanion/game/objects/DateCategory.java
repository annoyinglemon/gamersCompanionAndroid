package lemond.annoying.gamerscompanion.game.objects;



public enum DateCategory {
    // change (_) to (-) on conversion
    YYYY_MMM_DD(0),
    YYYY_MMM(1),
    YYYY(2),
    YYYY_Q1(3),
    YYYY_Q2(4),
    YYYY_Q3(5),
    YYYY_Q4(6),
    TBD(7);

    private final int value;

    DateCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}