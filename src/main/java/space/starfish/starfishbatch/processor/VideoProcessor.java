package space.starfish.starfishbatch.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import reactor.core.publisher.Mono;
import space.starfish.starfishbatch.domain.Video;
import space.starfish.starfishbatch.service.VideoService;

import java.io.File;

@Slf4j
public class VideoProcessor implements ItemProcessor<File, Video> {

    private final VideoService videoService;

    public VideoProcessor(VideoService videoService) {
        this.videoService = videoService;
    }

    @Override
    public Video process(final File file) throws Exception {

        videoService.findAllByAbsolutePath(file.getAbsolutePath());

        Video video = Video.builder()
                .filename(file.getAbsoluteFile().getName())
                .absolutePath(file.getAbsolutePath())
                .fileSize(file.getTotalSpace())
                .build();

        videoService.createVideo(video).subscribe();

        log.info("Created {}", video);

        return video;
    }
}
