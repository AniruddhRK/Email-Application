public class Message {
    protected String from, to, subject, content;
    protected int ind;
    public Message(int ind, String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.ind = ind;
    }
}
