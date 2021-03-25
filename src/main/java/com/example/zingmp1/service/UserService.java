package com.example.zingmp1.service;

import com.example.zingmp1.model.Audio;
import com.example.zingmp1.model.User;

import java.util.List;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<Audio> findAudioByUploader(String uploader);
	public void saveAudio(Audio audio);
	public Audio findAudioById(int id);
	public void deleteAudio(Audio audio);
}