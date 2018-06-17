package space.starfish.starfishbatch.service;

import lombok.extern.slf4j.Slf4j;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
import org.jcodec.scale.AWTUtil;
import org.springframework.stereotype.Service;
import sun.net.www.content.image.png;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class ThumbnailServiceImpl implements ThumbnailService {

    @Override
    public void generateJava(File file) {
        try {
            double startSec = 51.632;
            int frameCount = 10;

            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));
            grab.seekToSecondSloppy(startSec);

            for (int i = 0; i < frameCount; i++) {
                Picture picture = grab.getNativeFrame();

                BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
                ImageIO.write(bufferedImage, "png", new File(file.getAbsolutePath() + "-frame" + i + ".png"));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void generateBash(File file) {
//        ffmpeg  -i MOVIE.mp4 -r 0.0033 -vf scale=-1:120 -vcodec png capture-%002d.png


    }


}
