package space.starfish.starfishbatch.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class Video {

    String filename;
    long fileSize;
    String absolutePath;
}
