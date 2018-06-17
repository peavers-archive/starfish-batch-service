package space.starfish.starfishbatch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JobDurationListener extends JobExecutionListenerSupport {

    private StopWatch stopWatch;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        stopWatch = new StopWatch();
        stopWatch.start("Processing videos");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            stopWatch.stop();

            long duration = stopWatch.getLastTaskTimeMillis();

            String time = String.format("Video processing job ran for: %d minutes, %d seconds.",
                    TimeUnit.MILLISECONDS.toMinutes(duration),
                    TimeUnit.MILLISECONDS.toSeconds(duration) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));


            log.info(time);
        }
    }

}