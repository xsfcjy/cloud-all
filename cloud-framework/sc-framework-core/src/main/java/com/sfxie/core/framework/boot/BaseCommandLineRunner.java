package com.sfxie.core.framework.boot;

import org.springframework.boot.CommandLineRunner;

/**
 * 
 * @description 用mvn spring-boot:run命令启动程序，会执行run方法。<br>
 *              对于那种只需要在应用程序启动时执行一次的任务，非常适合继承此抽象类
 * @author 	xiesf
 * @email 	xsfcy@126.com
 * @date 	2016年7月29日
 */
public abstract class BaseCommandLineRunner implements CommandLineRunner {
}
