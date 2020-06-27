package com.mbn.elkhodary.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WalletTransaction {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("provider")
    @Expose
    public String provider;
    @SerializedName("balance")
    @Expose
    public String balance;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("topup_page")
    @Expose
    public String topupPage;
    @SerializedName("thankyou")
    @Expose
    public String thankyou;
    @SerializedName("thankyou_endpoint")
    @Expose
    public String thankyouEndpoint;
    @SerializedName("transactions")
    @Expose
    public List<Transaction> transactions = null;
    @SerializedName("status")
    @Expose
    public String status;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTopupPage() {
        return topupPage;
    }

    public void setTopupPage(String topupPage) {
        this.topupPage = topupPage;
    }

    public String getThankyou() {
        return thankyou;
    }

    public void setThankyou(String thankyou) {
        this.thankyou = thankyou;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThankyouEndpoint() {
        return thankyouEndpoint;
    }

    public void setThankyouEndpoint(String thankyouEndpoint) {
        this.thankyouEndpoint = thankyouEndpoint;
    }

    public class Transaction {

        @SerializedName("transaction_id")
        @Expose
        private String transactionId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("details")
        @Expose
        private String details;
        @SerializedName("date")
        @Expose
        private String date;

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

    }
}
