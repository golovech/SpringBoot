package org.sist.sb06_sbb7.user;

import java.util.Optional;

import org.sist.sb06_sbb7.exception.DataNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		
		return this.userRepository.save(user);
		//return user;
		
	}

	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
		if(siteUser.isPresent()) {
			return siteUser.get();//SiteUser return
		}else {
			throw new DataNotFoundException("@@@@@@@@@@@@siteUser 못 찾았어욤");
		}
		
	}
}
