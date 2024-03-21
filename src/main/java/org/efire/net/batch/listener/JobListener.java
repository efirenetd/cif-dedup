package org.efire.net.batch.listener;

import org.efire.net.dto.CustomerDTO;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobListener implements JobExecutionListener {

    /** The jdbc template. */
    private final JdbcTemplate jdbcTemplate;

    public JobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            //System.out.println("Batch Status:" +jobExecution.getStatus());
            jdbcTemplate.query("select customer_no, first_name, middle_name, last_name from customer_ind",
                (rs,rowNum)-> new CustomerDTO(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)))
                    .forEach(System.out::println);

        }
    }
}