package lemond.annoying.gamerscompanion.repository.objects;



public enum Region {

    EUROPE(1),
    NORTH_AMERICA(2),
    AUSTRALIA(3),
    NEW_ZEALAND(4),
    JAPAN(5),
    CHINA(6),
    ASIA(7),
    WORLDWIDE(8);

    private final int value;

    Region(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
