package com.sfxie.services.center.local;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CenterServiceApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;
	
	
	@Test
	public void sfxieSysCompanys() throws Exception {
		
		ResponseEntity<String> s = this.restTemplate.getForEntity("/sfxieSysCompanys", String.class, "Phil");
		System.out.println(s.getBody());
//		sfxieSysCompanys

//	    SfxieSysCompany sfxieSysCompany = new SfxieSysCompany();
//		sfxieSysCompany.setCompanyNameCn("");
//		List<SfxieSysCompany> list = centerServiceImpl.selectByCondition(sfxieSysCompany);
//		System.out.println(list.size());
	}
}
