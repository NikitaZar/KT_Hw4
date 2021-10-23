class MoneyAccount(private val accountType: AccountTypes = AccountTypes.VK_PAY) {

    private var curTransactionAmount = 0L
    private var monthTransactionAmount = 0L

    fun getAccountType() = accountType
    fun getMonthTransactionAmount() = monthTransactionAmount

    fun setCurTransaction(amount: Long) {
        curTransactionAmount = amount
        monthTransactionAmount += amount
    }


}