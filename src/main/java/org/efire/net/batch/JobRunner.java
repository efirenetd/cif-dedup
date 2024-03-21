package org.efire.net.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements CommandLineRunner {

  @Autowired
  private JobLauncher jobLauncher;
  @Autowired
  private ApplicationContext applicationContext;

  @Override
  public void run(String... args) throws Exception {
    Job job = applicationContext.getBean("csvToDatabaseJob", Job.class);
    jobLauncher.run(job, new JobParameters());
  }
}
