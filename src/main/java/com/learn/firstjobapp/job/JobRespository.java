package com.learn.firstjobapp.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRespository extends JpaRepository<Job,Long> {
}
