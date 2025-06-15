package com.learn.firstjobapp.job.impl;

import com.learn.firstjobapp.job.Job;
import com.learn.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiveImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }
}
