package ug.co.absa.schoolfees;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ug.co.absa.schoolfees.SchoolpayApp;
import ug.co.absa.schoolfees.config.AsyncSyncConfiguration;
import ug.co.absa.schoolfees.config.EmbeddedSQL;
import ug.co.absa.schoolfees.config.TestSecurityConfiguration;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { SchoolpayApp.class, AsyncSyncConfiguration.class, TestSecurityConfiguration.class })
@EmbeddedSQL
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public @interface IntegrationTest {
}
