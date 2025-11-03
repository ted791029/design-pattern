package com.ted.app;

public class Main {
    public static void main(String[] args) {
        Channel channel1 = new Channel("水球軟體學院");
        Channel channel2 = new Channel("PewDiePie");

        User user1 = new User("水球");
        User user2 = new User("火球");

        channel1.subscribe(user1, new Like());
        channel2.subscribe(user1, new Like());
        channel1.subscribe(user2, new Unsubscribe());
        channel2.subscribe(user2, new Unsubscribe());

        channel1.update(new Video("C1M1S2", "這個世界正是物件導向的呢！", 240));
        channel2.update(new Video("Hello guys", "”Clickbait！", 30));
        channel1.update(new Video("C1M1S3", "”物件 vs. 類別！", 60));
        channel2.update(new Video("Minecraft", "”Let’s play Minecraft", 1800));
    }
}
