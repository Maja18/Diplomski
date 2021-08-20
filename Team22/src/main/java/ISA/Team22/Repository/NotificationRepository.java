package ISA.Team22.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ISA.Team22.Domain.PharmacyWorkflow.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
