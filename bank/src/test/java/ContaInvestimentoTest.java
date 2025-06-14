import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xubank.model.ContaInvestimento;

public class ContaInvestimentoTest {
    ContaInvestimento inv;

    @BeforeEach
    public void init() {
        inv = new ContaInvestimento(0.01); // 1%
    }

    @Test
    public void testRendimentoComTaxa() {
        inv.depositar(1000);
        inv.renderMensal();
        assertTrue(inv.getSaldo() > 1000); // rendimento - 1% taxa
    }

    @Test
    public void testSaqueComImposto() {
        inv.depositar(1000);
        inv.renderMensal();
        double saldoAntes = inv.getSaldo();
        inv.sacar(300);
        assertTrue(inv.getSaldo() < saldoAntes - 300); // imposto sobre rendimento
    }
}