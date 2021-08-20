package ISA.Team22.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ISA.Team22.Domain.PharmacyWorkflow.Notification;
import ISA.Team22.Repository.NotificationRepository;
import ISA.Team22.Service.IService.INotificationService;

@Service
public class NotificationService implements INotificationService {

	private final NotificationRepository notificationRepository;

	@Autowired
	public NotificationService(NotificationRepository notificationRepository) {
		super();
		this.notificationRepository = notificationRepository;
	}

	@Override
	public void saveNotification(Notification notification) {
		notificationRepository.save(notification);
	}
	
	
}
