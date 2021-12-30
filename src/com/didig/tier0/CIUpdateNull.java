package com.didig.tier0;

import com.didig.util.AppTime;

/**
 * Null Pattern - Default Null Update Interface
 * @author Dan Cuccia
 */
public class CIUpdateNull implements IUpdate
{
	@Override
	public void OnAnimate(AppTime time) { }

	@Override
	public void OnUpdate(AppTime time) { }
}
