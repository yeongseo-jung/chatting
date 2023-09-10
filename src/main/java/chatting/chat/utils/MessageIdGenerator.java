package chatting.chat.utils;

public class MessageIdGenerator {
    private static Long id = 0L;

    public static Long generateId() {
        id += 1L;
        return id;
    }
}