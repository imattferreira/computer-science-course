public class Record {
    private String username;
    private int points;

    public Record(String username, int points) {
        this.setUsername(username);
        this.setPoints(points);
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    private void setPoints(int points) {
        this.points = points;
    }
}
