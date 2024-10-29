package com.backend.controllers;

import com.backend.dto.projectDto.RequestUpdateProject;
import com.backend.entities.Project;
import com.backend.internal.PublicEndpoint;
import com.backend.repositories.ProjectRepository;
import com.backend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @PublicEndpoint
    @GetMapping()
    public ArrayList<Project> getProjects () {
        return (ArrayList<Project>) projectRepository.findAll();
    }

    @PublicEndpoint
    @GetMapping("/{id}")
    public Optional<Project> getProjectById (@PathVariable Long id) {
        return this.projectRepository.findById(id);
    }

    @PublicEndpoint
    @PostMapping()
    public Project addProject(@RequestBody RequestUpdateProject project) {
        return this.projectService.addProject(project);
    }

    @PublicEndpoint
    @PatchMapping()
    public Project updateProject(@RequestBody RequestUpdateProject project) {
        return this.projectService.updateProject(project);
    }

    @PublicEndpoint
    @DeleteMapping("{id}")
    public void removeProject(@PathVariable Long id) {
        this.projectRepository.deleteById(id);
    }
}
