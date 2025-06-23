package com.learn.firstjobapp.job;
import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(long id);
    boolean deleteJobById(long id);

    boolean updateJob(long id, Job updatedJob);
}
