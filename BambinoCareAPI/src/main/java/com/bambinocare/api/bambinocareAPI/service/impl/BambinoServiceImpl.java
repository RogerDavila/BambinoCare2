package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Bambino;
import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.repository.BambinoRepository;
import com.bambinocare.api.bambinocareAPI.service.BambinoService;

@Service
public class BambinoServiceImpl implements BambinoService {

	@Autowired
	private BambinoRepository bambinoRepository;

	@Override
	public List<Bambino> findByClient(Client client) {

		if (client == null)
			return new ArrayList<>();

		return bambinoRepository.findByClient(client);

	}

	@Override
	public Bambino createBambino(Bambino bambino) {

		if (bambino == null)
			return null;

		return bambinoRepository.save(bambino);

	}

	@Override
	public Bambino findByBambinoIdAndClientUser(Integer bambinoId, User user) {

		if (bambinoId == 0 || user == null)
			return null;

		return bambinoRepository.findByBambinoIdAndClientUser(bambinoId, user).orElse(null);
	}

	@Override
	public boolean removeBambino(Integer bambinoId) {

		if (!bambinoRepository.existsById(bambinoId))
			return false;

		bambinoRepository.delete(findByBambinoId(bambinoId));

		return true;
	}

	@Override
	public Bambino findByBambinoId(Integer bambinoId) {
		return bambinoRepository.findById(bambinoId).orElse(null);
	}

	@Override
	public List<Bambino> findByClientUser(User user) {

		if (user == null)
			return null;

		return bambinoRepository.findByClientUser(user);
	}

	@Override
	public Set<Bambino> findByBambinoIdAndUser(List<String> bambinoIds, User user) {
		Set<Bambino> bambinos = new HashSet<>();

		for (String bambinoId : bambinoIds) {
			int bambinoIdInt = Integer.parseInt(bambinoId);
			bambinos.add(findByBambinoIdAndClientUser(bambinoIdInt, user));
		}
		return bambinos;
	}

}
