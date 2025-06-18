package com.learn.firstjobapp.job.impl;

import com.learn.firstjobapp.job.Job;
import com.learn.firstjobapp.job.JobRespository;
import com.learn.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    /*private List<Job> jobs = new ArrayList<>();
    The above code is only for array storage method only, lets move on with Jpa and make this code persistant*/
    private final JobRespository jobRespository;

    public JobServiceImpl(JobRespository jobRespository) {
        this.jobRespository = jobRespository;
    }

    @Override
    public List<Job> findAll() {
        return jobRespository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRespository.save(job);
    }

    @Override
    public Job getJobById(long id) {
        return jobRespository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(long id) {
        try {
            jobRespository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateJob(long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRespository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRespository.save(job); // this line is essential
            return true;
        }

        return false;
    }
}
