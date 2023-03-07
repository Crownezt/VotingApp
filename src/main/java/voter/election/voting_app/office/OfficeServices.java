package voter.election.voting_app.office;

import java.util.List;

public interface OfficeServices {
    public Response registerOffice(Office name, int duration);
    public Response deleteOfficeById(long id);
    public Office getOfficeById(long id);
    public List<Office> getAllOffice();

}
