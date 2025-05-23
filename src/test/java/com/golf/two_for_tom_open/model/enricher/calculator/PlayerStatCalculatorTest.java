package com.golf.two_for_tom_open.model.enricher.calculator;

import com.golf.two_for_tom_open.service.ScoreService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlayerStatCalculatorTest {

    @InjectMocks
    private PlayerStatCalculator calculator;
    @Mock
    private ScoreService scoreService;

}