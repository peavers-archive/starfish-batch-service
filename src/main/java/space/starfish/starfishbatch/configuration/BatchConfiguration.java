package space.starfish.starfishbatch.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.starfish.starfishbatch.domain.Video;
import space.starfish.starfishbatch.listener.JobDurationListener;
import space.starfish.starfishbatch.processor.VideoProcessor;
import space.starfish.starfishbatch.reader.DirectoryItemReader;

import java.io.File;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Value("${starfish.movies.directoryPath}")
    public String directoryPath;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public VideoProcessor processor() {
        return new VideoProcessor();
    }

    @Bean
    public Job importUserJob(JobDurationListener listener, Step step) {
        return jobBuilderFactory.get("readFiles")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<File, Video>chunk(10)
                .reader(directoryItemReader())
                .processor(processor())
                .build();
    }

    @Bean
    public DirectoryItemReader directoryItemReader() {
        log.info("Scanning {}", directoryPath);

        return new DirectoryItemReader("file:" + directoryPath);
    }
}