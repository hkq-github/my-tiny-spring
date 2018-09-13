package com.hkq.mtspring.beans.io.loader;

import com.hkq.mtspring.beans.io.resource.Resource;

public interface ResourceLoader {
	
	public Resource getResource(String location);
	
}
