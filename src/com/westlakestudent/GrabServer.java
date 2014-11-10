package com.westlakestudent;

import com.westlakestudent.engine.GrabEngine;

/**
 *
 * GrabServer
 * @author chendong
 * 2014年9月12日 下午4:49:31
 * 
 * @version 2.0.0
 *
 */
public class GrabServer {

	public static void main(String[] args) {
		GrabEngine.getIntance().start();
	}
}
