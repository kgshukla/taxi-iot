/**
 * 
 */
package io.pivotal.data.freetaxiservice.services;

import io.pivotal.data.freetaxiservice.model.FreeTaxiData;
import io.pivotal.data.freetaxiservice.model.FreeTaxiMetrics;
import io.pivotal.data.freetaxiservice.repositories.FreeTaxiRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.internal.cache.tier.sockets.VersionedObjectList.Iterator;

/**
 * @author shuklk2
 *
 */

@Component
public class FreeTaxiService {

	@Autowired
	private FreeTaxiRepository repository;
	
	public List<FreeTaxiData> getFreeTaxiesList() {
		
		/*
		List<String> data1 = new ArrayList<String>();
        data1.add("41");
        data1.add("81");
        data1.add("1234");
        List<String> data2 = new ArrayList<String>();
        data2.add("40");
        data2.add("80");
        data2.add("12345");
        
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(data1);
        list.add(data2);
        
        FreeTaxiMetrics ftm = new FreeTaxiMetrics();
        ftm.setData(list);
        ftm.setKey("LATEST_DATA");
        repository.save(ftm);
        */
		
		
		List<List<String>> data = repository.findOne("LATEST_DATA").getData();
		
		List<FreeTaxiData> listData = new ArrayList<FreeTaxiData>();
		
		for (java.util.Iterator<List<String>> it = data.iterator(); it.hasNext();) {
			List<String> taxiData = it.next();
			FreeTaxiData freetaxiData = new FreeTaxiData();
			freetaxiData.setLatitude(taxiData.get(0));
			freetaxiData.setLongitude(taxiData.get(1));
			freetaxiData.setDriver_num(taxiData.get(2));
			listData.add(freetaxiData);
		}
		return listData;
		
		/*
		FreeTaxiData ftd1 = new FreeTaxiData();
		ftd1.setLatitude("41");
		ftd1.setLongitude("81");
		ftd1.setDriver_num("1234");
		FreeTaxiData ftd2 = new FreeTaxiData();
		ftd2.setLatitude("40");
		ftd2.setLongitude("8");
		ftd2.setDriver_num("xyz");
		
		List<FreeTaxiData> data1 = new ArrayList<FreeTaxiData>();
        data1.add(ftd1);
        data1.add(ftd2);
        
        return data1;
        */
	}
	
}
