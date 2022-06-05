package eg.gov.iti.jets.service.util;

import eg.gov.iti.jets.persistence.dao.SecurityGroupDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MapperFromIdToSecurityGroup {
    @Autowired
    private SecurityGroupDao securityGroupDao;
    @Autowired
    UserDao userDao;

    public List<SecurityGroup> getSecurityGroups(List<Integer> ids){
        List<SecurityGroup> securityGroups = new ArrayList<>();
        for (Integer id:ids){
            Optional<SecurityGroup> group = securityGroupDao.findById(id);
            group.ifPresent(securityGroups::add);

        }
        return securityGroups;
    }

    public User getUser( int id){
        Optional<User> byId = userDao.findById( id );
        return byId.orElse( null );
    }


}
