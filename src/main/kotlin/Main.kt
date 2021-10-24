fun main() {

    val mastercardAccount = MoneyAccount(AccountTypes.MASTERCARD)
    VkMoneyTransaction.printCommission(mastercardAccount, 1000)
    VkMoneyTransaction.printCommission(mastercardAccount, 74_001)

    val maestroAccount = MoneyAccount(AccountTypes.MAESTRO)
    VkMoneyTransaction.printCommission(maestroAccount, 200_000)

    val visaAccount = MoneyAccount(AccountTypes.VISA)
    VkMoneyTransaction.printCommission(visaAccount, 1000)

    val mirAccount = MoneyAccount(AccountTypes.MIR)
    VkMoneyTransaction.printCommission(mirAccount, 10_000)

    val vkPayAccount = MoneyAccount(AccountTypes.VK_PAY)
    VkMoneyTransaction.printCommission(vkPayAccount, 1_000_000)
}