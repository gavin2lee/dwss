package com.gl.order.client.batch.bean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleBeanTest {
	ApplicationContext ctx;
	
	@Before
	public void setUp(){
		ctx = new ClassPathXmlApplicationContext("spring/quartz-application.xml");
		
	}

	@Test
	public void testSpring() {
		Assert.assertNotNull(ctx);
		SimpleBean sb = ctx.getBean("simpleBean", SimpleBean.class);
		Assert.assertNotNull(sb);
		String name = sb.getName();
		Assert.assertNotNull(name);
		Assert.assertEquals("simpleBean", name);
		
//		Assert.fail();
	}

}
