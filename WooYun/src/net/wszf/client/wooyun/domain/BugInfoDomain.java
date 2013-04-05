package net.wszf.client.wooyun.domain;

public class BugInfoDomain
	{
		
		
		public BugInfoDomain(String title, String date, String stauts, String link, String user_harmlevel, String corp_harmlevel, String corp_rank,
			String comment)
		{
			super();
			this.title = title;
			this.date = date;
			this.stauts = stauts;
			this.link = link;
			this.user_harmlevel = user_harmlevel;
			this.corp_harmlevel = corp_harmlevel;
			this.corp_rank = corp_rank;
			this.comment = comment;
		}
		private String title;
		private String date;
		private String stauts;
		private String link;
		private String user_harmlevel;
		private String corp_harmlevel;
		private String corp_rank;
		private String comment;
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
		public String getUser_harmlevel()
			{
				return user_harmlevel;
			}
		public String getCorp_harmlevel()
			{
				return corp_harmlevel;
			}
		public String getCorp_rank()
			{
				return corp_rank;
			}
		public String getComment()
			{
				return comment;
			}
	
	}
