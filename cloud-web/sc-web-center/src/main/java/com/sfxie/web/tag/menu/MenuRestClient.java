package com.sfxie.web.tag.menu;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sfxie.component.data.Result;
import com.sfxie.component.node.ParentNode;
import com.sfxie.utils.MapBuilder;
import com.sfxie.utils.jacson.codehaus.JsonUtil;
import com.sfxie.web.boot.util.ServerPathUtil;
import com.sfxie.web.boot.util.ServerPathUtil.ServiceName;


@Component
public class MenuRestClient {
	
	
    @Autowired
    private RestTemplate template;
    
    public List<ParentNode> getMenus(){
    	Map<String,String> urlVariables = new MapBuilder<String, String>()
    			.put("partitionCompany", "0")
    			.put("menuName", "")
    			.map();
    	Result<List<ParentNode>> result = template.postForObject(ServerPathUtil.getServerPath(ServiceName.centerServer)+"/menu/list", urlVariables, Result.class);
    	List<ParentNode> menus = JsonUtil.listFromJSON(JsonUtil.toJSON(result.getData()), ParentNode.class);
    	return menus;
    }
    
}
