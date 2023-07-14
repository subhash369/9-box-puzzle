package boxpuzzle;


import java.util.Comparator;

public class PlayerData implements Comparable<PlayerData> {
    private String name;
    private int score;
    String lev;
    public PlayerData(String name, int score, String lev) {
        this.name = name;
        this.score = score;
        this.lev=lev;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    public String getLev()
    {
    	return lev;
    }
    
    @Override
    public String toString() {
        return name + "\t" + score;
    }

    @Override
    public int compareTo(PlayerData o) {
        return this.getScore() - o.getScore();
    }
}
