package foopis.main;

import javax.sound.sampled.*;
import java.io.*;

public class MusicPlayer
{
    private Clip clipNonLoop;
    private Clip clipLoop;
    private boolean hasNonLoop;
    private boolean hasLoop;
    private boolean nonLoopPlaying;
    private boolean musicStarted;

    public MusicPlayer()
    {
        clipNonLoop = null;
        clipLoop = null;
        hasNonLoop = false;
        hasLoop = false;
        nonLoopPlaying = false;
    }

    public void setSong(String nonLoop,String loop)
    {
        hasNonLoop = nonLoop!=null;
        hasLoop = loop!=null;

        if(hasNonLoop) {
            File file = new File("songs\\" + nonLoop + ".wav");
            try {
                clipNonLoop = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(file);
                clipNonLoop.open(ais);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(hasLoop) {
            File file = new File("songs\\" + loop + ".wav");
            try {
                clipLoop = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(file);
                clipLoop.open(ais);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void playSong()
    {
        if(clipNonLoop!=null) {
            nonLoopPlaying = true;
            clipNonLoop.start();
        }

        if(clipLoop!=null&&!nonLoopPlaying)
        {
            clipLoop.loop(-1);
        }
    }

    public void update()
    {
        if(nonLoopPlaying&&clipNonLoop.getMicrosecondLength() == clipNonLoop.getMicrosecondPosition())
        {
                nonLoopPlaying=false;
            if(clipLoop!=null)
            {
                clipLoop.loop(-1);
            }
        }
    }
}