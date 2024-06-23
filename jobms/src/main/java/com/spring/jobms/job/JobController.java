package com.spring.jobms.job;

import com.spring.jobms.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService service) {
        this.jobService = service;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll() {
        return ResponseEntity.ok(this.jobService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        JobDTO jobDTO = this.jobService.getJobById(id);
        if (jobDTO != null) {
            return ResponseEntity.ok(jobDTO);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        this.jobService.createJob(job);
        return ResponseEntity.ok("New job added successfully!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean deleted = this.jobService.deleteJobById(id);
        if (deleted) {
            return ResponseEntity.ok("Job deleted successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = this.jobService.updateJob(id, updatedJob);
        if (updated) {
            return ResponseEntity.ok("Job updated successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
