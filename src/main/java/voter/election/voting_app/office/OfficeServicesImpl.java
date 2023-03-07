package voter.election.voting_app.office;

import java.util.List;

public class OfficeServicesImpl implements OfficeServices{
    private OfficeRepository officeRepository;

    @Override
    public Response registerOffice(Office name, int duration) {
        Office office = new Office();
        office.setName(name);
        office.setDuration(duration);
        officeRepository.save(office);
        return new Response(String.format("%s office created", name ));
    }

    @Override
    public Response deleteOfficeById(long id) {
        officeRepository.deleteById(id);
        return new Response(String.format("Office with ID %d successfully deleted", id));
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
