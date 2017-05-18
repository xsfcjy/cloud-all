package com.sfxie.component.node;


public class LeftNode extends NodeComponent {

	public LeftNode(){}
	
	public LeftNode(String code, String name) {
		super(code, name);
	}

	@Override
	public void add(NodeComponent c) throws Exception {
		throw new Exception("叶子节点不充许添加子节点！");
	}

	@Override
	public void remove(NodeComponent c) throws Exception {
		throw new Exception("叶子节点没有子节点！");
	}

	@Override
	public void foreach() throws Exception {
		throw new Exception("叶子节点没有子节点！");
	}

}
