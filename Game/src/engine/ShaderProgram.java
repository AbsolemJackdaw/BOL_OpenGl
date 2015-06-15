package engine;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;
import utils.Utility;

public class ShaderProgram {

	private int vertexShaderID;
	private int programID;
	int fragmentShaderID;


	public ShaderProgram() {
		programID = glCreateProgram();
	}

	/**
	 * Attach a Vertex Shader to this program.
	 *
	 * @param name The file name of the vertex shader.
	 */
	public void attachVertexShader(String name)
	{
		// Load the source
		String vertexShaderSource = Utility.readFromFile(name);

		// Create the shader and set the source
		vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexShaderID, vertexShaderSource);

		// Compile the shader
		glCompileShader(vertexShaderID);

		// Check for errors
		if (glGetShaderi(vertexShaderID, GL_COMPILE_STATUS) == GL_FALSE)
			throw new RuntimeException("Error creating vertex shader\n"
					+ glGetShaderInfoLog(vertexShaderID, glGetShaderi(vertexShaderID, GL_INFO_LOG_LENGTH)));

		// Attach the shader
		glAttachShader(programID, vertexShaderID);
	}

	public void attachFragmentShader(String name)
	{
		// Read the source
		String fragmentShaderSource = Utility.readFromFile(name);

		// Create the shader and set the source
		fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentShaderID, fragmentShaderSource);

		// Compile the shader
		glCompileShader(fragmentShaderID);

		// Check for errors
		if (glGetShaderi(fragmentShaderID, GL_COMPILE_STATUS) == GL_FALSE)
			throw new RuntimeException("Error creating fragment shader\n"
					+ glGetShaderInfoLog(fragmentShaderID, glGetShaderi(fragmentShaderID, GL_INFO_LOG_LENGTH)));

		// Attach the shader
		glAttachShader(programID, fragmentShaderID);
	}

	public void link(){
		// Link this program
		glLinkProgram(programID);

		// Check for linking errors
		if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE)
			throw new RuntimeException("Unable to link shader program:");
	}

	public void dispose()
	{
		// Unbind the program
		unbind();

		// Detach the shaders
		glDetachShader(programID, vertexShaderID);
		glDetachShader(programID, fragmentShaderID);

		// Delete the shaders
		glDeleteShader(vertexShaderID);
		glDeleteShader(fragmentShaderID);

		// Delete the program
		glDeleteProgram(programID);
	}


	/**
	 * Bind this program to use.
	 */
	public void bind()
	{
		glUseProgram(programID);
	}

	/**
	 * Unbind the shader program.
	 */
	public static void unbind()
	{
		glUseProgram(0);
	}


	public int getProgramID() {
		return programID;
	}

}
