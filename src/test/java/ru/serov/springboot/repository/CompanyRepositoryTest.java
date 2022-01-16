package ru.serov.springboot.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serov.springboot.IntegrationTestBase;
import ru.serov.springboot.entity.Company;

import static org.junit.jupiter.api.Assertions.*;


class CompanyRepositoryTest extends IntegrationTestBase {

    private static final Long APPLE_ID = 1L;
    private static final Long GOOGLE_ID = 2L;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetById() {
        var apple = companyRepository.findById(APPLE_ID);
        var google = companyRepository.findById(GOOGLE_ID);

        assertTrue(apple.isPresent());
        assertTrue(google.isPresent());
        apple.ifPresent(appleComp -> {
            assertEquals("apple", appleComp.getName().toLowerCase());
        });
        google.ifPresent(googleComp -> {
            assertEquals("google", googleComp.getName().toLowerCase());
        });
    }

    @Test
    void testSave() {
        var dell = Company.builder()
                .name("Dell")
                .build();
        var saveDell = companyRepository.save(dell);
        assertNotNull(saveDell.getId());
        assertNotNull(dell.getId());
    }
}