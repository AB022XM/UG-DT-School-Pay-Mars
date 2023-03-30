package ug.co.absa.schoolfees.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentChannelMapperTest {

    private PaymentChannelMapper paymentChannelMapper;

    @BeforeEach
    public void setUp() {
        paymentChannelMapper = new PaymentChannelMapperImpl();
    }
}
