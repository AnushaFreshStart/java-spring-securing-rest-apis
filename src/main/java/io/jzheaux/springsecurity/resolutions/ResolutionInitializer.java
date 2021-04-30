package io.jzheaux.springsecurity.resolutions;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResolutionInitializer implements SmartInitializingSingleton {
	private final ResolutionRepository resolutions;
	private final UserRepository users;

	public ResolutionInitializer(ResolutionRepository resolutions, UserRepository users) {
		this.resolutions = resolutions;
		this.users = users;
	}

	@Override
	public void afterSingletonsInstantiated() {
		this.resolutions.save(new Resolution("Read War and Peace", "user"));
		this.resolutions.save(new Resolution("Free Solo the Eiffel Tower", "user"));
		this.resolutions.save(new Resolution("Hang Christmas Lights", "user"));


		User user = new User("user",
				"{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		user.grantAuthority("resolution:read");
		user.grantAuthority("resolution:write");
		this.users.save(user);

		User hasread = new User();
		hasread.setId(UUID.randomUUID());
		hasread.setUsername("hasread");
		hasread.setPasword("{bcrypt}$2a$10$Pu.02bigwQm1drtqA2dysO9G/DtzNUyZ5z4guYJOtdlHl4ZfFQh.C");
		hasread.grantAuthority("resolution:read");
		this.users.save(hasread);

		User haswrite = new User();
		haswrite.setId(UUID.randomUUID());
		haswrite.setUsername("haswrite");
		haswrite.setPasword("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
		haswrite.grantAuthority("resolution:write");
		this.users.save(haswrite);

		User admin = new User("admin", "{bcrypt}$2a$10$acx7/5qSkgqcd8V8sXCNdOXX4Ol3yDOBxC.HjoN7M6L0r0tRLp7.6");
		user.setId(UUID.randomUUID());
		admin.grantAuthority("ROLE_ADMIN");
		admin.grantAuthority("resolution:read");
		admin.grantAuthority("resolution:write");
		this.users.save(admin);
	}

}
