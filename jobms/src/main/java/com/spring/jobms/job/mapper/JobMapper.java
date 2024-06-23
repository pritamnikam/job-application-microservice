package com.spring.jobms.job.mapper;

import com.spring.jobms.job.Job;
import com.spring.jobms.job.dto.JobWithCompanyDTO;
import com.spring.jobms.job.external.Company;

public class JobMapper {
    public static JobWithCompanyDTO mapToJobWithCompanyDTO(
            Job job,
            Company company
    ) {
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        return jobWithCompanyDTO;
    }
}
