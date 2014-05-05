package dmk.mongodb.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SystemEventTest {
  
  var e1: SystemEvent = null

  @Before
  def setup() = {
    e1 = SystemEvent("eventid1", "viewEventType", 
			  "127.0.0.1 visited http://localhost/home", false)
			  
  }
  
  @Test def testToString() = {
	  val str = e1.toString
	  val bool = str.contains("eventid1")
	  assertTrue(bool)
	  assertFalse(str.contains("e1"))
  }
  
  @Test
  def equality() = {
    val e2 = SystemEvent("eventid1", "viewEventType",
    				"127.0.0.1 visited http://localhost/home", false)
    println(e1.toString)
    println(e2.toString)
    assertTrue(e1 == e2)
    assertTrue(e2 == e1)
    assertFalse(e1.eq(e2))
    assertTrue(e1.equals(e2))
    assertFalse(e1 == null)
    assertFalse(e1.equals(null))
    assertFalse(e1 != e2)
    assertTrue(e1.eq(e1))
    
    val e3 = SystemEvent("eventid2", "viewEventType",
    				"127.0.0.1 visited http://localhost/home", false)
    assertFalse(e2 == e3)
    assertTrue(e2 != e3)
  }

}