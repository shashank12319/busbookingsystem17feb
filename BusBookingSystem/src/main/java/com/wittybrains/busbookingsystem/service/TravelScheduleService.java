package com.wittybrains.busbookingsystem.service;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittybrains.busbookingsystem.dto.TravelScheduleDTO;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.TravelSchedule;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.TravelScheduleRepository;

@Service
public class TravelScheduleService {

    @Autowired
    private TravelScheduleRepository scheduleRepository;
 @Autowired
  private BusRepository busRepository;
    public List<TravelSchedule> getAvailableSchedules(String source, String destination, LocalDate searchDate) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime searchDateTime = LocalDateTime.of(searchDate, LocalTime.MIDNIGHT);
        LocalDateTime minimumDateTime;

        if (searchDateTime.isBefore(currentDateTime)) {
            // cannot search for past schedules
            throw new IllegalArgumentException("Cannot search for schedules in the past");
        } else if (searchDateTime.toLocalDate().equals(currentDateTime.toLocalDate())) {
            // search for schedules at least 1 hour from now
            currentDateTime = currentDateTime.plusHours(1);
        }

        List<TravelSchedule> travelScheduleList = scheduleRepository
                .findBySourceAndDestinationAndEstimatedArrivalTimeAfter(source, destination, currentDateTime);
        return travelScheduleList;
    }

	public boolean createSchedule(TravelScheduleDTO travelScheduleDTO) {
		// TODO Auto-generated method stub
		Optional<Bus> optionalBus = busRepository.findById(travelScheduleDTO.getBusId());

		if (!optionalBus.isPresent()) {
			System.out.println("bus with id is not found" + travelScheduleDTO.getBusId());
		}

		TravelSchedule travelschedule = new TravelSchedule();

		travelschedule.setBus(optionalBus.get());

		travelschedule.setDestination(travelScheduleDTO.getDestination());
		travelschedule.setEstimatedArrivalTime(LocalDateTime.parse(travelScheduleDTO.getEstimatedArrivalTime()));
		travelschedule.setEstimatedDepartureTime(LocalDateTime.parse(travelScheduleDTO.getEstimatedDepartureTime()));
		travelschedule.setFareAmount(travelScheduleDTO.getFareAmount());
		travelschedule.setSource(travelScheduleDTO.getSource());
		//travelschedule.setDate(LocalDate.parse(travelScheduleDTO.getDate()));
		travelschedule = scheduleRepository.save(travelschedule);
		boolean flag=false;
		if(travelschedule.getScheduleId()!=null) {
			flag=true;
		}
		return flag;
	}
}
