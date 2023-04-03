package kr.codesqaud.cafe.domain;

public class User {
    private Long id;
    private String userId;
    private String name;
    private String password;
    private String email;

    private User(Long id, String userId, String name, String password, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public User(String userId, String name, String password, String email) {
        this(null, userId, name, password, email);
    }

    public void setId(Long id) {
        this.id = id;
    }
}