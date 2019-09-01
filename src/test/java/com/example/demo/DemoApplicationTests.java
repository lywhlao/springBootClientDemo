package com.example.demo;

import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.tomcat.util.buf.ByteBufferHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() throws IllegalAccessException, InstantiationException {

		Class<?> dynamicType = new ByteBuddy()
				.subclass(Object.class)
				.method(ElementMatchers.named("toString"))
				.intercept(FixedValue.value("Hello World!"))
				.make()
				.load(getClass().getClassLoader())
				.getLoaded();

		System.out.println(dynamicType.newInstance().toString());
		assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
	}

	@Test
	public void test2(){
		FastThreadLocal fastThreadLocal=new FastThreadLocal();
		fastThreadLocal.set("aaa");
		fastThreadLocal.set("bbb");

		FastThreadLocal f2=new FastThreadLocal();
		f2.set("ccc");

		f2.get();

		ThreadLocal threadLocal=new ThreadLocal();
		threadLocal.set("aaa");
		threadLocal.get();
	}





}
