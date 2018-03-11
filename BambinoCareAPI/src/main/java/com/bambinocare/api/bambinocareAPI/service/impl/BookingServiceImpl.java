package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Bambinaia;
import com.bambinocare.api.bambinocareAPI.model.Booking;
import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.EmergencyContact;
import com.bambinocare.api.bambinocareAPI.model.Parameter;
import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.model.Validation;
import com.bambinocare.api.bambinocareAPI.repository.BookingRepository;
import com.bambinocare.api.bambinocareAPI.service.BambinoService;
import com.bambinocare.api.bambinocareAPI.service.BookingService;
import com.bambinocare.api.bambinocareAPI.service.ClientService;
import com.bambinocare.api.bambinocareAPI.service.EmergencyContactService;
import com.bambinocare.api.bambinocareAPI.service.ParameterService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private BambinoService bambinoService;
	
	@Autowired
	private EmergencyContactService emergencyContactService;

	@Autowired
	private ParameterService parameterService;

	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking createBooking(Booking booking) {

		if (booking == null)
			return null;

		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> findByClientUser(User user) {
		if (user == null)
			return null;

		return bookingRepository.findByClientUser(user);
	}

	@Override
	public Booking findByBookingId(Integer bookingId) {
		return bookingRepository.findById(bookingId).orElse(null);
	}

	@Override
	public boolean bookingExist(Booking booking) {
		
		if(booking.getBookingId() == null) {
			return false;
		}
		
		if (bookingRepository.existsById(booking.getBookingId())) {
			return true;
		}
		return false;
	}

	@Override
	public Booking findByBookingIdAndClientUser(Integer bookingId, User user) {

		if (user == null)
			return null;

		return bookingRepository.findByBookingIdAndClientUser(bookingId, user).orElse(null);
	}

	@Override
	public Booking findByBookingIdAndClientUserAndBookingStatusBookingStatusDescNotIn(Integer bookingId, User user,
			String bookingStatusDesc) {

		if (user == null || bookingStatusDesc == null || bookingStatusDesc.equals(""))
			return null;

		return bookingRepository
				.findByBookingIdAndClientUserAndBookingStatusBookingStatusDescNotIn(bookingId, user, bookingStatusDesc)
				.orElse(null);
	}

	@Override
	public Booking findByBookingIdAndBookingStatusBookingStatusDescNotIn(Integer bookingId,
			String... bookingStatusDesc) {

		if (bookingStatusDesc == null || bookingStatusDesc.equals(""))
			return null;

		return bookingRepository.findByBookingIdAndBookingStatusBookingStatusDescNotIn(bookingId, bookingStatusDesc)
				.orElse(null);
	}

	@Override
	public List<Booking> findByBambinaiaAndBookingStatusBookingStatusDesc(Bambinaia bambinaia,
			String bookingStatusDesc) {
		if (bambinaia == null || bookingStatusDesc == null || bookingStatusDesc.equals(""))
			return null;

		return bookingRepository.findByBambinaiaAndBookingStatusBookingStatusDesc(bambinaia, bookingStatusDesc);
	}

	@Override
	public Booking findByBookingIdAndBookingStatusBookingStatusDescAndBambinaia(Integer bookingId,
			String bookingStatusDesc, Bambinaia bambinaia) {

		if (bambinaia == null || bookingStatusDesc == null || bookingStatusDesc.equals(""))
			return null;

		return bookingRepository
				.findByBookingIdAndBookingStatusBookingStatusDescAndBambinaia(bookingId, bookingStatusDesc, bambinaia)
				.orElse(null);
	}

	@Override
	public Booking findByBookingIdAndBookingStatusBookingStatusDesc(Integer bookingId, String bookingStatusDesc) {

		if (bookingStatusDesc == null || bookingStatusDesc.equals(""))
			return null;

		return bookingRepository.findByBookingIdAndBookingStatusBookingStatusDesc(bookingId, bookingStatusDesc)
				.orElse(null);
	}

	@Override
	public boolean delete(Booking booking) {
		if (booking == null)
			return false;

		if (!bookingExist(booking))
			return false;

		bookingRepository.delete(booking);

		return true;
	}

	@Override
	public String getFinalHour(String initialTime, Double duration) {
		// Calcular horario
		String[] initialHourAux = initialTime.split(":");

		Integer hoursToAdd = duration.intValue();
		Integer minutesToAdd = (int) ((duration - hoursToAdd) * 60);

		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 12, 12, Integer.parseInt(initialHourAux[0]), Integer.parseInt(initialHourAux[1]));
		calendar.add(Calendar.HOUR, hoursToAdd);
		calendar.add(Calendar.MINUTE, minutesToAdd);

		String finalHour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String finalMinute = String.valueOf(calendar.get(Calendar.MINUTE));

		if (finalMinute.equals("0")) {
			finalMinute = "00";
		}

		String finalTime = finalHour + ":" + finalMinute;
		return finalTime;
	}

	@Override
	public Date getDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);

		return calendar.getTime();
	}

	@Override
	public boolean isValideDate(Date date, String hour) {
		boolean isValideDate = false;

		String[] initialHourAux = hour.split(":");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(date, 1));
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(initialHourAux[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(initialHourAux[1]));

		Calendar newCalendar = Calendar.getInstance();
		newCalendar.add(Calendar.HOUR_OF_DAY, 24);

		if (calendar.after(newCalendar)) {
			isValideDate = true;
		}

		return isValideDate;
	}

	@Override
	public boolean isValideHour(String hour) {
		boolean isValideHour = false;

		if (hour == null || hour.equals("")) {
			return isValideHour;
		}

		Parameter parameterOpeningHour = parameterService.findByParameterKey("Hora Apertura");
		Parameter parameterClosingHour = parameterService.findByParameterKey("Hora Cierre");

		if (parameterOpeningHour.getParameterValue().equals("")) {
			parameterOpeningHour.setParameterValue("00:00");
		}

		if (parameterClosingHour.getParameterValue().equals("")) {
			parameterClosingHour.setParameterValue("24:00");
		}

		String serviceHour = hour.split(":")[0];
		String openingHour = parameterOpeningHour.getParameterValue().split(":")[0];
		String closingHour = parameterClosingHour.getParameterValue().split(":")[0];

		if (Integer.parseInt(serviceHour) >= Integer.parseInt(openingHour)
				&& Integer.parseInt(serviceHour) <= Integer.parseInt(closingHour)) {
			isValideHour = true;
		}

		return isValideHour;
	}

	@Override
	public Validation validateBookingForm(Booking booking, org.springframework.security.core.userdetails.User user) {
		String result = null;
		boolean requireOtherView = false;
		String otherView = null;

		if (booking.getDuration() == null || booking.getDuration() == 0) {
			result = "Favor de verificar el campo Duraci%C3%B3n";
			return new Validation(result, requireOtherView, otherView);
		}
		if (booking.getDate() == null) {
			result = "Favor de verificar el campo Fecha";
			return new Validation(result, requireOtherView, otherView);
		}
		if (booking.getHour() == null || booking.getHour().equals("")) {
			result = "Favor de verificar el campo Hora";
			return new Validation(result, requireOtherView, otherView);
		} else if (!isValideDate(booking.getDate(), booking.getHour())) {
			result = "La reservaci%C3%B3n debe realizarse al menos 24 horas antes de la fecha solictada, le sugerimos revisar el servicio Bambino ASAP";
			return new Validation(result, requireOtherView, otherView);
		} else if (!isValideHour(booking.getHour())) {
			String serviceHour = parameterService.findByParameterKey("Hora Apertura").getParameterValue();
			result = "Por el momento el horario para el servicio es a partir de las " + serviceHour + " hrs";
			return new Validation(result, requireOtherView, otherView);
		}
		if (booking.getPaymentType() == null) {
			result = "Favor de verificar la forma de pago";
			return new Validation(result, requireOtherView, otherView);
		}

		Client client = clientService.findByUserEmail(user.getUsername());
		booking.setClient(client);
		if (bambinoService.findByClient(client).isEmpty() && booking.getBookingType().getBookingTypeId() != 3) {
			result = "Favor de dar de alta a sus bambinos";
			requireOtherView = true;
			otherView = "redirect:/users/createbambinoform";
			return new Validation(result, requireOtherView, otherView);
		}

		List<EmergencyContact> emergencyContacts = emergencyContactService.findByClient(client);
		if ((emergencyContacts.isEmpty() || emergencyContacts.size() < 2)
				&& booking.getBookingType().getBookingTypeId() != 3) {
			result = "Favor de dar de alta al menos 2 contactos de emergencia";
			requireOtherView = true;
			otherView = "redirect:/users/createcontactform";
			return new Validation(result, requireOtherView, otherView);
		}

		// Validaciones para BambinoTutory
		if (booking.getBookingType().getBookingTypeId() == 2) {
			if (booking.getTutory() == null) {
				result = "Ocurri%C3%B3 un error al intentar generar el servicio seleccionado. Por favor intente de nuevo";
				return new Validation(result, requireOtherView, otherView);
			}
			if (booking.getTutory().getCourse() == null
					|| booking.getTutory().getCourse().trim().equalsIgnoreCase("")) {
				result = "Favor de especificar una materia";
				return new Validation(result, requireOtherView, otherView);
			}
			if (booking.getTutory().getTopic() == null || booking.getTutory().getTopic().trim().equalsIgnoreCase("")) {
				result = "Favor de especificar un tema";
				return new Validation(result, requireOtherView, otherView);
			}
		}

		// Validaciones para Bambino Events
		if (booking.getBookingType().getBookingTypeId() == 3) {

			if (booking.getEvent() == null) {
				result = "Ocurri%C3%B3 un error al intentar generar el servicio seleccionado. Por favor intente de nuevo";
				return new Validation(result, requireOtherView, otherView);
			}

			if (booking.getEvent().getEventType() == null) {
				result = "Favor de especificar un tipo de Evento";
				return new Validation(result, requireOtherView, otherView);
			}
			if (booking.getEvent().getBambinosQty() == null || booking.getEvent().getBambinosQty() <= 0) {
				result = "Favor de especificar el número de Bambinos";
				return new Validation(result, requireOtherView, otherView);
			}
			if (booking.getEvent().getAge() == null || booking.getEvent().getAge().trim().equalsIgnoreCase("")) {
				result = "Favor de especificar un rango de edades de los Bambinos";
				return new Validation(result, requireOtherView, otherView);
			}
			if (booking.getEvent().getStreet() == null || booking.getEvent().getStreet().trim().equalsIgnoreCase("")) {
				result = "Favor de especificar la calle y número del evento";
				return new Validation(result, requireOtherView, otherView);
			}
			if (booking.getEvent().getNeighborhood() == null
					|| booking.getEvent().getNeighborhood().trim().equalsIgnoreCase("")) {
				result = "Favor de especificar la colonia del evento";
				return new Validation(result, requireOtherView, otherView);
			}
			if (booking.getEvent().getCity() == null || booking.getEvent().getCity().trim().equalsIgnoreCase("")) {
				result = "Favor de especificar el municipio del evento";
				return new Validation(result, requireOtherView, otherView);
			}
			if (booking.getEvent().getState() == null || booking.getEvent().getState().trim().equalsIgnoreCase("")) {
				result = "Favor de especificar el estado del evento";
				return new Validation(result, requireOtherView, otherView);
			}
		}

		return new Validation(result, requireOtherView, otherView);
	}

	@Override
	public Date getBookingDateTime(Date bookingDate, String hour, Double duration, boolean isFinalDate) {
		Calendar date = Calendar.getInstance();

		Calendar originDate = Calendar.getInstance();
		originDate.setTime(bookingDate);
		String[] originTimeArr = hour.split(":");

		date.set(originDate.get(Calendar.YEAR), originDate.get(Calendar.MONTH), originDate.get(Calendar.DAY_OF_MONTH),
				Integer.parseInt(originTimeArr[0]), Integer.parseInt(originTimeArr[1]), 0);

		if (isFinalDate) {
			Integer hours = duration.intValue();
			Double minutesAux = (duration - hours) * 60;
			Integer minutes = minutesAux.intValue();
			date.add(Calendar.HOUR_OF_DAY, hours);
			date.add(Calendar.MINUTE, minutes);
			date.set(Calendar.MILLISECOND, 0);
		}

		return date.getTime();
	}

	@Override
	public List<Booking> findByBambinaia(Bambinaia bambinaia) {

		if(bambinaia == null) return null;
		
		return bookingRepository.findByBambinaia(bambinaia);
	}

	@Override
	public Booking editBooking(Booking booking) {
		
		if(booking == null || !bookingExist(booking)) {
			return null;
		}
		
		return bookingRepository.save(booking);
	}

}
