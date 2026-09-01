package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.PodCastDetails;

public interface PodCastService {

	PodCastDetails addPodcast(MultipartFile file,PodCastDetails request);

	List<PodCastDetails> getAllPodCast();

	PodCastDetails activeDeactivePodCast(Long id, int status);

	PodCastDetails getPodCastById(Long id);

	PodCastDetails updatePodcastRecord(MultipartFile file, PodCastDetails request);
}
