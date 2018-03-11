package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Booking;
import com.bambinocare.api.bambinocareAPI.model.BookingType;
import com.bambinocare.api.bambinocareAPI.model.Cost;
import com.bambinocare.api.bambinocareAPI.repository.CostRepository;
import com.bambinocare.api.bambinocareAPI.service.CostService;

@Service
public class CostServiceImpl implements CostService {

	@Autowired
	private CostRepository costRepository;

	@Override
	public List<Cost> findByBambinoQuantityAndBookingTypeOrderByHourQuantity(Integer bambinoQuantity,
			BookingType bookingType) {

		if (bambinoQuantity <= 0 || bookingType == null)
			return null;

		return costRepository.findByBambinoQuantityAndBookingTypeOrderByHourQuantity(bambinoQuantity, bookingType);
	}

	@Override
	public List<Cost> findAllByOrderByHourQuantity() {
		return costRepository.findAllByOrderByHourQuantity();
	}

	@Override
	public Double calculateTotalCost(Double duration, Integer bambinoQuantity, BookingType bookingType) {
		Double totalCost = 0.0;

		if (duration == null || duration == 0)
			return totalCost;

		if (bambinoQuantity > 3) {
			bambinoQuantity -= 3;
			totalCost += calculateTotalCost(duration, bambinoQuantity, bookingType);
			bambinoQuantity = 3;
		}

		List<Cost> costs = findByBambinoQuantityAndBookingTypeOrderByHourQuantity(bambinoQuantity, bookingType);

		for (int i = 0; i < costs.size(); i++) {
			if (duration > costs.get(i).getHourQuantity()) {
				if (i < costs.size() - 1)
					continue;
				else {
					totalCost += costs.get(i).getCost() * duration;
					break;
				}
			} else {
				if (duration.equals(costs.get(i).getHourQuantity())) {
					totalCost += costs.get(i).getCost() * duration;
				} else {
					totalCost += costs.get(i - 1).getCost() * duration;
				}
				break;
			}
		}

		return totalCost;
	}

	@Override
	public Double calculateTotalCostByBooking(Booking booking) {
		Double totalCost = 0.0;

		if (booking.getBookingType().getBookingTypeId() == 1 || booking.getBookingType().getBookingTypeId() == 2
				|| booking.getBookingType().getBookingTypeId() == 4) {
			if (booking.getBookingType().getBookingTypeId() == 2) {
				totalCost = calculateTotalCost(booking.getDuration(), 1, booking.getBookingType());
			} else {
				totalCost = calculateTotalCost(booking.getDuration(), booking.getBambino().size(),
						booking.getBookingType());
			}
		} else if (booking.getBookingType().getBookingTypeId() == 3) {
			totalCost = calculateTotalCostBambinoEvents(booking.getDuration(), booking.getEvent().getBambinosQty(),
					booking.getBookingType());
		}

		return totalCost;
	}

	@Override
	public Double calculateTotalCostBambinoEvents(Double duration, Integer bambinoQuantity, BookingType bookingType) {
		Double totalCost = 0.0;

		if (bambinoQuantity == null || bambinoQuantity <= 0.0 || duration == null || duration <= 0.0)
			return totalCost;

		List<Cost> costs = findByBookingTypeOrderByHourQuantity(bookingType);

		Cost costsCurrent = new Cost();

		for (int i = 0; i < costs.size(); i++) {

			if (i == (costs.size() - 1)) {
				costsCurrent = costs.get(i);
				break;
			}

			if (bambinoQuantity < costs.get(i + 1).getBambinoQuantity()) {
				costsCurrent = costs.get(i);
				break;
			}
		}

		if (duration > costsCurrent.getHourQuantity()) {
			Double normalCostDuration = costsCurrent.getHourQuantity();
			Double extraCostDuration = duration - normalCostDuration;

			Double extraCost = extraCostDuration * costsCurrent.getCostExtraHour();
			Double normalCost = normalCostDuration * costsCurrent.getCost();

			totalCost = extraCost + normalCost;

		} else {
			totalCost = costsCurrent.getCost() * duration;
		}

		return totalCost;
	}

	@Override
	public List<Cost> findByBookingTypeOrderByHourQuantity(BookingType bookingType) {
		if(bookingType == null) return null;
		
		return costRepository.findByBookingTypeOrderByHourQuantity(bookingType);
	}
}
