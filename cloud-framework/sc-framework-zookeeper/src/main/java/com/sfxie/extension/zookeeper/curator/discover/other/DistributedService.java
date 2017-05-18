package com.sfxie.extension.zookeeper.curator.discover.other;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * @author: elite_jigang@163.com
 */
@JsonRootName("services")
public class DistributedService {
    private String address ;
    private String info;
    private String sfxie;

    public DistributedService(){
        this("", "");
    }
    public DistributedService(String address, String info){
        this.address = address;
        this.info = info;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
	public String getSfxie() {
		return ""+System.currentTimeMillis();
	}
	public void setSfxie(String sfxie) {
		this.sfxie = sfxie;
	}
    
}
