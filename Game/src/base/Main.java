package base;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import game.Game;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;

import Utils.Log;


public class Main {

	public static Window window;

	public static int WIDTH = 1280;
	public static int HEIGHT = 600;

	public static void main(String[] args) {

		window = createWindow(WIDTH, HEIGHT, "Brim Of Life");

		Game game = new Game();

		while(glfwWindowShouldClose(window.getWindowID()) == GL_FALSE) {


			game.update();
			game.render();
			glfwPollEvents();
			glfwSwapBuffers(window.getWindowID());



		}

		game.dispose();

		window.getErrorCallBack().release();
		window.getKeyCallback().release();

		glfwDestroyWindow(window.getWindowID());
		glfwTerminate();

		System.exit(0);
	}

	private static Window createWindow(int width, int height, String title) {
		if(glfwInit() != GL_TRUE) {
			Log.err("Error initialising glfw window");
			System.exit(1);
		}

		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		glfwWindowHint(GLFW_SAMPLES, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

		long windowID = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);

		glfwSetWindowPos(
				windowID,
				(GLFWvidmode.width(vidmode) - WIDTH) / 2,
				(GLFWvidmode.height(vidmode) - HEIGHT) / 2
				);   

		GLFWErrorCallback errorCallBack;
		GLFWKeyCallback keyCallBack;

		if (windowID == NULL) {
			System.exit(1);
		}

		glfwMakeContextCurrent(windowID);
		GLContext.createFromCurrent();
		glfwSwapInterval(1);

		glfwSetKeyCallback(windowID, keyCallBack = new GLFWKeyCallback() {
			public void invoke(long window, int key, int scancode, int action, int mods) {
				onKeyInvoke(window, key, scancode, action, mods);
			}
		});

		glfwSetErrorCallback(errorCallBack = Callbacks.errorCallbackPrint(System.err));

		return new Window(windowID, keyCallBack, errorCallBack);
	}

	public static void onKeyInvoke(long window, int key, int scancode, int action, int mods) {

	}
}
