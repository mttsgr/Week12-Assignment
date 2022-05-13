import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TestDemoTest {
	
	TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		 testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource ("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			  assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
			} else { assertThatThrownBy(() -> 
				testDemo.addPositive(a, b))
				.isInstanceOf(IllegalArgumentException.class);
			}
	}
	static Stream <Arguments> argumentsForAddPositive(){
		return Stream.of(
				arguments(2, 4, 6, false),
				arguments(-1, 2, 1, true),
				arguments(0, 1, 1, true),
				arguments(-1, 1, 0, true),
				arguments(5, 2, 7, false),
				arguments(5, -6, -1, true)
				);
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
	
	
}
