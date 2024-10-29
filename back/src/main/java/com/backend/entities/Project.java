package com.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isMainProject = false;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String backgroundImage;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String image;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @ElementCollection
    @CollectionTable(name = "project_tools", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "tool")
    private List<String> tools;

    private String url;

    private String githubUrl;

    private String liveUrl;

    public Project() {

    }

    public Project(Boolean isMainProject, String title, String description, String backgroundImage, String image, String overview, List<String> tools, String url, String githubUrl, String liveUrl) {
        this.isMainProject = isMainProject;
        this.title = title;
        this.description = description;
        this.backgroundImage = backgroundImage;
        this.image = image;
        this.overview = overview;
        this.tools = tools;
        this.url = url;
        this.githubUrl = githubUrl;
        this.liveUrl = liveUrl;
    }
}
