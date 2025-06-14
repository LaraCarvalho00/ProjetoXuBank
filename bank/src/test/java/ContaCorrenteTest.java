import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xubank.model.ContaCorrente;

public class ContaCorrenteTest {
    ContaCorrente cc;

    @BeforeEach
    public void init() {
        cc = new ContaCorrente(100);
    }

    @Test
    public void testDeposito() {
        cc.depositar(200);
        assertEquals(200, cc.getSaldo(), 0.001);
    }

    @Test
    public void testSaqueDentroDoLimite() {
        cc.depositar(100);
        cc.sacar(180);
        assertEquals(-80, cc.getSaldo(), 0.001);
    }

    @Test
    public void testDepositoCorrigeSaldoNegativo() {
        cc.sacar(50); // saldo fica -50
        cc.depositar(100); // deve aplicar taxa sobre -50
        assertTrue(cc.getSaldo() < 50); // saldo final < 50 por causa da tarifa
    }
}