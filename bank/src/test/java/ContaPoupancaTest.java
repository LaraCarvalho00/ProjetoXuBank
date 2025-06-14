import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xubank.model.ContaPoupanca;

public class ContaPoupancaTest {
    ContaPoupanca cp;

    @BeforeEach
    public void init() {
        cp = new ContaPoupanca();
    }

    @Test
    public void testRendimentoMensal() {
        cp.depositar(1000);
        cp.renderMensal();
        assertEquals(1006, cp.getSaldo(), 0.001);
    }
}