package de.reondo.mqtt;

public class Message {
    private String id;
    private long seq;
    private String content;

    public Message(String id, long seq, String content) {
        this.id = id;
        this.seq = seq;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public long getSeq() {
        return seq;
    }

    public String getContent() {
        return content;
    }
}
