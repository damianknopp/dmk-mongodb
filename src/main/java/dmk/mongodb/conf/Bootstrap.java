package dmk.mongodb.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("dmk.mongodb")
@Import({
	MongodbConf.class,
	PropertiesConf.class
})
public class Bootstrap {

	
}
