package dtos;

import entities.Role;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDTO {

    private String userName;
    private String userPass;
    private List<String> roles;

    public UserDTO(User user){
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.roles = getRoles(user.getRoleList());
    }
    public User toUser () {
        return new User(this.userName, this.userPass);
    }

    public static List<UserDTO> getUserDTOs(List<User> users) {
        List<UserDTO> userDTOList = new ArrayList<>();
        users.forEach(user -> {
            userDTOList.add(new UserDTO(user));
        });
        return userDTOList;
    }
    public List<String> getRoles(List<Role> roles){
        List<String> stringRoles = new ArrayList<>();
        for (Role role : roles)
        {
            stringRoles.add(role.getRoleName());
        }
        return stringRoles;
    }

    public User getEntity(){
        User user = new User();
        if(this.userName != null){
            user.setUserName(this.userName);
        }
        user.setUserPass(this.userPass);
        user.setRoleList(user.getRoleList());
        return user;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return getUserName().equals(userDTO.getUserName()) && getUserPass().equals(userDTO.getUserPass()) && getRoles().equals(userDTO.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getUserPass(), getRoles());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", roles=" + roles +
                '}';
    }
}