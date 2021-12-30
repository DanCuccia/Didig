package com.didig.tier0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.didig.drawing.SceneObject;
import com.didig.exception.SceneLayerOutOfBoundsException;
import com.didig.util.AppTime;

import android.graphics.Canvas;


/**
 * The Scene is a HashMap of SceneObject ArrayLists,
 * This abstract class is inherited from, to build your major game states
 * @author Dan Cuccia
 */
public abstract class Scene 
{
	public static final short 						MAX_SCENE_LAYER_DEPTH 		= 32;
	protected Map<Short, ArrayList<SceneObject>> 	mSceneMap 					= new HashMap<Short, ArrayList<SceneObject>>(MAX_SCENE_LAYER_DEPTH);
	
	protected Scene()
	{	}
	
	/**
	 * Add a SceneObject to the Scene's HashMap
	 * @param obj The SceneObject you wish to add
	 * @param sceneLayer The Scene Layer you wish to add into
	 * @throws Exception if your scene layer is out of bounds
	 */
	public void AddSceneObject(SceneObject obj, short sceneLayer) throws Exception
	{
		if(sceneLayer < 0 && sceneLayer > Scene.MAX_SCENE_LAYER_DEPTH)
		{
			throw new SceneLayerOutOfBoundsException();
		}
		this.mSceneMap.get(sceneLayer).add(obj);
	}
	
	/**
	 * Movement / Animation Logic (Called before Update())
	 * @param time current application timing
	 */
	public abstract void AnimateScene(AppTime time);
	/**
	 * Apply Logic to any Movement / Animation (Called after Animate())
	 * @param time current application timing
	 */
	public abstract void UpdateScene(AppTime time);
	/**
	 * Any objects that have dirty drawing variables should get updated in here
	 * (eg. matrices, vectors, bones, etc..)
	 */
	public abstract void PrepareScene();
	/**
	 * Draw the Scene Map to the screen
	 * @param canvas used for 2D drawing
	 */
	public abstract void DrawScene(Canvas canvas);
	
	/**
	 * The Map which is returned should be used as a HashMap object
	 * @return Map of the Scene
	 */
	public Map<Short, ArrayList<SceneObject>> getSceneMap()
	{
		return this.mSceneMap;
	}

}
