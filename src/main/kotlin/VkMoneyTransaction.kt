class VkMoneyTransaction {
    companion object {
        fun calcCommission(srcAccount: MoneyAccount, transactionAmount: Long) {
            srcAccount.setCurTransaction(transactionAmount)
            val commission = srcAccount.getCommission()
            println("Перевод с карты ${srcAccount.getAccountType()} на сумму $transactionAmount руб.")
            println("Комиссия по переводу: $commission руб. \n")
        }
    }
}