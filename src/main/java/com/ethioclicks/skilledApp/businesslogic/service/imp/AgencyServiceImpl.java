package com.ethioclicks.skilledApp.businesslogic.service.imp;

import com.ethioclicks.skilledApp.businesslogic.entity.Agency;
import com.ethioclicks.skilledApp.businesslogic.exception.BadRequestException;
import com.ethioclicks.skilledApp.businesslogic.model.AgencyModel;
import com.ethioclicks.skilledApp.businesslogic.repo.AgencyRepo;
import com.ethioclicks.skilledApp.businesslogic.service.AgencyService;
import com.ethioclicks.skilledApp.businesslogic.util.Mapper;
import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.service.UserRegistrationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgencyServiceImpl implements AgencyService {

    private final UserRegistrationService userRegistrationService;
    private final AgencyRepo agencyRepo;

    public AgencyServiceImpl(UserRegistrationService userRegistrationService, AgencyRepo agencyRepo) {
        this.userRegistrationService = userRegistrationService;
        this.agencyRepo = agencyRepo;
    }


    @Override
    public AgencyModel saveAgency(AgencyModel agencyModel, String pid) {

        if (agencyModel == null) {
            throw new BadRequestException("Agency model is required");
        }
        User user = userRegistrationService.getUser(pid);
        if (user == null) {
            throw new BadRequestException("User not found");
        }
        if (agencyModel.getId() != null && !agencyModel.getId().toString().isEmpty()) {
            Agency existingAgency = agencyRepo.findById(agencyModel.getId()).orElseThrow(() -> new BadRequestException("Agency id not found"));

            if (isAgencyOwner(existingAgency.getAgencyPublicId(), pid)) {
                existingAgency.setDescription(agencyModel.getDescription());
                existingAgency.setCity(agencyModel.getCity());
                existingAgency.setStreet(agencyModel.getStreet());
                existingAgency.setEmail(agencyModel.getEmail());
                existingAgency.setPhone(agencyModel.getPhone());
                existingAgency.setPostDate(agencyModel.getPostDate());
                return Mapper.toAgencyModel(agencyRepo.save(existingAgency));
            } else {
                throw new BadRequestException("You do not have access to post in this agency");
            }
        } else {
            Agency agency = Mapper.toAgencyEntity(agencyModel);
            agency.setAgencyPublicId(UUID.randomUUID().toString());
            agency.setOwner(user);

            agency.setPostDate(LocalDateTime.now());
            return Mapper.toAgencyModel(agencyRepo.save(agency));
        }
    }

    private boolean isAgencyOwner(String agencyPublicId, String pid) {
        User user = userRegistrationService.getUser(pid);
        Optional<Agency> agency = agencyRepo.findByAgencyPublicId(agencyPublicId);
        return user != null && agency.isPresent() && user.getUserPublicId().equals(agency.get().getOwner().getUserPublicId());
    }
}



