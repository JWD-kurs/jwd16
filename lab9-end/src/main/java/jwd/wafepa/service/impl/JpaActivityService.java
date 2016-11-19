package jwd.wafepa.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.User;
import jwd.wafepa.repository.ActivityRepository;
import jwd.wafepa.repository.UserRepository;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.UserService;

@Service
@Transactional
public class JpaActivityService 
	implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Activity findOne(Long id) {
		return activityRepository.findOne(id);
	}

	@Override
	public Page<Activity> findAll(int page) {
		return activityRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Activity save(Activity activity) {
		for (User user : activity.getUsers()) {
			if (user.getId()==null) {
				userRepository.save(user);
			}
		}
		return activityRepository.save(activity);
	}

	@Override
	public List<Activity> save(List<Activity> activities) {
		return (List<Activity>)activityRepository.save(activities);
	}

	@Override
	public Activity delete(Long id) {
		Activity activity = activityRepository.findOne(id);
		if(activity == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant activity");
		}
		activityRepository.delete(activity);
		return activity;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public Page<Activity> findByName(String name, int page) {
		return activityRepository.findByNameLike("%" + name + "%", new PageRequest(page, 10));
	}
	
	//@PostConstruct
//	public void БилоШта(){
//		save(new Activity("Swimming"));
//		save(new Activity("Running"));
//	}

}
