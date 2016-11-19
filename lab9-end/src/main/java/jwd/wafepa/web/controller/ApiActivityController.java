package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.support.ActivityDTOToActivity;
import jwd.wafepa.support.ActivityToActivityDTO;
import jwd.wafepa.web.dto.ActivityDTO;

@RestController
@RequestMapping(value = "/api/activities")
public class ApiActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityToActivityDTO toDTO;

	@Autowired
	private ActivityDTOToActivity toActivity;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<Activity>> getActivities(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page) {

		List<Activity> activities;

		if (name != null) {
			activities = activityService.findByName(name, page).getContent();
		} else {
			activities = activityService.findAll(page).getContent();
		}

		return new ResponseEntity<>(activities, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ActivityDTO> getActivity(@PathVariable Long id) {
		Activity activity = activityService.findOne(id);
		if (activity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(activity), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<ActivityDTO> delete(@PathVariable Long id) {
		Activity deleted = activityService.delete(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Activity> add(@RequestBody Activity newActivity) {

		Activity savedActivity = activityService.save(newActivity);

		return new ResponseEntity<>(savedActivity, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<Activity> edit(@RequestBody Activity activity,
			@PathVariable Long id) {

		if (id != activity.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Activity persisted = activityService.save(activity);

		return new ResponseEntity<>(persisted, HttpStatus.OK);
	}

}
