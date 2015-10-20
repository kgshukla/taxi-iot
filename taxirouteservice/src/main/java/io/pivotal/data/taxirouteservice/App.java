/**
 * 
 */
package io.pivotal.data.taxirouteservice;

import io.pivotal.data.taxirouteservice.models.TaxiRouteMetrics;

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
    LocalRegionFactoryBean<String, TaxiRouteMetrics> localRegionFactory(final GemFireCache cache) {
        return new LocalRegionFactoryBean<String, TaxiRouteMetrics>() {

            {
                setCache(cache);
                setName("TaxiData");
                setClose(false);
            }
        };
    }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
