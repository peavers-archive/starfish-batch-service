package space.starfish.starfishbatch.service;

import java.io.File;

public interface ThumbnailService {

    void generateJava(File file);

    void generateBash(File file);
}
