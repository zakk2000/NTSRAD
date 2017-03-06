package com.nts.rad.learningtest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.either;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="applicationContext.xml")
public class SpringTestContextTest {

	@Autowired ApplicationContext context;
	
	static Set<SpringTestContextTest> obj = new HashSet<SpringTestContextTest>();
	static ApplicationContext applicationContext = null;
	
	@Test
	public void test1() {
		
		assertThat(obj, not(hasItem(this)));
		obj.add(this);
		
		assertThat(applicationContext == null || applicationContext == this.context, is(true));
		applicationContext = this.context;
	
	}
	
	@Test
	public void test2() {
		
		assertThat(obj, not(hasItem(this)));
		obj.add(this);
		
		assertTrue(applicationContext == null || applicationContext == this.context);
		applicationContext = this.context;
	
	}
	
	@Test
	public void test3() {
		
		assertThat(obj, not(hasItem(this)));
		obj.add(this);
		
		assertThat(applicationContext, either(is(nullValue())).or(is(this.context)));
		applicationContext = this.context;
	
	}

}
