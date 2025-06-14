import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.xubank.model.ContaCorrente;

public class ContaCorrente_DepositoNegativoTest {

    @Test
    public void testDepositoComSaldoNegativoAplicaTaxa() {
        ContaCorrente cc = new ContaCorrente(100);
        cc.sacar(100); // saldo agora é -100

        cc.depositar(200); // deve aplicar taxa: 3% de 100 = 3 + 10 = 13

        // Esperado: -100 + 200 - 13 = 87
        assertEquals(87.0, cc.getSaldo(), 0.001);
    }
}