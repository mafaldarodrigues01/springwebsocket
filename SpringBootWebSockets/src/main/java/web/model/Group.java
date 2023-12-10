package web.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;


@Entity(name = "Group")
@Table(name = "groupp")

public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid")
    private int gid;

    @Column(name = "groupname")
    String groupName;

    /*@NonNull
    @ManyToMany(mappedBy = "")
    @JoinTable(name="group_user",
            joinColumns=@JoinColumn(name="gid"),
            inverseJoinColumns=@JoinColumn(name="uid"))
    List<UserWeb> userWebs;*/

   // @OneToMany(mappedBy = "group")
    //List<Message> messages;


    public Integer getGid() {
        return gid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /*public List<UserWeb> getUsers() {
        return userWebs;
    }

    public void setUsers(String password) {
        this.userWebs = userWebs;
    }*/

    /*public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }*/

    //creates a new window chat with no initial messages
    public Group( String groupName/*, List<UserWeb> userWebs*/){
        //this.userWebs = userWebs;
        if(groupName != null)
            this.groupName = groupName;
        else
            this.groupName = "";//groupNameGenerator(userWebs);
    }

    public Group(){
        //this.userWebs = List.of();
        this.groupName = null;
    }

    /*private static String groupNameGenerator(@org.jetbrains.annotations.NotNull List<UserWeb> userWebs){
        Iterator<UserWeb> iterator = userWebs.iterator();
        StringBuilder groupName = new StringBuilder(iterator.next().username);
        if(userWebs.size() == 1){
            return groupName.toString();
        }
        while (iterator.hasNext()){
            groupName.append(", ").append(iterator.next().username);
        }
        return groupName.toString();
    }*/
}
