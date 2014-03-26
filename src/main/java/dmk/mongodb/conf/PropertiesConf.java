package dmk.mongodb.conf;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertiesConf {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	final static Resource[] locations = { 
			new ClassPathResource("dmk-mongodb.properties") 
			};

	@Value("${dmk.mongodb.host}")
	protected String mongodbHost;

	@Value("${dmk.mongodb.dbname}")
	protected String mongoDbName;

	@Bean
	public String mongodbHost(){
		return this.mongodbHost;
	}
	
	@Bean
	public String mongoDbName(){
		return this.mongoDbName;
	}
	
	@Bean
	public static EnvironmentStringPBEConfig environmentStringPBEConfig() {
		EnvironmentStringPBEConfig tmp = new EnvironmentStringPBEConfig();
		tmp.setAlgorithm("PBEWithMD5AndDES");
		String pass = "dmk-salt";
		tmp.setPasswordCharArray(pass.toCharArray());
		pass = null;
		return tmp;
	}

	@Bean
	public static StandardPBEStringEncryptor configurationEncryptor() {
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		standardPBEStringEncryptor.setConfig(environmentStringPBEConfig());
		return standardPBEStringEncryptor;
	}

	@Bean
	public static EncryptablePropertyPlaceholderConfigurer encryptablePropertyPlaceholderConfigurer() {
		EncryptablePropertyPlaceholderConfigurer configurer = new EncryptablePropertyPlaceholderConfigurer(
				configurationEncryptor());
		configurer.setLocations(locations);
		return configurer;
	}

}
