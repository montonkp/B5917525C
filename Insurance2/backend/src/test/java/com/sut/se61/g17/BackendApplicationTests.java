package com.sut.se61.g17;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BackendApplicationTests {

    @Autowired
    private TestEntityManager entityManager;


    private Validator validator;
    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private SubDistrictRepository subDistrictRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private CarDataRepository carDataRepository;
    @Autowired
    private BranchCarRepository branchCarRepository;
    @Autowired
    private CarColorRepository carColorRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private GearTypeRepository gearTypeRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @Test(expected=javax.persistence.PersistenceException.class)
    public void testPolicyCorrect() {
        Policy policy = new Policy();
        LocalDateTime dateTimeNow = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        LocalDate dateNow = LocalDate.now(ZoneId.of("Asia/Bangkok"));
        LocalDate dateExpiry = dateNow.plusYears(2);

        Address address = addressRepository.save(new Address("7/50 ม.6",
                districtRepository.findByDistrictName("พระประแดง"),
                provinceRepository.findByProvinceName("สมุทรปราการ"),
                subDistrictRepository.findBySubDistrictName("บางหัวเสือ")));

        Customer customer = customerRepository.save(new Customer(
                "Cusfirstname",
                "Cuslastname",
                "1234567890121",
                "email@gmail.com",
                dateNow,
                "0812345678",
                address,
                genderRepository.findByGenderType("male"),
                careerRepository.findByCareerName("Student")));
        CarData carData = carDataRepository.save(new CarData("gram","500",branchCarRepository.findByBranchName("HONDA"),
                carColorRepository.findByColor("Black"),carTypeRepository.findByCarType("4-door"),
                gearTypeRepository.findByGearType("Auto")));
        Property property = propertyRepository.save(new Property("ชั้นที่ 1"));

        policy.setPeriodStartDate(dateNow);
        policy.setPeriodExpiryDate(dateExpiry);
        policy.setIssuedDate(dateTimeNow);
        policy.setEmployee(employeeRepository.findByUsername("admin"));
        policy.setProperty(property);
        policy.setCarData(carData);
        policy.setCustomer(customer);
        policy.setLicensePlate("กข1234");
        policy.setVin("12345678901234567");

        try {
            entityManager.persist(policy);
            entityManager.flush();
//            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test(expected=javax.persistence.PersistenceException.class)
    public void testPolicyVinCannotBeNull() {
        Policy policy = new Policy();
        LocalDateTime dateTimeNow = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        LocalDate dateNow = LocalDate.now(ZoneId.of("Asia/Bangkok"));
        LocalDate dateExpiry = dateNow.plusYears(2);

        Address address = addressRepository.save(new Address("7/50 ม.6",
                districtRepository.findByDistrictName("พระประแดง"),
                provinceRepository.findByProvinceName("สมุทรปราการ"),
                subDistrictRepository.findBySubDistrictName("บางหัวเสือ")));

        Customer customer = customerRepository.save(new Customer(
                "Cusfirstname",
                "Cuslastname",
                "1234567890122",
                "email@gmail.com",
                dateNow,
                "0812345678",
                address,
                genderRepository.findByGenderType("male"),
                careerRepository.findByCareerName("Student")));
        CarData carData = carDataRepository.save(new CarData("gram","500",branchCarRepository.findByBranchName("HONDA"),
                carColorRepository.findByColor("Black"),carTypeRepository.findByCarType("4-door"),
                gearTypeRepository.findByGearType("Auto")));
        Property property = propertyRepository.save(new Property("ชั้นที่ 1"));

        policy.setPeriodStartDate(dateNow);
        policy.setPeriodExpiryDate(dateExpiry);
        policy.setIssuedDate(dateTimeNow);
        policy.setEmployee(employeeRepository.findByUsername("admin"));
        policy.setProperty(property);
        policy.setCarData(carData);
        policy.setCustomer(customer);
        policy.setLicensePlate("กข1234");
        policy.setVin(null);

        try {
            entityManager.persist(policy);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test(expected=javax.persistence.PersistenceException.class)
    public void testPolicyVinSizeIncorrect() {
        Policy policy = new Policy();
        LocalDateTime dateTimeNow = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        LocalDate dateNow = LocalDate.now(ZoneId.of("Asia/Bangkok"));
        LocalDate dateExpiry = dateNow.plusYears(2);

        Address address = addressRepository.save(new Address("7/50 ม.6",
                districtRepository.findByDistrictName("พระประแดง"),
                provinceRepository.findByProvinceName("สมุทรปราการ"),
                subDistrictRepository.findBySubDistrictName("บางหัวเสือ")));

        Customer customer = customerRepository.save(new Customer(
                "Cusfirstname",
                "Cuslastname",
                "1234567890124",
                "email@gmail.com",
                dateNow,
                "0812345678",
                address,
                genderRepository.findByGenderType("male"),
                careerRepository.findByCareerName("Student")));
        CarData carData = carDataRepository.save(new CarData("gram","500",branchCarRepository.findByBranchName("HONDA"),
                carColorRepository.findByColor("Black"),carTypeRepository.findByCarType("4-door"),
                gearTypeRepository.findByGearType("Auto")));
        Property property = propertyRepository.save(new Property("ชั้นที่ 1"));

        policy.setPeriodStartDate(dateNow);
        policy.setPeriodExpiryDate(dateExpiry);
        policy.setIssuedDate(dateTimeNow);
        policy.setEmployee(employeeRepository.findByUsername("admin"));
        policy.setProperty(property);
        policy.setCarData(carData);
        policy.setCustomer(customer);
        policy.setLicensePlate("กข1234");
        policy.setVin("123456789012345678");

        try {
            entityManager.persist(policy);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test(expected=javax.persistence.PersistenceException.class)
    public void testPolicyPlatePatternIncorrect() {
        Policy policy = new Policy();
        LocalDateTime dateTimeNow = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));
        LocalDate dateNow = LocalDate.now(ZoneId.of("Asia/Bangkok"));
        LocalDate dateExpiry = dateNow.plusYears(2);

        Address address = addressRepository.save(new Address("7/50 ม.6",
                districtRepository.findByDistrictName("พระประแดง"),
                provinceRepository.findByProvinceName("สมุทรปราการ"),
                subDistrictRepository.findBySubDistrictName("บางหัวเสือ")));

        Customer customer = customerRepository.save(new Customer(
                "Cusfirstname",
                "Cuslastname",
                "1234567890125",
                "email@gmail.com",
                dateNow,
                "0812345678",
                address,
                genderRepository.findByGenderType("male"),
                careerRepository.findByCareerName("Student")));
        CarData carData = carDataRepository.save(new CarData("gram","500",branchCarRepository.findByBranchName("HONDA"),
                carColorRepository.findByColor("Black"),carTypeRepository.findByCarType("4-door"),
                gearTypeRepository.findByGearType("Auto")));
        Property property = propertyRepository.save(new Property("ชั้นที่ 1"));

        policy.setPeriodExpiryDate(dateExpiry);
        policy.setIssuedDate(dateTimeNow);
        policy.setEmployee(employeeRepository.findByUsername("admin"));
        policy.setProperty(property);
        policy.setCarData(carData);
        policy.setCustomer(customer);
        policy.setLicensePlate("ก1ข1234");
        policy.setVin("123456789012345678");

        try {
            entityManager.persist(policy);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
}

