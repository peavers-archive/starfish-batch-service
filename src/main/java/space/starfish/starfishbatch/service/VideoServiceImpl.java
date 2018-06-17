package space.starfish.starfishbatch.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import space.starfish.starfishbatch.domain.Video;
import space.starfish.starfishbatch.repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Mono<Video> createVideo(Video video) {
        return videoRepository.insert(video);
    }

    @Override
    public Flux<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public Mono<Video> findAllByAbsolutePath(String path) {
        return videoRepository.findAllByAbsolutePath(path)
                .switchIfEmpty(Mono.error(new Exception("No video found with path : " + path)));
    }
}
