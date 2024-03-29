package com.cainaoliboni.auth;

import com.cainaoliboni.auth.entity.Permission;
import com.cainaoliboni.auth.entity.User;
import com.cainaoliboni.auth.repository.PermissionRepository;
import com.cainaoliboni.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
		return args -> {
			initUsers(userRepository, permissionRepository, bCryptPasswordEncoder);
		};
	}

	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
						   BCryptPasswordEncoder passwordEncoder){
		Permission permission = null;
		Permission findPermission = permissionRepository.findByDescription("Admin");
		if(findPermission == null){
			permission = new Permission();
			permission.setDescription("Admin");
			permission = permissionRepository.save(permission);
		}else{
			permission = findPermission;
		}

		User admin = new User();
		admin.setUsername("caina");
		admin.setAccountNonExpired(true);
		admin.setAccountNonLocked(true);
		admin.setCredentialsNonExpired(true);
		admin.setEnable(true);
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.setPermissions(List.of(permission));

		User find = userRepository.findByUsername("caina");

		if(find == null){
			userRepository.save(admin);
		}
	}

}
