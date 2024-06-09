package com.spring.jobms.job.impl;


import com.spring.jobms.job.Job;
import com.spring.jobms.job.JobRepository;
import com.spring.jobms.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    private long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return this.jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(this.nextId++);
        this.jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return this.jobRepository.findById(id).orElse(null);
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
