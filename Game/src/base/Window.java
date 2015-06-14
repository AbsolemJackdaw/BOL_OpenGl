package base;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Window {

	private long windowID;
	
	private GLFWKeyCallback keyCallback;
	private GLFWErrorCallback errorCallback;
	
	public Window(long id, GLFWKeyCallback callback, GLFWErrorCallback callbackerror) {
		windowID = id;
		this.keyCallback = callback;
		this.errorCallback = callbackerror;
	}
	
	public GLFWKeyCallback getKeyCallback() {
		return keyCallback;
	}
	public GLFWErrorCallback getErrorCallBack() {
		return errorCallback;
	}
	public long getWindowID() {
		return windowID;
	}
}
