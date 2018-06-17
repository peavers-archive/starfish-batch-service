package space.starfish.starfishbatch.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class DirectoryItemReader implements ItemReader<File>, InitializingBean {

    private final String directoryPath;

    private final List<File> foundFiles = Collections.synchronizedList(new ArrayList<>());

    public DirectoryItemReader(final String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public File read() {
        if (!foundFiles.isEmpty()) {
            return foundFiles.remove(0);
        }

        synchronized (foundFiles) {
            final Iterator files = foundFiles.iterator();

            if (files.hasNext()) {
                return foundFiles.remove(0);
            }
        }

        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (final Resource file : getFiles()) {
            this.foundFiles.add(file.getFile());
        }
    }

    private Resource[] getFiles() throws IOException {
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        return patternResolver.getResources(directoryPath);
    }
}