package com.bambinocare.api.bambinocareAPI.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.Role;
import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.model.Validation;
import com.bambinocare.api.bambinocareAPI.repository.ClientRepository;
import com.bambinocare.api.bambinocareAPI.service.ClientService;
import com.bambinocare.api.bambinocareAPI.service.RoleService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RoleService roleService;

	@Override
	public Client createClient(Client client) {

		if (client == null || clientExists(client))
			return null;

		Role role = roleService.findByRoleDesc("Cliente");//new Role(3, "Cliente");

		if (role == null)
			return null;
		
		client.getUser().setRole(role);
		client.getUser().setEnabled(false);

		User user = client.getUser();

		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return clientRepository.save(client);
	}

	@Override
	public Client findByUser(User user) {

		if (user == null)
			return null;

		return clientRepository.findByUser(user).orElse(null);
	}

	@Override
	public Client findByUserEmail(String email) {

		if (email == "" || email == null)
			return null;

		return clientRepository.findByUserEmail(email).orElse(null);
	}

	@Override
	public Client saveClient(Client client) {
		if(client == null || !clientExists(client)) return null;
		
		return clientRepository.save(client);
	}

	@Override
	public Validation validateClientForm(Client client, User user) {

		String result = null;
		boolean requireOtherView = false;
		String otherView = null;

		String empty = "";

		if (client.getUser().getEmail() == null || client.getUser().getEmail().equals(empty)) {
			result = "Favor de verificar el email";
		} else if (client.getStreet() == null || client.getStreet().equals(empty)) {
			result = "Favor de verificar el campo Fecha";
		} else if (client.getNeighborhood() == null || client.getNeighborhood().equals(empty)) {
			result = "Favor de verificar el campo Fecha";
		} else if (client.getCity() == null || client.getCity().equals(empty)) {
			result = "Favor de verificar el campo Fecha";
		} else if (client.getState() == null || client.getState().equals(empty)) {
			result = "Favor de verificar el campo Fecha";
		} else if (client.getJob() == null || client.getJob().equals(empty)) {
			result = "Favor de verificar el campo Fecha";
		} else if (!client.getUser().getPasswordConfirm().equals(client.getUser().getPassword())) {
			result = "La contraseña y la confirmaci%C3%B3n de contraseña no co%C3%ADnciden";
		}

		return new Validation(result, requireOtherView, otherView);
	}
	
	@Override
	public boolean clientExists( Client client ) {
		
		if (client.getClientId() != null) return clientRepository.existsById(client.getClientId());
		
		if(findByUserEmail(client.getUser().getEmail()) != null) return true;
		
		return false;
	}

}
