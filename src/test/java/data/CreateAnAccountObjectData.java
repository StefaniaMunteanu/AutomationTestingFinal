package data;

import lombok.Getter;

@Getter
public class CreateAnAccountObjectData {
    private String firstName;
    private String lastName;
    private String email;
    private String emailFinal;
    private String password;
    private String confirmPassword;

    public String getEmail() {
        if (emailFinal == null) {
            generateEmailFinal();
        }
        return emailFinal;
    }

    private void generateEmailFinal(){
        emailFinal = email.replace("{random}", System.currentTimeMillis()+"");
    }
}
