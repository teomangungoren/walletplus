package com.walletplus.wallet.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Transaction(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id:String?,
        val amount:BigDecimal?,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        val transactionDate:LocalDateTime?,

        @ManyToOne(fetch =FetchType.LAZY, optional = false)
        @JoinColumn(name="account_id", nullable = false)
        val account:Account
){
    constructor(amount: BigDecimal?, transactionDate: LocalDateTime, account: Account):this(
            id=null,
            amount,
            transactionDate,
            account
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Transaction

        if (id != other.id) return false
        if (amount != other.amount) return false
        if (transactionDate != other.transactionDate) return false
        return account == other.account
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (amount?.hashCode() ?: 0)
        result = 31 * result + (transactionDate?.hashCode() ?: 0)
        return result
    }

}
