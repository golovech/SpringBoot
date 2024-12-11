package org.sist.sb06_sbb5.user;

import java.util.Optional;

import org.sist.sb06_sbb5.exception.DataNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) {
		
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 주입받아서, 생성할 필요 없음.
		user.setPassword(passwordEncoder.encode(password)); // 암호화 작업
		
		this.userRepository.save(user);
		return user; // user로 리턴.
		
	}
	
	// 작성자 얻어오기
	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
		if (siteUser.isPresent()) {
			return siteUser.get();
		} else {
		    throw new DataNotFoundException("siteuser not foundCAKE!!");
		}
	}
}
