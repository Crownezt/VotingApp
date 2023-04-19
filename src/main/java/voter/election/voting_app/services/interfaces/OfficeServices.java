package voter.election.voting_app.services.interfaces;

import voter.election.voting_app.data.models.Office;
import voter.election.voting_app.dtos.response.OfficeResponse;

import java.util.List;

public interface OfficeServices {
    public OfficeResponse registerOffice(Office name, int duration);
    public OfficeResponse deleteOfficeById(long id);
    public Office getOfficeById(long id);
    public List<Office> getAllOffice();

}
