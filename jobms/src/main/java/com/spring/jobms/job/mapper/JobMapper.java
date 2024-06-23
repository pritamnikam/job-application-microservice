package com.spring.jobms.job.mapper;

import com.spring.jobms.job.Job;
import com.spring.jobms.job.dto.JobDTO;
import com.spring.jobms.job.external.Company;
import com.spring.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(
            Job job,
            Company company,
            List<Review> reviews
    ) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
