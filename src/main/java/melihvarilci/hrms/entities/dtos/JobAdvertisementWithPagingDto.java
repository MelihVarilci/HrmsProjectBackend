package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import melihvarilci.hrms.entities.concretes.JobAdvertisement;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementWithPagingDto {
    private List<JobAdvertisement> jobAdvertisements;

    private int totalPages;
}
