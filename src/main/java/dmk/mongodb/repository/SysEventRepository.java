package dmk.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dmk.mongodb.model.SysEvent;

@Repository
public interface SysEventRepository extends MongoRepository<SysEvent, String>{

	// Spring MongoRepo will generate the findBys for us
	public List<SysEvent> findByEventType(String eventType);
//	public List<SysEvent> findByEventTypeLike(String eventTypeRegex);

	public List<SysEvent> findBySysEvent(String sysType);
//	public List<SysEvent> findBySysEventLike(String sysTypeRegex);
	
}
