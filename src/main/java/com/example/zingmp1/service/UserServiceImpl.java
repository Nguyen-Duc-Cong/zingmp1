package com.example.zingmp1.service;

import com.example.zingmp1.model.Audio;
import com.example.zingmp1.model.Role;
import com.example.zingmp1.model.User;
import com.example.zingmp1.repository.AudioRepository;
import com.example.zingmp1.repository.RoleRepository;
import com.example.zingmp1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AudioRepository audioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public List<Audio> findAudioByUploader(String uploader) {
		return audioRepository.findAudioByUploader(uploader);
	}
	
	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	@Override
	public void saveAudio(Audio audio) {
		audioRepository.save(audio);
	}
	
	@Override
	public Audio findAudioById(int id) {
		return audioRepository.findById(id);
	}
	
	@Override
	public void deleteAudio(Audio audio) {
		audioRepository.delete(audio);
	}
}