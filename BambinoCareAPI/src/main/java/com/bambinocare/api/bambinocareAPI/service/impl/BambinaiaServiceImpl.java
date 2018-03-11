package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Bambinaia;
import com.bambinocare.api.bambinocareAPI.model.Booking;
import com.bambinocare.api.bambinocareAPI.model.Role;
import com.bambinocare.api.bambinocareAPI.repository.BambinaiaRepository;
import com.bambinocare.api.bambinocareAPI.service.BambinaiaService;
import com.bambinocare.api.bambinocareAPI.service.BookingService;
import com.bambinocare.api.bambinocareAPI.service.RoleService;

@Service
public class BambinaiaServiceImpl implements BambinaiaService {

	@Autowired
	private BambinaiaRepository bambinaiaRepository;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private RoleService roleService;

	@Override
	public List<Bambinaia> findAll() {
		return bambinaiaRepository.findAll();
	}

	@Override
	public Bambinaia findByBambinaiaId(Integer bambinaiaId) {
		return bambinaiaRepository.findById(bambinaiaId).orElse(null);
	}

	@Override
	public Bambinaia createBambinaia(Bambinaia bambinaia) {

		if (bambinaia == null || bambinaiaExists(bambinaia))
			return null;

		Role role = roleService.findByRoleDesc("Bambinaia");

		if (role == null)
			return null;

		bambinaia.getUser().setRole(role);

		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		bambinaia.getUser().setPassword(passwordEncoder.encode(bambinaia.getUser().getPassword()));

		Bambinaia newBambinaia = bambinaiaRepository.save(bambinaia);

		return newBambinaia;
	}

	@Override
	public Bambinaia editBambinaia(Bambinaia bambinaia) {
		
		if( bambinaia == null || !bambinaiaExists(bambinaia)) return null;
		
		Bambinaia newBambinaia = bambinaiaRepository.save(bambinaia);
		return newBambinaia;
	}

	@Override
	public boolean removeBambinaia(Integer bambinaiaId) {
		Bambinaia bambinaia =  findByBambinaiaId(bambinaiaId);
		
		if(bambinaia == null) return false;
		
		bambinaiaRepository.delete(bambinaia);
		
		return true;
	}

	@Override
	public Bambinaia findByUserEmail(String email) {
		return bambinaiaRepository.findByUserEmail(email).orElse(null);
	}

	@Override
	public boolean isBambinaiaAvailable(Date startDate, Date finalDate, Bambinaia bambinaia) {

		bambinaia = findByBambinaiaId(bambinaia.getBambinaiaId());
		List<Booking> bookings = bookingService.findByBambinaia(bambinaia);

		if (bookings == null || bookings.size() <= 0) {
			return true;
		}

		for (Booking booking : bookings) {
			if (((startDate.after(booking.getStartDateTime()) || startDate.equals(booking.getStartDateTime()))
					&& (startDate.before(booking.getFinishDateTime())
							|| startDate.equals((booking.getFinishDateTime())))
					|| (finalDate.after(booking.getStartDateTime()) || finalDate.equals(booking.getStartDateTime()))
							&& (finalDate.before(booking.getFinishDateTime())
									|| finalDate.equals(booking.getFinishDateTime())))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean bambinaiaExists(Bambinaia bambinaia) {
		if (bambinaia.getBambinaiaId() != null)
			return bambinaiaRepository.existsById(bambinaia.getBambinaiaId());

		if (findByUserEmail(bambinaia.getUser().getEmail()) != null)
			return true;

		return false;
	}
	
}
