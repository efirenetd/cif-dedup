package org.efire.net.batch.config;

import org.efire.net.batch.listener.JobListener;
import org.efire.net.batch.model.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class CsvToDatabaseJobConfig {


    @Bean
    public Job csvToDatabaseJob(JobRepository jobRepository, Step csvToDatabaseStep, JobListener jobListener) {
        return new JobBuilder("csvToDatabaseJob", jobRepository)
                .start(csvToDatabaseStep)
                .listener(jobListener)
                .build();
    }

    @Bean
    public Step csvToDatabaseStep(JobRepository jobRepository,
                                  FlatFileItemReader<Customer> csvFileReader,
                                  ItemProcessor<Customer, Customer> personItemProcessor,
                                  JdbcBatchItemWriter<Customer> jdbcBatchItemWriter,
                                  PlatformTransactionManager transactionManager) {
        return new StepBuilder("csvToDatabaseStep", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .reader(csvFileReader)
                .processor(personItemProcessor)
                .writer(jdbcBatchItemWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<Customer> csvFileReader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("csvFileReader")
                .resource(new ClassPathResource("data.csv"))
                .linesToSkip(1)
                .delimited()
                .names("customerNo", "firstName", "middleName", "lastName") // Column names in the CSV file
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Customer.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Customer> jdbcBatchItemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Customer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .dataSource(dataSource)
                .sql("INSERT INTO customer_ind (customer_no, first_name, middle_name, last_name) " +
                        "VALUES (:customerNo, :firstName, :middleName, :lastName)")
                .build();
    }

    @Bean
    public ItemProcessor<Customer, Customer> personItemProcessor() {

        return item -> {
            item.setFirstName(item.getFirstName().toUpperCase());
            item.setMiddleName(item.getMiddleName().toUpperCase());
            item.setLastName(item.getLastName().toUpperCase());
            return item;
        };
    }
}
