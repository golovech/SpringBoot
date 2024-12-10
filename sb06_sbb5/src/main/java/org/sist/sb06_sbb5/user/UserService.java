package org.sist.sb06_sbb5.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	public SiteUser create(String username, String email, String password) {
		
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password)); // 암호화 작업
		
		this.userRepository.save(user);
		return user; // user로 리턴.
		
	}
	
}
