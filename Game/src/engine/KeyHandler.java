package engine;

import static org.lwjgl.glfw.GLFW.*;

public class KeyHandler {


	static boolean UP;
	static boolean DOWN;
	static boolean LEFT;
	static boolean RIGHT;


	public static void setKey(int keyCode, int action){
		switch (keyCode)
		{
		case GLFW_KEY_Z:
			UP = (action != GLFW_RELEASE);
			break;
		case GLFW_KEY_S:
			DOWN = (action != GLFW_RELEASE);
			break;
		case GLFW_KEY_Q:
			LEFT = (action != GLFW_RELEASE);
			break;
		case GLFW_KEY_D:
			RIGHT = (action != GLFW_RELEASE);
			break;
		}
	}


}
