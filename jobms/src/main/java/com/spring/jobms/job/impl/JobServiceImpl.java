package com.spring.jobms.job.impl;


import com.spring.jobms.job.Job;
import com.spring.jobms.job.JobRepository;
import com.spring.jobms.job.JobService;
import com.spring.jobms.job.dto.JobWithCompanyDTO;
import com.spring.jobms.job.external.Company;
import com.spring.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    private long nextId = 1L;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = this.jobRepository.findAll();
        return jobs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private JobWithCompanyDTO convertToDto(Job job) {
        Company company = restTemplate.getForObject(
                "http://COMPANY-SERVICE:8081/companies/"+job.getCompanyId(),
                Company.class
        );
        return JobMapper.mapToJobWithCompanyDTO(job, company);
    }

    @Override
    public void createJob(Job job) {
        job.setId(this.nextId++);
        this.jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDTO getJobById(Long id) {
        Job job = this.jobRepository.findById(id).orElse(null);
        if (job != null) {
            return convertToDto(job);
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            this.jobRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = this.jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            job.setCompanyId(updatedJob.getCompanyId());
            this.jobRepository.save(job);
            return true;
        }
        return false;
    }
}
