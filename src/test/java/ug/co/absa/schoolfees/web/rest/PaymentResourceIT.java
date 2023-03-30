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
import ug.co.absa.schoolfees.domain.Payment;
import ug.co.absa.schoolfees.domain.enumeration.PaymentChannel;
import ug.co.absa.schoolfees.repository.PaymentRepository;
import ug.co.absa.schoolfees.service.dto.PaymentDTO;
import ug.co.absa.schoolfees.service.mapper.PaymentMapper;

/**
 * Integration tests for the {@link PaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PaymentResourceIT {

    private static final UUID DEFAULT_RECORD_UNIQUE_IDENTIFIER = UUID.randomUUID();
    private static final UUID UPDATED_RECORD_UNIQUE_IDENTIFIER = UUID.randomUUID();

    private static final String DEFAULT_RETURN_CODE = "AAA";
    private static final String UPDATED_RETURN_CODE = "BBB";

    private static final String DEFAULT_RETURN_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_RETURN_MESSAGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PROCESS_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROCESS_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_FEE_AMOUNT = 1;
    private static final Integer UPDATED_FEE_AMOUNT = 2;

    private static final String DEFAULT_FEE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_FEE_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FEE_DUE_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FEE_DUE_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FEE_DUE_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FEE_DUE_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_FEE_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_OUTSTANDING_AMOUNT = 1;
    private static final Integer UPDATED_OUTSTANDING_AMOUNT = 2;

    private static final String DEFAULT_PAYMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SCHOOL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SCHOOL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STUDENT_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_CLASS = "BBBBBBBBBB";

    private static final PaymentChannel DEFAULT_PAYMENT_CHANNEL = PaymentChannel.OVERTHECOUNTER;
    private static final PaymentChannel UPDATED_PAYMENT_CHANNEL = PaymentChannel.ABSAINTERNETBANKING;

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

    private static final String ENTITY_API_URL = "/api/payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentMockMvc;

    private Payment payment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Payment createEntity(EntityManager em) {
        Payment payment = new Payment()
            .recordUniqueIdentifier(DEFAULT_RECORD_UNIQUE_IDENTIFIER)
            .returnCode(DEFAULT_RETURN_CODE)
            .returnMessage(DEFAULT_RETURN_MESSAGE)
            .processTimestamp(DEFAULT_PROCESS_TIMESTAMP)
            .feeAmount(DEFAULT_FEE_AMOUNT)
            .feeDescription(DEFAULT_FEE_DESCRIPTION)
            .feeDueFromDate(DEFAULT_FEE_DUE_FROM_DATE)
            .feeDueToDate(DEFAULT_FEE_DUE_TO_DATE)
            .feeId(DEFAULT_FEE_ID)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .outstandingAmount(DEFAULT_OUTSTANDING_AMOUNT)
            .paymentCode(DEFAULT_PAYMENT_CODE)
            .schoolCode(DEFAULT_SCHOOL_CODE)
            .schoolName(DEFAULT_SCHOOL_NAME)
            .studentClass(DEFAULT_STUDENT_CLASS)
            .paymentChannel(DEFAULT_PAYMENT_CHANNEL)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return payment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Payment createUpdatedEntity(EntityManager em) {
        Payment payment = new Payment()
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .returnCode(UPDATED_RETURN_CODE)
            .returnMessage(UPDATED_RETURN_MESSAGE)
            .processTimestamp(UPDATED_PROCESS_TIMESTAMP)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .feeDescription(UPDATED_FEE_DESCRIPTION)
            .feeDueFromDate(UPDATED_FEE_DUE_FROM_DATE)
            .feeDueToDate(UPDATED_FEE_DUE_TO_DATE)
            .feeId(UPDATED_FEE_ID)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .outstandingAmount(UPDATED_OUTSTANDING_AMOUNT)
            .paymentCode(UPDATED_PAYMENT_CODE)
            .schoolCode(UPDATED_SCHOOL_CODE)
            .schoolName(UPDATED_SCHOOL_NAME)
            .studentClass(UPDATED_STUDENT_CLASS)
            .paymentChannel(UPDATED_PAYMENT_CHANNEL)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return payment;
    }

    @BeforeEach
    public void initTest() {
        payment = createEntity(em);
    }

    @Test
    @Transactional
    void createPayment() throws Exception {
        int databaseSizeBeforeCreate = paymentRepository.findAll().size();
        // Create the Payment
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);
        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeCreate + 1);
        Payment testPayment = paymentList.get(paymentList.size() - 1);
        assertThat(testPayment.getRecordUniqueIdentifier()).isEqualTo(DEFAULT_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testPayment.getReturnCode()).isEqualTo(DEFAULT_RETURN_CODE);
        assertThat(testPayment.getReturnMessage()).isEqualTo(DEFAULT_RETURN_MESSAGE);
        assertThat(testPayment.getProcessTimestamp()).isEqualTo(DEFAULT_PROCESS_TIMESTAMP);
        assertThat(testPayment.getFeeAmount()).isEqualTo(DEFAULT_FEE_AMOUNT);
        assertThat(testPayment.getFeeDescription()).isEqualTo(DEFAULT_FEE_DESCRIPTION);
        assertThat(testPayment.getFeeDueFromDate()).isEqualTo(DEFAULT_FEE_DUE_FROM_DATE);
        assertThat(testPayment.getFeeDueToDate()).isEqualTo(DEFAULT_FEE_DUE_TO_DATE);
        assertThat(testPayment.getFeeId()).isEqualTo(DEFAULT_FEE_ID);
        assertThat(testPayment.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testPayment.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPayment.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPayment.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testPayment.getOutstandingAmount()).isEqualTo(DEFAULT_OUTSTANDING_AMOUNT);
        assertThat(testPayment.getPaymentCode()).isEqualTo(DEFAULT_PAYMENT_CODE);
        assertThat(testPayment.getSchoolCode()).isEqualTo(DEFAULT_SCHOOL_CODE);
        assertThat(testPayment.getSchoolName()).isEqualTo(DEFAULT_SCHOOL_NAME);
        assertThat(testPayment.getStudentClass()).isEqualTo(DEFAULT_STUDENT_CLASS);
        assertThat(testPayment.getPaymentChannel()).isEqualTo(DEFAULT_PAYMENT_CHANNEL);
        assertThat(testPayment.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testPayment.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testPayment.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testPayment.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPayment.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createPaymentWithExistingId() throws Exception {
        // Create the Payment with an existing ID
        payment.setId(1L);
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        int databaseSizeBeforeCreate = paymentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRecordUniqueIdentifierIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setRecordUniqueIdentifier(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReturnMessageIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setReturnMessage(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFeeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setFeeId(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setFirstName(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setLastName(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMiddleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setMiddleName(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setPaymentCode(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSchoolCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setSchoolCode(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSchoolNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setSchoolName(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStudentClassIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setStudentClass(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPaymentChannelIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentRepository.findAll().size();
        // set the field null
        payment.setPaymentChannel(null);

        // Create the Payment, which fails.
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        restPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPayments() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get all the paymentList
        restPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payment.getId().intValue())))
            .andExpect(jsonPath("$.[*].recordUniqueIdentifier").value(hasItem(DEFAULT_RECORD_UNIQUE_IDENTIFIER.toString())))
            .andExpect(jsonPath("$.[*].returnCode").value(hasItem(DEFAULT_RETURN_CODE)))
            .andExpect(jsonPath("$.[*].returnMessage").value(hasItem(DEFAULT_RETURN_MESSAGE)))
            .andExpect(jsonPath("$.[*].processTimestamp").value(hasItem(DEFAULT_PROCESS_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].feeAmount").value(hasItem(DEFAULT_FEE_AMOUNT)))
            .andExpect(jsonPath("$.[*].feeDescription").value(hasItem(DEFAULT_FEE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].feeDueFromDate").value(hasItem(DEFAULT_FEE_DUE_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].feeDueToDate").value(hasItem(DEFAULT_FEE_DUE_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].feeId").value(hasItem(DEFAULT_FEE_ID)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].outstandingAmount").value(hasItem(DEFAULT_OUTSTANDING_AMOUNT)))
            .andExpect(jsonPath("$.[*].paymentCode").value(hasItem(DEFAULT_PAYMENT_CODE)))
            .andExpect(jsonPath("$.[*].schoolCode").value(hasItem(DEFAULT_SCHOOL_CODE)))
            .andExpect(jsonPath("$.[*].schoolName").value(hasItem(DEFAULT_SCHOOL_NAME)))
            .andExpect(jsonPath("$.[*].studentClass").value(hasItem(DEFAULT_STUDENT_CLASS)))
            .andExpect(jsonPath("$.[*].paymentChannel").value(hasItem(DEFAULT_PAYMENT_CHANNEL.toString())))
            .andExpect(jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1)))
            .andExpect(jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2)))
            .andExpect(jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getPayment() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        // Get the payment
        restPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, payment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(payment.getId().intValue()))
            .andExpect(jsonPath("$.recordUniqueIdentifier").value(DEFAULT_RECORD_UNIQUE_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.returnCode").value(DEFAULT_RETURN_CODE))
            .andExpect(jsonPath("$.returnMessage").value(DEFAULT_RETURN_MESSAGE))
            .andExpect(jsonPath("$.processTimestamp").value(DEFAULT_PROCESS_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.feeAmount").value(DEFAULT_FEE_AMOUNT))
            .andExpect(jsonPath("$.feeDescription").value(DEFAULT_FEE_DESCRIPTION))
            .andExpect(jsonPath("$.feeDueFromDate").value(DEFAULT_FEE_DUE_FROM_DATE.toString()))
            .andExpect(jsonPath("$.feeDueToDate").value(DEFAULT_FEE_DUE_TO_DATE.toString()))
            .andExpect(jsonPath("$.feeId").value(DEFAULT_FEE_ID))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.outstandingAmount").value(DEFAULT_OUTSTANDING_AMOUNT))
            .andExpect(jsonPath("$.paymentCode").value(DEFAULT_PAYMENT_CODE))
            .andExpect(jsonPath("$.schoolCode").value(DEFAULT_SCHOOL_CODE))
            .andExpect(jsonPath("$.schoolName").value(DEFAULT_SCHOOL_NAME))
            .andExpect(jsonPath("$.studentClass").value(DEFAULT_STUDENT_CLASS))
            .andExpect(jsonPath("$.paymentChannel").value(DEFAULT_PAYMENT_CHANNEL.toString()))
            .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
            .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
            .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPayment() throws Exception {
        // Get the payment
        restPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPayment() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();

        // Update the payment
        Payment updatedPayment = paymentRepository.findById(payment.getId()).get();
        // Disconnect from session so that the updates on updatedPayment are not directly saved in db
        em.detach(updatedPayment);
        updatedPayment
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .returnCode(UPDATED_RETURN_CODE)
            .returnMessage(UPDATED_RETURN_MESSAGE)
            .processTimestamp(UPDATED_PROCESS_TIMESTAMP)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .feeDescription(UPDATED_FEE_DESCRIPTION)
            .feeDueFromDate(UPDATED_FEE_DUE_FROM_DATE)
            .feeDueToDate(UPDATED_FEE_DUE_TO_DATE)
            .feeId(UPDATED_FEE_ID)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .outstandingAmount(UPDATED_OUTSTANDING_AMOUNT)
            .paymentCode(UPDATED_PAYMENT_CODE)
            .schoolCode(UPDATED_SCHOOL_CODE)
            .schoolName(UPDATED_SCHOOL_NAME)
            .studentClass(UPDATED_STUDENT_CLASS)
            .paymentChannel(UPDATED_PAYMENT_CHANNEL)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        PaymentDTO paymentDTO = paymentMapper.toDto(updatedPayment);

        restPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isOk());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
        Payment testPayment = paymentList.get(paymentList.size() - 1);
        assertThat(testPayment.getRecordUniqueIdentifier()).isEqualTo(UPDATED_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testPayment.getReturnCode()).isEqualTo(UPDATED_RETURN_CODE);
        assertThat(testPayment.getReturnMessage()).isEqualTo(UPDATED_RETURN_MESSAGE);
        assertThat(testPayment.getProcessTimestamp()).isEqualTo(UPDATED_PROCESS_TIMESTAMP);
        assertThat(testPayment.getFeeAmount()).isEqualTo(UPDATED_FEE_AMOUNT);
        assertThat(testPayment.getFeeDescription()).isEqualTo(UPDATED_FEE_DESCRIPTION);
        assertThat(testPayment.getFeeDueFromDate()).isEqualTo(UPDATED_FEE_DUE_FROM_DATE);
        assertThat(testPayment.getFeeDueToDate()).isEqualTo(UPDATED_FEE_DUE_TO_DATE);
        assertThat(testPayment.getFeeId()).isEqualTo(UPDATED_FEE_ID);
        assertThat(testPayment.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testPayment.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPayment.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPayment.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testPayment.getOutstandingAmount()).isEqualTo(UPDATED_OUTSTANDING_AMOUNT);
        assertThat(testPayment.getPaymentCode()).isEqualTo(UPDATED_PAYMENT_CODE);
        assertThat(testPayment.getSchoolCode()).isEqualTo(UPDATED_SCHOOL_CODE);
        assertThat(testPayment.getSchoolName()).isEqualTo(UPDATED_SCHOOL_NAME);
        assertThat(testPayment.getStudentClass()).isEqualTo(UPDATED_STUDENT_CLASS);
        assertThat(testPayment.getPaymentChannel()).isEqualTo(UPDATED_PAYMENT_CHANNEL);
        assertThat(testPayment.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testPayment.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testPayment.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testPayment.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPayment.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingPayment() throws Exception {
        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();
        payment.setId(count.incrementAndGet());

        // Create the Payment
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPayment() throws Exception {
        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();
        payment.setId(count.incrementAndGet());

        // Create the Payment
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPayment() throws Exception {
        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();
        payment.setId(count.incrementAndGet());

        // Create the Payment
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePaymentWithPatch() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();

        // Update the payment using partial update
        Payment partialUpdatedPayment = new Payment();
        partialUpdatedPayment.setId(payment.getId());

        partialUpdatedPayment
            .returnCode(UPDATED_RETURN_CODE)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .feeDueFromDate(UPDATED_FEE_DUE_FROM_DATE)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .middleName(UPDATED_MIDDLE_NAME)
            .schoolCode(UPDATED_SCHOOL_CODE)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2);

        restPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayment.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayment))
            )
            .andExpect(status().isOk());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
        Payment testPayment = paymentList.get(paymentList.size() - 1);
        assertThat(testPayment.getRecordUniqueIdentifier()).isEqualTo(DEFAULT_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testPayment.getReturnCode()).isEqualTo(UPDATED_RETURN_CODE);
        assertThat(testPayment.getReturnMessage()).isEqualTo(DEFAULT_RETURN_MESSAGE);
        assertThat(testPayment.getProcessTimestamp()).isEqualTo(DEFAULT_PROCESS_TIMESTAMP);
        assertThat(testPayment.getFeeAmount()).isEqualTo(UPDATED_FEE_AMOUNT);
        assertThat(testPayment.getFeeDescription()).isEqualTo(DEFAULT_FEE_DESCRIPTION);
        assertThat(testPayment.getFeeDueFromDate()).isEqualTo(UPDATED_FEE_DUE_FROM_DATE);
        assertThat(testPayment.getFeeDueToDate()).isEqualTo(DEFAULT_FEE_DUE_TO_DATE);
        assertThat(testPayment.getFeeId()).isEqualTo(DEFAULT_FEE_ID);
        assertThat(testPayment.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testPayment.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPayment.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPayment.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testPayment.getOutstandingAmount()).isEqualTo(DEFAULT_OUTSTANDING_AMOUNT);
        assertThat(testPayment.getPaymentCode()).isEqualTo(DEFAULT_PAYMENT_CODE);
        assertThat(testPayment.getSchoolCode()).isEqualTo(UPDATED_SCHOOL_CODE);
        assertThat(testPayment.getSchoolName()).isEqualTo(DEFAULT_SCHOOL_NAME);
        assertThat(testPayment.getStudentClass()).isEqualTo(DEFAULT_STUDENT_CLASS);
        assertThat(testPayment.getPaymentChannel()).isEqualTo(DEFAULT_PAYMENT_CHANNEL);
        assertThat(testPayment.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testPayment.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testPayment.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testPayment.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPayment.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdatePaymentWithPatch() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();

        // Update the payment using partial update
        Payment partialUpdatedPayment = new Payment();
        partialUpdatedPayment.setId(payment.getId());

        partialUpdatedPayment
            .recordUniqueIdentifier(UPDATED_RECORD_UNIQUE_IDENTIFIER)
            .returnCode(UPDATED_RETURN_CODE)
            .returnMessage(UPDATED_RETURN_MESSAGE)
            .processTimestamp(UPDATED_PROCESS_TIMESTAMP)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .feeDescription(UPDATED_FEE_DESCRIPTION)
            .feeDueFromDate(UPDATED_FEE_DUE_FROM_DATE)
            .feeDueToDate(UPDATED_FEE_DUE_TO_DATE)
            .feeId(UPDATED_FEE_ID)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .outstandingAmount(UPDATED_OUTSTANDING_AMOUNT)
            .paymentCode(UPDATED_PAYMENT_CODE)
            .schoolCode(UPDATED_SCHOOL_CODE)
            .schoolName(UPDATED_SCHOOL_NAME)
            .studentClass(UPDATED_STUDENT_CLASS)
            .paymentChannel(UPDATED_PAYMENT_CHANNEL)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayment.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayment))
            )
            .andExpect(status().isOk());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
        Payment testPayment = paymentList.get(paymentList.size() - 1);
        assertThat(testPayment.getRecordUniqueIdentifier()).isEqualTo(UPDATED_RECORD_UNIQUE_IDENTIFIER);
        assertThat(testPayment.getReturnCode()).isEqualTo(UPDATED_RETURN_CODE);
        assertThat(testPayment.getReturnMessage()).isEqualTo(UPDATED_RETURN_MESSAGE);
        assertThat(testPayment.getProcessTimestamp()).isEqualTo(UPDATED_PROCESS_TIMESTAMP);
        assertThat(testPayment.getFeeAmount()).isEqualTo(UPDATED_FEE_AMOUNT);
        assertThat(testPayment.getFeeDescription()).isEqualTo(UPDATED_FEE_DESCRIPTION);
        assertThat(testPayment.getFeeDueFromDate()).isEqualTo(UPDATED_FEE_DUE_FROM_DATE);
        assertThat(testPayment.getFeeDueToDate()).isEqualTo(UPDATED_FEE_DUE_TO_DATE);
        assertThat(testPayment.getFeeId()).isEqualTo(UPDATED_FEE_ID);
        assertThat(testPayment.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testPayment.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPayment.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPayment.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testPayment.getOutstandingAmount()).isEqualTo(UPDATED_OUTSTANDING_AMOUNT);
        assertThat(testPayment.getPaymentCode()).isEqualTo(UPDATED_PAYMENT_CODE);
        assertThat(testPayment.getSchoolCode()).isEqualTo(UPDATED_SCHOOL_CODE);
        assertThat(testPayment.getSchoolName()).isEqualTo(UPDATED_SCHOOL_NAME);
        assertThat(testPayment.getStudentClass()).isEqualTo(UPDATED_STUDENT_CLASS);
        assertThat(testPayment.getPaymentChannel()).isEqualTo(UPDATED_PAYMENT_CHANNEL);
        assertThat(testPayment.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testPayment.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testPayment.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testPayment.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPayment.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingPayment() throws Exception {
        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();
        payment.setId(count.incrementAndGet());

        // Create the Payment
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, paymentDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPayment() throws Exception {
        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();
        payment.setId(count.incrementAndGet());

        // Create the Payment
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPayment() throws Exception {
        int databaseSizeBeforeUpdate = paymentRepository.findAll().size();
        payment.setId(count.incrementAndGet());

        // Create the Payment
        PaymentDTO paymentDTO = paymentMapper.toDto(payment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(paymentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Payment in the database
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePayment() throws Exception {
        // Initialize the database
        paymentRepository.saveAndFlush(payment);

        int databaseSizeBeforeDelete = paymentRepository.findAll().size();

        // Delete the payment
        restPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, payment.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Payment> paymentList = paymentRepository.findAll();
        assertThat(paymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
