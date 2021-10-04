package sig;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FFXIV{
    public static double FRAMETIME=0;
    public static Robot r;
    public static void main(String[] args) {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        long startTime = System.nanoTime();
        for (int i=0;i<60;i++) {
            try {
                CaptureScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FRAMETIME = ((double)((long)(System.nanoTime()-startTime)/10000000)/100);
        System.out.println("60 captures took "+FRAMETIME+"s. FPS:"+Math.floor(60/FRAMETIME));
        if (60/FRAMETIME<10) {
            System.out.println(" WARNING!! For best performance, it's highly recommended to use this on a computer that can compute at least 10 frames per second!");
        }
        System.out.println("Hello World!");
    }
    private static BufferedImage CaptureScreen() throws IOException {
		BufferedImage screenshot = r.createScreenCapture(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		//ImageIO.write(screenshot,"png",new File("screenshot.png"));
		return screenshot;
	}
}