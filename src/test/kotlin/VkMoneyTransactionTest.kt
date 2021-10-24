import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class VkMoneyTransactionTest {

    @Test
    fun calcCommission_Mastercard() {
        var srcAccount = MoneyAccount(AccountTypes.MASTERCARD)

        //Before Limit exhausted
        var transaction: Long = 75_000L
        assertEquals(0, VkMoneyTransaction.calcCommission(srcAccount, transaction))

        //After Limit exhausted
        transaction = 75_001L
        assertEquals(470, VkMoneyTransaction.calcCommission(srcAccount, transaction))
    }

    @Test
    fun calcCommission_Maestro() {
        val srcAccount = MoneyAccount(AccountTypes.MAESTRO)

        //Before Limit exhausted
        var transaction: Long = 75_000L
        assertEquals(0, VkMoneyTransaction.calcCommission(srcAccount, transaction))

        //After Limit exhausted
        transaction = 75_001L
        assertEquals(470, VkMoneyTransaction.calcCommission(srcAccount, transaction))
    }

    @Test
    fun calcCommission_VISA() {
        val srcAccount = MoneyAccount(AccountTypes.VISA)

        //Minimal commission
        var transaction: Long = 100L
        assertEquals(35, VkMoneyTransaction.calcCommission(srcAccount, transaction))

        //Commission
        transaction = 75_000L
        assertEquals(562, VkMoneyTransaction.calcCommission(srcAccount, transaction))
    }

    @Test
    fun calcCommission_MIR() {
        val srcAccount = MoneyAccount(AccountTypes.VISA)

        //Minimal commission
        var transaction: Long = 100L
        assertEquals(35, VkMoneyTransaction.calcCommission(srcAccount, transaction))

        //Commission
        transaction = 75_000L
        assertEquals(562, VkMoneyTransaction.calcCommission(srcAccount, transaction))
    }

    @Test
    fun calcCommission_VkPay() {
        val srcAccount = MoneyAccount(AccountTypes.VK_PAY)

        //Commission
        var transaction: Long = 10_000_000L
        assertEquals(1, VkMoneyTransaction.calcCommission(srcAccount, transaction))
    }
}