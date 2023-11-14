package documentation.builder;

public class app {

    public static void main(String[] args) {
        UserTest marouan  = UserTest.builder()
                .firstName("marouan")
                .lastName("bouhalla")
                .build();

        System.out.println(marouan);

        UserTest abdellah  = UserTest.builder()
                .email("abdellah@gmail.com")
                .password("123456")
                .build();

        System.out.println(abdellah);
    }
}
