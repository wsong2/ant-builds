package qktest;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SearchEngine
{
	private FileFilter filter;
	private Map<String, String> contentMap = new HashMap<>();
	
	public SearchEngine(FileFilter filter)
	{
		this.filter = filter;
	}
	
	public void gatherFiles(Collection<File> files)
	{
		List<File> fileQueue = new LinkedList<>(files);
		
		while (!fileQueue.isEmpty())
		{
			File f = fileQueue.remove(0);
			if (f.isDirectory())
			{
				File[] fs = f.listFiles(filter);
				fileQueue.addAll(Arrays.asList(fs));
			}
			contentMap.put(f.getPath(), null);
		}
	}
	
	public static void searchWords(String sLine)
	{
		for (String s: Tokenizer.splitLine(sLine))
		{
			System.out.println("# " + s);
		}
	}
	
}
