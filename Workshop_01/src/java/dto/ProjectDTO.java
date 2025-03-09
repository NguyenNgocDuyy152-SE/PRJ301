
package dto;

import java.sql.Date;


public class ProjectDTO {
     private int projectId;
    private String projectName;
    private String description;
    private String status;
    private Date estimatedLaunch;
    
    public ProjectDTO(){
        
    }

    public ProjectDTO(int projectId, String projectName, String description, String status, Date estimatedLaunch) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.estimatedLaunch = estimatedLaunch;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEstimatedLaunch() {
        return estimatedLaunch;
    }

    public void setEstimatedLaunch(Date estimatedLaunch) {
        this.estimatedLaunch = estimatedLaunch;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" + "projectId=" + projectId + ", projectName=" + projectName + ", description=" + description + ", status=" + status + ", estimatedLaunch=" + estimatedLaunch + '}';
    }
    
    
    
}
