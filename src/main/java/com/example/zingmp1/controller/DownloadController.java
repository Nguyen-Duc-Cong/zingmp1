package com.example.zingmp1.controller;

import com.example.zingmp1.model.Audio;
import com.example.zingmp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@RestController
@RequestMapping("/download")
public class DownloadController {
	
	private static String UPLOAD_FOLDER = "Uploads/";
	
	@Autowired
	private UserService userService;

	@RequestMapping("/{fileName:.+}")
	public void downloadAudioResource(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
		File file = new File(UPLOAD_FOLDER + fileName);
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			int id = Integer.valueOf(removeExtension(file.getName()));
			Audio audio = userService.findAudioById(id);9
			String name = audio.getName();
			String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
			String realName = name + "." + extension;
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + realName + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
	}
	
	public static String removeExtension(String fileName) {
        if (fileName.indexOf(".") > 0) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        } else {
            return fileName;
        }
    }
}