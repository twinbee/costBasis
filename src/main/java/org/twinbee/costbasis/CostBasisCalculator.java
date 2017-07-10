package org.twinbee.costbasis;

import java.util.HashMap;
import java.util.List;

import org.twinbee.costbasis.TransactionRecord.TYPE;

public class CostBasisCalculator
{

	HashMap<String, Double> getCostBasis(List<TransactionRecord> transactions)
	{
		HashMap<String, Double> currentTotals = new HashMap<>();

		// TODO sort on date
		for (TransactionRecord t : transactions)
		{
			String sym = t.symbol.trim();

			if (currentTotals.get(sym) == null)
			{
				currentTotals.put(sym, 0d);
			}

			if (t.transactionType.equals(TYPE.BUY))
			{
				currentTotals.put(sym, currentTotals.get(sym) + t.price * t.quantity);
			} else if (t.transactionType.equals(TYPE.SELL))
			{
				currentTotals.put(sym, currentTotals.get(sym) - t.price * t.quantity);
			}

		}

		return currentTotals;
	}

	// TODO make a prettier-print than the one java collections provides
	String getCostBasisReport(List<TransactionRecord> transactions)
	{
		HashMap<String, Double> currentTotals = getCostBasis(transactions);

		return currentTotals.toString();
	}

}
