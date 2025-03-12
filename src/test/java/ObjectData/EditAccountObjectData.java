package ObjectData;

import lombok.Getter;

@Getter
public class EditAccountObjectData {
    private String firstName;
    private String lastName;
    private String email;
    private String newPassword;
    private String confirmNewPassword;
    private String emailFinal;

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
