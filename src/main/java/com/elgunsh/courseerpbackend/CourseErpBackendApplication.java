package com.elgunsh.courseerpbackend;

import com.elgunsh.courseerpbackend.model.enums.user.UserStatus;
import com.elgunsh.courseerpbackend.model.mybatis.user.User;
import com.elgunsh.courseerpbackend.repository.UserRepository;
import com.elgunsh.courseerpbackend.service.security.AccessTokenManager;
import com.elgunsh.courseerpbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@RequiredArgsConstructor
@SpringBootApplication
public class CourseErpBackendApplication implements CommandLineRunner {

	private final AccessTokenManager accessTokenManager;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CourseErpBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		User user = User.builder()
//				.id(1L)
//				.email("king@north")
//				.build();
//
//		final String token = accessTokenManager.generate(user);
//
//		System.out.println(token);
//		System.out.println(accessTokenManager.read(token).get("email", String.class));

//		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
//		generator.initialize(2048);
//		KeyPair keyPair = generator.generateKeyPair();
//		PublicKey aPublic = keyPair.getPublic();
//		PrivateKey aPrivate = keyPair.getPrivate();
//
//		String encodedPublicKey = Base64.getEncoder().encodeToString(aPublic.getEncoded());
//		String encodedPrivateKey = Base64.getEncoder().encodeToString(aPrivate.getEncoded());
//
//		System.out.println(convertToPublicKey(encodedPublicKey));
//		System.out.println();
//		System.out.println(convertToPrivateKey(encodedPrivateKey));


//		User user = User.builder()
//				.name("John")
//				.surname("Targaryen")
//				.status(UserStatus.ACTIVE)
//				.roleId(2L)
//				.email("king@north.com")
//				.password(passwordEncoder.encode("1234"))
//				.phoneNumber("551234566")
//				.build();
//		userService.insert(user);

		System.out.println(userService.getByEmail("king@north.com"));
	}


	private static String convertToPrivateKey(String key) {
		StringBuilder result = new StringBuilder();
		result.append("-----BEGIN PRIVATE KEY-----\n");
		result.append(key);
		result.append("\n-----END PRIVATE KEY-----");
		return result.toString();
	}

	private static String convertToPublicKey(String key) {
		StringBuilder result = new StringBuilder();
		result.append("-----BEGIN PUBLIC KEY-----\n");
		result.append(key);
		result.append("\n-----END PUBLIC KEY-----");
		return result.toString();
	}

}
