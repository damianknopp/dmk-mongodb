package dmk.mongodb.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("dmk.mongodb")
public class MongodbConf extends AbstractMongoConfiguration{

	@Autowired
	protected String mongodbHost;
	@Autowired
	protected String mongoDbName;
	
	@Override
	protected String getDatabaseName() {
		return this.mongoDbName;
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient(this.mongodbHost);
	}
	
	@Override
	public UserCredentials getUserCredentials(){
		return new UserCredentials("mongoUser", "mongoPass");
	}
	
}