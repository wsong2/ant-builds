package qktest.file;

import java.io.File;
import java.io.FileFilter;

public class SimpleFileFilter implements FileFilter
{
	private SimpleFileFilter()
	{	
	}
	
	public static FileFilter getInstance()
	{
		return new SimpleFileFilter();
	}
	
	@Override
	public boolean accept(File pathname)
	{
		if (pathname.isHidden())
			return false;
		if (!pathname.canRead())
			return false;
		if (!pathname.isDirectory())
		{
			// I could include HTML file if I included a HTML utility library in this project.
			// Need to decode HTML entities to deal with HTML.
			// Similar situatiion for .doc files
			return (pathname.getName().toLowerCase().endsWith(".txt"));			
		}
		return true;
	}
}
