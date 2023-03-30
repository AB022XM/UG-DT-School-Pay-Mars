package ug.co.absa.schoolfees.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ug.co.absa.schoolfees.web.rest.TestUtil;

class StudentClassTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentClass.class);
        StudentClass studentClass1 = new StudentClass();
        studentClass1.setId(1L);
        StudentClass studentClass2 = new StudentClass();
        studentClass2.setId(studentClass1.getId());
        assertThat(studentClass1).isEqualTo(studentClass2);
        studentClass2.setId(2L);
        assertThat(studentClass1).isNotEqualTo(studentClass2);
        studentClass1.setId(null);
        assertThat(studentClass1).isNotEqualTo(studentClass2);
    }
}
