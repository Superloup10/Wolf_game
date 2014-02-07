/*package fr.wolf.config;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerManager
{
	public static LoggerManager instance;
	private Calendar calendar;
	private Logger logger;
	
	public LoggerManager(Logger logger, Path logFile)
	{
		this.logger = logger;
		this.logger.addHandler(new LogHandler(logFile));
		instance = this;
	}
	
	public Logger getLogger()
	{
		return this.logger;
	}
	
	public String getDate()
	{
		return (calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
	}
	
	private class LogHandler extends Handler
	{
		private BufferedWriter writer = null;
		
		public LogHandler(Path logFile)
		{
			try
			{
				if(Files.exists(logFile))
				{
					Files.delete(logFile);
					Files.createFile(logFile);
				}
				else
				{
					Files.createFile(logFile);
				}
				this.writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8);
			}catch(Exception e)
			{
				System.out.println("[GRAVE] Erreur lors de la création/délétion du fichier log.");
			}
		}
		
		@Override
		public void publish(LogRecord record)
		{
			try
			{
				writer.write(record.getMessage("<" + getDate() + "> [" + record.getLevel().getLocalizedName() + "] " + record.getMessage()) + "\n");
			}catch(Exception e)
			{
				System.err.println("[GRAVE] Erreur lors de l'écriture du fichier log.");
				e.printStackTrace();
			}
		}

		@Override
		public void flush()
		{
			
		}

		@Override
		public void close() throws SecurityException
		{
			System.out.println("<" + getDate() + "> [Infos] Fermeture des ressources...");
			try
			{
				writer.flush();
				writer.close();
			}catch(Exception e){}
			System.out.println("<" + getDate() + "> [Infos] OK !");
		}
	}
}*/