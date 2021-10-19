class MoneyAccount(private val accountType: AccountTypes = AccountTypes.VK_PAY) {

    companion object {
        const val MAESTRO_COMMISSION_UNDER_LIMIT_PERCENT = 0.0
        const val MAESTRO_COMMISSION_MONTH_LIMIT = 75_000L
        const val MAESTRO_COMMISSION_LIMITED_PERCENT = 0.6
        const val MAESTRO_COMMISSION_LIMITED_ADD = 20L

        const val VISA_COMMISSION_PERCENT = 0.75
        const val VISA_COMMISSION_MINIMAL = 35L

        const val KOP_PER_RUB = 1000L
    }

    private var curTransactionAmount = 0L
    private var monthTransactionAmount = 0L
    private var isLimitedTransaction = false

    private fun rubToKop(amount: Long): Long {
        return amount * KOP_PER_RUB
    }

    private fun kopToRub(amount: Long): Long {
        return amount / KOP_PER_RUB
    }

    fun getAccountType() = accountType

    fun setCurTransaction(amount: Long) {
        curTransactionAmount = amount
        monthTransactionAmount += amount

        if (monthTransactionAmount > MAESTRO_COMMISSION_MONTH_LIMIT) isLimitedTransaction = true
    }

    fun getCommission(): Long {

        val transactionKop = rubToKop(curTransactionAmount)
        val transactionKopVisa = transactionKop * VISA_COMMISSION_PERCENT / 100
        val visaCommissionMinimalKop = rubToKop(VISA_COMMISSION_MINIMAL)

        val commission = when (accountType) {
            AccountTypes.MAESTRO, AccountTypes.MASTERCARD ->
                if (isLimitedTransaction) {
                    transactionKop * MAESTRO_COMMISSION_LIMITED_PERCENT / 100 + rubToKop(MAESTRO_COMMISSION_LIMITED_ADD)
                } else {
                    transactionKop * MAESTRO_COMMISSION_UNDER_LIMIT_PERCENT / 100
                }
            AccountTypes.VISA, AccountTypes.MIR ->
                if (transactionKopVisa < visaCommissionMinimalKop) {
                    visaCommissionMinimalKop
                } else {
                    transactionKopVisa
                }
            AccountTypes.VK_PAY -> 0L
        }

        return kopToRub(commission.toLong())
    }
}