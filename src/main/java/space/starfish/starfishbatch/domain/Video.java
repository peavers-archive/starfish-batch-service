package space.starfish.starfishbatch.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@Data
@Builder
@Document(collection = "video")
public class Video extends BaseEntity {

    long fileSize;

    @TextIndexed
    String filename;

    @Indexed
    String absolutePath;
}
