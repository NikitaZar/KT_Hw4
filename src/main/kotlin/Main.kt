fun main(args: Array<String>) {
    val mastercardAccount = MoneyAccount(AccountTypes.MASTERCARD)
    VkMoneyTransaction.calcCommission(mastercardAccount, 1000)
    VkMoneyTransaction.calcCommission(mastercardAccount, 74_001)

    val maestroAccount = MoneyAccount(AccountTypes.MAESTRO)
    VkMoneyTransaction.calcCommission(maestroAccount, 200_000)

    val visaAccount = MoneyAccount(AccountTypes.VISA)
    VkMoneyTransaction.calcCommission(visaAccount, 1000)

    val mirAccount = MoneyAccount(AccountTypes.MIR)
    VkMoneyTransaction.calcCommission(mirAccount, 10_000)

    val vkPayAccount = MoneyAccount(AccountTypes.VK_PAY)
    VkMoneyTransaction.calcCommission(vkPayAccount, 1_000_000)

}