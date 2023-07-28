package org.rest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fileId")
    private FileModel fileModel;


    public Event(Integer id, User user, FileModel fileModel) {
        this.id = id;
        this.user = user;
        this.fileModel = fileModel;
    }

    public Event(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FileModel getFile() {
        return fileModel;
    }

    public void setFile(FileModel fileModel) {
        this.fileModel = fileModel;
    }
}
