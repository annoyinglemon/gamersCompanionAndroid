package lemond.annoying.gamerscompanion.game.objects;



public class Website {

    public Category category;
    public String url;

    public enum Category {
        OFFICIAL(1),
        WIKIA(2),
        WIKIPEDIA(3),
        FACEBOOK(4),
        TWITTER(5),
        TWITCH(6),
        INSTAGRAM(8),
        YOUTUBE(9),
        IPHONE(10),
        IPAD(11),
        ANDROID(12),
        STEAM(13);

        private final int value;

        Category(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
