package com.didig.exception;

/**
 * Exception used when attempting to use an out of bound sceneLayer depth
 * @author Dan Cuccia
 */
public class SceneLayerOutOfBoundsException extends Exception 
{
	public SceneLayerOutOfBoundsException()
	{
		super("Scene Layer is out of Bounds - must be between 0 and Scene.MAX_SCENE_LAYER_DEPTH");
	}
	private static final long serialVersionUID = 1L;
}