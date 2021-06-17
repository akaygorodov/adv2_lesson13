package Project;

import Project.model.DriverLicence;
import Project.model.Person;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws Exception {
        final DriverLicence driverLicence = DriverLicence.builder()
                .categories(new String[]{"A", "B", "C"})
                .expireDate(Instant.parse("1999-11-30T18:35:24.00Z"))
                .build();
        final Person person = Person.builder()
                .firstName("John")
                .lastName("Smith")
                .driverLicence(Optional.ofNullable(driverLicence))
                .build();

        Optional.ofNullable(person)
                .flatMap(Person::getDriverLicence)
                .filter(driverLicence1 -> Instant.now().isBefore(driverLicence1.getExpireDate()))
                .map(DriverLicence::getCategories)
                .ifPresentOrElse(Categories -> System.out.println(Arrays.toString(Categories)), () -> {
                    try {
                        throw new Exception("No Active Licence");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
