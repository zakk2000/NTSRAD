package com.nts.rad.learningtest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class JUnitTest {

	static Set<JUnitTest> jUnitTests = new HashSet<JUnitTest>();
	
	@Test
	public void test1() {
		
		/*assertThat(this, is(not(sameInstance(jUnitTest))));
		jUnitTest = this;*/
		assertThat(jUnitTests, not(hasItem(this)));
		jUnitTests.add(this);
	
	}
	
	@Test
	public void test2() {
		
		/*assertThat(this, is(not(sameInstance(jUnitTest))));
		jUnitTest = this;*/
		assertThat(jUnitTests, not(hasItem(this)));
		jUnitTests.add(this);
	
	}
	
	@Test
	public void test3() {
		
		/*assertThat(this, is(not(sameInstance(jUnitTest))));
		jUnitTest = this;*/
		assertThat(jUnitTests, not(hasItem(this)));
		jUnitTests.add(this);
	
	}

}
