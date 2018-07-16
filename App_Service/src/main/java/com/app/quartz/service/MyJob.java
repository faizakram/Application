package com.app.quartz.service;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

@Service
@DisallowConcurrentExecution
public class MyJob implements Job {
    /*@Autowired
    private MyService myService;*/

    @Override
    public void execute(JobExecutionContext jobExecutionContext) 
        throws JobExecutionException {
        System.out.println("Message:============= ");
    }
}
