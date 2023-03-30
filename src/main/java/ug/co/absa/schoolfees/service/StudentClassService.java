package ug.co.absa.schoolfees.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.co.absa.schoolfees.domain.StudentClass;
import ug.co.absa.schoolfees.repository.StudentClassRepository;
import ug.co.absa.schoolfees.service.dto.StudentClassDTO;
import ug.co.absa.schoolfees.service.mapper.StudentClassMapper;

/**
 * Service Implementation for managing {@link StudentClass}.
 */
@Service
@Transactional
public class StudentClassService {

    private final Logger log = LoggerFactory.getLogger(StudentClassService.class);

    private final StudentClassRepository studentClassRepository;

    private final StudentClassMapper studentClassMapper;

    public StudentClassService(StudentClassRepository studentClassRepository, StudentClassMapper studentClassMapper) {
        this.studentClassRepository = studentClassRepository;
        this.studentClassMapper = studentClassMapper;
    }

    /**
     * Save a studentClass.
     *
     * @param studentClassDTO the entity to save.
     * @return the persisted entity.
     */
    public StudentClassDTO save(StudentClassDTO studentClassDTO) {
        log.debug("Request to save StudentClass : {}", studentClassDTO);
        StudentClass studentClass = studentClassMapper.toEntity(studentClassDTO);
        studentClass = studentClassRepository.save(studentClass);
        return studentClassMapper.toDto(studentClass);
    }

    /**
     * Update a studentClass.
     *
     * @param studentClassDTO the entity to save.
     * @return the persisted entity.
     */
    public StudentClassDTO update(StudentClassDTO studentClassDTO) {
        log.debug("Request to update StudentClass : {}", studentClassDTO);
        StudentClass studentClass = studentClassMapper.toEntity(studentClassDTO);
        studentClass = studentClassRepository.save(studentClass);
        return studentClassMapper.toDto(studentClass);
    }

    /**
     * Partially update a studentClass.
     *
     * @param studentClassDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StudentClassDTO> partialUpdate(StudentClassDTO studentClassDTO) {
        log.debug("Request to partially update StudentClass : {}", studentClassDTO);

        return studentClassRepository
            .findById(studentClassDTO.getId())
            .map(existingStudentClass -> {
                studentClassMapper.partialUpdate(existingStudentClass, studentClassDTO);

                return existingStudentClass;
            })
            .map(studentClassRepository::save)
            .map(studentClassMapper::toDto);
    }

    /**
     * Get all the studentClasses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StudentClassDTO> findAll() {
        log.debug("Request to get all StudentClasses");
        return studentClassRepository.findAll().stream().map(studentClassMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one studentClass by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StudentClassDTO> findOne(Long id) {
        log.debug("Request to get StudentClass : {}", id);
        return studentClassRepository.findById(id).map(studentClassMapper::toDto);
    }

    /**
     * Delete the studentClass by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StudentClass : {}", id);
        studentClassRepository.deleteById(id);
    }
}
