package com.wittybrains.busbookingsystem.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.Driver;
import com.wittybrains.busbookingsystem.model.TravelSchedule;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.wittybrains.busbookingsystem.dto.TravelScheduleDTO;
import com.wittybrains.busbookingsystem.exception.InvalidSearchDateException;
import com.wittybrains.busbookingsystem.exception.InvalidSourceOrDestinationException;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.DriverRepository;
import com.wittybrains.busbookingsystem.repository.TravelScheduleRepository;
import com.wittybrains.busbookingsystem.service.TravelScheduleService;

@RestController
@RequestMapping("/schedules")
public class TravelScheduleController {
//	@Autowired
//	private TravelScheduleRepository scheduleRepository;
	@Autowired
	private TravelScheduleService travelScheduleService;
	@Autowired
	private BusRepository busRepository;
	@PostMapping
	public ResponseEntity createSchedule(@RequestBody TravelScheduleDTO travelScheduleDTO) {
		if(travelScheduleService.createSchedule(travelScheduleDTO)) {
			return new ResponseEntity("Sucessfully created travelschedule",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity("Unable to create travelschedule",HttpStatus.BAD_REQUEST);
		}
	}

}
