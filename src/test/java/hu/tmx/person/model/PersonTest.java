package hu.tmx.person.model;

import hu.tmx.person.exception.AlreadyMarriedException;
import hu.tmx.person.exception.SameGenderException;
import hu.tmx.person.exception.TooYoungException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person;
    Person otherPerson;

    @BeforeEach
    public void setUp() {
        person = new Person("John", true, 25);
        person.setMarried(false);
        otherPerson = new Person("Lisa", false, 25);
    }

    @Test
    public void getCorrectNameOfPerson(){
        assertEquals("John", person.getName());
    }

    @Test
    public void personIsMale(){
        assertTrue(person.isMale());
    }

    @Test
    public void getCorrectAgeOfPerson(){
        assertEquals(25, person.getAge());
    }

    @Test
    public void successfulMarriage() throws AlreadyMarriedException, TooYoungException, SameGenderException {
        otherPerson.setMarried(false);
        assertTrue(person.marry(otherPerson));
    }

    @Test
    public void failedMarriageAlreadyMarriageException() {
        otherPerson.setMarried(true);
        assertThrows(AlreadyMarriedException.class, () -> person.marry(otherPerson));
    }

    @Test
    public void failedMarriageTooYoungException() {
        otherPerson.setMarried(false);
        otherPerson.setAge(17);
        assertThrows(TooYoungException.class, () -> person.marry(otherPerson));
    }

    @Test
    public void failedMarriageSameGenderException() {
        otherPerson.setMarried(false);
        otherPerson.setMale(true);
        assertThrows(SameGenderException.class, () -> person.marry(otherPerson));
    }

    @AfterEach
    public void tearDown() {
        person = null;
    }
}