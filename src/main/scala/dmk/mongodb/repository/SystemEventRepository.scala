package dmk.mongodb.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import dmk.mongodb.model.SystemEvent
import scala.collection.JavaConversions._

@Repository
trait SystemEventRepository extends MongoRepository[SystemEvent, String]{
	// no property system found for type List error when I uncomment
//	def findByEventType(eventType: String): List[SystemEvent]
//	def findBySystemEvent(sysType: String): List[SystemEvent] 
	
}