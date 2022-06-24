package eg.gov.iti.jets.api.resource.staff;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffUpdateRequest {

    private String roleId;
    private List<TrackType> tracks;
}