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


    public static void main(String[] args) {
        // create user with first name and last name


        UserTest user_1 = UserTest.builder()
                .firstName("abdellah")
                .lastName("el hilaly")
                .email("abdellah")
                .phoneNumber("0666666666")
                .build();


        System.out.println(user_1);


        UserTest  user_2 = UserTest.builder()
                .email("abdellah")
                .password("123fooier45678")
                .build();



    }
}



