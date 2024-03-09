package com.project_management_system.service;

import com.project_management_system.entity.Project;
import com.project_management_system.exception.ProjectNotFoundException;
import com.project_management_system.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {

        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            return projectOptional.get();
        } else {
            throw new ProjectNotFoundException("Project not found with ID: " + id);
        }
    }

    public Project updateProject(Long id, Project project) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project existingProject = projectOptional.get();
            existingProject.setName(project.getName());
            existingProject.setDescription(project.getDescription());
            existingProject.setStartDate(project.getStartDate());
            existingProject.setEndDate(project.getEndDate());
            return projectRepository.save(existingProject);
        } else {
            throw new ProjectNotFoundException("Project not found with ID: " + id);
        }
    }

    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new ProjectNotFoundException("Project not found with ID: " + id);
        }
    }
}
