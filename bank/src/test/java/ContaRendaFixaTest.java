import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xubank.model.ContaRendaFixa;

public class ContaRendaFixaTest {
    ContaRendaFixa rf;

    @BeforeEach
    public void init() {
        rf = new ContaRendaFixa(0.007); // 0.7%
    }

    @Test
    public void testRendimentoComDesconto() {
        rf.depositar(1000);
        rf.renderMensal();
        assertTrue(rf.getSaldo() > 980); // considerando taxa de R$20
    }

    @Test
    public void testSaqueComImposto() {
        rf.depositar(1000);
        rf.renderMensal();
        double saldoAntes = rf.getSaldo();
        rf.sacar(500);
        assertTrue(rf.getSaldo() < saldoAntes - 500);
    }
}