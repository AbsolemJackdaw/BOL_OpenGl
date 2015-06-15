package game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import utils.Log;
import engine.ShaderProgram;

public class Game {

	private ShaderProgram shaderProgram;

	private int vaoID;
	private int vboID;

	public Game() {
		Log.info("game started !");
	}

	public void update(float delta) {

	}

	public void render(float delta) {
	}

	public void dispose() {
	}


	public void init(){
		//initialize shader
		shaderProgram = new ShaderProgram();
		//load both shaders
		shaderProgram.attachVertexShader("/shaders/VertexShader.vs");
		shaderProgram.attachFragmentShader("/shaders/FragmentShader.fs");
		//link the shader to the programID
		shaderProgram.link();

	}
}
