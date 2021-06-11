package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementForAddDto {
    private String jobDescription;
    private Double minSalary;
    private Double maxSalary;
    private int openPositionCount;
    private Date lastApplyDate;
    private boolean isActive;
    private int cityId;
    private int jobPositionId;
    private int employerId;
}
