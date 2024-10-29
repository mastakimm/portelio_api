package com.backend.services;

import com.backend.dto.projectDto.RequestUpdateProject;
import com.backend.entities.Project;
import com.backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project addProject(RequestUpdateProject request) {
        Project project = new Project();
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setImage(request.getImage());
        project.setBackgroundImage(request.getBackgroundImage());
        project.setTools(request.getTools());
        project.setMainProject(request.isMainProject());
        project.setUrl(request.getUrl());
        project.setGithubUrl(request.getGithubUrl());
        project.setOverview(request.getOverview());
        project.setLiveUrl(request.getLiveUrl());

        return this.projectRepository.save(project);
    }

    public Project updateProject(RequestUpdateProject request) {
        Project project = this.getProjectById(request.getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        updateFieldIfChanged(request.getTitle(), project.getTitle(), project::setTitle);
        updateFieldIfChanged(request.getDescription(), project.getDescription(), project::setDescription);
        updateFieldIfChanged(request.getBackgroundImage(), project.getBackgroundImage(), project::setBackgroundImage);
        updateFieldIfChanged(request.getImage(), project.getImage(), project::setImage);
        updateFieldIfChanged(request.getOverview(), project.getOverview(), project::setOverview);
        updateFieldIfChanged(request.getTools(), project.getTools(), project::setTools);
        updateFieldIfChanged(request.getUrl(), project.getUrl(), project::setUrl);
        updateFieldIfChanged(request.getGithubUrl(), project.getGithubUrl(), project::setGithubUrl);
        updateFieldIfChanged(request.getLiveUrl(), project.getLiveUrl(), project::setLiveUrl);
        updateFieldIfChanged(request.isMainProject(), project.isMainProject(), project::setMainProject);

        return projectRepository.save(project);
    }

    private <T> void updateFieldIfChanged(T newValue, T oldValue, Consumer<T> setter) {
        if (newValue != null && !newValue.equals(oldValue)) {
            setter.accept(newValue);
        }
    }
}
