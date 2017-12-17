package lemond.annoying.gamerscompanion.game.objects;


import java.util.List;

public class Game {
    //https://igdb.github.io/api/endpoints/game/
    public long id;
    public String name;
    public long created_at;
    public long updated_at;
    public String summary;
    public String storyLine;
    public long collection;
    public long franchise;
    public long game;
    public long version_parent;
    // TODO: 2017-12-07 research gson how to convert array to list
    public List<Long> developers;
    public List<Long> publishers;
    public List<Long> game_engines;
    // TODO: 2017-12-07 research gson how to convert int to enum
    public Category category;
    public List<Long> themes;
    public long first_release_date;
    public GameStatus status;
    public List<ReleaseDate> release_dates;
    public List<Image> screenshots;
    public List<Video> videos;
    public Image cover;
    public ESRB esrb;
    public PEGI pegi;
    public List<Website> websites;
    public List<Long> dlcs;
    public List<Long> expansions;
    public List<Long> standalone_expansions;
    public List<Long> bundles;
    public List<Long> games;

    public enum Category {
        MAIN_GAME(0),
        DLC(1),
        EXPANSION(2),
        BUNDLE(3),
        STANDALONE_EXPANSION(4);

        private final int value;

        Category(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
