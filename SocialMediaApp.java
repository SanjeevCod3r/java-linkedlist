class User {
    int userId;
    String name;
    int age;
    User next;
    LinkedList<Integer> friends;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.next = null;
        this.friends = new LinkedList<>();
    }
}

class SocialMedia {
    private User head;

    public SocialMedia() {
        head = null;
    }

    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            newUser.next = head;
            head = newUser;
        }
    }

    public void addFriendConnection(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 != null && user2 != null) {
            user1.friends.add(userId2);
            user2.friends.add(userId1);
        }
    }

    public void removeFriendConnection(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 != null && user2 != null) {
            user1.friends.remove((Integer) userId2);
            user2.friends.remove((Integer) userId1);
        }
    }

    public User findUser(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    public void displayFriends(int userId) {
        User user = findUser(userId);
        if (user != null) {
            System.out.println("User " + userId + " Friends: " + user.friends);
        }
    }

    public void displayAllUsers() {
        User temp = head;
        while (temp != null) {
            System.out.println("User ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
            temp = temp.next;
        }
    }
}

// Example usage
public class SocialMediaApp {
    public static void main(String[] args) {
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.addUser(1, "Alice", 25);
        socialMedia.addUser(2, "Bob", 30);
        socialMedia.addUser(3, "Charlie", 22);
        socialMedia.addFriendConnection(1, 2);
        socialMedia.addFriendConnection(2, 3);
        socialMedia.displayAllUsers();
        socialMedia.displayFriends(2);
    }
}
