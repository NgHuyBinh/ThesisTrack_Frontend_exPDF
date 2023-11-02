package com.luanvan.ThesisTrack_Backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThesisTrackBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	// 3 dòng phía dưới mới thêm vào
	public static void main(String[] args) {
		SpringApplication.run(ThesisTrackBackendApplication.class, args);
	}

	// @SpringBootApplication
	// @EntityScan(basePackages = "com.luanvan.ThesisTrack_Backend.registerteacher")
	// public class ThesisTrackBackendApplication {
	// 	public static void main(String[] args) {
	// 		SpringApplication.run(ThesisTrackBackendApplication.class, args);
	// 	}
	// }

}
