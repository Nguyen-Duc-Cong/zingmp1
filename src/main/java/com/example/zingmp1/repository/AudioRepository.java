package com.example.zingmp1.repository;

import com.example.zingmp1.model.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("audioRepository")
public interface AudioRepository extends JpaRepository<Audio, Long> {
	List<Audio> findAudioByUploader(String uploader);
	Audio findById(int id);
}