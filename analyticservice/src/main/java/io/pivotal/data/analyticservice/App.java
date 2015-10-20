/**
 * 
 */
package io.pivotal.data.analyticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author shuklk2
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

}
