package dmk.mongodb.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dmk.mongodb.conf.Bootstrap;
import dmk.mongodb.model.SysEvent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Bootstrap.class)
public class SysEventRepositoryTest {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SysEventRepository sysEventRepository;
	@Autowired
	MongoOperations mongoOps;
	
	@Before
	public void before(){
		
		SysEvent e1 = new SysEvent();
		e1.setDeleted(false);
		e1.setEventType("View");
		e1.setSysEvent("127.0.0.1 view http://spring.io/guides");
		
		SysEvent e2 = new SysEvent();
		e2.setDeleted(false);
		e2.setEventType("View");
		e2.setSysEvent("127.0.0.1 created http://google.com");

		boolean exists = mongoOps.collectionExists(SysEvent.class);
		if(!exists){
			mongoOps.createCollection(SysEvent.class);
		}
		sysEventRepository.save(e1);
		sysEventRepository.save(e2);

	}
	
	@After
	public void after(){
//		sysEventRepository.deleteAll();
	}
	
	@Test
	public void findAll(){
		logger.debug("test mongo finder");
		final List<SysEvent> events = sysEventRepository.findAll();
		for (SysEvent sysEvent : events) {
			logger.debug(sysEvent.toString());
		}
	}
	
	@Test
	public void findBy(){
		logger.debug("testFindBy");
		final List<SysEvent> events = sysEventRepository.findByEventType("View");
		assertTrue(events.size() >= 2);
	}
	
}