class VkMoneyTransaction {

    companion object {

        private const val MAESTRO_COMMISSION_UNDER_LIMIT_PERCENT = 0.0
        private const val MAESTRO_COMMISSION_MONTH_LIMIT = 75_000L
        private const val MAESTRO_COMMISSION_LIMITED_PERCENT = 0.6
        private const val MAESTRO_COMMISSION_LIMITED_ADD = 20L

        private const val VISA_COMMISSION_PERCENT = 0.75
        private const val VISA_COMMISSION_MINIMAL = 35L

        private const val KOP_PER_RUB = 1000L


        private fun rubToKop(amount: Long): Long {
            return amount * KOP_PER_RUB
        }

        private fun kopToRub(amount: Long): Long {
            return amount / KOP_PER_RUB
        }

        fun calcCommission(srcAccount: MoneyAccount, transactionAmount: Long): Long {

            val accountType = srcAccount.getAccountType()
            srcAccount.setCurTransaction(transactionAmount)
            val transactionKop = rubToKop(transactionAmount)
            val transactionKopVisa = transactionKop * VISA_COMMISSION_PERCENT / 100
            val visaCommissionMinimalKop = rubToKop(VISA_COMMISSION_MINIMAL)
            val isLimitedTransaction = srcAccount.getMonthTransactionAmount() > MAESTRO_COMMISSION_MONTH_LIMIT

            val commission = when (accountType) {
                AccountTypes.MAESTRO, AccountTypes.MASTERCARD ->
                    if (isLimitedTransaction) {
                        transactionKop * MAESTRO_COMMISSION_LIMITED_PERCENT / 100 + rubToKop(
                            MAESTRO_COMMISSION_LIMITED_ADD
                        )
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

        fun printCommission(srcAccount: MoneyAccount, transactionAmount: Long) {
            val commission = calcCommission(srcAccount, transactionAmount)
            println("Перевод с карты ${srcAccount.getAccountType()} на сумму $transactionAmount руб.")
            println("Комиссия по переводу: $commission руб. \n")
        }
    }
}