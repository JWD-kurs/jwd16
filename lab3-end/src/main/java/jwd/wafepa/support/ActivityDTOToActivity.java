package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.web.dto.ActivityDTO;

@Component
public class ActivityDTOToActivity 
	implements Converter<ActivityDTO, Activity> {
	@Autowired
	private ActivityService activityService;

	@Override
	public Activity convert(ActivityDTO dto) {
		Activity activity = null;
		
		if(dto.getId()==null){
			activity = new Activity();
		}else{
			activity = activityService.findOne(dto.getId());
		}
		
		activity.setId(dto.getId());
		activity.setName(dto.getName());
		
		return activity;
	}
	
	public List<Activity> convert(List<ActivityDTO> dtos){
		List<Activity> activities = new ArrayList<>();
		
		for(ActivityDTO dto: dtos){
			activities.add(convert(dto));
		}
		
		return activities;
	}

}
