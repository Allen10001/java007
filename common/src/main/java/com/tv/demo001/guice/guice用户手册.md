## Overview
https://github.com/google/guice/wiki/
## Motivation  (动机)
https://github.com/google/guice/wiki/Motivation
>Unfortunately, now the clients of BillingService need to lookup its dependencies. We can fix some of these by applying the pattern again! Classes that depend on it can accept a BillingService in their constructor. For top-level classes, it's useful to have a framework. Otherwise you'll need to construct dependencies recursively when you need to use a service:

``` public static void main(String[] args) {
CreditCardProcessor processor = new PaypalCreditCardProcessor();
TransactionLog transactionLog = new DatabaseTransactionLog();
BillingService billingService
= new RealBillingService(processor, transactionLog);
...
}

