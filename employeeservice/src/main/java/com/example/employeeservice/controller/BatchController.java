package com.example.employeeservice.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

//    @Autowired
//    SpringBatchConfig springBatchConfig;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    Job job;

    @RequestMapping("/batch")
    public void startBatch()throws Exception {

//        jobLauncher.run(job, new JobParameters());

        try {
            JobParameters jobParameters =
                    new JobParametersBuilder()
                            .addLong("time",System.currentTimeMillis()).toJobParameters();

            JobExecution execution = jobLauncher.run(job, jobParameters);
            System.out.println("Exit Status : " + execution.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
