package com.example.Saloon.Service;

import com.example.Saloon.Dto.LoginDto;
import com.example.Saloon.Dto.NewUserDto;
import com.example.Saloon.Entity.User;
import com.example.Saloon.Logging.LoginMessage;
import com.example.Saloon.Repository.UserRepo;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String signUp(NewUserDto newUser) throws Exception {
        if(newUser==null)
            throw new Exception("Trying to create a null user!!!");
        else if(newUser.getUsername()==null)
            throw new Exception("Username is null !!");
        else if(newUser.getEmail()==null)
            throw new Exception("Email is null !!");
        else if(newUser.getFullName()==null)
            throw new Exception("FirstName is null !!");
        else if(newUser.getPassword()==null)
            throw new Exception("Password is null !!");
        else if(!isValidEmailAddress(newUser.getEmail()))
            throw new Exception("Enter valid email");
        else if(newUser.getPassword().length()<6)
            throw new Exception("Password must contain at least 6 characters ");

        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";

        Pattern pattern = Pattern.compile(regex);
//        if(newUser.getPhoneNumber()!=null &&newUser.getPhoneNumber().length()>0) {
//            Matcher matcher = pattern.matcher(newUser.getPhoneNumber());
//            if(!matcher.matches()){
//                throw new Exception("Enter valid  Phone number ");
//            }
//        }

        User existingUser = userRepo.getUserByUsername(newUser.getUsername());
        if (existingUser != null) {
            throw new Exception("There is already a user registered with the username provided !!!");
        }
        existingUser = userRepo.getUserByEmail(newUser.getEmail());
        if (existingUser != null) {
            throw new Exception("There is already a user registered with the email provided !!!");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = newUser.getPassword();
        final String encryptedPassword = bCryptPasswordEncoder.encode(rawPassword);

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(encryptedPassword);
        user.setEmail(newUser.getEmail());
        user.setRole("Customer");
        user.setFullName(newUser.getFullName());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setSalonBranch( newUser.getSalonBranch());
        user.setSalonName( newUser.getSalonName() );
        user.setSalonCity( newUser.getSalonCity() );
        user.setPinCode( newUser.getPinCode() );

        userRepo.save(user);
        return "Registered successfully!";
    }
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    public LoginMessage login(LoginDto loginDto) {
        String msg ="";
        User user = userRepo.getUserByUsername(loginDto.getUsername());
        if (user != null){
            String password = loginDto.getPassword();
            String  enCoderdPassword = user.getPassword();
            Boolean isTrue = passwordEncoder.matches(password, enCoderdPassword );
            if(isTrue){
                User userOptional = userRepo.getUserByUsername(loginDto.getUsername());
                if(userOptional != null){
                    return new LoginMessage("Login Success",true);

                }else {
                    return new LoginMessage("Login Failed", false);

                }

            } else {
                return new LoginMessage("Password Not Match",false);
            } }
        else
        {
            return new LoginMessage("UserName not match",false);

        }
    }

}
