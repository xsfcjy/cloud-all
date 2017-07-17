package com.sfxie.component.node;

import java.util.Comparator;

/**
 * 菜单排序器
 * @author xiesf
 *
 */
public class NodeComponentSort implements Comparator<NodeComponent> {

	@Override
	public int compare(NodeComponent o1, NodeComponent o2) {
		if (o1.getSequenceNo() > o2.getSequenceNo())
            return 1;
        else if (o1.getSequenceNo() < o2.getSequenceNo())
            return -1;
        else
            return 0;
	}

}
