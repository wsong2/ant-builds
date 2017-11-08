package qktest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer
{
	private final static char CHAR_DOUBLE_QUOTE = '"';
	private final static char CHAR_SIMPLE_QUOTE = '\'';
	
	private Tokenizer()
	{	
	}
	
	public static List<String> splitLine(String lineLn)
	{
		String line = lineLn;
		// javaScript style text quote
		List<String> units = new ArrayList<>();
		
		while (true)
		{
			int pos = nextPosition(line, CHAR_DOUBLE_QUOTE, units);
			if (pos < 0)
				pos = nextPosition(line, CHAR_SIMPLE_QUOTE, units);
			if (pos < 0)
				break;
			line = line.substring(pos);
		}
		String[] ws = line.split("\\s+");
		units.addAll(Arrays.asList(ws));
		return units;
	}
	
	private static int nextPosition(String line, char ch, List<String> units)
	{
		int iStart = line.indexOf(ch);		
		if (iStart < 0)
			return -1;
		
		int iEnd = line.indexOf(ch, iStart+1);
		if (iEnd < 0)
			return -1;
		
		String[] ws = line.substring(0, iStart).split("\\s+");
		units.addAll(Arrays.asList(ws));
		if (iEnd > (iStart+1) )
			units.add(line.substring(iStart+1, iEnd));
		return iEnd+1;
	}	
}
