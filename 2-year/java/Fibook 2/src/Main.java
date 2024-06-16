public class Main {
    public static void main(String[] args) {
        var feedPost = new FeedPost();

        var user1 = new User("Alberto");
        var user2 = new User("Beatriz");
        var user3 = new User("Camila");
        var user4 = new User("Anderson");

        feedPost.addFollower(user1, user2);
        feedPost.addFollower(user1, user3);

        feedPost.addFollower(user2, user1);
        feedPost.addFollower(user2, user3);

        feedPost.publish(user1, new Post(user1.getUsername(), "My first post of this app"));
        feedPost.publish(user1, new Post(user2.getUsername(), "Hello World"));

        feedPost.unfollow(user1, user2);

        feedPost.publish(user1, new Post(user1.getUsername(), "My second post"));
    }
}