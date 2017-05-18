package com.sfxie.component.node;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sfxie.component.data.Data;

public class NodeComponent extends Data {
	
	public NodeComponent(){}

	public NodeComponent(String code, String name) {
		super(code, name);
	}
	

	@JsonInclude(Include.NON_NULL)
	protected List<NodeComponent> nodes;
	
	@JsonInclude(Include.NON_NULL)
	private String url;
	
	@JsonInclude(Include.NON_NULL)
	private Integer sequenceNo;


	/**
	 * 添加子节点
	 * @param c
	 */
	public void add(NodeComponent c) throws Exception{
		
	}
	/**
	 * 删除子节点
	 * @param c
	 */
	public void remove(NodeComponent c) throws Exception{
		
	}
	public void foreach() throws Exception{
		
	}
	
	
	public List<NodeComponent> getNodes() {
		if(null == nodes)
			nodes = new ArrayList<NodeComponent>();
		return nodes;
	}
	public void setNodes(List<NodeComponent> nodes) {
		this.nodes = nodes;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

}
