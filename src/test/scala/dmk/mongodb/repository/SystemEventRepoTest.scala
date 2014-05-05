package dmk.mongodb.repository

import java.util.UUID

import scala.collection.JavaConversions._

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import dmk.mongodb.conf.Bootstrap
import dmk.mongodb.model.SystemEvent

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes=Array(classOf[Bootstrap]))
class SystemEventRepoTest {
  var logger : Logger = LoggerFactory.getLogger(getClass())
  
  @Autowired
  var sysEventRepository: SystemEventRepository = null
  @Autowired
  var mongoOps: MongoOperations = null
  
  @Before
  def before(): Unit = {
    
    val exists : Boolean = mongoOps.collectionExists(classOf[SystemEvent])
    if(!exists){
      mongoOps.createCollection(classOf[SystemEvent])
    }
    
    var e1 = new SystemEvent(UUID.randomUUID().toString, "view", "document1", false)
    var e2 = new SystemEvent(UUID.randomUUID().toString, "delete", "document9000", false)
    sysEventRepository.save(e1)
    sysEventRepository.save(e2)

  }
  
  @After def after() = {
//    sysEventRepository.deleteAll()
  }
  
  @Test def testFindAll(){
    logger.info("\n\ntestFindAll")
    var events = sysEventRepository.findAll()
    events.foreach { (e) => logger.info(e.toString) }
  }
  
}