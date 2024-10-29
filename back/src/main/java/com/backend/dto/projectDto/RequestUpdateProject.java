package com.backend.dto.projectDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestUpdateProject {
    private Long id;
    private String title;
    private String description;
    private String backgroundImage;
    private String image;
    private String overview;
    private List<String> tools;
    private String url;
    private String githubUrl;
    private String liveUrl;
    private boolean isMainProject;
}
