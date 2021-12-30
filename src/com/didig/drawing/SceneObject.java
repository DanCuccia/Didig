package com.didig.drawing;

import com.didig.tier0.CIDrawableNull;
import com.didig.tier0.CIGrabbableNull;
import com.didig.tier0.CIUpdateNull;
import com.didig.tier0.IDrawable;
import com.didig.tier0.IGrabbable;
import com.didig.tier0.IUpdate;

/**
 * The base class for all Scene Objects to be inherited from
 * 	-Strategy Pattern: grab/draw/update logic
 * @author Dan Cuccia
 */
public abstract class SceneObject
{
	private int id = -1;
	
	public boolean 				Grabbable 			= false;
	public IGrabbable 			GrabLogic			= new CIGrabbableNull();
	
	public boolean 				Drawable			= false;
	public IDrawable			DrawLogic			= new CIDrawableNull();
	
	public boolean 				Updatable			= false;
	public IUpdate				UpdateLogic		= new CIUpdateNull();
	
	public abstract String toConsole();
	
	public int GetID() { return id; }
	public void SetID(int value) { id = value; }
}
