package io.pivotal.data.freetaxiservice;


import java.util.ArrayList;
import java.util.List;

import io.pivotal.data.freetaxiservice.model.FreeTaxiMetrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.gemstone.gemfire.cache.GemFireCache;

/**
 * @author shuklk2
 *
 */

@SpringBootApplication
@EnableGemfireRepositories
public class App {

	@Bean
    CacheFactoryBean cacheFactoryBean() {
        return new CacheFactoryBean();
    }
	
    @Bean
    LocalRegionFactoryBean<String, FreeTaxiMetrics> localRegionFactory(final GemFireCache cache) {
        return new LocalRegionFactoryBean<String, FreeTaxiMetrics>() {

            {
                setCache(cache);
                setName("FreeTaxiList");
                setClose(false);
            }
        };
    }
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

}
