package com.hkq.mtspring.beans.io.loader;

import java.net.URL;

import com.hkq.mtspring.beans.io.resource.Resource;
import com.hkq.mtspring.beans.io.resource.UrlResource;

public class UrlResourceLoader implements ResourceLoader {

	public Resource getResource(String location) {
		URL resource = this.getClass().getClassLoader().getResource(location);
		return new UrlResource(resource);
	}
	
}
