package space.starfish.starfishbatch.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import space.starfish.starfishbatch.domain.Video;

@Repository
public interface VideoRepository extends ReactiveMongoRepository<Video, String> {

    Mono<Video> findAllByAbsolutePath(String path);

}
