import java.util.ArrayList;
import java.util.HashMap;

public class FeedPost {
    private final HashMap<User, ArrayList<Post>> feed;
    private final HashMap<User, ArrayList<User>> followers;

    public FeedPost() {
        this.feed = new HashMap<User, ArrayList<Post>>();
        this.followers = new HashMap<User, ArrayList<User>>();
    }

    public void publish(User user, Post post) {
        if (!this.feed.containsKey(user)) {
            this.feed.put(user, new ArrayList<Post>());
        }

        var storedPosts = this.feed.get(user);

        storedPosts.add(post);
        var storedFollowers = this.followers.get(user);

        if (storedFollowers == null) {
            return;
        }

        for (var follower : storedFollowers) {
            follower.notifyFollowPost(post);
        }

        this.feed.put(user, storedPosts);
    }

    public void addFollower(User user, User follower) {
        if (user.getUsername().equals(follower.getUsername())) {
            return;
        }

        User followerAlreadyExists = null;
        var storedFollowers = this.followers.get(user);

        if (storedFollowers == null) {
            storedFollowers = new ArrayList<>();
        }

        for (var storedFollower : storedFollowers) {
            if (storedFollower.getUsername().equals(follower.getUsername())) {
                followerAlreadyExists = storedFollower;
            }
        }

        if (followerAlreadyExists != null) {
            return;
        }

        storedFollowers.add(follower);

        this.followers.put(user, storedFollowers);
    }

    public void unfollow(User user, User follower) {
        var filteredFollowers = new ArrayList<User>();

        for (var storedFollower : this.followers.get(user)) {
            if (follower.getUsername().equals(storedFollower.getUsername())) {
                continue;
            }

            filteredFollowers.add(storedFollower);
        }

        this.followers.put(user, filteredFollowers);
    }
}
