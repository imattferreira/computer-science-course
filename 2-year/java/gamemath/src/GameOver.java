import java.util.TimerTask;

public class GameOver extends TimerTask {
    @Override
    public void run() {
        Game.over = true;
    }
}
