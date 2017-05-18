package com.sfxie.extension.zookeeper.curator.selection;



public class LLTest {
	
	public static String connectionString = "192.168.23.4:2181";
	
    public static void excute() throws Exception {
    	
        LeaderListener1.excute();
        LeaderListener2.excute();
        LeaderListener3.excute();
        LeaderListener4.excute();
    }
}