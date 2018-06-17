package space.starfish.starfishbatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import space.starfish.starfishbatch.domain.Video;
import space.starfish.starfishbatch.repository.VideoRepository;

@RestController
public class VideoController {

    private final VideoRepository videoRepository;

    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @GetMapping("/videos")
    public Flux<Video> findAll() {
        return videoRepository.findAll();
    }

}
