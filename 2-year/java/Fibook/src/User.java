import java.util.ArrayList;

public class User {
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void notifyFollowPost(Post post) {
        System.out.println("==========================");
        System.out.println("NEW POST:");
        System.out.println("User: " + this.getUsername());
        System.out.println("Follow: " + post.getUserName());
        System.out.println("Content: " + post.getContent());
        System.out.println("==========================");
    }
}
