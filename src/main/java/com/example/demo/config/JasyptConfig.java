package com.example.demo.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

//	@Bean("jasyptStringEncryptor") 
//	public StringEncryptor stringEncryptor() { 
//		
//		String key = "111";
//		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor(); 
//		SimpleStringPBEConfig config = new SimpleStringPBEConfig(); 
//		config.setPassword(key); 
//		config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256"); 
//		config.setKeyObtentionIterations("1000"); 
//		config.setPoolSize("1"); 
//		config.setProviderName("SunJCE"); 
//		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); 
//		config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator"); 
//		config.setStringOutputType("base64"); 
//		encryptor.setConfig(config); 
//		return encryptor; 
//		}

	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() { 
//	private StringEncryptor stringEncryptor() {
	String key = "111";
	PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword(key);
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
    config.setStringOutputType("base64");
    encryptor.setConfig(config);
    return encryptor;
}

//private String encrypt(String text) {
//    StringEncryptor textEncryptor = stringEncryptor();
//    String encryptedText = textEncryptor.encrypt(text);
//    return encryptedText;
//}

//private String decrypt(String text) {
//    StringEncryptor textEncryptor = stringEncryptor();
//    String decryptedText = textEncryptor.decrypt(text);
//    return decryptedText;
//}


}
