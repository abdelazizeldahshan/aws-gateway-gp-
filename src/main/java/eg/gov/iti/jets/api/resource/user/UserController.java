package eg.gov.iti.jets.api.resource.user;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.impl.UserManagementImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserManagementImpl userManagement;
    private final Mapper mapper;

    public UserController(UserManagementImpl userManagement , Mapper mapper){
        this.userManagement = userManagement;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority(T(eg.gov.iti.jets.persistence.entity.enums.PrivilegeName).MANAGE_USERS.name())")
    public ResponseEntity<UserResponseList> getUsers(){
        System.out.println("hey");
        List<User> users = userManagement.getAllUsers();
        List<UserResponse> userResponses =  mapper.mapFromListOfUsersToListOfUserResponses(users);
        UserResponseList userResponseList = new UserResponseList();
        for(UserResponse response : userResponses){
            userResponseList.getUserResponsesList().add(response);
        }
        return ResponseEntity.ok(userResponseList);
    }


    @PutMapping
    //all users
    public ResponseEntity updateUserPassword(@RequestBody UserPutRequest userPutRequest, @AuthenticationPrincipal UserAdapter userAdapter ){
            int currentLoggedUserId = userAdapter.getId();
            userManagement.updateUserPassword(mapper.mapFromUserPutRequestToUser(currentLoggedUserId, userPutRequest));
            return new ResponseEntity("Password updated", HttpStatus.OK);
    }

    @GetMapping("edit")
    //all users
    public ResponseEntity<UserPasswordResponse> getUserPassword(@AuthenticationPrincipal UserAdapter userAdapter){
        int currentLoggedUserId = userAdapter.getId();
        User user= userManagement.getUserById(currentLoggedUserId);
        return new ResponseEntity<>( mapper.mapFromUserToUserPasswordResponse(user),HttpStatus.OK);
    }
}
