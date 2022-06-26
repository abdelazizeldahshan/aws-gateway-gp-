package eg.gov.iti.jets.api.resource.ami;

import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.service.management.AmiAws;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/ami")
public class AmiController {
    private final
    AmiMapper amiMapper;
    private final
    AmiAws amiAws;

    public AmiController(  AmiAws amiAws, AmiMapper amiMapper ) {
        this.amiAws = amiAws;
        this.amiMapper = amiMapper;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_TEMPLATES.name())")
    public ResponseEntity<?> getAmi(@Valid @RequestBody AmiRequest amiRequest){
        Ami ami = amiAws.describeAmi( amiRequest.getAmiId() );
        AmiResponse amiResponse = amiMapper.mapFromAmiToAmiResponse( ami );
        return new ResponseEntity<>(amiResponse, HttpStatus.OK);
    }

}
