package v20annotation;

import java.util.List;

public class PasswordUtils {
    @UseCase(id=47,description = "密码必须有一个数字")
    public boolean validatePassword(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id=48)
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id=49,description = "新密码不能和旧密码重复")
    public boolean checkForNewPassword(List<String> prevPasswords,String password){
        return !prevPasswords.contains(password);
    }
}
