package space.starfish.starfishbatch.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import space.starfish.starfishbatch.domain.Video;

public interface VideoService {

    Mono<Video> createVideo(Video video);

    Flux<Video> findAll();

    Mono<Video> findAllByAbsolutePath(String path);

}
