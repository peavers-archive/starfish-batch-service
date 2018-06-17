package space.starfish.starfishbatch.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import space.starfish.starfishbatch.domain.Video;

import java.io.File;

@Slf4j
public class VideoProcessor implements ItemProcessor<File, Video> {

    @Override
    public Video process(final File item) throws Exception {
        Video video = Video.builder()
                .filename(item.getAbsoluteFile().getName())
                .absolutePath(item.getAbsolutePath())
                .fileSize(item.getTotalSpace())
                .build();

        log.info("Created {}", video);

        return video;
    }
}
