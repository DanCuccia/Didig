package com.didig.drawing;

import com.didig.datatype.CenterType2D;
import com.didig.datatype.World2D;


/**
 * functionality for drawing 2D sprites to the canvas
 * @author Dan Cuccia
 */
public class Sprite extends SceneObject
{
	World2D world = new World2D(CenterType2D.TOP_LEFT);
	
	
	
	@Override
	public String toConsole() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public World2D GetWorld()
	{
		return world;
	}
}
