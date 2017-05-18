package com.sfxie.component.node;

public class ParentNode extends NodeComponent {
	
	public ParentNode(){}

	public ParentNode(String code, String name){
		super(code, name);
	}

	@Override
	public void add(NodeComponent c) throws Exception {
		this.getNodes().add(c);
	}

	@Override
	public void remove(NodeComponent c) throws Exception {
		this.getNodes().remove(c);
	}

	@Override
	public void foreach() throws Exception {
	}

}
