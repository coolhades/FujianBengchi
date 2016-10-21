package com.mbmpv.treedemo;

import com.zhy.tree.bean.TreeNodeId;
import com.zhy.tree.bean.TreeNodeLabel;
import com.zhy.tree.bean.TreeNodePid;

public class FileBean
{
	@TreeNodeId
	private int _id;
	@TreeNodePid
	private int parentId;
	@TreeNodeLabel
	private String name;
	private long length;
	private String desc;
	
	private String key;
	
	

	public FileBean(int _id, int parentId, String name,String key)
	{
		super();
		this._id = _id;
		this.parentId = parentId;
		this.name = name;
		this.key=key;
	}

}
