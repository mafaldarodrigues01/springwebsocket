package web.model;

import jakarta.persistence.*;


@Entity(name = "User")
@Table(name = "userr")
public class User {

    @Id
    @Column(name = "uid", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer uid;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;
/*
    @ManyToMany(mappedBy="users")
    List<Group> groups;

    @OneToMany(mappedBy = "user")
    List<Message> messages;
*/
    public Integer getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 /*   public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }*/

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(){
        this.username = null;
        this.password = null;
    }

}
