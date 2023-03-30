package ug.co.absa.schoolfees.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ug.co.absa.schoolfees.IntegrationTest;
import ug.co.absa.schoolfees.domain.StudentClass;
import ug.co.absa.schoolfees.repository.StudentClassRepository;
import ug.co.absa.schoolfees.service.dto.StudentClassDTO;
import ug.co.absa.schoolfees.service.mapper.StudentClassMapper;

/**
 * Integration tests for the {@link StudentClassResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StudentClassResourceIT {

    private static final UUID DEFAULT_RECORD_UNIQUE_IDENTIFIER = UUID.randomUUID();
    private static final UUID UPDATED_RECORD_UNIQUE_IDENTIFIER = UUID.randomUUID();

    private static final Integer DEFAULT_STUDENT_CLASS_ID = 1;
    private static final Integer UPDATED_STUDENT_CLASS_ID = 2;

    private static final String DEFAULT_STUDENT_CLASS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_CLASS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_CLASS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_CLASS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_CLASS_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_CLASS_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_IS_DELETED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IS_DELETED = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/student-classes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStudentClassMockMvc;

    private StudentClass studentClass;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudentClass createEntity(EntityManager em) {
        StudentClass studentClass = new StudentClass()
            .recordUniqueIdentifier(DEFAULT_RECORD_UNIQUE_IDENTIFIER)
            .studentClassId(DEFAULT_STUDENT_CLASS_ID)
            .studentClassCode(DEFAULT_STUDENT_CLASS_CODE)
            .studentClassName(DEFAULT_STUDENT_CLASS_NAME)
            .studentClassDescription(DEFAULT_STUDENT_CLASS_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .isDeleted(DEFAULT_IS_DELETED);
        return studentClass;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudentClass createUpdatedEntity(EntityManager em) {
        StudentClass studentClass = new StudentClass()
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .studentClassId(UPDATED_STUDENT_CLASS_ID)
            .studentClassCode(UPDATED_STUDENT_CLASS_CODE)
            .studentClassName(UPDATED_STUDENT_CLASS_NAME)
            .studentClassDescription(UPDATED_STUDENT_CLASS_DESCRIPTION)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .isDeleted(UPDATED_IS_DELETED);
        return studentClass;
    }

    @BeforeEach
    public void initTest() {
        studentClass = createEntity(em);
    }

    @Test
    @Transactional
    void createStudentClass() throws Exception {
        int databaseSizeBeforeCreate = studentClassRepository.findAll().size();
        // Create the StudentClass
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);
        restStudentClassMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isCreated());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeCreate + 1);
        StudentClass testStudentClass = studentClassList.get(studentClassList.size() - 1);
        assertThat(testStudentClass.getRecordUniqueIdentifier()).isEqualTo(DEFAULT_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testStudentClass.getStudentClassId()).isEqualTo(DEFAULT_STUDENT_CLASS_ID);
        assertThat(testStudentClass.getStudentClassCode()).isEqualTo(DEFAULT_STUDENT_CLASS_CODE);
        assertThat(testStudentClass.getStudentClassName()).isEqualTo(DEFAULT_STUDENT_CLASS_NAME);
        assertThat(testStudentClass.getStudentClassDescription()).isEqualTo(DEFAULT_STUDENT_CLASS_DESCRIPTION);
        assertThat(testStudentClass.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testStudentClass.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testStudentClass.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testStudentClass.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testStudentClass.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testStudentClass.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testStudentClass.getIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    void createStudentClassWithExistingId() throws Exception {
        // Create the StudentClass with an existing ID
        studentClass.setId(1L);
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        int databaseSizeBeforeCreate = studentClassRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentClassMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRecordUniqueIdentifierIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentClassRepository.findAll().size();
        // set the field null
        studentClass.setRecordUniqueIdentifier(null);

        // Create the StudentClass, which fails.
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        restStudentClassMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStudentClassIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentClassRepository.findAll().size();
        // set the field null
        studentClass.setStudentClassId(null);

        // Create the StudentClass, which fails.
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        restStudentClassMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStudentClassCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentClassRepository.findAll().size();
        // set the field null
        studentClass.setStudentClassCode(null);

        // Create the StudentClass, which fails.
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        restStudentClassMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStudentClassNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentClassRepository.findAll().size();
        // set the field null
        studentClass.setStudentClassName(null);

        // Create the StudentClass, which fails.
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        restStudentClassMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStudentClassDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentClassRepository.findAll().size();
        // set the field null
        studentClass.setStudentClassDescription(null);

        // Create the StudentClass, which fails.
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        restStudentClassMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentClassRepository.findAll().size();
        // set the field null
        studentClass.setStatus(null);

        // Create the StudentClass, which fails.
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        restStudentClassMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllStudentClasses() throws Exception {
        // Initialize the database
        studentClassRepository.saveAndFlush(studentClass);

        // Get all the studentClassList
        restStudentClassMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentClass.getId().intValue())))
            .andExpect(jsonPath("$.[*].recordUniqueIdentifier").value(hasItem(DEFAULT_RECORD_UNIQUE_IDENTIFIER.toString())))
            .andExpect(jsonPath("$.[*].studentClassId").value(hasItem(DEFAULT_STUDENT_CLASS_ID)))
            .andExpect(jsonPath("$.[*].studentClassCode").value(hasItem(DEFAULT_STUDENT_CLASS_CODE)))
            .andExpect(jsonPath("$.[*].studentClassName").value(hasItem(DEFAULT_STUDENT_CLASS_NAME)))
            .andExpect(jsonPath("$.[*].studentClassDescription").value(hasItem(DEFAULT_STUDENT_CLASS_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.toString())));
    }

    @Test
    @Transactional
    void getStudentClass() throws Exception {
        // Initialize the database
        studentClassRepository.saveAndFlush(studentClass);

        // Get the studentClass
        restStudentClassMockMvc
            .perform(get(ENTITY_API_URL_ID, studentClass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(studentClass.getId().intValue()))
            .andExpect(jsonPath("$.recordUniqueIdentifier").value(DEFAULT_RECORD_UNIQUE_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.studentClassId").value(DEFAULT_STUDENT_CLASS_ID))
            .andExpect(jsonPath("$.studentClassCode").value(DEFAULT_STUDENT_CLASS_CODE))
            .andExpect(jsonPath("$.studentClassName").value(DEFAULT_STUDENT_CLASS_NAME))
            .andExpect(jsonPath("$.studentClassDescription").value(DEFAULT_STUDENT_CLASS_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
            .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
            .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.toString()));
    }

    @Test
    @Transactional
    void getNonExistingStudentClass() throws Exception {
        // Get the studentClass
        restStudentClassMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStudentClass() throws Exception {
        // Initialize the database
        studentClassRepository.saveAndFlush(studentClass);

        int databaseSizeBeforeUpdate = studentClassRepository.findAll().size();

        // Update the studentClass
        StudentClass updatedStudentClass = studentClassRepository.findById(studentClass.getId()).get();
        // Disconnect from session so that the updates on updatedStudentClass are not directly saved in db
        em.detach(updatedStudentClass);
        updatedStudentClass
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .studentClassId(UPDATED_STUDENT_CLASS_ID)
            .studentClassCode(UPDATED_STUDENT_CLASS_CODE)
            .studentClassName(UPDATED_STUDENT_CLASS_NAME)
            .studentClassDescription(UPDATED_STUDENT_CLASS_DESCRIPTION)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .isDeleted(UPDATED_IS_DELETED);
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(updatedStudentClass);

        restStudentClassMockMvc
            .perform(
                put(ENTITY_API_URL_ID, studentClassDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isOk());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeUpdate);
        StudentClass testStudentClass = studentClassList.get(studentClassList.size() - 1);
        assertThat(testStudentClass.getRecordUniqueIdentifier()).isEqualTo(UPDATED_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testStudentClass.getStudentClassId()).isEqualTo(UPDATED_STUDENT_CLASS_ID);
        assertThat(testStudentClass.getStudentClassCode()).isEqualTo(UPDATED_STUDENT_CLASS_CODE);
        assertThat(testStudentClass.getStudentClassName()).isEqualTo(UPDATED_STUDENT_CLASS_NAME);
        assertThat(testStudentClass.getStudentClassDescription()).isEqualTo(UPDATED_STUDENT_CLASS_DESCRIPTION);
        assertThat(testStudentClass.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStudentClass.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testStudentClass.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testStudentClass.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testStudentClass.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testStudentClass.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testStudentClass.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    void putNonExistingStudentClass() throws Exception {
        int databaseSizeBeforeUpdate = studentClassRepository.findAll().size();
        studentClass.setId(count.incrementAndGet());

        // Create the StudentClass
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentClassMockMvc
            .perform(
                put(ENTITY_API_URL_ID, studentClassDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStudentClass() throws Exception {
        int databaseSizeBeforeUpdate = studentClassRepository.findAll().size();
        studentClass.setId(count.incrementAndGet());

        // Create the StudentClass
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentClassMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStudentClass() throws Exception {
        int databaseSizeBeforeUpdate = studentClassRepository.findAll().size();
        studentClass.setId(count.incrementAndGet());

        // Create the StudentClass
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentClassMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStudentClassWithPatch() throws Exception {
        // Initialize the database
        studentClassRepository.saveAndFlush(studentClass);

        int databaseSizeBeforeUpdate = studentClassRepository.findAll().size();

        // Update the studentClass using partial update
        StudentClass partialUpdatedStudentClass = new StudentClass();
        partialUpdatedStudentClass.setId(studentClass.getId());

        partialUpdatedStudentClass
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .studentClassId(UPDATED_STUDENT_CLASS_ID)
            .studentClassName(UPDATED_STUDENT_CLASS_NAME)
            .status(UPDATED_STATUS)
            .freeField3(UPDATED_FREE_FIELD_3);

        restStudentClassMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudentClass.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudentClass))
            )
            .andExpect(status().isOk());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeUpdate);
        StudentClass testStudentClass = studentClassList.get(studentClassList.size() - 1);
        assertThat(testStudentClass.getRecordUniqueIdentifier()).isEqualTo(UPDATED_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testStudentClass.getStudentClassId()).isEqualTo(UPDATED_STUDENT_CLASS_ID);
        assertThat(testStudentClass.getStudentClassCode()).isEqualTo(DEFAULT_STUDENT_CLASS_CODE);
        assertThat(testStudentClass.getStudentClassName()).isEqualTo(UPDATED_STUDENT_CLASS_NAME);
        assertThat(testStudentClass.getStudentClassDescription()).isEqualTo(DEFAULT_STUDENT_CLASS_DESCRIPTION);
        assertThat(testStudentClass.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStudentClass.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testStudentClass.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testStudentClass.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testStudentClass.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testStudentClass.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testStudentClass.getIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    void fullUpdateStudentClassWithPatch() throws Exception {
        // Initialize the database
        studentClassRepository.saveAndFlush(studentClass);

        int databaseSizeBeforeUpdate = studentClassRepository.findAll().size();

        // Update the studentClass using partial update
        StudentClass partialUpdatedStudentClass = new StudentClass();
        partialUpdatedStudentClass.setId(studentClass.getId());

        partialUpdatedStudentClass
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .studentClassId(UPDATED_STUDENT_CLASS_ID)
            .studentClassCode(UPDATED_STUDENT_CLASS_CODE)
            .studentClassName(UPDATED_STUDENT_CLASS_NAME)
            .studentClassDescription(UPDATED_STUDENT_CLASS_DESCRIPTION)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .isDeleted(UPDATED_IS_DELETED);

        restStudentClassMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStudentClass.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStudentClass))
            )
            .andExpect(status().isOk());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeUpdate);
        StudentClass testStudentClass = studentClassList.get(studentClassList.size() - 1);
        assertThat(testStudentClass.getRecordUniqueIdentifier()).isEqualTo(UPDATED_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testStudentClass.getStudentClassId()).isEqualTo(UPDATED_STUDENT_CLASS_ID);
        assertThat(testStudentClass.getStudentClassCode()).isEqualTo(UPDATED_STUDENT_CLASS_CODE);
        assertThat(testStudentClass.getStudentClassName()).isEqualTo(UPDATED_STUDENT_CLASS_NAME);
        assertThat(testStudentClass.getStudentClassDescription()).isEqualTo(UPDATED_STUDENT_CLASS_DESCRIPTION);
        assertThat(testStudentClass.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStudentClass.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testStudentClass.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testStudentClass.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testStudentClass.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testStudentClass.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testStudentClass.getIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    void patchNonExistingStudentClass() throws Exception {
        int databaseSizeBeforeUpdate = studentClassRepository.findAll().size();
        studentClass.setId(count.incrementAndGet());

        // Create the StudentClass
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentClassMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, studentClassDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStudentClass() throws Exception {
        int databaseSizeBeforeUpdate = studentClassRepository.findAll().size();
        studentClass.setId(count.incrementAndGet());

        // Create the StudentClass
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentClassMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStudentClass() throws Exception {
        int databaseSizeBeforeUpdate = studentClassRepository.findAll().size();
        studentClass.setId(count.incrementAndGet());

        // Create the StudentClass
        StudentClassDTO studentClassDTO = studentClassMapper.toDto(studentClass);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStudentClassMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(studentClassDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the StudentClass in the database
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStudentClass() throws Exception {
        // Initialize the database
        studentClassRepository.saveAndFlush(studentClass);

        int databaseSizeBeforeDelete = studentClassRepository.findAll().size();

        // Delete the studentClass
        restStudentClassMockMvc
            .perform(delete(ENTITY_API_URL_ID, studentClass.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StudentClass> studentClassList = studentClassRepository.findAll();
        assertThat(studentClassList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
