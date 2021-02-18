package com.cdd.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch spritebatch;
	BitmapFont font;
	Texture img;
	TextureRegion textureRegion;
	Vector2 position;
	Vector2 touchPosition;
	float movespeed,delta;
	OrthographicCamera camera;
	@Override
	public void create () {
		spritebatch= new SpriteBatch();
		font= new BitmapFont(true);
		textureRegion= new TextureRegion(new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg"))));
		textureRegion.flip(false,true);
		position= new Vector2(0,0);
		touchPosition= new Vector2(0,0);
		movespeed=200f;
		camera = new OrthographicCamera();

		camera.setToOrtho(true,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	}
@Override
public  void dispose()
{
	spritebatch.dispose();;
	font.dispose();;
	textureRegion.getTexture().dispose();
}
	@Override
	public void render () {
	delta = Gdx.graphics.getDeltaTime();
		if(Gdx.input.justTouched())
			touchPosition.set(Gdx.input.getX(),Gdx.input.getY());
		if(touchPosition.x > position.x)
			position.x+=movespeed*delta;
		else if(touchPosition.x< position.x)
			position.x-=movespeed*delta;
		if(touchPosition.y > position.y)
			position.y+=movespeed*delta;
		else if(touchPosition.y< position.y)
			position.y-=movespeed*delta;
		if(Math.abs(touchPosition.x-position.x)<5)
			position.x=touchPosition.x;
		if(Math.abs(touchPosition.y-position.y)<5)
			position.y=touchPosition.y;
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.justTouched())
		camera.update();
		spritebatch.setProjectionMatrix(camera.combined);
		spritebatch.begin();
		spritebatch.draw(textureRegion,position.x,position.y);
		spritebatch.end();
	}
		@Override
				public void resize(int width,int height)
		{
		camera.setToOrtho(true,width,height);
	}
		@Override
				public void pause() {
		}
		@Override
				public  void resume()
		{
	}

}
