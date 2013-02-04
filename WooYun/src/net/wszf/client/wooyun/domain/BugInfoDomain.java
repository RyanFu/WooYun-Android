package net.wszf.client.wooyun.domain;

public class BugInfoDomain
	{
		
		public BugInfoDomain(String title, String date, String stauts, String link)
		{
			super();
			this.title = title;
			this.date = date;
			this.stauts = stauts;
			this.link = link;
		}
		private String title;
		private String date;
		private String stauts;
		private String link;
		public String getTitle()
			{
				return title;
			}
		public String getDate()
			{
				return date;
			}
		public String getStauts()
			{
				return stauts;
			}
		public String getLink()
			{
				return link;
			}
	
	}
