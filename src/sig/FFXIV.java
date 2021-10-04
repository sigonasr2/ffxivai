package sig;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FFXIV{
    public long FRAMETIME=0;
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
        System.out.println("60 captures took "+((double)((int)(System.nanoTime()-startTime)/10000000)/100)+"s");
        System.out.println("Hello World!");
    }
    private static BufferedImage CaptureScreen() throws IOException {
		BufferedImage screenshot = r.createScreenCapture(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		//ImageIO.write(screenshot,"png",new File("screenshot.png"));
		return screenshot;
	}
}