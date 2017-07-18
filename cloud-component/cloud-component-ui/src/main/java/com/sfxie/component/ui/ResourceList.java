package com.sfxie.component.ui;

import java.util.ArrayList;
import java.util.List;

public class ResourceList {

	private List<ResourceEntity> resources;

	public List<ResourceEntity> getResources() {
		return resources;
	}

	public void setResources(List<ResourceEntity> resources) {
		this.resources = resources;
	}

	public ResourceList() {
		this.resources = new ArrayList<ResourceEntity>();
	}
	
	public ResourceList addResourceEntity(ResourceEntity resourceEntity){
		this.resources.add(resourceEntity);
		return this;
	}

	
}
