package com.johnfnash.learn.springboot.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.springboot.async.demo.AsyncExceptionDemo;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class SpringbootAsyncApplicationTests {

	@Autowired
	private AsyncExceptionDemo demo;
	
	@Test
	public void simple() {
//		for (int i=0;i<3;i++){
//			try {
//				Future<String> future = demo.hardDemo("hard");
				//demo.simple();
				demo.inputDemo("input");
				
				// 等待异步方法执行完成，不然可能无法看到完整的输出内容
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				log.info(future.get());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
	}

}
