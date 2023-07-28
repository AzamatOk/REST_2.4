package org.rest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "File")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "filePath")
    private String filePath;
    public FileModel(Integer id, String name, String filePath){
        this.id = id;
        this.name = name;

        this.filePath = filePath;
    }
    public FileModel(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
