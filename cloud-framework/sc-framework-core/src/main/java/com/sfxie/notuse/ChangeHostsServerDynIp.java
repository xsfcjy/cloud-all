package com.sfxie.notuse;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 动态改变部署机的hosts文件，添加scconfig域名的ip对应关系
 * @author xiesf
 *
 */
@Component
@Configurable
@EnableScheduling
@Profile("changeIp")
public class ChangeHostsServerDynIp {

	/**
	 * 动态改变部署机的hosts文件，添加scconfig域名的ip对应关系
	 * 每10分钟执行一次
	 * @throws UnknownHostException
	 */
    @Scheduled(cron = "${schedule.cron.changeId}")
	public static void changeIp() throws UnknownHostException {
    	String hostsFilePath = "C:\\Windows\\System32\\drivers\\etc\\hosts";
		InetAddress ip = InetAddress.getByName("xsfcjy.oicp.net");
		String ipString = ip.getHostAddress();
		String[] hostNames = new String[]{"scconfig"};
		FileModify obj = new FileModify();
        obj.write(hostsFilePath, obj.read(hostsFilePath,hostNames,ipString));
	}
}
