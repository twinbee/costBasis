package org.twinbee.costbasis;

import java.time.Instant;

public class TransactionRecord
{
	public enum TYPE
	{
		BUY, SELL, OTHER
	}

	String symbol;
	double price;

	double quantity;

	TYPE transactionType;

	Instant transactionTime;

	public TransactionRecord(TYPE transactionType, String symbol, double price, double quantity,
			Instant transactionTime)
	{
		super();
		this.symbol = symbol;
		this.price = price;
		this.quantity = quantity;
		this.transactionType = transactionType;
		this.transactionTime = transactionTime;
	}

	@Override
	public String toString()
	{
		return "TransactionRecord [symbol=" + symbol + ", price=" + price + ", quantity=" + quantity
				+ ", transactionType=" + transactionType + ", transactionTime=" + transactionTime + "]";
	}

}
