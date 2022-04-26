package models;

public class Person {
    private int persId;
    private String fullName;
    private String email;
    private String note;

    public Person() {}

    public Person(int persId, String fullName, String email, String note) {
        this.persId = persId;
        this.fullName = fullName;
        this.email = email;
        this.note = note;
    }

    public int getPersId() { return persId; }

    public void setPersId(int persId) { this.persId = persId; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
}
