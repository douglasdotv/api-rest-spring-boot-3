package br.com.dv.api.repository;

import br.com.dv.api.domain.appointment.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndScheduledDateTime(Long doctorId, LocalDateTime scheduledDateTime);
    boolean existsByPatientIdAndScheduledDateTimeBetween(Long patientId, LocalDateTime start, LocalDateTime end);

    Page<Appointment> findAllByIsActiveIsTrue(Pageable pageable);
}
