package documentation.builder;

import lombok.*;

@ToString
@Builder
@Setter
@Getter
@Data
public class UserTest {
    private  Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;


}



