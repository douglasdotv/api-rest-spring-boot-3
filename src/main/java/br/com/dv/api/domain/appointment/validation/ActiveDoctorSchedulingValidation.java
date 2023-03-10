package br.com.dv.api.domain.appointment.validation;

import br.com.dv.api.domain.appointment.AppointmentSchedulingDto;
import br.com.dv.api.domain.appointment.exception.AppointmentValidationException;
import br.com.dv.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorSchedulingValidation implements AppointmentSchedulingValidation {

    private final DoctorRepository doctorRepository;

    @Autowired
    public ActiveDoctorSchedulingValidation(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void validate(AppointmentSchedulingDto dto) {
        /*
        Appointments can only be scheduled with active doctors.
         */

        if (dto.doctorId() == null) {
            return;
        }

        var doctorIsActive = doctorRepository.findIsActiveById(dto.doctorId());
        if (doctorIsActive != 1) {
            throw new AppointmentValidationException("Appointment cannot be scheduled: doctor is not active");
        }

    }

}
