package org.twinbee.costbasis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EtradeFileParser
{
	// Example string: stuff,stuff,Bought 30 VDE @ $73.58, stuff, stuff
	// \\s+ any whitespace
	// $(\\d+(\\.\\d{1,2})?) any number with a precision of two after a decimal
	// (decimal using . bc this is america)

	String boughtPatternString = ".*(Bought|Sold|BOT|SLD)\\s*(\\d*)\\s*(\\w*)\\s*\\@\\s+\\$(\\d+(\\.\\d{1,2})?).*";
	Pattern boughtPattern = Pattern.compile(boughtPatternString);

	public List<TransactionRecord> parse(String filePath)
	{
		List<TransactionRecord> ts = new ArrayList<>();

		try
		{
			File f = new File(filePath);

			BufferedReader b = new BufferedReader(new FileReader(f));

			String readLine = "";

			while ((readLine = b.readLine()) != null)
			{

				Matcher bpMatcher = boughtPattern.matcher(readLine);
				if (bpMatcher.matches())
				{

					// System.out.println(readLine);
					//
					// for (int i = 0; i < bpMatcher.groupCount(); i++)
					// {
					// System.out.println(i + ":" + bpMatcher.group(i).trim());
					//
					// }

					if (bpMatcher.groupCount() > 4)
					{
						TransactionRecord.TYPE type = TransactionRecord.TYPE.OTHER;

						if (bpMatcher.group(1).toLowerCase().contains("b"))
						{
							type = TransactionRecord.TYPE.BUY;
						} else if (bpMatcher.group(1).toLowerCase().contains("s"))
						{
							type = TransactionRecord.TYPE.SELL;
						}

						TransactionRecord transaction = new TransactionRecord(type, bpMatcher.group(3),
								Double.parseDouble(bpMatcher.group(4)), Double.parseDouble(bpMatcher.group(2)),
								Instant.now());

						ts.add(transaction);
					}

				}
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return ts;
	}
}
