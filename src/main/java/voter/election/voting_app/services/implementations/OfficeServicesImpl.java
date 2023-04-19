package voter.election.voting_app.services.implementations;

import voter.election.voting_app.data.models.Office;
import voter.election.voting_app.data.repositories.OfficeRepository;
import voter.election.voting_app.exception.OfficeException;
import voter.election.voting_app.dtos.response.OfficeResponse;
import voter.election.voting_app.services.interfaces.OfficeServices;

import java.util.List;

public class OfficeServicesImpl implements OfficeServices {
    private OfficeRepository officeRepository;

    @Override
    public OfficeResponse registerOffice(Office name, int duration) {
        Office office = new Office();
        office.setName(name);
        office.setDuration(duration);
        officeRepository.save(office);
        return new OfficeResponse(String.format("%s office created", name ));
    }

    @Override
    public OfficeResponse deleteOfficeById(long id) {
        officeRepository.deleteById(id);
        return new OfficeResponse(String.format("Office with ID %d successfully deleted", id));
    }

    @Override
    public Office getOfficeById(long id) {
        return officeRepository.findById(id).orElseThrow(() ->
                new OfficeException("Office not found"));
    }

    @Override
    public List<Office> getAllOffice() {
        return officeRepository.findAll();
    }
}
