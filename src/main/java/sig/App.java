package sig;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.JFrame;

public class App implements NativeKeyListener{

    public static boolean CLIENT = true; //true for CLIENT. false for SERVER.
    public static String SERVER = "projectdivar.com";
    public static int PORT = 5950;


    public static double FRAMETIME=0;
    public static Robot r;
    public static JFrame f;
    public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

    App(boolean client) {
        if (!client) {
            Server s = new Server();
            System.out.println("Starting server on port "+PORT+"...");
            s.start(PORT); 
        } else {
            new App();
        }
    }

    App() {
        /*RegisterKeyboardHook();


        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        RunInitialAnalysis();
        f = new JFrame("FFXIV AI");
*/
        Client c = new Client();
        System.out.println("Connecting to "+SERVER+" on port "+PORT+".");
        c.start(SERVER,PORT);
        System.out.println(c.sendMessage("I am in!"));
        System.out.println(c.sendMessage("EOF"));
        c.stop();
    }

    private void RegisterKeyboardHook() {
        try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(this);
    }

    public static void main(String[] args) {
        if (!CLIENT) {
            new App(CLIENT);
        } else {
            new App();
        }
    }
    private static void RunInitialAnalysis() {
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
    }
    private static BufferedImage CaptureScreen() throws IOException {
		BufferedImage screenshot = r.createScreenCapture(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		//ImageIO.write(screenshot,"png",new File("screenshot.png"));
		return screenshot;
	}
}